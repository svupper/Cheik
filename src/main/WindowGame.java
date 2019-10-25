package main;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class WindowGame extends BasicGame {

	private GameContainer container;
	private TiledMap map;

	public WindowGame() {
		super("Lesson 1 :: WindowGame");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		this.container = container;
		this.map = new TiledMap("/ressources/tuiles/room1bis.tmx");
	}

	public void loadMap(int number) throws SlickException{
		switch (number){
			case 1:
				this.map = new TiledMap("/ressources/tuiles/room1bis.tmx");
				break;
			case 2:
				this.map = new TiledMap("/ressources/tuiles/room2.tmx");
				break;
			case 3:
				this.map = new TiledMap("/ressources/tuiles/room3.tmx");
				break;
			case 4:
				this.map = new TiledMap("/ressources/tuiles/room4.tmx");
				break;
			case 5:
				this.map = new TiledMap("/ressources/tuiles/room5.tmx");
				break;
			case 6:
				this.map = new TiledMap("/ressources/tuiles/room6.tmx");
				break;
			case 7:
				this.map = new TiledMap("/ressources/tuiles/room7.tmx");
				break;
			case 8:
				this.map = new TiledMap("/ressources/tuiles/room8.tmx");
				break;
			case 9:
				this.map = new TiledMap("/ressources/tuiles/room9.tmx");
				break;
			case 10:
				this.map = new TiledMap("/ressources/tuiles/room10.tmx");
				break;
			case 11:
				this.map = new TiledMap("/ressources/tuiles/room11.tmx");
				break;
			case 12:
				this.map = new TiledMap("/ressources/tuiles/room12.tmx");
				break;
		}
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException {
		this.map.render(0, 0);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
	}

	@Override
	public void keyReleased(int key, char c) {
		if (Input.KEY_ESCAPE == key) {
			container.exit();
		}
	}

}