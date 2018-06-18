import java.io.*;
import java.util.*;
/*James Fazio
jfazio2@u.rochester.edu
No collaboration
*/
public class InfixToPostfix {
	protected File inFileName, outFileName;
	protected DoubleLinkedList<Double> answerList= new DoubleLinkedList(); // Stores results as the program runs; is handed off to writeOutput() for writing to output file
	
	public InfixToPostfix(String inFileName, String outFileName) { // Input and output files names are set at construction time
		this.inFileName = new File(inFileName);
		this.outFileName = new File(outFileName);
	}
	
	public File getOutFileName() {
		return outFileName;
	}

	public void setOutFileName(File outFileName) {
		this.outFileName = outFileName;
	}

	public File getInFileName() {
		return inFileName;
	}

	public void setInFileName(File inFileName) {
		this.inFileName = inFileName;
	}
	
	public void conversion(ArrayList list) { // The conversion method takes in the arraylist of tokens generated in the Calculator class
		Stack stack = new Stack();
		Queue queue = new Queue();
		int i = 0;
		
		System.out.println("Converting...");
		
		while(i < list.size()) { // Traverses the arraylist of tokens
			Object x = list.get(i);
			if(x instanceof Double) { // Based on how my tokenList generator works, if a token is an operand, it is also a double
				queue.enqueue(x); // If a token is an operator, enqueue it
			} else if(x.equals("(")) { 
				stack.push(x); // If a token is a (, push it onto the stack
			} else if(x.equals(")")) { // If a token is a ), pop and enqueue stack tokens until a ( is found
				boolean stop = false;
				while(!stop) {
					Object pop = stack.pop();
					if(pop.equals("(")) {
						stop = true;
						break;
					} else {
						queue.enqueue(pop);
					}
				}
			} else if(x.equals("^")) { // If a token is a ^, push it onto the stack if an operator of lower precedence is found
				boolean stop = false;
				while(!stop) {
					Object peek = stack.peek();
					if(peek.equals(0) || peek.equals("*") || peek.equals("/") || peek.equals("+") || peek.equals("-") || peek.equals("(") || peek.equals("=") || peek.equals("<") || peek.equals(">") || peek.equals("!") || peek.equals("&") || peek.equals("|")) {
						stop = true;
						stack.push(x);
						break;
					} else { // If the same operator or one of higher precedence is found, pop and enqueue the found token, then repeat
						Object pop = stack.pop();
						queue.enqueue(pop);
					}
				}
			} else if(x.equals("*") || x.equals("/")) { // If a token is a * or /, push it onto the stack if an operator of lower precedence is found
				boolean stop = false;
				while(!stop) {
					Object peek = stack.peek();
					if(peek.equals(0) || peek.equals("+") || peek.equals("-") || peek.equals("(") || peek.equals("=") || peek.equals("<") || peek.equals(">") || peek.equals("!") || peek.equals("&") || peek.equals("|")) {
						stop = true;
						stack.push(x);
						break;
					} else { // If the same operator or one of higher precedence is found, pop and enqueue the found token, then repeat
						Object pop = stack.pop();
						queue.enqueue(pop);
					}
				}
			} else if(x.equals("+") || x.equals("-")) { // If a token is a + or -, push it onto the stack if an operator of lower precedence is found
				boolean stop = false;
				while(!stop) {
					Object peek = stack.peek();
					if(peek.equals(0) || peek.equals("(") || peek.equals("=") || peek.equals("<") || peek.equals(">") || peek.equals("!") || peek.equals("&") || peek.equals("|")) {
						stop = true;
						stack.push(x);
						break;
					} else { // If the same operator or one of higher precedence is found, pop and enqueue the found token, then repeat
						Object pop = stack.pop();
						queue.enqueue(pop);
					}
				}	
			} else if(x.equals("<") || x.equals(">") || x.equals("=")) { // If a token is a <, >, or =, push it onto the stack if an operator of lower precedence is found
				boolean stop = false;
				while(!stop) {
					Object peek = stack.peek();
					if(peek.equals(0) || peek.equals("(") || peek.equals("!") || peek.equals("&") || peek.equals("|")) {
						stop = true;
						stack.push(x);
						break;
					} else { // If the same operator or one of higher precedence is found, pop and enqueue the found token, then repeat
						Object pop = stack.pop();
						queue.enqueue(pop);
					}
				}	
			} else if(x.equals("!")) { // If a token is an !, push it onto the stack if an operator of lower precedence is found
				boolean stop = false;
				while(!stop) {
					Object peek = stack.peek();
					if(peek.equals(0) || peek.equals("(") || peek.equals("&") || peek.equals("|")) {
						stop = true;
						stack.push(x);
						break;
					} else { // If the same operator or one of higher precedence is found, pop and enqueue the found token, then repeat
						Object pop = stack.pop();
						queue.enqueue(pop);
					}
				}	
			} else if(x.equals("&")) { // If a token is an &, push it onto the stack if an operator of lower precedence is found
				boolean stop = false;
				while(!stop) {
					Object peek = stack.peek();
					if(peek.equals(0) || peek.equals("(") || peek.equals("|")) {
						stop = true;
						stack.push(x);
						break;
					} else { // If the same operator or one of higher precedence is found, pop and enqueue the found token, then repeat
						Object pop = stack.pop();
						queue.enqueue(pop);
					}
				}	
			} else if(x.equals("|")) { // If a token is a |, push it onto the stack if an operator of lower precedence is found
				boolean stop = false;
				while(!stop) {
					Object peek = stack.peek();
					if(peek.equals(0) || peek.equals("(")) {
						stop = true;
						stack.push(x);
						break;
					} else { // If the same operator or one of higher precedence is found, pop and enqueue the found token, then repeat
						Object pop = stack.pop();
						queue.enqueue(pop);
					}
				}	
			}
			if(i + 1 == list.size()) { // After all tokens have been processed, the remaining tokens in the stack are popped and enqueued
				while(!stack.isEmpty()) {
					Object pop = stack.pop();
					queue.enqueue(pop);
				}
			}
			i++;
		}
		queue.displayPostfix();
		evaluation(stack, queue); // Evaluation method is privately called
	}
	
	public void writeOutput(DoubleLinkedList list) { // The results of evaluation for all expressions in the input file are stored in a double linked list and written to the output here
		System.out.println("Evaluation written to output: " + outFileName);
		list.writeListReverse(outFileName);
	}
	
	private void evaluation(Stack stack, Queue queue) { // The private evaluation method inherits the stack and queue from the conversion method
		System.out.println("Evaluating...");
			while(!queue.isEmpty()) { // Process tokens until the queue is empty
				Object x = queue.dequeue();
				if(x instanceof Double) { // If the token is an operand, push it onto the stack
					stack.push(x);
				} else {
					if(x.equals("^") || x.equals("*") || x.equals("/") || x.equals("+") || x.equals("-")) { // If the token is a traditional operator, pop two tokens from the stack and perform the correct operation
						double operand1 = (Double) stack.pop();
						double operand2 = (Double) stack.pop();
						double result = 0;
						if(x.equals("^")) {
							result = Math.pow(operand2, operand1);
							stack.push(result);
						}
						if(x.equals("*")) {
							result = operand2 * operand1;
							stack.push(result);
						}
						if(x.equals("/")) {
							result = operand2 / operand1;
							stack.push(result);
						}
						if(x.equals("+")) {
							result = operand2 + operand1;
							stack.push(result);
						}
						if(x.equals("-")) {
							result = operand2 - operand1;
							stack.push(result);
						}
					} else if(x.equals("<") || x.equals(">") || x.equals("=") || x.equals("&") || x.equals("|")) { // If the token is a comparative or logical (two-token-required) operator, pop two stack tokens and evaluate
						double operand1 = (Double) stack.pop();
						double operand2 = (Double) stack.pop();
						double result = 0;
						if(x.equals("<")) {
							if(operand2 < operand1) {
								result = 1.0;
							} else {
								result = 0.0;
							}
							stack.push(result);
						}
						if(x.equals(">")) {
							if(operand2 > operand1) {
								result = 1.0;
							} else {
								result = 0.0;
							}
							stack.push(result);
						}
						if(x.equals("=")) {
							if(operand2 == operand1) {
								result = 1.0;
							} else {
								result = 0.0;
							}
							stack.push(result);
						}
						if(x.equals("&")) {
							if(operand2 == 1 && operand1 == 1) {
								result = 1.0;
							} else {
								result = 0.0;
							}
							stack.push(result);
						}
						if(x.equals("|")) {
							if(operand2 == 1 || operand1 == 1) {
								result = 1.0;
							} else {
								result = 0.0;
							}
							stack.push(result);
						}
					} else if(x.equals("!")) { // If the token is an ! (one-token-required), pop one stack token and evaluate
						double operand1 = (Double) stack.pop();
						double result = 0;
						if(operand1 == 0) {
							result = 1.0;
						} else if (operand1 == 1) {
							result = 0.0;
						}
						stack.push(result);
					}
				}
			}
			double answer = (Double) stack.pop(); // When all tokens have been processed and the queue is emptied, the final value in the stack is the result
			answerList.insertFirst(answer); // The result of each expression is added to a double linked list to be written to the output after all expressions have been evaluated
	}
	
}
