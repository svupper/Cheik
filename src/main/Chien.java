package main;

public class Chien extends Entity {

	private boolean captured=false;
	//implementé ici un inventaire (copié collé de l'entité player ou herité de la classe entity qui possede un inventaire)
	public Chien(String name){
		super(name);
	}
	
	public Chien(){}
	
	public boolean isCaptured(){
		return captured;
	}
	
	public void gotcha(){
		this.captured=true;
	}
	
}
//create a class entity with power value 
//objet and Animaux heritaded from it

//Add the possibility to follow the player or not,
//by not implementing it we follow the player by default
//try to implement the possibility to miss a dog when switching room
//the dog became lost and we have to retrive it.

//try to give random name to dog also

//give the possibility to give a name to the dog

//create a command to capture a dog
//give dog a random number corresponding at it 's power
//every time the player try to capture a dog, it use a probability based on its total object power
//and pet power.
//object can increase power like the sword or reduce power like the rock or any other
//useless object