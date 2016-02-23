package maze.cli;
import java.util.Scanner;

import maze.logic.*;


public class Jogo {
	public static void main(String args[]){
		Tabuleiro T = new Tabuleiro();
		T.DesenhaTabuleiro();
		while(!T.getEstado()){
			
			
			System.out.println("Legenda: H(Heroi),D(Dragão), S(saída),E(espada)");
			System.out.print("Em que direcção deseja mover-se (w/a/s/d) introduza q se deseja sair: ");
			Scanner s=new Scanner(System.in);

			char resposta=s.next().charAt(0);
			if(resposta=='q'){
				s.close();
				return;
			}
			
			T.MoveH(resposta);
			if(T.Saiu())break;
			T.Morre();
			if(T.getEstadoDragao())
				System.out.println("Aspect of dragon, matáste o temível dragão");
			T.DesenhaTabuleiro();
		}
		if (T.getEstado()){
			System.out.print("\n");
			System.out.println("Morreste, lutar desarmado contra um dragão não é boa ideia ");
		}
		else{
			System.out.print("\n");
			System.out.print("Venceu");
			}
	}
}
