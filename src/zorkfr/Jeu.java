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

import java.util.Iterator;

import main.Chien;
import main.Objet;
import main.Player;

public class Jeu {
	private AnalyseurSyntaxique analyseurSyntaxique;

	private Piece pieceCourante;
	private Player p;



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

		
		// le jeu commence dehors
		pieceCourante = dehors;

		
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
			if(p.isFull()&&(p.isMaxPets())){
				termine=true;
			}
		}
		System.out.println("Vous avez ramasser suffisament d'objet et de chien pour sortir");
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
		} else if (motCommande.equals("echanger")) {		
			 exchangeItem();
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
	
	public int traiterCommandeSpec(Commande c){
		if(c!=null){
			if (!(c.isSpecial())) {//CA sert a rien !!!!!!!!!!!!!!!!!!!
				System.out.println("Commande Invalide lorsque l'inventaire du compagon est ouvert");
				return 2;
			}
			String motCommande = c.getMotCommande();

			if(motCommande!=null){
				if(motCommande.equalsIgnoreCase("donner")){
					return 0;
					
				}else if (motCommande.equalsIgnoreCase("prendre")) {
					return 1;
					
				}else if(motCommande.equalsIgnoreCase("fermer")) {
					return -1;
					
				}
			}
		}
		return 2;
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
		if(!(p.isBagEmpty())){
			System.out.println(p.bagDesc());
		}else{
		System.out.println("Le sac est vide");
		}
	}
	
	public void afficherAnimaux(){
		if(!(p.noPet())){
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

		if (pieceSuivante == null) {
			System.out.println("Il n'y a pas de porte dans cette direction!");
		} else {
			if(!(p.noPet())){
				petMovement(pieceCourante, pieceSuivante);//rajouter ici un message indiquant les chiens accompagnant le joueur et le deplacement qui se produit
			}
			pieceCourante = pieceSuivante;
			System.out.println(pieceCourante.descriptionLongue());
			
		}
	}

	public void petMovement(Piece A, Piece B){
		for(Chien c : p.petsList()){
		System.out.println("Votre Compagnon "+c.getName()+" se deplace depuis "+A.descriptionCourte()+" vers "+B.descriptionCourte());
		}
	}
	
	public void capture(){
		if(pieceCourante.isPetHere()){
			if(!(p.isMaxPets())){
				Chien recu=this.pieceCourante.pickUpPet();//cette methode doit retirer 1 au compteur de chien dans la piece ( pas de ArrayList chien dans la piece)
				String name;
				System.out.println("Capture Reussi ! Comment voullez vous l'appellez ?");
				name=analyseurSyntaxique.getName();
				recu.setName(name);
				p.addPet(recu);
				System.out.println("Votre nouveau compagnon s'appelle "+name);
			}else{
				System.out.println("plus de place pour de nouveaux compagnions");
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
	
	public void exchangeItem(){//il faut une commande pour ouvrir l'inventaire d'un chien
		//et pouvoir utiliser 2 commandes (donner prendre)
		System.out.println("Avec quel compagnon voulez vous echanger ?");
		String name = analyseurSyntaxique.getName();
		Iterator<Chien> iter=p.petsList().iterator();
		int command;
		Chien c=null;
		while(iter.hasNext()){
			c= iter.next();
			if(c.getName().equalsIgnoreCase(name)){
				System.out.println("Inventaire du compagnon "+c.getName()+" ouvert : ");
				System.out.println(c.bagDesc());
				c.openInv();
				break;
			}
		}
		while(c.invIsOpen()&&(c!=null)){
		//utiliser ici des commandes speciale et une boucle
			Commande commande = analyseurSyntaxique.getCommande();
			commande.setSpecial();
			command=traiterCommandeSpec(commande);
			if(command==1){

				if(!(c.isBagEmpty())){
					if(!(p.isFull())){
						System.out.println(c.bagDesc());
						System.out.println("Quel item prendre ?");
						String pre = analyseurSyntaxique.getName();
						Objet o=c.removeObj(pre);
						if(o==null){
							System.out.println("Aucun item possedant ce nom dans l'inventaire de "+c.getName());
							return;
						}
						p.addObj(o);
						System.out.println(o.getName()+" recuper�");
					}else{
						System.out.println("l'inventaire du joueur est plein");
					}
				}else{
					System.out.println("Aucun Item dans l'inventaire du compagnon");
				}
			}else if(command==0){

				if(!(p.isBagEmpty())){
					if(!(c.isFull())){
						System.out.println(p.bagDesc());
						System.out.println("Quel item donn� ?");
						String pre = analyseurSyntaxique.getName();
						Objet o=p.removeObj(pre);
						c.addObj(o);
						System.out.println(o.getName()+" donn� � "+c.getName());
					}else{
						System.out.println("Le compagnon ne peut plus porter d'objet supplementaire");
					}
				}else{
					System.out.println("Aucun Item dans l'inventaire");
				}
			}else if(command==-1){
				c.closeInv();
				System.out.println("Inventaire ferm� pour "+c.getName());
			}else if(command==2){
				System.out.println("Commande invalide quand l'inventaire du compagnon est ouvert");
			}
					
		}

			
	}
		
		
	
	
	public void pickUpItem(){
		String name;
		Objet retire=null;

		if(!(p.isFull())){
			
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

