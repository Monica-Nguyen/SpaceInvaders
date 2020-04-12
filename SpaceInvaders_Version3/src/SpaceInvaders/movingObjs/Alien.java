package SpaceInvaders.movingObjs;

import javafx.scene.Group;



public class Alien extends MovableObject {
	double yMoveSpeed = 0.35;

	//CONSTRUCTOR
	public Alien (double xPos, double yPos, int health, String type, Group root) {
        super(xPos, yPos, health,type,root);
	}
	
	
	//this function moves the object
	public void move(String s) {
		if (s == "Right") {
			imageView.setLayoutX(imageView.getLayoutX()+2);
			xPos += 2;
		}
		if (s == "Left") {
			imageView.setLayoutX(imageView.getLayoutX()-2);
			xPos -=2;
		}
		if (s == "Down") {
			imageView.setLayoutY(imageView.getLayoutY()+0.35);
			yPos += yMoveSpeed;
		}
	}

}
	                 
                    
	
