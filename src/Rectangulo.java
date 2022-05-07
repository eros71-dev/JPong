import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public class Rectangulo {
	public double x, y, ancho, alto;
	private Color color;
	
	public Rectangulo(double x, double y, double ancho, double alto, Color color) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.color = color;
	}
	
	public void dibujar(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.fill( new Rectangle2D.Double(x, y, ancho, alto) );
	}
}
