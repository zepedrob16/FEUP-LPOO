package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.OgreMap;
import dkeep.logic.GameState.State;
import dkeep.logic.DungeonMap;
import dkeep.logic.GameMap;
import dkeep.logic.GameState;

public class TestOgreKeepGameLogic {
	char[][] map = {{'X','X','X','X','X'},
					{'X',' ','k',' ','X'},
					{'X',' ',' ','O','X'},
					{'X','H',' ',' ','X'},	
					{'X','X','I','X','X'}};
	
	@Test
	public void testAdjacentOgrePosition(){
		
		GameMap gameMap = new OgreMap(map);
		GameState gameState = new GameState(gameMap);
		
		gameState.ogres.get(0).testMode(true);  //Disables the ogre's movement.
		gameState.processMove("d");
		gameState.processMove("w");
		
		assertEquals(State.DEFEAT, gameState.getState());
	}
	
	@Test
	public void testPicksUpKeyAndDoorsOpen(){
		
	}
	
	@Test
	public void testTriesToOpenDoorWithoutKey(){
		
	}
	
	@Test
	public void testOpensDoorWithKey(){
		
	}
	
	@Test
	public void testExitsOgreKeep(){
		
	}
	
	
}
