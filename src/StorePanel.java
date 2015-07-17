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
import javax.swing.Timer;

class StorePanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	RumpelstiltskinTypocalypse frame;
	//JButton objects
	JButton storePotionButton;
	JButton storeFreezeButton;
	JButton storeBombButton;
	
	//JLabel objects
	JLabel storePotionLabel;
	JLabel storeFreezeLabel;
	JLabel storeBombLabel;
	JLabel potionLabel;
	JLabel freezeLabel;
	JLabel bombLabel;
	JLabel goldIcon;
	JLabel potionCost;
	JLabel freezeCost;
	JLabel bombCost;
		
	//icons for JButton objects
	ImageIcon coin=new ImageIcon ("images/Coin-04-june.gif");
	ImageIcon potion=new ImageIcon ("images/potion.gif");
	ImageIcon freezeRay=new ImageIcon ("images/freezeray2.png");
	ImageIcon bomb=new ImageIcon ("images/bomb.gif");
	ImageIcon buy=new ImageIcon ("images/buy2.png");
	ImageIcon shopIcon=new ImageIcon ("images/shop.png");
	
	//images
	Image border;
	Image header;
	Image backGround;
	Image shop;
	Timer storeTimer;

	public StorePanel(RumpelstiltskinTypocalypse frame){

	this.frame=frame;
	this.setLayout(null);//null layout

	storeTimer=new Timer (100,this);
	storeTimer.start();//starts the timer
	
	//initialize components
	storePotionLabel = new JLabel(potion);
	storeFreezeLabel = new JLabel(freezeRay);
	storeBombLabel = new JLabel(bomb);
	goldIcon = new JLabel(coin);
	potionLabel = new JLabel("0");
	freezeLabel = new JLabel("0");
	bombLabel = new JLabel("0");
	storePotionButton = new JButton(buy);
	storeFreezeButton = new JButton(buy);
	storeBombButton = new JButton(buy);

	//add components to JPanel
	this.add(storePotionLabel);
	this.add(storeFreezeLabel);
	this.add(storeBombLabel);
	this.add(goldIcon);

	this.add(potionLabel);
	this.add(freezeLabel);
	this.add(bombLabel);

	this.add(storePotionButton);
	this.add(storeFreezeButton);
	this.add(storeBombButton);
	
	//add actionListeners
	storePotionButton.addActionListener(this);
	storeFreezeButton.addActionListener(this);
	storeBombButton.addActionListener(this);

	//set the positions of the components
	storeFreezeLabel.setBounds(350,150,100,200);
	storePotionLabel.setBounds(600,100,100,200);
	storeBombLabel.setBounds(850,150,100,200);
	goldIcon.setBounds(900,130,100,100);

	potionLabel.setBounds(615,369,10,10);
	freezeLabel.setBounds(365,369,10,10);
	bombLabel.setBounds(865,369,10,10);

	storeFreezeButton.setBounds(350,450,100,40);
	storePotionButton.setBounds(600,450,100,40);
	storeBombButton.setBounds(850,450,100,40);

	//initialize image objects
	border = Toolkit.getDefaultToolkit().getImage("images/prince.png");
	header = Toolkit.getDefaultToolkit().getImage("images/header2.png");	
	backGround = Toolkit.getDefaultToolkit().getImage("images/background.jpg");
	border = Toolkit.getDefaultToolkit().getImage("images/border2.jpg");
	shop = Toolkit.getDefaultToolkit().getImage("images/shop1.png");

	frame.gamePanel.bottomPanel.typeField.setEnabled(false);
	frame.gamePanel.bottomPanel.enterButton.setEnabled(false);
		
	}
	/**
	*	Override the paintComponent method for drawing images on the JPanel
	*/
	public void paintComponent(Graphics g){
	Graphics2D ga = (Graphics2D)g;
		super.paintComponent(g);
		//draw background
		g.drawImage(backGround,0,100,this);
		g.drawImage(border,200,120,this);
		g.drawImage(shop,250,150,this);
		g.setColor(Color.BLACK);
		g.drawImage(header,0,0,this);
		g.fillRect(600,0,700,100);
		ga.setPaint(Color.black);  
	
		//displays the current gold of the prince
		ga.setFont(new Font("TimesRoman", Font.BOLD,  24));  
		ga.drawString(""+frame.prince.getGold(),970,190);
		//display details for freeze ray
		ga.setFont(new Font("TimesRoman", Font.BOLD,  14)); 	
		ga.drawString("             Freeze Ray",300,300);	
		ga.drawString("This item make the elves",300,320);		
		ga.drawString("immobile for few seconds.",300,340);
		
		ga.drawString("In Bag: ",300,380);
		ga.drawString("Cost: 10 gold coins",300,400);
		//display details for potion
		ga.drawString("               Potion",550,300);	
		ga.drawString("This item restores your ",550,320);		
		ga.drawString("health by 10HP.",550,340);
	
		ga.drawString("In Bag: ",550,380);
		ga.drawString("Cost: 15 gold coins",550,400);
		//display details for bomb
		ga.drawString("               Bomb",800,300);	
		ga.drawString("This item kill all the elves",800,320);		
		ga.drawString("present on the screen.",800,340);
	
		ga.drawString("In Bag: ",800,380);
		ga.drawString("Cost: 20 gold coins",800,400);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==storePotionButton){
			if(frame.prince.getGold()>=15){
			frame.prince.buyPotion();//buy potion
			}
			//displays an error message
			else{
			JOptionPane.showMessageDialog(null,"Not Enough Gold!","Sorry!",JOptionPane.INFORMATION_MESSAGE,shopIcon);
			}
			frame.gamePanel.leftSidePanel.goldLabel.setText(""+frame.prince.getGold());
			frame.gamePanel.leftSidePanel.potionLabel.setText(""+frame.prince.getPotion());
			potionLabel.setText(""+frame.prince.getPotion());
		}
		if(e.getSource()==storeBombButton){
			if(frame.prince.getGold()>=20){
			frame.prince.buyBomb();//buy bomb
			}
			//displays an error message
			else{
			JOptionPane.showMessageDialog(null,"Not Enough Gold!","Sorry!",JOptionPane.INFORMATION_MESSAGE,shopIcon);
			}
			frame.gamePanel.leftSidePanel.goldLabel.setText(""+frame.prince.getGold());
			frame.gamePanel.leftSidePanel.bombLabel.setText(""+frame.prince.getBomb());
			bombLabel.setText(""+frame.prince.getBomb());
		}
		if(e.getSource()==storeFreezeButton){
			if(frame.prince.getGold()>=10){
			frame.prince.buyFreezeRay();//buy freeze ray
			}
			else{
			//displays an error message
			JOptionPane.showMessageDialog(null,"Not Enough Gold!","Sorry!",JOptionPane.INFORMATION_MESSAGE,shopIcon);
			}
			frame.gamePanel.leftSidePanel.goldLabel.setText(""+frame.prince.getGold());
			frame.gamePanel.leftSidePanel.freezeLabel.setText(""+frame.prince.getFreezeRay());
			freezeLabel.setText(""+frame.prince.getFreezeRay());
		}
		if(e.getSource()==storeTimer){
			this.repaint();				
		}
	}

}
