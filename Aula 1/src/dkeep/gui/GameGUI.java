package dkeep.gui;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class GameGUI {

	private JFrame frame;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameGUI window = new GameGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public GameGUI() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame("Dungeon Keep");
		frame.setBounds(100, 100, 900, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new GamePanel();
		panel.setBounds(12, 13, 550, 550);
		frame.getContentPane().add(panel);
		//frame.pack();
		frame.setVisible(true);
		panel.requestFocusInWindow();
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(12, 576, 56, 16);
		frame.getContentPane().add(lblNewLabel);
	}
}
