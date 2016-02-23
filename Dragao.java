package maze.logic;
import java.util.Random;


public class Dragao extends Peca {
	
	public Dragao(int x,int y){
		super(x,y);
		
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	//Origina o random que da origem a probabilidade, que faz o dragao saltar
	public char dragaomove(){
		Random randomdragao = new Random();
		int numero = randomdragao.nextInt(100)+1;
		
		if(numero < 20)
			return 'w';
		else if(numero > 20 && numero < 40)
			return 'a';
		else if(numero > 40 && numero < 60)
			return 'd';
		else if(numero > 60 && numero < 80)
			return 's';
		else
			return 'm';
	}
	//Actualiza o valor da posicao do dargao
	public void Move(int x,int y){
		this.x=x;
		this.y=y;
	}

}

