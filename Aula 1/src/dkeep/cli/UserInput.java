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
			DungeonMap dungeonMap = new DungeonMap();
			gameState.setGameMap(dungeonMap);
		}
		else if (loadMap == 2){ //Loads the Crazy Ogre map.
			OgreMap ogreMap = new OgreMap();
			gameState.setGameMap(ogreMap);
			gameState.spawnOgres();  //Spawns 1-5 ogres (randomly).
		}
		gameState.drawMap();
		
		while (true){
			String move = s.nextLine();
			if (!gameState.processMove(move)){
				break;
			}
		}
		s.close();
	}
}
