import java.awt.event.KeyEvent;

public class ControladorJugador {
	
	public Rectangulo jugador;
	public KeyList kl;
	
	public ControladorJugador(Rectangulo rectangulo, KeyList kl) {
		this.jugador = rectangulo;
		this.kl = kl;
	}
	
	public void update(double deltaTime) {
		if ( kl.isKeyPressed(KeyEvent.VK_DOWN )) {
			if ( (jugador.y + Constantes.VELOCIDAD_JUGADOR * deltaTime) + jugador.alto < Constantes.ALTO_PANTALLA - Constantes.BARRA_ABAJO_VENTANA) {
				this.jugador.y = (this.jugador.y + Constantes.VELOCIDAD_JUGADOR  * deltaTime);
			}
		} else if ( kl.isKeyPressed(KeyEvent.VK_UP) ) {
			if (jugador.y - Constantes.VELOCIDAD_JUGADOR * deltaTime > Constantes.BARRA_ARRIBA_VENTANA) {
				this.jugador.y = (this.jugador.y - Constantes.VELOCIDAD_JUGADOR  * deltaTime);
			}
		}
		
	}
}
