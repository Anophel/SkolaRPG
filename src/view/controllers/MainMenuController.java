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

public class MainMenuController implements Initializable {

	@FXML
	private Button novaHra;
	@FXML
	private Button nacistHru;
	@FXML
	private Button nastaveni;
	@FXML
	private Button konec;
	
	private Main main;

	public void handleNovaHra(ActionEvent event) throws Exception
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Maingame.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		main.getStage().setScene(scene);
	}

	public void handleNacistHru(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoadGame.fxml"));
		Parent koren = loader.load();
		Scene scena = new Scene(koren);
		main.getStage().setScene(scena);
	}

	public void handleNastaveni(ActionEvent event) {

	}

	public void handleKonec(ActionEvent event) {
		System.exit(0);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
