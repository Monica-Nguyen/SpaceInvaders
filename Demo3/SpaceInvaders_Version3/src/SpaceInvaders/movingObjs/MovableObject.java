package SpaceInvaders.movingObjs;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class MovableObject {

    protected double xPos,yPos;   // the origin point of the object
    public int extend;
    private int health;         // the health of the object

    private String type;
    protected ImageView imageView;
	protected Image image;

	//images of the ship and alien
    public final Image alienImage = new Image(getClass().getResourceAsStream("/res/Alien1.png"));
    public final Image shipImage = new Image(getClass().getResourceAsStream("/res/ship.png"));
    
    //images of the bullets
    public final Image shipBulletImage = new Image(getClass().getResourceAsStream("/res/ShipBullet.png"));
    public final Image alienBulletImage = new Image(getClass().getResourceAsStream("/res/AlienBullet.png"));

    
    // constructor for text-based
    public MovableObject(double xP, double yP, int h){
        xPos = xP;
        yPos = yP;
        health = h;
    }
        
    // the default constructor for the GUI class
    public MovableObject(double xP, double yP, int h ,String type, Group root){
        xPos = xP;
        yPos = yP;
        extend = 50;
        health = h;
        //Based on the type of the object there is a different image
        this.type = type;
        if (type == "Ship") image = shipImage;
        else if(type == "Alien") image = alienImage;
        else if(type == "ShipBullet") image = shipBulletImage;
        else if(type == "AlienBullet") image = alienBulletImage;
        this.imageView = getImageView();
        root.getChildren().addAll(imageView);
    }

    //Makes a node with the appropriate object images 
    //For more information, visit: https://docs.oracle.com/javase/8/docs/api/javax/swing/text/html/ImageView.html
    //creates the image view
    public ImageView getImageView() {
        ImageView i = new ImageView(image);
        i.setX(xPos);
        i.setY(yPos);
        return i;
    }

    //checks the bounds of two points
    public boolean checkBounds(double p1, double p2, int e1, int e2){
        //checks to see if p2 is between p1 and p1+extend
        if (p1 <= p2 && p1 + e1 >= p2) return true;
        //checks to see if p1 is between p2 and p2+extend
        else if(p2<= p1 && p2 + e2 >= p1) return true;
        else return false;
    }

    //makes the object take damage
    public void takeDamage(){
    	this.health -= 1;
    }

    //changes the visibility of object to false if collision occurs
    public void remove() {
        imageView.setVisible(false);
    }

    //returns a boolean on whether the object is supposed to be visible (true) or not (false)
    public boolean onScreen() {
        return imageView.isVisible();
    }

    //Getters
    public double getX(){return xPos + 0;}
    public double getY(){return yPos + 0;}
    public int getExtend(){return extend + 0;}
    public String getType(){return String.valueOf(type);}
    public int getHealth() {return this.health;}

    //checks the collision between an object and a bullet
    public boolean bulletCollisionCheck(Bullet bullet){

        //checks to see if the bullet type aligns with the object type
        if((this.type == "Alien" && bullet.getType() == "ShipBullet")||(this.type == "Ship" && bullet.getType() == "AlienBullet") ) {

            //Checks to see if the x & y bound line up
            if (checkBounds(this.getX(), bullet.getX(), this.getExtend(), bullet.getExtend()) && checkBounds(this.getY(), bullet.getY(), this.getExtend(), bullet.getExtend()) )return true;
            else return false;

        }else return false;
    }

}


