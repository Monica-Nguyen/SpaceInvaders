package SpaceInvaders;

import javafx.scene.Group;
import javafx.scene.image.Image;

public class MovableObject {
    int xPos,yPos;      // the origin point of the object
    int extend;         // the amount the sprite extends past the original cord
    int xSpeed, ySpeed; // the direction that the object is going in
    int speed = 10;     // default speed
    int health;         // the health of the object
	public Image image;
	public Group root;


    // the default constructor for the class
    public MovableObject(int xP, int yP, int e, int h ){
        xPos = xP;
        yPos = yP;
        extend = e;
        health = h;
    }
    //constructor for the bullet 
    public MovableObject(int xP, int yP, int e)
    {
        xPos = xP;
        yPos = yP;
        extend = e;
    }
    

    //this function moves the object
    public void move(String s) {
        if (s == "R"){
            if (this.xPos<550)
            	{this.xPos += xSpeed;
            	}
        }else if (s == "L"){
            if (this.xPos>50) 
            {this.xPos -= xSpeed;
            }
        }else if (s == "U"){
            if (this.yPos > 50) 
            	{this.yPos -= ySpeed;
            	}
        }else if (s == "D"){
            if (this.yPos < 550) 
            	{this.yPos += ySpeed;
            	}
        }
    }


	//checks collision with the bullet
    public void colCheck(Bullet bullet){
        // checks if the bullet xPos is within the moving object yPos + extend
        if (bullet.xPos >= this.xPos && bullet.xPos <= this.xPos+this.extend){
            //checks if the bullet yPos is within the moving object yPos + extend
            if(bullet.yPos >= this.yPos && bullet.yPos <= this.yPos+this.extend)
                this.takeDamage();
            //checks if the bullet yPo+extends is within the moving object yPos
            else if(bullet.yPos+bullet.extend >= this.yPos && bullet.yPos+bullet.extend <= this.yPos+this.extend)
                this.takeDamage();

        }// checks if the bullet xPos + extend is within the moving object yPos
        else if (bullet.xPos+bullet.extend >= this.xPos && bullet.xPos+bullet.extend <= this.xPos){
            if(bullet.yPos >= this.yPos && bullet.yPos <= this.yPos+this.extend)
                this.takeDamage();
            if(bullet.yPos+bullet.extend >= this.yPos && bullet.yPos+bullet.extend <= this.yPos+this.extend)
                this.takeDamage();
        }

    }
    //decreases the health and deletes the object if needed
    public void takeDamage(){
    boolean dead = false;
       health -= 1;
       if (health <= 0) {
    	   dead = true;
       }
    }
    

    
    
    
}


