package snake;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import java.util.Random;
import javax.swing.JPanel;

//@SupressWarnings("serial")//not in video??

public class gamePanel extends JPanel implements ActionListener{ //implements action events
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE =25;  //size of objects on screen
	static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT)/UNIT_SIZE;  //SETS SIZE ACCORDING TO ACTUAL SCREEN SIZE;
	static final int DELAY= 150;//HIGHER THE NUMBER THE SLOWER THE GAME &VV
	final int x[] = new int[GAME_UNITS];//x units of the body & head of snake
	final int y[] = new int[GAME_UNITS];//y units of snake
	int bodyParts = 6; //snake body parts
	int applesEaten;
	int appleX;
	int appleY;
	char direction = 'R';
	boolean running =false;
	Timer timer;
	Random random;
	
	
	gamePanel(){ //constructor
		random =new Random(); //inst of random class
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.white);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();			
	}
	public void startGame() { //method to start game
		newApple();
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
	}	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		draw (g);	
	}
	
	
	public void draw(Graphics g) {	
		
		if(running) {
			
			g.setColor(Color.blue);// apple color
			g.fillOval(appleX, appleY, UNIT_SIZE,UNIT_SIZE);
		
			for (int i=0; i<bodyParts; i++) {
				if (i == 0) {
					
					g.setColor(Color.green);//snake head
					g.fillRect(x[i],y[i], UNIT_SIZE,UNIT_SIZE);
				}
				
				else {
					g.setColor(new Color(45,180,0)); //snake body
					g.fillRect(x[i],y[i], UNIT_SIZE,UNIT_SIZE);
				}
			}
				g.setColor(Color.red);
				g.setFont(new Font ("Ink Free",Font.BOLD,40));
				FontMetrics metrics1 = getFontMetrics(g.getFont());
				g.drawString("Score: "+applesEaten,(SCREEN_WIDTH - metrics1.stringWidth("Score: +applesEaten"))/2, g.getFont().getSize());	
			}
	
		else {
	gameOver(g);
	}
 }

	
public void newApple () {
appleX =random.nextInt((int)SCREEN_WIDTH/UNIT_SIZE)*UNIT_SIZE; //generates new apple when needed on x axis
appleY =random.nextInt((int)SCREEN_HEIGHT/UNIT_SIZE)*UNIT_SIZE; //generates new apple when needed on Y axis
	}
	public void move() {
		for(int i =bodyParts;i>0;i--){
			x[i] = x[i-1];  //moves snake on x axis
			y[i] = y[i-1]; 		
		}
		switch(direction) {
		case'U'://movements
			y[0]= y[0]- UNIT_SIZE;  //HEAD OF SNAKE ON Y AXIS
			break;
		case'D':
			y[0]= y[0] + UNIT_SIZE;  //down
			break;
		case'L':
			x[0]= x[0]- UNIT_SIZE;  //left 
			break;
		case'R':
			x[0]= x[0]+ UNIT_SIZE;  //right
			break;
			}	
		}
	public void checkApple() {	
		if((x[0]==appleX)&& (y[0]==appleY)){
			bodyParts++;
			applesEaten++; //keeps score
			newApple(); //makes new apple to continue game		
		}
	}
	public void checkCollisions() {
		for(int i= bodyParts; i>0;i--) {
			if((x[0]==x[i])&&(y[0]==y[i])){ //checks if head collides w body 
		    running =false;  //triggers a game over
			}
		}
		if(x[0]<0) { //checks if head of snake touches  L border
			running =false;  //triggers a game over
		}

		if(x[0]> SCREEN_WIDTH) { //checks if head of snake touches  R border
			running =false;  //triggers a game over
		}
		if(y[0]< 0) { //checks if head of snake touches topborder
			running =false;  //triggers a game over
		}
		if(y[0]> SCREEN_HEIGHT) { //checks if head of snake touches bottom border
			running =false;  //triggers a game over
		}
		 if(!running) {
			 timer.stop();
		 }
	 }
	public void gameOver (Graphics g) {//game over text
		g.setColor(Color.red);
		g.setFont(new Font ("Ink Free",Font.BOLD,75));
		FontMetrics metrics =getFontMetrics(g.getFont());
		g.drawString("GameOver", (SCREEN_WIDTH - metrics.stringWidth("Game Over"))/2,SCREEN_HEIGHT/2);
	
		g.setColor(Color.red);
		g.setFont(new Font ("Block",Font.BOLD,40));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Score: "+applesEaten,(SCREEN_WIDTH - metrics2.stringWidth("Score: +applesEaten"))/2, g.getFont().getSize());	
	}
	
		
	@Override
	public void actionPerformed(ActionEvent e) {
			if (running) {
				move();
				checkApple();
				checkCollisions();				
			}
			repaint(); //reset			
		}
	public class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) { //sets up arrow keys		
			case KeyEvent.VK_LEFT:
			if (direction!='R') {//stops 180 degree turns 90 only
				direction='L';			
			}
			break;		 
		  case KeyEvent.VK_RIGHT:
			if (direction!='L') {//stops 180 degree turns 90 only
				direction='R';			
			}
			break;		 
		  case KeyEvent.VK_UP:
			if (direction!='D') {//stops 180 degree turns 90 only
				direction='U';			
			}
		break;		 
		  case KeyEvent.VK_DOWN:
			if (direction!='U') {//stops 180 degree turns 90 only
				direction='D';			
			}	
			break;
			}
		}
	}
}



