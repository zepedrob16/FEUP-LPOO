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
	public void testSetsTheGameMap(){
		
	}
}
