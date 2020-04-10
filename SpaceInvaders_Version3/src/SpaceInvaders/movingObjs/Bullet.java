package SpaceInvaders.movingObjs;

import javafx.scene.Group;

public class Bullet extends MovableObject {
	public String direction;

	
	public Bullet (double xPos, double yPos,int health,String type,Group root) {
		super(xPos,yPos,health,type,root);
		if(type == "ShipBullet") direction = "Up";
		else if(type == "AlienBullet") direction = "Down";
	}

	public void move() {
		if (direction == "Up") {
			imageView.setLayoutY(imageView.getLayoutY()-2);
			yPos -=2;
		}
		if (direction == "Down") {
			imageView.setLayoutY(imageView.getLayoutY()+2);
			yPos +=2;
		}
	}


}