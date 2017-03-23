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

import dkeep.gui.PanelManager.Event;


import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionEvent;

public class PanelLevelEditor extends JPanel implements MouseListener, MouseMotionListener{
	
	private PanelManager pm;
	
	//MOUSE BOOLEANS
	private boolean heroSelected = false, ogreSelected = false, doorSelected = false, wallSelected = false, keySelected = false;
	
	//IMAGES
	private BufferedImage dFloor, dWall, dDoor, key;
	private BufferedImage heroS, sOgre;
	private BufferedImage sideHero, sideOgre, sideWall, sideDoor, sideKey;
	private BufferedImage itemSelected;
	
	//JCOMPONENTS
	private JSlider grid;
	
	//OTHER VARIABLES
	private int offsetW, offsetH, windowW, windowH;
	private char[][] map;
	private int selX, selY;
	
	class SliderListener implements ChangeListener{
		
		@Override
		public void stateChanged(ChangeEvent arg0) {
			map = new char[grid.getValue()][grid.getValue()];
			
			offsetW = Math.round(windowW / grid.getValue());
			offsetH = Math.round(windowH / grid.getValue());
			try {
				loadImages();
			} catch (IllegalArgumentException | ImagingOpException | IOException e) {
				e.printStackTrace();
			}
			resetMap();
			repaint();
		}	
	}
	
	public void setUpGrid() {
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
	}
	
	public void setDimLabel() {
		JLabel lblGridDimension = new JLabel("Grid dimension");
		lblGridDimension.setFont(new Font("Cooper Black", Font.PLAIN, 17));
		lblGridDimension.setBounds(726, 34, 147, 30);
		add(lblGridDimension);
	}
	
	public void setComLabel() {
		JLabel lblElements = new JLabel("Components");
		lblElements.setFont(new Font("Cooper Black", Font.PLAIN, 17));
		lblElements.setBounds(739, 142, 120, 30);
		add(lblElements);
	}
	
	public void setMapButton() {
		JButton btnSaveMap = new JButton("Save Map");
		btnSaveMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkRequisites('V')){
					try {
						saveMap();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnSaveMap.setFont(new Font("Cooper Black", Font.PLAIN, 16));
		btnSaveMap.setBounds(730, 616, 112, 36);
		add(btnSaveMap);
	}
	
	public void setRetButton() {
		JButton btnReturnToMain = new JButton("Return");
		btnReturnToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pm.stateMachine(Event.EXIT_TO_MENU);
			}
		});
		btnReturnToMain.setFont(new Font("Cooper Black", Font.PLAIN, 13));
		btnReturnToMain.setBounds(730, 657, 112, 23);
		add(btnReturnToMain);
	}
	
	public void setVariables(int windowW, int windowH) {
		this.windowW = windowW;
		this.windowH = windowH;
		this.selX = -1;
		this.selY = -1;
		this.offsetW = Math.round(windowW / grid.getValue());
		this.offsetH = Math.round(windowW / grid.getValue());
	}
	
	public PanelLevelEditor(int windowW, int windowH) throws IllegalArgumentException, ImagingOpException, IOException{
		addMouseListener(this);
		addMouseMotionListener(this);
		this.setVisible(false);
		setLayout(null);
		
		setUpGrid();
		
		setVariables(windowW, windowH);
		
		setDimLabel();
		setComLabel();
		setMapButton();
		setRetButton();
		loadImages();
				
		map = new char[grid.getValue()][grid.getValue()];
		resetMap();
	}
	
	public boolean checkRequisites(char method){
		int heroCount = 0, doorCount = 0, ogreCount = 0, keyCount = 0;
		
		for (int i = 0; i < map.length; i++){
			for (int j = 0; j < map.length; j++){
				if (map[i][j] == 'H'){
					heroCount++;
				}
				else if (map[i][j] == 'I'){
					doorCount++;
				}
				else if (map[i][j] == 'O'){
					ogreCount++;
				}
				else if (map[i][j] == 'k'){
					keyCount++;
				}
			}
		}
		
		if (method == 'P'){
			if (heroCount == 1 && heroSelected){
				return false;
			}
			return true;
		}
		else if (method == 'V'){
			if (heroCount < 1){
				JLabel l = new JLabel("<html><i>This dungeon sure's feeling oddly quiet.</i><br><font color = 'red'>Add <u>one donkey</u>!</font></html>");
				JOptionPane.showMessageDialog(this, l, "Invalid Map", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if (doorCount < 1){
				JLabel l = new JLabel("<html><i>Even prisons have a way out!</i><br><font color = 'red'>Add at least <u>one door</u>!</font></html>");
				JOptionPane.showMessageDialog(this, l, "Invalid Map", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if (ogreCount < 1){
				JLabel l = new JLabel("<html><i>Friendships first, dungeoning later.</i><br><font color = 'red'>Add at least <u>one ogre</u>!</font></html>");
				JOptionPane.showMessageDialog(this, l, "Invalid Map", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if (keyCount < 1){
				JLabel l = new JLabel("<html><i>Perseverance is key. Also you need a key, literally.</i><br><font color = 'red'>Add at least <u>one key</u>!</font></html>");
				JOptionPane.showMessageDialog(this, l, "Invalid Map", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return true;
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
		this.sideWall = Scalr.resize(ImageIO.read(new File("res/sprites/static/dwall.png")), 60);
		this.sideDoor = Scalr.resize(ImageIO.read(new File("res/sprites/static/ddoor.png")), 60);
		this.sideKey = Scalr.resize(ImageIO.read(new File("res/sprites/static/key.png")), 60);
		
		//OTHERS
		this.itemSelected = Scalr.resize(ImageIO.read(new File("res/sprites/other/selected_item.png")), 90);
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
		
		if (checkRequisites('P')){
			paintSelSquare(g, new Color(0, 255, 0, 127));			
		}else{
			paintSelSquare(g, new Color(255, 0, 0, 127));
		}
	}
	
	public void paintSelSquare(Graphics g, Color c){
		if (selX != -1 && selY != -1){
			g.setColor(c);
			g.fillRect(selX * offsetW, selY * offsetH, offsetW, offsetH);
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getX() >= 750 && e.getX() <= 850 && e.getY() <= 260 && e.getY() >= 180){
			resetSidebarSel();
			heroSelected = true;
		}
		else if (e.getX() >= 750 && e.getX() <= 850 && e.getY() <= 360 && e.getY() >= 280){
			resetSidebarSel();
			ogreSelected = true;			
		}
		else if (e.getX() >= 750 && e.getX() <= 850 && e.getY() <= 440 && e.getY() >= 380){
			resetSidebarSel();
			wallSelected = true;			
		}
		else if (e.getX() >= 750 && e.getX() <= 850 && e.getY() <= 520 && e.getY() >= 460){
			resetSidebarSel();
			doorSelected = true;			
		}
		else if (e.getX() >= 750 && e.getX() <= 850 && e.getY() <= 600 && e.getY() >= 540){
			resetSidebarSel();
			keySelected = true;			
		}
		
		int i = Math.round(e.getX()/offsetW);
		int j = Math.round(e.getY()/offsetH);
		
		if (e.getX() >= offsetW && e.getX() <= (grid.getValue()-1)*offsetW && e.getY() >= offsetW && e.getY() <= (grid.getValue()-1)*offsetW && heroSelected && checkRequisites('P')) {
			map[i][j] = 'H';
		}
		else if(e.getX() >= offsetW && e.getX() <= (grid.getValue()-1)*offsetW && e.getY() >= offsetW && e.getY() <= (grid.getValue()-1)*offsetW && ogreSelected && checkRequisites('P')) {
			map[i][j] = 'O';
		}
		else if(e.getX() >= 0 && e.getX() <= grid.getValue()*offsetW && e.getY() >= 0 && e.getY() <= grid.getValue()*offsetW && wallSelected && checkRequisites('P')) {
			map[i][j] = 'X';
		}
		else if(e.getX() >= 0 && e.getX() <= grid.getValue()*offsetW && e.getY() >= 0 && e.getY() <= grid.getValue()*offsetW && doorSelected && checkRequisites('P')) {
			map[i][j] = 'I';
		}
		else if(e.getX() >= offsetW && e.getX() <= (grid.getValue()-1)*offsetW && e.getY() >= offsetW && e.getY() <= (grid.getValue()-1)*offsetW && keySelected) {
			map[i][j] = 'k';
		}
		repaint();
    }
	
	public void resetSidebarSel(){
		heroSelected = false;
		ogreSelected = false;
		wallSelected = false;
		doorSelected = false;
		keySelected = false;
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

		if (heroSelected){
			g.drawImage(itemSelected, 745, 180, this);
		}
		else if (ogreSelected){
			g.drawImage(itemSelected, 740, 270, this);
		}
		else if (wallSelected){
			g.drawImage(itemSelected, 735, 360, this);
		}
		else if (doorSelected){
			g.drawImage(itemSelected, 735, 445, this);
		}
		else if (keySelected){
			g.drawImage(itemSelected, 735, 520, this);
		}
	}
	
	public void setPanelManager(PanelManager pm){
		this.pm = pm;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("Mouse clicked");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		System.out.println("Mouse Exited");
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (e.getX() >= 0 && e.getX() <= grid.getValue()*offsetW && e.getY() >= 0 && e.getY() <= grid.getValue()*offsetW){
			this.selX = Math.round(e.getX()/offsetW);
			this.selY = Math.round(e.getY()/offsetH);
			System.out.println(selX + " " + selY);
			repaint();
		}else{
			this.selX = -1;
			this.selY = -1;
		}
		
	}
}
