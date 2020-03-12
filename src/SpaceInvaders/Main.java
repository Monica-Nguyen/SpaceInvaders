package SpaceInvaders;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class Main extends Application {

	final double WIDTH = 600;
	final double HEIGHT = 800;
	public int xPos = 0;
	public Image alienImage = new Image(getClass().getResourceAsStream("/res/Alien2.png"));
	public Image shipImage = new Image(getClass().getResourceAsStream("/res/Ship.png"));
	public static void main(String[] args) {
        launch(args);
}
	   
	    @Override
	    public void start(Stage stage) throws Exception {
	    
	    	//setting title and layout
	    	stage.setTitle("Space Invaders");
	    	Group root = new Group();
	    	
	    	//adding scene
	        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);
	        

	        
	        //Alien parameters: x,y,extend,health, alienImage, root
	        //Ship parameters: x,y,extend, health, shipImage, root
	        //TODO: CREATE SHIP WITH CORRECT STARTING LOCATION (HAVE A xPos in this class)
	        Ship Xship = new Ship(50,700,50,3, shipImage, root);
	        //TODO: CREATE ALIEN AND ALIEN ARRAYLIST
			Alien a = new Alien(150,150,50,1,alienImage,root);
			ArrayList<Alien> AlienList = new ArrayList<>();
			for(int x = 50;x<400;x+=100){
				for(int y = 50;y<600;y+=100){
					a  = new Alien(x,y,50,1,alienImage,root);
					AlienList.add(a);
				}

			}
			AlienList.get(0).move("D");
			System.out.println(AlienList.get(0).yPos);

	        
	        //TODO: Move ship object with keyboard 
	        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
	        	  @Override
	        	  public void handle(KeyEvent event){
	        		 //need to check bounds for ship movement and then key press
					  //pass in L,R,D,U
	        	    if (event.getCode() == KeyCode.RIGHT) {
	        	    	Xship.move("R");;

	        	    } else if (event.getCode() == KeyCode.LEFT) {
	        	    	Xship.move("L");

       	    }
        	  }
	        	});
	      
	        stage.setScene(scene);
	        stage.show();
	        
	        AnimationTimer timer = new AnimationTimer() {
	            @Override
	            public void handle(long now) {
	            //need to smooth transition by entering updated location
	            
				}
	        };
	        timer.start();

	        
	      
}
}
	    
	  
	        