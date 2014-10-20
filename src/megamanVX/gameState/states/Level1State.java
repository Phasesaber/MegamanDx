package megamanVX.gameState.states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import megamanVX.entites.player.Player;
import megamanVX.gameState.GameState;
import megamanVX.gameState.GameStateManager;
import megamanVX.main.GamePanel;
import megamanVX.tileMap.Background;
import megamanVX.tileMap.TileMap;


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
		
		player = new Player(tileMap);
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
		if(k == KeyEvent.VK_LEFT || k == KeyEvent.VK_A)player.setLeft(true);
		if(k == KeyEvent.VK_RIGHT || k == KeyEvent.VK_D)player.setRight(true);
		if(k == KeyEvent.VK_UP || k == KeyEvent.VK_W)player.setUp(true);
		if(k == KeyEvent.VK_DOWN || k == KeyEvent.VK_S)player.setDown(true);
		if(k == KeyEvent.VK_SPACE)player.setJumping(true);
		if(k == KeyEvent.VK_E)player.setGliding(true);
		if(k == KeyEvent.VK_R)player.setPunching();
		if(k == KeyEvent.VK_F)player.setFiring();
	}

	@Override
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT || k == KeyEvent.VK_A)player.setLeft(false);
		if(k == KeyEvent.VK_RIGHT || k == KeyEvent.VK_D)player.setRight(false);
		if(k == KeyEvent.VK_UP || k == KeyEvent.VK_W)player.setUp(false);
		if(k == KeyEvent.VK_DOWN || k == KeyEvent.VK_S)player.setDown(false);
		if(k == KeyEvent.VK_SPACE)player.setJumping(false);
		if(k == KeyEvent.VK_E)player.setGliding(false);
	}

}
