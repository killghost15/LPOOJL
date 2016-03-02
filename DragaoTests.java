package maze.test;

import static org.junit.Assert.*;

import org.junit.Test;
import maze.logic.*;

public class DragaoTests {

	@Test
	public void testMovimentacaoDLeft() {
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
	public void testMovimentacaoDRight() {
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
	public void testMovimentacaoDUp() {
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
	public void testMovimentacaoDDown() {
		char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
				{'X', 'D', ' ', 'H', 'S'},
				{'X', ' ', 'X', ' ', 'X'},
				{'X', 'E', ' ', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X'}};
		Tabuleiro maze=new Tabuleiro(m1);
		
		maze.MoveComDirecao('s');
		assertEquals(new Point(1,2),maze.getDrakePosition());
	}
	//Movimentação que no random permite voltar a fazer o random para o drake se mexer
	@Test
	public void testMovimentacaoDRandom() {
		char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
				{'X', 'D', ' ', 'H', 'S'},
				{'X', ' ', 'X', ' ', 'X'},
				{'X', 'E', ' ', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X'}};
		Tabuleiro maze=new Tabuleiro(m1);
		
		maze.MoveComDirecao('a');
		assertEquals(true,(maze.getDragao().getPosition().equals(new Point(1,1)))||maze.getDragao().getPosition().equals(new Point(2,1))||maze.getDragao().getPosition().equals(new Point(1,2)));
	}
	

}
