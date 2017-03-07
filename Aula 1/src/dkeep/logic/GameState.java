package dkeep.logic;

import java.util.Random;
import java.util.*;

public class GameState {
	
	// GAME ENTITIES
	private GameMap map;
	public Hero hero = new Hero();
	public Guard guard = new Guard();
	public ArrayList<Ogre> ogres = new ArrayList<Ogre>();  //Ogres spawned on the map.
	public int keyX, keyY;
	
	public GameState() {
	}
	
	public GameState(GameMap map) {
		super();
		this.map = map;
		spawnEntities();
	}
	
	public void spawnEntities(){
		char[][] thisMap = map.getMap();
		
		for (int i = 0; i < thisMap.length; i++){
			for (int j = 0; j < thisMap[i].length; j++){
				if (thisMap[i][j] == 'H'){
					thisMap[i][j] = ' ';
					spawnHero(i, j);
				}
				else if (thisMap[i][j] == 'G'){
					thisMap[i][j] = ' ';
					spawnGuard(i, j);
				}
				else if (thisMap[i][j] == 'k'){
					thisMap[i][j] = ' ';
					this.keyX = i;	this.keyY = j;
				}
			}
		}
		this.map.setMap(thisMap);
	}
	
	public boolean processMove(String move) throws InterruptedException{
		int m = hero.moveHero(this.map, move);
		
		if (this.map instanceof DungeonMap){
			if (m == 0){
				guard.moveGuard();
				drawMap();
			}
			else if (m == 1){
				guard.moveGuard();
				drawMap();
				System.out.println("Level complete!\n"); Thread.sleep(1500);
			}
			else if (m == -1){
				System.out.println("Invalid move!\n");
			}
			if (hero.heroSpotted(guard)){
				System.out.println("You got caught, doofus!\n");
			}
		}
		else if (this.map instanceof OgreMap){
			if (m == 0) {
				moveEveryOgre();
				hero.moveHeroClub(this);
				drawMap();
			}
			else if (m == 1){
				moveEveryOgre();
				hero.moveHeroClub(this);
				drawMap();
				System.out.println("Level complete!\n"); Thread.sleep(1500);
			}
			else if (m == 2) {
				moveEveryOgre();
				hero.moveHeroClub(this);
				hero.setSymbol('K');
				drawMap();
			}
			else if (m == -1){
				System.out.println("Invalid move!\n");	
			}
			for (int i = 0; i < ogres.size(); i++){
				if (ogres.get(i).heroAdjacent(hero)){
					System.out.println("You got caught, doofus!\n");
					return false;
				}
				hero.stunOgre(ogres.get(i));
			}
		}
		return true;
	}
	
	public GameMap getGameMap(){
		return this.map;
	}
	public void setGameMap(GameMap map){
		this.map = map;
		spawnEntities();
	}
	public void drawMap(){
		char[][] currentMap = map.getMap();
		
		for (int i = 0; i < currentMap.length; i++){
			resume:
			for (int j = 0; j < currentMap[i].length; j++){
				if (i == hero.getX() && j == hero.getY() && hero.getX() != 0 && hero.getY() != 0){
					System.out.print(hero.getSymbol() + " ");  //Display do herói.
					continue;
				}
				else if (i == hero.getClubX() && j == hero.getClubY()) {
					System.out.print(hero.getClubSymbol() + " "); //Display do club do herói
					continue;
				}
				
				else if (i == guard.getX() && j == guard.getY() && guard.getX() != 0 && guard.getY() != 0){
					System.out.print(guard.getSymbol() + " ");  //Display do guarda.
					continue;
				}
				else if (i == this.keyX && j == this.keyY){
					System.out.print("k ");
					continue;
				}
				for (int k = 0; k < ogres.size(); k++){  //Verifica se existe um ogre nesta posição.
					if (ogres.get(k).getX() == i && ogres.get(k).getY() == j){
						System.out.print(ogres.get(k).getSymbol() + " ");  //Display de um ogre.
						continue resume;  //Resume a iteração pelo mapa.
					}
					else if (ogres.get(k).getClubX() == i && ogres.get(k).getClubY() == j){
						System.out.print(ogres.get(k).getClubSymbol() + " ");  //Display do club de um ogre.
						continue resume;
					}
				}
				System.out.print(currentMap[i][j] + " ");  //Display da célula da planta (nenhum objeto encontrado).
			}
			System.out.print("\n");
		}
	}
	
	public void spawnHero(int x, int y){
		Hero h = new Hero(x, y, this.map.getName());
		hero = h;
	}
	
	public void spawnGuard(int x, int y){
		Random rnd = new Random();
		int guardGen = rnd.nextInt(3);
		
		if (guardGen == 0){
			GuardRookie rookie = new GuardRookie(x,y);
			guard = rookie;
		}
		else if (guardGen == 1){
			GuardDrunk drunk = new GuardDrunk(x,y);
			guard = drunk;
		}
		else if (guardGen == 2){
			GuardSuspicious suspicious = new GuardSuspicious(x,y);
			guard = suspicious;
		}
		return;
	}
	
	public void spawnOgres(){
		
		Random rnd = new Random();
		int ogresToGenerate = rnd.nextInt(4) + 1;
		
		for (int i = 0; i < ogresToGenerate; i++){
			int xPosition = rnd.nextInt(9), yPosition = rnd.nextInt(9);
			
			if (this.hero.getX() != xPosition && this.hero.getY() != yPosition && this.getGameMap().getMap()[xPosition][yPosition] == ' '){
				Ogre ogre = new Ogre(xPosition, yPosition);
				ogres.add(ogre);
			}else{
				i--;
			}
		}
		return;
	}
	
	public void spawnOgres(int generations){
		
		Random rnd = new Random();
		
		for (int i = 0; i < generations; i++){
			int xPosition = rnd.nextInt(9), yPosition = rnd.nextInt(9);
			
			if (this.hero.getX() != xPosition && this.hero.getY() != yPosition && this.getGameMap().getMap()[xPosition][yPosition] == ' '){
				Ogre ogre = new Ogre(xPosition, yPosition);
				ogres.add(ogre);
			}else{
				i--;
			}
		}
		return;
	}
	
	
	public void spawnKey(int x, int y){
		this.keyX = x;
		this.keyY = y;
	}
	public void moveEveryOgre(){
		for (int i = 0; i < ogres.size(); i++){
			ogres.get(i).move(this);
		}
	}
}
