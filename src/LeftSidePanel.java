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

class LeftSidePanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//JLabel objects	
	JLabel goldLabel;
	JLabel potionLabel;
	JLabel freezeLabel;
	JLabel bombLabel;
	JLabel goldPic;
	JLabel levelLabel;
	//JButton objects
	JButton potionButton;
	JButton freezeButton;
	JButton bombButton;
	RumpelstiltskinTypocalypse frame;
	//icons for the JButtons
	ImageIcon coin=new ImageIcon ("images/Coin-04-june.gif");
	ImageIcon potion=new ImageIcon ("images/potion.gif");
	ImageIcon freezeRay=new ImageIcon ("images/freezeray2.png");
	ImageIcon bomb=new ImageIcon ("images/bomb.gif");
	//image objects
	Image prince;
	Image castle;
	Image night;
	Image ground;
	Image level1;
	Image level2;
	Image level3;
	int i=0;
	//timer object
	Timer t2;
	int x=0;

	//constructor
	public LeftSidePanel(RumpelstiltskinTypocalypse frame){

		this.frame=frame;
		this.setBackground(new Color(30,0,100));
		this.setPreferredSize(new Dimension(200, 0));
		this.setLayout(null);
		
		t2 = new Timer(100,this);//timer for the freeze ray

		//initialize componets
		goldLabel = new JLabel("0");
		potionLabel = new JLabel("0");
		freezeLabel = new JLabel("0");
		bombLabel = new JLabel("0");
		levelLabel = new JLabel();

		goldPic = new JLabel(coin);
		potionButton= new JButton(potion);
		freezeButton= new JButton(freezeRay);
		bombButton= new JButton(bomb);

		//add components to JPanel
		this.add(goldPic);
		this.add(goldLabel);
		this.add(potionButton);
		this.add(potionLabel);
		this.add(freezeButton);
		this.add(freezeLabel);
		this.add(bombButton);
		this.add(bombLabel);
		this.add(levelLabel);
		
		//add actionListeners to JButton
		bombButton.addActionListener(this);
		freezeButton.addActionListener(this);
		potionButton.addActionListener(this);

		//deletes the border of the JButtons
		potionButton.setBorder(BorderFactory.createEmptyBorder());
		potionButton.setContentAreaFilled(false);
		freezeButton.setBorder(BorderFactory.createEmptyBorder());
		freezeButton.setContentAreaFilled(false);
		bombButton.setBorder(BorderFactory.createEmptyBorder());
		bombButton.setContentAreaFilled(false);

		//set the positions of the components
		goldLabel.setBounds(110,40,80,20);
		potionLabel.setBounds(110,130,80,20);
		freezeLabel.setBounds(110,210,80,20);
		bombLabel.setBounds(110,290,80,20);

		goldPic.setBounds(10,20,80,50);
		potionButton.setBounds(10,60,80,130);
		freezeButton.setBounds(10,200,80,50);
		bombButton.setBounds(10,270,80,50);

		//initialize images
		prince = Toolkit.getDefaultToolkit().getImage("images/prince4.gif");
		castle = Toolkit.getDefaultToolkit().getImage("images/nightcastle4.png");
		night = Toolkit.getDefaultToolkit().getImage("images/night.jpg");
		ground = Toolkit.getDefaultToolkit().getImage("images/ground7.png");
		level1 = Toolkit.getDefaultToolkit().getImage("images/level1.png");
		level2 = Toolkit.getDefaultToolkit().getImage("images/level2.png");
		level3 = Toolkit.getDefaultToolkit().getImage("images/level3.png");
	}
	/**
	*	Override the paintComponent method for drawing images on the JPanel
	*/
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//draw background
		g.drawImage(night,0,0,this);
		g.drawImage(ground,0,20,this);
		g.drawImage(castle,0,0,this);
		g.setColor(Color.WHITE);
		g.fillRect(100,40,80,20);	
		g.fillRect(100,130,80,20);
		g.fillRect(100,210,80,20);
		g.fillRect(100,290,80,20);	
		g.setColor(Color.BLACK);
		g.drawRect(100,40,80,20);	
		g.drawRect(100,130,80,20);
		g.drawRect(100,210,80,20);
		g.drawRect(100,290,80,20);
		g.drawImage(prince,-40,280,this);
		//draw the images for the level
		if(frame.prince.getLevel()==1){
		g.drawImage(level1,0,0,this);
		}
		else if(frame.prince.getLevel()==2){
		g.drawImage(level2,0,0,this);
		}
		else if(frame.prince.getLevel()==3){
		g.drawImage(level3,0,0,this);
		}
		
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==bombButton){
			if(frame.prince.getBomb()>0){
				//use bomb if the prince have bomb in bag
				for(i=0;i<frame.gamePanel.centerPanel.elfCount;i++){	
					if(frame.gamePanel.centerPanel.elf[i].getStatus().compareTo("alive")==0){
					frame.gamePanel.centerPanel.elf[i].bombed();
					}
				}
			frame.prince.useBomb();//use bomb
			}
			frame.gamePanel.leftSidePanel.bombLabel.setText(""+frame.prince.getBomb());
			frame.storePanel.bombLabel.setText(""+frame.prince.getBomb());

			frame.gamePanel.bottomPanel.typeField.requestFocusInWindow();
		}
		if(e.getSource()==freezeButton){
		
			if(frame.prince.getFreezeRay()>0){
			//use freeze ray if the prince have freeze ray in bag
				frame.gamePanel.centerPanel.t.stop();
				for(i=0;i<frame.gamePanel.centerPanel.elfCount;i++){
					if(frame.gamePanel.centerPanel.elf[i].getStatus().compareTo("alive")==0){	
					frame.gamePanel.centerPanel.elf[i].freeze();//set the status to "freezed"
					}
				}
		
			t2.start();	
			frame.prince.useFreezeRay();//use freezeRay
			}
			frame.gamePanel.leftSidePanel.freezeLabel.setText(""+frame.prince.getFreezeRay());
			frame.storePanel.freezeLabel.setText(""+frame.prince.getFreezeRay());
			frame.gamePanel.bottomPanel.typeField.requestFocusInWindow();

		}
		if(e.getSource()==potionButton){
			if(frame.prince.getPotion()>0){
			//use freeze ray if the prince have freeze ray in bag
			frame.prince.usePotion();//use potion
			}	
			frame.gamePanel.leftSidePanel.potionLabel.setText(""+frame.prince.getPotion());
			frame.storePanel.potionLabel.setText(""+frame.prince.getPotion());
			frame.gamePanel.bottomPanel.typeField.requestFocusInWindow();
		}
		if(e.getSource()==t2){
			x++;
			//stop the timer of the game and start a new timer
			if(x==50){
				frame.gamePanel.centerPanel.t.start();
				t2.stop();	
				x=0;
				for(i=0;i<frame.gamePanel.centerPanel.elfCount;i++){
					if(frame.gamePanel.centerPanel.elf[i].getStatus().compareTo("freezed")==0){	
					frame.gamePanel.centerPanel.elf[i].defrost();
					}
				}
			}
		}
	}
}

