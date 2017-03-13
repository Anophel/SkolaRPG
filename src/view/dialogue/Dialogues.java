package view.dialogue;

public enum Dialogues {

	first("test/path/dialogue1.di");
	
	private String path;
	
	private Dialogues(String path){
		this.path = path;
	}
	
	public String getPath(){
		return path;
	}
}
