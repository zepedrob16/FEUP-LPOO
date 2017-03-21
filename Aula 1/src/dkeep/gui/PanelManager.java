package dkeep.gui;

import javax.swing.JPanel;

public class PanelManager {
	
	//STATE MACHINE VARIABLES
	public enum Panel { MAIN_MENU, GAME, LEVEL_EDITOR };
	public enum Event { START_GAME, EDIT_GAME, LOAD_GAME, EXIT_TO_MENU };
	private Panel state;
	
	//PANEL REFERENCES
	private PanelGame pg;
	private PanelLevelEditor ple;
	private PanelMainMenu pmm;
	
	public PanelManager(PanelGame pg, PanelLevelEditor ple, PanelMainMenu pmm){
		state = Panel.MAIN_MENU;
		this.pg = pg;
		this.ple = ple;
		this.pmm = pmm;
	}
	
	public void stateMachine(Event evt){
		if (state == Panel.MAIN_MENU){
			if (evt == Event.START_GAME){
				state = Panel.GAME;
				activatePanel(pg);
			}
			else if (evt == Event.EDIT_GAME){
				state = Panel.LEVEL_EDITOR;
				activatePanel(ple);
			}
			else if (evt == Event.LOAD_GAME){
				state = Panel.GAME;
				//TODO: idk.
			}
		}
		else if (state == Panel.GAME){
			if (evt == Event.EXIT_TO_MENU){
				state = Panel.MAIN_MENU;
				activatePanel(pmm);
			}
		}
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
