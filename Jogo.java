package maze.cli;
import java.util.Scanner;

import maze.logic.*;


public class Jogo {
	public static void main(String args[]){
		Tabuleiro T = new Tabuleiro();
		Heroi h = T.getHeroi();
		while(!T.getEstado() && (T.getTabuleiro()[h.getY()][h.getX()]!='S' || !T.getEstadoDragao())){
			T.DesenhaTabuleiro();
			T.Morre();
			System.out.print("Legenda: H(Heroi),")
			Scanner s=new Scanner(System.in);
		}
		T.DesenhaTabuleiro();
		
		
	}
}
