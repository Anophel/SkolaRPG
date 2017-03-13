package view.dialogue;

import javafx.scene.image.Image;
import model.characters.Characters;

public class Speech {

	// Obrázek postavy, která mluví, její text a jestli je nalevo nebo napravo
	private Image avatar;
	private String text;
	private boolean fromLeft;
	
	/**
	 * Vytvoøí promluvu charakteru "charac", který øíká "text".
	 * fromLeft = true -> avatar je nalevo
	 * fromLeft = false -> avatar je napravo
	 * 
	 * @param charac
	 * @param text
	 * @param formLeft
	 */
	public Speech(Characters charac, String text, boolean fromLeft){
		this.avatar = charac.getImage();
		this.text = text;
		this.fromLeft = fromLeft;
	}

	public Image getAvatar() {
		return avatar;
	}

	public String getText() {
		return text;
	}

	public boolean isFromLeft() {
		return fromLeft;
	}
	
}
