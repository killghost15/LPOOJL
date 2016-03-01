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
	public void testOtherWayToStartMaze(){
		Tabuleiro maze=new Tabuleiro();
		assertEquals(false,maze.getEstado());
		assertEquals(false,maze.getEstadoDragao());
	}
	
	
	@Test
	public void testMoveHeroToFreeCell() {
	Tabuleiro maze = new Tabuleiro(m1);
	
	assertEquals(new Point(3, 1), maze.getHeroPosition());
	maze.MoveH('a');
	assertEquals(new Point(2, 1), maze.getHeroPosition());
	maze.MoveH('d');
	assertEquals(new Point(3,1),maze.getHeroPosition());
	
	maze.MoveH('w');
	assertEquals(new Point(3,1),maze.getHeroPosition());
	maze.MoveH('d');
	assertEquals(new Point(3,1),maze.getHeroPosition());
	}
	@Test
	public void testMoveHeroToWall(){
		Tabuleiro maze= new Tabuleiro(m1);
		maze.MoveH('w');
		assertEquals(new Point(3,1),maze.getHeroPosition());
	}
	
	@Test
	public void testMoveHeroToSwordDown(){
		char [][] m = {{'X', 'X', 'X', 'X', 'X'},
				{'X', ' ', ' ', ' ', 'S'},
				{'X', 'H', 'X', ' ', 'X'},
				{'X', 'E', ' ', 'D', 'X'},
				{'X', 'X', 'X', 'X', 'X'}};
		Tabuleiro maze= new Tabuleiro(m);
		maze.MoveH('s');
		assertEquals(new Point(1,3),maze.getHeroPosition());
		assertEquals(true,maze.getHeroi().Armado());
	}
	@Test
	public void testMoveHeroToSwordLeft(){
		char [][] m = {{'X', 'X', 'X', 'X', 'X'},
				{'X', ' ', ' ', ' ', 'S'},
				{'X', ' ', 'X', ' ', 'X'},
				{'X', 'E', 'H', 'D', 'X'},
				{'X', 'X', 'X', 'X', 'X'}};
		Tabuleiro maze= new Tabuleiro(m);
		maze.MoveH('a');
		assertEquals(new Point(1,3),maze.getHeroPosition());
		assertEquals(true,maze.getHeroi().Armado());
	}
	@Test
	public void testMoveHeroToSwordRight(){
		char [][] m = {{'X', 'X', 'X', 'X', 'X'},
				{'X', ' ', 'H', 'E', 'S'},
				{'X', ' ', 'X', ' ', 'X'},
				{'X', ' ', ' ', 'D', 'X'},
				{'X', 'X', 'X', 'X', 'X'}};
		Tabuleiro maze= new Tabuleiro(m);
		maze.MoveH('d');
		assertEquals(new Point(3,1),maze.getHeroPosition());
		assertEquals(true,maze.getHeroi().Armado());
	}
	
	@Test
	public void testMoveHeroUnarmedToNearDrake(){
		char [][] m = {{'X', 'X', 'X', 'X', 'X'},
				{'X', ' ', ' ', 'H', 'S'},
				{'X', ' ', 'X', ' ', 'X'},
				{'X', 'E', ' ', 'D', 'X'},
				{'X', 'X', 'X', 'X', 'X'}};
		Tabuleiro maze = new Tabuleiro(m);
		maze.MoveH('s');
		maze.Morre();
		assertEquals(new Point(3,2),maze.getHeroPosition());
		assertEquals(true,maze.getEstado());
		
	}
	@Test
	public void testItsKillingDragonTime(){
		char [][] m = {{'X', 'X', 'X', 'X', 'X'},
				{'X', ' ', ' ', 'A', 'S'},
				{'X', ' ', 'X', ' ', 'X'},
				{'X', ' ', ' ', 'D', 'X'},
				{'X', 'X', 'X', 'X', 'X'}};
		Tabuleiro maze = new Tabuleiro(m);
		maze.MoveH('s');
		maze.Morre();
		assertEquals(new Point(3,2),maze.getHeroPosition());
		assertEquals(true,maze.getEstadoDragao());
	}
	
	
	}