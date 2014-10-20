package megamanVX.gameState.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import megamanVX.gameState.GameState;
import megamanVX.gameState.GameStateManager;
import megamanVX.tileMap.Background;

public class HelpState extends GameState {

	private Background bg;
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	
	public HelpState(GameStateManager gsm){
		this.gsm = gsm;
		
		try{
			bg = new Background("/Background/menubg.png", 1);
			bg.setVector(-0.1, 0);
			
			titleColor = new Color(128,0,0);
			titleFont = new Font("Century Gothic", Font.PLAIN, 16);
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
		gsm.drawCenteredString("Help", g);
		g.setFont(font);
		for(int i = 0; i < options.length; i++){
			if(i == currentChoice)
				g.setColor(Color.RED);
			else g.setColor(Color.BLACK);
			g.drawString(options[i], 145, 140 + i * 15);
		}
	}

	public void keyPressed(int k) {}

	@Override
	public void keyReleased(int k) {
		gsm.setState(GameStateManager.MENU_STATE);
	}

}
