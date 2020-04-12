package SpaceInvaders;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import spaceInvadersLogic.movingObjs.Alien;
import spaceInvadersLogic.movingObjs.Bullet;
import spaceInvadersLogic.movingObjs.Ship;



public class Main extends Application {
	//values for tracking the group alien movement
	private int AlienCurrentPos = 0;
	private int AlienMoveBy = 2;
	private int Score = 0;
	
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
	
	//Ship object
	Ship Xship;
	
	//Creating Alien objects with parameters : x,y,extend, health, alienImage, group
	Alien A;
	ArrayList<Alien> AlienList = new ArrayList<>();
	ArrayList<Bullet> BulletList = new ArrayList<>();

	//the main function for the game
	public void start(Stage stage) throws Exception {
		//setting title
		stage.setTitle("Space Invaders");

		//adding scene
		Group root = new Group();
		Scene scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);
		//Creating Ship with parameters  : x,y,extend, health, shipImage, group
		Ship Xship = new Ship(250,700,3,"Ship",root);
		
		//TODO: If you can figure out a way to just call addAliens() in this next section instead - otherwise delete addAliens()
		//addAliens() method not working because it uses root and it has to be in same method as root
		
		//Adds the Aliens to a list to handle collectively
		//the main loop that creates the aliens and adds them to an ArrayList
		for(double x = 0; x<400; x += 100){
			for(double y = 0; y<300; y +=100){
				//creates a new alien spaced by the loop
				A  = new Alien(x, y,1,"Alien",root);
				AlienList.add(A);
			}
		}
		
		//Timeline creates a animation loop with timer for all game movement
		//For more information: https://docs.oracle.com/javase/8/javafx/api/javafx/animation/Timeline.html
		Timeline gameLoop = new Timeline(new KeyFrame(Duration.millis(16), t -> {
			
			//Initiating Alien Movement and loop during game
			alienMove();
			alienLoop();
			
			//TODO: The alien bullet method has the same issue as addAliens() which can't be called because of root
			//drawAlienBullet();
			//Aliens will randomly shoots an alien bullet
			for(Alien x : AlienList) {
				if(Math.random() <= 0.001){
					BulletList.add(new Bullet(x.getX(), x.getY(), 1,"AlienBullet", root));
				}
			}
			
			
			//TODO: Fix doneShipGame and invadedAliens in GameOver methods at bottom of file: So far, doneAlienGame works 
			//Gate keeping method to verify if the game should still be running using methods
			//If either methods returns true then the game will be cleared and an end screen will be initiated
			if (doneAlienGame()){// || doneShipGame()) { //|| invadedAliens()) {
				root.getChildren().clear();
				Text text = new Text("Game Over \n\n" + "Your Score is " + Score);
	            text.setTextAlignment(TextAlignment.CENTER);
	            StackPane pane = new StackPane();
				pane.getChildren().add(text);
				stage.getScene().setRoot(pane);
				stage.show();
			}

			
			alienCollision();
			updateAliens();
			updateBullets();
			//shipCollision();
			//updateShipGame();

			
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
		//For more information on Event Handlers with key presses: https://docs.oracle.com/javafx/2/events/handlers.htm
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

		//Shows the Screen where game elements are added 
		stage.setScene(scene);
		stage.show();
		}
	
	//********** METHODS THAT CAN'T BE CALLED IN THE MAIN PROGRAM********** DELETE IF NECESSARY 
		//Adds the Aliens to a list to handle collectively
		public void addAliens() {
			//the main loop that creates the aliens and adds them to an ArrayList
			for(double x = 0; x<400; x += 100){
				for(double y = 0; y<300; y +=100){
					//creates a new alien spaced by the loop
					A  = new Alien(x, y,1,"Alien",root);
					AlienList.add(A);
				}
			}
		}
		
		public void drawAlienBullet() {
		//randomly shoots an alien bullet
		for(Alien x : AlienList) {
			if(Math.random() <= 0.001){
				BulletList.add(new Bullet(x.getX(), x.getY(), 1,"AlienBullet", root));
			}
		}
		}
	//******************************************************************************************
		
		//Alien Movement throughout the game
		//loops through the alien list and moves all of them in the direction based off
		public void alienMove() {
			for(Alien x : AlienList) {
				if(AlienCurrentPos < 250 && AlienMoveBy > 0) {
					x.move("Right");
					x.move("Down");
				}
				else if(AlienCurrentPos > 0 && AlienMoveBy < 0) {
					x.move("Left");
				}
			}
			}
			
		//Moderates movement in loop within screen bounds 
		public void alienLoop() {
				//increases the tracker for the groups x coordinate and checks to see if it hit the screen bound
				AlienCurrentPos+=AlienMoveBy;
				if(AlienCurrentPos >= 250 || AlienCurrentPos <= 0) AlienMoveBy = -AlienMoveBy;
				bulletTimer += 1;
		}
		
		//loops through the aliens and checks for collision with a bullet
		public void alienCollision() {
			for(Bullet B : BulletList){
				B.move();
				for(Alien  a: AlienList){
					if (a.bulletCollisionCheck(B)) {
						a.remove();
						B.remove();
					}
			}
		}}
		
		//updates aliens after possible collisions with a Ship Bullet
		//Has a score counter to keep track of destroyed aliens if game ends 
		public void updateAliens() {
			for (int i = 0; i < AlienList.size(); i++) {
				Alien a = AlienList.get(i);
				if (!a.onScreen())
					{
					Score+= 10;
					AlienList.remove(i);
					}
			}
			}
	
		
		//TODO: Make the Ship collision work: I tested it and its not working this way (won't print hello2)
		//TODO: After Ship collision works, update MovableObject:TakeDamage and the doneShipGame/invadedAliens method below
		//checks collision with Ship with Alien Bullet 
		public void shipCollision() {
			//moves the bullets and handles collision
			for(Bullet B : BulletList) {
				B.move();
				System.out.println("hello");
			//checks for collision in the ship
			if(Xship.bulletCollisionCheck(B)) 
				System.out.println("hello2");
				{
				B.remove();
				System.out.println("hello3");
				Xship.takeDamage();
				}
		}
		}
		
		//updates bullets after possible collisions
		public void updateBullets() {
			for (int i = 0; i < BulletList.size(); i++) {
				Bullet b = BulletList.get(i);
				if (!b.onScreen()) 
				{
					BulletList.remove(i);
				}
		}
		}
		
		// *********GAME OVER METHODS: If they return true, then game will be ended 
		public Boolean invadedAliens() {
			for(Alien x : AlienList) {
				if (x.getY() <= 50)
					return true;
		}
			return false;
		}
		
		//updates ship after possible collision
			public Boolean doneShipGame() {
				int inHealth = Xship.getHealth();
				System.out.println(Xship.getHealth());
				if (inHealth<1) 
					return true;
				else return false;
				}
			
			public Boolean doneAlienGame() {
				if (AlienList.isEmpty())
						return true;
				return false;
			}
			
			
}