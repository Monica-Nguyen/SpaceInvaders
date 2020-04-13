package SpaceInvaders;
import java.util.ArrayList;

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
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;



public class Main extends Application {
    //values for tracking the group alien movement
    private double AlienCurrentPos = 0;
    private double AlienMoveBy = 2;

    //Score and game vars
    private int level = 0;
    private int Score = 0;
    private double difficulty = 0;

    //vars for tracking the movement/shots of the ship
    private boolean right = false;
    private boolean left = false;
    private boolean space = false;

    //tracker to time the player bullet
    private int bulletTimer = 60;

    //constants for the window size
    private final double WIDTH = 600;
    private final double HEIGHT = 800;



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
        Xship = new Ship(250,700,3,"Ship",root);

        //Adds the Aliens to a list to handle collectively
        //the main loop that creates the aliens and adds them to an ArrayList

        //Timeline creates a animation loop with timer for all game movement
        //For more information: https://docs.oracle.com/javase/8/javafx/api/javafx/animation/Timeline.html
        Timeline gameLoop = new Timeline(new KeyFrame(Duration.millis(16), t -> {

            //if there are no aliens on the board then .
            if (alienListEmpty()){
                AlienMoveBy = 2+difficulty;
                AlienCurrentPos = 1;
                AlienList = new ArrayList<>(addAliens(root));
                difficulty += 0.1;
                level += 1;
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
            //shoots once every second if the space bar is being held down
            if (right == true && Xship.getX() < 550) Xship.move("Right");
            if (left == true && Xship.getX() > 0) Xship.move("Left");
            if (space == true){
                if(bulletTimer >= 60) {
                    BulletList.add(new Bullet(Xship.getX(), Xship.getY(), 1, "ShipBullet", root));
                    bulletTimer = 0;
                }
            }




//          Gate keeping method to verify if the game should still be running using methods
//          If either methods returns true then the game will be cleared and an end screen will be initiated
            if (doneShipGame(Xship) || invadedAliens(AlienList)){
                root.getChildren().clear();
                Text text = new Text("Game Over \n\n" + "Your Score is " + Score);
                text.setTextAlignment(TextAlignment.CENTER);
                StackPane pane = new StackPane();
                pane.getChildren().add(text);
                stage.getScene().setRoot(pane);
                stage.show();
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

    //********CREATORS: used for creating aliens and bullets
    //Adds the Aliens to a list to handle collectively
    public ArrayList<Alien> addAliens(Group root) {
        ArrayList<Alien> a = new ArrayList<Alien>();
        //the main loop that creates the aliens and adds them to an ArrayList
        for(double x = 1; x<400; x += 100){
            for(double y = 50; y<350; y +=100){
                //creates a new alien spaced by the loop
                A  = new Alien(x, y,1,"Alien",difficulty,root);
                a.add(A);
            }
        }
        return a;
    }

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
            if(Xship.bulletCollisionCheck(B)){
                B.remove();
                Xship.takeDamage();
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
            {
                Score+= 10;
                AlienList.remove(i);
            }
        }
    }

    //updates bullets after possible collisions
    public void updateBullets() {
        bulletTimer += 1;
        for (int i = 0; i < BulletList.size(); i++) {
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


}
