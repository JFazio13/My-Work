/* James Fazio
 * Project 2
 * Lab: Mon/Wed 12:30 – 1:45
 * Lab TA: Sofia Carrillo
 * 
 * I did not collaborate with anyone on this assignment.
 */

import java.util.Scanner;

public class MainGame { // The below code (and the code in my other classes should be very clear and self-explanatory. Comments are present when necessary.

	public static void main(String[] args) {
		
		Player a = new Player("Placeholder");
		
		int playerScore = 0; // playerScore used to add up scores from individual holes.

		a.enterPlayerName();
		
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter '1' to begin the game, enter '2' for game instructions, or enter '3' to enter your unlock code for Course 2: ");
		
		int start = in.nextInt(); // Sets up a trigger method to either initiate the game or view instructions first.
		System.out.println();
		
		if (start == 1) {
			
			System.out.println("Great, let's go!" + "\n");
			
		} else {
			
			if (start == 2) {
				System.out.println("Instructions:" + "\n \n" + "- TTY Golf is a linear game that represents the real-life game of golf." + "\n" + "- To hit each shot, you will select a club (1-10) and set your power (1-10)." + "\n" + "- Your distance will be calculated using a random algorithm, meaning that each shot will have a different distance, even if the clubs and powers are the same." + "\n" + "- There are two courses to play – one without hazards, and one with. You will unlock Course 2 after completing Course 1." + "\n" + "\n");
				System.out.print("Enter S to begin the game when you are ready: ");
				String start2 = in.next();
				
				if(start2.equalsIgnoreCase("S")){
					System.out.println("\n" + "Great, let's go!" + "\n");
				}
				
			} else {
				
				if(start == 3) {
					System.out.print("Enter the unlock code to immediately play Course 2: ");
					String unlockCode = in.next(); // Unlock code for playing Course 2 right away is given to the player after they complete Course 1.
					
					if(unlockCode.equals("TTYGOLFMASTER")) {
						System.out.println("\n" + "Unlock code accepted!" + "\n");
					} else {
						System.out.println("Unlock code rejected. Terminating program...");
						System.exit(0);
					}
					
				}
				
				while (start != 1 && start != 2 && start != 3) { // Loop for users who input numbers other than 1, 2, or 3.
					
					System.out.print("Misrecognition. Please enter '1' to begin the game, enter '2' for game instructions, or enter '3' to enter the unlock code for Course 2: ");
					start = in.nextInt();
					System.out.println();
					
					if (start == 1) {
						System.out.println("Great, let's go!");
					} else {
						
						if (start == 2) {
							System.out.println("Instructions:" + "\n \n" + "- TTY Golf is a linear game that represents the real-life game of golf." + "\n" + "- To hit each shot, you will select a club (1-10) and set your power (1-10)." + "\n" + "- Your distance will be calculated using a random algorithm, meaning that each shot will have a different distance, even if the clubs and powers are the same." + "\n" + "- There are two courses to play – one without hazards, and one with. You will unlock Course 2 after completing Course 1." + "\n" + "\n");
							System.out.print("Enter S to begin the game when you are ready: ");
							String start3 = in.next();
							
							if(start3.equalsIgnoreCase("S")){
								System.out.println("\n" + "Great, let's go!");
							}
						} else {

							if(start == 3) {
								System.out.print("Enter the unlock code to immediately play Course 2: ");
								String unlockCode = in.next();
								
								if(unlockCode.equals("TTYGOLFMASTER")) {
									System.out.println("Unlock code accepted!" + "\n");
								} else {
									System.out.println("Unlock code rejected. Terminating program...");
									System.exit(0);
								}
								
							}
							
						}
					}
				}
			}
		
		}
		
		if(start != 3) { // Skips over Course 1 if unlock code is used.
		
			System.out.println("Course 1 – TYY at Sawgrass: This course is a standard golf course with no hazards.\nComplete this course to unlock Course 2, which is more difficult and includes water hazards." + "\n");
			
			//TYY at Sawgrass is on play on TPC at Sawgrass, a PGA golf course. Specifications sourced from golfdigest.com.
			
			Hole h1 = new Hole(1, 423, 4);
			Hole h2 = new Hole(2, 532, 5);
			Hole h3 = new Hole(3, 177, 3);
			Hole h4 = new Hole(4, 384, 4);
			Hole h5 = new Hole(5, 471, 4);
			Hole h6 = new Hole(6, 393, 4);
			Hole h7 = new Hole(7, 442, 4);
			Hole h8 = new Hole(8, 237, 3);
			Hole h9 = new Hole(9, 583, 5);
			Hole h10 = new Hole(10, 424, 4);
			Hole h11 = new Hole(11, 558, 5);
			Hole h12 = new Hole(12, 358, 4);
			Hole h13 = new Hole(13, 181, 3);
			Hole h14 = new Hole(14, 481, 4);
			Hole h15 = new Hole(15, 449, 4);
			Hole h16 = new Hole(16, 523, 5);
			Hole h17 = new Hole(17, 137, 3);
			Hole h18 = new Hole(18, 462, 4);
			
			String play = "Y"; // Sets up the framework to allow the user to replay the course as many times as he/she wants.
				
			while(play.equalsIgnoreCase("Y")) {	
	
				h1.playHole(); // The Hole class contains all of the gameplay code in its playHole method.
					playerScore = h1.getScore();
				
					if(playerScore > 0) {
						System.out.println("Your score after Hole " + h1.getNumber() + ": +" + playerScore + ". It's okay, " + a.getName() + ", it's only the first hole!" + "\n");
					} else {
						System.out.println("Your score after Hole " + h1.getNumber() + ": " + playerScore + "\n");
					}	
				h1.nextHole();
				
				h2.playHole();
					playerScore = playerScore + h2.getScore();
					
					if(playerScore > 0) {
						System.out.println("Your score after Hole " + h2.getNumber() + ": +" + playerScore + "\n");
					} else {
						System.out.println("You're on fire, " + a.getName() + "! Your score after Hole " + h2.getNumber() + ": " + playerScore + "\n");
					}
				h2.nextHole();
				
				h3.playHole();
					playerScore = playerScore + h3.getScore();
					
					if(playerScore > 0) {
						System.out.println("Your score after Hole " + h3.getNumber() + ": +" + playerScore + "\n");
					} else {
						System.out.println("Nice job, " + a.getName() + "! Your score after Hole " + h3.getNumber() + ": " + playerScore + "\n");
					}
				h3.nextHole();
		
				h4.playHole();
					playerScore = playerScore + h4.getScore();
					
					if(playerScore > 0) {
						System.out.println("Your score after Hole " + h4.getNumber() + ": +" + playerScore + "\n");
					} else {
						System.out.println("Nice job, " + a.getName() + "! Your score after Hole " + h4.getNumber() + ": " + playerScore + "\n");
					}
				h4.nextHole();
		
				h5.playHole();
					playerScore = playerScore + h5.getScore();
					
					if(playerScore > 0) {
						System.out.println("Your score after Hole " + h5.getNumber() + ": +" + playerScore + "\n");
					} else {
						System.out.println("Nice job, " + a.getName() + "! Your score after Hole " + h5.getNumber() + ": " + playerScore + "\n");
					}
				h5.nextHole();
		
				h6.playHole();
					playerScore = playerScore + h6.getScore();
					
					if(playerScore > 0) {
						System.out.println("Your score after Hole " + h6.getNumber() + ": +" + playerScore + "\n");
					} else {
						System.out.println("Nice job, " + a.getName() + "! Your score after Hole " + h6.getNumber() + ": " + playerScore + "\n");
					}
				h6.nextHole();
		
				h7.playHole();
					playerScore = playerScore + h7.getScore();
					
					if(playerScore > 0) {
						System.out.println("Your score after Hole " + h7.getNumber() + ": +" + playerScore + "\n");
					} else {
						System.out.println("Nice job, " + a.getName() + "! Your score after Hole " + h7.getNumber() + ": " + playerScore + "\n");
					}
				h7.nextHole();
		
				h8.playHole();
					playerScore = playerScore + h8.getScore();
					
					if(playerScore > 0) {
						System.out.println("Your score after Hole " + h8.getNumber() + ": +" + playerScore + "\n");
					} else {
						System.out.println("Nice job, " + a.getName() + "! Your score after Hole " + h8.getNumber() + ": " + playerScore + "\n");
					}
				h8.nextHole();
		
				h9.playHole();
					playerScore = playerScore + h9.getScore();
					
					if(playerScore > 0) {
						System.out.println("Your score after Hole " + h9.getNumber() + ": +" + playerScore + "\n");
					} else {
						System.out.println("Nice job, " + a.getName() + "! Your score after Hole " + h9.getNumber() + ": " + playerScore + "\n");
					}
				h9.nextHole();
		
				h10.playHole();
					playerScore = playerScore + h10.getScore();
					
					if(playerScore > 0) {
						System.out.println("Your score after Hole " + h10.getNumber() + ": +" + playerScore + "\n");
					} else {
						System.out.println("Nice job, " + a.getName() + "! Your score after Hole " + h10.getNumber() + ": " + playerScore + "\n");
					}
				h10.nextHole();
		
				h11.playHole();
					playerScore = playerScore + h11.getScore();
					
					if(playerScore > 0) {
						System.out.println("Your score after Hole " + h11.getNumber() + ": +" + playerScore + "\n");
					} else {
						System.out.println("Nice job, " + a.getName() + "! Your score after Hole " + h11.getNumber() + ": " + playerScore + "\n");
					}
				h11.nextHole();
		
				h12.playHole();
					playerScore = playerScore + h12.getScore();
					
					if(playerScore > 0) {
						System.out.println("Your score after Hole " + h12.getNumber() + ": +" + playerScore + "\n");
					} else {
						System.out.println("Nice job, " + a.getName() + "! Your score after Hole " + h12.getNumber() + ": " + playerScore + "\n");
					}
				h12.nextHole();
		
				h13.playHole();
					playerScore = playerScore + h13.getScore();
					
					if(playerScore > 0) {
						System.out.println("Your score after Hole " + h13.getNumber() + ": +" + playerScore + "\n");
					} else {
						System.out.println("Nice job, " + a.getName() + "! Your score after Hole " + h13.getNumber() + ": " + playerScore + "\n");
					}
				h13.nextHole();
		
				h14.playHole();
					playerScore = playerScore + h14.getScore();
					
					if(playerScore > 0) {
						System.out.println("Your score after Hole " + h14.getNumber() + ": +" + playerScore + "\n");
					} else {
						System.out.println("Nice job, " + a.getName() + "! Your score after Hole " + h14.getNumber() + ": " + playerScore + "\n");
					}
				h14.nextHole();
		
				h15.playHole();
					playerScore = playerScore + h15.getScore();
					
					if(playerScore > 0) {
						System.out.println("Your score after Hole " + h15.getNumber() + ": +" + playerScore + "\n");
					} else {
						System.out.println("Nice job, " + a.getName() + "! Your score after Hole " + h15.getNumber() + ": " + playerScore + "\n");
					}
				h15.nextHole();
		
				h16.playHole();
					playerScore = playerScore + h16.getScore();
					
					if(playerScore > 0) {
						System.out.println("Your score after Hole " + h16.getNumber() + ": +" + playerScore + "\n");
					} else {
						System.out.println("Nice job, " + a.getName() + "! Your score after Hole " + h16.getNumber() + ": " + playerScore + "\n");
					}
				h16.nextHole();
		
				h17.playHole();
					playerScore = playerScore + h17.getScore();
					
					if(playerScore > 0) {
						System.out.println("Your score after Hole " + h17.getNumber() + ": +" + playerScore + "\n");
					} else {
						System.out.println("Nice job, " + a.getName() + "! Your score after Hole " + h17.getNumber() + ": " + playerScore + "\n");
					}
				h17.nextHole();
				
				h18.playHole();
					playerScore = playerScore + h18.getScore();
					
					if(playerScore > 0) {
						System.out.println("Your final score is +" + playerScore + "\n");
					} else {
						System.out.println("Nice job, " + a.getName() + "! Your final score is " + playerScore + "\n");
					}
			
				System.out.println("That's the round, " + a.getName() + "! Would you like to play another round of golf at TYY at Sawgrass? ");
				System.out.print("Enter 'Y' or 'N': ");
				play = in.next();
				System.out.println();
				
			}
			
		}
		
		System.out.println("Congratulations, you have unlocked a new course! Pebble Beach Links is now available to play." + "\n");
		System.out.println("To play this course right away from the main menu, enter the unlock code: TTYGOLFMASTER" + "\n");
		System.out.println("Would you like to play this course now?");
		System.out.print("Enter 'Y' or 'N': ");
		String pebble = in.next();
		System.out.println();
		
		// Pebble Beach Links is a PGA golf course. Specifications sourced from pebblebeach.com.
		
		Hole2 c1 = new Hole2(1, 380, 4, 300, 320);
		Hole2 c2 = new Hole2(2, 502, 5, 227, 268);
		Hole2 c3 = new Hole2(3, 404, 4, 1, 123);
		Hole2 c4 = new Hole2(4, 331, 4, 290, 310);
		Hole2 c5 = new Hole2(5, 195, 3, 0, 0);
		Hole2 c6 = new Hole2(6, 525, 5, 323, 401);
		Hole2 c7 = new Hole2(7, 109, 3, 5, 88);
		Hole2 c8 = new Hole2(8, 428, 4, 0, 0);
		Hole2 c9 = new Hole2(9, 505, 5, 384, 484);
		Hole2 c10 = new Hole2(10, 495, 4, 264, 274);
		Hole2 c11 = new Hole2(11, 390, 4, 25, 77);
		Hole2 c12 = new Hole2(12, 202, 3, 101, 106);
		Hole2 c13 = new Hole2(13, 445, 4, 158, 203);
		Hole2 c14 = new Hole2(14, 580, 5, 107, 163);
		Hole2 c15 = new Hole2(15, 397, 4, 0, 0);
		Hole2 c16 = new Hole2(16, 403, 4, 23, 87);
		Hole2 c17 = new Hole2(17, 208, 3, 1, 187);
		Hole2 c18 = new Hole2(18, 543, 5, 387, 441);
		
		String play = "Y"; // Same use as in Course 1.
			
		while(play.equalsIgnoreCase("Y")) {	

			c1.playHole(); // As above, the gameplay code is in Hole2's playHole method.
				playerScore = c1.getScore();
			
				if(playerScore > 0) {
					System.out.println("Your score after Hole " + c1.getNumber() + ": +" + playerScore + ". It's okay, " + a.getName() + ", it's only the first hole!" + "\n");
				} else {
					System.out.println("Your score after Hole " + c1.getNumber() + ": " + playerScore + "\n");
				}	
			c1.nextHole();
			
			c2.playHole();
				playerScore = playerScore + c2.getScore();
				
				if(playerScore > 0) {
					System.out.println("Your score after Hole " + c2.getNumber() + ": +" + playerScore + "\n");
				} else {
					System.out.println("You're on fire, " + a.getName() + "! Your score after Hole " + c2.getNumber() + ": " + playerScore + "\n");
				}
			c2.nextHole();
			
			c3.playHole();
				playerScore = playerScore + c3.getScore();
				
				if(playerScore > 0) {
					System.out.println("Your score after Hole " + c3.getNumber() + ": +" + playerScore + "\n");
				} else {
					System.out.println("Nice job, " + a.getName() + "! Your score after Hole " + c3.getNumber() + ": " + playerScore + "\n");
				}
			c3.nextHole();
	
			c4.playHole();
				playerScore = playerScore + c4.getScore();
				
				if(playerScore > 0) {
					System.out.println("Your score after Hole " + c4.getNumber() + ": +" + playerScore + "\n");
				} else {
					System.out.println("Nice job, " + a.getName() + "! Your score after Hole " + c4.getNumber() + ": " + playerScore + "\n");
				}
			c4.nextHole();
	
			c5.playHole();
				playerScore = playerScore + c5.getScore();
				
				if(playerScore > 0) {
					System.out.println("Your score after Hole " + c5.getNumber() + ": +" + playerScore + "\n");
				} else {
					System.out.println("Nice job, " + a.getName() + "! Your score after Hole " + c5.getNumber() + ": " + playerScore + "\n");
				}
			c5.nextHole();
	
			c6.playHole();
				playerScore = playerScore + c6.getScore();
				
				if(playerScore > 0) {
					System.out.println("Your score after Hole " + c6.getNumber() + ": +" + playerScore + "\n");
				} else {
					System.out.println("Nice job, " + a.getName() + "! Your score after Hole " + c6.getNumber() + ": " + playerScore + "\n");
				}
			c6.nextHole();
	
			c7.playHole();
				playerScore = playerScore + c7.getScore();
				
				if(playerScore > 0) {
					System.out.println("Your score after Hole " + c7.getNumber() + ": +" + playerScore + "\n");
				} else {
					System.out.println("Nice job, " + a.getName() + "! Your score after Hole " + c7.getNumber() + ": " + playerScore + "\n");
				}
			c7.nextHole();
	
			c8.playHole();
				playerScore = playerScore + c8.getScore();
				
				if(playerScore > 0) {
					System.out.println("Your score after Hole " + c8.getNumber() + ": +" + playerScore + "\n");
				} else {
					System.out.println("Nice job, " + a.getName() + "! Your score after Hole " + c8.getNumber() + ": " + playerScore + "\n");
				}
			c8.nextHole();
	
			c9.playHole();
				playerScore = playerScore + c9.getScore();
				
				if(playerScore > 0) {
					System.out.println("Your score after Hole " + c9.getNumber() + ": +" + playerScore + "\n");
				} else {
					System.out.println("Nice job, " + a.getName() + "! Your score after Hole " + c9.getNumber() + ": " + playerScore + "\n");
				}
			c9.nextHole();
	
			c10.playHole();
				playerScore = playerScore + c10.getScore();
				
				if(playerScore > 0) {
					System.out.println("Your score after Hole " + c10.getNumber() + ": +" + playerScore + "\n");
				} else {
					System.out.println("Nice job, " + a.getName() + "! Your score after Hole " + c10.getNumber() + ": " + playerScore + "\n");
				}
			c10.nextHole();
	
			c11.playHole();
				playerScore = playerScore + c11.getScore();
				
				if(playerScore > 0) {
					System.out.println("Your score after Hole " + c11.getNumber() + ": +" + playerScore + "\n");
				} else {
					System.out.println("Nice job, " + a.getName() + "! Your score after Hole " + c11.getNumber() + ": " + playerScore + "\n");
				}
			c11.nextHole();
	
			c12.playHole();
				playerScore = playerScore + c12.getScore();
				
				if(playerScore > 0) {
					System.out.println("Your score after Hole " + c12.getNumber() + ": +" + playerScore + "\n");
				} else {
					System.out.println("Nice job, " + a.getName() + "! Your score after Hole " + c12.getNumber() + ": " + playerScore + "\n");
				}
			c12.nextHole();
	
			c13.playHole();
				playerScore = playerScore + c13.getScore();
				
				if(playerScore > 0) {
					System.out.println("Your score after Hole " + c13.getNumber() + ": +" + playerScore + "\n");
				} else {
					System.out.println("Nice job, " + a.getName() + "! Your score after Hole " + c13.getNumber() + ": " + playerScore + "\n");
				}
			c13.nextHole();
	
			c14.playHole();
				playerScore = playerScore + c14.getScore();
				
				if(playerScore > 0) {
					System.out.println("Your score after Hole " + c14.getNumber() + ": +" + playerScore + "\n");
				} else {
					System.out.println("Nice job, " + a.getName() + "! Your score after Hole " + c14.getNumber() + ": " + playerScore + "\n");
				}
			c14.nextHole();
	
			c15.playHole();
				playerScore = playerScore + c15.getScore();
				
				if(playerScore > 0) {
					System.out.println("Your score after Hole " + c15.getNumber() + ": +" + playerScore + "\n");
				} else {
					System.out.println("Nice job, " + a.getName() + "! Your score after Hole " + c15.getNumber() + ": " + playerScore + "\n");
				}
			c15.nextHole();
	
			c16.playHole();
				playerScore = playerScore + c16.getScore();
				
				if(playerScore > 0) {
					System.out.println("Your score after Hole " + c16.getNumber() + ": +" + playerScore + "\n");
				} else {
					System.out.println("Nice job, " + a.getName() + "! Your score after Hole " + c16.getNumber() + ": " + playerScore + "\n");
				}
			c16.nextHole();
	
			c17.playHole();
				playerScore = playerScore + c17.getScore();
				
				if(playerScore > 0) {
					System.out.println("Your score after Hole " + c17.getNumber() + ": +" + playerScore + "\n");
				} else {
					System.out.println("Nice job, " + a.getName() + "! Your score after Hole " + c17.getNumber() + ": " + playerScore + "\n");
				}
			c17.nextHole();
			
			c18.playHole();
				playerScore = playerScore + c18.getScore();
				
				if(playerScore > 0) {
					System.out.println("Your final score is +" + playerScore + "\n");
				} else {
					System.out.println("Nice job, " + a.getName() + "! Your final score is " + playerScore + "\n");
				}
		
			System.out.println("That's the round, " + a.getName() + "! Would you like to play another round of golf at Pebble Beach Links?");
			System.out.print("Enter 'Y' or 'N': ");
			play = in.next();
			System.out.println();
			
		}
		
		System.out.println("Thanks for playing, " + a.getName() + "!");
		
	}

}
