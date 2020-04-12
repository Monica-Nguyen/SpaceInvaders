package textBased;
import spaceInvadersLogic.movingObjs.*;

public class gameObjects extends MovableObject{

	Boolean isVisible;
	public gameObjects(double xP, double yP, int h) {
		super(xP, yP, h);
	}
	
	public void remove() {
		isVisible = false;
	}

	public boolean onScreen() {
		return isVisible;
	}
	
	//makes the object take damage
    public void takeDamage(){
    	//this.health -= 1;
    }
	
}
