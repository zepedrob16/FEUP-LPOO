package dkeep.gui;

import java.awt.EventQueue;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dkeep.gui.PanelManager.Event;

import javax.swing.JLabel;

public class GameGUI {

	private JFrame frame;
	private JPanel panel;
	
	private PanelMainMenu pmm;
	private PanelGame pg;
	private PanelLevelEditor ple;

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
		
		pmm = new PanelMainMenu();
		pg = new PanelGame(700, 700);
		ple = new PanelLevelEditor(700, 700);
		
		PanelManager pm = new PanelManager(pg, ple, pmm);
		pmm.setPanelManager(pm);
		pg.setPanelManager(pm);
		
		frame = new JFrame("Dungeon Keep");
		frame.setBounds(100, 100, 1280, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		
		pmm.setBounds(0, 0, 1280, 800);
		frame.getContentPane().add(pmm);
		pmm.requestFocusInWindow();
		
		
		//frame.setBounds(100, 100, 700, 700);
		pg.setBounds(0, 0, 700, 700);
		frame.getContentPane().add(pg);
		pg.addKeyListener(pg);
		pg.setFocusable(true);
		pg.requestFocusInWindow();
		
		
		//frame.setBounds(100, 100, 900, 700);
		frame.getContentPane().add(ple);
		ple.setBounds(0, 0, 900, 700);
		ple.setFocusable(true);
		ple.requestFocusInWindow();
		
		
	}

}
