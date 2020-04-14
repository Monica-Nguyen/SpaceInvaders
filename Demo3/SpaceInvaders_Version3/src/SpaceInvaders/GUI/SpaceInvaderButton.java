package SpaceInvaders.GUI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class SpaceInvaderButton extends Button  {
	
	
	
	//constant for setting the path of the font, when the button gets pressed, and the button style
	private final String Font_Path ="src/res/Luna.ttf";
	private final String Button_Pressed = "-fx-background-color: transparent; -fx-background-image: url('/res/blue_panel.png');";
	private final String Button_Style = "res/blue_button04.png";
	
	//creating a constructor for the text and calling the other buttons
	public SpaceInvaderButton(String text) {
		setText(text);
		setButtonFont();
		//set the preference width and height layout for the button
		setPrefWidth(190);
		setPrefHeight(29);
		setStyle(Button_Style);
		initializeButton();
	}
	//To set the font for the button
	private void setButtonFont() {
		//font becomes arial if the program font doesn't run
		try {
			setFont(Font.loadFont(new FileInputStream(Font_Path), 30));
		} catch (FileNotFoundException e){ {
			setFont(Font.font("Arial", 28));
	}
		}
	}
	
	//to set the effect when the button is pressed
	private void setButtonPressed() {
		setStyle(Button_Pressed);
		//to create the widith and hight when pressing 
		setPrefHeight(45);
		setLayoutY(getLayoutY() + 4);
	}
	 //to set the effect when the button is released
	private void setButtonReleased() {
		setStyle(Button_Style);
		//to create the widith and hight when pressing 
		setPrefHeight(45);
		setLayoutY(getLayoutY() - 4);
		
	}
	
//create the listeners when the mouse is pressed, released and exited
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
		


			
	
	


