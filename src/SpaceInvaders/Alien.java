package SpaceInvaders;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;



public class Alien extends MovableObject{
	
	Image image;
	ImageView imageView;
	String dir = "right";
	double yMoveSpeed = 0.1;

	//CONSTRUCTOR
	public Alien (int xPos, int yPos, int extend, int health, Image image, Group root) {
        super(xPos, yPos, extend, health);
        this.image = image;
        this.imageView = getImageView(xPos,yPos);
        root.getChildren().addAll(imageView);
	}
	
	
	//Will return imageview that we will use within class to add to root
		//used during the creation of the image
	private ImageView getImageView(double xPos, double yPos) {
		ImageView image = new ImageView(getImage());
		image.setX(xPos);
		image.setY(yPos);
		return image;
	}
	
	
	public void move(int xMoveSpeed) {
		//moving the alien sprite to a direct coordinate
		imageView.setLayoutX(imageView.getLayoutX()+xMoveSpeed);
		imageView.setLayoutY(imageView.getLayoutY()+yMoveSpeed);
	}

	//*****GETTER AND SETTER USING IMAGES*******
	public void setImage(Image image) {
      	this.image = image;
            }
    public Image getImage() {
           return image;
               }
}
	                 
                    
	
