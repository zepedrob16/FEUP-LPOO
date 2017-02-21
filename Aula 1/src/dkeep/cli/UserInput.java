package dkeep.cli;

import java.util.Scanner;
import dkeep.logic.GameMap;
import dkeep.logic.GameState;
import dkeep.logic.DungeonMap;

public class UserInput {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		System.out.print("Load Map : ");
		int loadMap = s.nextInt();
		
		GameMap map;
		GameState g;
		
		if (loadMap == 1){
			map = new DungeonMap();
			g = new GameState(map);
		}
		else if (loadMap == 2){
			//TODO: Implement more maps.
		}
		
		for (;;){
			String move = s.nextLine();
			
			if (moveHero(move)){
				System.out.println("Level complete!\n");
				Thread.sleep(1500);
				break;
			}
			
			if (heroSpotted()){
				System.out.println("You got caught, doofus!\n");
				return;
			}
		}
		
	}

}
