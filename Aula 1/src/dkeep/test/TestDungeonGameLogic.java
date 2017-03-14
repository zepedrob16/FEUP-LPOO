package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.DungeonMap;
import dkeep.logic.GameMap;
import dkeep.logic.GameState;
import dkeep.logic.GameState.State;

public class TestDungeonGameLogic {
	char[][] map = {{'X','X','X','X','X'},
					{'X','H',' ','G','X'},
					{'I',' ',' ',' ','X'},
					{'I','k',' ',' ','X'},
					{'X','X','X','X','X'}};
	
	@Test
	public void testMovesHeroIntoFreeCell(){
		
		GameMap gameMap = new DungeonMap(map);
		GameState gameState = new GameState(gameMap);
		
		assertEquals(1, gameState.hero.getX());
		assertEquals(1, gameState.hero.getY());
		
		gameState.processMove("s");
		
		assertEquals(2, gameState.hero.getX());
		assertEquals(1, gameState.hero.getY());
	}
	
	@Test
	public void testMovesHeroIntoWall(){
		
		GameMap gameMap = new DungeonMap(map);
		GameState gameState = new GameState(gameMap);
		
		assertEquals(1, gameState.hero.getX());
		assertEquals(1, gameState.hero.getY());
		
		gameState.processMove("a");
		
		assertEquals(1, gameState.hero.getX());
		assertEquals(1, gameState.hero.getY());
	}
	
	@Test
	public void testAdjacentGuardPosition(){
		
		GameMap gameMap = new DungeonMap(map);
		GameState gameState = new GameState(gameMap);
		
		//gameState.guard.testMode(true);  //Disables the guard's patrol route.
		gameState.processMove("d");
		
		assertEquals(State.DEFEAT, gameState.getState());
	}
	
	@Test
	public void testMovesToClosedDoor(){
		
		GameMap gameMap = new DungeonMap(map);
		GameState gameState = new GameState(gameMap);

		gameState.processMove("s");
		gameState.processMove("a");
		
		assertEquals(2, gameState.hero.getX());
		assertEquals(1, gameState.hero.getY());
	}
	
	@Test
	public void testActivatesLeverAndDoorsOpen(){
		
		GameMap gameMap = new DungeonMap(map);
		GameState gameState = new GameState(gameMap);
		
		gameState.processMove("s");
		gameState.processMove("s");
		
		assertEquals('S', gameState.getGameMap().getMap()[2][0]);
		assertEquals('S', gameState.getGameMap().getMap()[3][0]);
	}
	
	@Test
	public void testProgressesIntoKeep(){
		
		GameMap gameMap = new DungeonMap(map);
		GameState gameState = new GameState(gameMap);
		
		gameState.processMove("s");
		gameState.processMove("s");
		gameState.processMove("a");
		
		assertEquals(State.VICTORY, gameState.getState());
	}
	
}
