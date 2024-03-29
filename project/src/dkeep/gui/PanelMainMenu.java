package dkeep.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import javax.swing.JPanel;

import dkeep.gui.PanelManager.Event;
import dkeep.logic.DungeonMap;
import dkeep.logic.OgreMap;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class PanelMainMenu extends JPanel implements ActionListener {
	
	private BufferedImage titleScreen;
	private SettingsDialog settings;
	private PanelManager pm;
	
	public void ngButton() {
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try { settings = new SettingsDialog(); } catch (IOException e1) {}
				settings.setPanelManager(pm);
				settings.setVisible(true);
				try {
					pm.load(new DungeonMap());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		styleButton(btnNewGame, 76, 456, 231, 65, 24);
	}
	
	public void lgButton() {
		JButton btnLoadGame = new JButton("Load Game");
		btnLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try { loadMap(); } catch (IOException e) {}
			}
		});
		styleButton(btnLoadGame, 76, 534, 231, 37, 15);
	}
	
	public void leButton() {
		JButton btnLevelEditor = new JButton("Level Editor");
		btnLevelEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pm.stateMachine(Event.EDIT_GAME);
			}
		});
		styleButton(btnLevelEditor, 76, 584, 231, 37, 15);
	}
	public void eButton() {
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		styleButton(btnExit, 76, 634, 231, 37, 15);
	}
	
	public PanelMainMenu() throws IOException {

		setLayout(null);
		this.titleScreen = ImageIO.read(new File("res/title_screen_c64.png"));	
		ngButton();
		lgButton();
		leButton();
		eButton();		
	}
	
	public void styleButton(JButton btn, int x1, int x2, int x3, int x4, int size){
		btn.setFont(new Font("Cooper Black", Font.PLAIN, size));
		btn.setForeground(Color.WHITE);
		btn.setBackground(new Color(51, 51, 0));
		btn.setBounds(x1, x2, x3, x4);
		add(btn);
	}

	public void loadMap() throws IOException{
		File folder = new File("res/saves");
		File[] saves = folder.listFiles();
		String[] saveNames = new String[saves.length];
		
		for (int i = 0; i < saves.length; i++){
			saveNames[i] = (saves[i].getName().replaceAll(".txt", ""));
		}
		String res = (String) JOptionPane.showInputDialog(null, "Select a save file", "Load Game", JOptionPane.QUESTION_MESSAGE, null, saveNames, saveNames[0]);
		
		
		char[][] selMap;
		
		Scanner s1 = new Scanner(new File("res/saves/" + res + ".txt"));
		int gridSize = s1.nextLine().length();
		selMap = new char[gridSize][gridSize];
		s1.close();
		
		Scanner s2 = new Scanner(new File("res/saves/" + res + ".txt"));
		for (int i = 0; i < gridSize; i++){
			selMap[i] = s2.nextLine().toCharArray();
			System.out.println(selMap[i]);
		}
		s2.close();
		pm.load(new OgreMap(selMap));
		pm.stateMachine(Event.START_GAME);
	}
	
	public void setPanelManager(PanelManager pm){
		this.pm = pm;
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(titleScreen, 0, 0, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}

