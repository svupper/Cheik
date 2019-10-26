/**
 *  Classe principale du jeu "Zork". <p>
 *
 *  Zork est un jeu d'aventure très rudimentaire avec une interface en mode
 *  texte: les joueurs peuvent juste se déplacer parmi les différentes pieces.
 *  Ce jeu nécessite vraiment d'etre enrichi pour devenir intéressant!</p> <p>
 *
 *  Pour jouer a ce jeu, créer une instance de cette classe et appeler sa
 *  méthode "jouer". </p> <p>
 *
 *  Cette classe crée et initialise des instances de toutes les autres classes:
 *  elle crée toutes les pieces, crée l'analyseur syntaxique et démarre le jeu.
 *  Elle se charge aussi d'exécuter les commandes que lui renvoie l'analyseur
 *  syntaxique.</p>
 *
 * @author     Michael Kolling
 * @author     Marc Champesme (pour la traduction francaise)
 * @version    1.2
 * @since      March 2000
 */
package zorkfr;

import java.util.ArrayList;
import java.util.List;

import main.Chien;
import main.Objet;
import main.Player;

public class Jeu {
	private AnalyseurSyntaxique analyseurSyntaxique;
	private ArrayList<Piece> maze=new ArrayList<Piece>();;
	private Piece pieceCourante;
	private Player p;
	private int indiceP;


	/**
	 *  Crée le jeu et initialise la carte du jeu (i.e. les pièces).
	 */
	public Jeu() {
		creerPieces();
		analyseurSyntaxique = new AnalyseurSyntaxique();
		p=new Player(null,3,3);
		
	}


	/**
	 *  Crée toutes les pieces et relie leurs sorties les unes aux autres.
	 */
	public void creerPieces() {
		Piece dehors;
		Piece salleTD;
		Piece taverne;
		Piece batimentC;
		Piece burreau;

		// création des pieces
		dehors = new Piece("devant le batiment C");
		salleTD = new Piece("une salle de TD dans le batiment G");
		taverne = new Piece("la taverne");
		batimentC = new Piece("le batiment C");
		burreau = new Piece("le secrétariat");

		// initialise les sorties des pieces
		dehors.setSorties(null, salleTD, batimentC, taverne);
		salleTD.setSorties(null, null, null, dehors);
		taverne.setSorties(null, dehors, null, null);
		batimentC.setSorties(dehors, burreau, null, null);
		burreau.setSorties(null, null, null, batimentC);

		
		//assembler toutes les pieces dans le labyrinte
		maze.add(dehors);
		maze.add(salleTD);
		maze.add(taverne);
		maze.add(batimentC);
		maze.add(burreau);
		
		// le jeu commence dehors
		pieceCourante = dehors;
		indiceP = maze.indexOf(pieceCourante);
		
	}


	/**
	 *  Pour lancer le jeu. Boucle jusqu'a la fin du jeu.
	 */
	public void jouer() {
		afficherMsgBienvennue();
		String name=analyseurSyntaxique.getName();
		p.setName(name);
		
		System.out.println("Bonjour � toi, "+name+" !");
		System.out.println("Que voullez vous faire ?");
		// Entrée dans la boucle principale du jeu. Cette boucle lit
		// et exécute les commandes entrées par l'utilisateur, jusqu'a
		// ce que la commande choisie soit la commande "quitter"
		boolean termine = false;
		while (!termine) {
			Commande commande = analyseurSyntaxique.getCommande();
			termine = traiterCommande(commande);
		}
		System.out.println("Merci d'avoir jouer.  Au revoir.");
	}


	/**
	 *  Affiche le message d'accueil pour le joueur.
	 */
	public void afficherMsgBienvennue() {
		System.out.println();
		System.out.println("Bienvennue dans le dungeon !");
		
		System.out.println("Tapez 'aide' si vous avez besoin d'aide.");
		System.out.println();
		System.out.println(pieceCourante.descriptionLongue());
		System.out.println("Entrez votre nom : ");
		System.out.print(">");
	}

	public void updateMaze(Piece pc){
		int iP = maze.indexOf(pc);
		maze.set(iP, pc);
	}

	/**
	 *  Exécute la commande spécifiée. Si cette commande termine le jeu, la valeur
	 *  true est renvoyée, sinon false est renvoyée
	 *
	 * @param  commande  La commande a exécuter
	 * @return           true si cette commande termine le jeu ; false sinon.
	 */
	public boolean traiterCommande(Commande commande) {
		if (commande.estInconnue()) {
			System.out.println("Je ne comprends pas ce que vous voulez...");
			return false;
		}

		String motCommande = commande.getMotCommande();//update command list with catch,relacher...
		if (motCommande.equals("aide")) {
			afficherAide();
		} else if (motCommande.equals("aller")) {
			deplacerVersAutrePiece(commande);
		} else if (motCommande.equals("catch")) {
			capture();
		} else if (motCommande.equals("relacher")) {
			freePet();
		} else if (motCommande.equals("drop")) {
			throwItem();
		} else if (motCommande.equals("ramasser")) {
			pickUpItem();
		} else if (motCommande.equals("inventaire")) {	
			afficherInventaire();
			afficherAnimaux();
		} else if (motCommande.equals("quitter")) {
			if (commande.aSecondMot()) {
				System.out.println("Quitter quoi ?");
			} else {
				return true;
			}
		}
		return false;
	}


	// implementations des commandes utilisateur:

	/**
	 *  Affichage de l'aide. Affiche notament la liste des commandes utilisables.
	 */
	public void afficherAide() {
		System.out.println("Vous etes perdu. Vous etes seul. Vous errez");
		System.out.println("sur le campus de l'Université Paris 13.");
		System.out.println();
		System.out.println("Les commandes reconnues sont:");
		analyseurSyntaxique.afficherToutesLesCommandes();
	}
	
	public void afficherInventaire(){
		if(!p.isBagEmpty()){
			System.out.println(p.bagDesc());
		}else{
		System.out.println("Le sac est vide");
		}
	}
	
	public void afficherAnimaux(){
		if(!p.noPet()){
			System.out.println(p.petsDesc());
		}else{
		System.out.println("pas de compagnion");
		}
	}
	
	/**
	 *  Tente d'aller dans la direction spécifiée par la commande. Si la piece
	 *  courante possède une sortie dans cette direction, la piece correspondant a
	 *  cette sortie devient la piece courante, dans les autres cas affiche un
	 *  message d'erreur.
	 *
	 * @param  commande  Commande dont le second mot spécifie la direction a suivre
	 */
	public void deplacerVersAutrePiece(Commande commande) {
		if (!commande.aSecondMot()) {
			// si la commande ne contient pas de second mot, nous ne
			// savons pas ou aller..
			System.out.println("Aller où ?");
			return;
		}

		String direction = commande.getSecondMot();
		Direction d = null;
		try {
		    d = Direction.valueOf(direction);
		  } catch (IllegalArgumentException e) {
		      System.out.println("Pour indiquer une direction entrez une des chaînes de caractères suivantes:");
		      for (Direction dok : Direction.values()) {
		          System.out.println("-> \"" + dok + "\"");
		      }
		      return;
		  }

		// Tentative d'aller dans la direction indiquée.
		Piece pieceSuivante = pieceCourante.pieceSuivante(d);//mettre a jour les pieces apres l'avoir quitt�
		//rassembler toutes les pieces dans une liste. mettre a jour la liste avec l'element pieceCourante
		//La difference est qu'on possede l'indice de la piece actuelle est qu'on peut facilement la modifier
		pieceSuivante=getFromMaze(pieceSuivante);//le probleme vient peut etre de la fonction pick( a tester) ou bien je ne fais que passer des elements non modifi� dans la liste Maze.
		if (pieceSuivante == null) {
			System.out.println("Il n'y a pas de porte dans cette direction!");
		} else {
			//mettre ici a jour le maze avant de detruire la pieceCourante
			updateMaze(pieceCourante);
			pieceCourante = pieceSuivante;
			System.out.println(pieceCourante.descriptionLongue());
		}
	}
	
	private Piece getFromMaze(Piece p) {
		// TODO Auto-generated method stub
		
		return maze.get(maze.indexOf(p));
	}


	public void capture(){
		if(pieceCourante.isPetHere()){
			if(!(p.isFull())){
				Chien recu=this.pieceCourante.pickUpPet();//cette methode doit retirer 1 au compteur de chien dans la piece ( pas de ArrayList chien dans la piece)
				String name;
				//System.out.println("Il y a un chien ici !"); ecrire ca quand la personne change de piece dans la description longue(besoin que d'afficher le nombre en vraie, pas besoin de mmessage, si 0 chien bah zero chien)
				System.out.println("Comment voullez vous l'appeler ?!");
				name=analyseurSyntaxique.getName();
				recu.setName(name);
				p.addPet(recu);
				System.out.println("Capture Reussi !, votre nouveau compagnon s'appelle "+name);
			}
		}else{
			System.out.println("Il n'y a pas d'animaux ici!");
		}
	}
	
	public void freePet(){
		if(!p.noPet()){
			System.out.println("qui voullez vous relacher ?!");//afficher ici les animaux dispo
			String relache=analyseurSyntaxique.getName();
			p.removePet(relache);
			System.out.println("Aurevoir !");
		}else{
			System.out.println("Aucun animal � relacher !");
		}
	}
	
	public void throwItem(){
		if(p.haveItem()){
			//implementer ici une condition au cas ou il n'y a qu'un seul item, le jeter sans demander le nom (pour le chien aussi)
			System.out.println("Entrez le nom de l'item � jeter");
			String name = analyseurSyntaxique.getName();
			System.out.println("Entrez le nom de l'item � jeter");
			p.removeObj(name);
			System.out.println("Vous avez jet� l'objet "+name);
		}else{
			System.out.println("Vous ne possedez aucun objet !");
		}
	}
	
	public void pickUpItem(){
		String name;
		Objet retire=null;
		//choisir ici de ramasser n'importe quel objet plutot
		if(!p.isFull()){
			
			if(!pieceCourante.isObjHere()){
				System.out.println("Aucun item � ramasser ici");
				return;
			}
			
			System.out.println("Quel Item ramasser ?!"); //afficher ici les items par terre
			System.out.println(pieceCourante.objDesc());
			name=analyseurSyntaxique.getName();
			try{
				retire=pieceCourante.pickUpItem(name);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
				
			System.out.println(pieceCourante.objDesc());
			//afficher ici le contenu de la piece pour voir si le probleme vient de la methode ou du maze.
			if(retire!=null){
				if(p.addObj(retire)){
					System.out.println("Vous avez ramassez l'objet "+name+" !");
				}else{
					System.out.println("Erreur lors de la tentative de ramassage");//checker ici avant si l'objet existe bien avant de lajouter et le retirer
				}
			}else{
				System.out.println("Aucun Objet "+name+" sur le sol");
			}
		}else{
			System.out.println("Votre inventaire est plein !");
		}
	}
	
}

