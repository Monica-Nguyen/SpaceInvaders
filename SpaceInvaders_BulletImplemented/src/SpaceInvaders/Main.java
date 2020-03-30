package SpaceInvaders;
import java.util.ArrayList;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

	//constants for the window size
	final double WIDTH = 600;
	final double HEIGHT = 800;
	
	//getting the images of the ships
	public final Image alienImage = new Image(getClass().getResourceAsStream("/res/Alien3.png"));
	public final Image shipImage = new Image(getClass().getResourceAsStream("/res/ship.png"));
	
	//images of the bullets
	public final Image shipBulletImage = new Image(getClass().getResourceAsStream("/res/ShipBullet.png"));
	public final Image alienBulletImage = new Image(getClass().getResourceAsStream("/res/AlienBullet.png"));
	//the group that holds all the instances
	public Group root;

	//launches the program
	public static void main(String[] args) {
        launch(args);
	}

	//the main function for the game
	public void start(Stage stage) throws Exception {
		//setting title
		stage.setTitle("Space Invaders");

		//adding scene
		Group root = new Group();
		Scene scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);

		//Ship parameters  : x,y,extend, health, shipImage, group
		Ship Xship = new Ship(0,700,50,3,shipImage,root);
		

		//Alien parameters : x,y,extend, health, alienImage, group
		Alien A;
		Bullet B;
		ArrayList<Alien> AlienList = new ArrayList<>();
		ArrayList<Bullet> BulletList = new ArrayList();

		//the main loop that creates the aliens and adds them to an ArrayList
		for(double x = 0; x<400; x += 100){
			for(double y = 0; y<300; y +=100){
				//creates a new alien spaced by the loop
				A  = new Alien(x, y,50,1,alienImage,root);
				AlienList.add(A);


			}
		}

		//creates a loop for the aliens that move on a timer
		//the handler for the time loop
		Timeline gameLoop = new Timeline(new KeyFrame(Duration.millis(16), t -> {
		//Alien Movement
				//loops through the alien list and moves all of them in the direction based off

			for(Alien x : AlienList) {
				x.move(AlienMoveBy);
				//Tracks the aliens movement and shoots one bullet a frame

				//TODO:
					// make the bullets run on a timer
						// use rand int or something
					// make all of the aliens shoot
				BulletList.add(new Bullet(x.imageView.getLayoutX(), x.imageView.getLayoutY(), 50, alienBulletImage, "Down", root));
			}

			for(Bullet y : BulletList){
				y.move();

			}


			AlienCurrentPos+=AlienMoveBy;
				
				//increases the tracker for the groups x coordinate and checks to see if it hit the screen bound
			if(AlienCurrentPos == 250|| AlienCurrentPos == 0) AlienMoveBy = -AlienMoveBy;

		//Ship Movement
				//controls whether the ship moves left or right based on the variables left or right
			if (right == true && Xship.imageView.getLayoutX() < 596) Xship.move("Right");
			if (left == true && Xship.imageView.getLayoutX() > 0) Xship.move("Left");
			if (space == true){
				BulletList.add( new Bullet(Xship.xPos, Xship.yPos, 50, shipBulletImage, "Up", root));
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