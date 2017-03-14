package dkeep.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Graphic extends JPanel {
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		g.fillRect(2, 3, 10, 20);
	}
}
