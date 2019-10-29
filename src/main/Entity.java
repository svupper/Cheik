package main;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Entity {

	protected String name;
	protected ArrayList<Objet> bag=new ArrayList<Objet>();;
	protected int number_obj=0;
	protected int capacity;
	protected boolean openInv;//variable permettant de savoir si l'inventaire est ouvert ou pas
	
	protected int power;
	protected int hp=100;
	protected boolean alive;
		
	public Entity(){

	}
	
	public Entity(String name,int c){
		this.name=name;
		this.capacity=c;
	}
	/*
	public Entity(String name,int hp){
		this.name=name;
		this.alive=true;
		this.hp=hp;
		
	}
	*/
	public String getName(){
		return name;
	}
		
	public void setName(String n){
		this.name=n;
	
	}
	
	public boolean invIsOpen(){
		return openInv;
	}
	
	public void openInv(){
		openInv=true;
	}
	
	public void closeInv(){
		openInv=false;
	}
	
	public void takeDamage(int dmg){
		hp=hp-dmg;
	}
	
	public void die(){
		if (hp<=0){
			alive=false;
		}
	}
	
	public boolean addObj(Objet o){
		if(number_obj<capacity){
			this.bag.add(o);
			number_obj++;
			return true;
		}else{
			return false;
		}
	}
	
	public Objet removeObj(String name){//le probleme vient dici, cette methode ne fonctionne pas
		Objet o=null;
		//write here method to take off an object from the room
		if(!(number_obj<1)){
			Iterator<Objet> iter=this.bag.iterator();
			while(iter.hasNext()){
				o= iter.next();
				if(o.getName().equalsIgnoreCase(name)){
					
					iter.remove();
					number_obj--;
					
				}
			}
		}else{
			System.out.println("Aucun objet à jeté");
		}
		return o;
	}
	
	public boolean haveItem() {
		// TODO Auto-generated method stub
		if (number_obj<1){
		return true;}
		else{return false;}
		
	}
	
	public boolean isFull() {//fix that, why cant get the info
		// TODO Auto-generated method stub
		if (number_obj>=capacity){
			return true;
		}else{
			return false;
		}
	}
	
	public String bagDesc() {
		// TODO Auto-generated method stub
		String inv="Inventaire : ";

		for (Objet o : bag ) {
			inv += " " + o.getName();
		}
		
		return inv;
	}
	
	public boolean isBagEmpty() {
		// TODO Auto-generated method stub
		if(number_obj==0){return true;}
		return false;
	}
	
	public ArrayList<Objet> getInv(){
		return bag;
	}
	
}
