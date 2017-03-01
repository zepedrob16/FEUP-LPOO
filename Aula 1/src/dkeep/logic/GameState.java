package dkeep.logic;

import java.util.Random;

public class GameState {
	private GameMap map;
	
	public Hero hero = new Hero();
	public Guard guard = new Guard();
	public Ogre[] ogres = new Ogre[5];  //Ogres spawned on the map.
	
	public GameState() {
	}
	public GameState(GameMap map) {
		super();
		this.map = map;
	}
	public GameMap getGameMap(){
		return this.map;
	}
	public void setGameMap(GameMap map){
		this.map = map;
	}
	public void drawMap(){
		char[][] currentMap = map.getMap();
		
		for (int i = 0; i < currentMap.length; i++){
			for (int j = 0; j < currentMap[i].length; j++){
				if (i == hero.getX() && j == hero.getY()){
					System.out.print(hero.getSymbol() + " ");
				}
				else if (i == guard.getX() && j == guard.getY()){
					System.out.print(guard.getSymbol() + " ");
				}
				else{
					System.out.print(currentMap[i][j] + " ");
				}
			}
			System.out.print("\n");
		}
	}
	
	public void spawnHero(int x, int y){
		Hero h = new Hero(x,y);
		hero = h;
	}
	public void spawnGuard(int x, int y){
		Random rnd = new Random();
		int guardGen = 1;
		
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
}
