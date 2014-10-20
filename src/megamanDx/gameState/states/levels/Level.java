package megamanDx.gameState.states.levels;

import java.awt.Graphics2D;
import java.util.ArrayList;

import megamanDx.entites.player.Player;
import megamanDx.entites.player.character.Char;
import megamanDx.gameState.GameState;
import megamanDx.gameState.GameStateManager;
import megamanDx.main.GamePanel;
import megamanDx.main.math.Vector2i;
import megamanDx.tileMap.Background;
import megamanDx.tileMap.TileMap;

public class Level extends GameState{
	
	private TileMap tileMap;
	private Background bg;
	
	private Player player;
	private Char ch;
	private Vector2i startPosition;
	private int tileSize;
	private String tileSet;
	private String background;
	private String mapFile;
	
	private static ArrayList<Level> levels = new ArrayList<Level>();
	
	public Level(int level, Char ch, Vector2i startPosition, int tileSize,
			String tileSet, String background, String mapFile,
			GameStateManager gsm) {
		this.gsm = gsm;
		levels.add(this);
		this.ch = ch;
		this.startPosition = startPosition;
		this.tileSize = tileSize;
		this.tileSet = tileSet;
		this.background = background;
		this.mapFile = mapFile;
		init();
	}

	@Override
	public void init() {
		tileMap = new TileMap(tileSize);
		tileMap.loadTiles("/Tilesets/" + tileSet +".png");
		tileMap.loadMap("/Maps/" + mapFile +".map");
		tileMap.setPosition(0,0);
		tileMap.setTween(1);
		
		bg = new Background("/Background/" + background + ".png", 0.1);
		
		player = new Player(ch, tileMap);
		player.setPosition(startPosition);
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
