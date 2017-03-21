package dkeep.gui;

import java.awt.EventQueue;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class GameGUI {

	private JFrame frame;
	private JPanel panel;
	
	private PanelMainMenu panelMainMenu = new PanelMainMenu();
	private PanelGame panelGame;
	private PanelLevelEditor panelLevelEditor;

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

	public GameGUI() throws IOException {
		frame = new JFrame("Dungeon Keep");
		frame.setBounds(100, 100, 1280, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		/*
		panelMainMenu.setBounds(0, 0, 1280, 800);
		frame.getContentPane().add(panelMainMenu);
		panelMainMenu.requestFocusInWindow();
		*/
		
		frame.setBounds(100, 100, 700, 700);
		panelGame = new PanelGame(700, 700);
		panelGame.setBounds(0, 0, 700, 700);
		frame.getContentPane().add(panelGame);
		panelGame.addKeyListener(panelGame);
		panelGame.setFocusable(true);
		panelGame.requestFocusInWindow();
		
		/*
		frame.setBounds(100, 100, 900, 700);
		panelLevelEditor = new PanelLevelEditor(700, 700);
		frame.getContentPane().add(panelLevelEditor);
		panelLevelEditor.setBounds(0, 0, 900, 700);
		//panelLevelEditor.addKeyListener(panelLevelEditor);
		panelLevelEditor.setFocusable(true);
		panelGame.requestFocusInWindow();
		*/
		
	}

}
