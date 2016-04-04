package maze.gui;

import java.awt.EventQueue;

import maze.logic.*;

import javax.swing.*;

import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
public class GameApp {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameApp window = new GameApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Game Settings Menu");
		frame.setBounds(500, 150, 558, 523);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JLabel dim = new JLabel("Lab dimension:");
		dim.setBounds(28, 23, 95, 16);
		frame.getContentPane().add(dim);
		
		JTextField diminsert=new JTextField();
		diminsert.setColumns(10);
		diminsert.setBounds(133,20,116,22);
		frame.getContentPane().add(diminsert);
		
		JLabel NumD = new JLabel("Number of Drakes:");
		NumD.setBounds(28, 74, 95, 14);
		frame.getContentPane().add(NumD);
		
		JTextField NumDinsert = new JTextField();
		NumDinsert.setColumns(10);
		NumDinsert.setBounds(133,70,116,22);
		frame.getContentPane().add(NumDinsert);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(28, 116, 182, 20);
		comboBox.addItem("Estaticos");
		comboBox.addItem("A mover-se aleatoriamente");
		comboBox.addItem("A mover-se e a adormecer");
		frame.getContentPane().add(comboBox);
	
		JButton btnStart = new JButton("Gerar Labirinto");
		btnStart.setBounds(28, 152, 200, 50);
		btnStart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				IMazeBuilder builder = new MazeBuilder();
				int num = 0;
				if(Integer.parseInt(diminsert.getText())%2==0||Integer.parseInt(diminsert.getText())<5){
					num = Integer.parseInt(diminsert.getText())-1;
	
				if(Integer.parseInt(diminsert.getText())<5)
					 num=5;
				}
				else
					num=Integer.parseInt(diminsert.getText());
				char [][] m= builder.buildMaze(num , Integer.parseInt(NumDinsert.getText()));
				JPanel jogo=new GameUi(m,comboBox.getSelectedItem());
				JScrollPane scroll=new JScrollPane(jogo);
				scroll.setBounds(500, 150, 558, 523);
				frame.setContentPane(scroll);
				frame.pack();
				Dimension di=new Dimension(500,500);
				
				frame.setMinimumSize(di);
				frame.setTitle(NumDinsert.getText()+" Drakes go into a bar with a hero");
				jogo.setVisible(true);
				jogo.requestFocus();
				
				
				
				
			}
		});
		frame.getContentPane().add(btnStart);
		
		
		
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				
			}
		});
		btnExit.setBounds(258, 152, 151, 50);
		frame.getContentPane().add(btnExit);
		
	}
	public void Visible(){
		this.frame.setVisible(true);
	}

}
