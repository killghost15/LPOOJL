package maze.logic;

public class Heroi extends Peca {
	boolean Espada;
	public Heroi(int x, int y){
		super(x,y);
		
		Espada=false;
		
	}
	public void ApanhaEspada(){
		Espada=true;
	}
	public boolean Armado(){
		return Espada;
	}
	public void Move(int x,int y){
		this.x=x;
		this.y=y;
	}

}
