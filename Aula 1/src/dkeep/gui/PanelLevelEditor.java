package dkeep.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.imgscalr.Scalr;
import javax.swing.JSlider;

public class PanelLevelEditor extends JPanel {

	//IMAGES
	private BufferedImage dFloor, dFloorBlood, dFloorGrass1, dWall, dDoor, dLeverOn, key;
	private BufferedImage heroS, guardS, sOgre;
	
	//JCOMPONENTS
	private JSlider gridX, gridY;
	
	//MATH VARIABLES
	private int offsetW, offsetH; 
	
	public PanelLevelEditor(int windowW, int windowH) {
		setLayout(null);
		
		gridX = new JSlider();
		gridX.setBounds(818, 230, 200, 26);
		add(gridX);
		
		gridY = new JSlider();
		gridY.setBounds(818, 124, 200, 26);
		add(gridY);
		
		this.offsetW = Math.round(windowW / gridX.getValue());
		this.offsetH = Math.round(windowH / gridY.getValue());		
				
	}
	public void loadImages() throws IllegalArgumentException, ImagingOpException, IOException{

		//STATIC IMAGES
		this.dFloor = Scalr.resize(ImageIO.read(new File("res/sprites/static/dfloor.png")), this.offsetH);
		this.dFloorBlood = Scalr.resize(ImageIO.read(new File("res/sprites/static/dfloorblood.png")), this.offsetH);
		this.dFloorGrass1 = Scalr.resize(ImageIO.read(new File("res/sprites/static/dfloorgrass1.png")), this.offsetH);
		this.dWall = Scalr.resize(ImageIO.read(new File("res/sprites/static/dwall.png")), this.offsetH);
		this.dDoor = Scalr.resize(ImageIO.read(new File("res/sprites/static/ddoor.png")), this.offsetH);
		this.dLeverOn = Scalr.resize(ImageIO.read(new File("res/sprites/static/dleveron.png")), this.offsetH);
		this.key = Scalr.resize(ImageIO.read(new File("res/sprites/static/key.png")), this.offsetH - 10);
		
		//ENTITIES
		this.heroS = Scalr.resize(ImageIO.read(new File("res/sprites/hero/0.png")), this.offsetH);
		this.guardS = Scalr.resize(ImageIO.read(new File("res/sprites/guard/0.png")), this.offsetH);
		this.sOgre = Scalr.resize(ImageIO.read(new File("res/sprites/ogre/0.png")), this.offsetH);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
	}

}
