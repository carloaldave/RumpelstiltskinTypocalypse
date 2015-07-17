/*
*Author:John Carlo C. Aldave
*CMSC 22 Y-2L
*Project Title: Rumpelstiltskin Typocalypse
*Date Submitted:March 26, 2013
*Program Description:Rumpelstiltskin Typocalypse is a typing game application.
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class HowToPlayPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	RumpelstiltskinTypocalypse frame;
	//image objects
	Image border;
	Image header;
	Image backGround;
	Image howToPlay;

	public HowToPlayPanel(RumpelstiltskinTypocalypse frame){
	this.frame=frame;
	this.setLayout(null);
	//initialize the image objects
	border = Toolkit.getDefaultToolkit().getImage("images/prince.png");
	header = Toolkit.getDefaultToolkit().getImage("images/header2.png");	
	backGround = Toolkit.getDefaultToolkit().getImage("images/background.jpg");
	border = Toolkit.getDefaultToolkit().getImage("images/border2.jpg");
	howToPlay = Toolkit.getDefaultToolkit().getImage("images/howtoplay.png");
	}
	public void paintComponent(Graphics g){
	Graphics2D ga = (Graphics2D)g;
		super.paintComponent(g);
		//draw the background
		g.drawImage(backGround,0,100,this);
		g.drawImage(border,200,120,this);
		g.drawImage(howToPlay,250,150,this);
		g.setColor(Color.BLACK);
		g.drawImage(header,0,0,this);
		g.fillRect(600,0,700,100);
		//draw the instructions for the game
		ga.setFont(new Font("TimesRoman", Font.BOLD,  14)); 	
		ga.drawString("Your mission is to survive till the end of the game, kill all the elves that will try to",300,220);	
		ga.drawString("reach the castle. You can kill them by typing their name on the text field on the bottom and ",300,240);	
		ga.drawString("click enter or press UP on your keyboard. Collect all the gold coins because you can use",300,260);	
		ga.drawString("these to buy items at the shop, these items will surely help you to survive in the game.",300,280);	
		ga.drawString("You must collect the coin before it disappears, coin closer to the castle disappears faster ",300,300);
		ga.drawString("You cannot buy items during the game, you can only go to shop after every level. ",300,320);
		ga.drawString("WARNING: The elves get tougher as the games goes further, their names get longer, speed",300,380);	
		ga.drawString("goes faster and their damage gets higher.",300,400);	
	
	}
	public void actionPerformed(ActionEvent e){

	}
}
