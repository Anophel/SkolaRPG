package model.items;

import javafx.scene.image.ImageView;

public class Jidlo implements INositelne {
	private static final TypPredmetu TYPPREDMETU = TypPredmetu.JIDLO;
	private String nazev;
	private String popis;
	private int hodnota;
	private int hodnotaObnovy;
	private String typObnovy;
	private ImageView Image;
	private int trvanlivost = 1;
	private int rarity;

	public Jidlo(String nazev, int hodnota, int hodnotaObnovy, String typObnovy, String popis, int rarity) {
		this.nazev = nazev;
		this.hodnota = hodnota;
		this.hodnotaObnovy = hodnotaObnovy;
		this.typObnovy = typObnovy;
		this.popis = popis;
		this.rarity = rarity;
	}

	public String getNazev() {
		return nazev;
	}

	public TypPredmetu getTypPredmetu() {
		return TYPPREDMETU;
	}

	public String getPopis() {
		return popis;
	}

	public int getHodnota() {
		return hodnota;
	}

	public int getHodnotaObnovy() {
		return hodnotaObnovy;
	}

	public String getTypObnovy() {
		return typObnovy;
	}

	@Override
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
		return getHodnotaObnovy();
	}

	public int getRarity() {
		return rarity;
	}
}
