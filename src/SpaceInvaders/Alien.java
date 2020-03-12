package SpaceInvaders;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;



public class Alien extends MovableObject{

	Image image;
	
	//CONSTRUCTOR
	public Alien (int xPos, int yPos, int extend, int health, Image image, Group root) {
        super(xPos, yPos, extend, health);
        this.image = image;
        xSpeed = 50;
		ySpeed = 50;
        root.getChildren().addAll(getImageView(xPos,yPos));
        //this.imagePath = Alien.setImage(imagePath);
	}
	
	
	//Will return imageview that we will use within class to add to root 
	public ImageView getImageView(double xPos, double yPos) {
		ImageView i = new ImageView(getImage());
		i.setX(xPos);
		i.setY(yPos);
		return i;
	}


	//*****GETTER AND SETTER USING IMAGES*******
	public void setImage(Image image) {
      	this.image = image;
            }
            public Image getImage() {
           return image;
               }
}
                    
                    
                    
                    
	
