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

class BottomPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton enterButton;
	JTextField typeField;
	int i,j;
	int cnt;
	String name="";
	Timer time;
	RumpelstiltskinTypocalypse frame;
	//icons fo JButtons
	ImageIcon enter=new ImageIcon ("images/enter1.jpg");
	ImageIcon winner=new ImageIcon ("images/winner.jpg");

	public BottomPanel(RumpelstiltskinTypocalypse frame)
	{
	this.frame=frame;
	this.setBackground(new Color(0,0,10));
	this.setPreferredSize(new Dimension(0, 50));
	//initialize components
	typeField = new JTextField(15);
	enterButton=new JButton(enter);
	enterButton.setBorder(BorderFactory.createEmptyBorder());
	enterButton.setContentAreaFilled(false);
	time = new Timer(100,this);	
	this.add(typeField);  
	this.add(enterButton);
	enterButton.addActionListener(this);//add an actionListener to the JButton
	time.start();//starts the timer

	//add KeyListener(UP key as enter)
	typeField.addKeyListener(new KeyListener(){  
         public void keyPressed(KeyEvent evt)  
         {
            int key = evt.getKeyCode(); 
			//up key 
            if (key == KeyEvent.VK_UP) {
			String typedName = typeField.getText();
			System.out.println(typedName);
			name = ""+typedName;
			typeField.setText("");
			cnt=0;
			}
         }  
		public void keyReleased(KeyEvent e)
		{
		
		}
		public void keyTyped(KeyEvent e)
		{
	
		}
    });
		

	}		

	public void actionPerformed(ActionEvent e){
		if(e.getSource()==enterButton){
			for(i=0;i<frame.gamePanel.centerPanel.elfCount;i++){
			String a=frame.gamePanel.centerPanel.elf[i].getName();
				if(a.compareTo(typeField.getText())==0){
				frame.gamePanel.centerPanel.elf[i].kill();
				frame.prince.accumulateScore(100);

					//stops the game if the name typed is ruMpeLsTilTskiN
					if(typeField.getText().compareTo("ruMpeLsTilTskiN")==0){
					frame.prince.accumulateScore(2000);
					frame.gamePanel.centerPanel.elf[i].bombed();
					frame.gamePanel.centerPanel.t.stop();
					frame.gamePanel.centerPanel.gameTimer.stop();
					//displays you win
					JOptionPane.showMessageDialog(null,"YOU WIN"+"\n"+"YOUR SCORE:"+frame.prince.getScore(),"Congratulations!",JOptionPane.INFORMATION_MESSAGE,winner);	
					//back to main menu				
					frame.cardLayout.show(frame.cardHolder,"Card1");
					frame.nameField.setEnabled(true);
					frame.nameButton.setEnabled(true);
					frame.playButton.setEnabled(false);
					frame.playButton.setIcon(frame.play);
					frame.storeButton.setEnabled(false);
					frame.gamePanel.leftSidePanel.bombButton.setEnabled(true);
					frame.gamePanel.leftSidePanel.freezeButton.setEnabled(true);
					frame.gamePanel.leftSidePanel.potionButton.setEnabled(true);
					frame.gamePanel.centerPanel.gameTime=0;		
					//make all the elves disappear
						for(j=0;j<frame.gamePanel.centerPanel.elfCount;j++){	
							frame.gamePanel.centerPanel.elf[j].dissolve();
						}			
					}		
				typeField.setText("");
				}
			}
		}
		if(e.getSource()==time){
			for(i=0;i<frame.gamePanel.centerPanel.elfCount;i++){
			String a=frame.gamePanel.centerPanel.elf[i].getName();
			
				if(a.compareTo(name)==0){		
					if(cnt==0){	
						frame.prince.accumulateScore(100);	
						//stops the game if the name typed is ruMpeLsTilTskiN
						if(name.compareTo("ruMpeLsTilTskiN")==0){
						frame.prince.accumulateScore(2000);
						frame.gamePanel.centerPanel.elf[i].bombed();
						frame.gamePanel.centerPanel.t.stop();
						frame.gamePanel.centerPanel.gameTimer.stop();
						//displays you win
						JOptionPane.showMessageDialog(null,"YOU WIN"+"\n"+"YOUR SCORE:"+frame.prince.getScore(),"Congratulations!",JOptionPane.INFORMATION_MESSAGE,winner);	
						//back to main menu				
						frame.cardLayout.show(frame.cardHolder,"Card1");
						frame.nameField.setEnabled(true);
						frame.nameButton.setEnabled(true);
						frame.playButton.setEnabled(false);
						frame.playButton.setIcon(frame.play);
						frame.storeButton.setEnabled(false);
						frame.gamePanel.leftSidePanel.bombButton.setEnabled(true);
						frame.gamePanel.leftSidePanel.freezeButton.setEnabled(true);
						frame.gamePanel.leftSidePanel.potionButton.setEnabled(true);
						frame.gamePanel.centerPanel.gameTime=0;
						//make all the elves disappear
							for(j=0;j<frame.gamePanel.centerPanel.elfCount;j++){	
								frame.gamePanel.centerPanel.elf[j].dissolve();
							}
						}
					}
					frame.gamePanel.centerPanel.elf[i].kill();//kill the elf if the name typed is correct			
					cnt++;			
				}
			}		
		}
	}
}
