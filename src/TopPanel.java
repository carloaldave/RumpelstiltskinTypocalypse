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

class TopPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField nameField;
	JButton startButton;
	int maxHealth=50;
	Image header;
	RumpelstiltskinTypocalypse frame;
	ImageIcon start=new ImageIcon ("images/startbutton.png");

	//constructor
	public TopPanel(RumpelstiltskinTypocalypse frame){
		this.frame=frame;
		this.setLayout(null);
		this.setBackground(new Color(250,0,0));
		this.setPreferredSize(new Dimension(0, 100));
		frame.mainMenuButton.setBounds(1000,0,400,100);
		startButton = new JButton(start);
		startButton.setBounds(500,0,300,100);
		this.add(startButton);
		startButton.addActionListener(this);
		header = Toolkit.getDefaultToolkit().getImage("images/header2.png");
		//deletes the border of the JButton
		startButton.setBorder(BorderFactory.createEmptyBorder());
		startButton.setContentAreaFilled(false);
	}

	public void actionPerformed(ActionEvent e){

		if(e.getSource()==startButton){
			frame.gamePanel.bottomPanel.typeField.setEnabled(true);
			frame.gamePanel.bottomPanel.enterButton.setEnabled(true);
			frame.gamePanel.centerPanel.t.start();
			frame.gamePanel.centerPanel.gameTimer.start();//start the game timer
			frame.gamePanel.bottomPanel.typeField.requestFocusInWindow();

		}

	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawImage(header,0,0,this);//draw the header
		g.fillRect(600,0,700,100);
		
	}
}
