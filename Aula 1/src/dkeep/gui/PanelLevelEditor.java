package dkeep.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.imgscalr.Scalr;

import dkeep.logic.GameState;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class PanelLevelEditor extends JPanel {
	
	GameState state;
	
	//IMAGES
	private BufferedImage dFloor, dFloorBlood, dFloorGrass1, dWall, dDoor, dLeverOn, key;
	private BufferedImage heroS, guardS, sOgre;
	
	//JCOMPONENTS
	private JSlider grid;
	
	//MATH VARIABLES
	private int offsetW, offsetH;
	private int windowW, windowH;
	
	class SliderListener implements ChangeListener{
		
		@Override
		public void stateChanged(ChangeEvent arg0) {
			offsetW = Math.round(windowW / grid.getValue());
			offsetH = Math.round(windowH / grid.getValue());
			try {
				loadImages();
			} catch (IllegalArgumentException | ImagingOpException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			repaint();
		}	
	}
	
	public PanelLevelEditor(int windowW, int windowH) throws IllegalArgumentException, ImagingOpException, IOException {
		state = new GameState();
		setLayout(null);
		
		this.windowW = windowW;
		this.windowH = windowH;
		
		grid = new JSlider();
		grid.setBounds(720, 133, 153, 52);
		grid.setMinimum(5);
		grid.setMaximum(25);
		grid.setMajorTickSpacing(5);
		grid.setPaintLabels(true);
		grid.setPaintTicks(true);
		grid.addChangeListener(new SliderListener());
		add(grid);
		
		this.offsetW = Math.round(windowW / grid.getValue());
		this.offsetH = Math.round(windowW / grid.getValue());
		
		loadImages();
				
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
		
		for (int i = 0; i < grid.getValue(); i++){
			for (int j = 0; j < grid.getValue(); j++){
				g.drawImage(dFloor, i * offsetH, j * offsetW, this);
			}
		}
	}

}
