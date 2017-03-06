package view.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import app.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class LoadGameController implements Initializable {

	@FXML
	private Button saveGame1;

	@FXML
	private Button saveGame2;

	@FXML
	private Button saveGame3;

	@FXML
	private Button smazat;

	@FXML
	private Button nacist;

	@FXML
	private Button zpet;
	
	private Main main;

	public void handleSaveGame(ActionEvent event) {

	}

	public void handleSmazat(ActionEvent event) {

	}

	public void handleNacist(ActionEvent event) {

	}

	public void handleZpet(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainMenu.fxml"));
		Parent koren = loader.load();
		Scene scena = new Scene(koren);
		main.getStage().setScene(scena);
		main.getStage().show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
}
