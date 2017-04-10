package model.items;

import javafx.scene.image.ImageView;

public class Odpad implements INositelne {
	private static final TypPredmetu TYPPREDMETU = TypPredmetu.ODPAD;
	private String nazev;
	private String popis;
	private int hodnota;
	private ImageView Image;
	private int trvanlivost = 1;
	private int rarity;

	public Odpad(String nazev, int hodnota, String popis, int rarity) {
		this.nazev = nazev;
		this.hodnota = hodnota;
		this.popis = popis;
		this.rarity = rarity;
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
		return getHodnota();
	}

	public int getRarity() {
		return rarity;
	}
}
