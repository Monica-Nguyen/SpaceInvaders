package SpaceInvaders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class SpaceInvaderButton extends Button  {
	
	//private final static int Menu_Button_x = 100;
//	private final static int Menu_Button_y = 200;
	
	private final String Font_Path ="src/res/Luna.ttf";
	private final String Button_Pressed = "-fx-background-color: transparent; -fx-background-image: url('/res/blue_panel.png');";
	private final String Button_Style = "res/blue_button04.png";
	
	
	public SpaceInvaderButton(String text) {
		setText(text);
		setButtonFont();
		setPrefWidth(190);
		setPrefHeight(29);
		setStyle(Button_Style);
		initializeButton();
	}
	
	private void setButtonFont() {
		
		try {
			setFont(Font.loadFont(new FileInputStream(Font_Path), 30));
		} catch (FileNotFoundException e){ {
			setFont(Font.font("Arial", 28));
	}
		}
	}
	

	private void setButtonPressed() {
		setStyle(Button_Pressed);
		setPrefHeight(45);
		setLayoutY(getLayoutY() + 4);
	}
	 
	private void setButtonReleased() {
		setStyle(Button_Style);
		setPrefHeight(45);
		setLayoutY(getLayoutY() - 4);
		
	}
	

	private void initializeButton()  {
		
		setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonPressed();
				}
			}
				
		});
		
		setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonReleased();
				}
			}
		});
		
		setOnMouseEntered(new EventHandler <MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setEffect(new DropShadow());
				
			}
		});
		
		setOnMouseExited(new EventHandler <MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				setEffect(null);
			
			}
		});

	}
	
}
		


			
	
	


