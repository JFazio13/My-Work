import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;

public class Projectile {

	protected static int missileX = Rocket.rocketX, missileY = Rocket.rocketY;
	
	public Projectile() {
		
	}
	
	public static void rocketMissile(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(missileX, missileY + 7, 20, 10);
		g.setColor(Color.black);
	}
	
	public static Rectangle getRect() {
		return new Rectangle(missileX, missileY + 7, 20, 10);
	}
	
}
