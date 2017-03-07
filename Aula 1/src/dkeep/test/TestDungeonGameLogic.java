package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.DungeonMap;
import dkeep.logic.GameMap;
import dkeep.logic.GameState;

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
		
		gameState.spawnHero(1, 1);
		
		assertEquals(1, gameState.hero.getX());
		assertEquals(1, gameState.hero.getY());
		
		gameState.hero.moveHero(gameMap, "s");
		
		assertEquals(2, gameState.hero.getX());
		assertEquals(1, gameState.hero.getY());
	}
	
	@Test
	public void testMovesHeroIntoWall(){
		GameMap gameMap = new DungeonMap(map);
		GameState gameState = new GameState(gameMap);
		
		gameState.spawnHero(1, 1);
		
		assertEquals(1, gameState.hero.getX());
		assertEquals(1, gameState.hero.getY());
		
		gameState.hero.moveHero(gameMap, "a");
		
		assertEquals(1, gameState.hero.getX());
		assertEquals(1, gameState.hero.getY());
	}
	
	@Test
	public void testAdjacentGuardPosition(){
		/*
		GameMap gameMap = new DungeonMap(map);
		GameState gameState = new GameState(gameMap);
		
		gameState.spawnHero(1, 1);
		gameState.spawnGuard(1, 3);
		*/
	}
	
	@Test
	public void testMovesToClosedDoor(){
		GameMap gameMap = new DungeonMap(map);
		GameState gameState = new GameState(gameMap);
		
		gameState.spawnHero(1, 1);
		gameState.hero.moveHero(gameMap, "s");
		gameState.hero.moveHero(gameMap, "a");
		
		assertEquals(2, gameState.hero.getX());
		assertEquals(1, gameState.hero.getY());
	}
	
	@Test
	public void testPicksKeyAndDoorsOpen(){
		/*
		GameMap gameMap = new DungeonMap(map);
		GameState gameState = new GameState(gameMap);
		
		gameState.spawnHero(1, 1);
		gameState.hero.moveHero(gameMap, "s");
		gameState.hero.moveHero(gameMap, "s");
		*/
	}
	
	@Test
	public void testProgressesIntoKeep(){
		/*
		GameMap gameMap = new DungeonMap(map);
		GameState gameState = new GameState(gameMap);
		
		gameState.spawnHero(1, 1);
		*/
	}
	
}
