# Space Invaders (Graphical User Interface) 

A shooter game in which the player controls a laser ship to remove all aliens from the space. The Graphical User Interface (GUI) has aliens at the top and the ship at the bottom that the user controls at the bottom. The ship is moved horizontally across the bottom of the screen using the right and left arrow keys while using spacebar to fire at aliens. The objective of the game is to remove all aliens. The aliens shoot back at the ship, and once three lives are gone then the game is over. 

## Prerequisites
You will need to install JDK(Java Development Kit) and an IDE, for example Eclipse.

To set up a Java Development Environment, follow the download links:
https://www.oracle.com/technetwork/java/archive-139210.html
https://www.eclipse.org/downloads/
https://www.youtube.com/watch?v=klob1me6O6Q

You will need to install JavaFx. Follow the instructions below.

## How to execute the game on Eclipse
• Download all the files from the master branch as a Zipped Download. Extract the src and res folders to your desktop for easy access. Open the Eclipse program. Install JavaFX following the instructions on this link: 

https://bittlife.com/install-javafx-eclipse/

Create a new Java FX Project and import the GitHub project into Eclipse following the instructions on this link: 

https://github.com/collab-uniba/socialcde4eclipse/wiki/How-to-import-a-GitHub-project-into-Eclipse

. Import the files from your dekstop to this Java Project. You will run the Main.java file to start the game. 
move all files to your desktop for easy access. You may use another folder but you must make sure all the files are in the same folder. 

## How to execute the game on Terminal
Download all the files from this Github as a Zipped Download. Extract the src and res folders to your desktop. Open a command prompt window and go to the directory where you saved the file. Ensure all the files are in the same directory. Assume it's C:. Type 'javac Main.java' to compile your code. You will have to compile each file from Github. If there are no errors in your code, the command prompt will take you to the next line (Assumption: The path variable is set). Now, type 'java Main' to run the game. You will be able to see the gamespace printed on the window.
 
### Final 
When you have successfully downloaded the game, there will be aliens scattered throughout the black screen with the ship at the bottom.

![Space Invaders - Animated gif demo](Demo3.gif)

The game is still in progress, still need to fix the bullets and add the colisions along iwth adding the scoring mechanism to the game.

Hope you enjoy the game!
