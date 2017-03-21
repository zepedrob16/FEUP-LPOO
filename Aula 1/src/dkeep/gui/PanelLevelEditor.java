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
import javax.swing.JLabel;
import java.awt.Font;


public class PanelLevelEditor extends JPanel {
	
	GameState state;
	private PanelManager pm;
	
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
		this.setVisible(false);
		state = new GameState();
		setLayout(null);
		
		this.windowW = windowW;
		this.windowH = windowH;
		
		grid = new JSlider();
		grid.setBounds(719, 72, 147, 52);
		grid.setMinimum(5);
		grid.setMaximum(25);
		grid.setMajorTickSpacing(5);
		grid.setPaintLabels(true);
		grid.setPaintTicks(true);
		grid.addChangeListener(new SliderListener());
		add(grid);
		
		this.offsetW = Math.round(windowW / grid.getValue());
		this.offsetH = Math.round(windowW / grid.getValue());
		
		JLabel lblGridDimension = new JLabel("Grid dimension");
		lblGridDimension.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		lblGridDimension.setBounds(726, 35, 120, 30);
		add(lblGridDimension);
		
		JLabel lblElements = new JLabel("Components");
		lblElements.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		lblElements.setBounds(727, 140, 120, 30);
		add(lblElements);
		
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
		this.heroS = Scalr.resize(ImageIO.read(new File("res/sprites/hero/0.png")), 90);
		this.guardS = Scalr.resize(ImageIO.read(new File("res/sprites/guard/0.png")), 85);
		this.sOgre = Scalr.resize(ImageIO.read(new File("res/sprites/ogre/209.png")), 80);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		for (int i = 0; i < grid.getValue(); i++){
			for (int j = 0; j < grid.getValue(); j++){
				g.drawImage(dFloor, i * offsetH, j * offsetW, this);
			}
		}
		g.drawImage(heroS, 750, 180, this);
		g.drawImage(sOgre, 750, 280, this);
		g.drawImage(dWall, 750, 480, this);
		g.drawImage(dDoor, 790, 480, this);
		g.drawImage(dLeverOn, 750, 520, this);
		g.drawImage(key, 790, 520, this);
	}
	
	public void setPanelManager(PanelManager pm){
		this.pm = pm;
	}
}
