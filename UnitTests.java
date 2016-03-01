package maze.test;

import static org.junit.Assert.*;

import org.junit.Test;

import maze.logic.*;

public class UnitTests {
	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
			{'X', ' ', ' ', 'H', 'S'},
			{'X', ' ', 'X', ' ', 'X'},
			{'X', 'E', ' ', 'D', 'X'},
			{'X', 'X', 'X', 'X', 'X'}};

	@Test
	public void testMoveHeroToFreeCell() {
	Tabuleiro maze = new Tabuleiro(m1);
	
	assertEquals(new Point(3, 1), maze.getHeroPosition());
	maze.MoveH('a');
	assertEquals(new Point(2, 1), maze.getHeroPosition());
	maze.MoveH('d');
	assertEquals(new Point(3,1),maze.getHeroPosition());
	maze.MoveH('s');
	assertEquals(new Point(3,2),maze.getHeroPosition());
	maze.MoveH('w');
	assertEquals(new Point(3,1),maze.getHeroPosition());
	maze.MoveH('d');
	assertEquals(new Point(3,1),maze.getHeroPosition());
	maze.MoveH('a');
	maze.MoveH('a');
	maze.MoveH('a');
	maze.MoveH('s');
	maze.MoveH('s');
	assertEquals(new Point(1,3),maze.getHeroPosition());
	}
	@Test
	public void testMoveHeroToWall(){
		Tabuleiro maze= new Tabuleiro(m1);
		maze.MoveH('w');
		assertEquals(new Point(3,1),maze.getHeroPosition());
	}
	
	
	
	}
