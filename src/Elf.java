//This class represents an elf

public class Elf{
	private String name;
	private String status;
	private int speed;
	private int distance;
	private int wealth;
	private int position;
	private int damage;

 	//constructor
	public Elf( String name,String status,int speed,int distance,int wealth,int position,int damage){
	this.name=name;
	this.status=status;
	this.speed=speed;
	this.distance=distance;
	this.wealth=wealth;
	this.position=position;
	this.damage=damage;
	}
	//returns the name of the elf
	public String getName(){
	return this.name;
	}
	//returns the status of the elf
	public String getStatus(){
	return this.status;
	}
	//returns the speed of the elf
	public int getSpeed(){
	return this.speed;
	}
	//returns the distance of the elf
	public int getDistance(){
	return this.distance;
	}
	//returns the wealth of the elf
	public int getWealth(){
	return this.wealth;
	}
	//returns the y coordinate of the elf
	public int getPosition(){
	return this.position;
	}
	//returns the damage of the elf
	public int getDamage(){
	return this.damage;
	}
	//decrements the distance of the elf from the castle
	public void move(){
	distance=distance-speed;
	}	
	//change the status of the elf to "dead"
	public void kill(){
	status="dead";
	}
	//change the status of the elf to "bombed"
	public void bombed(){
	status="bombed";
	}
	//change the status of the elf to "freezed"
	public void freeze(){
	status="freezed";
	}
	//change the status of the elf to "alive"
	public void defrost(){
	status="alive";
	}
	//change the status of the elf to "dissolved"
	public void dissolve(){
	status="dissolved";
	}
}

