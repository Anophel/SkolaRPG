package view.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.scene.control.ToggleButton;

public class SetText {

	public LoadGameController controller = new LoadGameController();

	public void setText(ToggleButton button) {
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("H:m:s d | M | y G");
		button.setText(ft.format(date));
	}

}
