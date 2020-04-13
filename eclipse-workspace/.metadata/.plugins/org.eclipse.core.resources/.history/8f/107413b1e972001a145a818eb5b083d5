package SpaceInvaders;

import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Bullet extends MovableObject {
	
	private String type;

	public Image image;
	public ImageView imageView;
	public String Direction;

	
	public Bullet (double xPos, double yPos, int extend, Image image, String Direction,Group root) {
		super(xPos,yPos,extend);
        this.image = image;
        this.imageView = getImageView();
        this.Direction = Direction;
        root.getChildren().addAll(imageView);
        //this.type = type;
	}
	
	public ImageView getImageView() {
		ImageView i = new ImageView(getImage());
		i.setX(xPos);
		i.setY(yPos);
		return i;
	}

	
    public void move() {
		if (this.Direction == "Up") {
			imageView.setLayoutY(imageView.getLayoutY()-5);

		}
		if (this.Direction == "Down") {
			imageView.setLayoutY(imageView.getLayoutY()+5);

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