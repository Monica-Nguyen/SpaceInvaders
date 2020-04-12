package textBased;
import spaceInvadersLogic.movingObjs.*;

public class gameObjects extends MovableObject{

	public gameObjects(double xP, double yP, int h) {
		super(xP, yP, h);
	}
	
	public void remove() {
		
	}

	public boolean onScreen() {
		return imageView.isVisible();
	}
	
	//makes the object take damage
    public void takeDamage(){
    	//this.health -= 1;
    }
	
}
