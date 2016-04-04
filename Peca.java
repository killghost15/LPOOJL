package maze.logic;
/**
 * 
 * Peca.java
 * Super classe que cria os objectos de um tabuleiro para mais efeciente tratamento com as movimenta��es e gest�o da posi��o 
 * @see Point 
 * 
 *
 */
public class Peca {
	Point p;
	public Peca(int x,int y){
		p= new Point(x,y);
		
	}
	/**
	 * 
	 * @return coordenada x da Peca
	 */
	public int getX(){
		return p.getX();
	}
	/**
	 * 
	 * @return coordenada y da peca
	 */
	public int getY(){
		return p.getY();
	}
	/**
	 * 
	 * @return p objecto point que � variavel privada de Peca
	 */
	public Point getPosition(){
		return p;
	}
}
