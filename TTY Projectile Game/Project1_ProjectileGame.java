/* James Fazio
 * Project 1
 * Lab: Mon/Wed 12:30 – 1:45
 * Lab TA: Sofia Carrillo
 * 
 * I did not collaborate with anyone on this assignment.
 */

import java.util.Scanner;
import java.util.Random;

public class Project1_ProjectileGame {

	public static void main(String[] args) {

		System.out.println("Welcome to the TTY Projectile Challenge!" + "\n");
		
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter '1' to begin the game, or enter '2' for game instructions: ");
		
		int start = in.nextInt(); // Sets up a trigger method to either initiate the game or view instructions first.
		System.out.println();
		
		if (start == 1) { // Start the game if 1 is entered.
			System.out.println("Great, let's go!");
		} else {
			if (start == 2) { // Display instructions if 2 is entered.
				System.out.println("Instructions:" + "\n \n" + "- The object of this game is to hit a target some distance away from you with a projectile launched from a catapult." + "\n" + "- In order to accomplish this, you will set an exit velocity and launch angle for the catapult." + "\n" + "- You begin the game with five credits and each launch costs one credit." + "\n" + "- There are three rounds of gameplay, each more difficult than the last." + "\n");
				System.out.print("Enter S to begin the game when you are ready: "); // Begin the game once the user enters 'S' (regardless of case).
				String start2 = in.next();
				
				if(start2.equalsIgnoreCase("S")){
					System.out.println("\n" + "Great, let's go!");
				} else {
					System.out.println("That's not an 'S'... but let's start anyway!");
				}
				
			} else {
				while (start != 1 && start != 2) { // Loop for users who input numbers other than 1 or 2.
					
					System.out.print("Misrecognition. Please enter '1' to begin the game, or enter '2' for game instructions: ");
					start = in.nextInt();
					System.out.println();
					
					if (start == 1) {
						System.out.println("Great, let's go!");
					} else {
						
						if (start == 2) {
							System.out.println("Instructions:" + "\n \n" + "- The object of this game is to hit a target some distance away from you with a projectile launched from a catapult." + "\n" + "- In order to accomplish this, you will set an exit velocity and launch angle for the catapult." + "\n" + "- You begin the game with five credits and each launch costs one credit." + "\n" + "- There are three rounds of gameplay, each more difficult than the last." + "\n");
							System.out.print("Enter S to begin the game when you are ready: ");
							String start3 = in.next();
							
							if(start3.equalsIgnoreCase("S")){ // Begin the game when the user enters 'S' (regardless of case).
								System.out.println("\n" + "Great, let's go!");
							} else {
								System.out.println("That's not an 'S'... but let's start anyway!");
							}
						}
					}
				}
			}
		}
		
		System.out.print("\n" + "Round 1... \n");
		
		int credits = 5; // Player starts with 5 credits. Game ends if player loses all credits.
		
		while(credits > 0 && credits < 10) { // Player gets a game over when they lose all of their credits and moves to round 2 when they have 10 or more credits.
			
			Random random = new Random(); // Creates a random variable for use when generating random distances.
			int d = random.nextInt(10) + 1; // Creates the variable d and assigns it a random integer from 1 to 10 (using random.nextInt(10) would include zero, which does not work for this game).
			
			System.out.print("\n" + "Target distance from player: " + d +"m" + "\n \n");
			System.out.println("You have " + credits + " credits remaining. Next launch will cost 1 credit. Do you wish to proceed?");
			System.out.print("Enter 'Y' or 'N': "); // Allows the user to quit after each shot.
			String play = in.next();
			System.out.println();
			
			if(play.equalsIgnoreCase("Y")){ // Continue the game if the user enters Y (regardless of case).
		
				credits = credits - 1; // Credit is taken immediately after the user selects to continue.
				System.out.println("Credits remaining: " + credits + "\n");
				System.out.print("Enter catapult speed for launch (m/s): ");
				int v = in.nextInt();
				System.out.print("\n" + "Enter launch angle (degrees): ");
				int o = in.nextInt();
				System.out.println();
				double result = (v*v*(2*Math.sin(Math.toRadians(o))*Math.cos(Math.toRadians(o))))/(9.8); // Stores the formula for distance of the projectile in a variable for easy access.
		
				if(result >= d-1 && result <= d+1) { // If the projectile lands within 1 meter of the target in either direction, award 5 credits.
					System.out.println("Direct hit!! The projectile traveled " + result +" meters.");
					credits = credits + 5;
					System.out.println("\n" + "+5 credits");
				
				} else{ 
					
					if(result >= d-3 && result <= d+3) { // If the projectile lands within 3 meters of the target in either direction, award 1 credit.
						System.out.println("Near miss! The projectile traveled " + result +" meters.");
						credits = credits + 1;
						System.out.println("\n" + "+1 credit");
						
					} else {
						
						if(result < d-3) { // If the projectile lands further than 3 meters from the target, award 0 credits.
							System.out.println("Fell short. The projectile traveled " + result +" meters.");
							
						} else{
							
							if(result > d+3) { // If the projectile lands further than 3 meters from the target, award 0 credits.
								System.out.println("Went long. The projectile traveled " + result +" meters.");
							}
							
						}
						
					}
					
				}
			
			} else {
				
				if(play.equalsIgnoreCase("N")){ 
					System.out.println("Thanks for playing!");
					System.exit(0); // Terminate the program if the player decides not to continue.
				}
				
			}
			
			if(credits == 0) { // The game ends when the user is out of credits.
				System.out.println("\n" + "Game over... You have used all of your available credits. Thanks for playing!");
				System.exit(0);
			}
		
		}
	
		System.out.println("\n" + "Congratulations! Round 2...");
		
		if(credits < 10){ // Checks to make sure the player has sufficient credits to begin round 2, otherwise the program detects a cheater and quits.
			System.out.println("\n" + "Cheater detected! Terminating program...");
			System.exit(0);
		}
		
		System.out.print("\n" + "Enter 1 to begin the round: ");
		
		start = in.nextInt();
		
		if(start == 1) {
			
			int bonus = 0; // Variable used for a bonus round.
			
			while(credits > 0 && credits < 25) { // Player gets a game over when they lose all of their credits and moves to round 3 when they have 25 or more credits.

				if(bonus == 2) { // Bonus round begins when the player has earned two bonus tokens.
					
					Random random = new Random(); // Creates a random variable for use when generating random distances.
					int d = random.nextInt(40) + 10; // Create the variable d and assigns it a random integer from 10 and 50.
					
					bonus = 0; // Bonus tokens reset to zero once bonus round begins.
					
					System.out.println("\n" + "Bonus round initiated! Hit the hidden target for a 10-credit reward. (This launch is free and does not use a credit.)" + "\n");
					
					System.out.print("Target distance from player: HIDDEN" +"\n \n");
					
					System.out.print("Enter catapult speed for launch (m/s): ");
					int v = in.nextInt();
					System.out.print("\n" + "Enter launch angle (degrees): ");
					int o = in.nextInt();
					System.out.println();
					double result = (v*v*(2*Math.sin(Math.toRadians(o))*Math.cos(Math.toRadians(o))))/(9.8);
					
					if(result >= d-5 && result <= d+5) {
						System.out.println("Target hit! You have successfully earned 10 bonus credits!" + "\n");
						System.out.println("The target was" + d + "m" + "away from you, and your projectile traveled" + result + "meters.");
						credits = credits + 10;
					} else {
						System.out.println("Sorry, target missed. Better luck next time!");
						System.out.println("\n" + "The target was " + d + "m" + " away from you, and your projectile traveled " + result + " meters.");
					}
				
				}
					
				Random random = new Random(); // Creates a random variable for use when generating random distances.
				int d = random.nextInt(40) + 10; // Create the variable d and assigns it a random integer from 10 and 50.
			
				System.out.print("\n" + "Target distance from player: " + d +"m" + "\n \n");
				System.out.println("You have " + credits + " credits remaining. Next launch will cost 1 credit. Do you wish to proceed?");
				System.out.print("Enter 'Y' or 'N': "); // Allows the user to quit after each shot.
				String play = in.next();
				System.out.println();
			
				if(play.equalsIgnoreCase("Y")){ // Continue the game if the user enters Y (regardless of case).
					
					credits = credits - 1; // Credit is taken immediately after the user selects to continue.
					System.out.println("Credits remaining: " + credits + "\n");
					System.out.print("Enter catapult speed for launch (m/s): ");
					int v = in.nextInt();
					System.out.print("\n" + "Enter launch angle (degrees): ");
					int o = in.nextInt();
					System.out.println();
					double result = (v*v*(2*Math.sin(Math.toRadians(o))*Math.cos(Math.toRadians(o))))/(9.8); // Stores the formula for distance of the projectile in a variable for easy access.
		
					if(result >= d-1 && result <= d+1) { // Award five credits and one bonus token to the player if the projectile lands within 1m of the target in either direction.
						
						System.out.println("Direct hit!! The projectile traveled " + result +" meters.");
						credits = credits + 5;
						System.out.println("\n" + "+5 credits");
						bonus = bonus + 1;
						System.out.println("\n" + "+1 bonus token!");
						
					} else {
						
						if(result >= d-2 && result <= d+2) { // If the projectile lands within 2 meters of the target in either direction, award 5 credits.
							System.out.println("Hit the target! The projectile traveled " + result +" meters.");
							credits = credits + 5;
							System.out.println("\n" + "+5 credits");
							
						} else{ 
					
							if(result >= d-4 && result <= d+4) { // If the projectile lands within 4 meters of the target in either direction, award 1 credit.
								System.out.println("Near miss! The projectile traveled " + result +" meters.");
								credits = credits + 1;
								System.out.println("\n" + "+1 credit");
						
							} else {
						
								if(result < d-4) { // If the projectile lands further than 4 meters from the target, award 0 credits.
									System.out.println("Fell short. The projectile traveled " + result +" meters.");
									
								} else{
							
									if(result > d+4) { // If the projectile lands further than 4 meters from the target, award 0 credits.
										System.out.println("Went long. The projectile traveled " + result +" meters.");
									}
							
								}
						
							}
					
						}
						
					}
			
				} else {
				
					if(play.equalsIgnoreCase("N")){ 
						System.out.println("Thanks for playing!");
						System.exit(0);
					}
				
				}
			
				if(credits == 0) { // The game ends when the user is out of credits.
					System.out.println("\n" + "Game over... You have used all of your available credits. Thanks for playing!");
					System.exit(0);
			}
		
		}
		
		}
		
		System.out.println("\n" + "Congratulations! Final round...");

		if(credits < 25){ // Checks to make sure the player has sufficient credits to begin round 3, otherwise the program detects a cheater and quits.
			System.out.println("\n" + "Cheater detected! Terminating program...");
			System.exit(0);
		}
		
		System.out.print("\n" + "Enter 1 to begin the round: ");
		
		start = in.nextInt();
		
		if(start == 1) {
			
			int bonus = 0; // Variable used for a bonus round.
			
			while(credits > 0 && credits < 50) { // Player gets a game over when they lose all of their credits and completes the game when they have 50 or more credits.

				if(bonus == 2) { // Initiate a bonus round if the player has two bonus tokens.
					
					int bonusLaunch = 2; // Sets the framework for giving the player two bonus shots per bonus round.
					Random random = new Random(); // Creates a random variable for use when generating random distances.
					int d = random.nextInt(150) + 50; // Creates the variable d and assigns it a random integer from 50 and 200.
					
					System.out.println("\n" + "Bonus round initiated! Hit the hidden target for a 15-credit reward and 1 additional bonus token. (This launch is free and does not use a credit.)" + "\n");
					
					while(bonus > 0) { // Continues the framework set forth to give two shots per bonus round.
					
						bonus = bonus - 1;
						System.out.println("You have " + bonusLaunch + " bonus launches remaining." + "\n");
						bonusLaunch = bonusLaunch - 1;
						System.out.print("Target distance from player: HIDDEN" +"\n \n");
					
						System.out.print("Enter catapult speed for launch (m/s): ");
						int v = in.nextInt();
						System.out.print("\n" + "Enter launch angle (degrees): ");
						int o = in.nextInt();
						System.out.println();
						double result = (v*v*(2*Math.sin(Math.toRadians(o))*Math.cos(Math.toRadians(o))))/(9.8);
					
						if(result >= d-10 && result <= d+10) { // Award the player 15 credits and an additional bonus token if the projectile lands within 10m of the hidden target.
							System.out.println("Target hit! You have successfully earned 15 credits and an additional bonus token!" + "\n");
							System.out.println("The target was" + d + "m" + "away from you, and your projectile traveled" + result + "meters.");
							credits = credits + 15;
							bonus = bonus + 1;
						} else {
							System.out.println("Sorry, target missed. Better luck next time!");
							System.out.println("\n" + "The target was " + d + "m" + " away from you, and your projectile traveled " + result + " meters." + "\n");
						}
					
					}
				
				}
					
				Random random = new Random(); // Creates a random variable for use when generating random distances.
				int d = random.nextInt(150) + 50; // Create the variable d and assigns it a random integer from 50 and 200.
			
				System.out.print("\n" + "Target distance from player: " + d +"m" + "\n \n");
				System.out.println("You have " + credits + " credits remaining. Next launch will cost 2 credits. Do you wish to proceed?");
				System.out.print("Enter 'Y' or 'N': "); // Allows the user to quit after each shot.
				String play = in.next();
				System.out.println();
			
				if(play.equalsIgnoreCase("Y")){ // Continue the game if the user enters Y (regardless of case).
					
					credits = credits - 2; // Credit is taken immediately after the user selects to continue.
					System.out.println("Credits remaining: " + credits + "\n");
					System.out.print("Enter catapult speed for launch (m/s): ");
					int v = in.nextInt();
					System.out.print("\n" + "Enter launch angle (degrees): ");
					int o = in.nextInt();
					System.out.println();
					double result = (v*v*(2*Math.sin(Math.toRadians(o))*Math.cos(Math.toRadians(o))))/(9.8); // Stores the formula for distance of the projectile in a variable for easy access.
		
					if(result >= d-3 && result <= d+3) {
						
						System.out.println("Direct hit!! The projectile traveled " + result +" meters.");
						credits = credits + 7;
						System.out.println("\n" + "+7 credits");
						bonus = bonus + 1;
						System.out.println("\n" + "+1 bonus token!");
						
					} else {
						
						if(result >= d-4 && result <= d+4) { // If the projectile lands within 4 meters of the target in either direction, award 5 credits.
							System.out.println("Hit the target! The projectile traveled " + result +" meters.");
							credits = credits + 5;
							System.out.println("\n" + "+5 credits");
							
						} else{ 
					
							if(result >= d-5 && result <= d+5) { // If the projectile lands within 5 meters of the target in either direction, award 1 credit.
								System.out.println("Near miss! The projectile traveled " + result +" meters.");
								credits = credits + 1;
								System.out.println("\n" + "+1 credit");
						
							} else {
						
								if(result < d-5) { // If the projectile lands further than 5 meters from the target, award 0 credits.
									System.out.println("Fell short. The projectile traveled " + result +" meters.");
									
								} else{
							
									if(result > d+5) { // If the projectile lands further than 5 meters from the target, award 0 credits.
										System.out.println("Went long. The projectile traveled " + result +" meters.");
									}
							
								}
						
							}
					
						}
						
					}
			
				} else {
				
					if(play.equalsIgnoreCase("N")){ 
						System.out.println("Thanks for playing!");
						break;
					}
				
				}
			
				if(credits == 0) { // The game ends when the user is out of credits.
					System.out.println("\n" + "Game over... You have used all of your available credits. Thanks for playing!");
			}
		
		}
		
		}
		
		in.close();
		
	}

}
