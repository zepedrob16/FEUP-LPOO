package dkeep.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import dkeep.logic.GameState;
import javax.swing.JButton;

public class PanelGame extends JPanel implements ActionListener {

	private Utilities u;
	
	private GameState gameState;
	
	
	public int ogreNumber;
	public String guardPersonality;
	
	private int WIDTH, HEIGHT;
	
	public PanelGame() {
		gameState = new GameState();
		setLayout(null);
		
		this.setVisible(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.repaint();
	}
	
	public void fillImageArray(){
		char[][] map = gameState.getGameMap().getMap();
		
	}

}
