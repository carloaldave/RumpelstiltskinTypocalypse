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

public class RumpelstiltskinTypocalypse extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//cardHolder panel
	JPanel cardHolder;
	
	//panels
	MainMenuPanel mainMenuPanel;
	GamePanel gamePanel;
	StorePanel storePanel;
	HowToPlayPanel howToPlayPanel; 

	//menu buttons
	JButton playButton;
	JButton storeButton;
	JButton howToPlayButton;
	JButton exitButton;
	
	//back to main menu buttons
	JButton mainMenuButton;
	JButton mainMenuButton2;
	JButton mainMenuButton3;

	//name field
	JTextField nameField;
	JButton nameButton;

	//layout
	CardLayout cardLayout;
	
	Prince prince;//declare the prince object
	int maxHealth=50;//initialize the maximum health for the prince 

	//initialize icons for buttons
	ImageIcon howToPlay=new ImageIcon ("images/howtoplay.png");
	ImageIcon play=new ImageIcon ("images/play.png");
	ImageIcon cont=new ImageIcon ("images/continue.png");
	ImageIcon shop=new ImageIcon ("images/shop1.png");
	ImageIcon exit=new ImageIcon ("images/exit.png");
	ImageIcon submit=new ImageIcon ("images/boxbutton3.gif");
	ImageIcon mainMenu=new ImageIcon ("images/menubutton.png");
	ImageIcon princeIcon=new ImageIcon ("images/prince4.gif");

	public RumpelstiltskinTypocalypse(){
		this.setTitle("Rumpelstiltskin Typocalypse");//set the title of JFrame

		cardLayout=new CardLayout();//create card layout object
		cardHolder=new JPanel(cardLayout);		
		
		Container container=getContentPane();
		container.add(cardHolder,BorderLayout.CENTER);
	
		//initialize mainMenuPanel and components
		mainMenuPanel=new MainMenuPanel();	
		nameField=new JTextField(10);
		mainMenuPanel.add(nameField);
		nameButton=new JButton(submit);
		nameButton.addActionListener(this);
		nameButton.setBorder(BorderFactory.createEmptyBorder());
		nameButton.setContentAreaFilled(false);	
		nameField.setBounds(50,70,200,30);
		nameButton.setBounds(90,110,122,40);
		mainMenuPanel.add(nameButton);		

		playButton=new JButton(play);
		playButton.setBounds(980,200,200,30);	
		playButton.addActionListener(this);	
		mainMenuPanel.add(playButton);		

		mainMenuButton=new JButton(mainMenu);	
		gamePanel=new GamePanel(this);			
		mainMenuButton.addActionListener(this);		
		gamePanel.topPanel.add(mainMenuButton);	

		storeButton=new JButton(shop);	
		storePanel=new StorePanel(this);
		storeButton.setBounds(980,300,200,30);
		storeButton.addActionListener(this);		
		mainMenuPanel.add(storeButton);

		howToPlayButton=new JButton(howToPlay);	
		howToPlayPanel=new HowToPlayPanel(this);
		howToPlayButton.setBounds(930,400,300,30);
		howToPlayButton.addActionListener(this);		
		mainMenuPanel.add(howToPlayButton);
		
		//creates a back to main menu button on the storePanel
		mainMenuButton2=new JButton(mainMenu);			
		mainMenuButton2.addActionListener(this);
		mainMenuButton2.setBounds(1000,0,400,100);		
		storePanel.add(mainMenuButton2);

		//creates a back to main menu button on the howToPlayPanel
		mainMenuButton3=new JButton(mainMenu);			
		mainMenuButton3.addActionListener(this);		
		mainMenuButton3.setBounds(1000,0,400,100);			
		howToPlayPanel.add(mainMenuButton3);
	
		//creates an exit button
		exitButton = new JButton(exit);
		mainMenuPanel.add(exitButton);	
		exitButton.setBounds(1030,500,100,30);
		exitButton.addActionListener(this);

		//add panels to the cardHolder panel
		cardHolder.add(mainMenuPanel,"Card1");
		cardHolder.add(gamePanel,"Card2");
		cardHolder.add(howToPlayPanel,"Card3");	
		cardHolder.add(storePanel,"Card4");	
		
		//delete the borders of the JButton objects
		
		howToPlayButton.setBorder(BorderFactory.createEmptyBorder());
		howToPlayButton.setContentAreaFilled(false);
		playButton.setBorder(BorderFactory.createEmptyBorder());
		playButton.setContentAreaFilled(false);
		storeButton.setBorder(BorderFactory.createEmptyBorder());
		storeButton.setContentAreaFilled(false);
		exitButton.setBorder(BorderFactory.createEmptyBorder());
		exitButton.setContentAreaFilled(false);
		mainMenuButton.setBorder(BorderFactory.createEmptyBorder());
		mainMenuButton.setContentAreaFilled(false);
		mainMenuButton2.setBorder(BorderFactory.createEmptyBorder());
		mainMenuButton2.setContentAreaFilled(false);
		mainMenuButton3.setBorder(BorderFactory.createEmptyBorder());
		mainMenuButton3.setContentAreaFilled(false);

		//enable the play and store button
		playButton.setEnabled(false);
		storeButton.setEnabled(false);

		this.setSize(1300,700);	
		this.setVisible(true);
		this.setResizable(false);
		this.setLocation(0,0);
	}
	
	public static void main(String[] args){
		RumpelstiltskinTypocalypse frame = new RumpelstiltskinTypocalypse();
	}
	
	public void actionPerformed(ActionEvent e){	
		if(e.getSource()==playButton){				
			cardLayout.show(cardHolder,"Card2");//show the GamePanel
			playButton.setIcon(cont);//change the icon of the playbutton
		}
		if(e.getSource()==howToPlayButton){	
			cardLayout.show(cardHolder,"Card3");//show the HowToPlayPanel
		}
		if(e.getSource()==storeButton){	
			cardLayout.show(cardHolder,"Card4");//show the StorePanel
		}
		if(e.getSource()==exitButton){	
			System.exit(0);//exit 
		}
		if(e.getSource()==mainMenuButton){
			gamePanel.centerPanel.t.stop();
			gamePanel.centerPanel.gameTimer.stop();
			gamePanel.bottomPanel.typeField.setEnabled(false);
			gamePanel.bottomPanel.enterButton.setEnabled(false);
			cardLayout.show(cardHolder,"Card1");//show the MainMenuPanel
		}
		if(e.getSource()==mainMenuButton2){
			cardLayout.show(cardHolder,"Card1");//show the MainMenuPanel
		}
		if(e.getSource()==mainMenuButton3){	
			cardLayout.show(cardHolder,"Card1");//show the MainMenuPanel
		}
		if(e.getSource()==nameButton){
			//create a prince object
			prince=new Prince(nameField.getText(),maxHealth);
			prince.receiveGold(50);
			prince.levelUp();
			gamePanel.leftSidePanel.levelLabel.setText(""+prince.getLevel());
			gamePanel.leftSidePanel.goldLabel.setText(""+prince.getGold());
			playButton.setEnabled(true);
			storeButton.setEnabled(true);
			nameField.setEnabled(false);
			nameButton.setEnabled(false);
			
			JOptionPane.showMessageDialog(null,"Hello Prince "+prince.getName()+"!"
			+"\nYou are given 50 gold coins which you "
			+"\ncan use to buy items at the store."
			+"\nThese items will help you kill the"
			+"\nelves that will try to reach the castle.",
			"WELCOME",JOptionPane.INFORMATION_MESSAGE,princeIcon);
			
		}
	}
}





