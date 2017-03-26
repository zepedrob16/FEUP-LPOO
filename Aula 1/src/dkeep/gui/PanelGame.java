package dkeep.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import org.imgscalr.Scalr;

import dkeep.gui.PanelManager.Event;
import dkeep.logic.DungeonMap;
import dkeep.logic.GameMap;
import dkeep.logic.GameState;
import dkeep.logic.GameState.State;
import dkeep.logic.Ogre;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import dkeep.logic.OgreMap;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;

public class PanelGame extends JPanel implements KeyListener, ActionListener {

	private GameState gameState, newState;
	
	private int grid, windowW, windowH, offset, fDonkeyIter;
	public int ogreNumber;
	public String guardPersonality;
	
	private BufferedImage dFloor, dFloorBlood, dFloorGrass1, dWall, dDoor, dDoorOpen, dLeverOn, dLeverOff, sDonkey, sBarrel, key, screenGameOver, screenKeepComplete, screenLevelComplete;
	private BufferedImage[] fDonkey, fGuard, fOgre;
	private BufferedImage[][] gameImages;
	
	private static MediaPlayer mediaPlayer;
	private Media doorOpen;
	
	private Timer fps;
	
	private boolean soundPlayed = false;
	
	private PanelManager pm;
	private JLabel lblMessage;
	
	private boolean alreadyAdapted = false;
	
	
	public PanelGame(int windowW, int windowH) throws IOException {
		this.setVisible(false);
		this.windowW = windowW;
		this.windowH = windowH;
		setLayout(null);
		
		lblMessage = new JLabel("");
		lblMessage.setForeground(Color.BLACK);
		lblMessage.setBackground(new Color(255,255,255, 220));
		lblMessage.setOpaque(true);
		lblMessage.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		lblMessage.setBounds(12, 13, 168, 25);
		add(lblMessage);
	}
	
	public void loadMap(GameMap map) throws IOException {
		gameState = new GameState(map);
		gameImages = new BufferedImage[map.getMap().length][map.getMap().length];
		setUpVariables(windowW, windowH);
		
		this.loadImages();
		this.setFloor();
		
		fps = new Timer(500, this);
		fps.start();
		setLayout(null);
		setUpButtons();
	}
	
	public void disableLabel(){
		lblMessage.setText(this.gameState.message);
		
		if (lblMessage.getText() == null || lblMessage.getText() == "" || lblMessage.getText() == "Level complete!" || lblMessage.getText() == "GAME OVER!"){
			lblMessage.setVisible(false);
		}else{
			lblMessage.setVisible(true);
		}
	}
	
	public void setUpVariables(int windowW,int windowH) {
		this.windowW = windowW;
		this.windowH = windowH;
		this.grid = this.gameState.getGameMap().getMap().length;
		this.offset = Math.round(this.windowW / this.grid);
	}
	
	public void setButtonUp(){
		JButton btnUp = new JButton("\u25B2");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				requestFocusInWindow();
				gameState.processMove("w");
				repaint();
			}
		});
		btnUp.setBounds(58, 497, 48, 48);
		add(btnUp);
	}
	
	public void setButtonLeft(){
		JButton btnLeft = new JButton("\u25C0");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestFocusInWindow();
				gameState.processMove("a");
				repaint();
			}
		});
		btnLeft.setBounds(12, 543, 48, 48);
		add(btnLeft);
	}
	
	public void setButtonRight() {
		JButton btnRight = new JButton("\u25B6");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestFocusInWindow();
				gameState.processMove("d");
				repaint();
			}
		});
		btnRight.setBounds(104, 543, 48, 48);
		add(btnRight);
	}
	
	public void setButtonDown() {
		JButton btnDown = new JButton("\u25BC");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestFocusInWindow();
				gameState.processMove("s");
				repaint();
			}
		});
		btnDown.setBounds(58, 589, 48, 48);
		add(btnDown);
	}
	
	public void setUpButtons() {
		setButtonUp();
		setButtonLeft();
		setButtonRight();
		setButtonDown();
	}
	
	public void loadImages() throws IOException{
		
		//STATIC IMAGES
		this.dFloor = Scalr.resize(ImageIO.read(new File("res/sprites/static/dfloor.png")), this.offset);
		this.dFloorBlood = Scalr.resize(ImageIO.read(new File("res/sprites/static/dfloorblood.png")), this.offset);
		this.dFloorGrass1 = Scalr.resize(ImageIO.read(new File("res/sprites/static/dfloorgrass1.png")), this.offset);
		this.dWall = Scalr.resize(ImageIO.read(new File("res/sprites/static/dwall.png")), this.offset);
		this.dDoor = Scalr.resize(ImageIO.read(new File("res/sprites/static/ddoor.png")), this.offset);
		this.dDoorOpen = Scalr.resize(ImageIO.read(new File("res/sprites/static/ddooropen.png")), this.offset);
		this.dLeverOn = Scalr.resize(ImageIO.read(new File("res/sprites/static/dleveron.png")), this.offset);
		this.dLeverOff = Scalr.resize(ImageIO.read(new File("res/sprites/static/dleveroff.png")), this.offset);
		this.key = Scalr.resize(ImageIO.read(new File("res/sprites/static/key.png")), this.offset - 10);
		
		//SCREENS
		this.screenLevelComplete = Scalr.resize(ImageIO.read(new File("res/level_complete_screen.png")), 600);
		this.screenKeepComplete = Scalr.resize(ImageIO.read(new File("res/keep_complete_screen.png")), 600);
		this.screenGameOver = Scalr.resize(ImageIO.read(new File("res/game_over_screen.png")), 600);
		
		//ENTITIES
		this.fDonkey = new BufferedImage[2];
		this.fDonkey[0] = Scalr.resize(ImageIO.read(new File("res/sprites/hero/0.png")), this.offset);
		this.fDonkey[1] = Scalr.resize(ImageIO.read(new File("res/sprites/hero/2.png")), this.offset);
		this.sDonkey = this.fDonkey[0];
		this.fGuard = new BufferedImage[2];
		this.fGuard[0] = Scalr.resize(ImageIO.read(new File("res/sprites/guard/0.png")), this.offset);
		this.fGuard[1] = Scalr.resize(ImageIO.read(new File("res/sprites/guard/36.png")), this.offset);
		this.fOgre = new BufferedImage[2];
		this.fOgre[0] = Scalr.resize(ImageIO.read(new File("res/sprites/ogre/209.png")), this.offset); 
		this.fOgre[1] = Scalr.resize(ImageIO.read(new File("res/sprites/ogre/116.png")), this.offset); 
		this.sBarrel = Scalr.resize(ImageIO.read(new File("res/sprites/ogre/158.png")), this.offset);
		
		//SOUNDS
		this.doorOpen = new Media(new File("res/sound/open_door.mp3").toURI().toString());
	}
	
	@Override
	public void paintComponent(Graphics g){
		if (gameState.getGameMap() instanceof OgreMap && !this.alreadyAdapted){
			try { adjustGridToWindow(); } catch (IOException e1) { e1.printStackTrace(); }
			alreadyAdapted = true;
		}
		
		super.paintComponent(g);
		
		char[][] map = this.gameState.getGameMap().getMap();
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				
				if (map[i][j] == 'X'){
					g.drawImage(dWall, j * this.offset, i * this.offset, this);
				}
				else if (map[i][j] == 'I'){
					g.drawImage(dDoor, j * this.offset, i * this.offset, this);
				}
				else if (map[i][j] == 'S'){
					g.drawImage(dDoorOpen, j * this.offset, i * this.offset, this);
					checkSound(g, i, j);
				}
				else if (map[i][j] == ' '){
					g.drawImage(gameImages[i][j], j * this.offset, i * this.offset, this);
				}
				mapSpecificDisplays(g, i, j);
				
				if (i == this.gameState.hero.getX() && j == this.gameState.hero.getY()){
					g.drawImage(sDonkey, j * offset, i * offset, this);
				}				
			}
		}
		displayStatePanels(g);
		disableLabel();		
		
	}
	public void mapSpecificDisplays(Graphics g, int i, int j){
		if (this.gameState.getGameMap() instanceof DungeonMap){
			if (i == gameState.keyX && j == gameState.keyY && gameState.leverOn){
				g.drawImage(dLeverOn, j * this.offset, i * this.offset, this);
			}
			else if (i == gameState.keyX && j == gameState.keyY && !gameState.leverOn){
				g.drawImage(dLeverOff, j * this.offset, i * this.offset, this);
			} 
			if (this.gameState.guard != null && i == gameState.guard.getX() && j == gameState.guard.getY() && this.gameState.guard.getSleeping()){
				g.drawImage(this.fGuard[1], j * offset, i * offset, this);
			}
			else if (this.gameState.guard != null && i == gameState.guard.getX() && j == gameState.guard.getY() && !this.gameState.guard.getSleeping()) {
				g.drawImage(this.fGuard[0], j * offset, i * offset, this);
			}
		}
		else if (this.gameState.getGameMap() instanceof OgreMap){
			if (i == gameState.keyX && j == gameState.keyY){
				g.drawImage(key, j * this.offset, i * this.offset, this);
			}
			checkOgres(g, i, j);
		}
	}
	
	public void adjustGridToWindow() throws IOException{
		this.grid = this.gameState.getGameMap().getMap().length;
		this.offset = Math.round(this.windowW / this.grid);
		loadImages();
		setFloor();
	}
	
	public void checkOgres(Graphics g, int i, int j){
		ArrayList<Ogre> ogres = gameState.ogres;
		for (int k = 0; k < ogres.size(); k++){
			if (i == ogres.get(k).getX() && j == ogres.get(k).getY() && ogres.get(k).getStunned()){
				g.drawImage(fOgre[1], j * offset, i * offset, this);
			}
			else if (i == ogres.get(k).getX() && j == ogres.get(k).getY() && !ogres.get(k).getStunned()){
				g.drawImage(fOgre[0], j * offset, i * offset, this);
			}
			else if (i == ogres.get(k).getClubX() && j == ogres.get(k).getClubY()){
				g.drawImage(sBarrel, j * offset, i * offset, this);
			}
		}
	}
	
	public void checkSound(Graphics g, int i, int j){
		GameMap thisMap = gameState.getGameMap();
		if (!soundPlayed && thisMap instanceof OgreMap){
			playSound(doorOpen);
			soundPlayed = true;
		}
		else if (!soundPlayed && thisMap instanceof DungeonMap && i != this.gameState.hero.getX() && j != this.gameState.hero.getY()){
			playSound(doorOpen);
			soundPlayed = true;
		}
	}
	
	public void displayStatePanels(Graphics g){
		State thisState = gameState.getState();
		GameMap thisMap = gameState.getGameMap();
		
		if (thisState == State.VICTORY && thisMap instanceof DungeonMap){
			g.setColor(new Color(0, 0, 0, 140));
			g.fillRect(0, 0, this.windowH, this.windowW);
			g.drawImage(screenLevelComplete, 30, 220, this);
			this.soundPlayed = false;
		}
		else if (thisState == State.VICTORY && thisMap instanceof OgreMap){
			g.setColor(new Color(0, 0, 0, 140));
			g.fillRect(0, 0, this.windowH, this.windowW);	
			g.drawImage(screenKeepComplete, 30, 220, this);
		}
		else if (thisState == State.DEFEAT){
			g.setColor(new Color(0, 0, 0, 140));
			g.fillRect(0, 0, this.windowH, this.windowW);
			g.drawImage(screenGameOver, 30, 220, this);
		}
	}
	
	public void setOgreMap(GameMap oMap){
		gameState.setOgreMap(oMap);
		gameState.setOgres(ogreNumber);
		gameImages = new BufferedImage[oMap.getMap().length][oMap.getMap().length];
		setFloor();
	}
	
	public void playSound(Media media){
		JFXPanel fxPanel = new JFXPanel();
		
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(0.20);
		mediaPlayer.play();
	}
	
	public void setFloor() {
		Random rnd = new Random();
		int gridSize = this.gameState.getGameMap().getMap().length;
		
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				int genGrass = rnd.nextInt(4), genBlood = rnd.nextInt(20);

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
	
	public void setPanelManager(PanelManager pm){
		this.pm = pm;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (gameState.getState() == State.DEFEAT || (gameState.getState() == State.VICTORY && this.gameState.getGameMap() instanceof OgreMap)){
			pm.stateMachine(Event.EXIT_TO_MENU);
			return;
		}
		int key = e.getKeyCode();
		processMovementKeys(key);
		processSerialization(key);
		processGameExit(key);
		
	}
	public void processMovementKeys(int key){
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
	}
	public void processSerialization(int key){
		if (key == 116){
			try {serializeState();} catch (IOException e1) {}
			gameState.message = "Game saved!";
			repaint();
		}
		else if (key == 117){
			try {unserializeState(newState);} catch (ClassNotFoundException | IOException e1) {}
			gameState.message = "Game loaded!";
			repaint();
		}
	}
	public void processGameExit(int key){
		if (key == 27){
			String options[] = {"Yes", "No"};
			int select = JOptionPane.showOptionDialog(this, "Return to main menu?\n(Unsaved progress will be lost!)", "Return to Main Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
			if (select == 0){
				pm.stateMachine(Event.EXIT_TO_MENU);
				System.out.println(pm.getState());
			}
		}
	}

	public void serializeState() throws IOException {
		FileOutputStream fileOut = new FileOutputStream("res/quicksaves/temp_qs.ser");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(gameState);
		out.close();
		fileOut.close();
	}
	
	public void unserializeState(GameState newState) throws IOException, ClassNotFoundException {
		GameState g = null;
		FileInputStream fileIn = new FileInputStream("res/quicksaves/temp_qs.ser");
		ObjectInputStream in = new ObjectInputStream(fileIn);
		g = (GameState) in.readObject();
		in.close();
		fileIn.close();
		gameState = new GameState(g);
		repaint();
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		return;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		return;
	}
	public void startGame() {
		gameState = new GameState();
		gameState.setGameMap(new DungeonMap());
		gameState.setPers(guardPersonality);
		gameState.setGameMap(new DungeonMap());
		gameState.setOgres(ogreNumber);
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.fDonkeyIter++;
		if (this.fDonkeyIter == 2){
			this.fDonkeyIter = 0;
		}
		this.sDonkey = this.fDonkey[this.fDonkeyIter];
		repaint();
	}
}
