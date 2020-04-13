package SpaceInvaders.textBased;
import SpaceInvaders.movingObjs.*;

abstract class gameObjects extends MovableObject {
	
	Boolean isVisible;
	public static int score;
	
	public gameObjects(double xP, double yP, int h) {
		super(xP, yP, h);
	}
	
	public void remove() {
		isVisible = false;
	}

	public boolean onScreen() {
		return isVisible;
	}

}
