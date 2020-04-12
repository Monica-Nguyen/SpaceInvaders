package SpaceInvaders;

import javafx.scene.Group;
import spaceInvadersLogic.movingObjs.MovableObject;

public class Ship extends MovableObject {

	public Ship (double xPos, double yPos, int health, String type, Group root) {
        super(xPos, yPos, health,type,root);
	}
}
