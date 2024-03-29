/**
 *  <p>
 *
 *  Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 *  texte.</p> <p>
 *
 *  Classe répertoriant l'ensemble des mots-clé utilisables comme commande dans
 *  le jeu. Elle est utilisée pour vérifier la validité des commandes de
 *  l'utilisateur.
 *
 * @author     Michael Kolling
 * @author     Marc Champesme (pour la traduction francaise)
 * @version    1.0
 * @since      July 1999
 */
package zorkfr;

public class MotCleCommande {
	/**
	 *  Un tableau constant contenant tous les mots-clé valides comme commandes.
	 */
	private final static String commandesValides[] = {"aller", "quitter", "aide","catch","relacher","drop","ramasser","inventaire","echanger"};
	private final static String commandesSpec[] = {"donner","prendre","inv","fermer"};

	/**
	 *  Initialise la liste des mots-clé utilisables comme commande.
	 */
	public MotCleCommande() { }


	/**
	 *  Teste si la chaine de caractères spécifiée est un mot-clé de commande
	 *  valide. Check whether a given String is a valid command word. Return true
	 *  if it is, false if it isn't.
	 *
	 * @param  aString  Chaine de caractères a tester
	 * @return          true si le paramètre est une commande valide, false sinon
	 */
	public boolean estCommande(String aString) {
		for (int i = 0; i < commandesValides.length; i++) {
			if (commandesValides[i].equalsIgnoreCase(aString)) {
				return true;
			}
		}
		return false;
	}


	/**
	 *  Affiche toutes les commandes (i.e. les mots-clé) valides.
	 */
	public void afficherToutesLesCommandes() {
		for (int i = 0; i < commandesValides.length; i++) {
			System.out.print(commandesValides[i] + "  ");
		}
		System.out.println();
	}
	
	public void afficherCommandesSpec() {
		for (int i = 0; i < commandesSpec.length; i++) {
			System.out.print(commandesSpec[i] + "  ");
		}
		System.out.println();
	}


	public boolean estCommandeSpec(String aString) {
		// TODO Auto-generated method stub
		for (int i = 0; i < commandesSpec.length; i++) {
			if (commandesSpec[i].equalsIgnoreCase(aString)) {
				return true;
			}
		}
		return false;
	}
}
