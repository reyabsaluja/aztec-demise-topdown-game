import java.awt.event.*;

public class MyKeyListener implements KeyListener {

	public boolean[] keys = new boolean[120];

    public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(keyCode < keys.length)
			keys[keyCode] = true;
	}
    public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if(keyCode < keys.length)
			keys[keyCode] = false;
    }
    public void keyTyped(KeyEvent e) {}

	/** 
	 * @return boolean
	 */
	public boolean up()
	{
		return keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
	}

	/** 
	 * @return boolean
	 */
	public boolean down()
	{
		return keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN];
	}
	
	/** 
	 * @return boolean
	 */
	public boolean left()
	{
		return keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
	}
	
	/** 
	 * @return boolean
	 */
	public boolean right()
	{
		return keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
	}

	/** 
	 * @return boolean
	 */
	public boolean run() {
		return keys[KeyEvent.VK_R];
	}

	/** 
	 * @return boolean
	 */
	public boolean interact() {
		return keys[KeyEvent.VK_SPACE];
	}
}