package SpaceInvaders;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.util.Duration;


public class Ship extends MovableObject{
	
	public final Image shipImage = new Image(getClass().getResourceAsStream("/res/ship.png"));
	public ImageView imageView;

	
	public Ship (double xPos, double yPos, int extend, int health, Image image, Group root) {
        super(xPos, yPos, extend, health);
        this.image = shipImage;
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
			imageView.setLayoutX(imageView.getLayoutX()+4);
			xPos += 4;
		}
		if (direction == "Left") {
			imageView.setLayoutX(imageView.getLayoutX()-4);
			xPos -=4;
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
