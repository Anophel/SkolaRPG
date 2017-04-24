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
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Pozice;
import model.Vektor;
import model.characters.Characters;
import model.characters.Hrac;
import model.characters.Postava;
import model.items.INositelne;
import view.animation.Avatar;
import view.animation.Level;
import view.animation.Map;
import view.animation.levels.Part;
import view.dialogue.Dialogue;
import view.dialogue.Dialogues;

public class MainGameControler implements Initializable 
{
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
	private Stage stage; // jevi�t�
	private ArrayList<String> input = new ArrayList<String>(); // udr�uje informaci o stisknut�ch kl�ves�ch
	private long last; // udr�uje informaci o tom, kdy prob�hl posledn� update
	private long now; // moment�ln� �as
	private Canvas canvas = new Canvas(); // slou�� jako pl�tno pro vykreslov�n�
	private Scene scene;
	private Part initialPart = Part.LEFT;

	
	private Level level;
	/*
	 * READ ME!!!
	 * 
	 * 
	 * Ne� m� za�ne� pou��vat, nezapome� na: 
	 * setHrac(Hrac) - hlavn� postava
	 * setLevel(Level) - informace o map�ch a nep��tel�ch
	 * setStage(Stage) - okno, do kter�ho kresl�m
	 */

	public void run() 
	{
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

		new AnimationTimer() 
		{
			Map map = level.getMap(initialPart);	//mapa, po kter� se pohybujeme
			ArrayList<Postava> entities = level.getEnemies(initialPart);	//nep��tel�
			Vektor vektor = new Vektor(0, 0); // popisuje sm�r pohybu mapy
			
			@Override
			public void handle(long present) 
			{
				now = System.nanoTime();

				canvas.setWidth(pane.getWidth());
				canvas.setHeight(pane.getHeight());

				double ubehlyCas = (now - last) / 10e6;
				last = now;

				setVelocity(); // nastaven� rychlosti (p�echodn� map�)

				setAnimation(ubehlyCas); // nastaven� animace

				setAvatarVelocity(ubehlyCas); // nastav� rychlost avatara
				avatar.update(ubehlyCas, canvas); // aplikace zm�n avatara

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
				
				changeMap();
			}

			private void setVelocity() // nastavuje rychlost podle stisknut�ch kl�ves
			{
				vektor.set(new Vektor(0, 0));
				if (input.contains("A")) 
				{
					vektor.addX(8);
				}
				if (input.contains("D")) 
				{
					vektor.addX(-8);
				}
				if (input.contains("W")) 
				{
					vektor.addY(8);
				}
				if (input.contains("S")) 
				{
					vektor.addY(-8);
				}
			}

			private void setAnimation(double ubehlyCas) // nastav� animaci avatara
			{
				if (vektor.getX() != 0) // pokud se h�be po X
				{
					if (vektor.getX() > 0) // pokud se h�be sm�rem vlevo
					{
						avatar.left(ubehlyCas);
					}
					else // h�be se vpravo
					{
						avatar.right(ubehlyCas);
					}
				} 
				else if (vektor.getY() != 0) // pokud se h�be po Y
				{
					if (vektor.getY() > 0) // pohybuje se nahoru
					{
						avatar.up(ubehlyCas);
					} 
					else // pohybuje se dolu
					{
						avatar.down(ubehlyCas);
					}
				}
				else // neh�be se v�bec
				{
					avatar.relax(ubehlyCas);
				}
			}

			private void setAvatarVelocity(double time) // nastav� rychlost avataru
			{
				avatar.setVelocity(0, 0);

				if (map.getXborderHit()) 
				{
					avatar.addVelocity(-vektor.getX(), 0);
				}
				if (map.getYborderHit()) 
				{
					avatar.addVelocity(0, -vektor.getY());
				}
				if (map.intersectsWithBackground(avatar)) // pokud se protne pozad� s avatarem
				{
					if (vektor.getY() > 0) // pokud se pohybuje mapa sm�rem dol� = avatar nahoru
					{
						avatar.setVelocity(avatar.getXvelocity(), vektor.getY());
					}
				}

				if (avatar.outOfCenterX(canvas) && !map.getXborderHit()) 
				{
					avatar.setVelocity(-vektor.getX(), avatar.getYvelocity());
				}
				if (avatar.outOfCenterY(canvas) && vektor.getY() > 0 && !map.intersectsWithBackground(avatar)
						&& wontIntersect(time)) 
				{
					avatar.setVelocity(avatar.getXvelocity(), -vektor.getY());
				}
			}

			private void setMapVelocity() // nastav� rychlost map�
			{
				map.setVelocity(vektor.getX(), vektor.getY());
				if (avatar.outOfCenterX(canvas)) 
				{
					map.setVelocity(0, vektor.getY());
				}
				if (avatar.outOfCenterY(canvas)) 
				{
					if (vektor.getY() > 0) 
					{
						map.setVelocity(map.getXvelocity(), 0);
					}
					else if (vektor.getY() < 0 && !map.getXborderHit())
					{
						map.setVelocity(map.getXvelocity(), vektor.getY());
					}
				}
			}

			private void updateOthers(double time) 
			{
				for (Postava postava : entities) 
				{
					postava.getAnimatedCharacter().setVelocity(0, 0);
					postava.getAnimatedCharacter().getPozice().plus(map.getLastChange());
				}
			}

			private void renderOthers(GraphicsContext gc)
			{
				// ut��dit podle Y
				entities.sort(new Comparator<Postava>() {
					@Override
					public int compare(Postava p0, Postava p1) {
						return (int) (p0.getAnimatedCharacter().getPozice().getYPoz()
								- p1.getAnimatedCharacter().getPozice().getYPoz());
					}
				});
				// vykreslit od men��ho k v�t��mu Y
				for (Postava postava : entities) 
				{
					postava.getAnimatedCharacter().render(gc);
				}
			}
			
			private void intersectionsAndActions() 
			{
				boolean intersected = false;
				Postava intersectionist = null;
				@SuppressWarnings("unchecked")
				ArrayList<Postava> temp = (ArrayList<Postava>) entities.clone();
				for (Postava postava : temp) 
				{
					if (avatar.intersectsWith(postava.getAnimatedCharacter())) 
					{
						intersected = true;
						try 
						{
							intersectionist = postava;
							startDialog(intersectionist);
							
							stop();
						} 
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
				}
				if (intersected) 
				{
					entities.remove(intersectionist);
				}
			}

			private boolean wontIntersect(double time) 
			{
				Avatar probe = new Avatar(Characters.HERO);
				probe.setPozice(avatar.getPozice());
				probe.setVelocity(avatar.getXvelocity(), -vektor.getY());
				probe.update(time, canvas);

				if (map.intersectsWithBackground(probe))
				{
					return false;
				} 
				else 
				{
					return true;
				}
			}
		
			private void changeMap()
			{
				/*
				 * Pokud mapa dorazila na konec a nach�z�m se na lev� ��sti levelu a avatar do�el a� do prava,
				 * zm�n�m mapu na pravou ��st.
				 */
				if(level.getPartBeingUsed() == Part.LEFT && avatar.getReachedRight() && hrac.getBatoh().includes(level.getLeftMapReleaseKey()))
				{
					double oldX = level.getRightMap().getPozice().getXPoz();
					double oldY = level.getRightMap().getPozice().getYPoz();
					
					level.getRightMap().setPozice(new Pozice(0, map.getPozice().getYPoz()));
					map = level.getRightMap();
					level.setPartBeingUsed(Part.RIGHT);
					avatar.setPozice(new Pozice(50, avatar.getPozice().getYPoz()));
					entities = level.getRightMapEnemies();
					
					double Xdiference = map.getPozice().getXPoz() - oldX;
					double Ydiference = map.getPozice().getYPoz() - oldY;
					
					for(Postava postava:entities)
					{
						postava.getAnimatedCharacter().setPozice(new Pozice(postava.getAnimatedCharacter().getPozice().getXPoz()+Xdiference,postava.getAnimatedCharacter().getPozice().getYPoz()+Ydiference));
					}
				}
				else if (level.getPartBeingUsed() == Part.RIGHT && avatar.getReachedLeft())
				{
					double oldX = level.getLeftMap().getPozice().getXPoz();
					double oldY = level.getLeftMap().getPozice().getYPoz();
					
					level.getLeftMap().setPozice(new Pozice(-level.getLeftMap().getImage().getWidth(), map.getPozice().getYPoz()));
					map = level.getLeftMap();
					level.setPartBeingUsed(Part.LEFT);
					avatar.setPozice(new Pozice(canvas.getWidth()-(avatar.getImage().getWidth()+50),avatar.getPozice().getYPoz()));
					entities = level.getLeftMapEnemies();
					
					double Xdiference = map.getPozice().getXPoz() - oldX;
					double Ydiference = map.getPozice().getYPoz() - oldY;
					
					for(Postava postava:entities)
					{
						postava.getAnimatedCharacter().setPozice(new Pozice(postava.getAnimatedCharacter().getPozice().getXPoz()+Xdiference,postava.getAnimatedCharacter().getPozice().getYPoz()+Ydiference));
					}
				}
			}
		}.start();
	}

	public void setHrac(Hrac hrac) {
		this.hrac = hrac;
	}
	
	public void setInitialPart(Part part)
	{
		initialPart = part;
	}
	public void setLevel(Level lvl)
	{
		level = lvl;
	}
	public Level getLevel()
	{
		return level;
	}
	public void setStage(Stage stage) 
	{
		this.stage = stage;
	}

	public Stage getStage() 
	{
		return stage;
	}

	public void OpenInventory(ActionEvent event) throws Exception // handler pro tla��tko Inventory
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
			hrac.setVybranyItem(item);
		} catch (NullPointerException e) {
			System.out.println("U�ivatel nic nevybral!");
		}
	}
	
	private void startDialog(Postava enemy) throws Exception{
		Dialogues dials = Dialogues.getDialogOfCharacter(enemy.getAnimatedCharacter().getCharacter());
		int width = (int) (pane.getWidth() * 0.85);
		int height = (int) (pane.getHeight() * 0.5);
		if(dials != null){
			Dialogue dial = new Dialogue(dials, width, height, this, enemy);
			
			AnchorPane backgroundWrap = new AnchorPane();
			backgroundWrap.setPrefSize(width, height);
			backgroundWrap.getChildren().add(dial);
			backgroundWrap.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
			
			backgroundWrap.disableProperty().addListener((ov, oldVal, newVal)->{
				if(newVal.booleanValue() == true){
					pane.getChildren().remove(backgroundWrap);
				}
			});
			
			AnchorPane.setLeftAnchor(backgroundWrap, (pane.getWidth() * 0.07));
			AnchorPane.setTopAnchor(backgroundWrap, (pane.getWidth() * 0.3));
			pane.getChildren().add(backgroundWrap);
			dial.start();
		} else {
			startFight(hrac, enemy);
		}
	}

	public void handleLoadGame(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/controllers/LoadGame.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);

		Stage stage = new Stage();
		stage.setScene(scene);
		stage.showAndWait();

	}

	public void startFight(Postava me, Postava enemy) throws Exception 
	{
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

	public Hrac getHrac() {
		return hrac;
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