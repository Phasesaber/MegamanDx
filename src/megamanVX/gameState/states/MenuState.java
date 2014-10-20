package megamanVX.gameState.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import megamanVX.gameState.GameState;
import megamanVX.gameState.GameStateManager;
import megamanVX.tileMap.Background;


public class MenuState extends GameState {

	private Background bg;
	
	private int currentChoice = 0;
	private String[] options = {"Start", "Help", "Quit"};
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	
	
	public MenuState(GameStateManager gsm){
		this.gsm = gsm;
		
		try{
			bg = new Background("/Background/menubg.png", 1);
			bg.setVector(-0.1, 0);
			
			titleColor = new Color(128,0,0);
			titleFont = new Font("Century Gothic", Font.PLAIN, 28);
			font = new Font("Arial", Font.PLAIN, 12);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void init() {
		
	}

	@Override
	public void update() {
		bg.update();
	}

	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);
		g.setColor(titleColor);
		g.setFont(titleFont);
		//TODO Center Text
		g.drawString("MegamanVX", 70, 70);
		g.setFont(font);
		for(int i = 0; i < options.length; i++){
			if(i == currentChoice)
				g.setColor(Color.RED);
			else g.setColor(Color.BLACK);
			g.drawString(options[i], 145, 140 + i * 15);
		}
	}

	
	private void select(){
		switch(currentChoice){
		case 0: gsm.setState(GameStateManager.LEVEL1_STATE); break;//Start
		case 1: break;//Help 
		case 2: System.exit(0); //Quit
		}
	}
	
	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER)
			select();
		
		if(k == KeyEvent.VK_UP || k == KeyEvent.VK_W){
			currentChoice--;
			if(currentChoice == -1)
				currentChoice = options.length - 1;
		}
		if(k == KeyEvent.VK_DOWN || k == KeyEvent.VK_D){
			currentChoice++;
			if(currentChoice == options.length)
				currentChoice = 0;
		}
		
	}

	@Override
	public void keyReleased(int k) {
		
	}

}
