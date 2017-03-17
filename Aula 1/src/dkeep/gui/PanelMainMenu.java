package dkeep.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import javax.swing.JPanel;
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
	
	public PanelMainMenu() throws IOException {
		try{
			this.settings = new SettingsDialog();
			
			this.titleScreen = ImageIO.read(new File("res/title_screen_c64.png"));	
			setLayout(null);
			
			JButton btnLevelEditor = new JButton("Level Editor");
			btnLevelEditor.setFont(new Font("Cooper Black", Font.PLAIN, 15));
			btnLevelEditor.setForeground(new Color(255, 255, 255));
			btnLevelEditor.setBackground(new Color(51, 51, 0));
			btnLevelEditor.setBounds(698, 513, 132, 37);
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
			btnExit.setBounds(698, 563, 132, 37);
			add(btnExit);
			
			JButton btnNewGame = new JButton("New Game");
			btnNewGame.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					settings.setVisible(true);
				}
			});
			btnNewGame.setForeground(Color.WHITE);
			btnNewGame.setFont(new Font("Cooper Black", Font.PLAIN, 15));
			btnNewGame.setBackground(new Color(51, 51, 0));
			btnNewGame.setBounds(698, 463, 132, 37);
			add(btnNewGame);
		} catch (IOException ex){
			//Add exception.
		}
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

