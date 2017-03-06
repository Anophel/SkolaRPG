package model.items;

import javafx.scene.image.ImageView;

public class Odpad implements INositelne
{
	private static final TypPredmetu TYPPREDMETU = TypPredmetu.ODPAD;
	private String nazev;
	private String popis;
	private int hodnota;
	private ImageView Image;
	private int trvanlivost = 1;
	private int rarity;
	
	public Odpad(String nazev, int hodnota, String popis, int rarity)
	{
		this.nazev = nazev;
		this.hodnota = hodnota;
		this.popis = popis;
		this.rarity = rarity;
	}
	@Override
	public TypPredmetu getTypPredmetu() {
		return TYPPREDMETU;
	}
	@Override
	public String getNazev() {
		return nazev;
	}
	@Override
	public String getPopis() {
		return popis;
	}
	@Override
	public int getHodnota() {
		return hodnota;
	}
	@Override
	public String toString()
	{
		return getNazev()+", "+getPopis();
	}
	@Override
	public ImageView getObrazek()
	{
		return Image;
	}
	public void setObrazek(ImageView image)
	{
		Image = image;
	}
	@Override
	public int getTrvanlivost() 
	{
		return trvanlivost;
	}
	@Override
	public void setTrvanlivost(int trvanlivost) 
	{
		this.trvanlivost = trvanlivost;
	}
	@Override
	public int getPower()
	{
		return getHodnota();
	}
	@Override
	public int getRarity()
	{
		return rarity;
	}
}
