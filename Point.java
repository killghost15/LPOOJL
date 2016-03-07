package maze.logic;

public class Point {
	private int x;
	private int y;
	public Point(int a,int b){
		x=a;
		y=b;
	}
public int getX(){
	return x;
}
public int getY(){
	return y;
}
public boolean equals(Object z){
	if(z!= null && z instanceof Point && this.getX()==((Point)z).getX() && this.getY()==((Point)z).getY() )
		return true;
	else return false;
}
public boolean adjacentTo(Point p) {
	return Math.abs(p.x - this.x) + Math.abs(p.y - this.y) == 1;
}
}
