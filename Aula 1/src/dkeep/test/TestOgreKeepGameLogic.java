package dkeep.test;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import dkeep.logic.OgreMap;
import dkeep.logic.GameState.Level;
import dkeep.logic.GameState.State;
import dkeep.logic.Hero;
import dkeep.logic.Ogre;
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
	
	@Test(timeout=1000)
	public void testVerifiesHeroDies(){
		GameMap gameMap = new OgreMap(map);
		GameState gameState = new GameState(gameMap);
		
		gameState.spawnOgres(1);
		Ogre o = gameState.ogres.get(0);
		
		boolean heroHitByClub = false;
		while (!heroHitByClub){
			gameState.moveEveryOgre();
			if (o.heroAdjacent(gameState.hero)){
				heroHitByClub = true;
			}
		}
	}
	
	@Test(timeout=1000)
	public void testOgreValidMove(){
		Ogre o = new Ogre(3,3);
		
		Random rnd = new Random();
		boolean detectsObstacles = false;
		
		while (!detectsObstacles){
			int gen = rnd.nextInt(4);
			if (!o.checkValidMove(gen, map)){
				detectsObstacles = true;
			}
		}
	}
	
	
}
