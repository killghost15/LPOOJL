package maze.gui;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import maze.logic.*;

@SuppressWarnings("serial")
public class GameUi extends JPanel implements KeyListener {
private BufferedImage hero;
private BufferedImage drake;
private BufferedImage wall;
private BufferedImage exit;
private BufferedImage sword;
private BufferedImage ontop;
private BufferedImage sleep;
private BufferedImage armed;
private BufferedImage empty;
private int cellWidth=30;
private int cellHeight=30;
private int x,y;
private Tabuleiro T;
/**
	 * Create the panel.
	 */
	public GameUi( char [][] maze) {
		T= new Tabuleiro(maze);
		try {
			sword =  ImageIO.read(new File("sword.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			hero =  ImageIO.read(new File("hero.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			drake =  ImageIO.read(new File("drake.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			wall =  ImageIO.read(new File("wall.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			exit =  ImageIO.read(new File("exit.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			ontop =  ImageIO.read(new File("ontop.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			sleep =  ImageIO.read(new File("sleep.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			armed =  ImageIO.read(new File("armed.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			empty =  ImageIO.read(new File("empty.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.addKeyListener(this);
		}
		
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT:
			repaint();
			break;
			
		case KeyEvent.VK_RIGHT: 
			//System.out.println("x=" + x);
			repaint();
			break;

		case KeyEvent.VK_UP:
			repaint();
			break;

		case KeyEvent.VK_DOWN:
			repaint();
			break;
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
		

	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);// clears the backgorund ...
		x=0;y=0;
		for(int i=0; i< T.getLabirinto().length;i++){
			x=0;
			for(int j=0;j<T.getLabirinto().length;j++){
				switch (T.getLabirinto()[i][j]){
				case 'X':g.drawImage(wall,x,y, x+cellWidth,y+cellHeight,0,0,30,30, null);
				break;
				case 'H':g.drawImage(hero,x,y, x+cellWidth,y+cellHeight,0,0,30,30, null);
				break;
				case 'D':g.drawImage(drake,x,y,x+cellWidth,y+cellHeight,0,0,30,30, null);
				break;
				case 'E':g.drawImage(sword,x,y,x+cellWidth,y+cellHeight,0,0,30,30, null);
				break;
				case 'Z':g.drawImage(sleep,x,y, x+cellWidth,y+cellHeight,0,0,30,30, null);
				break;
				case 'S':g.drawImage(exit,x,y, x+cellWidth,y+cellHeight,0,0,30,30, null);
				break;
				case 'F':g.drawImage(ontop,x,y, x+cellWidth,y+cellHeight,0,0,30,30, null);
				break;	
				case 'A':g.drawImage(armed,x,y, x+cellWidth,y+cellHeight,0,0,30,30, null);
				break;
				case ' ':g.drawImage(empty,x,y, x+cellWidth,y+cellHeight,0,0,30,30, null);
				break;
				}
				x+=cellWidth;
			}
			y+=cellHeight;
			}
		}

}
