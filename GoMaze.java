package maze.gui;

import java.awt.EventQueue;

import maze.logic.*;

import javax.swing.*;

import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;

public class GoMaze {

	private JFrame frame;
	private JTextField NumDinsert;
	private Tabuleiro T;
	private JButton UP;
	private JButton Down;
	private JButton Right;
	private JButton Left; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GoMaze window = new GoMaze();
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
	public GoMaze() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Game Settings Menu");
		frame.setBounds(500, 150, 678, 523);
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
		
		NumDinsert = new JTextField();
		NumDinsert.setColumns(10);
		NumDinsert.setBounds(133,70,116,22);
		frame.getContentPane().add(NumDinsert);
		JTextArea textArea = new JTextArea();
		textArea.setForeground(Color.BLACK);
		textArea.setBackground(SystemColor.desktop);
		textArea.setEditable(false);
		textArea.setEnabled(false);
		textArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		textArea.setBounds(28, 236, 381, 206);
		JScrollPane scroll=new JScrollPane(textArea);
		scroll.setBounds(28, 236, 381, 206);
		
		frame.getContentPane().add(scroll);
		JLabel label = new JLabel("");
		label.setBounds(38, 460, 127, 14);
		frame.getContentPane().add(label);
		
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
				if(diminsert.getText().equals(""))
					diminsert.setText("5");
				if(Integer.parseInt(diminsert.getText())%2==0||Integer.parseInt(diminsert.getText())<5){
					num = Integer.parseInt(diminsert.getText())-1;
	
				if(Integer.parseInt(diminsert.getText())<5)
					 num=5;
				}
				else
					num=Integer.parseInt(diminsert.getText());
				if(NumDinsert.getText().equals(""))
					NumDinsert.setText("1");
				char [][] m= builder.buildMaze(num , Integer.parseInt(NumDinsert.getText()));
				T=new Tabuleiro(m);
				textArea.setText(T.toString());
				UP.setEnabled(true);
				Down.setEnabled(true);
				Right.setEnabled(true);
				Left.setEnabled(true);
				comboBox.setEnabled(false);
				label.setText("");
				
				
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
		
		
		
		UP = new JButton("UP");
		UP.setEnabled(false);
		UP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				T.MoveH('w');
				T.Morre();
				if(comboBox.getSelectedItem()=="A mover-se e a adormecer")
					T.Adormece(20);
				if(comboBox.getSelectedItem()=="A mover-se aleatoriamente"||comboBox.getSelectedItem()== "A mover-se e a adormecer"){
					for(int i=0;i< T.getDrakeList().size();i++)
						T.MoveD(T.getDrakeList().get(i));
				}
				T.Morre();
				textArea.setText(T.toString());
				if(T.getEstado()||T.Vitoria()){
					UP.setEnabled(false);
					Down.setEnabled(false);
					Right.setEnabled(false);
					Left.setEnabled(false);
					if(T.getEstado())
						label.setText("Morreste, lutar desarmado contra um dragão não é boa ideia");
					if(T.Vitoria())
						label.setText("Venceu");
				}
			}
		});
		UP.setBounds(479, 152, 89, 50);
		frame.getContentPane().add(UP);
		
		Down = new JButton("Down");
		Down.setEnabled(false);
		Down.setBounds(479, 255, 89, 50);
		Down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				T.MoveH('s');
				T.Morre();
				if(comboBox.getSelectedItem()=="A mover-se e a adormecer")
					T.Adormece(20);
				if(comboBox.getSelectedItem()=="A mover-se aleatoriamente"||comboBox.getSelectedItem()== "A mover-se e a adormecer"){
					for(int i=0;i< T.getDrakeList().size();i++)
						T.MoveD(T.getDrakeList().get(i));
				}
				T.Morre();
				textArea.setText(T.toString());
				if(T.getEstado()||T.Vitoria()){
					UP.setEnabled(false);
					Down.setEnabled(false);
					Right.setEnabled(false);
					Left.setEnabled(false);
					if(T.getEstado())
						label.setText("Morreste, lutar desarmado contra um dragão não é boa ideia");
					if(T.Vitoria())
						label.setText("Venceu");
				}
			}
		});
		frame.getContentPane().add(Down);
		
		Right = new JButton("Right");
		Right.setEnabled(false);
		Right.setBounds(528, 202, 89, 50);
		Right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				T.MoveH('d');
				T.Morre();
				if(comboBox.getSelectedItem()=="A mover-se e a adormecer")
					T.Adormece(20);
				if(comboBox.getSelectedItem()=="A mover-se aleatoriamente"||comboBox.getSelectedItem()== "A mover-se e a adormecer"){
					for(int i=0;i< T.getDrakeList().size();i++)
						T.MoveD(T.getDrakeList().get(i));
				}
				T.Morre();
				textArea.setText(T.toString());
				if(T.getEstado()||T.Vitoria()){
					UP.setEnabled(false);
					Down.setEnabled(false);
					Right.setEnabled(false);
					Left.setEnabled(false);
					if(T.getEstado())
						label.setText("Morreste, lutar desarmado contra um dragão não é boa ideia");
					if(T.Vitoria())
						label.setText("Venceu");
				}
			}
		});
		frame.getContentPane().add(Right);
		
		Left = new JButton("Left");
		Left.setEnabled(false);
		Left.setBounds(429, 202, 89, 50);
		Left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				T.MoveH('a');
				T.Morre();
				if(comboBox.getSelectedItem()=="A mover-se e a adormecer")
					T.Adormece(20);
				if(comboBox.getSelectedItem()=="A mover-se aleatoriamente"||comboBox.getSelectedItem()== "A mover-se e a adormecer"){
					for(int i=0;i< T.getDrakeList().size();i++)
						T.MoveD(T.getDrakeList().get(i));
				}
				T.Morre();
				textArea.setText(T.toString());
				if(T.getEstado()||T.Vitoria()){
					UP.setEnabled(false);
					Down.setEnabled(false);
					Right.setEnabled(false);
					Left.setEnabled(false);
					if(T.getEstado())
						label.setText("Morreste, lutar desarmado contra um dragão não é boa ideia");
					if(T.Vitoria())
						label.setText("Venceu");
				}
			}
		});
		frame.getContentPane().add(Left);
		
		
		
		
	}
}
