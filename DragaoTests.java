package maze.test;

import static org.junit.Assert.*;

import org.junit.Test;
import maze.logic.*;

public class DragaoTests {

	@Test
	public void testMoveDLeft() {
		char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
				{'X', ' ', ' ', 'H', 'S'},
				{'X', ' ', 'X', ' ', 'X'},
				{'X', 'E', ' ', 'D', 'X'},
				{'X', 'X', 'X', 'X', 'X'}};
		Tabuleiro maze=new Tabuleiro(m1);
		
		maze.MoveComDirecao('a');
		assertEquals(new Point(2,3),maze.getDrakePosition());
	}
	
	@Test
	public void testMoveDRight() {
		char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
				{'X', ' ', ' ', 'H', 'S'},
				{'X', ' ', 'X', ' ', 'X'},
				{'X', 'E', 'D', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X'}};
		Tabuleiro maze=new Tabuleiro(m1);
		
		maze.MoveComDirecao('d');
		assertEquals(new Point(3,3),maze.getDrakePosition());
	}
	
	@Test
	public void testMoveDUp() {
		char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
				{'X', ' ', ' ', 'H', 'S'},
				{'X', ' ', 'X', ' ', 'X'},
				{'X', 'E', ' ', 'D', 'X'},
				{'X', 'X', 'X', 'X', 'X'}};
		Tabuleiro maze=new Tabuleiro(m1);
		
		maze.MoveComDirecao('w');
		assertEquals(new Point(3,2),maze.getDrakePosition());
	}
	@Test
	public void testMoveDDown() {
		char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
				{'X', 'D', ' ', 'H', 'S'},
				{'X', ' ', 'X', ' ', 'X'},
				{'X', 'E', ' ', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X'}};
		Tabuleiro maze=new Tabuleiro(m1);
		
		maze.MoveComDirecao('s');
		assertEquals(new Point(1,2),maze.getDrakePosition());
	}
	//Movimenta��o que no random permite voltar a fazer o random para o drake se mexer
	@Test
	public void testMoveDRandom() {
		char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
				{'X', 'D', ' ', 'H', 'S'},
				{'X', ' ', 'X', ' ', 'X'},
				{'X', 'E', ' ', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X'}};
		Tabuleiro maze=new Tabuleiro(m1);
		
		maze.MoveComDirecao('a');
		assertEquals(true,(maze.getDragao().getPosition().equals(new Point(1,1)))||maze.getDragao().getPosition().equals(new Point(2,1))||maze.getDragao().getPosition().equals(new Point(1,2)));
	}
	@Test
	public void testMoveDDownOverSword() {
		char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
				{'X', ' ', ' ', 'H', 'S'},
				{'X', 'D', 'X', ' ', 'X'},
				{'X', 'E', ' ', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X'}};
		Tabuleiro maze=new Tabuleiro(m1);
		
		maze.MoveComDirecao('s');
		assertEquals(new Point(1,3),maze.getDrakePosition());
		}
	
	@Test
	public void testMoveDRightLeavingSword() {
		char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
				{'X', ' ', ' ', 'H', 'S'},
				{'X', ' ', 'X', ' ', 'X'},
				{'X', 'F', ' ', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X'}};
		Tabuleiro maze=new Tabuleiro(m1);
		
		maze.MoveComDirecao('d');
		assertEquals(new Point(2,3),maze.getDrakePosition());
		assertEquals(new Point(1,3),maze.getSwordPosition());
		}
	@Test
	public void testMoveDLeftLeavingSword() {
		char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
				{'X', ' ', ' ', 'H', 'S'},
				{'X', ' ', 'X', ' ', 'X'},
				{'X', ' ', 'F', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X'}};
		Tabuleiro maze=new Tabuleiro(m1);
		
		maze.MoveComDirecao('a');
		assertEquals(new Point(1,3),maze.getDrakePosition());
		assertEquals(new Point(2,3),maze.getSwordPosition());
		}
	
	@Test
	public void testMoveDUpLeavingSword() {
		char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
				{'X', ' ', ' ', 'H', 'S'},
				{'X', ' ', 'X', ' ', 'X'},
				{'X', 'F', ' ', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X'}};
		Tabuleiro maze=new Tabuleiro(m1);
		
		maze.MoveComDirecao('w');
		assertEquals(new Point(1,2),maze.getDrakePosition());
		assertEquals(new Point(1,3),maze.getSwordPosition());
		}
	
	
	@Test
	public void testMoveDRandomWithPossibilityOfSleeping() {
		char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
				{'X', 'D', ' ', 'H', 'S'},
				{'X', ' ', 'X', ' ', 'X'},
				{'X', 'E', ' ', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X'}};
		Tabuleiro maze=new Tabuleiro(m1);
		
		
		maze.Adormece(100); //equivalente a 100% probabilidade de adormecer
		maze.MoveD();
		assertEquals(new Point(1,1),maze.getDrakePosition());
		assertEquals(true,maze.getDragao().getDorme());
		}
	

}
