package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
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
		lblNumberOfOgres.setFont(new Font("Inconsolata", Font.PLAIN, 16));
		lblNumberOfOgres.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(lblNumberOfOgres);
		
		txtAs = new JTextField();
		txtAs.setBounds(194, 11, 63, 22);
		txtAs.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(txtAs);
		txtAs.setColumns(2);
		
		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setHorizontalAlignment(SwingConstants.LEFT);
		lblGuardPersonality.setFont(new Font("Inconsolata", Font.PLAIN, 16));
		lblGuardPersonality.setBounds(22, 45, 146, 19);
		frame.getContentPane().add(lblGuardPersonality);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("");
		comboBox.setBounds(194, 44, 171, 22);
		frame.getContentPane().add(comboBox);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnNewGame.setFont(new Font("Inconsolata", Font.PLAIN, 15));
		btnNewGame.setBounds(507, 98, 107, 40);
		frame.getContentPane().add(btnNewGame);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(22, 99, 400, 400);
		frame.getContentPane().add(textArea);
		
		JButton btnRight = new JButton("RIGHT");
		btnRight.setFont(new Font("Inconsolata", Font.PLAIN, 15));
		btnRight.setBounds(586, 262, 84, 25);
		frame.getContentPane().add(btnRight);
		
		JButton btnDown = new JButton("DOWN");
		btnDown.setFont(new Font("Inconsolata", Font.PLAIN, 15));
		btnDown.setBounds(522, 314, 73, 25);
		frame.getContentPane().add(btnDown);
		
		JButton btnUp = new JButton("UP");
		btnUp.setFont(new Font("Inconsolata", Font.PLAIN, 15));
		btnUp.setBounds(522, 209, 73, 25);
		frame.getContentPane().add(btnUp);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Inconsolata", Font.PLAIN, 15));
		btnExit.setBounds(507, 459, 107, 40);
		frame.getContentPane().add(btnExit);
		
		JButton btnLeft = new JButton("LEFT");
		btnLeft.setFont(new Font("Inconsolata", Font.PLAIN, 15));
		btnLeft.setBounds(448, 262, 84, 25);
		frame.getContentPane().add(btnLeft);
	}
}
