package SpaceInvaders.movingObjs;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MovableObject {
    double xPos,yPos;   // the origin point of the object
    int health;         // the health of the object
    String type;
    public ImageView imageView;
	public Image image;

	//images of the ship and alien
    public final Image alienImage = new Image(getClass().getResourceAsStream("/res/Alien1.png"));
    public final Image shipImage = new Image(getClass().getResourceAsStream("/res/ship.png"));
    //images of the bullets
    public final Image shipBulletImage = new Image(getClass().getResourceAsStream("/res/ShipBullet.png"));
    public final Image alienBulletImage = new Image(getClass().getResourceAsStream("/res/AlienBullet.png"));

    // the default constructor for the class
    public MovableObject(double xP, double yP, int h ,String type, Group root){
        xPos = xP;
        yPos = yP;
        health = h;
        if (type == "Ship") image = shipImage;
        else if(type == "Alien") image = alienImage;
        else if(type == "ShipBullet") image = shipBulletImage;
        else if(type == "AlienBullet") image = alienBulletImage;
        this.type = type;
        this.imageView = getImageView();
        root.getChildren().addAll(imageView);
    }


    public ImageView getImageView() {
        ImageView i = new ImageView(image);
        i.setX(xPos);
        i.setY(yPos);
        return i;
    }
    //*****GETTER AND SETTER USING IMAGES*******

    public double getX(){return this.xPos + 0;}
    public double getY(){return this.yPos + 0;}
    

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
        if (s == "Up") {
            imageView.setLayoutY(imageView.getLayoutY()-2);
            yPos -=2;
        }
        if (s == "Down") {
            imageView.setLayoutY(imageView.getLayoutY()+2);
            yPos +=2;
        }
    }
}


