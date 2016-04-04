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
private BufferedImage victory;
private BufferedImage defeat;
private JButton restart;
private Object option;
private int cellWidth=30;
private int cellHeight=30;
private int x,y;
private Tabuleiro T;
/**
	 * Create the panel.
	 */
	public GameUi( char [][] maze,Object option) {
		T= new Tabuleiro(maze);
		this.option=option;
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
		try {
			victory =  ImageIO.read(new File("victory.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			defeat =  ImageIO.read(new File("defeat.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.addKeyListener(this);
		setLayout(null);
		
		restart = new JButton("Restart");
		restart.setBackground(Color.LIGHT_GRAY);
		restart.setForeground(Color.BLACK);
		restart.setVisible(false);
		restart.setEnabled(false);
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameApp n=new GameApp();
				n.Visible();
			}
		});
		restart.setBounds(349, 80, 91, 23);
		add(restart);
		}
		
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT:
			if(T.getEstado()||T.Vitoria())break;
			T.MoveH('a');
			T.Morre();
			if (option=="A mover-se e a adormecer")
				T.Adormece(20);
			if(option=="A mover-se aleatoriamente"||option== "A mover-se e a adormecer"){
				for(int i=0;i< T.getDrakeList().size();i++)
					T.MoveD(T.getDrakeList().get(i));
			}
			T.Morre();
			repaint();
			break;
			
		case KeyEvent.VK_RIGHT: 
			if(T.getEstado()||T.Vitoria())break;
			T.MoveH('d');
			T.Morre();
			if (option=="A mover-se e a adormecer")
				T.Adormece(20);
			if(option=="A mover-se aleatoriamente"||option== "A mover-se e a adormecer"){
				for(int i=0;i< T.getDrakeList().size();i++)
					T.MoveD(T.getDrakeList().get(i));
			}
			T.Morre();
			repaint();
			break;

		case KeyEvent.VK_UP:
			if(T.getEstado()||T.Vitoria())break;
			T.MoveH('w');
			T.Morre();
			if (option=="A mover-se e a adormecer")
				T.Adormece(20);
			if(option=="A mover-se aleatoriamente"||option== "A mover-se e a adormecer"){
				for(int i=0;i< T.getDrakeList().size();i++)
					T.MoveD(T.getDrakeList().get(i));
			}
			T.Morre();
			repaint();
			break;

		case KeyEvent.VK_DOWN:
			if(T.getEstado()||T.Vitoria())break;
			T.MoveH('s');
			T.Morre();
			if (option=="A mover-se e a adormecer")
				T.Adormece(20);
			if(option=="A mover-se aleatoriamente"||option== "A mover-se e a adormecer"){
				for(int i=0;i< T.getDrakeList().size();i++)
					T.MoveD(T.getDrakeList().get(i));
			}
			T.Morre();
			repaint();
			break;
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
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
		if(T.Vitoria()){g.drawImage(victory,x,y, x+victory.getWidth(),y+victory.getHeight(),0,0,victory.getWidth(),victory.getHeight(), null);
		//this.removeKeyListener(this);
		restart.setVisible(true);
		restart.setEnabled(true);
		return;
		}
		if(T.getEstado()){g.drawImage(defeat,x,y, x+defeat.getWidth(),y+defeat.getHeight(),0,0,defeat.getWidth(),defeat.getHeight(), null);
		//this.removeKeyListener(this);
		restart.setVisible(true);
		restart.setEnabled(true);
		return;
		}
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
