package maze.logic;

public class Peca {
	Point p;
	public Peca(int x,int y){
		p= new Point(x,y);
		
	}
	public int getX(){
		return p.getX();
	}
	public int getY(){
		return p.getY();
	}
	public Point getPosition(){
		return p;
	}
}
