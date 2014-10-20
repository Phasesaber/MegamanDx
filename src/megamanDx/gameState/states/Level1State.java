package megamanDx.gameState.states;

import java.awt.Graphics2D;

import megamanDx.entites.player.Player;
import megamanDx.entites.player.character.Char;
import megamanDx.gameState.GameState;
import megamanDx.gameState.GameStateManager;
import megamanDx.main.GamePanel;
import megamanDx.tileMap.Background;
import megamanDx.tileMap.TileMap;


public class Level1State extends GameState {
	
	private TileMap tileMap;
	private Background bg;
	
	private Player player;
	
	public Level1State(GameStateManager gsm){
		this.gsm = gsm;
		init();
	}
	
	
	@Override
	public void init() {
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/grasstileset.png");
		tileMap.loadMap("/Maps/level1-1.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		
		bg = new Background("/Background/grassbg1.png", 0.1);
		
		player = new Player(Char.Megaman, tileMap);
		player.setPosition(100, 0);
	}

	@Override
	public void update() {
		player.update();
		tileMap.setPosition(GamePanel.WIDTH/2 - player.getX(), GamePanel.HEIGHT/2 - player.getY());
	}

	@Override
	public void draw(Graphics2D g) {
		//Draw Background
		bg.draw(g);
		
		//Draw TileMap
		tileMap.draw(g);
		
		//Draw Player
		player.draw(g);
	}

	@Override
	public void keyPressed(int k) {
		player.keyPressed(k);
	}

	@Override
	public void keyReleased(int k) {
		player.keyReleased(k);
	}


}
