package dkeep.cli;

import java.util.Scanner;
import dkeep.logic.GameMap;
import dkeep.logic.GameState;
import dkeep.logic.Hero;
import dkeep.logic.DungeonMap;

public class UserInput {

	public static void main(String[] args) throws InterruptedException {

		Scanner s = new Scanner(System.in);
		System.out.print("Load Map : ");
		int loadMap = s.nextInt();

		Hero h = new Hero(1,1);
		//GameMap map;
		GameState g;

		if (loadMap == 2){
			//TODO: Implement more maps.
		}

		for (;;){
			String move = s.nextLine();


			if (loadMap == 1) {
				DungeonMap map = new DungeonMap();
				g = new GameState(map);
				if (h.moveHero(map, move)){
					System.out.println("Level complete!\n");
					Thread.sleep(1500);
					break;
				}
				if (map.heroSpotted()){
					System.out.println("You got caught, doofus!\n");
					return;
				}
			}

		}

	}

}
