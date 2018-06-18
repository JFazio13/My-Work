import java.util.Scanner;

public class MainGame {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.err.print("Welcome to Tic Tac Toe! Enter your preferred game token (X or O): ");
		String token = in.nextLine();
		System.err.println();
		
		if(token.equals("x")) { // Ensure token is always capitalized for pleasant visuals
			token = "X";
		}
		if(token.equals("o") || token.equals("0")) {
			token = "O";
		}
		
		if(!token.equalsIgnoreCase("o") && !token.equalsIgnoreCase("x")) {
			token = "X";
			System.err.println("Unexpected token entered. Your game token has automatically been set to X." + "\n");
		}
		
		TTT t = new TTT(token);
		if(!token.equals("O")) {
			System.err.println("You've selected X! You will be going first this round.");
			System.err.println();
			t.printBoard();
			System.err.println();
		}
		boolean cpuFirst = true; // Used if user selects O as their token (X goes first)
		boolean tied = false;
		
		while(!t.gameOver) { // Loop game actions until game is over
			if(tied) {
				t.resetBoard();
				tied = false;
			}
			if(!t.boardFull()) { // Loop game action until board is full, indicating end of game
				if(token.equals("O") && cpuFirst == true) {
					System.err.println("You've selected O! The CPU will be going first this round.");
					System.err.println();
					t.cpuMove();
					t.printBoard();
					System.err.println();
					cpuFirst = false;
				}
				boolean insubordinate = false; // Used if an out of range position is entered
				System.err.print("Enter your next move (position 1-9): ");
				int position = in.nextInt();
				if(position == 11) { // Quit the game
					System.exit(0);
				}
				if(position < 1 || position > 9) {
					insubordinate = true;
					System.err.println();
					while(insubordinate) {
						System.err.print("Invalid position. Enter your next move (position 1-9): ");
						position = in.nextInt();
						if(position > 0 && position < 10) {
							insubordinate = false;
						}
					}
				}
				System.err.println();
				t.playerMove(position); // Place player's move; call for cpu move
				t.printBoard();
				t.checkWinner(); // Check if someone has won
				System.err.println();
				if(t.gameOver) {
					t.resetGame(); // Reset game when current game ends
					tied = true;
					cpuFirst = true;
				}
			} else {
				System.err.println("You tied!" + "\n");
				t.resetGame();
				cpuFirst = true;
			}
		}
		in.close();
	}

}
