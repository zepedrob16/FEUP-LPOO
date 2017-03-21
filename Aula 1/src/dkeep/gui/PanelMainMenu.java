package dkeep.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import javax.swing.JPanel;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;

public class PanelMainMenu extends JPanel implements ActionListener {
	
	private BufferedImage titleScreen;
	private JDialog settings;
	private static MediaPlayer mediaPlayer;
	
	public PanelMainMenu() throws IOException {
		playMusic();
		try{
			this.settings = new SettingsDialog();
			
			this.titleScreen = ImageIO.read(new File("res/title_screen_c64.png"));	
			setLayout(null);
			
			JButton btnLevelEditor = new JButton("Level Editor");
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
	
	public void playMusic(){
		JFXPanel fxPanel = new JFXPanel();

		Media hit = new Media(new File("res/sound/title_screen_song.mp3").toURI().toString());
		mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.setVolume(0.05);
		mediaPlayer.play();
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

