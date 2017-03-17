package dkeep.gui;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class GameGUI {

	private JFrame frame;
	private JPanel panel;
	
	private PanelMainMenu panelMainMenu = new PanelMainMenu();
	private PanelGame panelGame = new PanelGame();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameGUI window = new GameGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public GameGUI() throws IOException {
		frame = new JFrame("Dungeon Keep");
		frame.setBounds(100, 100, 1280, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		/*
		panelMainMenu.setBounds(0, 0, 1280, 800);
		frame.getContentPane().add(panelMainMenu);
		//frame.pack();
		panelMainMenu.requestFocusInWindow();
		*/
		
		frame.setBounds(100, 100, 850, 850);
		panelGame.setBounds(0, 0, 850, 850);
		frame.getContentPane().add(panelGame);
		panelGame.requestFocusInWindow();
		
		
	}

}
