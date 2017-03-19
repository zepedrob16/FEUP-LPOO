package dkeep.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

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
	
	private BufferedImage dFloor, dFloorBlood, dFloorGrass1, dFloorGrass2, dFloorWater, dFloorBarricade, dWall;
	private BufferedImage heroS;
	
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
		this.dFloor = ImageIO.read(new File("res/sprites/static/dfloor.png"));
		this.dFloorBlood = ImageIO.read(new File("res/sprites/static/dfloorblood.png"));
		this.dFloorGrass1 = ImageIO.read(new File("res/sprites/static/dfloorgrass1.png"));
		this.dFloorGrass2 = ImageIO.read(new File("res/sprites/static/dfloorgrass2.png"));
		this.dFloorWater = ImageIO.read(new File("res/sprites/static/dfloorwater.png"));
		this.dFloorBarricade = ImageIO.read(new File("res/sprites/static/dfloorbarricade.png"));
		this.dWall = ImageIO.read(new File("res/sprites/static/dwall.png"));
		this.heroS = ImageIO.read(new File("res/sprites/hero/0.png"));
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		Random rnd = new Random();
		
		for (int i = 0; i < this.gridW; i++){
			for (int j = 0; j < this.gridH; j++){
				if (i == 0 || j == 0 || i == this.gridW - 1 || j == this.gridH - 1){
					g.drawImage(dWall, i * this.offsetW, j * this.offsetH, this);
					continue;
				}
				
				int genGrass = rnd.nextInt(5);
				int genBlood = rnd.nextInt(25);
				
				
				if (genGrass == 0){
					g.drawImage(dFloorGrass1, i * this.offsetW, j * this.offsetH, this);					
				}
				else if (genBlood == 0){
					g.drawImage(dFloorBlood, i * this.offsetW, j * this.offsetH, this);
				}
				else{
					g.drawImage(dFloor, i * this.offsetW, j * this.offsetH, this);
				}
			}
		}
		g.drawImage(this.heroS, 100, 100, this);
				
	}
	
	public void setDimensions(){
		this.gridW = this.gameState.getGameMap().getMap().length;
		this.gridH = this.gameState.getGameMap().getMap()[0].length;
		this.offsetW = Math.round(this.windowW / this.gridW);
		this.offsetH = Math.round(this.windowH / this.gridH);
		
		this.dFloor = Scalr.resize(this.dFloor, this.offsetH);
		this.dFloorBlood = Scalr.resize(this.dFloorBlood, this.offsetH);
		this.dFloorGrass1 = Scalr.resize(this.dFloorGrass1, this.offsetH);
		this.dFloorGrass2 = Scalr.resize(this.dFloorGrass2, this.offsetH);
		this.dFloorWater = Scalr.resize(this.dFloorWater, this.offsetH);
		this.dFloorBarricade = Scalr.resize(this.dFloorBarricade, this.offsetH);
		this.dWall = Scalr.resize(this.dWall, this.offsetH);
		this.heroS = Scalr.resize(this.heroS, 70);
		
		//System.out.println(this.gridW + " " + this.gridH + " " + this.offsetW + " " + this.offsetH);
	}

}
