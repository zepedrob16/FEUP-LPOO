package dkeep.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private BufferedImage sprHero, sprGuard, sprDWall, sprDFloor, sprLever, sprDoorC, sprStairs;
	private BufferedImage sprOgre;
	
	public GamePanel() throws IOException {
		try{
			this.sprHero = ImageIO.read(new File("res/ogreSprites.png"));			
		} catch (IOException ex){
			
		}
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(sprHero, 0, 0, this);
	}

}
