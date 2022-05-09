import java.awt.Font;
import java.awt.Graphics2D;

public class Texto {
	public String texto;
	public Font fuente;
	public double x, y;
	
	public Texto(String texto, double x, double y) {
		this.fuente = new Font("Arial", Font.BOLD, Constantes.TAM_TEXTO);
		this.texto = texto;
		this.x = x;
		this.y = y;
	}
	
	public void dibujar(Graphics2D g2d) {
		g2d.setColor(Constantes.COLOR_TEXTO);
		g2d.setFont(fuente);
		g2d.drawString(texto, (float) x, (float) y);
		
	}
}
