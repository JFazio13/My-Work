/* James Fazio
 * Project 2
 * Lab: Mon/Wed 12:30 – 1:45
 * Lab TA: Sofia Carrillo
 * 
 * I did not collaborate with anyone on this assignment.
 */

import java.util.Scanner;
import java.util.Random;

public class Putter {
	
	protected int power;
	protected int mean;
	protected int stddev;
	protected Random random = new Random();
	protected int distance;

	public Putter (int power, int mean, int stddev) {
		this.power = power;
		this.mean = mean;
		this.stddev = stddev;
	}
	
	public void setPower(int power){
		this.power = power;
	}
	
	public int getPower() {
		return power;
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
	
	public void setDistance() { // Majority of this code taken directly from the project prompt, with a few alterations.
		
		if(power != 0) {
			double mean_calc = (mean * power / 10.0) * 3; // Multiplying by 3 to output in feet instead of yards.
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
