/* James Fazio
 * Project 2
 * Lab: Mon/Wed 12:30 – 1:45
 * Lab TA: Sofia Carrillo
 * 
 * I did not collaborate with anyone on this assignment.
 */

import java.util.Scanner;

public class Hole {
	
	protected int number;
	protected int distance;
	protected int par;
	protected int score;

	public Hole(int number, int distance, int par) {
		this.number = number;
		this.distance = distance;
		this.par = par;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public int getDistance() {
		return distance;
	}

	public void setPar(int par) {
		this.par = par;
	}
	
	public int getPar() {
		return par;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public void nextHole() { // Method to reduce amount of code in the MainGame class.
		
		Scanner in = new Scanner(System.in);
		System.out.println("Driving your golf cart to the next hole..." + "\n");
		System.out.print("Enter any character to continue, or enter 'Q' to quit: ");
		String play = in.next();
		System.out.println();
		
		if(play.equalsIgnoreCase("Q")) {
			System.out.println("Thanks for playing! Hope to see you again soon...");
			System.exit(0);
		}
		
	}
	
	public void playHole() { // Drastically reduces the amount of code in the MainGame class.
		
		System.out.println("TYY at Sawgrass – Hole " + number + ", " + distance + " yards, Par " + par + "\n");
		
		Club driver = new Club("Driver", 230, 30);
		Club wood3 = new Club("3-Wood", 215, 20);
		Club iron3 = new Club("3-Iron", 180, 20);
		Club iron4 = new Club("4-Iron", 170, 17);
		Club iron5 = new Club("5-Iron", 155, 15);
		Club iron6 = new Club("6-Iron", 145, 15);
		Club iron7 = new Club("7-Iron", 135, 15);
		Club iron8 = new Club("8-Iron", 125, 15);
		Club iron9 = new Club("9-Iron", 110, 10);
		Club wedge = new Club("Pitching Wedge", 50, 10);
		
		Putter p10 = new Putter(10, 40, 5);
		Putter p9 = new Putter(9, 30, 5);
		Putter p8 = new Putter(8, 25, 4);
		Putter p7 = new Putter(7, 20, 4);
		Putter p6 = new Putter(6, 16, 3);
		Putter p5 = new Putter(5, 12, 3);
		Putter p4 = new Putter(4, 8, 2);
		Putter p3 = new Putter(3, 4, 2);
		Putter p2 = new Putter(2, 2, 1);
		Putter p1 = new Putter(1, 1, 1);
				
		Scanner in = new Scanner(System.in);
		int ball = 0;
		int stroke = 1;
			
		while(ball < distance - 20) { // While the ball is not on the green...
		
			int shotDistance = 0;
			int puttDistance = 0;
			
			System.out.println("Stroke " + stroke + ", distance from green: " + (distance - 20 - ball) + " yards" + "\n"); // Computes ball distance from green.
			System.out.print("Please select your club: ");
			int num = in.nextInt();
			System.out.println();
			
			if(num == 1) {
				System.out.println("You have selected your driver." + "\n");
				driver.setPower();
				driver.setDistance();
				shotDistance = driver.getDistance();
			}
	
			if(num == 2) {
				System.out.println("You have selected your 3-Wood." + "\n");
				wood3.setPower();
				wood3.setDistance();
				shotDistance = wood3.getDistance();
			}
	
			if(num == 3) {
				System.out.println("You have selected your 3-Iron." + "\n");
				iron3.setPower();
				iron3.setDistance();
				shotDistance = iron3.getDistance();
			}
	
			if(num == 4) {
				System.out.println("You have selected your 4-Iron." + "\n");
				iron4.setPower();
				iron4.setDistance();
				shotDistance = iron4.getDistance();
			}
	
			if(num == 5) {
				System.out.println("You have selected your 5-Iron." + "\n");
				iron5.setPower();
				iron5.setDistance();
				shotDistance = iron5.getDistance();
			}
	
			if(num == 6) {
				System.out.println("You have selected your 6-Iron." + "\n");
				iron6.setPower();
				iron6.setDistance();
				shotDistance = iron6.getDistance();
			}
	
			if(num == 7) {
				System.out.println("You have selected your 7-Iron." + "\n");
				iron7.setPower();
				iron7.setDistance();
				shotDistance = iron7.getDistance();
			}
	
			if(num == 8) {
				System.out.println("You have selected your 8-Iron." + "\n");
				iron8.setPower();
				iron8.setDistance();
				shotDistance = iron8.getDistance();
			}
	
			if(num == 9) {
				System.out.println("You have selected your 9-Iron." + "\n");
				iron9.setPower();
				iron9.setDistance();
				shotDistance = iron9.getDistance();
			}
	
			if(num == 10) {
				System.out.println("You have selected your Pitching Wedge." + "\n");
				wedge.setPower();
				wedge.setDistance();
				shotDistance = wedge.getDistance();
			}
			
			if(num < 1 || num > 10) {
				System.out.println("Error code 2: Your club selection must be between 1-10." + "\n");
				continue;
			}
			
			ball = ball + shotDistance; // Moves the ball forward.
			
			if(stroke == par + 5) { // Cuts the player off at a score of +5.
				System.out.println("You have reached the maximum number of strokes for this hole. Your score for this hole is +5..." + "\n");
				score = score + 5;
				break;
			}
			
			stroke++;
			
			if(ball > distance) { // One stroke penalty for over-shooting. No ball movement.
				System.out.println("Out of bounds!" + "\n");
				ball = ball - shotDistance;
				System.out.println("You were " + (distance - 20 - ball) + " yards from the green, and your ball traveled " + shotDistance + " yards." + "\n");
				System.out.println("Your ball will return to its previous location on the hole..." + "\n");
				continue;
			}
			
			if(ball >= (distance - 20) && ball <= distance) { // Allows for chip-ins and shots going in the hole without having to putt.
				
				if(ball >=(distance - 1) && ball <= distance) {
					System.out.println("Woah!! That shot went in!" + "\n");
					System.out.println("Your ball traveled " + shotDistance + " yards and ended up in the cup!" + "\n");

					if(stroke == par) {
						System.out.println("You made par." + "\n"); // Score does not change.
					}
					
					if(stroke == par - 1) {
						System.out.println("You got a birdie!" + "\n");
						score = score - 1;
					}
					
					if(stroke == (par - 2) && par != 3) {
						System.out.println("You got an eagle!!" + "\n");
						score = score - 2;
					}
					
					if(stroke == (par - 2) && par == 3) {
						System.out.println("Woah! Hole in one!!" + "\n");
						score = score - 2;
					}
					
					if(stroke == (par - 3) && par == 4) {
						System.out.println("Woah! Hole in one!!" + "\n");
						score = score - 3;
					}
					
					if(stroke == (par - 3) && par == 5) {
						System.out.println("You got a double eagle!!" + "\n");
						score = score - 3;
					}
					
					if(stroke == (par - 4) && par == 5) {
						System.out.println("Woah! Hole in one!!" + "\n");
						score = score - 4;
					}
					
					if(stroke == par + 1) {
						System.out.println("You got a bogey..." + "\n");
						score = score + 1;
					}
					
					if(stroke == par + 2) {
						System.out.println("You got a double bogey..." + "\n");
						score = score + 2;
					}
					
					if(stroke == par + 3) {
						System.out.println("You got a triple bogey..." + "\n");
						score = score + 3;
					}
					
					if(stroke == par + 4) {
						System.out.println("You got a score of +4..." + "\n");
						score = score + 4;
					}
					
					if(stroke == par + 5) {
						System.out.println("You got a score of +5..." + "\n");
						score = score + 5;
					}
					
					break;
				}
				
				System.out.println("You are on the green!" + "\n");
				System.out.println("Your ball traveled " + shotDistance + " yards and is " + (distance - ball) + " feet from the cup." + "\n");
				
				int ballInFeet = ball * 3; // Converts units to feet for putting.
				int distanceInFeet = distance * 3;
				
				while(ballInFeet < (distanceInFeet - 1)) { // While the ball is on the green...
					
					System.out.println("Stroke " + stroke + ", distance from cup: " + (distanceInFeet - 1 - ballInFeet) + " feet" + "\n");
					System.out.print("You are now putting. Please set your putt strength (1-10): ");
					
					int num2 = in.nextInt();
					System.out.println();
					
					if(num2 == 10) {
						p10.setDistance();
						puttDistance = p10.getDistance();
					}

					if(num2 == 9) {
						p9.setDistance();
						puttDistance = p9.getDistance();
					}

					if(num2 == 8) {
						p8.setDistance();
						puttDistance = p8.getDistance();
					}

					if(num2 == 7) {
						p7.setDistance();
						puttDistance = p7.getDistance();
					}

					if(num2 == 6) {
						p6.setDistance();
						puttDistance = p6.getDistance();
					}

					if(num2 == 5) {
						p5.setDistance();
						puttDistance = p5.getDistance();
					}

					if(num2 == 4) {
						p4.setDistance();
						puttDistance = p4.getDistance();
					}

					if(num2 == 3) {
						p3.setDistance();
						puttDistance = p3.getDistance();
					}

					if(num2 == 2) {
						p2.setDistance();
						puttDistance = p2.getDistance();
					}

					if(num2 == 1) {
						p1.setDistance();
						puttDistance = p1.getDistance();
					}
					
					if(num2 == 0) {
						System.out.println("You missed the ball..." + "\n");
					}
					
					ballInFeet = ballInFeet + puttDistance;
					
					if(ballInFeet >= (distanceInFeet - 1) && ballInFeet <= (distanceInFeet + 10)) { // + 10 used to give the player a cushion
						System.out.println("You sunk the putt!" + "\n");
						
						if(stroke == par) {
							System.out.println("You made par." + "\n"); // Score does not change.
						}
						
						if(stroke == par - 1) {
							System.out.println("You got a birdie!" + "\n");
							score = score - 1;
						}
						
						if(stroke == (par - 2) && par != 3) {
							System.out.println("You got an eagle!!" + "\n");
							score = score - 2;
						}
						
						if(stroke == (par - 2) && par == 3) {
							System.out.println("Woah! Hole in one!!" + "\n");
							score = score - 2;
						}
						
						if(stroke == (par - 3) && par == 4) {
							System.out.println("Woah! Hole in one!!" + "\n");
							score = score - 3;
						}
						
						if(stroke == (par - 3) && par == 5) {
							System.out.println("You got a double eagle!!" + "\n");
							score = score - 3;
						}
						
						if(stroke == (par - 4) && par == 5) {
							System.out.println("Woah! Hole in one!!" + "\n");
							score = score - 4;
						}
						
						if(stroke == par + 1) {
							System.out.println("You got a bogey..." + "\n");
							score = score + 1;
						}
						
						if(stroke == par + 2) {
							System.out.println("You got a double bogey..." + "\n");
							score = score + 2;
						}
						
						if(stroke == par + 3) {
							System.out.println("You got a triple bogey..." + "\n");
							score = score + 3;
						}
						
						if(stroke == par + 4) {
							System.out.println("You got a score of +4..." + "\n");
							score = score + 4;
						}
						
						if(stroke == par + 5) {
							System.out.println("You got a score of +5..." + "\n");
							score = score + 5;
						}
						
						break;
					}
					
					if(ballInFeet > (distanceInFeet + 10)) { // Same penalty as above.
						System.out.println("Out of bounds!" + "\n");
						ballInFeet = ballInFeet - puttDistance;
						System.out.println("Your putt traveled " + puttDistance + " feet, but you were only " + (distanceInFeet - 1 - ballInFeet) + " feet from the hole." + "\n");
						System.out.println("Your ball will return to its previous location on the green." + "\n");
						stroke++;
						continue;
					}
					
					if(stroke == par + 5) {
						System.out.println("You have reached the maximum number of strokes for this hole. Your score for this hole is +5..." + "\n");
						break;
					}
					
					System.out.println("You missed the putt! Your putt traveled " + puttDistance + " feet, and you are " + (distanceInFeet - 1 - ballInFeet) + " feet from the hole." + "\n");
					stroke++;
				}
				break;
			}
			
			System.out.println("Your ball traveled " + shotDistance + " yards and is " + (distance - 20 - ball) + " yards from the green." + "\n");
			
		}
		
	}

}
