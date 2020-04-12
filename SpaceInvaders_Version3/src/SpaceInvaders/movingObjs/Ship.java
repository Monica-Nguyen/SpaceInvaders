package SpaceInvaders.movingObjs;

import javafx.scene.Group;

public class Ship extends MovableObject {
	public Ship (double xPos, double yPos, int health, String type, Group root) {
        super(xPos, yPos, health,type,root);
	}
	
	public void takeDamage() {
	
	}
	
	
}