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
import view.animation.Map;
import view.controllers.MainGameControler;

public class Main extends Application
{	
	private Stage jeviste;
	
	public static void main(String[] args)
	{
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
		
		//tvorba hráèe a avatara
		Hrac hrac = new Hrac();
		Avatar avatar = new Avatar(Characters.HERO);
		avatar.setPozice(new Pozice(600,300));
		hrac.setAnimatedCharacter(avatar);
		
		//tvorba mapy
		Map map = new Map();
		
		//tvorba nepøátel
		ArrayList<Postava> enemies = new ArrayList<Postava>();
		//tvorba nepøátel - 1 
		NPC npc = new NPC("Redman");
		Enemy enemy = new Enemy(Characters.REDMAN);
		enemy.setPozice(new Pozice(1000,400));
		enemy.setImageSize(300, 300);
		npc.setAnimatedCharacter(enemy);
		//tvorba nepøátel - 2
		NPC npc2 = new NPC("Blonde");
		Enemy enemy2 = new Enemy(Characters.BLONDE);
		enemy2.setPozice(new Pozice(1500, 550));
		enemy2.setImageSize(300, 300);
		npc2.setAnimatedCharacter(enemy2);
		
		enemies.add(npc);
		enemies.add(npc2);
		
		MGC.setHrac(hrac);
		MGC.setMap(map);
		MGC.setEntities(enemies);
		MGC.setStage(jeviste);
		jeviste.setMaximized(true);
		
		jeviste.show();
		MGC.run();
	}
	private void setJeviste(Stage stage)
	{
		this.jeviste = stage;
	}
	public Stage getStage()
	{
		return jeviste;
	}
}