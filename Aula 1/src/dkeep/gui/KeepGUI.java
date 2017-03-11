package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.SwingConstants;

import dkeep.logic.DungeonMap;
import dkeep.logic.GameState;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KeepGUI {

	private JFrame frame;
	private JTextField txtAs;
	
	GameState state;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KeepGUI window = new KeepGUI();
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
	public KeepGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setBounds(22, 13, 160, 19);
		lblNumberOfOgres.setFont(new Font("Inconsolata", Font.PLAIN, 14));
		lblNumberOfOgres.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(lblNumberOfOgres);
		
		txtAs = new JTextField();
		txtAs.setFont(new Font("Inconsolata", Font.PLAIN, 16));
		txtAs.setBounds(171, 10, 63, 22);
		txtAs.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(txtAs);
		txtAs.setColumns(2);
		
		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setHorizontalAlignment(SwingConstants.LEFT);
		lblGuardPersonality.setFont(new Font("Inconsolata", Font.PLAIN, 14));
		lblGuardPersonality.setBounds(22, 45, 146, 19);
		frame.getContentPane().add(lblGuardPersonality);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("");
		comboBox.setFont(new Font("Inconsolata", Font.PLAIN, 14));
		comboBox.addItem("Rookie");
		comboBox.addItem("Drunk");
		comboBox.addItem("Suspicious");
		comboBox.setBounds(171, 45, 171, 22);
		frame.getContentPane().add(comboBox);
		
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Inconsolata", Font.PLAIN, 30));
		textArea.setBounds(22, 99, 400, 360);
		frame.getContentPane().add(textArea);
		
		JLabel lblNewLabel = new JLabel("You may start a new game.");
		lblNewLabel.setFont(new Font("Inconsolata", Font.PLAIN, 14));
		lblNewLabel.setBounds(22, 472, 200, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DungeonMap map = new DungeonMap();
				GameState gameState = new GameState(map);
				state = gameState;
				
				textArea.setText(state.drawMap());
				lblNewLabel.setText(null);
			}
			
		});
		btnNewGame.setFont(new Font("Inconsolata", Font.PLAIN, 14));
		btnNewGame.setBounds(507, 98, 107, 40);
		frame.getContentPane().add(btnNewGame);
		
		JButton btnUp = new JButton("UP");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				state.processMove("w");
				lblNewLabel.setText(state.message);
				textArea.setText(state.drawMap());
			}
		});
		btnUp.setFont(new Font("Inconsolata", Font.PLAIN, 14));
		btnUp.setBounds(522, 209, 73, 25);
		frame.getContentPane().add(btnUp);
		
		JButton btnLeft = new JButton("LEFT");
		btnLeft.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				state.processMove("a");
				lblNewLabel.setText(state.message);
				textArea.setText(state.drawMap());
			}
		});
		btnLeft.setFont(new Font("Inconsolata", Font.PLAIN, 14));
		btnLeft.setBounds(448, 262, 84, 25);
		frame.getContentPane().add(btnLeft);
		
		JButton btnDown = new JButton("DOWN");
		btnDown.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				state.processMove("s");
				lblNewLabel.setText(state.message);
				textArea.setText(state.drawMap());
			}
		});
		btnDown.setFont(new Font("Inconsolata", Font.PLAIN, 14));
		btnDown.setBounds(522, 314, 73, 25);
		frame.getContentPane().add(btnDown);
		
		JButton btnRight = new JButton("RIGHT");
		btnRight.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				state.processMove("d");
				lblNewLabel.setText(state.message);
				textArea.setText(state.drawMap());
			}
		});
		btnRight.setFont(new Font("Inconsolata", Font.PLAIN, 14));
		btnRight.setBounds(586, 262, 84, 25);
		frame.getContentPane().add(btnRight);
		
		
		
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Inconsolata", Font.PLAIN, 14));
		btnExit.setBounds(507, 459, 107, 40);
		frame.getContentPane().add(btnExit);
		
		
	}
}
