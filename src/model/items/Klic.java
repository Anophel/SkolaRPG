package model.items;

import javafx.scene.image.ImageView;
import model.items.generator.KlicDruhy;

public class Klic implements INositelne {
	private static final TypPredmetu TYPPREDMETU = TypPredmetu.KLIC;
	private String nazev;
	private String popis;
	private int hodnota;
	private int IDDveri;
	private ImageView Image;
	private int trvanlivost = 1;
	private int rarity = 10;

	public Klic(String nazev, int hodnota, int IDDveri, String popis) {
		this.nazev = nazev;
		this.hodnota = hodnota;
		this.IDDveri = IDDveri;
		this.popis = popis;
	}
	public Klic(KlicDruhy klic)
	{
		this(klic.getNazev(), klic.getHodnota(), klic.getID(), klic.getPopis());
	}
	public int getIDDveri() {
		return IDDveri;
	}

	public void setPopis(String popis) {
		this.popis = popis;
	}

	public TypPredmetu getTypPredmetu() {
		return TYPPREDMETU;
	}

	public String getNazev() {
		return nazev;
	}

	public String getPopis() {
		return popis;
	}

	public int getHodnota() {
		return hodnota;
	}

	public String toString() {
		return getNazev() + ", " + getPopis();
	}

	public ImageView getObrazek() {
		return Image;
	}

	public void setObrazek(ImageView image) {
		Image = image;
	}

	public int getTrvanlivost() {
		return trvanlivost;
	}

	public void setTrvanlivost(int trvanlivost) {
		this.trvanlivost = trvanlivost;
	}

	public int getPower() {
		return getIDDveri();
	}

	public int getRarity() {
		return rarity;
	}
}
