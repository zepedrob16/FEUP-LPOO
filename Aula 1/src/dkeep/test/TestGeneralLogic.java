package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.DungeonMap;
import dkeep.logic.GameMap;
import dkeep.logic.GameState;
import dkeep.logic.GameState.State;
import dkeep.logic.OgreMap;

public class TestGeneralLogic {
	
	@Test
	public void testDefaultLoad(){
		GameState gameState = new GameState();

		assertEquals(State.RUNNING, gameState.getState());
	}
	
	@Test
	public void testComparesTwoGameStates(){
		GameMap gameMap = new DungeonMap();
		
		GameState g1 = new GameState(gameMap);
		GameState g2 = new GameState(gameMap);
		
		assertEquals(true, g1.equals(g2));
	}
}
