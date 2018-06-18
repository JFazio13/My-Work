import java.io.*;
import java.util.*;
/*James Fazio
jfazio2@u.rochester.edu
No collaboration
*/
public class Calculator {

	public static void main(String[] args) {
		InfixToPostfix calc = new InfixToPostfix("infix_expr_short.txt", "results.txt");
		
		try { // Reads-in infix expressions from a file and disperses all of the tokens into an arraylist
			Scanner in = new Scanner(calc.inFileName);
			String token = null;
			int i = 0;
			while(in.hasNext()) { // File is read line by line and initially stored in a string
				token = in.nextLine();
				System.out.println("Infix expression found: " + token);
				ArrayList<Object> tokenList = new ArrayList();
				Scanner in2 = new Scanner(token); // A new scanner traverses the token string to correctly separate operators and operands
				while(in2.hasNext()) {
					if(in2.hasNextDouble()) { // If the next token is a double (an operand), add it to the arraylist
						tokenList.add(in2.nextDouble());
					} else { // Otherwise, begin checking the string representation for parentheses or ! (special operators)
						String tokenCheck = in2.next();
						if((tokenCheck.contains("(")) || (tokenCheck.contains(")")) || (tokenCheck.contains("!"))) {
							int j = 0;
							while(j < tokenCheck.length()) {
								String x = tokenCheck.substring(j, j+1);
								if(x.equals("(") || x.equals("!")) {
									tokenList.add(x); // Adds the special operator to the list as a separate token, then checks the next token to see if it is also a special operator
									if(tokenCheck.substring(j+1, j+2).equals("(")) {
										tokenList.add(tokenCheck.substring(j+1, j+2)); // If the token after the first special operator is another special operator, it's also added separately
										tokenList.add(Double.parseDouble(tokenCheck.substring(j+2, j+3)));
									} else { // If not, the rest of the tokenCheck string is added
										tokenList.add(Double.parseDouble(tokenCheck.substring(j+1, j+2)));
									}
									break;
								}
								if(x.equals(")")) {
									tokenList.add(Double.parseDouble(tokenCheck.substring(j-1, j)));
									tokenList.add(x);
									if(tokenCheck.length() > 2 && tokenCheck.substring(j+1, j+2).equals(")")) {
										tokenList.add(tokenCheck.substring(j+1, j+2));
									}
									break;
								}
								j++;
							}
						} else {
							tokenList.add(tokenCheck);
						}
					}
				}	
				calc.conversion(tokenList); // Converts expression to postfix; evaluation method privately called
				calc.writeOutput(calc.answerList); // Writes results to output
			}
		} catch (FileNotFoundException e) {
			System.out.println("File " + calc.inFileName + " not found.");
		}
	}
}
