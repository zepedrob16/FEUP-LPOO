package dkeep.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dkeep.gui.PanelManager.Event;

import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class SettingsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private PanelManager pm;

	public static void main(String[] args) {
		try {
			SettingsDialog dialog = new SettingsDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SettingsDialog() throws IOException {
		
		this.setTitle("Create New Game");
		setBounds(100, 100, 335, 308);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JSlider slider = new JSlider();
		initSlider(slider);
		
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		initLabel(lblNumberOfOgres, 12, 100);
		
		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		initLabel(lblGuardPersonality, 12, 13);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		initComboBox(comboBox, new String[]{"Rookie", "Drunk", "Suspicious"});
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pm.stateMachine(Event.START_GAME);
				pm.setGuardPers((String)comboBox.getSelectedItem());
				pm.setOgreNum(slider.getValue());
				pm.start();
				dispose();
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		
	}
	public void initSlider(JSlider slider){
		slider.setBounds(12, 129, 200, 44);
		slider.setMinimum(1);
		slider.setMaximum(5);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		contentPanel.add(slider);
	}
	
	public void initLabel(JLabel label, int x, int y){
		label.setBounds(x, y, 112, 16);
		contentPanel.add(label);
	}
	
	public void initComboBox(JComboBox<String> cb, String[] options){
		cb.setBounds(22, 37, 149, 22);
		for (int i = 0; i < options.length; i++){
			cb.addItem(options[i]);
		}
		contentPanel.add(cb);
	}
	
	public void setPanelManager(PanelManager pm){
		this.pm = pm;
	}
}
