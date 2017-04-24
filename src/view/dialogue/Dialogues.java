package view.dialogue;

import app.Main;
import model.characters.Characters;

public enum Dialogues {

	test(Main.class.getResource("/view/dialogue/data/testDialogue.xml").getFile(), Characters.BLONDE);
	
	private String path;
	private Characters character;
	
	private Dialogues(String path, Characters character){
		this.path = path;
		this.character = character;
	}
	
	public String getPath(){
		return path;
	}
	
	public Characters getCharacter() {
		return character;
	}

	public static Dialogues getDialogOfCharacter(Characters character){
		Dialogues[] dials = Dialogues.values();
		for(Dialogues dial : dials){
			if(dial.getCharacter() == character){
				return dial;
			}
		}
		return  null;
	}
}
