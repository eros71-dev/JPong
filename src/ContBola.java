import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ContBola {
	
	public Rectangulo bola, j1, j2;
	public Texto PuntJ1, PuntJ2;
	boolean irAbajo, irDerecha;
	Random rd = new Random(); //crear objeto random
	
	public ContBola(Rectangulo rectangulo, Rectangulo j1, Rectangulo j2, Texto PuntJ1, Texto PuntJ2) {
		this.bola = rectangulo;
		this.j1 = j1;
		this.j2 = j2;
		this.PuntJ1 = PuntJ1;
		this.PuntJ2 = PuntJ2;
		irAbajo = rd.nextBoolean();
		irDerecha = rd.nextBoolean();
	}
	

	
	public static boolean entre(double i, double minValorInclusivo, double maxValorInclusivo) {
	    if (i >= minValorInclusivo && i <= maxValorInclusivo)
	        return true;
	    else
	        return false;
	}
	
	public void update(double deltaTime) throws MalformedURLException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		//bordes de la pantalla
		if(bola.x <= 0) {
			Ventana.PuntuacionJ2 = Ventana.PuntuacionJ2 + 1;
			this.PuntJ2.texto = "" + Ventana.PuntuacionJ2;
			ReproductorSonidos.reproducir("sonidos/point.wav");
			Constantes.VELOCIDAD_IA -= 20;
			Constantes.VELOCIDAD_BOLA = 200;
			Constantes.VELOCIDAD_JUGADOR = 200;
			bola.x = (Constantes.ANCHO_PANTALLA/2);
			bola.y = (Constantes.ALTO_PANTALLA/2);
			irAbajo = rd.nextBoolean();
			irDerecha = rd.nextBoolean();
		}
		
		else if(bola.x >= Constantes.ANCHO_PANTALLA - bola.ancho) {
			Ventana.PuntuacionJ1 = Ventana.PuntuacionJ1 + 1;
			this.PuntJ1.texto = "" + Ventana.PuntuacionJ1;
			ReproductorSonidos.reproducir("sonidos/point.wav");
			Constantes.VELOCIDAD_IA += 40;
			Constantes.VELOCIDAD_BOLA = 200;
			Constantes.VELOCIDAD_JUGADOR = 200;
			bola.x = (Constantes.ANCHO_PANTALLA/2);
			bola.y = (Constantes.ALTO_PANTALLA/2);
			irAbajo = rd.nextBoolean();
			irDerecha = rd.nextBoolean();
		}
		
		if(irAbajo) {
			bola.y = bola.y - Constantes.VELOCIDAD_BOLA * deltaTime;
		}
		
		if (!irAbajo) {
			bola.y = bola.y + Constantes.VELOCIDAD_BOLA * deltaTime;
		}
		
		if (irDerecha) {
			bola.x = bola.x + Constantes.VELOCIDAD_BOLA * deltaTime;
		}
		
		if (!irDerecha) {
			bola.x = bola.x - Constantes.VELOCIDAD_BOLA * deltaTime;
		}
		
		if(bola.y <= (0+Constantes.BARRA_ARRIBA_VENTANA)) {
			bola.y = 0+Constantes.BARRA_ARRIBA_VENTANA;
			irAbajo = !irAbajo;
			ReproductorSonidos.reproducir("sonidos/beep.wav");
			Constantes.VELOCIDAD_BOLA +=5;
			Constantes.VELOCIDAD_JUGADOR +=5;
		}
		
		else if(bola.y >= Constantes.ALTO_PANTALLA - bola.alto ) {
			bola.y = Constantes.ALTO_PANTALLA - bola.alto;
			irAbajo = !irAbajo;
			ReproductorSonidos.reproducir("sonidos/beep.wav");
			Constantes.VELOCIDAD_BOLA +=5;
			Constantes.VELOCIDAD_JUGADOR +=5;
		}
		
		//Si la bola esta en el area de la pala del jugador 1 o 2
		//Seguro que hay formas mas limpias de implementarlo pero esta es la que se me ha ocurrido
		//Si se da con el lado horizontal de la pala se bugea
		if( entre(bola.x, (j1.x-Constantes.ANCHO_PALA), (j1.x+Constantes.ANCHO_PALA) ) ) { 
			if( entre(bola.y, (j1.y-Constantes.COMPENSACION_DELAY), (j1.y+Constantes.ALTO_PALA) ) ) {
				irDerecha = true;
				ReproductorSonidos.reproducir("sonidos/beep.wav");
				Constantes.VELOCIDAD_BOLA +=5;
				Constantes.VELOCIDAD_JUGADOR +=5;
			}
		}
		
		if( entre(bola.x, (j2.x-Constantes.ANCHO_PALA), (j2.x+Constantes.ANCHO_PALA) ) ) { 
			if( entre(bola.y, (j2.y-Constantes.COMPENSACION_DELAY), (j2.y+Constantes.ALTO_PALA) ) ) {
				irDerecha = false;
				ReproductorSonidos.reproducir("sonidos/beep.wav");
				Constantes.VELOCIDAD_BOLA +=5;
				Constantes.VELOCIDAD_JUGADOR +=5;
			}
		}
		
		
	}
}