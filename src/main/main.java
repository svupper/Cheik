package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import zorkfr.*;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class main {

	
	/*
	Commande c = new Commande("motCommande", "secondMot");
	
	MotCleCommande mcc = new MotCleCommande();
	
	AnalyseurSyntaxique as = new AnalyseurSyntaxique();
	
	Commande t = as.getCommande();*/
	public static void main(String[] args)  {

		Jeu leJeu;
		
		leJeu = new Jeu();
		leJeu.jouer();//rajouter une commande inventaire
		
	}
}
		//obj.[0]=new Objet();
		/*
		for (Objet objet :  obj) {
			name += " " + objet.getName();
		*/	
			/*
	Iterator iter = obj.iterator();
	System.out.print(iter.hasNext());
	while (iter.hasNext()) {
		System.out.println(iter.next());
			
		}
		
		System.out.print(name);
	}
	*/
		/*

		
		//String name = Cave.EPEE.name();
		//System.out.print(Cave.randomObj().name());
	}
		
		//new AppGameContainer(new WindowGame(), 192, 140, false).start();
		
		/*Window w = new Window();
		w.loadMap(1);
		w.render();
		
		GameContainer gc=new GameContainer();
		WindowGame wg=new WindowGame();
		wg.loadMap(1);
		wg.render(0, 0);
		//try maybe to display a picture instead of using slick2D
    }
	
	//Jeu game = new Jeu();
	//game.jouer();

	*/
	
	
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
	
	

