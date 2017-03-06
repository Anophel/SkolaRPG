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
import model.characters.Hrac;
import model.characters.Postava;
import model.items.INositelne;
import view.animation.Avatar;
import view.animation.Map;

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
	

	
	private Hrac hrac;			//hlavní postava
	private ArrayList<Postava> entities;	//nepøátelé
	private Stage stage;		//jevištì
	private Map map;			//pozadí, po kterém se pohybujeme
	private ArrayList<String> input = new ArrayList<String>();	//udržuje informaci o stisknutých klávesách
	private long last;	//udržuje informaci o tom, kdy probìhl poslední update
	private Canvas canvas = new Canvas();	//slouží jako plátno pro vykreslování
	
	private Scene scene;

	/*
	 * 					READ ME!!!
	 * 
	 * 
	 * 	Než mì zaèneš používat, nezapomeò na:
	 * 		setHrac(Hrac)					 	- hlavní postava
	 * 		setEntities(ArrayList<Postava>) 	- nepøátelé
	 * 		setMap(Map)							- hrací plocha
	 * 		setStage(Stage)						- okno, do kterého kreslím
	 */
	
	
	
	public void run()
	{
		pane.applyCss();
		pane.layout();
		
		pane.getChildren().clear();
		pane.getChildren().add(canvas);
		
		last = System.nanoTime();
		
		pane.getScene().setOnKeyPressed(e -> keyPressed(e));
		pane.getScene().setOnKeyReleased(e -> keyReleased(e));
		
		GraphicsContext gt = canvas.getGraphicsContext2D();
		
		Avatar avatar = (Avatar) hrac.getAnimatedCharacter();
		
		new AnimationTimer()
		{
			@Override
			public void handle(long present) 
			{
				canvas.setWidth(pane.getWidth());
				canvas.setHeight(pane.getHeight());
				
				double ubehlyCas = (present - last) / 1.0E14;
				last = (long) ubehlyCas;
				
				setVelocity();	//nastavení rychlosti (pøechodnì mapì)
				
				setAnimation(ubehlyCas);	//nastavení animace
				
				setAvatarVelocity();	//nastaví rychlost avatara
				
				//aplikace zmìn avatara				
				if(avatar.getXvelocity() != 0 || avatar.getYvelocity() != 0)
				{
					avatar.update(ubehlyCas, canvas);
				}
				else
				{
					avatar.update(canvas);
				}
				
				setMapVelocity();	//pøípadná úprava rychlosti mapy
				map.update(ubehlyCas, canvas);	//aplikace zmìn mapy
				
				setOthersVelocityAndUpdate(ubehlyCas);
				
				//render
				gt.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				map.render(gt);
				avatar.render(gt);
				renderOthers(gt);
				
				//intersects - start action
				intersectionsAndActions();
			}
			private void setVelocity() //nastavuje rychlost podle stisknutých kláves
			{
				map.setVelocity(0, 0);
				if(input.contains("A"))
				{
					map.addVelocity(3, 0);
				}
				if(input.contains("D"))
				{
					map.addVelocity(-3, 0);
				}
				if(input.contains("W"))
				{
					map.addVelocity(0, 3);
				}
				if(input.contains("S"))
				{
					map.addVelocity(0, -3);
				}
			}
			private void setAnimation(double ubehlyCas) //nastaví animaci avatara
			{
				if(map.getXvelocity() != 0) //pokud se hýbe po X
				{
					if(map.getXvelocity()>0)	//pokud se hýbe smìrem vlevo
					{
						avatar.left(ubehlyCas);
					}
					else	//hýbe se vpravo
					{
						avatar.right(ubehlyCas);
					}
				}
				else if(map.getYvelocity() != 0) //pokud se hýbe po Y
				{
					if(map.getYvelocity()>0)	//pohybuje se nahoru
					{
						avatar.up(ubehlyCas);
					}
					else	//pohybuje se dolu
					{
						avatar.down(ubehlyCas);
					}
				}
				else	//nehýbe se vùbec
				{
					avatar.relax(ubehlyCas);
				}
			}
			private void setAvatarVelocity() //nastaví rychlost avataru
			{
				avatar.setVelocity(0,0);
				
				if(map.getXborderHit())
				{
					avatar.addVelocity(-map.getXvelocity(), 0);
				}
				if(map.getYborderHit())
				{
					avatar.addVelocity(0,-map.getYvelocity());
				}
			}
			private void setMapVelocity() //nastaví rychlost mapì
			{
				if(avatar.outOfCenterX(canvas) && avatar.outOfCenterY(canvas))
				{
					map.setVelocity(0, 0);
				}
				else if(avatar.outOfCenterX(canvas))
				{
					map.setVelocity(0, map.getYvelocity());
				}
				else if(avatar.outOfCenterY(canvas))
				{
					map.setVelocity(map.getXvelocity(), 0);
				}
			}
			private void setOthersVelocityAndUpdate(double time)
			{
				for(Postava postava:entities)
				{
					postava.getAnimatedCharacter().setVelocity(map.getXvelocity(), map.getYvelocity());
					postava.getAnimatedCharacter().update(time, canvas);
				}
			}
			private void renderOthers(GraphicsContext gc)
			{
				//utøídit podle Y
				entities.sort(new Comparator<Postava>(){
					@Override
					public int compare(Postava p0, Postava p1) 
					{
						return (int) (p0.getAnimatedCharacter().getPozice().getYPoz()-p1.getAnimatedCharacter().getPozice().getYPoz());
					}
					
				});
				
				//vykreslit od menšího k vìtšímu Y
				for(Postava postava:entities)
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
				for(Postava postava:temp)
				{
					if(avatar.intersectsWith(postava.getAnimatedCharacter()))
					{
						intersected = true;
						try 
						{ 
							startFight(hrac,postava);
							intersectionist = postava;
							stop();
						} 
						catch (Exception e) 
						{
							e.printStackTrace();
						}
					}
				}
				if(intersected)
				{
					entities.remove(intersectionist);
				}
			}		
		}.start();
	}
	public void setHrac(Hrac hrac)
	{
		this.hrac = hrac;
	}
	public void setEntities(ArrayList<Postava> entities)
	{
		this.entities = entities;
	}
	public ArrayList<Postava> getEntities()
	{
		return entities;
	}
	public void setMap(Map map)
	{
		this.map = map;
	}
	public Map getMap()
	{
		return map;
	}
	public void setStage(Stage stage)
	{
		this.stage = stage;
	}
	public Stage getStage()
	{
		return stage;
	}
	public void OpenInventory(ActionEvent event) throws Exception //handler pro tlaèítko Inventory
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/controllers/InventarView.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		
		//nastavování controlleru
		InventarController inventar = loader.getController();
		inventar.setPostava(hrac);
		inventar.inicializujInventar();
		
		//zobrazení inventáøe
		Stage stage = new Stage(); //POZOR! showAndWait() nesmí být použito na primaryStage
		stage.setScene(scene);
		stage.showAndWait(); //WAIT! jinak se pokroèí rovnou k dalšímu kroku
		
		//získání vybraného itemu
		INositelne item = inventar.getVybranyItem();
		try
		{
			System.out.println(item.toString());
		}
		catch(NullPointerException e)
		{
			System.out.println("Uživatel nic nevybral!");
		}
	}
	private void startFight(Postava me, Postava enemy) throws Exception
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
	public void setScenePane(Scene scene)
	{
		this.scene = scene;
	}
	public Scene getScene()
	{
		return scene;
	}
	private void keyPressed(KeyEvent e)
	{
		String code = e.getCode().toString();
		if(!input.contains(code))
		{
			input.add(code);
		}
	}	private void keyReleased(KeyEvent e)
	{
		String code = e.getCode().toString();
		input.remove(code);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		
	}
}