package dkeep.cli;

import java.util.Scanner;
import dkeep.logic.GameState;
import dkeep.logic.DungeonMap;
import dkeep.logic.OgreMap;

public class UserInput {

	public static void main(String[] args) throws InterruptedException {

		Scanner s = new Scanner(System.in);
		System.out.print("Load Map : ");
		int loadMap = s.nextInt();

		GameState gameState = new GameState();
		
		if (loadMap == 1){	//Loads the Dungeon map.
			System.out.println("\nLoading DUNGEON level...\n");
			DungeonMap dungeonMap = new DungeonMap();
			gameState.setGameMap(dungeonMap);
			gameState.spawnHero(1,1);  //Instantiates a new hero.
			gameState.spawnGuard(1,8);  //Instantiates a guard with a random personality.	
		}
		else if (loadMap == 2){ //Loads the Crazy Ogre map.
			System.out.println("\nLoading CRAZY OGRE level...\n");
			OgreMap ogreMap = new OgreMap();
			gameState.setGameMap(ogreMap);
			gameState.spawnHero(1,1);  //Instantiates a new hero.
			gameState.hero.setSymbol('A');
			gameState.spawnKey(1,7);
			gameState.spawnOgres();  //Spawns 1-5 ogres (randomly).
		}
		gameState.drawMap();
		
		while (true){
			String move = s.nextLine();
			int m = gameState.hero.moveHero(gameState.getGameMap(), move);  //0 if movement is valid, 1 if victorious, -1 if invalid.
			
			if (loadMap == 1) {
				if (m == 0){
					gameState.guard.moveGuard();
					gameState.drawMap();
				}
				else if (m == 1){
					gameState.guard.moveGuard();
					gameState.drawMap();
					System.out.println("Level complete!\n"); Thread.sleep(1500);
					
					OgreMap ogreMap = new OgreMap();
					gameState.setGameMap(ogreMap);
				}
				else if (m == -1){
					System.out.println("Invalid move!\n");
					continue;
				}
				if (gameState.hero.heroSpotted(gameState.guard) == true){
					System.out.println("You got caught, doofus!\n");
					break;
				}
			}
			if (loadMap == 2) {
				if (m == 0) {
					gameState.moveEveryOgre();
					gameState.hero.moveHeroClub(gameState);
					gameState.drawMap();
				}
				else if (m == 1){
					gameState.moveEveryOgre();
					gameState.hero.moveHeroClub(gameState);
					gameState.drawMap();
					System.out.println("Level complete!\n"); Thread.sleep(1500);
					return;
				}
				else if (m == -1){
					System.out.println("Invalid move!\n");
					continue;
				}
				for (int i = 0; i < gameState.ogres.size(); i++){
					if (gameState.ogres.get(i).heroAdjacent(gameState.hero) == true){
						System.out.println("You got caught, doofus!\n");
						break;
					}
				}
			}
		}
		s.close();
	}
}
