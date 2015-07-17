/*
*Author:John Carlo C. Aldave
*CMSC 22 Y-2L
*Project Title: Rumpelstiltskin Typocalypse
*Date Submitted:March 26, 2013
*Program Description:Rumpelstiltskin Typocalypse is a typing game application.
*/
import javax.swing.*;
import java.awt.*;

class GamePanel extends JPanel { 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Jpanel objects
	CenterPanel centerPanel;
	LeftSidePanel leftSidePanel; 
	TopPanel topPanel;
	BottomPanel bottomPanel;

	int i=0;	
	RumpelstiltskinTypocalypse frame;
	BorderLayout layout;
	
	//constructor
	public GamePanel(RumpelstiltskinTypocalypse frame){

		this.frame=frame;
		layout = new BorderLayout();
		this.setLayout(layout);//set the layout of the panel
		this.setBackground(new Color(250,20,0));

		//initialize panels
		centerPanel = new CenterPanel(frame);
		leftSidePanel = new LeftSidePanel(frame);
		topPanel = new TopPanel(frame);
		bottomPanel = new BottomPanel(frame);
		
		//add the panels to the gamePanel
		this.add(centerPanel,BorderLayout.CENTER);		
		this.add(leftSidePanel,BorderLayout.WEST);
		this.add(topPanel,BorderLayout.NORTH);
		this.add(bottomPanel,BorderLayout.SOUTH);


	}


}


