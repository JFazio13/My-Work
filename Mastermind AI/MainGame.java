import java.util.*;

public class MainGame {

	public static void playGame() {

		Scanner in = new Scanner(System.in);
		System.out.print("Welcome to TTY Mastermind. Please enter your name: ");
		String name = in.next();
		System.out.println();
		System.out.println("Hello, " + name + "! To get started, please follow the proceeding instructions to set up the game." + "\n");
		
		int tokenColorsTemp = 0;
		int tokenPositionsTemp = 0;
		boolean gameOver = false;
		
		try {
			System.out.print("Enter a number 2-4 for the amount of colors in the game: ");
			int n = in.nextInt();
			if(n <= 4 && n >= 2) {
				tokenColorsTemp = n;
			} else {
				boolean disobedient = true;
				System.out.println("Error code 01: Value entered was not in the range specified.");
				while(disobedient) {
					System.out.print("Please enter a value from 2-4 for the amount of colors in the game: ");
					n = in.nextInt();
					if(n <= 4 && n >= 2) {
						tokenColorsTemp = n;
						disobedient = false;
					} else {
						continue;
					}
				}
			}
		String[] tokenColors = new String[tokenColorsTemp];
		System.out.println(n + " colors, got it!" + "\n");
	
		int i = 0;
		while(i < tokenColors.length){
			System.out.print("Enter a value for color number " + (i+1) + ": "); // i begins at 0, hence the i+1 in the human interaction
			String str = in.next();
			tokenColors[i] = str;
			i++;
		}
		
		System.out.println();
		System.out.println("Perfect! For simplicity, I will be referring to colors by their first letters from here on out.");
		
		System.out.println();
		System.out.print("Enter a number 2-5 for the amount of possible positions in the game: ");
		int n2 = in.nextInt();
		
		if(n2 <= 5 && n2 >= 2) {
			tokenPositionsTemp = n2;
		} else {
			boolean disobedient = true;
			System.out.println("Error code 01: Value entered was not in the range specified.");
			while(disobedient) {
				System.out.print("Please enter a value from 2-5 for the amount of possible positions in the game: ");
				n2 = in.nextInt();
				System.out.println();
				if(n2 <= 5 && n2 >= 2) {
					tokenPositionsTemp = n2;
					disobedient = false;
				} else {
					continue;
				}
			}
		}
		int tokenPositions = tokenPositionsTemp;
		System.out.println("Cool, " + n2 + " positions!" + "\n");
		
		int rounds = 0;
		boolean roundsSet = false;
		if((n == 2 || n2 == 2) && !roundsSet) {
			roundsSet = true;
			if(n == 2 && n2 == 2) {
				rounds = 3;
				System.out.println("Based on the parameters you've given me, I will have " + rounds + " turns to successfully guess your combination." + "\n");
			} else {
				rounds = 5;
				System.out.println("Based on the parameters you've given me, I will have " + rounds + " turns to successfully guess your combination." + "\n");
			}
		}
		
		if((n == 3 || n2 == 3) && !roundsSet) {
			roundsSet = true;
			rounds = 10;
			System.out.println("Based on the parameters you've given me, I will have " + rounds + " turns to successfully guess your combination." + "\n");
		}
			
		if((n == 4 || n2 == 4) && !(n == 4 && n2 == 5) && !roundsSet) {
			roundsSet = true;
			rounds = 25;
			System.out.println("Based on the parameters you've given me, I will have " + rounds + " turns to successfully guess your combination." + "\n");
		}
		
		if((n == 4 && n2 == 5) && !roundsSet) {
			roundsSet = true;
			rounds = 75;
			System.out.println("Based on the parameters you've given me, I will have " + rounds + " turns to successfully guess your combination." + "\n");
		}
		
		System.out.println("Okay, let's play!" + "\n");
		Mastermind MMGame = new Mastermind(tokenColors, tokenPositions); // Mastermind game is constructed
		MMGame.generate(); // Initial list of possibilities is generated
		
		int turns = 0;
		while(!gameOver) {
			System.out.println("Turn: " + (turns + 1));
			System.out.println();
			
			MMGame.nextMove(); // First/next guess is generated and displayed to player
			System.out.println();
			
			int colorRight; // Color right, position wrong
			int bothRight;
			
			System.out.print("Please enter the number of tokens in which I guessed the color correctly but have the position incorrect: ");
			colorRight = in.nextInt();
			
			System.out.print("Please enter the number of tokens in which I guessed both color and position correct: ");
			bothRight = in.nextInt();
			System.out.println();
			
			MMGame.response(colorRight, bothRight); // Program analyzes feedback, removes as many possibilities as it can, then submits a new list
			
			if(bothRight == MMGame.tokenPositions) {
				gameOver = true;
				break;
			}
			turns++;
			
			if(n <= 2 || n2 <= 2) {
				if(n == 2 && n2 == 2) {
					if(turns >= 3) { // Player wins if computer hasn't correctly guessed the combination after 3 guesses
						gameOver = true;
						System.out.println("You win!");
						System.out.println();
					}
				} else if(turns >= 5) { // Player wins if computer hasn't correctly guessed the combination after 5 guesses
					gameOver = true;
					System.out.println("You win!");
					System.out.println();
				}
			}
			
			if(n <= 3 || n2 <= 3) {
				if(turns >= 10) { // Player wins if computer hasn't correctly guessed the combination after 10 guesses
					gameOver = true;
					System.out.println("You win!");
					System.out.println();
				}
			}
				
			if(n <= 4 || n2 <= 4) {
				if(turns >= 25) { // Player wins if computer hasn't correctly guessed the combination after 25 guesses
					gameOver = true;
					System.out.println("You win!");
					System.out.println();
				}
			}
			
			if(n == 4 && n2 == 5) {
				if(turns >= 75) { // Player wins if computer hasn't correctly guessed the combination after 50 guesses
					gameOver = true;
					System.out.println("You win!");
					System.out.println();
				}
			}
			
		}
			
		MMGame.newGame(); // Calls the newGame() method, which creates a new game if the user chooses to do so
		
		} catch (InputMismatchException e) {
			System.out.println("Error code 02: Value entered does not match value expected. Please rerun the program and try again.");
			System.exit(0);
		}

	}
	
	public static void main(String[] args) {
		playGame(); // Begins the game!
	}

}
