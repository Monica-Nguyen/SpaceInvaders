package SpaceInvaders;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.util.Duration;


public class Ship extends MovableObject{
	public Image image;
	public ImageView imageView;

	
	public Ship (int xPos, int yPos, int extend, int health, Image image, Group root) {
        super(xPos, yPos, extend, health);
        this.image = image;
        this.imageView = getImageView();
        root.getChildren().addAll(imageView);
	}
	
	public ImageView getImageView() {
		ImageView i = new ImageView(getImage());
		i.setX(xPos);
		i.setY(yPos);
		return i;
	}

	
    public void move(String direction) {
		TranslateTransition transition = new TranslateTransition(); 
		transition.setDuration(Duration.seconds(0.0125));
		if (direction == "Right") {
			xPos += 4;
			transition.setToX(xPos);
			transition.setCycleCount(1/60);
			transition.setNode(imageView);
			transition.play();
		}
		if (direction == "Left") {
			xPos -=4;
			transition.setToX(xPos);
			transition.setCycleCount(1/60);
			transition.setNode(imageView);
			transition.play();
		}
	}
	
    
	//*****GETTER AND SETTER USING IMAGES*******
	public void setImage(Image image) {
      	this.image = image;
	}
	public Image getImage() {
		return image;
	}
}
