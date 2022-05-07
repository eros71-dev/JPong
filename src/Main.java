
public class Main {

	public static void main(String[] args) {
	Ventana ventana = new Ventana();
	Thread t1 = new Thread(ventana);
	t1.start();
	}

}
