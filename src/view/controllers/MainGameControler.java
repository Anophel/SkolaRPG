package view.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Vektor;
import model.characters.Characters;
import model.characters.Hrac;
import model.characters.Postava;
import model.items.INositelne;
import view.animation.Avatar;
import view.animation.Map;

public class MainGameControler implements Initializable {
	@FXML
	private MenuButton Menu;

	@FXML
	private MenuItem LoadGame;

	@FXML
	private MenuItem Options;

	@FXML
	private MenuItem MainMenu;

	@FXML
	private Button Inventory;

	@FXML
	private AnchorPane pane;

	private Hrac hrac; // hlavn� postava
	private ArrayList<Postava> entities; // nep��tel�
	private Stage stage; // jevi�t�
	private Map map; // pozad�, po kter�m se pohybujeme
	private ArrayList<String> input = new ArrayList<String>(); // udr�uje
																// informaci o
																// stisknut�ch
																// kl�ves�ch
	private long last; // udr�uje informaci o tom, kdy prob�hl posledn� update
	private long now; // moment�ln� �as
	private Canvas canvas = new Canvas(); // slou�� jako pl�tno pro vykreslov�n�
	private Vektor vektor = new Vektor(0, 0); // popisuje sm�r pohybu mapy

	private Scene scene;

	/*
	 * READ ME!!!
	 * 
	 * 
	 * Ne� m� za�ne� pou��vat, nezapome� na: setHrac(Hrac) - hlavn� postava
	 * setEntities(ArrayList<Postava>) - nep��tel� setMap(Map) - hrac� plocha
	 * setStage(Stage) - okno, do kter�ho kresl�m
	 */

	public void run() {
		pane.applyCss();
		pane.layout();

		pane.getChildren().clear();
		pane.getChildren().add(canvas);

		last = System.nanoTime();
		now = System.nanoTime();

		pane.getScene().setOnKeyPressed(e -> keyPressed(e));
		pane.getScene().setOnKeyReleased(e -> keyReleased(e));

		GraphicsContext gt = canvas.getGraphicsContext2D();

		Avatar avatar = (Avatar) hrac.getAnimatedCharacter();

		canvas.setWidth(pane.getWidth());
		canvas.setHeight(pane.getHeight());

		new AnimationTimer() {
			@Override
			public void handle(long present) {
				now = System.nanoTime();

				canvas.setWidth(pane.getWidth());
				canvas.setHeight(pane.getHeight());

				double ubehlyCas = (now - last) / 10e6;
				last = now;

				setVelocity(); // nastaven� rychlosti (p�echodn� map�)

				setAnimation(ubehlyCas); // nastaven� animace

				setAvatarVelocity(ubehlyCas); // nastav� rychlost avatara

				// aplikace zm�n avatara
				if (avatar.getXvelocity() != 0 || avatar.getYvelocity() != 0) {
					avatar.update(ubehlyCas, canvas);
				} else {
					avatar.update(canvas);
				}

				setMapVelocity(); // p��padn� �prava rychlosti mapy
				map.update(ubehlyCas, canvas); // aplikace zm�n mapy

				updateOthers(ubehlyCas);

				// render
				gt.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				map.render(gt);
				avatar.render(gt);
				renderOthers(gt);

				// intersects - start action
				intersectionsAndActions();
			}

			private void setVelocity() // nastavuje rychlost podle stisknut�ch
										// kl�ves
			{
				vektor.set(new Vektor(0, 0));
				if (input.contains("A")) {
					vektor.addX(8);
				}
				if (input.contains("D")) {
					vektor.addX(-8);
				}
				if (input.contains("W")) {
					vektor.addY(8);
				}
				if (input.contains("S")) {
					vektor.addY(-8);
				}
			}

			private void setAnimation(double ubehlyCas) // nastav� animaci
														// avatara
			{
				if (vektor.getX() != 0) // pokud se h�be po X
				{
					if (vektor.getX() > 0) // pokud se h�be sm�rem vlevo
					{
						avatar.left(ubehlyCas);
					} else // h�be se vpravo
					{
						avatar.right(ubehlyCas);
					}
				} else if (vektor.getY() != 0) // pokud se h�be po Y
				{
					if (vektor.getY() > 0) // pohybuje se nahoru
					{
						avatar.up(ubehlyCas);
					} else // pohybuje se dolu
					{
						avatar.down(ubehlyCas);
					}
				} else // neh�be se v�bec
				{
					avatar.relax(ubehlyCas);
				}
			}

			private void setAvatarVelocity(double time) // nastav� rychlost
														// avataru
			{
				avatar.setVelocity(0, 0);

				if (map.getXborderHit()) {
					avatar.addVelocity(-vektor.getX(), 0);
				}
				if (map.getYborderHit()) {
					avatar.addVelocity(0, -vektor.getY());
				}
				if (map.intersectsWithBackground(avatar)) // pokud se protne
															// pozad� s avatarem
				{
					if (vektor.getY() > 0) // pokud se pohybuje mapa sm�rem dol�
											// = avatar nahoru
					{
						avatar.setVelocity(avatar.getXvelocity(), vektor.getY());
					}
				}

				if (avatar.outOfCenterX(canvas) && !map.getXborderHit()) {
					avatar.setVelocity(-vektor.getX(), avatar.getYvelocity());
				}
				if (avatar.outOfCenterY(canvas) && vektor.getY() > 0 && !map.intersectsWithBackground(avatar)
						&& wontIntersect(time)) {
					avatar.setVelocity(avatar.getXvelocity(), -vektor.getY());
				}
			}

			private void setMapVelocity() // nastav� rychlost map�
			{
				map.setVelocity(vektor.getX(), vektor.getY());
				if (avatar.outOfCenterX(canvas)) {
					map.setVelocity(0, vektor.getY());
				}
				if (avatar.outOfCenterY(canvas)) {
					if (vektor.getY() > 0) {
						map.setVelocity(map.getXvelocity(), 0);
					} else if (vektor.getY() < 0 && !map.getXborderHit()) {
						map.setVelocity(map.getXvelocity(), vektor.getY());
					}
				}
			}

			private void updateOthers(double time) {
				for (Postava postava : entities) {
					postava.getAnimatedCharacter().setVelocity(0, 0);
					postava.getAnimatedCharacter().getPozice().plus(map.getLastChange());
				}
			}

			private void renderOthers(GraphicsContext gc) {
				// ut��dit podle Y
				entities.sort(new Comparator<Postava>() {
					@Override
					public int compare(Postava p0, Postava p1) {
						return (int) (p0.getAnimatedCharacter().getPozice().getYPoz()
								- p1.getAnimatedCharacter().getPozice().getYPoz());
					}
				});
				// vykreslit od men��ho k v�t��mu Y
				for (Postava postava : entities) {
					postava.getAnimatedCharacter().render(gc);
				}
			}

			private void intersectionsAndActions() {
				boolean intersected = false;
				Postava intersectionist = null;
				@SuppressWarnings("unchecked")
				ArrayList<Postava> temp = (ArrayList<Postava>) entities.clone();
				for (Postava postava : temp) {
					if (avatar.intersectsWith(postava.getAnimatedCharacter())) {
						intersected = true;
						try {
							startFight(hrac, postava);
							intersectionist = postava;
							stop();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				if (intersected) {
					entities.remove(intersectionist);
				}
			}

			private boolean wontIntersect(double time) {
				Avatar probe = new Avatar(Characters.HERO);
				probe.setPozice(avatar.getPozice());
				probe.setVelocity(avatar.getXvelocity(), -vektor.getY());
				probe.update(time, canvas);

				if (map.intersectsWithBackground(probe)) {
					return false;
				} else {
					return true;
				}
			}
		}.start();
	}

	public void setHrac(Hrac hrac) {
		this.hrac = hrac;
	}

	public void setEntities(ArrayList<Postava> entities) {
		this.entities = entities;
	}

	public ArrayList<Postava> getEntities() {
		return entities;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Map getMap() {
		return map;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Stage getStage() {
		return stage;
	}

	public void OpenInventory(ActionEvent event) throws Exception // handler pro
																	// tla��tko
																	// Inventory
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/controllers/InventarView.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);

		// nastavov�n� controlleru
		InventarController inventar = loader.getController();
		inventar.setPostava(hrac);
		inventar.inicializujInventar();

		// zobrazen� invent��e
		Stage stage = new Stage(); // POZOR! showAndWait() nesm� b�t pou�ito na
									// primaryStage
		stage.setScene(scene);
		stage.showAndWait(); // WAIT! jinak se pokro�� rovnou k dal��mu kroku

		// z�sk�n� vybran�ho itemu
		INositelne item = inventar.getVybranyItem();
		try {
			System.out.println(item.toString());
		} catch (NullPointerException e) {
			System.out.println("U�ivatel nic nevybral!");
		}
	}

	public void handleLoadGame(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/controllers/LoadGame.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);

		LoadGameController loadGame = loader.getController();

		Stage stage = new Stage();
		stage.setScene(scene);
		stage.showAndWait();

	}

	private void startFight(Postava me, Postava enemy) throws Exception {
		input.clear();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/controllers/Arena.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);

		double height = stage.getHeight();
		double width = stage.getWidth();
		stage.setScene(scene);
		stage.setHeight(height);
		stage.setWidth(width);

		ArenaController AC = loader.getController();
		AC.setTvojePostava(me);
		AC.setNepritelovaPostava(enemy);
		AC.setMainGameControler(this);
		AC.setStage(stage);
		AC.createArena();
	}

	public void setScenePane(Scene scene) {
		this.scene = scene;
	}

	public Scene getScene() {
		return scene;
	}

	private void keyPressed(KeyEvent e) {
		String code = e.getCode().toString();
		if (!input.contains(code)) {
			input.add(code);
		}
	}

	private void keyReleased(KeyEvent e) {
		String code = e.getCode().toString();
		input.remove(code);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}