/*
*Author:John Carlo C. Aldave
*CMSC 22 Y-2L
*Project Title: Rumpelstiltskin Typocalypse
*Date Submitted:March 26, 2013
*Program Description:Rumpelstiltskin Typocalypse is a typing game application.
*/
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.io.File;

class CenterPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//timer objects
	Timer t;
	Timer gameTimer;
	
	//random generator object
	Random generator=new Random();

	//elf object
	Elf[] elf;
	int speed,distance,wealth,position;
	String status;

	//image objects
	Image elfImage;
	Image coin;
	Image explosion;
	Image frozen;
	Image moon;
	Image fireworks;
	Image night;
	Image ground;
	Image start;
	Image levelCleared;
	Image healthBar;
	Image lightning1;
	Image lightning2;
	Image lightning3;
	Image skull;
	Image tree1;
	Image tree2;
	Image tree3;
	
	//icon for JButtons
	ImageIcon goldCoin=new ImageIcon ("images/Coin-04-june.gif");
	ImageIcon play=new ImageIcon ("images/play.png");
	ImageIcon levelUpIcon=new ImageIcon ("images/levelup.jpeg");
	ImageIcon gameOver=new ImageIcon ("images/gameover.png");
	ImageIcon elfIcon=new ImageIcon ("images/Druid-01-june.gif");
	
	int elfCount;//number of elves
	int i,j,k=0;//counters
	int[] count;
	int[] x;
	JButton[] goldButton;//gold button

	String[] names;
	String elfName;
	int nameGenerator;
	Scanner fileInput;
	int gameTime=0;
	int level;
	int damage;
	int hp;

	RumpelstiltskinTypocalypse frame;
	//constructor
	public CenterPanel(RumpelstiltskinTypocalypse frame){
		
		this.frame=frame;
		this.setBackground(new Color(10,100,250));
		this.setPreferredSize(new Dimension(0, 0));
		this.setLayout(null);

		//initialize timer
		t = new Timer(100,this);
		gameTimer = new Timer(100,this);

		elfCount=5;//number of elves

		elf = new Elf[elfCount];//create n elves

		try{
		fileInput= new Scanner( new File("names.txt") );//reads the file
		}
		catch(Exception e){}

		names = new String[3380];//create array of names
		while ( fileInput.hasNext() ) {
		names[k] = fileInput.nextLine();
		names[k]=names[k].toLowerCase();//copy the contents of the file to the array
		k++;
		}
		
		x = new int[elfCount];	
		count = new int[elfCount];
		goldButton=new JButton[elfCount];

		//initialize image objects
		elfImage = Toolkit.getDefaultToolkit().getImage("images/Druid-01-june.gif");
		healthBar = Toolkit.getDefaultToolkit().getImage("images/healthbar.png");
		coin = Toolkit.getDefaultToolkit().getImage("images/Coin-04-june.gif");
		explosion = Toolkit.getDefaultToolkit().getImage("images/explosion1.gif");
		frozen = Toolkit.getDefaultToolkit().getImage("images/freezedelf3.png");
		moon = Toolkit.getDefaultToolkit().getImage("images/Moon-01-june.gif");
		fireworks = Toolkit.getDefaultToolkit().getImage("images/Fireworks.gif");
		night = Toolkit.getDefaultToolkit().getImage("images/night.jpg");
		ground = Toolkit.getDefaultToolkit().getImage("images/ground7.png");
		start = Toolkit.getDefaultToolkit().getImage("start.png");
		levelCleared = Toolkit.getDefaultToolkit().getImage("images/levelcleared.png");
		lightning1 = Toolkit.getDefaultToolkit().getImage("images/Storm-03-june.gif");
		lightning2 = Toolkit.getDefaultToolkit().getImage("images/Storm-07-june.gif");
		lightning3 = Toolkit.getDefaultToolkit().getImage("images/Storm-09-june.gif");
		skull = Toolkit.getDefaultToolkit().getImage("images/SkullBones-01-june.gif");
		tree1 = Toolkit.getDefaultToolkit().getImage("images/tree.png");
		tree2 = Toolkit.getDefaultToolkit().getImage("images/tree2.png");
		tree3 = Toolkit.getDefaultToolkit().getImage("images/tree3.png");

		for(i=0;i<elfCount;i++){
			nameGenerator = generator.nextInt(3380);//generate random index
			elfName=names[nameGenerator];
			//checks the number of letters of a name
			
			while( (elfName.length()!=level+3)){
			nameGenerator = generator.nextInt(3380);
			elfName=names[nameGenerator];
			}
			speed=generator.nextInt(3)+5;
			distance=generator.nextInt(250)+1000;
			wealth=generator.nextInt(3)+1+level;
			damage=generator.nextInt(5)+3;
			status="alive";
			position=generator.nextInt(350)+30;
			//create an elf object
			elf[i]=new Elf(elfName,status,speed,distance,wealth,position,damage);
			//create gold Buttons
			goldButton[i]=new JButton(goldCoin);
			goldButton[i].addActionListener(this);
			goldButton[i].setBorder(BorderFactory.createEmptyBorder());//deletes the border of the JButton
			goldButton[i].setContentAreaFilled(false);	
			count[i]=0;
		}
	
	}		
	/**
	*	Override the paintComponent method for drawing images on the JPanel
	*/
	public void paintComponent(Graphics g){
	
		Graphics2D ga = (Graphics2D)g;

		super.paintComponent(g);
		//draw background images
		g.drawImage(night,0,0,this);
		g.drawImage(moon,gameTime,0,this);
		g.drawImage(ground,950,30,this);
		g.drawImage(ground,0,30,this);

		//draw background of health bar
		g.setColor(Color.RED); 
		g.fillRect(0,0,5,700);
		g.setColor(Color.BLACK); 
		g.fillRect(30,475,200,30);
		g.setColor(Color.BLACK); 
		g.drawRect(30,475,200,30);
	
		//draw the start image
		if(gameTime<30&&gameTime>0){
		g.drawImage(start,300,200,this);
		}

		//draw the health bar
		try{
			hp=frame.prince.getHealth();	
			level=frame.prince.getLevel();	
			if(hp>30){
			g.setColor(Color.GREEN);}
			else if(hp<20){
			g.drawImage(skull,250,450,this);
			g.setColor(Color.RED);}
			else{
			g.setColor(Color.YELLOW);
			}
			g.fillRect(30,475,frame.prince.getHealth()*4,35);
		}
		catch(Exception ex){}
		g.drawImage(healthBar,10,460,this);
		//set the font and color of health string
		ga.setPaint(Color.blue);  
		ga.setFont(new Font("TimesRoman", Font.BOLD,  14)); 

		//display 0 if health<0
		int health;
		health=frame.prince.getHealth();
		if(health<=0){
		health=0;
		}
		//draw the health of the prince /50
		ga.drawString(health+"/50",110,495);

		for(i=0;i<elfCount;i++){
			//if status of elf is alive, draw the walking elf image with the name on top
			if(elf[i].getStatus().compareTo("alive")==0){
				g.drawImage(elfImage,elf[i].getDistance(),elf[i].getPosition(),this);
				ga.setPaint(Color.white);  
				ga.setFont(new Font("TimesRoman", Font.BOLD,  14));  
				ga.drawString(elf[i].getName(),elf[i].getDistance(),elf[i].getPosition());
			}
			
			//if status of elf is alive, draw the walking elf image with the name on top
			else if(elf[i].getStatus().compareTo("freezed")==0){
				
				x[i]=elf[i].getDistance();
				g.drawImage(frozen,x[i],elf[i].getPosition(),this);	
				ga.setPaint(Color.white);  
		  		ga.setFont(new Font("TimesRoman", Font.BOLD,  14));  
				ga.drawString(elf[i].getName(),elf[i].getDistance(),elf[i].getPosition());
				
			}
			//if status of bombed is alive, draw the xplosion animation
			else if(elf[i].getStatus().compareTo("bombed")==0){
				//stores the distance of the elf where it is bombed
				if(count[i]==0){
				x[i]=elf[i].getDistance();
				}
				count[i]++;
				//draw the explosion while the current distance misnus the previous in not equal 50 
				if(x[i]-elf[i].getDistance()<50){
				g.drawImage(explosion,x[i],elf[i].getPosition(),this);	
				}
	
			}
			//draw nothing
			else if(elf[i].getStatus().compareTo("dissolved")==0){
				g.drawImage(null,x[i],elf[i].getPosition(),this);
			}
			//display the gold button
			else{
				//stores the distance of the elf where it is killed	
				if(count[i]==0){
					x[i]=elf[i].getDistance();
					this.add(goldButton[i]);
					goldButton[i].setVisible(true);
				}
				count[i]++;
				goldButton[i].setBounds(x[i],elf[i].getPosition(),50,50);
			}
		}

		for(i=0;i<elfCount;i++){
			
			if(elf[i].getDistance()<0){
			//if the elf reach the end and the status is alive, inflict damage on the prince
				if(elf[i].getStatus().compareTo("alive")==0){
				frame.prince.receiveDamage(elf[i].getDamage());
				}
			//create a new elf
			goldButton[i].setVisible(false);
			nameGenerator = generator.nextInt(3380);
			elfName=names[nameGenerator];
			
			while( (elfName.length()!=(level*2)+1&&elfName.length()!=(level*2)+2) ){
			nameGenerator = generator.nextInt(3380);
			elfName=names[nameGenerator];	
			}
		
			speed=generator.nextInt(3)+4+level;
			distance=generator.nextInt(250)+1000;
			position=generator.nextInt(350)+30;
			wealth=generator.nextInt(3)+1+level;
			damage=generator.nextInt(5)+3+(level*2);
			status="alive";
			elf[i]=new Elf(elfName,status,speed,distance,wealth,position,damage);
			count[i]=0;
			


			}
		}
		//display lightning images on the third level
		if(gameTime>890&&level==3){
		g.drawImage(lightning1,0,0,this);
		g.drawImage(lightning1,800,0,this);
		g.drawImage(lightning2,0,0,this);
		g.drawImage(lightning2,600,0,this);
		g.drawImage(lightning3,elf[0].getDistance()-50,100,this);
		}

		//if the moon image reach the end, stop the timer of the game 
		if(gameTime==1100){
		gameTimer.stop();
		
		for(i=0;i<elfCount;i++){
			elf[i].dissolve();
			goldButton[i].setVisible(false);
		}

		t.stop();
		//level up 
		frame.prince.levelUp();
		//reset the game timer
		gameTime=0;
		}

		//draw tree images depending on the current level
		if(level==2){
			g.drawImage(tree1,850,50,this);
			g.drawImage(tree2,850,300,this);
		}
		if(level==3){
			g.drawImage(tree1,850,50,this);
			g.drawImage(tree2,850,300,this);
			g.drawImage(tree1,800,100,this);
			g.drawImage(tree2,800,200,this);
		}
			g.drawImage(tree1,900,25,this);
			g.drawImage(tree1,900,30,this);
			g.drawImage(tree2,900,100,this);
			g.drawImage(tree3,900,200,this);
			g.drawImage(tree3,900,300,this);
			g.drawImage(tree3,900,400,this);

		//show the master on the third level
		if(gameTime==890&&level==3){
			for(i=0;i<elfCount;i++){
				elf[i].dissolve();	
			}
			elf[0]=new Elf("ruMpeLsTilTskiN","alive",5,1000,100,200,50);

			for(i=1;i<elfCount;i++){
				elf[i]=new Elf("a","alive",6,2000,0,1000,0);
			}
		}
	
	}
	
	public void actionPerformed(ActionEvent e){
		for(i=0;i<elfCount;i++){
			//add gold and score to the prince if the gold button is clicked
			if(e.getSource()==goldButton[i]){
				goldButton[i].setVisible(false);	
				frame.prince.receiveGold(elf[i].getWealth());
				frame.prince.accumulateScore(25);
				frame.gamePanel.leftSidePanel.goldLabel.setText(""+frame.prince.getGold());
				//maintain the focus on the JTextField on the BottomPanel
				frame.gamePanel.bottomPanel.typeField.requestFocusInWindow();
			}
		}
		//make the elf move while the timer is running
		if(e.getSource()==t){
			this.repaint();
			for(i=0;i<elfCount;i++){
			elf[i].move();	
			}
			
		}
		if(e.getSource()==gameTimer){
			gameTime++;
			frame.storeButton.setEnabled(false);
			//display the master on the third level
			if(gameTime==890&&level==3){	
				t.stop();
				gameTimer.stop();
				frame.gamePanel.leftSidePanel.bombButton.setEnabled(false);
				frame.gamePanel.leftSidePanel.freezeButton.setEnabled(false);
				frame.gamePanel.leftSidePanel.potionButton.setEnabled(false);
				JOptionPane.showMessageDialog(null,"Oh it is the third night! Rumpelstiltskin is coming."+"\nKill him before he reach the castle,or else your game will be over"+"\nNOTE:You are not allowed to use any item.","WARNING",JOptionPane.INFORMATION_MESSAGE,elfIcon);	
				t.start();
				gameTimer.start();
			}
			//show the attributes of the elf after a level 
			if(gameTime==1100){
				frame.gamePanel.bottomPanel.typeField.setEnabled(false);
				frame.gamePanel.bottomPanel.enterButton.setEnabled(false);	
				frame.prince.accumulateScore(1000);
				JOptionPane.showMessageDialog(null,"Score:"+frame.prince.getScore()+"\n"+"Gold:"+frame.prince.getGold()+"\n"+"Health:"+frame.prince.getHealth(),"Good job!",JOptionPane.INFORMATION_MESSAGE,levelUpIcon);	
				frame.cardLayout.show(frame.cardHolder,"Card1");	
				frame.storeButton.setEnabled(true);
			}
			//displays a GAMEOVER message and stops the game
			if(hp<=0){
				t.stop();
				gameTimer.stop();
				JOptionPane.showMessageDialog(null,"YOU LOSE"+"\nYOUR SCORE:"+frame.prince.getScore(),"Sorry!",JOptionPane.INFORMATION_MESSAGE,gameOver);
				frame.cardLayout.show(frame.cardHolder,"Card1");
				frame.nameField.setEnabled(true);
				frame.nameButton.setEnabled(true);
				frame.playButton.setEnabled(false);
				frame.playButton.setIcon(play);
				frame.storeButton.setEnabled(false);
				frame.gamePanel.leftSidePanel.bombButton.setEnabled(true);
				frame.gamePanel.leftSidePanel.freezeButton.setEnabled(true);
				frame.gamePanel.leftSidePanel.potionButton.setEnabled(true);
				for(i=0;i<elfCount;i++){	
					elf[i].dissolve();
					gameTime=0;
				}
			}
			frame.gamePanel.leftSidePanel.levelLabel.setText(""+frame.prince.getLevel());
		}
	}
}
