package maze.cli;
import java.util.Scanner;

import maze.logic.*;


public class Jogo {
	public static void main(String args[]){
		Tabuleiro T = new Tabuleiro();
		System.out.print("Que tipo de estrategia deseja:"+"\n");
		System.out.println("1:Drag�o parado");
		System.out.println("2:Drag�o a mover-se aleatoriamente");
		System.out.println("3:Drag�o a mover-se aleatoriamente e a adormecer aleatoriamente");
		System.out.print("Op��o: ");
		Scanner r=new Scanner(System.in);
		int opcao=r.nextInt();
		r.reset();
		
		System.out.print("\n");
		T.DesenhaTabuleiro();
		while(!T.getEstado()){
			
			
			System.out.println("Legenda: H(Heroi),D(Drag�o), S(sa�da),E(espada)");
			System.out.print("Em que direc��o deseja mover-se (w/a/s/d) introduza q se deseja sair: ");
			char resposta=r.next().charAt(0);
			if(resposta=='q'){
				r.close();
				return;
			}
			
			T.MoveH(resposta);
			if(T.Vitoria())break;
			
			T.Morre();
			if(opcao==3 || opcao==2){
				if(opcao==3)
					T.Adormece();
			T.MoveD();
			}
			if(T.getEstadoDragao())
				System.out.println("Aspect of dragon, mat�ste o tem�vel drag�o");
			T.DesenhaTabuleiro();
			T.Morre();
		}
		if (T.getEstado()){
			r.close();
			System.out.print("\n");
			System.out.println("Morreste, lutar desarmado contra um drag�o n�o � boa ideia ");
		}
		else{
			r.close();
			System.out.print("\n");
			System.out.print("Venceu");
			}
	}
}
