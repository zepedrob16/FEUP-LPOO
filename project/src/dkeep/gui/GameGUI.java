package dkeep.gui;

import java.awt.EventQueue;
import java.io.IOException;
import javax.swing.JFrame;

public class GameGUI {

	private JFrame frame;
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
		frame = new JFrame("Dungeon Keep");
		
		PanelManager pm = new PanelManager(pg, ple, pmm, frame);
		pmm.setPanelManager(pm);
		pg.setPanelManager(pm);
		ple.setPanelManager(pm);
		
		frame.setBounds(100, 100, 1280, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		pmm.setBounds(0, 0, 1280, 800);
		frame.getContentPane().add(pmm);
		pmm.requestFocusInWindow();
		
		pg.setBounds(0, 0, 700, 700);
		frame.getContentPane().add(pg);
		pg.addKeyListener(pg);
		pg.setFocusable(true);
		pg.requestFocusInWindow();
		
		ple.setBounds(0, 0, 900, 700);
		frame.getContentPane().add(ple);
		ple.setFocusable(true);
		ple.requestFocusInWindow();
	}

}
