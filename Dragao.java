package maze.logic;
import java.util.Random;

/**
 * Dragao.java 
 * class que cria o objecto dragao a partir da super classe Peca.
 * @param Dorme booleano que indica true se o dragao estiver a dormir
 * @param Dramorto booleano que indica true se o dragao estiver morto
 * @see Peca
 *
 */

public class Dragao extends Peca {
	boolean Dorme;
	boolean Dramorto;
	public Dragao(int x,int y){
		super(x,y);
		Dorme=false;
		Dramorto=false;
		
	}
	
	/**Origina o random que da probabilidade, que faz o dragao mover se
	 * 
	 * @return um char que depois é utilizado pelas funções do logic para mover o Dragão
	 */
	public char dragaomove(){
		Random randomdragao = new Random();
		int numero = randomdragao.nextInt(99)+1;
		
		if(numero < 20)
			return 'w';
		else if(numero >= 20 && numero < 40)
			return 'a';
		else if(numero >= 40 && numero < 60)
			return 'd';
		else if(numero >= 60 && numero < 80)
			return 's';
		else
			return 'm';
	}
	/**Actualiza o valor da posicao do dragao
	 * 
	 * @param x nova coordenada x
	 * @param y nova coordenada y 
	 */
	public void Move(int x,int y){
		 this.p=new Point(x,y);
	}
/**
 * "Mata" o Dragão isto é muda o booleano Dramorto para true
 */
public void Morre(){
	this.Dramorto=true;
}
/**
 * Retorna se o dragão está a dormir ou não
 * @return booleano Dorme
 */
public boolean getDorme(){
	return Dorme;
}

/**
 * Adormece ou não dependendo do random
 * @param prob probabilidade de 0-100
 */
public void Adormece(int prob){
	Random randomdragao = new Random();
	int numero=randomdragao.nextInt(99)+1;
	if (numero <= prob)
		Dorme= true;
	else 
		Dorme = false;
}
/**
 * Retorna o estado do dragao
 * @return Dramorto, o estado de vida do Dragão
 */
public boolean getDramorto(){
	return Dramorto;
}
}
