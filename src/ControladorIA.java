public class ControladorIA {
	
	public Rectangulo ia, bola;
	public ContBola contBola;
	private int PunJ1;
	
	public ControladorIA(Rectangulo ia, ContBola contBola, Rectangulo bola, int PunJ1) {
		this.ia = ia;
		this.contBola = contBola;
		this.bola = bola;
		this.PunJ1 = PunJ1;
	}
	
	public int numAleatorio(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
	void bajar(double deltaTime) {
		ia.y = ( ia.y - Constantes.VELOCIDAD_IA  * deltaTime);
	}
	
	void subir(double deltaTime) {
		ia.y = ( ia.y + Constantes.VELOCIDAD_IA  * deltaTime);
	}
	
	public void update(double deltaTime) {
		
		/*if(contBola.irAbajo) {
			bajar(deltaTime);
		}
		
		if(!contBola.irAbajo) {
			subir(deltaTime);
		}*/
		
		if(ia.y-numAleatorio(-20, 20)>bola.y+numAleatorio(-20, 20) && PunJ1 < 5) {
			bajar(deltaTime);
		}else if(PunJ1 >= 5){
			if(ia.y>bola.y) {
				bajar(deltaTime);
			}
		}
		
		if(ia.y+numAleatorio(-20, 20)<=bola.y+numAleatorio(-20, 20) && PunJ1 < 5) {
			subir(deltaTime);
		}else if(PunJ1 >= 5){
			if(ia.y<=bola.y) {
				subir(deltaTime);
			}
		}
		
		if (ia.y < (0+Constantes.BARRA_ARRIBA_VENTANA)) {
			ia.y = 0+Constantes.BARRA_ARRIBA_VENTANA;
		}
		
		if (ia.y > (Constantes.ALTO_PANTALLA - ia.alto)) {
			ia.y = Constantes.ALTO_PANTALLA - ia.alto;
		}
		
	}
}
