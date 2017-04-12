package app;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Pozice;
import model.characters.Characters;
import model.characters.Hrac;
import model.characters.NPC;
import model.characters.Postava;
import view.animation.Avatar;
import view.animation.Enemy;
import view.animation.ImageWithSource;
import view.animation.Map;
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
		avatar.setImageSize(250, 250);
		hrac.setAnimatedCharacter(avatar);
		
		ArrayList<Postava> npc = new ArrayList<>();
		NPC blonde = new NPC("Blonde");
		Enemy blondeAnim = new Enemy(Characters.BLONDE);
		blondeAnim.setPozice(new Pozice(2500, 800));
		blonde.setAnimatedCharacter(blondeAnim);
		npc.add(blonde);
		
		Map map = new Map(new ImageWithSource("/view/img/chodba.png"), new ImageWithSource("/view/img/pozadi.png"), true);
		map.setPozice(new Pozice(0, -500));
		
		MGC.setHrac(hrac);
		MGC.setMap(map);
		MGC.setEntities(npc);
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