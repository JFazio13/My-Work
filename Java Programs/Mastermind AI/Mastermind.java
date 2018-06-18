import java.util.*;

public class Mastermind {
	
	protected String[] tokenColors, tokenColorsConverted, guess;
	protected int tokenPositions;
	protected ArrayList<String> possibilityList = new ArrayList();
	protected boolean firstGuess = true;
	
	public Mastermind(String[] tokenColors, int tokenPositions) { // Constructs Mastermind game
		this.tokenColors = tokenColors;
		this.tokenPositions = tokenPositions;
	}

	public String[] getTokenColors() {
		return tokenColors;
	}

	public void setTokenColors(String[] tokenColors) {
		this.tokenColors = tokenColors;
	}

	public int getTokenPositions() {
		return tokenPositions;
	}

	public void setTokenPositions(int tokenPositions) {
		this.tokenPositions = tokenPositions;
	}
	
	private String[] convert(String [] tokenColors) { // This method is used by the generate() method to convert color names (ex. Red) to letters (ex. R)
		int i = 0;
		String [] tokenColorsConverted = new String[tokenColors.length];
		while(i < tokenColors.length) {
			String str = tokenColors[i];
			String colorChar = str.substring(0, 1);
			tokenColorsConverted[i] = colorChar;
			i++;
		}
		this.tokenColorsConverted = tokenColorsConverted;
		return tokenColorsConverted;
	}
	
	public void generate() { // This method calls the other generate() method by filling in its parameters
		generate(convert(tokenColors), tokenPositions, "");
	}
	
	public void generate(String[] tokenColorsConverted, int tokenPositions, String result) { // This method generates the list of possible solutions
		if(result.length() >= tokenPositions){
			possibilityList.add(result);
		} else{
			int i = 0;
			while(i< tokenColorsConverted.length){
				generate(tokenColorsConverted, tokenPositions, result+tokenColorsConverted[i]);
				i++;
			}
		}
	}
	
	public String[] nextMove() { // This method generates each guess
		String[] guess = new String[1];
		Random random = new Random();
		int r = random.nextInt(possibilityList.size()); // Allows guesses to randomly select a guess based on what's left in the arraylist
		if(firstGuess) {
			System.out.println("Preparing first guess...");
			guess[0] = possibilityList.get(0);
			System.out.println(Arrays.deepToString(guess));
			possibilityList.remove(0); // Removes guess from the list of possibilities so it cannot be guessed again
			this.guess = guess;
			return guess;
		} else {
			System.out.println("Preparing next guess...");
		
		guess[0] = possibilityList.get(r);
		System.out.println(Arrays.deepToString(guess));
		possibilityList.remove(r); // Removes guess from the list of possibilities so it cannot be guessed again
		this.guess = guess;
		return guess;
		}
	}
	 
	 public void response(int colorsRightPositionWrong, int positionsAndColorRight) { // This method attempts to analyze feedback given by the user and creates a new list of possibilities
		 int colorRight = colorsRightPositionWrong; // Re-casting these variables for name-simplicity
		 int bothRight = positionsAndColorRight;
		 String g = guess[0];
		 String[] g2 = new String[g.length()];
		 boolean newGuess = true;
		 int i = 0;
		 int j = 0;

		 while(i < g.length()) {
			 g2[i] = g.substring(j, j+1);
			 i++;
			 j++;
		 }
		 if(firstGuess && colorRight > 0) {
			 firstGuess = false;
			 newGuess = false;
			 int x = 0;
			 ArrayList<String> newList = new ArrayList();
			 while(x < possibilityList.size()) {
				 String s = possibilityList.get(x);
				 if(s.contains(g2[0])) {
					 newList.add(possibilityList.get(x));
				 }
				 x++;
			 }
			 possibilityList = newList;
		 }
		 
		 if(newGuess && colorRight == 1) {
			 int x = 0;
			 ArrayList<String> newList = new ArrayList();
			 while(x < possibilityList.size()) {
				 String s = possibilityList.get(x);
				 int y = 0;
				 while(y < g2.length) {
					 if(s.contains(g2[y])) {
						 newList.add(possibilityList.get(x));
						 break;
					 }
					 y++;
				 }
				 x++;
			 }
			 possibilityList = newList;
		 }
		 
		 if(colorRight == 2) {
			 int x = 0;
			 ArrayList<String> newList = new ArrayList();
			 while(x < possibilityList.size()) {
				 String s = possibilityList.get(x);
				 int y = 0;
				 int retain = 0;
				 while(y < g2.length) {
					 if(s.contains(g2[y])) {
						 retain++;
					 }
					 if(retain >= g2.length - 1) {
						 newList.add(possibilityList.get(x));
						 break;
					 }
					 y++;
				 }
				 x++;
			 }
			 possibilityList = newList;
		 }
		 
		 if(colorRight >= 3) {
			 int x = 0;
			 ArrayList<String> newList = new ArrayList();
			 while(x < possibilityList.size()) {
				 String s = possibilityList.get(x);
				 int y = 0;
				 int retain = 0;
				 while(y < g2.length) {
					 if(s.contains(g2[y])) {
						 retain++;
					 }
					 if(retain >= g2.length) {
						 newList.add(possibilityList.get(x));
						 break;
					 }
					 y++;
				 }
				 x++;
			 }
			 possibilityList = newList;
		 }
		 
		 if(bothRight == 1) {
			 int x = 0;
			 ArrayList<String> newList = new ArrayList();
			 while(x < possibilityList.size()) {
				 String s = possibilityList.get(x);
				 String[] s2 = new String[s.length()];
				 int n = 0;
				 int m = 0;
				 while(n < s.length()) {
					 s2[n] = s.substring(m, m+1);
					 n++;
					 m++;
				 }
				 int y = 0;
				 while(y < g2.length) {
					 if(s2[y].equals(g2[y])) {
						 newList.add(possibilityList.get(x));
						 break;
					 }
					 y++;
				 }
				 x++;
			 }
			 possibilityList = newList;
		 }
		 
		 if(bothRight == 2) {
			 int x = 0;
			 ArrayList<String> newList = new ArrayList();
			 while(x < possibilityList.size()) {
				 String s = possibilityList.get(x);
				 String[] s2 = new String[s.length()];
				 int n = 0;
				 int m = 0;
				 int retain = 0;
				 while(n < s.length()) {
					 s2[n] = s.substring(m, m+1);
					 n++;
					 m++;
				 }
				 int y = 0;
				 while(y < g2.length) {
					 if(s2[y].equals(g2[y])) {
						 retain++;
					 }
					 if(retain >= 2) {
						 newList.add(possibilityList.get(x));
						 break;
					 }
					 
					 y++;
				 }
				 x++;
			 }
			 possibilityList = newList;
		 }
		 
		 if(bothRight == 3) {
			 int x = 0;
			 ArrayList<String> newList = new ArrayList();
			 while(x < possibilityList.size()) {
				 String s = possibilityList.get(x);
				 String[] s2 = new String[s.length()];
				 int n = 0;
				 int m = 0;
				 int retain = 0;
				 while(n < s.length()) {
					 s2[n] = s.substring(m, m+1);
					 n++;
					 m++;
				 }
				 int y = 0;
				 while(y < g2.length) {
					 if(s2[y].equals(g2[y])) {
						 retain++;
					 }
					 if(retain >= 3) {
						 newList.add(possibilityList.get(x));
						 break;
					 }
					 
					 y++;
				 }
				 x++;
			 }
			 possibilityList = newList;
		 }
		 
		 if(bothRight == 4) {
			 int x = 0;
			 ArrayList<String> newList = new ArrayList();
			 while(x < possibilityList.size()) {
				 String s = possibilityList.get(x);
				 String[] s2 = new String[s.length()];
				 int n = 0;
				 int m = 0;
				 int retain = 0;
				 while(n < s.length()) {
					 s2[n] = s.substring(m, m+1);
					 n++;
					 m++;
				 }
				 int y = 0;
				 while(y < g2.length) {
					 if(s2[y].equals(g2[y])) {
						 retain++;
					 }
					 if(retain >= 4) {
						 newList.add(possibilityList.get(x));
						 break;
					 }
					 
					 y++;
				 }
				 x++;
			 }
			 possibilityList = newList;
		 }
		 
		 if(bothRight == tokenPositions) {
			 System.out.println("I successfully cracked the code!");
			 System.out.println();
		 }
		 firstGuess = false;
	 }
	 
	 public static void newGame() { // Since the main game is run from one method in the MainGame class, resetting the game is as simple as calling the method again.
		Scanner in = new Scanner(System.in);	
		System.out.print("Would you like to play again? Enter 'Y' or 'N': ");
		String playAgain = in.next();
		System.out.println();
		if(playAgain.equalsIgnoreCase("y")) {
			MainGame.playGame();
		} else {
			System.out.println("Thanks for playing!");
			System.exit(0);
		}
		in.close();
	 }

}
