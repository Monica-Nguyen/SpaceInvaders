package textBased;


import java.util.ArrayList;

public class Ship {
	
	public void move(ArrayList<ArrayList<Integer>> board, int col) {
	    // Get the bottom row of the board generated in Alien class to add where the ship should be
		ArrayList<Integer> bottomRow = board.get(board.size() - 1);
		// Set the item in the row at the ship's position to 2 and the rest to 0
		for (int i = 0; i < bottomRow.size(); i ++) {
			//will move ship to user input column location
		    if (i == col) {
		        bottomRow.set(i, 2);
		        //default location is 0 column
		    } else {
		        bottomRow.set(i, 0);
		    }
		}
    }    

	//Fire on Alien will return int xposition of S to alien Class
	public int fire(ArrayList<ArrayList<Integer>> board) {
	    // Get the bottom row of the board where the ship should be
		ArrayList<Integer> bottomRow = board.get(board.size() - 1);
		//loop through to find ship location and return index
		for (int i = 0; i < bottomRow.size(); i ++) {
		    if (bottomRow.get(i) == 2) {
		        return i;
		    }
		}
		//return if error 
		return -1; 
	}

	//print function that transforms arrays into character-based board for user-friendliness
	public void printBoard (ArrayList<ArrayList<Integer>> board) {
		for (ArrayList<Integer> rows: board) {
	        for (Integer i: rows) {
	            if (i == 0) {
	                System.out.print("-");
	            } else if (i == 1) {
	                System.out.print("1");
	            } else if (i == 2) {
	                System.out.print("S");
	            }
	        }
	        System.out.println();
    	} 
}

}
	

