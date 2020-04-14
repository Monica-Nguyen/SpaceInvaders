package SpaceInvaders.movingObjs;

import javafx.scene.Group;

public class Ship extends MovableObject {
	//constructor for the ship
	public Ship (double xPos, double yPos, int health, String type, Group root) {
        super(xPos, yPos, health,type,root);
	}
	//unique movement fo the ship, limiting it to only lateral movement in order to prevent problems
	public void move(String s) {
		if (s == "Right") {
			imageView.setLayoutX(imageView.getLayoutX()+2);
			xPos += 2;
		}
		if (s == "Left") {
			imageView.setLayoutX(imageView.getLayoutX()-2);
			xPos -=2;
		}
	}
	
	
}