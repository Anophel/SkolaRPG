package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Pozice;
import model.characters.Characters;
import model.characters.Hrac;
import view.animation.Avatar;
import view.animation.Level;
import view.animation.levels.Levels;
import view.controllers.MainGameControler;

public class Main extends Application {
	private Stage jeviste;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		Stage jeviste = primaryStage;
		setJeviste(jeviste);

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/controllers/Maingame.fxml"));
		MainGameControler MGC = new MainGameControler();
		loader.setController(MGC);
		Parent root = loader.load();
		Scene scene = new Scene(root);
		jeviste.setScene(scene);

		MGC.setScenePane(scene);
		
		Hrac hrac = new Hrac();
		Avatar avatar = new Avatar(Characters.HERO);
		avatar.setPozice(new Pozice(500, 800));
		avatar.setImageSize(400, 400);
		hrac.setAnimatedCharacter(avatar);
		
		
		MGC.setHrac(hrac);
		MGC.setLevel(new Level(Levels.LEVEL_1));
		MGC.setStage(jeviste);
		jeviste.setMaximized(true);

		jeviste.show();
		MGC.run();
	}
	
	private void setJeviste(Stage stage) {
		this.jeviste = stage;
	}

	public Stage getStage() {
		return jeviste;
	}
}