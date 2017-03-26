package dkeep.test;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import dkeep.logic.OgreMap;
import dkeep.logic.GameState.Level;
import dkeep.logic.GameState.State;
import dkeep.logic.Hero;
import dkeep.logic.Ogre;
import dkeep.logic.DungeonMap;
import dkeep.logic.GameMap;
import dkeep.logic.GameState;

public class TestOgreLogic {
	char[][] map = {{'X','X','X','X','X'},
					{'X',' ','k',' ','X'},
					{'X',' ',' ','O','X'},
					{'X','H',' ',' ','X'},	
					{'X','X','I','X','X'}};
	
	@Test(timeout=1000)
	public void testGeneratesRandomOgres(){
		GameMap gameMap = new OgreMap(map);
		GameState gameState = new GameState(gameMap);
		
		boolean gen1 = false, gen2 = false, gen3 = false, gen4 = false, gen5 = false;
		
		while (!gen1 || !gen2 || !gen3 || !gen4 || !gen5){
			gameState.spawnOgres();
			int res = gameState.ogres.size();
			System.out.println(res);
			
			if (res == 1){
				gen1 = true;
			}
			else if (res == 2){
				gen2 = true;
			}
			else if (res == 3){
				gen3 = true;
			}
			else if (res == 4){
				gen4 = true;
			}
			else{
				gen5 = true;
			}
		}
	}
	
	@Test
	public void testGeneratesFixedOgres(){
		GameMap gameMap = new OgreMap(map);
		GameState gameState = new GameState(gameMap);
		
		Random rnd = new Random();
		int i = 0;
		while (i < 1000){
			int gen = rnd.nextInt(5) + 1;
			gameState.spawnOgres(gen);
			assertEquals(gen, gameState.ogres.size());
			i++;
		}
	}
	
	@Test
	public void testHeroAndOgreSameCell(){
		Ogre o = new Ogre(2,2);
		Hero h = new Hero(2,2, "OGRE");
		
		assertEquals(true, o.heroAdjacent(h));
	}
	
	@Test
	public void testHeroHitByClub(){
		
		GameMap gameMap = new OgreMap(map);
		GameState gameState = new GameState(gameMap);
		
		gameState.moveEveryOgre();
		
		Ogre o = gameState.ogres.get(0);
		
		int clubX = o.getClubX();
		int clubY = o.getClubY();
		
		Hero h = new Hero(clubX, clubY, "OGRE");
		
		assertEquals(true, o.heroAdjacent(h));
	}
	
	@Test
	public void testTestMode(){
		Ogre o = new Ogre(1,1);
		
		o.testMode(true);
		assertEquals(true, o.getTestMode());
		
		o.testMode(false);
		assertEquals(false, o.getTestMode());
	}
	
	
}
