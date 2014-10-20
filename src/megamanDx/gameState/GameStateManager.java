package megamanDx.gameState;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import megamanDx.entites.player.character.Char;
import megamanDx.gameState.states.HelpState;
import megamanDx.gameState.states.MenuState;
import megamanDx.gameState.states.levels.Level;
import megamanDx.main.GamePanel;
import megamanDx.main.math.Vector2i;


public class GameStateManager {
	
	private List<GameState> gameStates;
	private int currentState;
	
	public static final int MENU_STATE = 0;
	public static final int LEVEL1_STATE = 1;
	public static final int HELP_STATE = 2;
	
	public GameStateManager(){
		gameStates = new ArrayList<GameState>();
		currentState = MENU_STATE;
		gameStates.add(new MenuState(this));
		gameStates.add(new Level(1, Char.Megaman, new Vector2i(100,0), 30, "grasstileset", "grassbg1", "level1", this));
		gameStates.add(new HelpState(this));
	}
	
	public void setState(int state){
		currentState = state;
		gameStates.get(currentState).init();
	}
	
	public void update(){
		if(!gameStates.get(currentState).isPaused())
			gameStates.get(currentState).update();
	}
	
	public void draw(Graphics2D g){
		gameStates.get(currentState).draw(g);
	}
	
	public void keyPressed(int k){
		gameStates.get(currentState).keyPressed(k);
	}
	
	public void keyReleased(int k){
		gameStates.get(currentState).keyReleased(k);
	}
	
	public void drawCenteredString(String s,int y, Graphics2D g) {
	    FontMetrics fm = g.getFontMetrics();
	    int x = (GamePanel.WIDTH - fm.stringWidth(s)) / 2;
	    g.drawString(s, x, y);
	  }
	
	
}
