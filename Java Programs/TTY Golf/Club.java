/* James Fazio
 * Project 2
 * Lab: Mon/Wed 12:30 – 1:45
 * Lab TA: Sofia Carrillo
 * 
 * I did not collaborate with anyone on this assignment.
 */

import java.util.Scanner;
import java.util.Random;

public class Club {
	
	protected String name;
	protected int mean;
	protected int stddev;
	protected int power;
	protected Random random = new Random();
	protected int distance;
	
	public Club (String name, int mean, int stddev) {
		this.name = name;
		this.mean = mean;
		this.stddev = stddev;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setMean(int mean) {
		this.mean = mean;
	}
	
	public int getMean() {
		return mean;
	}
	
	public void setStddev(int stddev) {
		this.stddev = stddev;
	}
	
	public int getStddev() {
		return stddev;
	}
	
	public void setPower() {
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter your desired power: ");
		int userPower = in.nextInt();
		System.out.println();
		
		if(userPower > 0 && userPower <= 10) {
			power = userPower;
		} else {
			System.out.println("You swung and missed!" + "\n");
			
			while(userPower < 0 || userPower > 10) {
				System.out.print("Please enter your desired power: ");
				userPower = in.nextInt();
				System.out.println()
				;
				if(userPower > 0 && userPower <= 10) {
					power = userPower;
				} else {
					System.out.println("You swung and missed!" + "\n");
				}
				
			}
		}

	}
	
	public int getPower() {
		return power;
	}
	
	public void setDistance() { // Majority of this code taken directly from the project prompt, with a few alterations.
		if(power != 0) { 
			double mean_calc = mean * power / 10.0;
			double stddev_calc = stddev * power / 10.0;
			int distance;
			do {
				double value = (random.nextGaussian() * stddev_calc) + mean_calc;
				distance = (int)value;
			} while (distance < 0);
			this.distance = distance;
		}
	}
	
	public int getDistance() {
		return distance;
	}

}
