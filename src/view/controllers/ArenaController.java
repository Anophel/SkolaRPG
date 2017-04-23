package view.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.Arena;
import model.characters.Postava;

public class ArenaController implements Initializable
{
	@FXML
	private Label mojeJmeno;
	@FXML
	private Label nepritelovoJmeno;
	@FXML
	private ProgressBar tvojeHPBar;
	@FXML
	private ProgressBar nepritelovoHpBar;
	@FXML
	private Button vyberZbran;
	@FXML
	private ImageView nepritelovaZbranView;
	@FXML
	private Label mojeHP;
	@FXML
	private Label nepritelovoHP;
	@FXML
	private TextFlow textFlow;
	@FXML
	private ScrollPane scrollPane;
	@FXML
	private Label countdown;
	@FXML
	private Button result;
	
	private Postava tvojePostava;
	private Postava nepritel;
	private Arena arena = new Arena();
	private MainGameControler MGC;
	private Stage stage;
	
	public void refresh()
	{
		mojeJmeno.setText(tvojePostava.getJmeno());
		nepritelovoJmeno.setText(nepritel.getJmeno());
		tvojeHPBar.setProgress(tvojePostava.getHP()/100);
		nepritelovoHpBar.setProgress(nepritel.getHP()/100);
		mojeHP.setText(Double.toString(tvojePostava.getHP()));
		nepritelovoHP.setText(Double.toString(nepritel.getHP()));
		
		Text report = new Text(arena.getReport()+" \n");
		textFlow.getChildren().add(report);
		scrollPane.setVvalue(1.0);
	}
	public void createArena()
	{
		Arena arena = new Arena();
		arena.setTvojePostava(tvojePostava);
		arena.setNepritel(nepritel);
		this.arena = arena;
		refresh();
	}
	public void selectItem(ActionEvent event)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/controllers/InventarView.fxml"));
			Parent root = loader.load();
			InventarController inventar = loader.getController();
			inventar.setPostava(tvojePostava);
			inventar.inicializujInventar();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.showAndWait();
			arena.setVybranyItem(inventar.getVybranyItem());
			fight();
		}
		catch(Exception ex)
		{
			System.out.println("An error occured while executing selectItem(ActionEvent)");
			ex.printStackTrace();
		}
	}
	private void fight()
	{
		boolean obaNazivu = arena.fight();
		if(arena.getVybranyItem() != null)
		{
			refresh();
		}
		arena.setVybranyItem(null); //prevence proti zavírání výbìru
		if(!obaNazivu)
		{
			if(tvojePostava.getHP()>=0)
			{
				Text report = new Text("Vyhrál jsi!");
				textFlow.getChildren().add(report);
				result.setVisible(true);
				result.setText("You Won");
				result.setOnAction(e -> youWon(e));
				tvojePostava.getBatoh().addAll(nepritel.getBatoh().getObsah());
			}
			else
			{
				Text report = new Text("Prohrál jsi!");
				textFlow.getChildren().add(report);
				result.setVisible(true);
				result.setText("You Lost");
				result.setOnAction(e -> youLost(e));
			}
			vyberZbran.setVisible(false);
		}
		if(tvojePostava.getBatoh().getObsah().isEmpty())
		{
			Text report = new Text("Došly ti zbranì!");
			textFlow.getChildren().add(report);
			result.setVisible(true);
			result.setText("You Lost");
			result.setOnAction(e -> youLost(e));
			vyberZbran.setVisible(false);
		}
		else if(nepritel.getBatohKBoji().getObsah().isEmpty())
		{
			Text report = new Text("Nepøítelovy došly zbranì!");
			textFlow.getChildren().add(report);
			result.setVisible(true);
			result.setText("You Won");
			result.setOnAction(e -> youWon(e));
			vyberZbran.setVisible(false);
			tvojePostava.getBatoh().addAll(nepritel.getBatoh().getObsah());
		}
	}
	private void youWon(ActionEvent e)
	{
		MGC.setInitialPart(MGC.getLevel().getPartBeingUsed());
		stage.setScene(MGC.getScene());
		
		MGC.run();
	}
	private void youLost(ActionEvent e)
	{
		
	}
	public void setStage(Stage stage)
	{
		this.stage = stage;
	}
	public void setTvojePostava(Postava postava)
	{
		this.tvojePostava = postava;
	}
	public void setNepritelovaPostava(Postava postava)
	{
		this.nepritel = postava;
	}
	public void setMainGameControler(MainGameControler mgc)
	{
		MGC = mgc;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		textFlow.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		result.setVisible(false);
	}
}
