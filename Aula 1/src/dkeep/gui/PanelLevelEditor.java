package dkeep.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.imgscalr.Scalr;

import dkeep.logic.GameState;


import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;


public class PanelLevelEditor extends JPanel implements MouseListener{
	
	GameState state;
	private PanelManager pm;
	
	//BOOLEANS FOR THE MOUSE
	private boolean heroSelected = false, ogreSelected = false, doorSelected = false, wallSelected = false, keySelected = false;
	
	//IMAGES
	private BufferedImage dFloor, dWall, dDoor, key;
	private BufferedImage heroS, sOgre;
	private BufferedImage sideHero, sideOgre, sideFloor, sideWall, sideDoor, sideLever, sideKey;
	
	//JCOMPONENTS
	private JSlider grid;
	
	//OTHER VARIABLES
	private int offsetW, offsetH, windowW, windowH;
	private char[][] map;
	
	class SliderListener implements ChangeListener{
		
		@Override
		public void stateChanged(ChangeEvent arg0) {
			map = new char[grid.getValue()][grid.getValue()];
			
			offsetW = Math.round(windowW / grid.getValue());
			offsetH = Math.round(windowH / grid.getValue());
			try {
				loadImages();
			} catch (IllegalArgumentException | ImagingOpException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resetMap();
			repaint();
		}	
	}
	
	public PanelLevelEditor(int windowW, int windowH) throws IllegalArgumentException, ImagingOpException, IOException{
		addMouseListener(this);
		
		this.setVisible(false);
		state = new GameState();
		setLayout(null);
		
		this.windowW = windowW;
		this.windowH = windowH;
		
		grid = new JSlider();
		grid.setBounds(719, 72, 147, 52);
		grid.setValue(10);
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
		lblGridDimension.setFont(new Font("Cooper Black", Font.PLAIN, 17));
		lblGridDimension.setBounds(726, 34, 147, 30);
		add(lblGridDimension);
		
		JLabel lblElements = new JLabel("Components");
		lblElements.setFont(new Font("Cooper Black", Font.PLAIN, 17));
		lblElements.setBounds(739, 142, 120, 30);
		add(lblElements);
		
		JButton btnSaveMap = new JButton("Save Map");
		btnSaveMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					saveMap();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSaveMap.setFont(new Font("Cooper Black", Font.PLAIN, 16));
		btnSaveMap.setBounds(726, 631, 112, 52);
		add(btnSaveMap);
		
		loadImages();
				
		map = new char[grid.getValue()][grid.getValue()];
		resetMap();
	}
	
	public void loadImages() throws IllegalArgumentException, ImagingOpException, IOException{

		//STATIC IMAGES
		this.dFloor = Scalr.resize(ImageIO.read(new File("res/sprites/static/dfloor.png")), this.offsetH);
		this.dWall = Scalr.resize(ImageIO.read(new File("res/sprites/static/dwall.png")), this.offsetH);
		this.dDoor = Scalr.resize(ImageIO.read(new File("res/sprites/static/ddoor.png")), this.offsetH);
		this.key = Scalr.resize(ImageIO.read(new File("res/sprites/static/key.png")), this.offsetH - 10);
		
		//MAP ENTITIES
		this.heroS = Scalr.resize(ImageIO.read(new File("res/sprites/hero/0.png")), this.offsetH);
		this.sOgre = Scalr.resize(ImageIO.read(new File("res/sprites/ogre/209.png")), this.offsetH);
		
		//SIDEBAR ENTITIES
		this.sideHero = Scalr.resize(ImageIO.read(new File("res/sprites/hero/0.png")), 90);
		this.sideOgre = Scalr.resize(ImageIO.read(new File("res/sprites/ogre/209.png")), 80);
		this.sideFloor = Scalr.resize(ImageIO.read(new File("res/sprites/static/dfloor.png")), 60);
		this.sideWall = Scalr.resize(ImageIO.read(new File("res/sprites/static/dwall.png")), 60);
		this.sideDoor = Scalr.resize(ImageIO.read(new File("res/sprites/static/ddoor.png")), 60);
		this.sideKey = Scalr.resize(ImageIO.read(new File("res/sprites/static/key.png")), 60);
	
	}
	
	public void saveMap() throws FileNotFoundException {
		String fileName = JOptionPane.showInputDialog(this, "Enter save name:");
		PrintWriter out = new PrintWriter("res/saves/" + fileName + ".txt");
		
		for (int i = 0; i < grid.getValue(); i++){
			for (int j = 0; j < grid.getValue(); j++){
				out.print(map[j][i]);
			}
			out.println();
		}
		out.close();
	}
	
	public void resetMap(){
		for (int i = 0; i < grid.getValue(); i++){
			for (int j = 0; j < grid.getValue(); j++){
				if (i == 0 || j == 0 || i == grid.getValue() - 1 || j == grid.getValue() - 1){
					map[i][j] = 'X';
				}else{
					map[i][j] = ' ';
				}
			}
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		for (int i = 0; i < grid.getValue(); i++){
			for (int j = 0; j < grid.getValue(); j++){
				if (map[i][j] == ' '){
					g.drawImage(dFloor, i * offsetW, j * offsetH, this);
				}
				else if (map[i][j] == 'H'){
					g.drawImage(dFloor, i * offsetW, j * offsetH, this);
					g.drawImage(heroS, i * offsetW, j * offsetH, this);
				}
				else if (map[i][j] == 'O'){
					g.drawImage(dFloor, i * offsetW, j * offsetH, this);
					g.drawImage(sOgre, i * offsetW, j * offsetH, this);
				}
				else if (map[i][j] == 'I'){
					g.drawImage(dFloor, i * offsetW, j * offsetH, this);
					g.drawImage(dDoor, i * offsetW, j * offsetH, this);
				}
				else if (map[i][j] == 'k'){
					g.drawImage(dFloor, i * offsetW, j * offsetH, this);
					g.drawImage(key, i * offsetW, j * offsetH, this);
				}
				else if (map[i][j] == 'X'){
					g.drawImage(dWall, i * offsetW, j * offsetH, this);
				}
			}
		}
		paintSidebar(g);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getX() >= 750 && e.getX() <= 850 && e.getY() <= 260 && e.getY() >= 180)
			heroSelected = true;
		else if (e.getX() >= 750 && e.getX() <= 850 && e.getY() <= 360 && e.getY() >= 280) 		
			ogreSelected = true;
		else if (e.getX() >= 750 && e.getX() <= 850 && e.getY() <= 440 && e.getY() >= 380) 	
			wallSelected = true;
		else if (e.getX() >= 750 && e.getX() <= 850 && e.getY() <= 520 && e.getY() >= 460) 	
			doorSelected = true;
		else if (e.getX() >= 750 && e.getX() <= 850 && e.getY() <= 600 && e.getY() >= 540) 	
			keySelected = true;
		
		
		if (e.getX() >= offsetW && e.getX() <= (grid.getValue()-1)*offsetW && e.getY() >= offsetW && e.getY() <= (grid.getValue()-1)*offsetW && heroSelected) {
			int i = Math.round(e.getX()/offsetW);
			int j = Math.round(e.getY()/offsetH);
			map[i][j] = 'H';
			repaint();
			heroSelected = false;
		}
		else if(e.getX() >= offsetW && e.getX() <= (grid.getValue()-1)*offsetW && e.getY() >= offsetW && e.getY() <= (grid.getValue()-1)*offsetW && ogreSelected) {
			int i = Math.round(e.getX()/offsetW);
			int j = Math.round(e.getY()/offsetH);
			map[i][j] = 'O';
			repaint();
			ogreSelected = false;
		}
		else if(e.getX() >= 0 && e.getX() <= grid.getValue()*offsetW && e.getY() >= 0 && e.getY() <= grid.getValue()*offsetW && wallSelected) {
			int i = Math.round(e.getX()/offsetW);
			int j = Math.round(e.getY()/offsetH);
			map[i][j] = 'X';
			repaint();
			wallSelected = false;
		}
		else if(e.getX() >= 0 && e.getX() <= grid.getValue()*offsetW && e.getY() >= 0 && e.getY() <= grid.getValue()*offsetW && doorSelected) {
			int i = Math.round(e.getX()/offsetW);
			int j = Math.round(e.getY()/offsetH);
			map[i][j] = 'I';
			repaint();
			doorSelected = false;
		}
		else if(e.getX() >= offsetW && e.getX() <= (grid.getValue()-1)*offsetW && e.getY() >= offsetW && e.getY() <= (grid.getValue()-1)*offsetW && keySelected) {
			int i = Math.round(e.getX()/offsetW);
			int j = Math.round(e.getY()/offsetH);
			map[i][j] = 'k';
			repaint();
			keySelected = false;
		}
		else {
			System.out.println(e.getX() + " " + e.getY());
		}
    }
	@Override
	 public void mouseReleased(MouseEvent e) {
		
	        System.out.println("Mouse released");
	    }
	public void paintSidebar(Graphics g){
		g.drawImage(sideHero, 750, 180, this);
		g.drawImage(sideOgre, 750, 280, this);
		g.drawImage(sideWall, 750, 380, this);
		g.drawImage(sideDoor, 750, 460, this);
		g.drawImage(sideKey, 750, 540, this);		
	}
	
	public void setPanelManager(PanelManager pm){
		this.pm = pm;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("Mouse clicked");
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		System.out.println("Mouse Entered");
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		System.out.println("Mouse Exited");
	}
}
