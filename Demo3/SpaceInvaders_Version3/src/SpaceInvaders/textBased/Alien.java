package SpaceInvaders.textBased;
import java.util.ArrayList;


public class Alien {
	//Initializing the Variables
	public int score = 0; 
	ArrayList<Integer> RowFull = new ArrayList<Integer>();
	ArrayList<Integer> RowEmpty = new ArrayList<Integer>();
	ArrayList<ArrayList<Integer>> Board = new ArrayList<ArrayList<Integer>>();
	
	//Creating the board
	public void createBoard(){
		//creating the row
		for (int i = 0; i < 10; i++) {
			RowFull.add(1);
			RowEmpty.add(0);
		}
		//adding the row to the board
		for (int x = 0; x <4;x++) {
			Board.add(new ArrayList<Integer>(RowFull));
		}
		for (int x = 0; x <6;x++) {
			Board.add(new ArrayList<Integer>(RowEmpty));
		}
		//set default location of ship to Column 0
		ArrayList<Integer> RowShip = Board.get(Board.size() - 1);
		RowShip.set(0, 2);
	}

	public int takeDamage(int xPos) {
		//scans through the 2d array until it has found a 1 then replaces it with a 0
		//will remove the lowermost 1 
		for(int i = this.Board.size() - 1; i >= 0; i--){
			ArrayList<Integer> x = this.Board.get(i);
			if(x.get(xPos) == 1) {
				x.add(xPos, 0);
				x.remove(xPos+1);
				score += 10;
				return 10;
			}
		}
		score += 0;
		return 0;
	}
		
	//Return score to Main Class 
		public int getScore() {
			return score;
	}
		//Score method to add score from Alien Class
		public void addScore(int score) {
			this.score += score;
		}
		
		public static void gameOver(int score) {
			if (score == 400) {
				System.out.println("Game over! Congratulations, You Win!");
				System.exit(0);
			} else {
				System.out.println("Your score is " + score);
			}
		}
}