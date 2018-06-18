/*
 * James Fazio
 * jfazio2@u.rochester.edu
 * CSC 172 Project 1
 * No collaboration
 */
import java.util.*;

public class Array {
	
	protected static int[][] gameArray = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}};
	protected static int[][] compareArray = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}};
	protected static int[] subArray1 = {0,0,0,0};
	protected static int[] subArray2 = {0,0,0,0};
	protected static int[] subArray3 = {0,0,0,0};
	protected static int[] subArray4 = {0,0,0,0};
	protected static int[] subCompare1 = {0,0,0,0};
	protected static int[] subCompare2 = {0,0,0,0};
	protected static int[] subCompare3 = {0,0,0,0};
	protected static int[] subCompare4 = {0,0,0,0};
	protected static boolean gameOver = false;
	protected static int score = 0;
	
	public static boolean validMove(String direction) {
		/* The user's next move will first be performed on a throwaway 2D array for comparison.
		 * If, after performing the move on the new array, the main game array matches the
		 * throwaway array exactly, the move is NOT valid.
		 */
		
		for(int i = 0; i < 4; i++) { // Populates the throwaway sub-arrays
			subCompare1[i] = subArray1[i];
		}
		for(int i = 0; i < 4; i++) {
			subCompare2[i] = subArray2[i];
		}
		for(int i = 0; i < 4; i++) {
			subCompare3[i] = subArray3[i];
		}
		for(int i = 0; i < 4; i++) {
			subCompare4[i] = subArray4[i];
		}
		
		compareArray[0] = subCompare1; // Populates the throwaway 2D; at this point, an exact match to the main game array
		compareArray[1] = subCompare2;
		compareArray[2] = subCompare3;
		compareArray[3] = subCompare4;
		
		nextMoveValidity(direction); // Performs the user's attempted next move on the throwaway array
		
		if(Arrays.deepEquals(compareArray, gameArray)) { // If the main game array and the throwaway array are still identical, the move is invalid
			MainGame.clearBoard(100);
			print2048Array(gameArray);
			System.out.println("Invalid move. Try again.");
			return false;
		} else {
			return true;
		}
	}
	
	public static int maxTile() {
		int max = 0;
		for(int[] row : gameArray) {
		    for(int i = 0; i < row.length; i++) {
		    	if(row[i] > max) {
		    		max = row[i];
		    	}
		    }
		}
		return max;
	}
	
	public static boolean check2048() {
		boolean found2048 = false;
		for(int[] row : gameArray) {
			if(found2048) {
	    		break;
	    	}
		    for(int i = 0; i < row.length; i++) {
		    	if(row[i] == 2048) {
		    		found2048 = true;
		    		break;
		    	}
		    }
		}
		if(found2048) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean endGame() {
		/* The endGame method uses the throwaway 2D array to test if there
		 * are moves available to be made on the board when the board is
		 * full. This prevents the game from ending prematurely when the 
		 * board is full but there are still possible moves that could be
		 * made.
		 */
		boolean endGame = false;
		
		for(int i = 0; i < 4; i++) { // Populates the throwaway sub-arrays
			subCompare1[i] = subArray1[i];
		}
		for(int i = 0; i < 4; i++) {
			subCompare2[i] = subArray2[i];
		}
		for(int i = 0; i < 4; i++) {
			subCompare3[i] = subArray3[i];
		}
		for(int i = 0; i < 4; i++) {
			subCompare4[i] = subArray4[i];
		}
		
		compareArray[0] = subCompare1; // Populates the throwaway 2D; at this point, an exact match to the main game array
		compareArray[1] = subCompare2;
		compareArray[2] = subCompare3;
		compareArray[3] = subCompare4;
		
		String left = "a";
		String right = "d";
		String up = "w";
		String down = "s";
		/* These four booleans are set to true if their respective
		 * trial move results in an invalid move. If all four are 
		 * set to true, that means the board is full and no moves
		 * can be made.
		 */
		boolean leftNegative = false;
		boolean rightNegative = false;
		boolean upNegative = false;
		boolean downNegative = false;
		
		nextMoveValidity(left);
		if(Arrays.deepEquals(compareArray, gameArray)) {
			leftNegative = true;
		}
		
		for(int i = 0; i < 4; i++) {
			subCompare1[i] = subArray1[i];
		}
		for(int i = 0; i < 4; i++) {
			subCompare2[i] = subArray2[i];
		}
		for(int i = 0; i < 4; i++) {
			subCompare3[i] = subArray3[i];
		}
		for(int i = 0; i < 4; i++) {
			subCompare4[i] = subArray4[i];
		}
		
		nextMoveValidity(right);
		if(Arrays.deepEquals(compareArray, gameArray)) {
			rightNegative = true;
		}
		
		for(int i = 0; i < 4; i++) {
			subCompare1[i] = subArray1[i];
		}
		for(int i = 0; i < 4; i++) {
			subCompare2[i] = subArray2[i];
		}
		for(int i = 0; i < 4; i++) {
			subCompare3[i] = subArray3[i];
		}
		for(int i = 0; i < 4; i++) {
			subCompare4[i] = subArray4[i];
		}
		
		nextMoveValidity(up);
		if(Arrays.deepEquals(compareArray, gameArray)) {
			upNegative = true;
		}
		
		for(int i = 0; i < 4; i++) {
			subCompare1[i] = subArray1[i];
		}
		for(int i = 0; i < 4; i++) {
			subCompare2[i] = subArray2[i];
		}
		for(int i = 0; i < 4; i++) {
			subCompare3[i] = subArray3[i];
		}
		for(int i = 0; i < 4; i++) {
			subCompare4[i] = subArray4[i];
		}
		
		nextMoveValidity(down);
		if(Arrays.deepEquals(compareArray, gameArray)) {
			downNegative = true;
		}
		
		if(leftNegative && rightNegative && upNegative && downNegative) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void resetGame() {
		/*The resetGame method sets all subArray indexes to zero
		 * and puts them into the main game array and sets the
		 * score back to zero.
		 */
		for(int i = 0; i < subArray1.length; i++) {
			subArray1[i] = 0;
		}
		for(int i = 0; i < subArray1.length; i++) {
			subArray2[i] = 0;
		}
		for(int i = 0; i < subArray1.length; i++) {
			subArray3[i] = 0;
		}
		for(int i = 0; i < subArray1.length; i++) {
			subArray4[i] = 0;
		}
		gameArray[0] = subArray1;
		gameArray[1] = subArray2;
		gameArray[2] = subArray3;
		gameArray[3] = subArray4;
		
		score = 0;
	}
	
	public static int[][] update2048Array() {
		 /* The update2048Array method is in charge of placing a new 2 or 4
		 * on the board after each valid move. This method is called twice
		 * to begin the game or after a reset.
		 */
		Random r = new Random();
		int randomX = 0; // randomX is used to randomly select a subArray index 0-3 (horizontal)
		int randomY = 0; // randomY is used to randomly select a subArray 1-4 (vertical)
		int chance = 0; // chance is used to generate a 2 80% of the time and a 4 20% of the time
		boolean openSlot = false;
		
		while(!openSlot) { // Ensures that already-occupied spaces don't get overwritten; if an occupied spot is randomly selected, it will loop around until an open spot is found or the board is full.
			randomY = r.nextInt(4);
			if(randomY == 0) {
				randomX = r.nextInt(4);
				chance = r.nextInt(10);
				if(chance <=7) { // 8/10 = .8 chance of occurrence
					if(subArray1[randomX] == 0) {
						openSlot = true;
						subArray1[randomX] = 2;
					}
				} else { // 2/10 = .2 chance of occurrence
					if(subArray1[randomX] == 0) {
						openSlot = true;
						subArray1[randomX] = 4;
					}
				}
			}
			if(randomY == 1) {
				randomX = r.nextInt(4);
				chance = r.nextInt(10);
				if(chance <=7) { // 8/10 = .8 chance of occurrence
					if(subArray2[randomX] == 0) {
						openSlot = true;
						subArray2[randomX] = 2;
					}	
				} else { // 2/10 = .2 chance of occurrence
					if(subArray2[randomX] == 0) {
						openSlot = true;
						subArray2[randomX] = 4;
					}
				}
			}
			if(randomY == 2) {
				randomX = r.nextInt(4);
				chance = r.nextInt(10);
				if(chance <=7) { // 8/10 = .8 chance of occurrence
					if(subArray3[randomX] == 0) {
						openSlot = true;
						subArray3[randomX] = 2;
					}	
				} else { // 2/10 = .2 chance of occurrence
					if(subArray3[randomX] == 0) {
						openSlot = true;
						subArray3[randomX] = 4;
					}
				}
			}
			if(randomY == 3) {
				randomX = r.nextInt(4);
				chance = r.nextInt(10);
				if(chance <=7) { // 8/10 = .8 chance of occurrence
					if(subArray4[randomX] == 0) {
						openSlot = true;
						subArray4[randomX] = 2;
					}	
				} else { // 2/10 = .2 chance of occurrence
					if(subArray4[randomX] == 0) {
						openSlot = true;
						subArray4[randomX] = 4;
					}
				}
			}
			
			if(endGame()) {
				gameOver = true; // When this boolean is set to true, the main loop in the MainGame Class finishes
				break;
			}
		}
		
		gameArray[0] = subArray1;
		gameArray[1] = subArray2;
		gameArray[2] = subArray3;
		gameArray[3] = subArray4;
		
		return gameArray;
	}
	
	public static String printModArray(int[] intArray) {
		int i = 0;
		String str = "";
		
		while(i < intArray.length) {
			if(i != intArray.length - 1){
				if(intArray[i] != 0) {
					if(intArray[i] < 10) { // Used for visual formatting
						str += intArray[i] + "          ";
					} else if(intArray[i] < 100) {
						str += intArray[i] + "         ";
					} else if(intArray[i] < 1000){
						str += intArray[i] + "        ";
					} else {
						str += intArray[i] + "       ";
					}
				} else {
					str += "*" + "          ";
				}
			} else {
				if(intArray[i] != 0) {
					str+= intArray[i];
				} else {
					str += "*";
				}
			}
			i++;
		}
		
		return str;
	}		
	
	public static void print2048Array(int[][] array) { // Print each subArray on a line
		for(int[] row : array) {
		    System.out.println(printModArray(row));
		    System.out.println("\n");
		}
	}
	
	public static void nextMove(String direction) {
		/* The nextMove method contains a LOT of code and is a little
		 * complicated. There are four main if-statements (for each 
		 * of the supported directions). The code for the horizontal
		 * directions is very similiar, as is the code for the vertical
		 * directions. All of the movement and summing occurs within
		 * this method. Comments are present throughout where I felt
		 * necessary
		 */
		if(direction.equalsIgnoreCase("a")) {
			/*For the horizonal directions, there are two main loops for
			 * each subArray. The first is always summing, while the second
			 * is always movement.
			 */
			for(int i = 1; i < 4; i++) { //subArray 1 sum
				if(subArray1[i] != 0) {
					for(int n = i-1; n >= 0; n--) {
						if(subArray1[i] != subArray1[n] && subArray1[n] != 0) { // Makes sure no numbers are skipped over when combining like numbers
							break;
						}
						if(subArray1[i] == subArray1[n]) {
							subArray1[n] += subArray1[i];
							subArray1[i] = 0;
						}
					}
					
				}
			}
			
			for(int i = 1; i < 4; i++) { // //subArray 1 movement; start at index 1 because index 0 cannot move left
				if(subArray1[i] != 0 && subArray1[i-1] == 0) {
					int emptySpot = 0; // Used to find the farthest away empty spot, making sure spots in between are also empty
					for(int n = 0; n < 4; n++) {
						if(subArray1[n] == 0) {
							emptySpot = n;
							break;
						}
					}
					subArray1[emptySpot] = subArray1[i];
					subArray1[i] = 0;
				}
			}
			
			for(int i = 1; i < 4; i++) { //subArray 2 sum
				if(subArray2[i] != 0) {
					for(int n = i-1; n >= 0; n--) {
						if(subArray2[i] != subArray2[n] && subArray2[n] != 0) { // Makes sure no numbers are skipped over when combining like numbers
							break;
						}
						if(subArray2[i] == subArray2[n]) {
							subArray2[n] += subArray2[i];
							subArray2[i] = 0;
						}
					}
					
				}
			}
			
			for(int i = 1; i < 4; i++) { // //subArray 2 movement
				if(subArray2[i] != 0 && subArray2[i-1] == 0) {
					int emptySpot = 0;
					for(int n = 0; n < 4; n++) {
						if(subArray2[n] == 0) {
							emptySpot = n;
							break;
						}
					}
					subArray2[emptySpot] = subArray2[i];
					subArray2[i] = 0;
				}
			}
			
			for(int i = 1; i < 4; i++) { //subArray 3 sum
				if(subArray3[i] != 0) {
					for(int n = i-1; n >= 0; n--) {
						if(subArray3[i] != subArray3[n] && subArray3[n] != 0) { // Makes sure no numbers are skipped over when combining like numbers
							break;
						}
						if(subArray3[i] == subArray3[n]) {
							subArray3[n] += subArray3[i];
							subArray3[i] = 0;
						}
					}
					
				}
			}
			
			for(int i = 1; i < 4; i++) { //subArray 3 movement
				if(subArray3[i] != 0 && subArray3[i-1] == 0) {
					int emptySpot = 0;
					for(int n = 0; n < 4; n++) {
						if(subArray3[n] == 0) {
							emptySpot = n;
							break;
						}
					}
					subArray3[emptySpot] = subArray3[i];
					subArray3[i] = 0;
				}
			}
			
			for(int i = 1; i < 4; i++) { //subArray 4 sum
				if(subArray4[i] != 0) {
					for(int n = i-1; n >= 0; n--) {
						if(subArray4[i] != subArray4[n] && subArray4[n] != 0) { // Makes sure no numbers are skipped over when combining like numbers
							break;
						}
						if(subArray4[i] == subArray4[n]) {
							subArray4[n] += subArray4[i];
							subArray4[i] = 0;
						}
					}
					
				}
			}
			
			for(int i = 1; i < 4; i++) { //subArray 4 movement
				if(subArray4[i] != 0 && subArray4[i-1] == 0) {
					int emptySpot = 0;
					for(int n = 0; n < 4; n++) {
						if(subArray4[n] == 0) {
							emptySpot = n;
							break;
						}
					}
					subArray4[emptySpot] = subArray4[i];
					subArray4[i] = 0;
				}
			}
		}
		
		if(direction.equalsIgnoreCase("d")) { 
			for(int i = 2; i >= 0; i--) {//subArray 1 sum
				if(subArray1[i] != 0) {
					for(int n = i+1; n < 4; n++) {
						if(subArray1[i] != subArray1[n] && subArray1[n] != 0) { // Makes sure no numbers are skipped over when combining like numbers
							break;
						}
						if(subArray1[i] == subArray1[n]) {
							subArray1[n] += subArray1[i];
							subArray1[i] = 0;
						}
					}
					
				}
			}
			for(int i = 2; i >= 0; i--) { //subArray 1 movement
				if(subArray1[i] != 0 && subArray1[i+1] == 0) {
					int emptySpot = 0;
					for(int n = 3; n >= 0; n--) {
						if(subArray1[n] == 0) {
							emptySpot = n;
							break;
						}
					}
					subArray1[emptySpot] = subArray1[i];
					subArray1[i] = 0;
				}
			}
			
			for(int i = 2; i >= 0; i--) { //subArray 2 sum
				if(subArray2[i] != 0) {
					for(int n = i+1; n < 4; n++) {
						if(subArray2[i] != subArray2[n] && subArray2[n] != 0) { // Makes sure no numbers are skipped over when combining like numbers
							break;
						}
						if(subArray2[i] == subArray2[n]) {
							subArray2[n] += subArray2[i];
							subArray2[i] = 0;
						}
					}
					
				}
			}
			for(int i = 2; i >= 0; i--) { //subArray 2 movement
				if(subArray2[i] != 0 && subArray2[i+1] == 0) {
					int emptySpot = 0;
					for(int n = 3; n >= 0; n--) {
						if(subArray2[n] == 0) {
							emptySpot = n;
							break;
						}
					}
					subArray2[emptySpot] = subArray2[i];
					subArray2[i] = 0;
				}
			}
			
			for(int i = 2; i >= 0; i--) { //subArray 3 sum
				if(subArray3[i] != 0) {
					for(int n = i+1; n < 4; n++) {
						if(subArray3[i] != subArray3[n] && subArray3[n] != 0) { // Makes sure no numbers are skipped over when combining like numbers
							break;
						}
						if(subArray3[i] == subArray3[n]) {
							subArray3[n] += subArray3[i];
							subArray3[i] = 0;
						}
					}
					
				}
			}
			for(int i = 2; i >= 0; i--) { //subArray 3 movement
				if(subArray3[i] != 0 && subArray3[i+1] == 0) {
					int emptySpot = 0;
					for(int n = 3; n >= 0; n--) {
						if(subArray3[n] == 0) {
							emptySpot = n;
							break;
						}
					}
					subArray3[emptySpot] = subArray3[i];
					subArray3[i] = 0;
				}
			}
			
			for(int i = 2; i >= 0; i--) { //subArray 4 sum
				if(subArray4[i] != 0) {
					for(int n = i+1; n < 4; n++) {
						if(subArray4[i] != subArray4[n] && subArray4[n] != 0) { // Makes sure no numbers are skipped over when combining like numbers
							break;
						}
						if(subArray4[i] == subArray4[n]) {
							subArray4[n] += subArray4[i];
							subArray4[i] = 0;
						}
					}
					
				}
			}
			for(int i = 2; i >= 0; i--) { //subArray 4 movement
				if(subArray4[i] != 0 && subArray4[i+1] == 0) {
					int emptySpot = 0;
					for(int n = 3; n >= 0; n--) {
						if(subArray4[n] == 0) {
							emptySpot = n;
							break;
						}
					}
					subArray4[emptySpot] = subArray4[i];
					subArray4[i] = 0;
				}
			}
		}
		
		if(direction.equalsIgnoreCase("s")) {
			/* For the vertical directions, each subArray has two main loops,
			 * one for summing and one for movement. The loops this time, however,
			 * are grouped by type (summing or movement) instead of by subArray.
			 */
			boolean skip4 = false; // These skip booleans are used to make sure a number that has already been summed once in a turn won't be summed again that turn
			boolean skip3 = false;
			
			for(int i = 0; i < 4; i++) { // subArray3 sum
				if(subArray3[i] == subArray4[i] && subArray3[i] != 0) {
					subArray4[i] += subArray3[i];
					subArray3[i] = 0;
					skip4 = true;
				}
			}
			for(int i = 0; i < 4; i++) { // subArray2 sum
				if(subArray2[i] == subArray3[i] && subArray2[i] != 0) {
					subArray3[i] += subArray2[i];
					subArray2[i] = 0;
					skip3 = true;
				}
				/*For a number in row 2 to combine with a number in row 4, row 3
				 * in that column must be zero, and the number in row 4 in that
				 * column cannot have been summed already that same turn. This
				 * logic is repeated a lot in this method.
				 */
				if(subArray2[i] == subArray4[i] && subArray2[i] != 0 && subArray3[i] == 0 && !skip4) {
					subArray4[i] += subArray2[i];
					subArray2[i] = 0;
					skip4 = true;
				}
			}
			for(int i = 0; i < 4; i++) { // subArray1 sum
				if(subArray1[i] == subArray2[i] && subArray1[i] != 0) {
					subArray2[i] += subArray1[i];
					subArray1[i] = 0;
				}
				if(subArray1[i] == subArray3[i] && subArray1[i] != 0 && subArray2[i] == 0 && !skip3) {
					subArray3[i] += subArray1[i];
					subArray1[i] = 0;
				}
				if(subArray1[i] == subArray4[i] && subArray1[i] != 0 && subArray2[i] == 0 && subArray3[i] == 0 && !skip4) {
					subArray4[i] += subArray1[i];
					subArray1[i] = 0;
				}
			}
			
			for(int i = 0; i < 4; i++) { // subArray3 movement (finding empty spot)
				if(subArray4[i] == 0) {
					subArray4[i] = subArray3[i];
					subArray3[i] = 0;
				}
			}
			
			for(int i = 0; i < 4; i++) { // subArray2 movement (finding empty spot)
				if(subArray3[i] == 0) { // If row 3 is zero, check if row 4 is also zero. This logic is also used a lot up ahead.
					if(subArray4[i] == 0) {
							subArray4[i] = subArray2[i];
							subArray2[i] = 0;
					} else {
						subArray3[i] = subArray2[i];
						subArray2[i] = 0;
					}
				}
			}
			
			for(int i = 0; i < 4; i++) { // subArray1 movement (finding empty spot)
				if(subArray2[i] == 0) {
					if(subArray3[i] == 0) {
						if(subArray4[i] == 0) {
							subArray4[i] = subArray1[i];
							subArray1[i] = 0;
						} else {
							subArray3[i] = subArray1[i];
							subArray1[i] = 0;
						}
					} else {
						subArray2[i] = subArray1[i];
						subArray1[i] = 0;
					}
				}
			}
			
		}
		
		if(direction.equalsIgnoreCase("w")) {
			boolean skip1 = false;
			boolean skip2 = false;
			
			for(int i = 0; i < 4; i++) { // subArray2 sum
				if(subArray2[i] == subArray1[i] && subArray2[i] != 0) {
					subArray1[i] += subArray2[i];
					subArray2[i] = 0;
					skip1 = true;
				}
			}
			for(int i = 0; i < 4; i++) { // subArray3 sum
				if(subArray3[i] == subArray2[i] && subArray3[i] != 0) {
					subArray2[i] += subArray3[i];
					subArray3[i] = 0;
					skip2 = true;
				}
				if(subArray3[i] == subArray1[i] && subArray3[i] != 0 && subArray2[i] == 0 && !skip1) {
					subArray1[i] += subArray3[i];
					subArray3[i] = 0;
					skip1 = true;
				}
			}
			for(int i = 0; i < 4; i++) { // subArray4 sum
				if(subArray4[i] == subArray3[i] && subArray4[i] != 0) {
					subArray3[i] += subArray4[i];
					subArray4[i] = 0;
				}
				if(subArray4[i] == subArray2[i] && subArray4[i] != 0 && subArray3[i] == 0 && !skip2) {
					subArray2[i] += subArray4[i];
					subArray4[i] = 0;
				}
				if(subArray4[i] == subArray1[i] && subArray4[i] != 0 && subArray3[i] == 0 && subArray2[i] == 0 && !skip1) {
					subArray1[i] += subArray4[i];
					subArray4[i] = 0;
				}
			}
			
			for(int i = 0; i < 4; i++) { // subArray2 movement (finding empty spot)
				if(subArray1[i] == 0) {
					subArray1[i] = subArray2[i];
					subArray2[i] = 0;
				}
			}
			
			for(int i = 0; i < 4; i++) { // subArray3 movement (finding empty spot)
				if(subArray2[i] == 0) {
					if(subArray1[i] == 0) {
							subArray1[i] = subArray3[i];
							subArray3[i] = 0;
					} else {
						subArray2[i] = subArray3[i];
						subArray3[i] = 0;
					}
				}
			}
			
			for(int i = 0; i < 4; i++) { // subArray4 movement (finding empty spot)
				if(subArray3[i] == 0) {
					if(subArray2[i] == 0) {
						if(subArray1[i] == 0) {
							subArray1[i] = subArray4[i];
							subArray4[i] = 0;
						} else {
							subArray2[i] = subArray4[i];
							subArray4[i] = 0;
						}
					} else {
						subArray3[i] = subArray4[i];
						subArray4[i] = 0;
					}
				}
			}
			
		}
		score++; // Increase score by 1 after each (valid) move
	}
	
public static void nextMoveValidity(String direction) {
		/* This method is identical to the nextMove method, except
		 * movement and summing is conducted using the throwaway
		 * sub-arrays used in the validMove and endGame methods.
		 * Comments in this method are left over from my testing
		 * and do not need to be considered.
		 */
		if(direction.equalsIgnoreCase("a")) {
			for(int i = 1; i < 4; i++) {
				if(subCompare1[i] != 0) {
					for(int n = i-1; n >= 0; n--) {
						if(subCompare1[i] != subCompare1[n] && subCompare1[n] != 0) { // Makes sure no numbers are skipped over when combining like numbers
							break;
						}
						if(subCompare1[i] == subCompare1[n]) {
							subCompare1[n] += subCompare1[i];
							subCompare1[i] = 0;
						}
					}
					
				}
			}
			
			for(int i = 1; i < 4; i++) { // Start at index 1 because index 0 cannot move left
				if(subCompare1[i] != 0 && subCompare1[i-1] == 0) {
					int emptySpot = 0;
					for(int n = 0; n < 4; n++) {
						if(subCompare1[n] == 0) {
							emptySpot = n;
							break;
						}
					}
					subCompare1[emptySpot] = subCompare1[i];
					subCompare1[i] = 0;
				}
			}
			
			for(int i = 1; i < 4; i++) {
				if(subCompare2[i] != 0) {
					for(int n = i-1; n >= 0; n--) {
						if(subCompare2[i] != subCompare2[n] && subCompare2[n] != 0) { // Makes sure no numbers are skipped over when combining like numbers
							break;
						}
						if(subCompare2[i] == subCompare2[n]) {
							subCompare2[n] += subCompare2[i];
							subCompare2[i] = 0;
						}
					}
					
				}
			}
			
			for(int i = 1; i < 4; i++) { // Start at index 1 because index 0 cannot move left
				if(subCompare2[i] != 0 && subCompare2[i-1] == 0) {
					int emptySpot = 0;
					for(int n = 0; n < 4; n++) {
						if(subCompare2[n] == 0) {
							emptySpot = n;
							break;
						}
					}
					subCompare2[emptySpot] = subCompare2[i];
					subCompare2[i] = 0;
				}
			}
			
			for(int i = 1; i < 4; i++) {
				if(subCompare3[i] != 0) {
					for(int n = i-1; n >= 0; n--) {
						if(subCompare3[i] != subCompare3[n] && subCompare3[n] != 0) { // Makes sure no numbers are skipped over when combining like numbers
							break;
						}
						if(subCompare3[i] == subCompare3[n]) {
							subCompare3[n] += subCompare3[i];
							subCompare3[i] = 0;
						}
					}
					
				}
			}
			
			for(int i = 1; i < 4; i++) { // Start at index 1 because index 0 cannot move left
				if(subCompare3[i] != 0 && subCompare3[i-1] == 0) {
					int emptySpot = 0;
					for(int n = 0; n < 4; n++) {
						if(subCompare3[n] == 0) {
							emptySpot = n;
							break;
						}
					}
					subCompare3[emptySpot] = subCompare3[i];
					subCompare3[i] = 0;
				}
			}
			
			for(int i = 1; i < 4; i++) {
				if(subCompare4[i] != 0) {
					for(int n = i-1; n >= 0; n--) {
						if(subCompare4[i] != subCompare4[n] && subCompare4[n] != 0) { // Makes sure no numbers are skipped over when combining like numbers
							break;
						}
						if(subCompare4[i] == subCompare4[n]) {
							subCompare4[n] += subCompare4[i];
							subCompare4[i] = 0;
						}
					}
					
				}
			}
			
			for(int i = 1; i < 4; i++) { // Start at index 1 because index 0 cannot move left
				if(subCompare4[i] != 0 && subCompare4[i-1] == 0) {
					int emptySpot = 0;
					for(int n = 0; n < 4; n++) {
						if(subCompare4[n] == 0) {
							emptySpot = n;
							break;
						}
					}
					subCompare4[emptySpot] = subCompare4[i];
					subCompare4[i] = 0;
				}
			}
		}
		
		if(direction.equalsIgnoreCase("d")) {
			for(int i = 2; i >= 0; i--) {
				if(subCompare1[i] != 0) {
					for(int n = i+1; n < 4; n++) {
						if(subCompare1[i] != subCompare1[n] && subCompare1[n] != 0) { // Makes sure no numbers are skipped over when combining like numbers
							break;
						}
						if(subCompare1[i] == subCompare1[n]) {
							subCompare1[n] += subCompare1[i];
							subCompare1[i] = 0;
						}
					}
					
				}
			}
			for(int i = 2; i >= 0; i--) { // Start at index 2 because index 3 cannot move right
				if(subCompare1[i] != 0 && subCompare1[i+1] == 0) {
					int emptySpot = 0;
					for(int n = 3; n >= 0; n--) {
						if(subCompare1[n] == 0) {
							emptySpot = n;
							break;
						}
					}
					subCompare1[emptySpot] = subCompare1[i];
					subCompare1[i] = 0;
				}
			}
			
			for(int i = 2; i >= 0; i--) {
				if(subCompare2[i] != 0) {
					for(int n = i+1; n < 4; n++) {
						if(subCompare2[i] != subCompare2[n] && subCompare2[n] != 0) { // Makes sure no numbers are skipped over when combining like numbers
							break;
						}
						if(subCompare2[i] == subCompare2[n]) {
							subCompare2[n] += subCompare2[i];
							subCompare2[i] = 0;
						}
					}
					
				}
			}
			for(int i = 2; i >= 0; i--) {
				if(subCompare2[i] != 0 && subCompare2[i+1] == 0) {
					int emptySpot = 0;
					for(int n = 3; n >= 0; n--) {
						if(subCompare2[n] == 0) {
							emptySpot = n;
							break;
						}
					}
					subCompare2[emptySpot] = subCompare2[i];
					subCompare2[i] = 0;
				}
			}
			
			for(int i = 2; i >= 0; i--) {
				if(subCompare3[i] != 0) {
					for(int n = i+1; n < 4; n++) {
						if(subCompare3[i] != subCompare3[n] && subCompare3[n] != 0) { // Makes sure no numbers are skipped over when combining like numbers
							break;
						}
						if(subCompare3[i] == subCompare3[n]) {
							subCompare3[n] += subCompare3[i];
							subCompare3[i] = 0;
						}
					}
					
				}
			}
			for(int i = 2; i >= 0; i--) {
				if(subCompare3[i] != 0 && subCompare3[i+1] == 0) {
					int emptySpot = 0;
					for(int n = 3; n >= 0; n--) {
						if(subCompare3[n] == 0) {
							emptySpot = n;
							break;
						}
					}
					subCompare3[emptySpot] = subCompare3[i];
					subCompare3[i] = 0;
				}
			}
			
			for(int i = 2; i >= 0; i--) {
				if(subCompare4[i] != 0) {
					for(int n = i+1; n < 4; n++) {
						if(subCompare4[i] != subCompare4[n] && subCompare4[n] != 0) { // Makes sure no numbers are skipped over when combining like numbers
							break;
						}
						if(subCompare4[i] == subCompare4[n]) {
							subCompare4[n] += subCompare4[i];
							subCompare4[i] = 0;
						}
					}
					
				}
			}
			for(int i = 2; i >= 0; i--) {
				if(subCompare4[i] != 0 && subCompare4[i+1] == 0) {
					int emptySpot = 0;
					for(int n = 3; n >= 0; n--) {
						if(subCompare4[n] == 0) {
							emptySpot = n;
							break;
						}
					}
					subCompare4[emptySpot] = subCompare4[i];
					subCompare4[i] = 0;
				}
			}
		}
		
		if(direction.equalsIgnoreCase("s")) {
			boolean skip4 = false; // These skip booleans are used to make sure a number that has already been summed once in a turn won't be summed again that turn
			boolean skip3 = false;
			
			for(int i = 0; i < 4; i++) { // subCompare3 sum
				if(subCompare3[i] == subCompare4[i] && subCompare3[i] != 0) {
					subCompare4[i] += subCompare3[i];
					subCompare3[i] = 0;
					skip4 = true;
				}
			}
			for(int i = 0; i < 4; i++) { // subCompare2 sum
				if(subCompare2[i] == subCompare3[i] && subCompare2[i] != 0) {
					subCompare3[i] += subCompare2[i];
					subCompare2[i] = 0;
					skip3 = true;
				}
				if(subCompare2[i] == subCompare4[i] && subCompare2[i] != 0 && subCompare3[i] == 0 && !skip4) {
					subCompare4[i] += subCompare2[i];
					subCompare2[i] = 0;
					skip4 = true;
				}
			}
			for(int i = 0; i < 4; i++) { // subCompare1 sum
				if(subCompare1[i] == subCompare2[i] && subCompare1[i] != 0) {
					subCompare2[i] += subCompare1[i];
					subCompare1[i] = 0;
				}
				if(subCompare1[i] == subCompare3[i] && subCompare1[i] != 0 && subCompare2[i] == 0 && !skip3) {
					subCompare3[i] += subCompare1[i];
					subCompare1[i] = 0;
				}
				if(subCompare1[i] == subCompare4[i] && subCompare1[i] != 0 && subCompare2[i] == 0 && subCompare3[i] == 0 && !skip4) {
					subCompare4[i] += subCompare1[i];
					subCompare1[i] = 0;
				}
			}
			
			for(int i = 0; i < 4; i++) { // subCompare3 movement (finding empty spot)
				if(subCompare4[i] == 0) {
					subCompare4[i] = subCompare3[i];
					subCompare3[i] = 0;
				}
			}
			
			for(int i = 0; i < 4; i++) { // subCompare2 movement (finding empty spot)
				if(subCompare3[i] == 0) {
					if(subCompare4[i] == 0) {
							subCompare4[i] = subCompare2[i];
							subCompare2[i] = 0;
					} else {
						subCompare3[i] = subCompare2[i];
						subCompare2[i] = 0;
					}
				}
			}
			
			for(int i = 0; i < 4; i++) { // subCompare1 movement (finding empty spot)
				if(subCompare2[i] == 0) {
					if(subCompare3[i] == 0) {
						if(subCompare4[i] == 0) {
							subCompare4[i] = subCompare1[i];
							subCompare1[i] = 0;
						} else {
							subCompare3[i] = subCompare1[i];
							subCompare1[i] = 0;
						}
					} else {
						subCompare2[i] = subCompare1[i];
						subCompare1[i] = 0;
					}
				}
			}
			
		}
		
		if(direction.equalsIgnoreCase("w")) {
			boolean skip1 = false;
			boolean skip2 = false;
			
			for(int i = 0; i < 4; i++) { // subCompare2 sum
				if(subCompare2[i] == subCompare1[i] && subCompare2[i] != 0) {
					subCompare1[i] += subCompare2[i];
					subCompare2[i] = 0;
					skip1 = true;
				}
			}
			for(int i = 0; i < 4; i++) { // subCompare3 sum
				if(subCompare3[i] == subCompare2[i] && subCompare3[i] != 0) {
					subCompare2[i] += subCompare3[i];
					subCompare3[i] = 0;
					skip2 = true;
				}
				if(subCompare3[i] == subCompare1[i] && subCompare3[i] != 0 && subCompare2[i] == 0 && !skip1) {
					subCompare1[i] += subCompare3[i];
					subCompare3[i] = 0;
					skip1 = true;
				}
			}
			for(int i = 0; i < 4; i++) { // subCompare4 sum
				if(subCompare4[i] == subCompare3[i] && subCompare4[i] != 0) {
					subCompare3[i] += subCompare4[i];
					subCompare4[i] = 0;
				}
				if(subCompare4[i] == subCompare2[i] && subCompare4[i] != 0 && subCompare3[i] == 0 && !skip2) {
					subCompare2[i] += subCompare4[i];
					subCompare4[i] = 0;
				}
				if(subCompare4[i] == subCompare1[i] && subCompare4[i] != 0 && subCompare3[i] == 0 && subCompare2[i] == 0 && !skip1) {
					subCompare1[i] += subCompare4[i];
					subCompare4[i] = 0;
				}
			}
			
			for(int i = 0; i < 4; i++) { // subCompare2 movement (finding empty spot)
				if(subCompare1[i] == 0) {
					subCompare1[i] = subCompare2[i];
					subCompare2[i] = 0;
				}
			}
			
			for(int i = 0; i < 4; i++) { // subCompare3 movement (finding empty spot)
				if(subCompare2[i] == 0) {
					if(subCompare1[i] == 0) {
							subCompare1[i] = subCompare3[i];
							subCompare3[i] = 0;
					} else {
						subCompare2[i] = subCompare3[i];
						subCompare3[i] = 0;
					}
				}
			}
			
			for(int i = 0; i < 4; i++) { // subCompare4 movement (finding empty spot)
				if(subCompare3[i] == 0) {
					if(subCompare2[i] == 0) {
						if(subCompare1[i] == 0) {
							subCompare1[i] = subCompare4[i];
							subCompare4[i] = 0;
						} else {
							subCompare2[i] = subCompare4[i];
							subCompare4[i] = 0;
						}
					} else {
						subCompare3[i] = subCompare4[i];
						subCompare4[i] = 0;
					}
				}
			}
			
		}
	}
	
}
