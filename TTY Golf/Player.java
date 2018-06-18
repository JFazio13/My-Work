/* James Fazio
 * Project 2
 * Lab: Mon/Wed 12:30 – 1:45
 * Lab TA: Sofia Carrillo
 * 
 * I did not collaborate with anyone on this assignment.
 */

import java.util.Scanner;

public class Player {
	
	protected String name;

	public Player(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return "Player: " + name;
	}
	
	public void enterPlayerName() {
		
		Scanner in = new Scanner(System.in);
		System.out.print("Welcome to TTY Golf! Please enter your name: ");
		name = in.next();
		System.out.println();
		System.out.println("Hello, " + name + ", nice to meet you!" + "\n");
		
	}
	
}
