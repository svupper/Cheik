package zorkfr;
import main.Chien;
import main.Objet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 *  Une piece dans un jeu d'aventure. <p>
 *
 *  Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 *  texte.</p> <p>
 *
 *  Une "Piece" represente un des lieux dans lesquels se déroule l'action du
 *  jeu. Elle est reliée a au plus quatre autres "Piece" par des sorties. Les
 *  sorties sont étiquettées "nord", "est", "sud", "ouest". Pour chaque
 *  direction, la "Piece" possède une référence sur la piece voisine ou null
 *  s'il n'y a pas de sortie dans cette direction.</p>
 *
 * @author     Michael Kolling
 * @author     Marc Champesme (pour la traduction francaise)
 * @version    1.2
 * @since      August 2000
 */

public class Piece {
 	private String description;
 	private int countPets;
 	private int countObj;
	// mémorise les sorties de cette piece.
	private Map<Direction, Piece> sorties;
	private List<Chien> pets;
	private List<Objet> obj;
	private int max=1;
	private int min = 0;



	/**
	 *  Initialise une piece décrite par la chaine de caractères spécifiée.
	 *  Initialement, cette piece ne possède aucune sortie. La description fournie
	 *  est une courte phrase comme "la bibliothèque" ou "la salle de TP".
	 *
	 * @param  description  Description de la piece.
	 */
	public Piece(String description) {
		this.description = description;
		sorties = new HashMap<Direction, Piece>();
		countPets = (int)(Math.random()*((max-min)+1))+min;
		countObj = (int)(Math.random()*((max-min)+1))+min;
		pets = new ArrayList<Chien>(countPets);
		obj = new ArrayList<Objet>(countObj);
		
		for(int i=1;i<= countPets;i++){
			pets.add(new Chien());
		}
		
		for(int i=1;i<= countObj;i++){
			obj.add(new Objet());
		}
		//create a library of objects and a randomize method to her from it. the heir class will have a 
		//randomize method to give the nature of the object.
	}


	/**
	 *  Définie les sorties de cette piece. A chaque direction correspond ou bien
	 *  une piece ou bien la valeur null signifiant qu'il n'y a pas de sortie dans
	 *  cette direction.
	 *
	 * @param  nord   La sortie nord
	 * @param  est    La sortie est
	 * @param  sud    La sortie sud
	 * @param  ouest  La sortie ouest
	 */
	public void setSorties(Piece nord, Piece est, Piece sud, Piece ouest) {
		if (nord != null) {
			sorties.put(Direction.NORD, nord);
		}
		if (est != null) {
			sorties.put(Direction.EST, est);
		}
		if (sud != null) {
			sorties.put(Direction.SUD, sud);
		}
		if (ouest != null) {
			sorties.put(Direction.OUEST, ouest);
		}
	}


	/**
	 *  Renvoie la description de cette piece (i.e. la description spécifiée lors
	 *  de la création de cette instance).
	 *
	 * @return    Description de cette piece
	 */
	public String descriptionCourte() {
		return description;
	}


	/**
	 *  Renvoie une description de cette piece mentionant ses sorties et
	 *  directement formatée pour affichage, de la forme: <pre>
	 *  Vous etes dans la bibliothèque.
	 *  Sorties: nord ouest</pre> Cette description utilise la chaine de caractères
	 *  renvoyée par la méthode descriptionSorties pour décrire les sorties de
	 *  cette piece.
	 *
	 * @return    Description affichable de cette piece
	 */
	public String descriptionLongue() {
		return "Vous etes dans " + description + ".\n" + descriptionSorties() + ".\n" + objDesc() + ".\n" + petsDesc();
	}


	/**
	 *  Renvoie une description des sorties de cette piece, de la forme: <pre>
	 *  Sorties: nord ouest</pre> Cette description est utilisée dans la
	 *  description longue d'une piece.
	 *
	 * @return    Une description des sorties de cette pièce.
	 */
	public String descriptionSorties() {
		String resulString = "Sorties:";
		for (Direction sortie :  sorties.keySet()) {
			resulString += " " + sortie;
		}
		return resulString;
	}
	
	
	/**
	 * Give the names of the object in the room
	 * @return String
	 */
	public String objDesc(){
		String objs="Objets presents : ";
		for (Objet objet :  obj) {
			objs += " " + objet.getName();
		}
		return objs;
	}
	
	/**
	 * Give the names of pets in the room
	 * @return String
	 */
	public String petsDesc(){
		
		String c="Chien present :";
		c += " " + countPets;
		return c;
	}
	
	public Chien pickUpPet(){
		//if 
		Chien c=new Chien();
		//write here method to take off a pet from the room
		if(!(countPets<1)){
			countPets--;
		}
		return c;
	}
	
	/*
	public Objet pickUpItem(){
		//if 
		Objet o=null;
		//write here method to take off a pet from the room
		if(!(countObj<1)){
			o=this.obj.get(obj.size());
			this.obj.remove(obj.size());
			countObj--;
		}
		return o;
	}
	*/
	
	public Objet pickUpItem(String name){ //le probleme vient dici, cette methode ne fonctionne pas
		Objet r = null;
		//write here method to take off an object from the room
		if(!(countObj<1)){
			Iterator<Objet> iter=this.obj.iterator();
			while(iter.hasNext()){
				Objet o= iter.next();
				if(o.getName().equalsIgnoreCase(name)){
					r=o;
					iter.remove();
				}
			}
		}else{
			System.out.println("il n'y pas d'objet ici");
		}
		return r;
	}
	
	//public String create here a method to describe object in the room
	//and an other one to describe pet in
	//the room

	/**
	 *  Renvoie la piece atteinte lorsque l'on se déplace a partir de cette piece
	 *  dans la direction spécifiée. Si cette piece ne possède aucune sortie dans cette direction,
	 *  renvoie null.
	 *
	 * @param  direction  La direction dans laquelle on souhaite se déplacer
	 * @return            Piece atteinte lorsque l'on se déplace dans la direction
	 *      spécifiée ou null.
	 */
	public Piece pieceSuivante(Direction d) {
		return sorties.get(d);
	}
	
	public boolean isObjHere() {
		// TODO Auto-generated method stub
		if(countObj!=0){return true;}else{
		return false;}
	}

	public boolean isPetHere() {
		// TODO Auto-generated method stub
		if(countPets!=0){return true;}else{
		return false;}
	}
}

