package SpaceInvaders.GUI;

import java.util.ArrayList;
import java.util.List;


import SpaceInvaders.Logic.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;


public class Menu extends Application  {
	
	private static final int WIDTH = 600;
	private static final int HEIGHT = 800;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	

	private final static int Menu_Button_x = 220;
	private final static int Menu_Button_y = 220;
	
	private ScoreLabel pointsLabel; //new for score

	
	
	List<SpaceInvaderButton>menuButton;
	
	public Menu () {
		
		menuButton = new ArrayList();
		mainPane = new AnchorPane ();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		createButton ();
		createBackground();
		createLogo();
		
	}
		
		
	
	public Stage getMainStage() {
		return mainStage;
	}
	
	private void addMenuButton(SpaceInvaderButton button) {
		button.setLayoutX(Menu_Button_x);
		button.setLayoutY(Menu_Button_y + menuButton.size() * 150);
		menuButton.add(button);
		mainPane.getChildren().add(button);
	}
	public static void main(String[] args) {
	launch(args);
}
//	}
	private void createButton () {
		createStartButton();
	//	createScoretButton(); score is not working for now 
		createExitButton();
		ScoreElements();
		
	}
	
	
	
	private void createStartButton() {
		SpaceInvaderButton startButton = new SpaceInvaderButton("Start");
		addMenuButton(startButton);
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (startButton!=null) {
					Main gameManager = new Main();
					try {
						gameManager.start(mainStage);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
				}		
			}
			
		});	
	
	}
	
	private void ScoreElements() {
		//playerLife = 2;
		pointsLabel = new ScoreLabel("SCORE : 00");
		pointsLabel.setLayoutX(460);
		pointsLabel.setLayoutY(20);
		mainPane.getChildren().add(pointsLabel);
		
	}
	
	private void createScoretButton() {
		SpaceInvaderButton scoreButton = new SpaceInvaderButton("Score");
		addMenuButton(scoreButton);
	}
	private void createExitButton() {
		SpaceInvaderButton exitButton = new SpaceInvaderButton("Exit");
		addMenuButton(exitButton);
		
		exitButton.setOnAction(new EventHandler<ActionEvent> () {

			@Override
			public void handle(ActionEvent event) {
				mainStage.close();
				
			}
			
		});
	}
	
	private void createBackground() {
		Image backgroundImage = new Image("res/mainBackground.gif", 256,256, false, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null );
		mainPane.setBackground(new Background(background));
	}
	
	private void createLogo() {
		ImageView logo = new ImageView("res/Space_invaders_logo.png");
		logo.setLayoutX(200);
		logo.setLayoutY(50);
		
		logo.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(new DropShadow());
			}
				
			});
		logo.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(null);
				
			}
			
		});
		mainPane.getChildren().add(logo);
	}
	
	public void start (Stage primaryStage) throws Exception {
		try {
			Menu manager = new Menu();
			primaryStage = manager.getMainStage();
			primaryStage.setTitle("Space Invaders");
			primaryStage.show();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}




	}
