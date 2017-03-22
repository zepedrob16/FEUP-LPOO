package dkeep.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import javax.swing.JPanel;

import dkeep.gui.PanelManager.Event;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;

public class PanelMainMenu extends JPanel implements ActionListener {
	
	private BufferedImage titleScreen;
	private SettingsDialog settings;
	private static MediaPlayer mediaPlayer;
	
	private PanelManager pm;
	
	public PanelMainMenu() throws IOException {
		
		playMusic();
		try{
			this.titleScreen = ImageIO.read(new File("res/title_screen_c64.png"));	
			setLayout(null);
			
			JButton btnLevelEditor = new JButton("Level Editor");
			btnLevelEditor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pm.stateMachine(Event.EDIT_GAME);
				}
			});
			btnLevelEditor.setFont(new Font("Cooper Black", Font.PLAIN, 15));
			btnLevelEditor.setForeground(new Color(255, 255, 255));
			btnLevelEditor.setBackground(new Color(51, 51, 0));
			btnLevelEditor.setBounds(76, 534, 231, 37);
			add(btnLevelEditor);
			
			JButton btnExit = new JButton("Exit");
			btnExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			btnExit.setForeground(Color.WHITE);
			btnExit.setFont(new Font("Cooper Black", Font.PLAIN, 15));
			btnExit.setBackground(new Color(51, 51, 0));
			btnExit.setBounds(76, 634, 231, 37);
			add(btnExit);
			
			JButton btnNewGame = new JButton("New Game");
			btnNewGame.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						settings = new SettingsDialog();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					settings.setPanelManager(pm);
					settings.setVisible(true);
				}
			});
			btnNewGame.setForeground(Color.WHITE);
			btnNewGame.setFont(new Font("Cooper Black", Font.PLAIN, 24));
			btnNewGame.setBackground(new Color(51, 51, 0));
			btnNewGame.setBounds(76, 456, 231, 65);
			add(btnNewGame);
			
			JButton btnLoadGame = new JButton("Load Game");
			btnLoadGame.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						loadMap();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			btnLoadGame.setForeground(Color.WHITE);
			btnLoadGame.setFont(new Font("Cooper Black", Font.PLAIN, 15));
			btnLoadGame.setBackground(new Color(51, 51, 0));
			btnLoadGame.setBounds(76, 584, 231, 37);
			add(btnLoadGame);
		} catch (IOException ex){
			//Add exception.
		}
	}

	public void loadMap() throws IOException{
		File folder = new File("res/saves");
		File[] saves = folder.listFiles();
		String[] saveNames = new String[saves.length];
		
		for (int i = 0; i < saves.length; i++){
			saveNames[i] = (saves[i].getName().replaceAll(".txt", ""));
		}
		String res = (String) JOptionPane.showInputDialog(null, "Select a save file", "Load Game", JOptionPane.QUESTION_MESSAGE, null, saveNames, saveNames[0]);
		File sel = new File("res/saves/" + res + ".txt");
		
		FileReader fileReader = new FileReader(sel);
		BufferedReader bufferedReader = new BufferedReader(fileReader);	//Wrap for efficiency.
		char[][] selMap;
		
		int gridSize = bufferedReader.readLine().length(); //Saves the grid size.
		selMap = new char[gridSize][gridSize];
		bufferedReader.mark(0);
		bufferedReader.reset();
		
		for (int i = 0; i < gridSize; i++){
			char[] tiles = new char[gridSize];
			bufferedReader.readLine().getChars(0, gridSize, tiles, 0);
			for (int h = 0; h < tiles.length; h++){
				System.out.println(tiles[h]);
			}
			
			selMap[i] = tiles;
		}
		for (int i = 0; i < selMap.length; i++){
			for (int j = 0; j < selMap.length; j++){
				System.out.println(selMap[i][j]);
			}
			System.out.print("\n");
		}
		
	}
	
	public void playMusic(){
		JFXPanel fxPanel = new JFXPanel();

		Media hit = new Media(new File("res/sound/title_screen_song.mp3").toURI().toString());
		mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.setVolume(0.05);
		mediaPlayer.play();
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

