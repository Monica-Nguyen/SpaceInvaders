package SpaceInvaders.movingObjs;

import javafx.scene.Group;



public class Alien extends MovableObject {
	double yMoveSpeed = 0.2;
	double xMoveSpeed = 2;

	//CONSTRUCTOR
	public Alien (double xPos, double yPos, int health, String type, double difficulty,Group root) {
        super(xPos, yPos, health,type,root);
        this.yMoveSpeed = yMoveSpeed + difficulty;
        this.xMoveSpeed = xMoveSpeed + difficulty;
	}
	
	
	//this function moves the object
	public void move(String s) {
		if (s == "Right") {
			imageView.setLayoutX(imageView.getLayoutX()+xMoveSpeed);
			xPos += xMoveSpeed;
		}
		if (s == "Left") {
			imageView.setLayoutX(imageView.getLayoutX()-xMoveSpeed);
			xPos -= xMoveSpeed;
		}
		if (s == "Down") {
			imageView.setLayoutY(imageView.getLayoutY()+yMoveSpeed);
			yPos += yMoveSpeed;
		}
	}

}
	                 
                    
	
