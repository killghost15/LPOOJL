package maze.logic;

import java.util.Random;
import java.util.Stack;

public class MazeBuilder implements IMazeBuilder {

	@Override
	public char[][] buildMaze(int size) throws IllegalArgumentException {
		if (size % 2 ==0)throw new IllegalArgumentException();
		char [][] maze= new char[size][size];
		for(int i=0;i<maze.length;i++)
			for(int j=0;j<maze[i].length;j++)
				maze[i][j]='X';
		
		for(int i=0;i<maze.length;i++)
			for(int j=0;j<maze[i].length;j++){
				if(i%2!=0 && j %2 !=0 ){
					maze[i][j]=' ';
				}
			}
		int x=0;
		int y=0;
		Random r=new Random();
		while (maze [y][x]!=' ' || (x!=1 && x!=size-2 && y!=size-2 && y!=1))
		{
		x=r.nextInt(size-1);
		y=r.nextInt(size-1);
		}
		Point guideCell=new Point((x-1)/2,(y-1)/2);
		
		if(x==1)
			maze[y][x-1]='S';
		else if(x==size-2)
			maze[y][x+1]='S';
		else if(y==1)
			maze[y-1][x]='S';
		else if (y==size-2)
			maze[y+1][x]='S';
		
		
		boolean [][]visitedCells= new boolean [(size-1)/2][(size-1)/2];
		for(int i=0;i<visitedCells.length;i++)
			for(int j=0;j<visitedCells[i].length;j++){
				visitedCells[i][j]=false;
			}
		visitedCells[guideCell.getY()][guideCell.getX()]=true;
		Stack <Point> l= new Stack<Point>();
		l.push(guideCell);
		//System.out.println(l.peek().getY()+","+l.peek().getX()+"\n"); debug
		while(!l.isEmpty()){
			guideCell=l.peek();
			char num;
			num=RandomDir();
			switch(num){
			case 'w':if(guideCell.getY()-1>=0){
				if(!visitedCells[guideCell.getY()-1][guideCell.getX()]){
					for(int k=(guideCell.getY()-1)*2+1;k <guideCell.getY()*2+1;k++){
						maze[k][guideCell.getX()*2+1]=' ';
					}
					
					guideCell=new Point(guideCell.getX(),guideCell.getY()-1);
					visitedCells[guideCell.getY()][guideCell.getX()]=true;
					l.push(guideCell);
					
					
				}
			}
			break;
			case 'd':if(guideCell.getX()+1< visitedCells.length){
				if(!visitedCells[guideCell.getY()][guideCell.getX()+1]){
					for(int k=guideCell.getX()*2+1;k <(guideCell.getX()+1)*2+1;k++){
						maze[guideCell.getY()*2+1][k]=' ';
					}
					
					guideCell=new Point(guideCell.getX()+1,guideCell.getY());
					visitedCells[guideCell.getY()][guideCell.getX()]=true;
					l.push(guideCell);
					
					
				}
			}
			break;
			case 's':if(guideCell.getY()+1<visitedCells.length){
				if(!visitedCells[guideCell.getY()+1][guideCell.getX()]){
					for(int k=(guideCell.getY())*2+1;k <(guideCell.getY()+1)*2+1;k++){
						maze[k][guideCell.getX()*2+1]=' ';
					}
					
					guideCell=new Point(guideCell.getX(),guideCell.getY()+1);
					visitedCells[guideCell.getY()][guideCell.getX()]=true;
					l.push(guideCell);
					
					
				}
			}
			break;
			case 'a':if(guideCell.getX()-1>= 0){
				if(!visitedCells[guideCell.getY()][guideCell.getX()-1]){
					for(int k=(guideCell.getX()-1)*2+1;k <guideCell.getX()*2+1;k++){
						maze[guideCell.getY()*2+1][k]=' ';
					}
					
					guideCell=new Point(guideCell.getX()-1,guideCell.getY());
					visitedCells[guideCell.getY()][guideCell.getX()]=true;
					l.push(guideCell);
					
					
				}
			}
			break;
			default : 
				break;
			}
			if(!AvailableCells(guideCell,visitedCells)){
				while(!AvailableCells(l.peek(),visitedCells)){
					l.pop();
					if(l.empty())break;
					}
			}
			
		}
		int h1=0;
		int h2=0;
		while (maze [h2][h1]!=' ')
		{
		h1=r.nextInt(size-1);
		h2=r.nextInt(size-1);
		}
		maze[h2][h1]='H';
		int d1=0;
		int d2=0;
		
		while (maze [d2][d1]!=' ' || (maze[d2-1][d1]=='H' || maze[d2+1][d1]=='H' || maze[d2][d1-1]=='H' || maze[d2][d1+1]=='H'))
		{
		d1=r.nextInt(size-1);
		d2=r.nextInt(size-1);
		}
		maze[d2][d1]='D';
		
		int e1=0;
		int e2=0;
		
		while(maze[e2][e1]!=' ')
		{
			e1=r.nextInt(size-1);
			e2=r.nextInt(size-1);
		}
		maze[e2][e1]='E';
		return maze;	
		}
	
	//vê se existem celulas q ainda n foram visitadas adjancentes à Cell
	public boolean AvailableCells(Point Cell,boolean [][]a){
		if(Cell.getX()+1==a.length||Cell.getX()==0 ||Cell.getY()+1==a.length||Cell.getY()==0){
			if(Cell.getY()+1==a.length && Cell.getX()+1==a.length){
				if(!a[Cell.getY()-1][Cell.getX()]||!a[Cell.getY()][Cell.getX()-1])
					return true;
			}
			 if(Cell.getY()==0 && Cell.getX()==0){
				if(!a[Cell.getY()+1][Cell.getX()]||!a[Cell.getY()][Cell.getX()+1])
					return true;
				}
			 if(Cell.getY()+1==a.length && Cell.getX()!=0 && Cell.getX()!=a.length-1){
				if(!a[Cell.getY()-1][Cell.getX()]|| !a[Cell.getY()][Cell.getX()-1]||!a[Cell.getY()-1][Cell.getX()+1])
					return true;
				}
			 if(Cell.getY()==0 && Cell.getX()!=0 && Cell.getX()!=a.length-1)
				if(!a[Cell.getY()+1][Cell.getX()]||!a[Cell.getY()][Cell.getX()-1]||!a[Cell.getY()][Cell.getX()+1])
					return true;
			 if (Cell.getX()+1==a.length && Cell.getY()!=0 && Cell.getY()!=a.length-1)
					if(!a[Cell.getY()+1][Cell.getX()]||!a[Cell.getY()-1][Cell.getX()]||!a[Cell.getY()][Cell.getX()-1])
					return true;
			 if (Cell.getX()==0 && Cell.getY()!=0 && Cell.getY()!=a.length-1)
				if(!a[Cell.getY()+1][Cell.getX()]||!a[Cell.getY()-1][Cell.getX()]||!a[Cell.getY()][Cell.getX()+1])
					return true;
			 if (Cell.getX()==0 && Cell.getY()==a.length-1)
					if(!a[Cell.getY()-1][Cell.getX()]||!a[Cell.getY()][Cell.getX()+1])
						return true;
			 if(Cell.getX()==a.length-1 && Cell.getY()==0)
					if(!a[Cell.getY()+1][Cell.getX()]||!a[Cell.getY()][Cell.getX()-1])
							return true;
		}
		else{
			if(!a[Cell.getY()+1][Cell.getX()] || !a[Cell.getY()-1][Cell.getX()]||!a[Cell.getY()][Cell.getX()+1]||!a[Cell.getY()][Cell.getX()-1])
				return true;
			else
				return false;
		}
		return false;
		
	}
	public char RandomDir(){
		int num;
		Random r=new Random();
		num=r.nextInt(99)+1;
		if (num<25)
			return 'w';
		else if(num >= 25 && num < 50 )
			return'd';
		else if (num >= 50 && num <75)
			return 's';
		else 
			return 'a';
		
	}


}
