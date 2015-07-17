/*
*Author:John Carlo C. Aldave
*CMSC 22 Y-2L
*Project Title: Rumpelstiltskin Typocalypse
*Date Submitted:March 26, 2013
*Program Description:Rumpelstiltskin Typocalypse is a typing game application.
*/

//This class represents a prince

public class Prince{
	private String name;
	private int health;
	private int gold;
	private int potion;
	private int freezeRay;
	private int bomb;
	private int level;
	private int score;
 
	//constructor
	public Prince( String name,int health){
	this.name=name;
	this.health=health;
	}
	//returns the name of the prince
	public String getName(){
	return this.name;
	}
	//returns the health points of the prince
	public int getHealth(){
	return this.health;
	}
	//returns the current gold of the prince
	public int getGold(){
	return this.gold;
	}
	//returns the level of the prince
	public int getLevel(){
	return this.level;
	}
	//returns the number of potion the prince have
	public int getPotion(){
	return this.potion;
	}
	//returns the number of freeze ray the prince have
	public int getFreezeRay(){
	return this.freezeRay;
	}
	//returns the number of bomb the prince have		
	public int getBomb(){
	return this.bomb;
	}
	//returns score of the prince
	public int getScore(){
	return this.score;
	}
	//add the wealth of an elf to the gold of the prince
	public void receiveGold(int wealth){
	gold=gold+wealth;
	}
	//increments bomb by 1, decrements gold by 20
	public void buyBomb(){
	bomb=bomb+1;
	gold=gold-20;
	}
	//decrements bomb of the prince
	public void useBomb(){
	bomb--;
	}
	//increments freeze ray by 1, decrements gold by 10
	public void buyFreezeRay(){
	freezeRay=freezeRay+1;
	gold=gold-10;
	}
	//decrements freeze ray of the prince
	public void useFreezeRay(){
	freezeRay--;
	}
	//increments potion by 1, decrements gold by 15
	public void buyPotion(){
	potion=potion+1;
	gold=gold-15;
	}
	//decrements potion of the prince
	public void usePotion(){
		//add 10 hp to the prince
		int temp=health;
		temp=temp+10;
		if(temp<50){
		health=health+10;
		}
		else{
		health=50;
		}
		potion--;
		}
	//decrements the health of the prince by the damage of the elf
	public void receiveDamage(int damage){
	health=health-damage;
	}
	//increments level of the prince
	public void levelUp(){
	level++;
	}
	//increments the score of the prince
	public void accumulateScore(int value){
	score=score+value;
	}		
}

