package maze.cli;
import java.util.Iterator;

import java.util.Scanner;

import maze.logic.*;


public class Jogo {
	public static void main(String args[]){
		Scanner r=new Scanner(System.in);
		System.out.print("Dimens�o do tabuleiro a gerar(tem de ser maior ou igual a 5 e impar, se introduzir um numero par ser� transformado no impar anterior, se for menor que 5 ser� transformado em 5):");
		int dim=r.nextInt();
		if (dim<5)
			dim=5;
		if(dim% 2==0)
			dim=dim-1;
		char [][] m;
		IMazeBuilder builder = new MazeBuilder();
		
		
		
		
		r.reset();
		System.out.print("Contra quantos drag�es deseja jogar: " );
		int numD=r.nextInt();
		m=builder.buildMaze(dim,numD);
		
		Tabuleiro T = new Tabuleiro(m);
		r.reset();
		System.out.print("Que tipo de estrategia deseja:"+"\n");
		System.out.println("1:Drag�o parado");
		System.out.println("2:Drag�o a mover-se aleatoriamente");
		System.out.println("3:Drag�o a mover-se aleatoriamente e a adormecer aleatoriamente");
		System.out.print("Op��o: ");

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
			if(opcao==3)
				T.Adormece(20);
			
			T.Morre();
			if(opcao==3 || opcao==2){
				for(Iterator<Dragao> it=T.getDrakeList().iterator();it.hasNext();)
					T.MoveD(it.next());
			}
			if(T.AllDead())
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
