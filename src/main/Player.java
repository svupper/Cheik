package main;

import java.util.ArrayList;
import java.util.List;

public class Player extends Entity{
	
	private List<Objet> bag;
	private int number_obj;
	private int capacity;
	
	private List<Chien> pets;
	private int maxCompagnie;
	private int pet_count;
		
	public Player(){}
	
	public Player(String name){
		super(name);
		}
	
	public Player(String name,int maxBag,int maxPet){
		super(name);
		this.number_obj=maxBag;
		this.pet_count=maxPet;
		bag = new ArrayList<Objet>();
		pets=new ArrayList<Chien>();
		this.capacity=10;
		this.maxCompagnie=5;
		
	}
	
	public void addPet(Chien p){
		if(pet_count<maxCompagnie){
			this.pets.add(p);
			pet_count++;
		}
	}
	
	public void removePet(String relache){		
		if(pet_count>0){
			this.pets.remove(relache);
			pet_count--;
		}
	}
	
	//put here method for knowing if there is object in bag and if there is pet following
	//try to implement a method to track pet move as ordered in the exercise
	
	public void addObj(Objet o){
		if(number_obj<capacity){
			this.bag.add(o);
		}
	}
	
	public void removeObj(String name){
		Objet o = new Objet(name);
		if(number_obj>0){
			this.bag.remove(o);
		}
	}
		
	
	public void increasePower(int ammount){
		this.power=power+ammount;
	}
	
	public void reducePower(int amount){
		this.power=power-amount;
	}

	public boolean havePet() {
		// TODO Auto-generated method stub
		if (pet_count!=0){
		return true;}
		else{return false;}
		
	}
	
	public boolean haveItem() {
		// TODO Auto-generated method stub
		if (number_obj!=0){
		return true;}
		else{return false;}
		
	}


	public boolean isFull() {//fix that, why cant get the info
		// TODO Auto-generated method stub
		if (number_obj<=capacity){
			return false;
		}else{
			return true;
		}
	}
	
}
