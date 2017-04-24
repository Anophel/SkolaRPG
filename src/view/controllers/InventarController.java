package view.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import model.Batoh;
import model.TlacitkoInventare;
import model.characters.Postava;
import model.items.INositelne;
import model.items.Jidlo;
import model.items.Zbran;

public class InventarController implements Initializable
{
	/*
	 * Deklarace všech prvkù, které figurují v oknì batohu
	 */
	
	//oddíl zbraní
	@FXML
	private TilePane zbraneTilePane;
	@FXML
	private Label nazevZbraneLabel;
	@FXML
	private Label popisZbraneLabel;
	@FXML
	private Label utokZbraneLabel;
	@FXML
	private Label obranaZbraneLabel;
	@FXML
	private Label typPoskozeniZbraneLabel;
	@FXML
	private Label vydrzZbraneLabel;
	@FXML
	private Label cenaZbraneLabel;
	@FXML
	private Button vyberZbraneButton;
	
	//oddíl jídla
	@FXML
	private TilePane jidloTilePane;
	@FXML
	private Label nazevJidlaLabel;
	@FXML
	private Label popisJidlaLabel;
	@FXML
	private Label hodnotaObnovyJidlaLabel;
	@FXML
	private Label cenaJidlaLabel;
	@FXML
	private Button vyberJidlaButton;
	
	//oddíl ostatní
	@FXML
	private TilePane ostatniTilePane;
	@FXML
	private Label nazevOstatniLabel;
	@FXML
	private Label popisOstatniLabel;
	@FXML
	private Label cenaOstatniLabel;
	@FXML
	private Button vyberOstatniButton;
	
	
	@FXML
	private Button sort1;
	@FXML
	private Button sort2;
	@FXML
	private Button sort3;
	
	/*
	 * Deklarace parametrù a promìnných
	 */
	
	private Postava postava;
	private INositelne vybranaZbran = null;
	private INositelne vybraneJidlo = null;
	private INositelne vybraneOstatni = null;
	private INositelne vybranyItem = null;
	private boolean vzestupne = true;
	
	/*
	 * Metody controlleru
	 */
	
	public void inicializujInventar() //naplnìní TilePanù tlaèítky
	{
		clear();
		Batoh batoh = postava.getBatoh();
		for(INositelne item:batoh.getZbrane())
		{
			TlacitkoInventare tlacitko = new TlacitkoInventare();
			tlacitko.setItem(item);
			tlacitko.setMinHeight(120);
			tlacitko.setMinWidth(120);
			tlacitko.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> setVybranaZbran(item));
			zbraneTilePane.getChildren().add(tlacitko);
		}
		for(INositelne item:batoh.getJidlo())
		{
			TlacitkoInventare tlacitko = new TlacitkoInventare();
			tlacitko.setItem(item);
			tlacitko.setMinHeight(120);
			tlacitko.setMinWidth(120);
			tlacitko.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> setVybraneJidlo(item));
			jidloTilePane.getChildren().add(tlacitko);
		}
		for(INositelne item:batoh.getOdpad())
		{
			TlacitkoInventare tlacitko = new TlacitkoInventare();
			tlacitko.setItem(item);
			tlacitko.setMinHeight(120);
			tlacitko.setMinWidth(120);
			tlacitko.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> setVybraneOstatni(item));
			ostatniTilePane.getChildren().add(tlacitko);
		}
		for(INositelne item:batoh.getKlice())
		{
			TlacitkoInventare tlacitko = new TlacitkoInventare();
			tlacitko.setItem(item);
			tlacitko.setMinHeight(120);
			tlacitko.setMinWidth(120);
			tlacitko.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> setVybraneOstatni(item));
			ostatniTilePane.getChildren().add(tlacitko);
		}
		if(vzestupne)
		{
			sort1.setText("Seøaï vzestupnì");
			sort2.setText("Seøaï vzestupnì");
			sort3.setText("Seøaï vzestupnì");
		}
		else
		{
			sort1.setText("Seøaï sestupnì");
			sort2.setText("Seøaï sestupnì");
			sort3.setText("Seøaï sestupnì");
		}
	}
	public void setPostava(Postava postava)
	{
		this.postava = postava;
	}
	private void clear() //èistí TilePany, kdyby v nich nìco pøípadnì bylo
	{
		zbraneTilePane.getChildren().removeAll(zbraneTilePane.getChildren());
		jidloTilePane.getChildren().removeAll(jidloTilePane.getChildren());
		ostatniTilePane.getChildren().removeAll(ostatniTilePane.getChildren());
	}
	private void refreshZbranStaty() //nastaví informace o dané zbrani
	{
		nazevZbraneLabel.setText(getVybranaZbran().getNazev());
		popisZbraneLabel.setText(getVybranaZbran().getPopis());
		utokZbraneLabel.setText(Integer.toString(getVybranaZbran().getUtok()));
		obranaZbraneLabel.setText(Integer.toString(getVybranaZbran().getObrana()));
		typPoskozeniZbraneLabel.setText(getVybranaZbran().getTypUtoku().toString());
		vydrzZbraneLabel.setText(Integer.toString(getVybranaZbran().getTrvanlivost()));
		cenaZbraneLabel.setText(Integer.toString(getVybranaZbran().getHodnota()));
	}
	private void refreshJidloStaty() //nastaví informace o daném jídle
	{
		nazevJidlaLabel.setText(getVybraneJidlo().getNazev());
		popisJidlaLabel.setText(getVybraneJidlo().getPopis());
		hodnotaObnovyJidlaLabel.setText(Integer.toString(getVybraneJidlo().getHodnotaObnovy())+getVybraneJidlo().getTypObnovy());
		cenaJidlaLabel.setText(Integer.toString(getVybraneJidlo().getHodnota()));
	}
	private void refreshOstatniStaty() //nastaví informace o daném odpadu/klíèi
	{
		nazevOstatniLabel.setText(getVybraneOstatni().getNazev());
		popisOstatniLabel.setText(getVybraneOstatni().getPopis());
		cenaOstatniLabel.setText(Integer.toString(getVybraneOstatni().getHodnota()));
	}
	public void setVybranyItem(INositelne item)
	{
		this.vybranyItem = item;
	}
	public INositelne getVybranyItem()
	{
		return vybranyItem;
	}
	
	/*
	 * Metody controlleru - handlery
	 */
	
	private void setVybranaZbran(INositelne zbran)	//handler pro tlaèítka zbraní
	{
		vybranaZbran = zbran;
		refreshZbranStaty();
	}
	public Zbran getVybranaZbran()
	{
		return (Zbran) vybranaZbran;
	}
	
	private void setVybraneJidlo(INositelne jidlo)	//handler pro jídla
	{
		vybraneJidlo = jidlo;
		refreshJidloStaty();
	}
	public Jidlo getVybraneJidlo()
	{
		return (Jidlo) vybraneJidlo;
	}
	
	private void setVybraneOstatni(INositelne ostatni)	//handler pro tlaèítka odpadu + klíèù
	{
		vybraneOstatni = ostatni;
		refreshOstatniStaty();
	}
	public INositelne getVybraneOstatni()
	{
		return vybraneOstatni;
	}
	
	/*
	 * tohle zakládáš, abys vìdìl, ze které záložky byl výbìr zvolen
	 * kdyby totiž uživatel nejdøív na všech nìco naklikal a potom se vrátil k tomu prvnímu,
	 * tak nevybral to, na co naposled klinul, musíš tedy rozeznat, který výbìr platí
	 */
	public void setVybranyItemZbran(ActionEvent event) //handler tlaèítka výbìru u zbraní
	{
		setVybranyItem(vybranaZbran);
		closeInventory(event);
	}
	public void setVybranyItemJidlo(ActionEvent event)	//handler pro talèítko výbìru u jídla
	{
		setVybranyItem(vybraneJidlo);
		closeInventory(event);
	}
	public void setVybranyItemOstatni(ActionEvent event) //handler pro tlaèítko výbìru odpadu+klíèù
	{
		setVybranyItem(vybraneOstatni);
		closeInventory(event);
	}
	private void closeInventory(ActionEvent event) //zavírá okno
	{
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.close();
	}
	public void handleSort(ActionEvent e)
	{
		postava.getBatoh().sortByPower(vzestupne);
		if(vzestupne)
		{
			vzestupne = false;
		}
		else
		{
			vzestupne = true;
		}
		inicializujInventar();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
	}
}
