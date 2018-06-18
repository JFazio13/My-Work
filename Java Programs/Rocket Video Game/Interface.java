/* James Fazio
 * Project 4
 * Lab: Mon/Wed 12:30 – 1:45
 * Lab TA: Sofia Carrillo
 * 
 * I did not collaborate with anyone on this assignment.
 */

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class Interface extends JPanel implements ActionListener {
	
	protected JButton button;
	protected static int droneColor = 0;
	protected static int flameColor = 0;
	protected static int type = 0;
	protected JLabel label, label2;
	protected JTextField jtext;
	
	public Interface() {
		this.showInterface(); // Immediately build the UI upon creating an instance
		
		JFrame frame = new JFrame("Rocket Pilot");
		frame.setSize(305, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent a) {
		
		if(a.getSource() == button) {
			this.newCanvas(); // Create new drawing canvas
		}
		
		if(a.getSource() == jtext) {
			if(jtext.getText().equals("RedIsReal2016")) {
				System.out.println("You have unlocked the red rocket!");
				droneColor = 2;
			}
		}
		
	}
	
	public void showInterface() { // Implement each element and fill in the UI
		
		setLayout(new FlowLayout());
		
		label = new JLabel("Welcome to Rocket Pilot! Press Start to begin.");
		add(label);
		
		label2 = new JLabel("Earned an unlock code? Enter it here:");
		add(label2);
		
		jtext = new JTextField(10);
		jtext.addActionListener(this);
		add(jtext);
	    
		button = new JButton("Start!");
		button.addActionListener(this);
		add(button);
		
	}
	
	public void newCanvas() {
		Canvas canvas = new Canvas();
	}
	
	public static void main(String[] args) {

		Interface canvas = new Interface();

	}

}
