import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Rocket extends JPanel {

	protected static int rocketX = 60, rocketY = 400;
	protected static Rectangle rect;
	protected static int imageWidth, imageHeight;
	
	public Rocket() {
		
	}

	protected Image loadImage(String fileLocation) { // Code partially sourced from Lecture 20 sample code; slightly modified
		try {
			return ImageIO.read(new File(fileLocation));
		} catch (IOException a) {
			System.err.println(a);
			return null;
		}
	}
	
	public void drawRocket(Graphics g) {

		if(Interface.droneColor == 1 || Interface.droneColor == 0) {
			
			Image rocket = loadImage("images/rocket.png");
			imageWidth = rocket.getWidth(null);
			imageHeight = rocket.getHeight(null);
			
			g.drawImage(rocket, rocketX - imageWidth/2, rocketY - imageHeight/2, null);
		}

		if(Interface.droneColor == 2) {
			Image rocket = loadImage("images/rocket_red.png");
			imageWidth = rocket.getWidth(null);
			imageHeight = rocket.getHeight(null);
			g.drawImage(rocket, rocketX - imageWidth/2, rocketY - imageHeight/2, null);
		}

		if(Interface.droneColor == 3) {
			Image rocket = loadImage("images/rocket_blue.png");
			imageWidth = rocket.getWidth(null);
			imageHeight = rocket.getHeight(null);
			g.drawImage(rocket, rocketX - imageWidth/2, rocketY - imageHeight/2, null);
		}

	}
	
	public void drawRocketUp(Graphics g) {

		if(Interface.droneColor == 1 || Interface.droneColor == 0) {
			Image rocket = loadImage("images/rocket_up.png");
			imageWidth = rocket.getWidth(null);
			imageHeight = rocket.getHeight(null);
			g.drawImage(rocket, rocketX - imageWidth/2, rocketY - imageHeight/2, null);
		}

		if(Interface.droneColor == 2) {
			Image rocket = loadImage("images/rocket_red_up.png");
			imageWidth = rocket.getWidth(null);
			imageHeight = rocket.getHeight(null);
			g.drawImage(rocket, rocketX - imageWidth/2 + 1, rocketY - imageHeight/2, null);
		}

		if(Interface.droneColor == 3) {
			Image rocket = loadImage("images/rocket_blue_up.png");
			imageWidth = rocket.getWidth(null);
			imageHeight = rocket.getHeight(null);
			g.drawImage(rocket, rocketX - imageWidth/2, rocketY - imageHeight/2, null);
		}

	}

	public void drawRocketDown(Graphics g) {

		if(Interface.droneColor == 1 || Interface.droneColor == 0) {
			Image rocket = loadImage("images/rocket_down.png");
			imageWidth = rocket.getWidth(null);
			imageHeight = rocket.getHeight(null);
			g.drawImage(rocket, rocketX - imageWidth/2, rocketY - imageHeight/2, null);
		}

		if(Interface.droneColor == 2) {
			Image rocket = loadImage("images/rocket_red_down.png");
			imageWidth = rocket.getWidth(null);
			imageHeight = rocket.getHeight(null);
			g.drawImage(rocket, rocketX - imageWidth/2 - 2, rocketY - imageHeight/2, null);
		}

		if(Interface.droneColor == 3) {
			Image rocket = loadImage("images/rocket_blue_down.png");
			imageWidth = rocket.getWidth(null);
			imageHeight = rocket.getHeight(null);
			g.drawImage(rocket, rocketX - imageWidth/2, rocketY - imageHeight/2, null);
		}
		
	}

	public void drawRocketLeft(Graphics g) {

		if(Interface.droneColor == 1 || Interface.droneColor == 0) {
			Image rocket = loadImage("images/rocket_l.png");
			imageWidth = rocket.getWidth(null);
			imageHeight = rocket.getHeight(null);
			g.drawImage(rocket, rocketX - imageWidth/2, rocketY - imageHeight/2, null);
		}

		if(Interface.droneColor == 2) {
			Image rocket = loadImage("images/rocket_red_l.png");
			imageWidth = rocket.getWidth(null);
			imageHeight = rocket.getHeight(null);
			g.drawImage(rocket, rocketX - imageWidth/2, rocketY - imageHeight/2, null);
		}

		if(Interface.droneColor == 3) {
			Image rocket = loadImage("images/rocket_blue_l.png");
			imageWidth = rocket.getWidth(null);
			imageHeight = rocket.getHeight(null);
			g.drawImage(rocket, rocketX - imageWidth/2, rocketY - imageHeight/2, null);
		}

	}

	public void drawRocketDL(Graphics g) {

		if(Interface.droneColor == 1 || Interface.droneColor == 0) {
			Image rocket = loadImage("images/rocket_dl.png");
			imageWidth = rocket.getWidth(null);
			imageHeight = rocket.getHeight(null);
			g.drawImage(rocket, rocketX - imageWidth/2, rocketY - imageHeight/2, null);
		}

		if(Interface.droneColor == 2) {
			Image rocket = loadImage("images/rocket_red_dl.png");
			imageWidth = rocket.getWidth(null);
			imageHeight = rocket.getHeight(null);
			g.drawImage(rocket, rocketX - imageWidth/2, rocketY - imageHeight/2, null);
		}

		if(Interface.droneColor == 3) {
			Image rocket = loadImage("images/rocket_blue_l.png");
			imageWidth = rocket.getWidth(null);
			imageHeight = rocket.getHeight(null);
			g.drawImage(rocket, rocketX - imageWidth/2, rocketY - imageHeight/2, null);
		}

	}

	public void drawRocketUL(Graphics g) {

		if(Interface.droneColor == 1 || Interface.droneColor == 0) {
			Image rocket = loadImage("images/rocket_up_l.png");
			imageWidth = rocket.getWidth(null);
			imageHeight = rocket.getHeight(null);
			g.drawImage(rocket, rocketX - imageWidth/2, rocketY - imageHeight/2, null);
		}

		if(Interface.droneColor == 2) {
			Image rocket = loadImage("images/rocket_red_ul.png");
			imageWidth = rocket.getWidth(null);
			imageHeight = rocket.getHeight(null);
			g.drawImage(rocket, rocketX - imageWidth/2, rocketY - imageHeight/2, null);
		}

		if(Interface.droneColor == 3) {
			Image rocket = loadImage("images/rocket_blue_l.png");
			imageWidth = rocket.getWidth(null);
			imageHeight = rocket.getHeight(null);
			g.drawImage(rocket, rocketX - imageWidth/2, rocketY - imageHeight/2, null);
		}

	}
	
	public void drawRocketUR(Graphics g) {

		if(Interface.droneColor == 1 || Interface.droneColor == 0) {
			Image rocket = loadImage("images/rocket_up_r.png");
			imageWidth = rocket.getWidth(null);
			imageHeight = rocket.getHeight(null);
			g.drawImage(rocket, rocketX - imageWidth/2, rocketY - imageHeight/2, null);
		}

		if(Interface.droneColor == 2) {
			Image rocket = loadImage("images/rocket_red_up_r.png");
			imageWidth = rocket.getWidth(null);
			imageHeight = rocket.getHeight(null);
			g.drawImage(rocket, rocketX - imageWidth/2, rocketY - imageHeight/2, null);
		}

		if(Interface.droneColor == 3) {
			Image rocket = loadImage("images/rocket_blue_l.png");
			imageWidth = rocket.getWidth(null);
			imageHeight = rocket.getHeight(null);
			g.drawImage(rocket, rocketX - imageWidth/2, rocketY - imageHeight/2, null);
		}

	}

	public void drawRocketDR(Graphics g) {

		if(Interface.droneColor == 1 || Interface.droneColor == 0) {
			Image rocket = loadImage("images/rocket_dr.png");
			imageWidth = rocket.getWidth(null);
			imageHeight = rocket.getHeight(null);
			g.drawImage(rocket, rocketX - imageWidth/2, rocketY - imageHeight/2, null);
		}

		if(Interface.droneColor == 2) {
			Image rocket = loadImage("images/rocket_red_dr.png");
			imageWidth = rocket.getWidth(null);
			imageHeight = rocket.getHeight(null);
			g.drawImage(rocket, rocketX - imageWidth/2, rocketY - imageHeight/2, null);
		}

		if(Interface.droneColor == 3) {
			Image rocket = loadImage("images/rocket_blue_l.png");
			imageWidth = rocket.getWidth(null);
			imageHeight = rocket.getHeight(null);
			g.drawImage(rocket, rocketX - imageWidth/2, rocketY - imageHeight/2, null);
		}

	}
	
	public void drawFlame(Graphics g) {

			Image flame = loadImage("images/flame.png");
			imageWidth = flame.getWidth(null);
			imageHeight = flame.getHeight(null);
			g.drawImage(flame, rocketX - imageWidth/2 - imageWidth - 5, rocketY - imageHeight/2, null);

	}

	public void drawFlameUp(Graphics g) {

			Image flame = loadImage("images/flame_up.png");
			imageWidth = flame.getWidth(null);
			imageHeight = flame.getHeight(null);
			g.drawImage(flame, rocketX - imageWidth/2, rocketY + imageHeight/2 + 7, null);
		
	}

	public void drawFlameDown(Graphics g) {

			Image flame = loadImage("images/flame_down.png");
			imageWidth = flame.getWidth(null);
			imageHeight = flame.getHeight(null);
			g.drawImage(flame, rocketX - imageWidth/2, rocketY - imageHeight*2 + 27, null);

	}

	public void drawFlameLeft(Graphics g) {

		Image flame = loadImage("images/flame_l.png");
		imageWidth = flame.getWidth(null);
		imageHeight = flame.getHeight(null);
		g.drawImage(flame, rocketX + imageWidth/2 + 7, rocketY - imageHeight/2, null);

	}
	
	public void drawFlameUL(Graphics g) {

		Image flame = loadImage("images/flame_ul.png");
		imageWidth = flame.getWidth(null);
		imageHeight = flame.getHeight(null);
		g.drawImage(flame, rocketX + 17, rocketY + 16, null);

	}
	
	public void drawFlameUR(Graphics g) {

		Image flame = loadImage("images/flame_ur.png");
		imageWidth = flame.getWidth(null);
		imageHeight = flame.getHeight(null);
		g.drawImage(flame, rocketX - imageWidth - 16, rocketY + 15, null);

	}

	public void drawFlameDR(Graphics g) {

		Image flame = loadImage("images/flame_dr.png");
		imageWidth = flame.getWidth(null);
		imageHeight = flame.getHeight(null);
		g.drawImage(flame, rocketX - imageWidth - 16, rocketY - imageHeight - 15, null);

	}

	public void drawFlameDL(Graphics g) {

		Image flame = loadImage("images/flame_dl.png");
		imageWidth = flame.getWidth(null);
		imageHeight = flame.getHeight(null);
		g.drawImage(flame, rocketX + imageWidth/2 - 21, rocketY - imageHeight - 14, null);

	}
	
	public static Rectangle getRect() {
		return new Rectangle(rocketX - imageWidth/2, rocketY - imageHeight/2, imageWidth, imageHeight);
	}
	
}
