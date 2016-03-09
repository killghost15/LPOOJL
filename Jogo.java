package maze.cli;
import java.util.Iterator;

import java.util.Scanner;

import maze.logic.*;


public class Jogo {
	public static void main(String args[]){
		Scanner r=new Scanner(System.in);
		System.out.print("Dimensão do tabuleiro a gerar(tem de ser maior ou igual a 5 e impar, se introduzir um numero par será transformado no impar anterior, se for menor que 5 será transformado em 5):");
		int dim=r.nextInt();
		if (dim<5)
			dim=5;
		if(dim% 2==0)
			dim=dim-1;
		char [][] m;
		IMazeBuilder builder = new MazeBuilder();
		
		
		
		
		r.reset();
		System.out.print("Contra quantos dragões deseja jogar: " );
		int numD=r.nextInt();
		m=builder.buildMaze(dim,numD);
		
		Tabuleiro T = new Tabuleiro(m);
		r.reset();
		System.out.print("Que tipo de estrategia deseja:"+"\n");
		System.out.println("1:Dragão parado");
		System.out.println("2:Dragão a mover-se aleatoriamente");
		System.out.println("3:Dragão a mover-se aleatoriamente e a adormecer aleatoriamente");
		System.out.print("Opção: ");

		int opcao=r.nextInt();
		r.reset();
		
		System.out.print("\n");
		T.DesenhaTabuleiro();
		while(!T.getEstado()){
			
			
			System.out.println("Legenda: H(Heroi),D(Dragão), S(saída),E(espada)");
			System.out.print("Em que direcção deseja mover-se (w/a/s/d) introduza q se deseja sair: ");
			char resposta=r.next().charAt(0);
			if(resposta=='q'){
				r.close();
				return;
			}
			
			T.MoveH(resposta);
			if(T.Vitoria())break;
			if(opcao==3)
				T.Adormece(20);
			
			T.Morre();
			if(opcao==3 || opcao==2){
				for(Iterator<Dragao> it=T.getDrakeList().iterator();it.hasNext();)
					T.MoveD(it.next());
			}
			if(T.AllDead())
				System.out.println("Aspect of dragon, matáste o temível dragão");
			T.DesenhaTabuleiro();
			T.Morre();
		}
		if (T.getEstado()){
			r.close();
			System.out.print("\n");
			System.out.println("Morreste, lutar desarmado contra um dragão não é boa ideia ");
		}
		else{
			r.close();
			System.out.print("\n");
			System.out.print("Venceu");
			}
	}
}
