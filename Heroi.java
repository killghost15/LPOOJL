package maze.logic;
/**
 * Heroi.java 
 * subclasse de peca que representa o nosso personagem que podemos mover
 * @param Espada booleano que indica se o heroi está armado ou não
 * @see Peca
 *
 */
public class Heroi extends Peca {
	boolean Espada;
	public Heroi(int x, int y){
		super(x,y);
		
		Espada=false;
		
	}
	/**
	 * Muda o booleano para o estado de jogo em que o heroi  apanha a espada
	 * 
	 */
	public void ApanhaEspada(){
		Espada=true;
	}
	/**
	 * Retorna se o heroi está armado ou não
	 * @return
	 */
	public boolean Armado(){
		return Espada;
	}
	/**
	 *  Muda a posição do heroi
	 * @param x nova coordenada x 
	 * @param y nova coordenada y
	 */
	public void Move(int x,int y){
		 this.p=new Point(x,y);
	}

}
