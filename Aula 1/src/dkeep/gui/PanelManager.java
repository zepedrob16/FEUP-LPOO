package dkeep.gui;

import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dkeep.logic.GameMap;

public class PanelManager {
	
	//STATE MACHINE VARIABLES
	public enum Panel { MAIN_MENU, GAME, LEVEL_EDITOR };
	public enum Event { START_GAME, EDIT_GAME, LOAD_GAME, EXIT_TO_MENU };
	private Panel state;
	
	//PANEL REFERENCES
	private PanelGame pg;
	private PanelLevelEditor ple;
	private PanelMainMenu pmm;
	private JFrame frame;
	
	public PanelManager(PanelGame pg, PanelLevelEditor ple, PanelMainMenu pmm, JFrame frame){
		state = Panel.MAIN_MENU;
		this.pg = pg;
		this.ple = ple;
		this.pmm = pmm;
		this.frame = frame;
	}
	
	public void stateMachine(Event evt){
		if (state == Panel.MAIN_MENU){
			if (evt == Event.START_GAME){
				state = Panel.GAME;
				activatePanel(pg);
			}
			else if (evt == Event.EDIT_GAME){
				state = Panel.LEVEL_EDITOR;
				setResolution(900, 740);
				activatePanel(ple);
			}
			else if (evt == Event.LOAD_GAME){
				state = Panel.GAME;
			}
		}
		else if (state == Panel.GAME){
			if (evt == Event.EXIT_TO_MENU){
				state = Panel.MAIN_MENU;
				activatePanel(pmm);
			}
		}
	}
	
	public void setResolution(int x, int y){
		frame.setSize(x, y);
	}
	
	public void setOgreMap(GameMap gm){
		pg.setOgreMap(gm);
	}
	
	public void closeAllPanels(){
		pg.setVisible(false);
		ple.setVisible(false);
		pmm.setVisible(false);
	}
	
	public void activatePanel(JPanel jp){
		closeAllPanels();
		jp.setVisible(true);
	}
	
	public Panel getState(){
		return state;
	}
	
}
