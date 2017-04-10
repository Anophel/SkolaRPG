package view.controllers;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class LoadGameController {

	@FXML
	private ToggleButton saveGame1;

	@FXML
	private ToggleButton saveGame2;

	@FXML
	private ToggleButton saveGame3;

	@FXML
	private Button smazat;

	@FXML
	private Button nacist;

	@FXML
	private Button ulozit;

	@FXML
	private Button zpet;

	public int idSaveGame;

	public int getIdSaveGame() {
		return idSaveGame;
	}

	public void setIdSaveGame(int idSaveGame) {
		this.idSaveGame = idSaveGame;
	}

	public void handleSaveGame1(ActionEvent event) {
		SetText setText = new SetText();
		setText.setText(saveGame1);
		saveGame1.setDisable(true);
		saveGame2.setDisable(false);
		saveGame3.setDisable(false);
		setIdSaveGame(1);
		saveGame2.setSelected(false);
		saveGame3.setSelected(false);
	}

	public void handleSaveGame2(ActionEvent event) {
		SetText setText = new SetText();
		setText.setText(saveGame2);
		saveGame1.setDisable(false);
		saveGame2.setDisable(true);
		saveGame3.setDisable(false);
		setIdSaveGame(2);
		saveGame1.setSelected(false);
		saveGame3.setSelected(false);
	}

	public void handleSaveGame3(ActionEvent event) {
		SetText setText = new SetText();
		setText.setText(saveGame3);
		saveGame1.setDisable(false);
		saveGame2.setDisable(false);
		saveGame3.setDisable(true);
		setIdSaveGame(3);
		saveGame1.setSelected(false);
		saveGame2.setSelected(false);

	}

	public void handleSmazat(ActionEvent event) {
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("H:m:s d | M | y G");
		try {

			File file = new File(ft.format(date));

			if (file.delete()) {
				System.out.println("Ulo�en� hra " + idSaveGame + " byla smaz�na.");
			} else {
				System.out.println(
						"Nepoda�ilo se smazat ulo�enou hru. N�co se asi pokazilo. Kontaktujte technickou podporu, nebo se modlete k Bohu. Oboje bude m�t podobn� v�sledek.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void handleUlozit(ActionEvent event) {

		Ukladani ukladani = new Ukladani();
		ukladani.ulozitHru();

	}

	public void handleNacist(ActionEvent event) {

		Ukladani ukladani = new Ukladani();
		ukladani.nacistHru();

	}

	public void handleZpet(ActionEvent event) throws Exception {
		Stage podium = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainMenu.fxml"));
		Parent koren = loader.load();

		loader.getController();

		Scene scena = new Scene(koren);
		podium.setScene(scena);
		podium.show();
		podium.setTitle("�kola RPG");

		((Node) (event.getSource())).getScene().getWindow().hide();
	}

}
