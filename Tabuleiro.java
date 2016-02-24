package maze.logic;

public class Tabuleiro {
	private char tabuleiro [][];
	private boolean Dramorto;
	private boolean Morto;
	private Heroi h;
	private Dragao d;
	private Saida s;
	private Espada e;
	//cria tabuleiro
	public Tabuleiro(){
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
		
	
	}
	// Verifica se quando o heroi e o dragao estao juntos e qual morre
	public void Morre(){
		if((tabuleiro[h.getY()-1][h.getX()]=='D' || tabuleiro[h.getY()][h.getX()-1]=='D' || tabuleiro[h.getY()+1][h.getX()]=='D' || tabuleiro[h.getY()][h.getX()+1]=='D') && !h.Armado() && !d.getDorme())
			{
			Morto=true;
			}
		if ((tabuleiro[h.getY()-1][h.getX()]=='D' || tabuleiro[h.getY()][h.getX()-1]=='D' || tabuleiro[h.getY()+1][h.getX()]=='D' || tabuleiro[h.getY()][h.getX()+1]=='D') && h.Armado())
		{
			tabuleiro[d.getY()][d.getX()]=' ';
			Dramorto=true;
		}
		
	}
	// Verfica se a posi��o nao e uma parede e n�o permite ao heroi ir para a saida sem matar o drag�o
	public boolean Valida(int x,int y){
		if (tabuleiro[y][x]=='X')
			return false;
		if(tabuleiro[y][x]=='S' && !Dramorto){
			System.out.println("AINDA N�O MATOU O DRAG�O!");
			return false;
			}
		else return true;
			
	}
	public void Adormece (){
		d.Adormece();
	}
	//Drag�o Move-se se n�o estiver morto ou a dormir, e tbm n�o se move se o heroi morreu.
	public void MoveD(){
		if (Dramorto || Morto|| d.getDorme()){
			if( !Dramorto && d.getDorme())tabuleiro[d.getY()][d.getX()]='Z';
			else return;
		}
		else{
			char direc= d.dragaomove();
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
			else MoveD();
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
			else MoveD();
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
			else MoveD();
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
			else MoveD();
			break;
			case 'm':
				break;
			}
		}
	}
	
	
	// Move o heroi de acordo com uma direc��o direc w para cima,a esquerda,s para baixo, d para a direita
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
			default: System.out.print("N�o � uma direc��o aceite");
			break;
		}
		
		}
	//Desenha o Tabuleiro
	public void DesenhaTabuleiro(){
		for(int i=0; i< 10;i++){
			for(int j=0; j<10;j++){
				System.out.print(tabuleiro[i][j]+ " ");
			}
			System.out.print("\n");
		}
	}
	// Verifica o estado de vida do drag�o se estiver morto= true
	public boolean getEstadoDragao(){
		return Dramorto;
	}
	//fun��es uteis para testes de erro 
	public Heroi getHeroi(){
		return h;
	}
	public Dragao getDragao(){
		return d;
	}
	// testa se chegaste a saida j� depois de matares o drag�o pois a fun��o v�lida() n�o te deixa ir para saida sem matares o drag�o
	public boolean Saiu(){
		if(s.getY()==h.getY() && s.getX()== h.getX())return true;
		
		else return false;
	}
	public boolean getEstado(){
		return Morto;
	}
	
}
