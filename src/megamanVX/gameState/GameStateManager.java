package megamanVX.gameState;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import megamanVX.gameState.states.HelpState;
import megamanVX.gameState.states.Level1State;
import megamanVX.gameState.states.MenuState;
import megamanVX.main.GamePanel;


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
		gameStates.add(new Level1State(this));
		gameStates.add(new HelpState(this));
	}
	
	public void setState(int state){
		currentState = state;
		gameStates.get(currentState).init();
	}
	
	public void update(){
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
