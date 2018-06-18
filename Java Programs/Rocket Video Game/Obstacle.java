import java.awt.*;
import java.awt.geom.Line2D;
import java.util.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Obstacle {
	
	protected static int obstacleX = 0, obstacleY = 0, obstacleMotionX = 0, obstacleMotionY = 0, obstacleMotionYY = 0;
	protected int wallX, wallY, wallX2, wallY2, imageX, imageY, coinX, coinY;
	protected String fileLocation;
	protected static int modX, modX2;
	protected static int modY;
	protected static int size, fire;
	protected int imageWidth, imageHeight;
	protected Random random;
	protected int randomX, randomY, randomSize;
	protected boolean imagePresent = true, coinPresent = true;
	
	public Obstacle() {
		
	}
	
	public Obstacle(int wallX, int wallY, int wallX2, int wallY2) {
		this.wallX = wallX;
		this.wallY = wallY;
		this.wallX2 = wallX2;
		this.wallY2 = wallY2;
	}
	
	public Obstacle(String fileLocation, int imageX, int imageY) {
		this.fileLocation = fileLocation;
		this.imageX = imageX;
		this.imageY = imageY;
	}
	
	public Obstacle(int coinX, int coinY) {
		this.coinX = coinX;
		this.coinY = coinY;
	}
	
	public int numberGenerator(int cap) {
		fire = random.nextInt(cap);
		return fire;
	}
	
	protected Image loadImage(String fileLocation) { // Code partially sourced from Lecture 20 sample code; slightly modified
		try {
			return ImageIO.read(new File(fileLocation));
		} catch (IOException a) {
			System.err.println(a);
			return null;
		}
	}
	
	public void drawFireball(Graphics g) {
		Image obstacle = loadImage(fileLocation);
		imageWidth = obstacle.getWidth(null);
		imageHeight = obstacle.getHeight(null);
		g.drawImage(obstacle, obstacleX + imageX - imageWidth/2, imageY - imageHeight/2, null);
	}
	
	public void drawImage(Graphics g) {
		Image obstacle = loadImage(fileLocation);
		imageWidth = obstacle.getWidth(null);
		imageHeight = obstacle.getHeight(null);
		
		if(imagePresent) {
			g.drawImage(obstacle, obstacleX + imageX - imageWidth/2, obstacleMotionY + imageY - imageHeight/2, null);
		}
	}
	
	public void drawBowser(Graphics g) {
		Image obstacle = loadImage(fileLocation);
		imageWidth = obstacle.getWidth(null);
		imageHeight = obstacle.getHeight(null);
		
		if(imagePresent) {
			g.drawImage(obstacle, imageX - imageWidth/2, obstacleMotionYY + imageY - imageHeight/2, null);
		}
	}
	
	public void drawCoin(Graphics g) {
		if(coinPresent) {
			g.setColor(Color.yellow);
			g.fillOval(obstacleX + coinX, coinY, 50, 50);
			g.setColor(Color.gray);
			g.fillOval(obstacleX + coinX + 18, coinY + 18, 15, 15);
			g.setColor(Color.black);
		}
	}
	
	public void drawObstacle(Graphics g) {
		modX = obstacleX + randomX;
		modY = obstacleY + randomY;
		size = randomSize;
		g.fillRect(modX, modY, size, size);
	}
	
	public void drawWall(Graphics g) {
		
		g.drawLine(obstacleX + wallX, wallY, obstacleX + wallX2, wallY2);
		
	}
	
	public void drawDoor(Graphics g) {
		
		g.drawLine(obstacleX + wallX, wallY, obstacleX + wallX2, obstacleY + wallY2);
		
	}
	
	public void drawButton(Graphics g) {
		
		g.drawLine(obstacleX + wallX, wallY, obstacleX + wallX2, wallY2);
		g.drawLine(obstacleX + wallX - 1, wallY, obstacleX + wallX2 - 1, wallY2);
		g.drawLine(obstacleX + wallX - 2, wallY, obstacleX + wallX2 - 2, wallY2);
		g.drawLine(obstacleX + wallX - 3, wallY, obstacleX + wallX2 - 3, wallY2);
		g.drawLine(obstacleX + wallX - 4, wallY, obstacleX + wallX2 - 4, wallY2);
		g.drawLine(obstacleX + wallX - 5, wallY, obstacleX + wallX2 - 5, wallY2);
		
	}
	
	public Rectangle getRectImage() {
		return new Rectangle(obstacleX + imageX - imageWidth/2, obstacleMotionY + imageY - imageHeight/2, imageWidth, imageHeight);
	}
	
	public Rectangle getRectBowser() {
		return new Rectangle(imageX - imageWidth/2, obstacleMotionYY + imageY - imageHeight/2, imageWidth, imageHeight);
	}
	
	public Rectangle getRectButton() {
		return new Rectangle(obstacleX + wallX - 6, wallY, 2, 80);
	}
	
	public Rectangle getRectCoin() {
		return new Rectangle(obstacleX + coinX, coinY, 50, 50);
	}
	
	public Line2D getLine() {
		return new Line2D.Float(obstacleX + wallX, wallY, obstacleX + wallX2, wallY2);
	}
	
	public Line2D getLine2() {
		return new Line2D.Float(wallX, wallY, wallX2, wallY2);
	}
	
}
