package SpaceInvaders;
import java.util.ArrayList;

import SpaceInvaders.movingObjs.Alien;
import SpaceInvaders.movingObjs.Bullet;
import SpaceInvaders.movingObjs.Ship;

import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class Main extends Application {
	//values for tracking the group alien movement
	private int AlienCurrentPos = 0;
	private int AlienMoveBy = 2;
	
	//vars for tracking the movement/shots of the ship
	private boolean right = false;
	private boolean left = false;
	private boolean space = false;

	//tracker to time the player bullet
	private int bulletTimer = 60;

	//constants for the window size
	final double WIDTH = 600;
	final double HEIGHT = 800;	
	

	
	//the group that holds all the instances
	public Image image;
	public Group root;

	//the main function for the game
	public void start(Stage stage) throws Exception {
		//setting title
		stage.setTitle("Space Invaders");

		//adding scene
		Group root = new Group();
		Scene scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);

		//Ship parameters  : x,y,extend, health, shipImage, group
		Ship Xship = new Ship(250,700,3,"Ship",root);
		

		//Alien parameters : x,y,extend, health, alienImage, group
		Alien A;
		ArrayList<Alien> AlienList = new ArrayList<>();
		ArrayList<Bullet> BulletList = new ArrayList();

		//the main loop that creates the aliens and adds them to an ArrayList
		for(double x = 0; x<400; x += 100){
			for(double y = 0; y<300; y +=100){
				//creates a new alien spaced by the loop
				A  = new Alien(x, y,1,"Alien",root);
				AlienList.add(A);
			}
		}

		//creates a loop for the aliens that move on a timer
		//the handler for the time loop
		Timeline gameLoop = new Timeline(new KeyFrame(Duration.millis(16), t -> {
		//Alien Movement
				//loops through the alien list and moves all of them in the direction based off

			for(Alien x : AlienList) {
				if(AlienCurrentPos < 250 && AlienMoveBy > 0) {
					x.move("Right");
				}
				else if(AlienCurrentPos > 0 && AlienMoveBy < 0) {
					x.move("Left");
				}

				//randomly shoots an alien bullet
				if(Math.random() <= 0.001){
					BulletList.add(new Bullet(x.getX(), x.getY(), 1,"AlienBullet", root));
				}


			}

			//increases the tracker for the groups x coordinate and checks to see if it hit the screen bound
			AlienCurrentPos+=AlienMoveBy;
			if(AlienCurrentPos >= 250 || AlienCurrentPos <= 0) AlienMoveBy = -AlienMoveBy;

			//moves the bullets and handles collision
			for(Bullet B : BulletList){
				B.move();

				//loops through the aliens and checks for collision with a bullet
				for(Alien  a: AlienList){
					if(a.bulletCollisionCheck(B))System.out.println("a");
				}
				//checks for collision in the ship
				if(Xship.bulletCollisionCheck(B)) Xship.takeDamage();

			}
			//increased the bullet timer to make sure that the ship doesn't shoot too fast
			bulletTimer += 1;



		//Ship Movement
				//controls whether the ship moves left or right based on the variables left or right
			if (right == true && Xship.getX() < 550) Xship.move("Right");
			if (left == true && Xship.getX() > 0) Xship.move("Left");
			if (space == true){
				if(bulletTimer >= 60) {
					BulletList.add(new Bullet(Xship.getX(), Xship.getY(), 1, "ShipBullet", root));
					bulletTimer = 0;
				}
			}
		}));


		//sets the time line for the aliens
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		gameLoop.play();
		
		//handler for if a key is pressed starting a move command
				//all movement is handled in the time loop
		scene.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.RIGHT ) right = true;
			if (event.getCode() == KeyCode.LEFT) left = true;
			if (event.getCode() == KeyCode.SPACE) space = true;

		});
		//handler for if the key is released breaking the move command
		scene.setOnKeyReleased(event -> {
			if (event.getCode() == KeyCode.RIGHT ) right  = false;
			if (event.getCode() == KeyCode.LEFT ) left = false;
			if (event.getCode() == KeyCode.SPACE) space = false;
		});

		stage.setScene(scene);
		stage.show();
}
}