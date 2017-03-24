package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.DungeonMap;
import dkeep.logic.GameMap;
import dkeep.logic.GameState;
import dkeep.logic.GameState.State;
import dkeep.logic.Guard;
import dkeep.logic.GuardDrunk;
import dkeep.logic.GuardRookie;
import dkeep.logic.GuardSuspicious;

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
	public void testGuardInit(){
		Guard g = new Guard(24,56);
		
		assertEquals(24, g.getX());
		assertEquals(56, g.getY());
	}
	
	@Test(timeout=1000)
	public void testGuardProbabilityToSleepAndWake(){
		GameMap gameMap = new DungeonMap();
		GameState gameState = new GameState(gameMap);
		
		gameState.spawnGuard(0, 0, "Drunk");
		
		assertEquals(false, gameState.guard.getSleeping());
		boolean wokeUp = false, fellAsleep = false, changesPath = false;
		
		while (!wokeUp || !fellAsleep || !changesPath){
			gameState.guard.moveGuard();
			if (gameState.guard.getInversePath()){
				changesPath = true;
			}
			if (gameState.guard.getSleeping()){
				fellAsleep = true;
			}
			if (!gameState.guard.getSleeping() && fellAsleep){
				wokeUp = true;
			}
		}
		
		changesPath = false;
		gameState.spawnGuard(0, 0, "Suspicious");
		
		while (!changesPath){
			gameState.guard.moveGuard();
			if (gameState.guard.getInversePath()){
				changesPath = true;
			}
		}
	}
	
	@Test
	public void testRookieGuardIsPatrolling(){
		
		GameMap gameMap = new DungeonMap(map);
		GameState gameState = new GameState(gameMap);
		
		gameState.guard.testMode(true);
		gameState.spawnGuard(1, 8, "Rookie");
		gameState.processMove("d");
		gameState.processMove("d");
		
		assertEquals(2, gameState.guard.getX());
		assertEquals(7, gameState.guard.getY());
	}
	
	@Test(timeout=1000)
	public void testCanGenerateRandomPersonalities(){
		GameMap gameMap = new DungeonMap(map);
		GameState gameState = new GameState(gameMap);
		
		gameState.guard.testMode(true);
		
		boolean genRookie = false, genDrunk = false, genSuspicious = false;
		
		while (!genRookie || !genDrunk || !genSuspicious){
			gameState.spawnGuard(0, 0);
			if (gameState.guard instanceof GuardRookie){
				genRookie = true;
			}
			else if (gameState.guard instanceof GuardDrunk){
				genDrunk = true;
			}
			else if (gameState.guard instanceof GuardSuspicious){
				genSuspicious = true;
			}
			else{
				fail("Couldn't generate every guard personality.");
			}
		}
	}
	
	@Test
	public void testCanGenerateFixedPersonalities(){
		GameMap gameMap = new DungeonMap(map);
		GameState gameState = new GameState(gameMap);
		
		gameState.guard.testMode(true);
		
		gameState.spawnGuard(0, 0, "Rookie");
		assertEquals(true, gameState.guard instanceof GuardRookie);
		
		gameState.spawnGuard(0, 0, "Drunk");
		assertEquals(true, gameState.guard instanceof GuardDrunk);
		
		gameState.spawnGuard(0, 0, "Suspicious");
		assertEquals(true, gameState.guard instanceof GuardSuspicious);	
	}
	
	
	
}
