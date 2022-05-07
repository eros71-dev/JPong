import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyList implements KeyListener{
	
	private boolean keyPressedArray[] = new boolean[128];
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		keyPressedArray[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyPressedArray[e.getKeyCode()] = false;
		
	}

	public boolean isKeyPressed(int keyCode) {
		return keyPressedArray[keyCode];
	}
	
}
