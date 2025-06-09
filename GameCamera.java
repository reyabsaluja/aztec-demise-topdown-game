/**
 * The GameCamera class is an offset calculation used for making relative movement.
 * 
 * @see Rect
 * @see Text
 * @see MenuItem
 * @author Reyab Saluja
 * @version "1.8.0_322"
 */

public class GameCamera {
	
	private Game game;
	private int xOffset, yOffset;
	
	public GameCamera(Game game, int xOffset, int yOffset){
		this.game = game;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	public void checkBlankSpace() {
		if (this.xOffset < 0) {
			this.xOffset = 0;
		} else if(this.xOffset > Const.WORLD_WIDTH - Const.SCREEN_WIDTH) {
			xOffset = Const.WORLD_WIDTH - Const.SCREEN_WIDTH;
		}
		if (this.yOffset < 0) {
			this.yOffset = 0;
		} else if(this.yOffset > Const.WORLD_HEIGHT - Const.SCREEN_HEIGHT) {
			yOffset = Const.WORLD_HEIGHT - Const.SCREEN_HEIGHT;
		}
	}
	
	/** 
	 * Method to center gameCamera on player
	 * 
	 * @param character
	 */
	public void centerOnPlayer(Character character){
		this.xOffset = character.getX() - Const.SCREEN_WIDTH / 2 + (24 / 2);
		this.yOffset = character.getY() - Const.SCREEN_HEIGHT / 2 + (30 / 2);
		checkBlankSpace();
	}
	
	/** 
	 * Method to move an
	 * 
	 * @param xAmt
	 * @param yAmt
	 */
	public void move(int xAmt, int yAmt){
		xOffset += xAmt;
		yOffset += yAmt;
		checkBlankSpace();
	}
	public int getXOffset() {
		return xOffset;
	}
	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}
	public int getYOffset() {
		return yOffset;
	}
	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}
}