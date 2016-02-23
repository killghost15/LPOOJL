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
			System.out.println("Legenda: H(Heroi),D(Drag�o), S(sa�da),E(espada)");
			System.out.print("Em que direc��o deseja mover-se: w/a/s/d");
			Scanner s=new Scanner(System.in);
			char resposta=s.next().charAt(0);
			
			while(resposta != 'w' || resposta !='a' || resposta != 's' || resposta !='d' ){
				System.out.print("Em que direc��o deseja mover-se(n�o introduziu uma direc��o valida: w/a/s/d");
			}
		s.close();	
		}
	
		
	}
}
