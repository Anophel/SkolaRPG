package view.dialogue;

import app.Main;

public enum Dialogues {

	test(Main.class.getResource("/view/dialogue/data/testDialogue.xml").getFile());
	
	private String path;
	
	private Dialogues(String path){
		this.path = path;
	}
	
	public String getPath(){
		return path;
	}
}
