import java.util.*;

public class TTT {
	protected String[][] board = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}}; // Board is set up as a 2D array of Strings, " ", X, or O

	protected String playerToken = "", cpuToken = ""; // Used when move is placed; set to either X or O
	
	protected Position cpuMove; // Temporary cpu move storage
	
	protected boolean gameOver =  false;
	
	protected String winner = "";
	
	public TTT(String playerToken) { // Instantiates game and sets tokens for player and cpu
		this.playerToken = playerToken;
		if(playerToken.equals("X")) {
			cpuToken = "O";
		} else {
			cpuToken = "X";
		}
	}
	
	public void resetGame() { // Resets game board and winner; start new game
		gameOver = false;
		winner = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = " ";
			}
		}
		System.err.println("Starting new game..." + "\n");
	}
	
	public void resetBoard() { // Reset board only
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = " ";
			}
		}
	}
	
	public boolean boardFull() { // Determine if board is full by searching for spaces
		boolean filled = true;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j].equals(" ")) {
					filled = false;
				}
			}
		}
		return filled;
	}
	
	public void printBoard() { // Display game board
		System.err.println("         |         |");
		System.err.println("    " + board[0][0] + "    |    " + board[0][1] + "    |    " + board[0][2]);
		System.err.println("         |         |");
		System.err.println("-----------------------------");
		System.err.println("         |         |");
		System.err.println("    " + board[1][0] + "    |    " + board[1][1] + "    |    " + board[1][2]);
		System.err.println("         |         |");
		System.err.println("-----------------------------");
		System.err.println("         |         |");
		System.err.println("    " + board[2][0] + "    |    " + board[2][1] + "    |    " + board[2][2]);
		System.err.println("         |         |");
	}
	
	public void playerMove(int position) { // Place player's move; call cpuMove()
		Scanner in = new Scanner(System.in);
		boolean moveMade = false; // If a position is unavailable, the method is called again. This prevents cpuMove() from being called before a successful move
		
		switch (position) {
		case 1:
			if(board[0][0].equals(" ")) { // If space is empty, place player's token, else prompt for new position and recall method
				board[0][0] = playerToken;
				moveMade = true;
			} else {
				System.err.print("Position already played. Enter your next move (position 1-9): ");
				playerMove(in.nextInt());
			}
			break;
		case 2:
			if(board[0][1].equals(" ")) {
				board[0][1] = playerToken;
				moveMade = true;
			} else {
				System.err.print("Position already played. Enter your next move (position 1-9): ");
				playerMove(in.nextInt());
			}
			break;
		case 3:
			if(board[0][2].equals(" ")) {
				board[0][2] = playerToken;
				moveMade = true;
			} else {
				System.err.print("Position already played. Enter your next move (position 1-9): ");
				playerMove(in.nextInt());
			}
			break;
		case 4:
			if(board[1][0].equals(" ")) {
				board[1][0] = playerToken;
				moveMade = true;
			} else {
				System.err.print("Position already played. Enter your next move (position 1-9): ");
				playerMove(in.nextInt());
			}
			break;
		case 5:
			if(board[1][1].equals(" ")) {
				board[1][1] = playerToken;
				moveMade = true;
			} else {
				System.err.print("Position already played. Enter your next move (position 1-9): ");
				playerMove(in.nextInt());
			}
			break;
		case 6:
			if(board[1][2].equals(" ")) {
				board[1][2] = playerToken;
				moveMade = true;
			} else {
				System.err.print("Position already played. Enter your next move (position 1-9): ");
				playerMove(in.nextInt());
			}
			break;
		case 7:
			if(board[2][0].equals(" ")) {
				board[2][0] = playerToken;
				moveMade = true;
			} else {
				System.err.print("Position already played. Enter your next move (position 1-9): ");
				playerMove(in.nextInt());
			}
			break;
		case 8:
			if(board[2][1].equals(" ")) {
				board[2][1] = playerToken;
				moveMade = true;
			} else {
				System.err.print("Position already played. Enter your next move (position 1-9): ");
				playerMove(in.nextInt());
			}
			break;
		case 9:
			if(board[2][2].equals(" ")) {
				board[2][2] = playerToken;
				moveMade = true;
			} else {
				System.err.print("Position already played. Enter your next move (position 1-9): ");
				playerMove(in.nextInt());
			}
		}
		if(moveMade) { // If successful move, check for a player win, then call for cpu move if board isn't full
			checkWin(playerToken);
			if(!boardFull()) {
				cpuMove();
			}
		}
	}
	
	public void checkWin(String token) { // Checks horizontals, verticals, and diagonals for matches (win)
		boolean winner = false;
		if (board[0][0].equals(board[0][1]) && board[0][1].equals(board[0][2]) && !board[0][0].equals(" ")) {
			winner = true;
		}
		if (board[1][0].equals(board[1][1]) && board[1][1].equals(board[1][2]) && !board[1][0].equals(" ")) {
			winner = true;
		}
		if (board[2][0].equals(board[2][1]) && board[2][1].equals(board[2][2]) && !board[2][0].equals(" ")) {
			winner = true;
		}
		if (board[0][0].equals(board[1][0]) && board[1][0].equals(board[2][0]) && !board[0][0].equals(" ")) {
			winner = true;
		}
		if (board[0][1].equals(board[1][1]) && board[1][1].equals(board[2][1]) && !board[0][1].equals(" ")) {
			winner = true;
		}
		if (board[0][2].equals(board[1][2]) && board[1][2].equals(board[2][2]) && !board[0][2].equals(" ")) {
			winner = true;
		}
		if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].equals(" ")) {
			winner = true;
		}
		if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[0][2].equals(" ")) {
			winner = true;
		}
		
		if(winner == true) {
			gameOver = true;
			if(token.equals(playerToken)) {
				this.winner = "player"; // Impossible!
			} else {
				this.winner = "cpu";
			}
		}
	}
	
	public void checkWinner() { // Check top-level winner variable for a winner
		if (winner.equals("player")) {
			System.err.println("\n" + "You win!");
		} else if (winner.equals("cpu")) {
			System.err.println("\n" + "You lose!");
		}
	}
	
	public List<Position> getStates() { // Get all empty positions on board to compute states and next available actions
		List<Position> emptyPositions = new ArrayList();
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals(" ")) {
                    emptyPositions.add(new Position(i, j));
                }
            }
        }
		return emptyPositions;
	}
	
	public void placeMove(Position point, String player) { // Place a move, given position and player/cpu
		if(player.equalsIgnoreCase("cpu")) {
			board[point.x][point.y] = cpuToken;
		} else {
			board[point.x][point.y] = playerToken;
		}
    }
	
	public boolean checkCPUWin() { // Check for win, specifically for cpu; used in minimax
		boolean winner = false;
		if (board[0][0].equals(board[0][1]) && board[0][1].equals(board[0][2]) && board[0][0].equals(cpuToken)) {
			winner = true;
		}
		if (board[1][0].equals(board[1][1]) && board[1][1].equals(board[1][2]) && board[1][0].equals(cpuToken)) {
			winner = true;
		}
		if (board[2][0].equals(board[2][1]) && board[2][1].equals(board[2][2]) && board[2][0].equals(cpuToken)) {
			winner = true;
		}
		if (board[0][0].equals(board[1][0]) && board[1][0].equals(board[2][0]) && board[0][0].equals(cpuToken)) {
			winner = true;
		}
		if (board[0][1].equals(board[1][1]) && board[1][1].equals(board[2][1]) && board[0][1].equals(cpuToken)) {
			winner = true;
		}
		if (board[0][2].equals(board[1][2]) && board[1][2].equals(board[2][2]) && board[0][2].equals(cpuToken)) {
			winner = true;
		}
		if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && board[0][0].equals(cpuToken)) {
			winner = true;
		}
		if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && board[0][2].equals(cpuToken)) {
			winner = true;
		}
		
		if(winner == true) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkPlayerWin() { // Check for win, specifically for player; used in minimax
		boolean winner = false;
		if (board[0][0].equals(board[0][1]) && board[0][1].equals(board[0][2]) && board[0][0].equals(playerToken)) {
			winner = true;
		}
		if (board[1][0].equals(board[1][1]) && board[1][1].equals(board[1][2]) && board[1][0].equals(playerToken)) {
			winner = true;
		}
		if (board[2][0].equals(board[2][1]) && board[2][1].equals(board[2][2]) && board[2][0].equals(playerToken)) {
			winner = true;
		}
		if (board[0][0].equals(board[1][0]) && board[1][0].equals(board[2][0]) && board[0][0].equals(playerToken)) {
			winner = true;
		}
		if (board[0][1].equals(board[1][1]) && board[1][1].equals(board[2][1]) && board[0][1].equals(playerToken)) {
			winner = true;
		}
		if (board[0][2].equals(board[1][2]) && board[1][2].equals(board[2][2]) && board[0][2].equals(playerToken)) {
			winner = true;
		}
		if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && board[0][0].equals(playerToken)) {
			winner = true;
		}
		if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && board[0][2].equals(playerToken)) {
			winner = true;
		}
		
		if(winner == true) {
			return true;
		} else {
			return false;
		}
	}
	
	public int minimax(int depth, String turn) { // Compute best next move for cpu, based on available states and actions
		if (checkCPUWin()) { // If cpu has won, positive utility (terminal state)
			return 10;
		}
        if (checkPlayerWin()) { // If cpu has lost, negative utility (terminal state) **Not possible**
        		return -10;
        }
		
		List<Position> states = getStates();
		if(states.isEmpty()) { // If board is full and no one has won, 0 utility (terminal state)
			return 0;
		}
		
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		for (int i = 0; i < states.size(); i++) { // For all available moves, test move then recurse for player (min) move
			Position point = states.get(i);
			if (turn.equalsIgnoreCase("cpu")) { // If cpu (max) move, test move and compute minimax values, then recurse for player move
				placeMove(point, "cpu");
				int score = minimax(depth + 1, "player"); // Recurse for player (min) move; increase depth by 1
				max = Math.max(score, max); // Compute max minimax value
				
				if (score >= 0) { // Select cpu move based on score, depth, and max
					if(depth == 0) {
						cpuMove = point;
					}
				}
				if (score == 1) {
					board[point.x][point.y] = " ";
					break;
				}
				if ((i == states.size() - 1) && max < 0) {
					if(depth == 0) {
						cpuMove = point;
					}
				}
			} else if (turn.equalsIgnoreCase("player")) { // If player (min) move, test move, compute minimax values, and recurse for cpu move
                placeMove(point, "player");
                int score = minimax(depth + 1, "cpu");
                min = Math.min(score, min);
                if(min == -1) {
                		board[point.x][point.y] = " "; break;
                }
            }
			board[point.x][point.y] = " "; // Reset tested space
		}
		if(turn.equalsIgnoreCase("cpu")) {
			return max;
		} else {
			return min;
		}
	}
	
	public void cpuMove() { // Select and place best possible cpu move
		minimax(0, "cpu");
		placeMove(cpuMove, "cpu");
		checkWin(cpuToken);
	}
}
