package main;

public abstract class Entity {

	protected String name;
	
	protected int power;
	protected int hp=100;
	protected boolean alive;
		
	public Entity(){

	}
	
	public Entity(String name){
		this.name=name;
	}
	
	public Entity(String name,int hp){
		this.name=name;
		this.alive=true;
		this.hp=hp;
	}
	
	public String getName(){
		return name;
	}
		
	public void setName(String n){
		this.name=n;
	
	}
	
	public void takeDamage(int dmg){
		hp=hp-dmg;
	}
	
	public void die(){
		if (hp<=0){
			alive=false;
		}
	}
}
