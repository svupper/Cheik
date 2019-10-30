package main;

public class Objet {

	private String name;
	public int power;
	public int weight;
	private int max=11;
	private int min=1;
	
	public Objet(){
		this.name=Cave.randomObj().name();
		this.weight=(int)(Math.random()*((max-min)+1))+min;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public Objet(String name){
		this.name=name;
	}
	
	public String getName(){
		return name;
	}
	//create here a method to to randomize element in enum type and attribute
	//it as a string to name
	/*public enum Modes {
    some-really-long-string,
    mode1,
    mode2,
    mode3
    
}
*/
	//try to use String name = Modes.mode1.name(); and
	//Modes mode = Modes.valueOf(name);
	//in the main method to check what valueOf return
}
