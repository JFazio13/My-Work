import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import java.awt.geom.Line2D;

public class Canvas extends JPanel {

	protected Timer timer, timer2;
	protected int horizMotion = 0;
	protected int vertMotion = 0, gauge = 1300;
	protected int lives = 3, m = 0, m2 = 0, coins = 0;
	protected boolean flameFired = false, rocketUp = false, rocketDown = false, rocketLeft = false, leftPressed = false, upPressed = false;
	protected boolean rightPressed = false, downPressed = false, timerStopped = false, obstaclePresent = true, timer2Ran = false;
	protected boolean rocketMissile = false, invincibilityFrames = false, doorPresent = true, targetHit = false, doorHitBoxPresent = true;
	protected boolean motionUp = true, nextLevel = false, motionUp2 = true, bowserHit = false, missilePresent = false, missileError = false;
	protected int level = 1, bowserLife = 500;
	
	public Canvas() {

		JFrame frame = new JFrame("Canvas2");
		frame.setSize(1440, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.addKeyListener(new KeyHandler());
		
		timer = new Timer(16, new TimerHandler());
		timer.start();
		
		timer2 = new Timer(2000, new TimerHandler2());
		
		frame.setVisible(true);
	}

	protected class TimerHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			Rocket.rocketX += horizMotion;
			Rocket.rocketY += 4;
			Obstacle.obstacleX -= 10;
			
			if(m % 100 == 0) {
				if(motionUp) {
					motionUp = false;
				} else if(!motionUp) {
					motionUp = true;
				}

			}
			if(motionUp) {
				Obstacle.obstacleMotionY -= 1;
			}
			if(!motionUp) {
				Obstacle.obstacleMotionY += 1;
			}
			
			if(m2 % 80 == 0) {
				if(motionUp2) {
					motionUp2 = false;
				} else if(!motionUp2) {
					motionUp2 = true;
				}

			}
			if(motionUp2) {
				Obstacle.obstacleMotionYY += 5;
			}
			if(!motionUp2) {
				Obstacle.obstacleMotionYY -= 5;
			}
			
			if(targetHit) {
				if(Obstacle.obstacleY != -250) {
					Obstacle.obstacleY -= 5;
				}
				doorHitBoxPresent = false;
			}
			
			if(rocketMissile) {
				
				missilePresent = true;
				Projectile.missileX += 30;
				if(Projectile.missileX >= 1440) {
					rocketMissile = false;
					missilePresent = false;
				}
				
			}
			
			if(Rocket.rocketY > 0 + 26) { // 26 is roughly half of the rocket image height.
				Rocket.rocketY += vertMotion;
			}
			
			repaint();
			
			m++;
			m2++;
		}
	}
	
	protected class TimerHandler2 implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			obstaclePresent = true;
			invincibilityFrames = false;
			timer2Ran = true;
		}
	}
	
	protected class KeyHandler implements KeyListener { // Inspired by Lecture 20 sample code
		
		public void keyPressed(KeyEvent k) {
			
			switch (k.getKeyCode()) {
			
				case KeyEvent.VK_RIGHT:
					horizMotion = 10;
					flameFired = true;
					rocketUp = false;
					rocketDown = false;
					rocketLeft = false;
					rightPressed = true;
					break;
				case KeyEvent.VK_LEFT:
					horizMotion = -10;
					flameFired = true;
					rocketUp = false;
					rocketDown = false;
					rocketLeft = true;
					leftPressed = true;
					break;
				case KeyEvent.VK_UP:
					vertMotion = -14;
					flameFired = true;
					rocketUp = true;
					rocketDown = false;
					rocketLeft = false;
					upPressed = true;
					break;
				case KeyEvent.VK_DOWN:
					vertMotion = 8;
					rocketUp = false;
					rocketDown = true;
					rocketLeft = false;
					downPressed = true;
					break;
			}
			
			if(k.getKeyCode() == KeyEvent.VK_SPACE) {
				if(!missilePresent)	{
					rocketMissile = true;
					Projectile.missileX = Rocket.rocketX;
					Projectile.missileY = Rocket.rocketY;
				} else {
					missileError = true;
				}
			}
			
			if(timerStopped && lives > 0) {
				if(k.getKeyCode() == KeyEvent.VK_ENTER) {
					invincibilityFrames = true;
					timer.start();
					
					if(nextLevel) {
						Obstacle.obstacleX = 0;
						Rocket.rocketX = 150;
						level = 2;
					}
					
				}
			}
	
		}
		
		public void keyReleased(KeyEvent k) {
			
			switch (k.getKeyCode()) {
			
				case KeyEvent.VK_RIGHT:
					horizMotion = 0;
					flameFired = false;
					rightPressed = false;
					break;
				case KeyEvent.VK_LEFT:
					horizMotion = 0;
					flameFired = false;
					rocketLeft = false;
					leftPressed = false;
					break;
				case KeyEvent.VK_UP:
					vertMotion = 0;
					flameFired = false;
					rocketUp = false;
					upPressed = false;
					break;
				case KeyEvent.VK_DOWN:
					vertMotion = 0;
					flameFired = false;
					rocketDown = false;
					downPressed = false;
					break;
			}
			
		}
		
		public void keyTyped(KeyEvent k) {
			
		}
		
	}
	
	Obstacle w1 = new Obstacle(0, 100, 2000, 100);
	Obstacle w2 = new Obstacle(0, 700, 2000, 700);
	
	Obstacle w3 = new Obstacle(2000, 100, 2500, 250);
	Obstacle w4 = new Obstacle(2000, 700, 2500, 550);
	
	Obstacle w5 = new Obstacle(2500, 250, 4000, 250);
	Obstacle w6 = new Obstacle(2500, 550, 4000, 550);
	
	Obstacle w7 = new Obstacle(4000, 250, 7000, 50);
	Obstacle w8 = new Obstacle(4000, 550, 7000, 750);
	
	Obstacle w9 = new Obstacle(7000, 50, 10000, 50);
	Obstacle w10 = new Obstacle(7000, 750, 10000, 750);
	
	Obstacle w11 = new Obstacle(10000, 50, 10000, 275);
	Obstacle w12 = new Obstacle(10000, 750, 10000, 525);
	
	Obstacle w13 = new Obstacle(10000, 275, 11000, 275);
	Obstacle w14 = new Obstacle(10000, 525, 11000, 525);
	
	Obstacle w15 = new Obstacle(11000, 275, 12000, 25);
	Obstacle w16 = new Obstacle(11000, 525, 12000, 275);
	
	Obstacle w17 = new Obstacle(12000, 25, 12500, 25);
	Obstacle w18 = new Obstacle(12000, 275, 12500, 275);

	Obstacle w19 = new Obstacle(12500, 25, 13200, 500);
	Obstacle w20 = new Obstacle(12500, 275, 13200, 750);
	
	Obstacle w21 = new Obstacle(13200, 500, 14000, 50);
	Obstacle w22 = new Obstacle(13200, 750, 14000, 750);
	
	Obstacle w23 = new Obstacle(14000, 50, 25000, 325);
	Obstacle w24 = new Obstacle(14000, 750, 25000, 475);
	
	Obstacle door = new Obstacle(10000, 275, 10000, 525);
	Obstacle door2 = new Obstacle(10001, 275, 10001, 525);
	Obstacle door3 = new Obstacle(10002, 275, 10002, 525);
	
	Obstacle button = new Obstacle(9999, 120, 9999, 200);
	Obstacle finish = new Obstacle(25000, 325, 25000,475);
	
	Obstacle koopa = new Obstacle("images/redkoopa.png", 1000, 300);
	Obstacle goomba = new Obstacle("images/goomba.png", 2500, 350);
	Obstacle beet = new Obstacle("images/beetle.png", 6000, 250);
	Obstacle bro = new Obstacle("images/hammerbro.png", 7000, 250);
	Obstacle goomba2 = new Obstacle("images/goomba.png", 15000, 450);
	Obstacle koopa3 = new Obstacle("images/greenkoopa.png", 17000, 370);
	Obstacle bill = new Obstacle("images/bulletbill.png", 20000, 350);
	
	Obstacle bowser = new Obstacle("images/bowser.png", 1270, 600);
	Obstacle fireball = new Obstacle("images/fireball.png", 1200, 600);
	
	Obstacle l2Line = new Obstacle(45, 30, 45, 750);
	Obstacle l2Line2 = new Obstacle(45, 30, 1390, 30);
	Obstacle l2Line3 = new Obstacle(45, 750, 1390, 750);
	Obstacle l2Line4 = new Obstacle(1390, 30, 1390, 750);
	
	Obstacle coin = new Obstacle(400, 400);
	Obstacle coin2 = new Obstacle(460, 400);
	Obstacle coin3= new Obstacle(400, 460);
	Obstacle coin4 = new Obstacle(460, 460);
	Obstacle coin5 = new Obstacle(3500, 300);
	Obstacle coin6 = new Obstacle(3560, 300);
	Obstacle coin7 = new Obstacle(3620, 300);
	Obstacle coin8 = new Obstacle(3500, 360);
	Obstacle coin9 = new Obstacle(3560, 360);
	Obstacle coin10 = new Obstacle(3620, 360);
	Obstacle coin11 = new Obstacle(7200, 600);
	Obstacle coin12 = new Obstacle(7260, 600);
	Obstacle coin13 = new Obstacle(7200, 660);
	Obstacle coin14 = new Obstacle(7260, 660);
	Obstacle coin15 = new Obstacle(12150, 100);
	Obstacle coin16 = new Obstacle(12210, 100);
	Obstacle coin17 = new Obstacle(12270, 100);
	Obstacle coin18 = new Obstacle(12150, 160);
	Obstacle coin19 = new Obstacle(12210, 160);
	Obstacle coin20 = new Obstacle(12270, 160);
	Obstacle coin21 = new Obstacle(16000, 350);
	Obstacle coin22 = new Obstacle(16060, 350);
	Obstacle coin23 = new Obstacle(16120, 350);
	Obstacle coin24 = new Obstacle(18500, 400);
	Obstacle coin25 = new Obstacle(18560, 400);
	Obstacle coin26 = new Obstacle(18620, 400);
	
	public void checkHit(Obstacle o, Graphics g) {
		if(Rocket.getRect().intersectsLine(o.getLine())) {

			lives--;
			timer.stop();
			
			if(lives > 0) {
				Rocket.rocketX = 60;
				Rocket.rocketY = 400;
				timerStopped = true;
				g.drawString("Press enter to continue...", 650, 40);
			} else {
				System.out.println("Game over!");
			}
			
		}
	}
	
	public void checkHit2(Obstacle o, Graphics g) {
		if(Rocket.getRect().intersectsLine(o.getLine2())) {

			lives--;
			timer.stop();
			
			if(lives > 0) {
				Rocket.rocketX = 60;
				Rocket.rocketY = 400;
				timerStopped = true;
				g.drawString("Press enter to continue...", 650, 40);
			} else {
				System.out.println("Game over!");
			}
			
		}
	}
	
	public void checkHitImage(Obstacle o, Graphics g) {
		if(Rocket.getRect().intersects(o.getRectImage())) {

			o.imagePresent = false;
			lives--;
			timer.stop();
			
			if(lives > 0) {
				Rocket.rocketX = 60;
				Rocket.rocketY = 400;
				timerStopped = true;
				g.drawString("Press enter to continue...", 650, 40);
			} else {
				System.out.println("Game over!");
			}
			
		}
	}
	
	public void checkHitBowserRocket(Obstacle o, Graphics g) {
		if(Rocket.getRect().intersects(o.getRectBowser())) {

			lives--;
			timer.stop();
			
			if(lives > 0) {
				Rocket.rocketX = 120;
				Rocket.rocketY = 400;
				timerStopped = true;
				g.drawString("Press enter to continue...", 650, 40);
			} else {
				System.out.println("Game over!");
			}
			
		}
	}
	
	public void checkHitEnemy(Obstacle o) {
		if(Projectile.getRect().intersects(o.getRectImage())) {
			missilePresent = false;
			o.imagePresent = false;
			rocketMissile = false;
		}
	}
	
	public void checkHitBowser(Obstacle o, Graphics g) {
		if(Projectile.getRect().intersects(o.getRectBowser())) {
			missilePresent = false;
			bowserHit = true;
			bowserLife -= 10;
			rocketMissile = false;
			Projectile.missileX = 0;
			Projectile.missileY = 0;
	
			if(bowserLife == 0) {
				o.imagePresent = false;
				g.drawString("Congratulations! You have defeated Bowser and completed the game!", 500, 20);
			}
		}
	}
	
	public void checkHitCoin(Obstacle o) {
		if(Rocket.getRect().intersects(o.getRectCoin())) {
			o.coinPresent = false;
			coins+= 1;
		}
	}
	
	public void checkButton(Obstacle b) {
		if(Projectile.getRect().intersects(b.getRectButton())) {
			missilePresent = false;
			targetHit = true;
			rocketMissile = false;
		}
	}
	
	public void paintComponent(Graphics g) {
		
		if(missileError) {
			//missileError = false;
			g.drawString("One missile at a time!", 500, 60);
		}
		
		if(level == 1) {
			Rocket r = new Rocket();
			
			int w = getWidth();
			int h = getHeight();
			
			if(coins == 26) {
				g.drawString("You collected all 26 coins! Here's a secret unlock code: RedIsReal2016", 500, 60);
			}
			
			if(obstaclePresent) {
				g.drawString("Lives remaining: " + lives, 20, 40);
				g.drawString("Coins: " + coins, 150, 40);
				w1.drawWall(g);
				w2.drawWall(g);
				w3.drawWall(g);
				w4.drawWall(g);
				w5.drawWall(g);
				w6.drawWall(g);
				w7.drawWall(g);
				w8.drawWall(g);
				w9.drawWall(g);
				w10.drawWall(g);
				w11.drawWall(g);
				w12.drawWall(g);
				w13.drawWall(g);
				w14.drawWall(g);
				w15.drawWall(g);
				w16.drawWall(g);
				w17.drawWall(g);
				w18.drawWall(g);
				w19.drawWall(g);
				w20.drawWall(g);
				w21.drawWall(g);
				w22.drawWall(g);
				w23.drawWall(g);
				w24.drawWall(g);
				g.setColor(Color.green);
				finish.drawWall(g);
				g.setColor(Color.black);
				koopa.drawImage(g);
				goomba.drawImage(g);
				beet.drawImage(g);
				bro.drawImage(g);
				goomba2.drawImage(g);
				koopa3.drawImage(g);
				bill.drawImage(g);
				coin.drawCoin(g);
				coin2.drawCoin(g);
				coin3.drawCoin(g);
				coin4.drawCoin(g);
				coin5.drawCoin(g);
				coin6.drawCoin(g);
				coin7.drawCoin(g);
				coin8.drawCoin(g);
				coin9.drawCoin(g);
				coin10.drawCoin(g);
				coin11.drawCoin(g);
				coin12.drawCoin(g);
				coin13.drawCoin(g);
				coin14.drawCoin(g);
				coin15.drawCoin(g);
				coin16.drawCoin(g);
				coin17.drawCoin(g);
				coin18.drawCoin(g);
				coin19.drawCoin(g);
				coin20.drawCoin(g);
				coin21.drawCoin(g);
				coin22.drawCoin(g);
				coin23.drawCoin(g);
				coin24.drawCoin(g);
				coin25.drawCoin(g);
				coin26.drawCoin(g);
			}
			
			if(rocketMissile) {
				Projectile.rocketMissile(g);
			}
			
			if(leftPressed && upPressed) {
				r.drawRocketUL(g);
				r.drawFlameUL(g);
			} else if(rightPressed && upPressed) {
				r.drawRocketUR(g);
				r.drawFlameUR(g);
			} else if(rightPressed && downPressed) {
				r.drawRocketDR(g);
				r.drawFlameDR(g);
			} else if(leftPressed && downPressed) {
				r.drawRocketDL(g);
				r.drawFlameDL(g);
			}else if(rocketUp) {
				r.drawRocketUp(g);
				r.drawFlameUp(g);
			} else if(rocketDown) {
				r.drawRocketDown(g);
				r.drawFlameDown(g);
			} else if(rocketLeft) {
				r.drawRocketLeft(g);
				if(flameFired) {
					r.drawFlameLeft(g);
				}
			} else {
			
				r.drawRocket(g);
				
				if(flameFired) {
					r.drawFlame(g);
				}
				
			}
			
			if(obstaclePresent) {
				this.checkHit(w1, g);
				this.checkHit(w2, g);
				this.checkHit(w3, g);
				this.checkHit(w4, g);
				this.checkHit(w5, g);
				this.checkHit(w6, g);
				this.checkHit(w7, g);
				this.checkHit(w8, g);
				this.checkHit(w9, g);
				this.checkHit(w10, g);
				this.checkHit(w11, g);
				this.checkHit(w12, g);
				this.checkHit(w13, g);
				this.checkHit(w14, g);
				this.checkHit(w15, g);
				this.checkHit(w16, g);
				this.checkHit(w17, g);
				this.checkHit(w18, g);
				this.checkHit(w19, g);
				this.checkHit(w20, g);
				this.checkHit(w21, g);
				this.checkHit(w22, g);
				this.checkHit(w23, g);
				this.checkHit(w24, g);
				if(koopa.imagePresent) {
					this.checkHitImage(koopa, g);
					this.checkHitEnemy(koopa);
				}
				if(goomba.imagePresent) {
					this.checkHitImage(goomba, g);
					this.checkHitEnemy(goomba);
				}
				if(beet.imagePresent) {
					this.checkHitImage(beet, g);
					this.checkHitEnemy(beet);
				}
				if(bro.imagePresent) {
					this.checkHitImage(bro, g);
					this.checkHitEnemy(bro);
				}
				if(goomba2.imagePresent) {
					this.checkHitImage(goomba2, g);
					this.checkHitEnemy(goomba2);
				}
				if(koopa3.imagePresent) {
					this.checkHitImage(koopa3, g);
					this.checkHitEnemy(koopa3);
				}
				if(bill.imagePresent) {
					this.checkHitImage(bill, g);
					this.checkHitEnemy(bill);
				}
				if(coin.coinPresent) {
					this.checkHitCoin(coin);
				}
				if(coin2.coinPresent) {
					this.checkHitCoin(coin2);
				}
				if(coin3.coinPresent) {
					this.checkHitCoin(coin3);
				}
				if(coin4.coinPresent) {
					this.checkHitCoin(coin4);
				}
				if(coin5.coinPresent) {
					this.checkHitCoin(coin5);
				}
				if(coin6.coinPresent) {
					this.checkHitCoin(coin6);
				}
				if(coin7.coinPresent) {
					this.checkHitCoin(coin7);
				}
				if(coin8.coinPresent) {
					this.checkHitCoin(coin8);
				}
				if(coin9.coinPresent) {
					this.checkHitCoin(coin9);
				}
				if(coin10.coinPresent) {
					this.checkHitCoin(coin10);
				}
				if(coin11.coinPresent) {
					this.checkHitCoin(coin11);
				}
				if(coin12.coinPresent) {
					this.checkHitCoin(coin12);
				}
				if(coin13.coinPresent) {
					this.checkHitCoin(coin13);
				}
				if(coin14.coinPresent) {
					this.checkHitCoin(coin14);
				}
				if(coin15.coinPresent) {
					this.checkHitCoin(coin15);
				}
				if(coin16.coinPresent) {
					this.checkHitCoin(coin16);
				}
				if(coin17.coinPresent) {
					this.checkHitCoin(coin17);
				}
				if(coin18.coinPresent) {
					this.checkHitCoin(coin18);
				}
				if(coin19.coinPresent) {
					this.checkHitCoin(coin19);
				}
				if(coin20.coinPresent) {
					this.checkHitCoin(coin20);
				}
				if(coin21.coinPresent) {
					this.checkHitCoin(coin21);
				}
				if(coin22.coinPresent) {
					this.checkHitCoin(coin22);
				}
				if(coin23.coinPresent) {
					this.checkHitCoin(coin23);
				}
				if(coin24.coinPresent) {
					this.checkHitCoin(coin24);
				}
				if(coin25.coinPresent) {
					this.checkHitCoin(coin25);
				}
				if(coin26.coinPresent) {
					this.checkHitCoin(coin26);
				}
				if(doorHitBoxPresent) {
					this.checkHit(door, g);
					this.checkHit(door2, g);
					this.checkHit(door3, g);
				}
				
			}
			
			if(invincibilityFrames) {
				timer2.start();
			}
			
			if(obstaclePresent) {
				timer2.stop();
			}
			
			if(doorPresent) {
				g.setColor(Color.red);
				door.drawDoor(g);
				door2.drawDoor(g);
				door3.drawDoor(g);
				g.setColor(Color.black);
			}
				
			if(!targetHit) {
				g.setColor(Color.red);
				button.drawButton(g);
				g.setColor(Color.black);
				this.checkButton(button);
			}
			
			if(targetHit) {
				g.setColor(Color.green);
				button.drawButton(g);
				g.setColor(Color.black);
			}
			
			if(Rocket.rocketY >= getHeight() - 26) { // 26 is roughly half the height of the rocket image.
				
				lives--;
				timer.stop();
				obstaclePresent = false;
				
				if(lives > 0) {
					Rocket.rocketX = 60;
					Rocket.rocketY = 400;
					timerStopped = true;
				} else {
					System.out.println("Game over!");
				}
				
			}
			
			if(Rocket.getRect().intersectsLine(finish.getLine())) {
				timer.stop();
				timerStopped = true;
				nextLevel = true;
			}
		
		}

		if(level == 2) {
			Rocket r = new Rocket();
			
			int w = getWidth();
			int h = getHeight();
			
			int healthX = 67;
			int healthY = 50;
			
			g.setColor(Color.red);
			g.fillRect(healthX, healthY, gauge, 10);
			g.setColor(Color.black);
			
			if(obstaclePresent) {
				if(bowserLife == 0) {
					g.drawString("Congratulations! You have defeated Bowser and completed the game!", 500, 20);
					timer.stop();
				}
				g.drawString("Lives remaining: " + lives, 80, 20);
				//this.checkHit2(l2Line, g);
				this.checkHit2(l2Line2, g);
				this.checkHit2(l2Line3, g);
				this.checkHit2(l2Line4, g);
				g.drawLine(45, 30, 45, 750);
				g.drawLine(45, 30, 1390, 30);
				g.drawLine(45, 750, 1390, 750);
				g.drawLine(1390, 30, 1390, 750);
				bowser.drawBowser(g);
				fireball.drawFireball(g);
				if(bowser.imagePresent) {
					this.checkHitBowser(bowser, g);
					this.checkHitBowserRocket(bowser, g);
					if(bowserHit) {
						gauge -= 26;
						bowserHit = false;
					}
				}
			}
			
			if(rocketMissile) {
				Projectile.rocketMissile(g);
			}
			
			if(leftPressed && upPressed) {
				r.drawRocketUL(g);
				r.drawFlameUL(g);
			} else if(rightPressed && upPressed) {
				r.drawRocketUR(g);
				r.drawFlameUR(g);
			} else if(rightPressed && downPressed) {
				r.drawRocketDR(g);
				r.drawFlameDR(g);
			} else if(leftPressed && downPressed) {
				r.drawRocketDL(g);
				r.drawFlameDL(g);
			}else if(rocketUp) {
				r.drawRocketUp(g);
				r.drawFlameUp(g);
			} else if(rocketDown) {
				r.drawRocketDown(g);
				r.drawFlameDown(g);
			} else if(rocketLeft) {
				r.drawRocketLeft(g);
				if(flameFired) {
					r.drawFlameLeft(g);
				}
			} else {
			
				r.drawRocket(g);
				
				if(flameFired) {
					r.drawFlame(g);
				}
				
			}
			
			if(invincibilityFrames) {
				timer2.start();
			}
			
			if(obstaclePresent) {
				timer2.stop();
			}
			
			if(Rocket.rocketY >= getHeight() - 26) { // 26 is roughly half the height of the rocket image.
				
				lives--;
				timer.stop();
				obstaclePresent = false;
				
				if(lives > 0) {
					Rocket.rocketX = 60;
					Rocket.rocketY = 400;
					timerStopped = true;
				} else {
					System.out.println("Game over!");
				}
				
			}
			
		}
		
	}
	
	
}
