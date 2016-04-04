package maze.logic;


import java.util.LinkedList;
/**
 * 
 * Tabuleiro.java
 * ao meu jogo chamei de Tabuleiro a class que cria o labirinto e depois efectua as movimentações e gestão do estado de jogo
 *
 */

public class Tabuleiro {
	private char tabuleiro [][];
	private boolean Morto;
	private Heroi h;
	private LinkedList<Dragao> l;
	private Saida s;
	private Espada e;
	//cria tabuleiro(labirinto)
/*	public Tabuleiro(){
		h=new Heroi(1,1);
		d=new Dragao(1,3);
		s=new Saida(9,5);
		e=new Espada(1,8);
		Dramorto=false;
		Morto=false;
		tabuleiro = new char[10][10];
		
		for(int i=0; i< 10;i++){
			for(int j=0; j<10;j++){
				if (i==0 || j==0 || i == 9 || j== 9)
					tabuleiro [i][j]='X';
				else 
					tabuleiro[i][j]=' ';
				}
			
		}
		
		tabuleiro[1][1]='H';
		tabuleiro[3][1]= 'D';
		tabuleiro[5][9]='S';
		tabuleiro[8][1]='E';
		
		for(int i=2; i <5;i++){
			for(int j=2; j< 4;j++)
				tabuleiro[i][j]='X';
		}
		
		for(int i=6; i <9;i++){
			for(int j=2; j< 4;j++)
				tabuleiro[i][j]='X';
		}
		
		for(int i=2; i <5;i++){
			int j=5;
				tabuleiro[i][j]='X';
		}
		
		for(int i=6; i <8;i++){
			int j=5;
				tabuleiro[i][j]='X';
		}
		for(int i=2; i <8;i++){
			int j=7;
				tabuleiro[i][j]='X';
		}
		
	
	}*/
	// construtor para introduzir uma matriz como tabuleiro.
/**
 * 	Inicializa o jogo, cria um heroi,uma espada, uma saida ,um numero de dragões e coloca-os na estrutura de dados linked List l
 * @param m matriz de chars que é gerada pelo gerador de labirinto
 * @see MazeBuilder
 */
	public Tabuleiro(char m[][]){
		tabuleiro = m;
		Morto=false;
		l=new LinkedList<Dragao>();
		for(int i=0;i<m.length;i++){
			for (int j=0; j< m[i].length;j++){
				if (m[i][j]=='H'){
					h=new Heroi(j,i);
				}
				if (m[i][j]=='A')
				{
					h=new Heroi(j,i);
					h.ApanhaEspada();
				}
				if(m[i][j]=='F'){
					Dragao d2=new Dragao(j,i);
					l.add(d2);
					e=new Espada(j,i);
				}
				if (m[i][j]=='D'){
					Dragao d=null;
					d = new Dragao(j,i);
					l.add(d);
					}
				if(m[i][j]=='E')
					e=new Espada(j,i);
				if(m[i][j]=='S')
					s=new Saida(j,i);
			}
		}
	}
	/**
	 *  Verifica se quando o heroi e o dragao estao juntos e qual morre também verificando se o dragão está a dormir ou não
	 *	
	 */
	public void Morre(){
		
		if (!h.Armado()){
		for(int i=0;i<l.size();i++)
			{
			if(h.getPosition().adjacentTo(l.get(i).getPosition()) && !l.get(i).getDorme() && !l.get(i).getDramorto() ){
			Morto=true;
			}
			}
		}
		else{
			for(int i=0;i<l.size();i++)
		{
			if(h.getPosition().adjacentTo(l.get(i).getPosition())){
			tabuleiro[l.get(i).getPosition().getY()][l.get(i).getPosition().getX()]=' ';
			l.get(i).Morre();
			}
		}
		}
		
	}
	/**
	 *  Verfica se a posição nao e uma parede e não permite ao heroi ir para a saida sem matar o dragão
	 * @param x coordenada horizontal da posição
	 * @param y coordenada vertical da posição
	 * @return true se for uma movimentação valida 
	 */
	public boolean Valida(int x,int y){
		if (tabuleiro[y][x]=='X')
			return false;
		if(tabuleiro[y][x]=='S' && !AllDead()){
			System.out.println("AINDA NÃO MATOU Pelo menos um DRAGÃO!"); //talvez eliminar mais tarde
			return false;
			}
		else return true;
			
	}
	/**
	 * Verifica se os Dragões estão todos mortos ou se algum ainda está vivo
	 * @return true se estiverem todos os dragões mortos
	 */
	public boolean AllDead() {
		for(int i=0;i<l.size();i++)
			if(!l.get(i).getDramorto())
				return false;
		return true;
	}
	/**
	 * está preparado para se poder perguntar ao utilizador qual a probabilidade de o dragão adormecer 0-100
	 * @param prob probabilidade de adormecer
	 */
	public void Adormece (int prob){
		for(int i=0;i<l.size();i++)
			l.get(i).Adormece(prob);
	}
	/**
	 * Dragão Move-se se não estiver morto ou a dormir, e tbm não se move se o heroi morreu.
	 * Chama a função MoveComDirecção
	 * @param d Dragão 
	 */
	public void MoveD(Dragao d){
		if (d.getDramorto() || Morto|| d.getDorme()){
			if( !d.getDramorto() && d.getDorme())tabuleiro[d.getY()][d.getX()]='Z';
			else return;
		}
		else{
		char direc= d.dragaomove();
		MoveComDirecao(direc,d);
		}
	}
	/**
	 * Tenta Mover na determinada direcção direc se não for uma direcção válida volta para a função MoveD() que gera outra direcção de maneira que só fique no mesmo sitio se sair a direcção manter 
	 * @param direc char direcção
	 * @param d Dragão 
	 * 
	 */
	public void MoveComDirecao(char direc,Dragao d){
		
			
			switch (direc){
			case 'w': if(Valida(d.getX(),d.getY()-1)){
				if(tabuleiro[d.getY()-1][d.getX()]=='E'){
				tabuleiro[d.getY()][d.getX()]=' ';
				tabuleiro[d.getY()-1][d.getX()]='F';
				d.Move(d.getX(), d.getY()-1);
			}
				else 
				{
					if (d.getX()==e.getX() && d.getY()==e.getY()){
					tabuleiro[d.getY()][d.getX()]='E';
					tabuleiro[d.getY()-1][d.getX()]='D';
					d.Move(d.getX(),d.getY()-1);
				}
					else {
						tabuleiro[d.getY()][d.getX()]=' ';
						tabuleiro[d.getY()-1][d.getX()]='D';
						d.Move(d.getX(),d.getY()-1);
					}
				}
			}
			else MoveD(d);
			break;
			case 'a': if(Valida(d.getX()-1,d.getY())){
				if(tabuleiro[d.getY()][d.getX()-1]=='E'){
				tabuleiro[d.getY()][d.getX()]=' ';
				tabuleiro[d.getY()][d.getX()-1]='F';
				d.Move(d.getX()-1, d.getY());
			}
				else 
				{
					if (d.getX()==e.getX() && d.getY()==e.getY()){
					tabuleiro[d.getY()][d.getX()]='E';
					tabuleiro[d.getY()][d.getX()-1]='D';
					d.Move(d.getX()-1,d.getY());
				}
					else {
						tabuleiro[d.getY()][d.getX()]=' ';
						tabuleiro[d.getY()][d.getX()-1]='D';
						d.Move(d.getX()-1,d.getY());
					}
				}
			}
			else MoveD(d);
			break;
			case 's' : 
				if(Valida(d.getX(),d.getY()+1)){
				if(tabuleiro[d.getY()+1][d.getX()]=='E'){
				tabuleiro[d.getY()][d.getX()]=' ';
				tabuleiro[d.getY()+1][d.getX()]='F';
				d.Move(d.getX(), d.getY()+1);
			}
				else 
				{
					if (d.getX()==e.getX() && d.getY()==e.getY()){
					tabuleiro[d.getY()][d.getX()]='E';
					tabuleiro[d.getY()+1][d.getX()]='D';
					d.Move(d.getX(),d.getY()+1);
				}
					else {
						tabuleiro[d.getY()][d.getX()]=' ';
						tabuleiro[d.getY()+1][d.getX()]='D';
						d.Move(d.getX(),d.getY()+1);
					}
				}
			}
			else MoveD(d);
			break;
			case 'd':if(Valida(d.getX()+1,d.getY())){
				if(tabuleiro[d.getY()][d.getX()+1]=='E'){
				tabuleiro[d.getY()][d.getX()]=' ';
				tabuleiro[d.getY()][d.getX()+1]='F';
				d.Move(d.getX()+1, d.getY());
			}
				else 
				{
					if (d.getX()==e.getX() && d.getY()==e.getY()){
					tabuleiro[d.getY()][d.getX()]='E';
					tabuleiro[d.getY()][d.getX()+1]='D';
					d.Move(d.getX()+1,d.getY());
				}
					else {
						tabuleiro[d.getY()][d.getX()]=' ';
						tabuleiro[d.getY()][d.getX()+1]='D';
						d.Move(d.getX()+1,d.getY());
					}
				}
			}
			else MoveD(d);
			break;
			case 'm':
				break;
			}
		}
	
	
	/**
	 * Move o heroi de acordo com uma direcção direc w para cima,a esquerda,s para baixo, d para a direita se não for valida fica no mesmo sitio
	 * @param direc char direcção
	 */
	public void MoveH(char direc){
		
		switch(direc){
		case 'w': if(Valida(h.getX(),h.getY()-1)){
			if(tabuleiro[h.getY()-1][h.getX()]=='E'){
				h.ApanhaEspada();
				tabuleiro[h.getY()][h.getX()]=' ';
				tabuleiro[h.getY()-1][h.getX()]='A';
				h.Move(h.getX(), h.getY()-1);
				}
			else {
				if(h.Armado()){
					tabuleiro[h.getY()][h.getX()]=' ';
					tabuleiro[h.getY()-1][h.getX()]='A';
					h.Move(h.getX(), h.getY()-1);
				}
				else{
					tabuleiro[h.getY()][h.getX()]=' ';
					tabuleiro[h.getY()-1][h.getX()]='H';
					h.Move(h.getX(), h.getY()-1);
				}
			}
			
			
		}
		break;
		case 'a':if(Valida(h.getX()-1,h.getY())){
			if(tabuleiro[h.getY()][h.getX()-1]=='E'){
				h.ApanhaEspada();
				tabuleiro[h.getY()][h.getX()]=' ';
				tabuleiro[h.getY()][h.getX()-1]='A';
				h.Move(h.getX()-1, h.getY());
				}
			else{
				if(h.Armado()){
					tabuleiro[h.getY()][h.getX()]=' ';
					tabuleiro[h.getY()][h.getX()-1]='A';
					h.Move(h.getX()-1, h.getY());
				}
				else{
					tabuleiro[h.getY()][h.getX()]=' ';
					tabuleiro[h.getY()][h.getX()-1]='H';
					h.Move(h.getX()-1, h.getY());
				}
			}
			
			
		}
		break;
		case 'd':if(Valida(h.getX()+1,h.getY())){
			if(tabuleiro[h.getY()][h.getX()+1]=='E'){
				h.ApanhaEspada();
				tabuleiro[h.getY()][h.getX()]=' ';
				tabuleiro[h.getY()][h.getX()+1]='A';
				h.Move(h.getX()+1, h.getY());
				}
			else{
				if(h.Armado()){
					tabuleiro[h.getY()][h.getX()]=' ';
					tabuleiro[h.getY()][h.getX()+1]='A';
					h.Move(h.getX()+1, h.getY());
				}
				else{
					tabuleiro[h.getY()][h.getX()]=' ';
					tabuleiro[h.getY()][h.getX()+1]='H';
					h.Move(h.getX()+1, h.getY());
				}
			}
			
			
		}
		break;
		case 's':if(Valida(h.getX(),h.getY()+1)){
			if(tabuleiro[h.getY()+1][h.getX()]=='E'){
				h.ApanhaEspada();
				tabuleiro[h.getY()][h.getX()]=' ';
				tabuleiro[h.getY()+1][h.getX()]='A';
				h.Move(h.getX(), h.getY()+1);
				}
			else{
				if(h.Armado()){
					tabuleiro[h.getY()][h.getX()]=' ';
					tabuleiro[h.getY()+1][h.getX()]='A';
					h.Move(h.getX(), h.getY()+1);
				}
				else{
					tabuleiro[h.getY()][h.getX()]=' ';
					tabuleiro[h.getY()+1][h.getX()]='H';
					h.Move(h.getX(), h.getY()+1);
				}
			}
			
			
		}
		break;
			default: System.out.println("Não é uma direcção aceite");
			break;
		}
		
		}
	/**
	 * Desenha o labirinto em modo de texto 
	 */
	public void DesenhaTabuleiro(){
		for(int i=0; i<tabuleiro.length;i++){
			for(int j=0; j<tabuleiro.length;j++){
				System.out.print(tabuleiro[i][j]+ " ");
			}
			System.out.print("\n");
		}
	}
	//funções uteis para testes de erro 
	/**
	 * Retorna heroi
	 * @see Heroi
	 * @return h heroi deste jogo
	 */
	public Heroi getHeroi(){
		return h;
	}
	/**
	 * Retorna a matriz do labirinto
	 * @return tabuleiro matriz 
	 */
	public char [][] getLabirinto(){
		return tabuleiro;
	}
	/**
	 * Retorna o Dragão da lista de Dragões com indice i
	 * @param i indice
	 * @return Dragão
	 */
	public Dragao getDragao(int i){
		return l.get(i);
	}
	/** 
	 * Retorna um objecto point da posição de heroi
	 * @see Point
	 * @return Point
	 */
	public Point getHeroPosition(){
		return h.getPosition();
	}
	/**
	 *  testa se chegaste a saida já depois de matares o dragão pois a função válida() não te deixa ir para saida sem matares o(s) dragão(ões)
	 * @return true se ganhaste se não retorna
	 */
	public boolean Vitoria(){
		if(s.getY()==h.getY() && s.getX()== h.getX())return true;
		
		else return false;
	}
	/**
	 * Retorna o estado de heroi
	 * @return true se o heroi está morto
	 */
	public boolean getEstado(){
		return Morto;
	}
	/**
	 * Retorna um objecto do tipo point que dá a posição do Dragão de indice i
	 * @param i indice
	 * @return Point
	 */
	public Point getDrakePosition(int i){
		return l.get(i).getPosition();
	}
	/**
	 * Retorna um objecto do tipo Point que dá a posição da espada
	 * @return Point
	 */
	public Point getSwordPosition(){
		return e.getPosition();
	}
	/**
	 * Retorna a lista de Dragões
	 * @return LinkedList<Dragao>
	 */
	public LinkedList<Dragao> getDrakeList(){
		return l;
	}
	/**
	 * Adiciona o o dragão à LinkedList
	 * @param d
	 */
	public void AddDrake(Dragao d){
		l.add(d);
	}
	/**
	 * Método toString do labirinto
	 */
	public String toString(){
		String s="";
		for(int i=0; i<tabuleiro.length;i++){
			for(int j=0; j<tabuleiro.length;j++){
				s=s+tabuleiro[i][j]+" ";
			}
		s=s+"\n";
		}
		return s;
		}
}