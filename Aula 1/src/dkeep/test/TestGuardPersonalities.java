package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.DungeonMap;
import dkeep.logic.GameMap;
import dkeep.logic.GameState;
import dkeep.logic.GameState.State;

public class TestGuardPersonalities {
	char[][] map = {{'X','X','X','X','X','X','X','X','X','X'},
					{'X','H',' ',' ','I',' ','X',' ','G','X'},
					{'X','X','X',' ','X','X','X',' ',' ','X'},
					{'X',' ','I',' ','I',' ','X',' ',' ','X'},
					{'X','X','X',' ','X','X','X',' ',' ','X'},
					{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
					{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
					{'X','X','X',' ','X','X','X','X',' ','X'},
					{'X',' ','I',' ','I',' ','X','k',' ','X'},
					{'X','X','X','X','X','X','X','X','X','X'}
	};
	
	@Test
	public void testRookieGuardIsPatrolling(){
		
		GameMap gameMap = new DungeonMap(map);
		GameState gameState = new GameState(gameMap);
		
		gameState.guard.testMode(false);
		gameState.spawnGuard(1, 8, "Rookie");
		gameState.processMove("d");
		gameState.processMove("d");
		
		assertEquals(2, gameState.guard.getX());
		assertEquals(7, gameState.guard.getY());
	}
	
}
