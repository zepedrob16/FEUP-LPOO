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
	
	private int gridW, gridH, windowW, windowH, offsetW, offsetH, heroX, heroY, guardX, guardY;
	
	public int ogreNumber;
	public String guardPersonality;
	private BufferedImage[][] gameImages = new BufferedImage[10][10];
	
	private BufferedImage dFloor, dFloorBlood, dFloorGrass1, dFloorGrass2, dFloorWater, dFloorBarricade, dWall, dDoor;
	private BufferedImage heroS, guardS;
	
	public PanelGame(int windowW, int windowH) throws IOException {
		this.windowW = windowW;
		this.windowH = windowH;
		
		this.loadImages();
		
		gameState = new GameState();
		gameState.setGameMap(new DungeonMap());
		this.setDimensions();
		this.setMap();
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
		this.dDoor = ImageIO.read(new File("res/sprites/static/ddoor.png"));
		this.heroS = ImageIO.read(new File("res/sprites/hero/0.png"));
		this.guardS = ImageIO.read(new File("res/sprites/guard/0.png"));
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(this.heroS, this.heroX, this.heroY, this);
		g.drawImage(this.guardS, this.guardX, this.guardY, this);

		Random rnd = new Random();
		for (int i = 0; i < this.gameImages.length; i++) {
			for (int j = 0; j < this.gameImages[0].length; j++) {
				if (i == this.heroX/this.offsetW && j == this.heroY/this.offsetH){
					g.drawImage(dFloor, this.heroX, this.heroY, this);
					g.drawImage(this.heroS, this.heroX, this.heroY, this);
					continue;
				}
				else if (i == this.guardX/this.offsetW && j == this.guardY/this.offsetH){
					g.drawImage(dFloor, this.guardX, this.guardY, this);
					g.drawImage(this.guardS, this.guardX, this.guardY, this);
					continue;
				}
				else if (gameImages[i][j] == dFloor) {
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
				else 
					g.drawImage(gameImages[i][j], i*this.offsetW, j*this.offsetW, this);
			}
		}

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
		this.dDoor = Scalr.resize(this.dDoor, this.offsetH);
		this.heroS = Scalr.resize(this.heroS, 70);
		this.guardS = Scalr.resize(this.guardS, 70);
		
		//System.out.println(this.gridW + " " + this.gridH + " " + this.offsetW + " " + this.offsetH);
	}
	
	public void setMap() {		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if(this.gameState.getGameMap().getMap()[i][j] == 'X'){
					gameImages[j][i] = dWall;
					continue;
				}
				else if (this.gameState.getGameMap().getMap()[i][j] == 'I') {
					gameImages[j][i] = dDoor;
					continue;
				}
				else if (this.gameState.getGameMap().getMap()[i][j] == ' ') {
					gameImages[j][i] = dFloor;
					continue;
				}
			}
		}
		this.heroX = this.offsetW;
		this.heroY = this.offsetH;
		this.guardX = 7 * this.offsetW;
		this.guardY = this.offsetH;
	}

}
