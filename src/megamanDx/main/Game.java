package megamanDx.main;

import javax.swing.JFrame;

public class Game {
	
	public static void main(String[] args){
		JFrame window = new JFrame("Stones");
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}

	public static void pause(double d) {
		try{Thread.sleep((long) (d*1000));}
		catch(Exception e){e.printStackTrace();}
	}
	
}
