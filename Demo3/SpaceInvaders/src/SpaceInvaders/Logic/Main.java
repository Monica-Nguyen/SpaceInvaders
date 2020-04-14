package SpaceInvaders.Logic;
import java.util.ArrayList;

import SpaceInvaders.GUI.ScoreLabel;
import SpaceInvaders.movingObjs.Alien;
import SpaceInvaders.movingObjs.Bullet;
import SpaceInvaders.movingObjs.Ship;

import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class Main extends Application {
    //values for tracking the group alien movement
    private double AlienCurrentPos = 0;
    private double AlienMoveBy = 2;

    //creating game vars
    private double difficulty = 0;
    private int level = 0;

    //vars for tracking the movement/shots of the ship
    private boolean right = false;
    private boolean left = false;
    private boolean space = false;

    //tracker to time the player bullet
    private int bulletTimer = 40;

    //constants for the window size
    private final double WIDTH = 600;
    private final double HEIGHT = 800;
    
    //view for score label
    private ScoreLabel scoreLabel;
	private ImageView[] playerLifes;
	private int playerLife;
	private int score;

    //the group that holds all the instances
    public Group root;

    
    //initializing the game objects
    Ship Xship;
    Alien A;
    ArrayList<Alien> AlienList = new ArrayList<>();
    ArrayList<Bullet> BulletList = new ArrayList<>();

    //the main function for the game
    public void start(Stage stage) throws Exception {
        //setting title
        stage.setTitle("Space Invaders");
        

        //adding scene
        root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);
        
        //Creating Ship with parameters  : x,y,extend, health, shipImage, group
        Xship = new Ship(250,700,3,"Ship",root);

        //Time line creates a animation loop with timer for all game movement
        //For more information: https://docs.oracle.com/javase/8/javafx/api/javafx/animation/Timeline.html
        Timeline gameLoop = new Timeline(new KeyFrame(Duration.millis(16), t -> {

            //if there are no aliens on the board then the game variables are increased.
            if (alienListEmpty()){
                AlienCurrentPos = 1;
                AlienList = new ArrayList<>(addAliens(root));
                AlienMoveBy = 2+difficulty;
                difficulty += 0.1;
                level += 1;
                String textToSet = "Level : ";
                if (level <= 50) {
                    textToSet = textToSet + "0";
                }
                scoreLabel.setText(textToSet + level);
            }

            //Initiating Alien Movement and loop during game
            alienMove();
            alienLoop();

            //Aliens will randomly shoots an alien bullet
            drawAlienBullet(root);

            //Checks for collision then updates the list of aliens and bullets
            alienCollision();
            shipCollision(Xship);
            updateAliens();
            updateBullets();

            //Ship Movement/Firing
            //controls whether the ship moves left or right based on the variables left or right
            //shoots once every 0.6s if the space bar is being held down
            if (right == true && Xship.getX() < 550) Xship.move("Right");
            if (left == true && Xship.getX() > 0) Xship.move("Left");
            if (space == true){
                if(bulletTimer >= 40) {
                    BulletList.add(new Bullet(Xship.getX(), Xship.getY(), 1, "ShipBullet", root));
                    bulletTimer = 0;
                }
            }

//          Gate keeping method to verify if the game should still be running using methods
//          If either methods returns true then the game will be cleared and an end screen will be initiated
            if ((doneShipGame(Xship) || invadedAliens(AlienList))){
                //clears the root and initializes the vars for the background image
                root.getChildren().clear();
                BorderPane pane = new BorderPane();
                Image backgroundImage = new Image("res/blue.png", 256,256, false, true);
                BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null );
        		pane.setBackground(new Background(background));
                
        		//indicate game over and displays the background
        		ImageView gameOver = new ImageView("res/GameOver.png");
        		gameOver.setLayoutX(130);
        		gameOver.setLayoutY(200);
        		pane.getChildren().add(gameOver);
        		
        	    // Shows the score at the end of the game over the background image
        		scoreLabel.setLayoutX(220);
        		scoreLabel.setLayoutY(450);
        		pane.getChildren().add(scoreLabel);

        		//sets the new root
                stage.getScene().setRoot(pane);
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
        ScoreElements();
        stage.setScene(scene);
        stage.show();
    }
    
	//********CREATORS: used for creating aliens and bullets
    //Adds the Aliens to a list to handle collectively
    public ArrayList<Alien> addAliens(Group root) {
        ArrayList<Alien> a = new ArrayList<Alien>();
        //the main loop that creates the aliens and adds them to an ArrayList
        for(double x = 1; x<400; x += 100){
            for(double y = 50; y<350; y +=100){
                //creates a new alien spaced by the loop
                A  = new Alien(x, y, 1, "Alien", difficulty, root);
                a.add(A);
            }
        }
        return a;
    }

    //Creates an alien bullet
    public void drawAlienBullet(Group r) {
        //randomly shoots an alien bullet
        for(Alien x : AlienList) {
            if(Math.random() <= 0.001){
                BulletList.add( new Bullet(x.getX(), x.getY(), 1,"AlienBullet", r));
            }
        }
    }

    //********ALIEN MOVEMENT
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
                x.move("Down");
            }
        }
    }

    //Moderates movement in loop within screen bounds
    public void alienLoop() {
        //increases the tracker for the groups x coordinate and checks to see if it hit the screen bound
        AlienCurrentPos+=AlienMoveBy;
        if(AlienCurrentPos >= 250 || AlienCurrentPos <= 0) AlienMoveBy = -AlienMoveBy;
    }


    //*****COLLISION DETECTION: will trigger
    //loops through the aliens and checks for collision with a bullet
    public void alienCollision() {
        for(Bullet B : BulletList){
            B.move("");
            //if a bullet hits an alien then it will cause it to become not visible and later deleted by an update function
            for(Alien  a: AlienList){
                if (a.bulletCollisionCheck(B)) {
                    a.remove();
                    B.remove();
                }
            }
        }
    }

    //checks collision with Ship with Alien Bullet
    public void shipCollision(Ship Xship) {
        for(Bullet B : BulletList){
            //if an alien bullet collides with the ship then it takes damage
            if(Xship.bulletCollisionCheck(B)){
                B.remove();
                Xship.takeDamage();
                removePlayer();
            }
        }
    }


    //*********UPDATE METHODS: Used with the collision to update score and remove items
    //updates aliens after possible collisions with a Ship Bullet
    //Has a score counter to keep track of destroyed aliens if game ends
    public void updateAliens() {
    	for (int i = 0; i < AlienList.size(); i++) {
            Alien a = AlienList.get(i);
            if (!a.onScreen())
            {   //Score tracking
            	score+=10;
                String textToSet = "Score : ";
                if (score < 10) {
                	textToSet = textToSet + "0";
                }
                scoreLabel.setText(textToSet + score);
                //removes alien from the list
                AlienList.remove(i);
            }
        }
    }

    //updates bullets after possible collisions
    public void updateBullets() {
        bulletTimer += 1;
        for (int i = 0; i < BulletList.size(); i++) {
            //if the bullet isn't being displayed then it is removed
            Bullet b = BulletList.get(i);
            if (!b.onScreen())
            {
                BulletList.remove(i);
            }
        }
    }


    // *********GAME OVER/RESET METHODS: If they return true, then game will be ended
    
    //Checks to see if there are Aliens left
    public Boolean alienListEmpty() {
        if (AlienList.isEmpty())
            return true;
        return false;
    }

    //updates ship after possible collision
    public Boolean doneShipGame(Ship x) {
        if (x.getHealth() < 1) return true;
        else return false;
    }

    //Checks to see if the aliens have reached the bottom of the screen
    public Boolean invadedAliens(ArrayList<Alien> a) {
        for(Alien x : a) {
            if (x.getY() >= 700)
                return true;
        }
        return false;
    }
    
	//creates the score label at the top right corner of the screen
    //gives the player life equal to 3
    private void ScoreElements() {
    	playerLife = 2;
    	scoreLabel = new ScoreLabel("SCORE : 00");
		scoreLabel.setLayoutX(460);
		scoreLabel.setLayoutY(20);
		root.getChildren().add(scoreLabel);
		playerLifes = new ImageView [3];
		
		for (int i =0;i < playerLifes.length; i++){
			playerLifes[i] = new ImageView("res/shipLife.png");
			playerLifes[i].setLayoutX(455+(i*50));
			playerLifes[i].setLayoutY(80);
			root.getChildren().add(playerLifes[i]);
		}
	}
    //method to remove a life when it gets shot at 
		private void removePlayer() {
			root.getChildren().remove(playerLifes[playerLife]);
			playerLife--;
			}
		
}
