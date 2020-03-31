package SpaceInvaders;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bullet extends MovableObject {
	
	public Image image;
	public ImageView imageView;
	public String direction;

	
	public Bullet (double xPos, double yPos, int extend, Image image, String direction,Group root) {
		super(xPos,yPos,extend);
        this.image = image;
        this.imageView = getImageView();
        this.direction = direction;
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
		if (this.direction == "Up") {
			imageView.setLayoutY(imageView.getLayoutY()-5);

		}
		if (this.direction == "Down") {
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