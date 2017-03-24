package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.OgreMap;
import dkeep.logic.GameState.Level;
import dkeep.logic.GameState.State;
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
		
		assertEquals(State.RUNNING, gameState.getState());
	}
	
	@Test
	public void testPicksUpKeyAndDoorsOpen(){
		
		GameMap gameMap = new OgreMap(map);
		GameState gameState = new GameState(gameMap);
		
		gameState.ogres.get(0).testMode(true);
		gameState.processMove("w");
		gameState.processMove("w");
		gameState.processMove("d");
		
		assertEquals('K', gameState.hero.getSymbol());
	}
	
	@Test
	public void testTriesToOpenDoorWithoutKey(){
		
		GameMap gameMap = new OgreMap(map);
		GameState gameState = new GameState(gameMap);
		
		gameState.ogres.get(0).testMode(true);
		gameState.processMove("d");
		gameState.processMove("s");
		gameState.processMove("s");
		
		assertEquals(gameState.hero.getX(), 3);
		assertEquals(gameState.hero.getY(), 2);
	}
	
	@Test
	public void testOpensDoorWithKey(){
		
		GameMap gameMap = new OgreMap(map);
		GameState gameState = new GameState(gameMap);
		
		gameState.ogres.get(0).testMode(true);
		gameState.processMove("w");
		gameState.processMove("w");
		gameState.processMove("d");
		gameState.processMove("a");
		gameState.processMove("s");
		gameState.processMove("s");
		gameState.processMove("d");
		gameState.processMove("s");
		
		assertEquals('S', gameState.getGameMap().getMap()[4][2]);
	}
	
	@Test
	public void testExitsOgreKeep(){
		
		GameMap gameMap = new OgreMap(map);
		GameState gameState = new GameState(gameMap);
		
		gameState.ogres.get(0).testMode(true);
		gameState.processMove("w");
		gameState.processMove("w");
		gameState.processMove("d");
		gameState.processMove("a");
		gameState.processMove("s");
		gameState.processMove("s");
		gameState.processMove("d");
		gameState.processMove("s");
		gameState.processMove("s");
		
		assertEquals(State.VICTORY, gameState.getState());
	}
	
	
}
