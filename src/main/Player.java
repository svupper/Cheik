package main;

import java.util.ArrayList;
import java.util.Iterator;


public class Player extends Entity{
	
	private ArrayList<Objet> bag=new ArrayList<Objet>();;
	private int number_obj;
	private int capacity;
	
	private ArrayList<Chien> pets=new ArrayList<Chien>();;
	private int maxCompagnie;
	private int pet_count;
		
	public Player(){}
	
	public Player(String name){
		super(name);
		}
	
	public Player(String name,int maxBag,int maxPet){
		super(name);

		this.capacity=maxBag;
		this.maxCompagnie=maxPet;

		
	}
	
	public void addPet(Chien p){
		if(pet_count<maxCompagnie){
			this.pets.add(p);
			pet_count++;
		}
	}
	
	public void removePet(String name){		

		//write here method to take off an object from the room
		if(!(pet_count<1)){
			Iterator<Chien> iter=this.pets.iterator();
			while(iter.hasNext()){
				Chien c= iter.next();
				if(c.getName().equalsIgnoreCase(name)){
					
					iter.remove();
					pet_count--;
				}
			}
		}else{
			System.out.println("Aucun animal à relaché");
		}
	}
	
	
	
	
	
	//put here method for knowing if there is object in bag and if there is pet following
	//try to implement a method to track pet move as ordered in the exercise
	
	public boolean addObj(Objet o){
		if(number_obj<capacity){
			this.bag.add(o);
			number_obj++;
			return true;
		}else{
			return false;
		}
	}
	
	
	public void removeObj(String name){//le probleme vient dici, cette methode ne fonctionne pas

		//write here method to take off an object from the room
		if(!(number_obj<1)){
			Iterator<Objet> iter=this.bag.iterator();
			while(iter.hasNext()){
				Objet o= iter.next();
				if(o.getName().equalsIgnoreCase(name)){
					
					iter.remove();
					number_obj++;
				}
			}
		}else{
			System.out.println("Aucun objet à jeté");
		}
	}
	
		
	
	public void increasePower(int ammount){
		this.power=power+ammount;
	}
	
	public void reducePower(int amount){
		this.power=power-amount;
	}

	public boolean noPet() {
		// TODO Auto-generated method stub
		if (pet_count<1){
		return true;}
		else{return false;}
		
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
	
	public boolean isMaxPets(){
		if (pet_count>=maxCompagnie){
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

	public String petsDesc(){
		String pet="Compagnions attrapés : ";
		
		for (Chien c : pets ) {
			pet += " " + c.getName();
		}
		
		return pet;
	}
	
	public ArrayList<Chien> petsList(){ 
		return pets;
	}
}
