package megamanDx.gameState.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import megamanDx.gameState.GameState;
import megamanDx.gameState.GameStateManager;
import megamanDx.main.GamePanel;
import megamanDx.tileMap.Background;

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
		gsm.drawCenteredString("Help", 14, g);
		g.setFont(font);
		gsm.drawCenteredString("Press Any Key to go Back", GamePanel.HEIGHT - 30, g);
		g.setColor(Color.CYAN);
		g.drawString("This is a Megaman tribute game. It is a fully working 2D", 8, 26);
		g.drawString("engine that can be used for other games. I will be", 8, 26 + 12);
		g.drawString("improving it to be just like the original Megaman Games.", 8, 26 + 12 + 12);
	}

	public void keyPressed(int k) {}

	@Override
	public void keyReleased(int k) {
		if (k != KeyEvent.VK_ENTER)
			gsm.setState(GameStateManager.MENU_STATE);
	}

}
