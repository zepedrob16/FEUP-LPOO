package dkeep.cli;

import java.util.Scanner;
import dkeep.logic.GameMap;
import dkeep.logic.GameState;
import dkeep.logic.Guard;
import dkeep.logic.Hero;
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
		}
		else if (loadMap == 2){ //Loads the Crazy Ogre map.
			System.out.println("Loading CRAZY OGRE level!");
			//OgreMap ogreMap = new OgreMap();
			//gameState.setGameMap(ogreMap);
		}
		
		gameState.spawnHero(1,1);  //Instantiates a new hero.
		gameState.spawnGuard(1,8);  //Instantiates a guard with a random personality.
		gameState.drawMap();

		while (true){
			String move = s.nextLine();
			
			int m = gameState.hero.moveHero(gameState.getGameMap(), move);
			
			if (m == 0){
				g.moveGuard(gameState.getGameMap());
				gameState.getGameMap().drawMap(h,g);
				System.out.println(h.getX() + "|" + h.getY() + "\n");    //To study the hero's movement
			}
			else if (m == 1){
				g.moveGuard(gameState.getGameMap());
				gameState.getGameMap().drawMap(h,g);
				System.out.println(h.getX() + "|" + h.getY() + "\n");
				System.out.println("Level complete!\n"); Thread.sleep(1500);
				return;
				
				//TODO: gameState.setGameMap(map);
			}
			else if (m == -1){
				System.out.println("Invalid move!\n");
				System.out.println(h.getX() + "|" + h.getY() + "\n");
				continue;
			}
			
			if (h.heroSpotted(g) == true){
				System.out.println("You got caught, doofus!\n");
				return;
			}
		}
	}
}
