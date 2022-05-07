import java.awt.Color;

public class Constantes {
	//Ventana
	public static final int ANCHO_PANTALLA = 800;
	public static final int ALTO_PANTALLA = 600;
	public static final String TITULO = "Pong!";
	public static final int LIMITADOR_FPS = 13; //Alrededor de 60fps
	public static double BARRA_ARRIBA_VENTANA;
	public static double BARRA_ABAJO_VENTANA;
	public static final Color COLOR_TEXTO = Color.white;
	public static final int POS_X_TEXTO = 100;
	public static final int POS_Y_TEXTO = 100;
	public static final int TAM_TEXTO = 40;
	//Jugadores
	public static final double ANCHO_PALA = 24;
	public static final double ALTO_PALA = 140;
	public static final Color COLOR_JUGADOR = Color.white;
	public static int VELOCIDAD_JUGADOR = 200;
	public static int VELOCIDAD_IA = VELOCIDAD_JUGADOR;
	//Bola
	public static final double SIZE_BOLA = 20;
	public static final Color COLOR_BOLA = Color.white;
	public static double VELOCIDAD_BOLA = 200;
	public static final int COMPENSACION_DELAY = 14;
}
