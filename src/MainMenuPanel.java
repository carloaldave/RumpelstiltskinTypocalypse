/*
*Author:John Carlo C. Aldave
*CMSC 22 Y-2L
*Project Title: Rumpelstiltskin Typocalypse
*Date Submitted:March 26, 2013
*Program Description:Rumpelstiltskin Typocalypse is a typing game application.
*/
import javax.swing.*;
import java.awt.*;

class MainMenuPanel extends JPanel{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//image objects
Image title;
Image menu;
	
	//constructor
	public MainMenuPanel(){
		this.setLayout(null);
		//initialize image objects
		title = Toolkit.getDefaultToolkit().getImage("images/home.jpg");
		menu = Toolkit.getDefaultToolkit().getImage("images/menu2.png");
	}
	/**
	*	Override the paintComponent method for drawing images on the JPanel
	*/
	public void paintComponent(Graphics g){

		super.paintComponent(g);
		//draw background
		g.setColor(Color.BLACK);
		g.drawImage(title,0,0,this);
		g.fillRect(870,0,430,700);
		g.drawImage(menu,900,100,this);
		g.setColor(Color.WHITE);  
		g.setFont(new Font("TimesRoman", Font.BOLD,  12));  
		g.drawString("Enter your name here:",50,60);
  
	}
	
}
