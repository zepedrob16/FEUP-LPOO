package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.SwingConstants;

import dkeep.logic.DungeonMap;
import dkeep.logic.GameState;
import dkeep.logic.GameState.State;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class KeepGUI {

	private JFrame frame;
	private JPanel panel;
	private JTextField txtAs;
	
	GameState state;
	private JLabel lblNewLabel;
	private JTextArea textArea;
	private JComboBox<String> comboBox;
	
	private int numberOgres;

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
	public KeepGUI() throws IOException {
		initialize();
	}

	private void play(String direction) {
		if (state.getState() == State.DEFEAT){
			return;
		}
		state.processMove(direction);
		lblNewLabel.setText(state.message);
		textArea.setText(state.drawMap());
		
		if (state.getState() == State.VICTORY){
			textArea.setText("");
			state.spawnOgres(Integer.parseInt(txtAs.getText()));
		}
		return;
	}

	private void initialize() throws IOException {
		frame = new JFrame("Dungeon Keep");
		frame.getContentPane().setFont(new Font("Lucida Sans", Font.PLAIN, 13));
		frame.setBounds(100, 100, 711, 547);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/*
		panel = new GamePanel();
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
		panel.requestFocusInWindow();
		*/
		
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setBounds(22, 13, 160, 19);
		lblNumberOfOgres.setFont(new Font("Lucida Sans", Font.PLAIN, 13));
		lblNumberOfOgres.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(lblNumberOfOgres);
		
		txtAs = new JTextField();
		txtAs.setFont(new Font("Lucida Sans", Font.PLAIN, 13));
		txtAs.setBounds(171, 10, 63, 22);
		txtAs.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(txtAs);
		txtAs.setColumns(2);
		
		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setHorizontalAlignment(SwingConstants.LEFT);
		lblGuardPersonality.setFont(new Font("Lucida Sans", Font.PLAIN, 13));
		lblGuardPersonality.setBounds(22, 45, 146, 19);
		frame.getContentPane().add(lblGuardPersonality);
		
		comboBox = new JComboBox<String>();
		comboBox.setToolTipText("");
		comboBox.setFont(new Font("Lucida Sans", Font.PLAIN, 13));
		comboBox.addItem("Rookie");
		comboBox.addItem("Drunk");
		comboBox.addItem("Suspicious");
		comboBox.setBounds(171, 45, 171, 22);
		frame.getContentPane().add(comboBox);
		
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 30));
		textArea.setBounds(22, 84, 400, 375);
		frame.getContentPane().add(textArea);
		
		lblNewLabel = new JLabel("You may start a new game.");
		lblNewLabel.setFont(new Font("Lucida Sans", Font.PLAIN, 13));
		lblNewLabel.setBounds(22, 472, 200, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtAs.getText().equals("") || Integer.parseInt(txtAs.getText()) > 5 || Integer.parseInt(txtAs.getText()) < 1){
					JOptionPane.showMessageDialog(frame, "Invalid number of ogres!");
					return;
				}
				
					
				
				
				DungeonMap map = new DungeonMap();
				GameState gameState = new GameState(map);
				state = gameState;
				
				
				state.spawnGuard(state.guard.getX(), state.guard.getY(), (String)comboBox.getSelectedItem());
				
				textArea.setText(state.drawMap());
				lblNewLabel.setText(null);
			}
			
		});
		btnNewGame.setFont(new Font("Lucida Sans", Font.PLAIN, 12));
		btnNewGame.setBounds(507, 98, 107, 40);
		frame.getContentPane().add(btnNewGame);
		
		JButton btnUp = new JButton("UP");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				play("w");
			}});
		btnUp.setFont(new Font("Lucida Sans", Font.PLAIN, 12));
		btnUp.setBounds(522, 209, 73, 25);
		frame.getContentPane().add(btnUp);
		
		JButton btnLeft = new JButton("LEFT");
		btnLeft.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				play("a");
		}});
		btnLeft.setFont(new Font("Lucida Sans", Font.PLAIN, 12));
		btnLeft.setBounds(448, 262, 84, 25);
		frame.getContentPane().add(btnLeft);
		
		JButton btnDown = new JButton("DOWN");
		btnDown.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				play("s");
		}});
		btnDown.setFont(new Font("Lucida Sans", Font.PLAIN, 12));
		btnDown.setBounds(522, 314, 73, 25);
		frame.getContentPane().add(btnDown);
		
		JButton btnRight = new JButton("RIGHT");
		btnRight.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				play("d");
		}});
		btnRight.setFont(new Font("Lucida Sans", Font.PLAIN, 12));
		btnRight.setBounds(586, 262, 84, 25);
		frame.getContentPane().add(btnRight);
		
		
		
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Lucida Sans", Font.PLAIN, 12));
		btnExit.setBounds(507, 419, 107, 40);
		frame.getContentPane().add(btnExit);
		
		
	}
}
