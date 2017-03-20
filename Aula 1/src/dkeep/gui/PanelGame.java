package dkeep.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.swing.JPanel;

import org.imgscalr.Scalr;

import dkeep.logic.DungeonMap;
import dkeep.logic.GameState;
import dkeep.logic.GameState.State;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class PanelGame extends JPanel implements KeyListener {
	
	private GameState gameState;
	
	private int gridW, gridH, windowW, windowH, offsetW, offsetH, heroX, heroY, guardX, guardY;
	
	public int ogreNumber;
	public String guardPersonality;
	private BufferedImage[][] gameImages = new BufferedImage[10][10];
	
	private BufferedImage dFloor, dFloorBlood, dFloorGrass1, dFloorGrass2, dFloorWater, dFloorBarricade, dWall, dDoor, dDoorOpen, dLeverOn, dLeverOff;
	private BufferedImage heroS, guardS;
	private BufferedImage key;
	
	public PanelGame(int windowW, int windowH) throws IOException {
		
		
		gameState = new GameState();
		gameState.setGameMap(new DungeonMap());
		this.windowW = windowW;
		this.windowH = windowH;
		this.gridW = this.gameState.getGameMap().getMap().length;
		this.gridH = this.gameState.getGameMap().getMap()[0].length;
		this.offsetW = Math.round(this.windowW / this.gridW);
		this.offsetH = Math.round(this.windowH / this.gridH);
		
		this.loadImages();
		this.setFloor();
		
		setLayout(null);
	}
	
	public void loadImages() throws IOException{
		
		//STATIC IMAGES
		this.dFloor = Scalr.resize(ImageIO.read(new File("res/sprites/static/dfloor.png")), this.offsetH);
		this.dFloorBlood = Scalr.resize(ImageIO.read(new File("res/sprites/static/dfloorblood.png")), this.offsetH);
		this.dFloorGrass1 = Scalr.resize(ImageIO.read(new File("res/sprites/static/dfloorgrass1.png")), this.offsetH);
		this.dFloorGrass2 = Scalr.resize(ImageIO.read(new File("res/sprites/static/dfloorgrass2.png")), this.offsetH);
		this.dFloorWater = Scalr.resize(ImageIO.read(new File("res/sprites/static/dfloorwater.png")), this.offsetH);
		this.dFloorBarricade = Scalr.resize(ImageIO.read(new File("res/sprites/static/dfloorbarricade.png")), this.offsetH);
		this.dWall = Scalr.resize(ImageIO.read(new File("res/sprites/static/dwall.png")), this.offsetH);
		this.dDoor = Scalr.resize(ImageIO.read(new File("res/sprites/static/ddoor.png")), this.offsetH);
		this.dDoorOpen = Scalr.resize(ImageIO.read(new File("res/sprites/static/ddooropen.png")), this.offsetH);
		this.dLeverOn = Scalr.resize(ImageIO.read(new File("res/sprites/static/dleveron.png")), this.offsetH);
		this.dLeverOff = Scalr.resize(ImageIO.read(new File("res/sprites/static/dleveroff.png")), this.offsetH);
		this.key = Scalr.resize(ImageIO.read(new File("res/sprites/static/key.png")), this.offsetH);
		
		//ENTITIES
		this.heroS = Scalr.resize(ImageIO.read(new File("res/sprites/hero/0.png")), this.offsetH);
		this.guardS = Scalr.resize(ImageIO.read(new File("res/sprites/guard/0.png")), this.offsetH);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		char[][] map = this.gameState.getGameMap().getMap();
		
		for (int i = 0; i < this.gridW; i++) {
			for (int j = 0; j < this.gridH; j++) {
				
				if (map[i][j] == 'X'){
					g.drawImage(dWall, j * this.offsetW, i * this.offsetH, this);
				}
				else if (map[i][j] == 'I'){
					g.drawImage(dDoor, j * this.offsetW, i * this.offsetH, this);
				}
				else if (map[i][j] == 'S'){
					g.drawImage(dDoorOpen, j * this.offsetW, i * this.offsetH, this);
				}
				else if (map[i][j] == ' '){
					g.drawImage(gameImages[i][j], j * this.offsetW, i * this.offsetH, this);
				}

				if (i == gameState.keyX && j == gameState.keyY && gameState.leverOn){
					g.drawImage(dLeverOn, j * this.offsetW, i * this.offsetH, this);
				}
				else if (i == gameState.keyX && j == gameState.keyY && !gameState.leverOn){
					g.drawImage(dLeverOff, j * this.offsetW, i * this.offsetH, this);
				} 
				
				if (i == this.gameState.hero.getX() && j == this.gameState.hero.getY()){
					g.drawImage(this.heroS, j * this.offsetW, i * this.offsetH, this);
				}
				else if (i == gameState.guard.getX() && j == gameState.guard.getY()){
					g.drawImage(this.guardS, j * offsetW, i * offsetH, this);
				}	
			}
		}
	}
	
	public void setFloor() {
		Random rnd = new Random();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				int genGrass = rnd.nextInt(4);
				int genBlood = rnd.nextInt(20);

				if (genGrass == 0){
					gameImages[i][j] = dFloorGrass1;					
				}
				else if (genBlood == 0){
					gameImages[i][j] = dFloorBlood;
				}
				else{
					gameImages[i][j] = dFloor;
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (gameState.getState() == State.DEFEAT){
			return;
		}
		
		int key = e.getKeyCode();
		
		
		if (key == 38 || key == 87){
			this.gameState.processMove("w");
			repaint();
		}
		else if (key == 37 || key == 65){
			this.gameState.processMove("a");
			repaint();
		}
		else if (key == 40 || key == 83){
			this.gameState.processMove("s");
			repaint();
		}
		else if (key == 39 || key == 68){
			this.gameState.processMove("d");
			repaint();
		}
		else if (key == 27 && e.isShiftDown()){
			System.exit(0);	
		}
		else if (key == 27){
			JOptionPane jop = new JOptionPane();
			String options[] = {"Yes", "No"};
			int select = JOptionPane.showOptionDialog(this, "Return to main menu?\n(Unsaved progress will be lost!)", "Return to Main Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
			
			if (select == 0){
				this.setVisible(false);
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		return;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		return;
	}

}
