package SpaceInvaders.GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

public class ScoreLabel extends Label {
	// creating the font path 
	private final static String Font_Path ="src/res/Luna.ttf";
	
	//method to create the score button at the top right corner of the game
	public ScoreLabel(String text) {
		//setting the width and height 
		setPrefWidth(130);
		setPrefHeight(50);
		
		//setting the background image for the score button 
		BackgroundImage backgroundImage = new BackgroundImage(
				new Image ("/res/blue_button04.png", 130, 50, false, true), BackgroundRepeat.NO_REPEAT, 
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		
		setBackground(new Background(backgroundImage));
		setAlignment(Pos.CENTER_LEFT);
		// to set the padding of the button 
		//For more information: https://docs.oracle.com/javase/8/javafx/api/javafx/geometry/Insets.html
		setPadding(new Insets(10,10,10,10));
		ScoreLabelFont();
		setText(text);
		
	}
	// setting the font for the score label
	//If the font doesn't load then there is a try-catch to let us know in Arial font
	private void ScoreLabelFont() {
		try {
			setFont(Font.loadFont(new FileInputStream(new File(Font_Path)), 15));
		} catch (FileNotFoundException e) {
			setFont(Font.font("Arial", 15));
		}
		
	}
	

}
