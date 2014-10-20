package megamanVX.gameState;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import megamanVX.gameState.states.Level1State;
import megamanVX.gameState.states.MenuState;


public class GameStateManager {
	
	private List<GameState> gameStates;
	private int currentState;
	
	public static final int MENU_STATE = 0;
	public static final int LEVEL1_STATE = 1;
	
	public GameStateManager(){
		gameStates = new ArrayList<GameState>();
		currentState = MENU_STATE;
		gameStates.add(new MenuState(this));
		gameStates.add(new Level1State(this));
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
	
	
}
