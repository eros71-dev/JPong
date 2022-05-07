import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class Ventana extends JFrame implements Runnable{
	
	public Graphics2D g2d;
	public KeyList kl = new KeyList();
	public Rectangulo jugadorUno, jugadorDos, bola;
	public ControladorJugador contJug;
	public ControladorIA contIA;
	public ContBola contBola;
	public Texto puntIzq, puntDer;
	
	public static int PuntuacionJ1;
	public static int PuntuacionJ2;
	public static int PuntosTotales;
	
	public Ventana() { //inicializacion
		this.setSize(Constantes.ANCHO_PANTALLA, Constantes.ALTO_PANTALLA);
		this.setTitle(Constantes.TITULO);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(kl);
		Constantes.BARRA_ARRIBA_VENTANA = this.getInsets().top;
		Constantes.BARRA_ABAJO_VENTANA = this.getInsets().bottom;
		g2d = (Graphics2D) this.getGraphics();
		
		PuntuacionJ1=0;
		PuntuacionJ2=0;
		PuntosTotales=10;
		
		puntIzq= new Texto(Integer.toString(PuntuacionJ1), Constantes.POS_X_TEXTO, Constantes.POS_Y_TEXTO);
		puntDer= new Texto(Integer.toString(PuntuacionJ2), Constantes.ANCHO_PANTALLA - Constantes.POS_X_TEXTO - Constantes.TAM_TEXTO, Constantes.POS_Y_TEXTO);
		
		jugadorUno = new Rectangulo(Constantes.ANCHO_PALA, (Constantes.ALTO_PANTALLA/6), Constantes.ANCHO_PALA, Constantes.ALTO_PALA, Color.white);
		contJug = new ControladorJugador(jugadorUno, kl);
		jugadorDos = new Rectangulo( (Constantes.ANCHO_PANTALLA - Constantes.ANCHO_PALA*2), (Constantes.ALTO_PANTALLA/6), Constantes.ANCHO_PALA, Constantes.ALTO_PALA, Color.white);
		bola = new Rectangulo( (Constantes.ANCHO_PANTALLA/2) , (Constantes.ALTO_PANTALLA/2), Constantes.SIZE_BOLA, Constantes.SIZE_BOLA, Constantes.COLOR_BOLA);
		contBola = new ContBola(bola, jugadorUno, jugadorDos, puntIzq, puntDer);
		contIA = new ControladorIA(jugadorDos, contBola, bola, PuntuacionJ1);
	}
	
	public void update(double deltaTime) throws MalformedURLException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		//Para prevenir parpadeos y otros problemas raros de JFrame si no se limitan los FPS
		Image imagenDobleBuffer = createImage( getWidth(), getHeight() );
		Graphics graficoDobleBuffer = imagenDobleBuffer.getGraphics();
		this.dibujar(graficoDobleBuffer);
		g2d.drawImage(imagenDobleBuffer, 0, 0, this);

		
		contJug.update(deltaTime);
		contBola.update(deltaTime);
		contIA.update(deltaTime);
		
		if(PuntuacionJ1==PuntosTotales || PuntuacionJ2==PuntosTotales) {
			ReproductorSonidos.reproducir("sonidos/win.wav");
			PuntuacionJ1=0;
			PuntuacionJ2=0;
			puntIzq.texto = "" + PuntuacionJ1;
			puntDer.texto = "" + PuntuacionJ2;
			jugadorUno.y=(Constantes.ALTO_PANTALLA/6);
			jugadorDos.y=(Constantes.ALTO_PANTALLA/6);
		}

		//System.out.println("Han pasado " + deltaTime + " segundos desde el ultimo fotograma.");
		//System.out.println(1 / deltaTime + " fps");
	}
	
	public void dibujar(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, Constantes.ANCHO_PANTALLA, Constantes.ALTO_PANTALLA);
		
		puntIzq.dibujar(g2d);
		puntDer.dibujar(g2d);
		jugadorUno.dibujar(g2d);
		jugadorDos.dibujar(g2d);
		bola.dibujar(g2d);
	}
	
	@Override
	public void run() {
		double lastFrameTime = 0.0;
		while (true) {
			double time = Time.getTime();
			double deltaTime = time - lastFrameTime;
			lastFrameTime = time;
			
			try {
				update(deltaTime);
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try { //Limitar FPS para que no se queme la grafica XD
				Thread.sleep(Constantes.LIMITADOR_FPS);
			} catch (Exception e) {
				
			}
			
		}
	}

}
