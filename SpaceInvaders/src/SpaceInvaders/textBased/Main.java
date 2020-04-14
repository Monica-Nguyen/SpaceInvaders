package SpaceInvaders.textBased;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main (String args[]){
		System.out.println("Space Invaders: The board has columns 0 to 9. Enter a number between 0 and 9.");
		
		Scanner reader = new Scanner(System.in);
		String turn;
		
		Alien alien = new Alien();
		Ship the_ship = new Ship(0, 0, 0);
		
		alien.createBoard();
		ArrayList<ArrayList<Integer>> board = alien.Board;
		the_ship.printBoard(board);
		
		while(true){ // Later on: replace true with non-winning condition (<400 points) so that it exits these steps when the game is over? Many ways to implement gameover part
			
			
			//alien.printBoard(); I used to call Stu's board but we're using Sanjita's now so maybe this can be made into a method since it is called in 3 different places and then I can call that method here instead
			System.out.print("Enter m for move or f for fire: ");
			turn = reader.nextLine(); //prompt for what to do 
			
			
			if(turn.contentEquals("m") || turn.contentEquals("M") ){ //not case-sensitive :)
				int column = col_move(reader);
				the_ship.move(board, column); //return column of attack as an integer to move method in Ship class
				the_ship.printBoard(board);
			}
			
			else if(turn.contentEquals("f") || turn.contentEquals("F")){
				int column_fire = the_ship.fire(board); // invoke fire method (using previous number entered as the position..)
				alien.takeDamage(column_fire);
				the_ship.printBoard(board); 
				int score = alien.getScore();
				Alien.gameOver(score); //displays score and will notify if gameplay is over and all aliens have been removed
				
			}
			
			
			else {
				System.out.println("Invalid input. Try again. ");
				
			}
		
		}
	}

	
	public static int col_move(Scanner reader){
		while(true) {
			System.out.print("What column do you want to move to? ");
			
			try{
				 int col_number = Integer.parseInt(reader.nextLine()); //accept input as string, later on catch if it is not a string 
			
			if(col_number < 0 || col_number > 9) {
				System.out.println("That column does not exist. Please enter a column between 0 and 9.");
			}
			else {
				
			}
				return col_number; 
			}
			catch (NumberFormatException exception){ //Caught the number format exception if the input is not a string
				System.out.println("That column does not exist. Please enter a column between 0 and 9.");
			}
		}
	
}

}
