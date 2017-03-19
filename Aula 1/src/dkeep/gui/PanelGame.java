package dkeep.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import org.imgscalr.Scalr;

import dkeep.logic.DungeonMap;
import dkeep.logic.GameState;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class PanelGame extends JPanel implements ActionListener {
	
	private Utilities u;
	
	private GameState gameState;
	
	private int gridW, gridH, windowW, windowH, offsetW, offsetH;
	
	public int ogreNumber;
	public String guardPersonality;
	
	private BufferedImage dFloorTile, heroS;
	
	public PanelGame(int windowW, int windowH) throws IOException {
		this.windowW = windowW;
		this.windowH = windowH;
		
		this.loadImages();
		
		gameState = new GameState();
		gameState.setGameMap(new DungeonMap());
		this.setDimensions();
		setLayout(null);
	}
	
	public void loadImages() throws IOException{
		this.dFloorTile = ImageIO.read(new File("res/sprites/static/floortile.png"));
		this.heroS = ImageIO.read(new File("res/sprites/hero/0.png"));
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		for (int i = 0; i < this.windowW; i += this.offsetW){
			for (int j = 0; j < this.windowH; j += this.offsetH){
				g.drawImage(dFloorTile, i, j, this);
			}
		}
		g.drawImage(this.heroS, 0, 0, this);
				
	}
	
	public void setDimensions(){
		this.gridW = this.gameState.getGameMap().getMap().length;
		this.gridH = this.gameState.getGameMap().getMap()[0].length;
		this.offsetW = Math.round(this.windowW / this.gridW);
		this.offsetH = Math.round(this.windowH / this.gridH);
		
		this.dFloorTile = Scalr.resize(this.dFloorTile, this.offsetH);
		this.heroS = Scalr.resize(this.heroS, 70);
		
		//System.out.println(this.gridW + " " + this.gridH + " " + this.offsetW + " " + this.offsetH);
	}

}
