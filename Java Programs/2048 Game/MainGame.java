/*
 * James Fazio
 * jfazio2@u.rochester.edu
 * CSC 172 Project 1
 * No collaboration
 */
import java.util.*;
import java.io.*;

public class MainGame {
	
	public static void clearBoard(int value) {
		for(int i = 0; i < value; i++) {
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to TTY 2048! Would you like to read the directions before playing?");
		System.out.print("Enter 'y' or 'n': ");
		String info = in.next();
		if(info.equalsIgnoreCase("y")) {
			System.out.println();
			System.out.println("Instructions:" + "\n \n" + "– For more information about the 2048 game, visit 2048game.com." + "\n" + "– Movement in this games uses the keys a, s, d, w for left, down, right, up respectively." + "\n" + "– You will see your input in the console after pressing each key. You must press the return key to confirm your move." + "\n" + "– You may reset or quit the game at any time by entering 'r' or 'q' respectively." + "\n");
			System.out.println();
			System.out.print("Enter any character to begin the game: ");
			info = in.next();
		}
			System.out.println();
			System.out.println("Let the game begin! Generating 2048 game board...");
		Array.gameArray = Array.update2048Array(); // Creates the initial game board
		Array.gameArray = Array.update2048Array();
		Array.print2048Array(Array.gameArray);
		System.out.println("Current score: " + Array.score);
		String direction = "";
		boolean winner = false;
		
		while(!Array.gameOver) {
			boolean reset = false; // Used to "continue" through double loop
			boolean incompetent = true; // Used to ignore unexpected characters and belittle the user (just slightly)
			while(incompetent) {
				direction = in.next();
				if(direction.equalsIgnoreCase("a") || direction.equalsIgnoreCase("s") || direction.equalsIgnoreCase("d") || direction.equalsIgnoreCase("w") || direction.equalsIgnoreCase("r") || direction.equalsIgnoreCase("q")) {
					incompetent = false;
				}
				if(!(direction.equalsIgnoreCase("a") || direction.equalsIgnoreCase("s") || direction.equalsIgnoreCase("d") || direction.equalsIgnoreCase("w") || direction.equalsIgnoreCase("r") || direction.equalsIgnoreCase("q"))) {
					System.out.print("Unexpected input. Please enter a valid character: ");
				}
				if(direction.equalsIgnoreCase("r")) {
					System.out.println();
					System.out.println("Are you sure you want to reset the game? All current progress will be lost.");
					System.out.print("Enter 'y' or 'n': ");
					String confirm = in.next();
					if(confirm.equalsIgnoreCase("y")) {
						Array.resetGame();
						Array.gameArray = Array.update2048Array(); //Re-initiates game board
						Array.gameArray = Array.update2048Array();
						clearBoard(100);
						Array.print2048Array(Array.gameArray);
						System.out.println("Current score: " + Array.score);
						reset = true;
						break;
					} else {
						System.out.println();
						System.out.println("Continuing current game...");
						System.out.print("Input your next move: ");
						direction = in.next();
					}
				}
				if(direction.equalsIgnoreCase("q")) {
					System.out.println();
					System.out.println("Are you sure you want to quit the game? All current progress will be lost and the program will exit.");
					System.out.print("Enter 'y' or 'n': ");
					String confirm = in.next();
					if(confirm.equalsIgnoreCase("y")) {
						System.out.println();
						System.out.println("Thanks for playing!" + "\n");
						System.out.println("Final score: " + Array.score + "; Highest tile reached: " + Array.maxTile());
						System.exit(0);
					} else {
						System.out.println();
						System.out.println("Continuing current game...");
						System.out.print("Input your next move: ");
						direction = in.next();
					}
				}
			}
			if(reset) {
				continue;
			}
			if(Array.validMove(direction)) { // Check to see if move will be valid before initializing move on main game array
				Array.nextMove(direction);
				Array.gameArray = Array.update2048Array();
				clearBoard(100);
				Array.print2048Array(Array.gameArray);
				System.out.println("Current score: " + Array.score);
			}
			if(Array.check2048()) {
				System.out.println();
				System.out.println("You win!");
				System.out.println("Congratulations, you have conquered TTY 2048! Final score: " + Array.score + "; Highest tile reached: " + Array.maxTile());
				winner = true;
				break;
			}
		}
		in.close();
		if(!winner) {
			System.out.println();
			System.out.println("Game over... The board is full and it has been determined that no additional moves can be made. Thanks for playing!" + "\n");
			System.out.println("Final score: " + Array.score + "; Highest tile reached: " + Array.maxTile());
		}
		
	}
	
}
