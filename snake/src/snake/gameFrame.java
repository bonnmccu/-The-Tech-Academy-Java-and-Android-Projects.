package snake;

import javax.swing.JFrame;
//@SupressWarnings("serial") //not in video?

public class gameFrame extends JFrame{
	gameFrame(){
		
	this.add(new gamePanel()); // shortcut from gamePanel panel =new gamePanel()
	this.setTitle("Snake");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setResizable(false);
	this.pack(); //used to fit JFrame around components
	this.setVisible(true);
	this.setLocationRelativeTo(null); //centers in the middle of screen
	
	}

}
