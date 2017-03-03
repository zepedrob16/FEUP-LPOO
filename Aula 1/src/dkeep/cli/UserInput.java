package dkeep.cli;

import java.util.Scanner;
import dkeep.logic.GameMap;
import dkeep.logic.GameState;
import dkeep.logic.Guard;
import dkeep.logic.Hero;
import dkeep.logic.Ogre;
import dkeep.logic.OgreMap;
import dkeep.logic.DungeonMap;

public class UserInput {

	public static void main(String[] args) throws InterruptedException {

		Scanner s = new Scanner(System.in);
		System.out.print("Load Map : ");
		int loadMap = s.nextInt();

		GameState gameState = new GameState();
		
		if (loadMap == 1){	//Loads the Dungeon map.
			System.out.println("Loading DUNGEON level!");
			DungeonMap dungeonMap = new DungeonMap();
			gameState.setGameMap(dungeonMap);
			gameState.spawnGuard(1,8);  //Instantiates a guard with a random personality.
			
		}
		else if (loadMap == 2){ //Loads the Crazy Ogre map.
			System.out.println("Loading CRAZY OGRE level!");
			OgreMap ogreMap = new OgreMap();
			gameState.setGameMap(ogreMap);
		}
		
		gameState.spawnHero(1,1);  //Instantiates a new hero.
		Guard g = gameState.guard;
		Ogre[] o = gameState.ogres;
		gameState.drawMap();
		
		Hero h = gameState.hero;
		
		while (true){
			String move = s.nextLine();
			
			int m = gameState.hero.moveHero(gameState.getGameMap(), move);
			
			if (loadMap == 1) {
				if (m == 0){
					g.moveGuard();
					gameState.drawMap();
				}
				else if (m == 1){
					g.moveGuard();
					gameState.drawMap();
					System.out.println("Level complete!\n"); Thread.sleep(1500);
					return;

					//TODO: gameState.setGameMap(map);
				}
				else if (m == -1){
					System.out.println("Invalid move!\n");
					continue;
				}

				if (h.heroSpotted(g) == true){
					System.out.println("You got caught, doofus!\n");
					return;
				}
			}
			if (loadMap == 2) {
				if (m == 0) {
					
				}
			}
		}
	}
}
