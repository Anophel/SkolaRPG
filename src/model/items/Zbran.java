package model.items;

import javafx.scene.image.ImageView;

public class Zbran implements INositelne
{
	private static final TypPredmetu TYPPRDMETU = TypPredmetu.ZBRAN;
	private String nazev;
	private String popis;
	private int hodnota;
	private int utok;
	private int obrana;
	private int trvanlivost;
	private TypUtoku TYPUTOKU;
	private ImageView Image;
	private int rarity;
	
	public Zbran(String nazev, int hodnota, int utok, int obrana, int trvanlivost, TypUtoku TYPUTOKU, String popis, int rarity)
	{
		this.nazev = nazev;
		this.hodnota = hodnota;
		this.utok = utok;
		this.obrana = obrana;
		this.trvanlivost = trvanlivost;
		this.TYPUTOKU = TYPUTOKU;
		this.popis = popis;
		this.rarity = rarity;
	}
	public int getUtok()
	{
		return utok;
	}
	public int getObrana()
	{
		return obrana;
	}
	public TypUtoku getTypUtoku()
	{
		return TYPUTOKU;
	}
	public void poskodPredmet(int oKolik)
	{
		trvanlivost = trvanlivost - oKolik;
	}
	@Override
	public TypPredmetu getTypPredmetu() {
		return TYPPRDMETU;
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
		return getNazev() +", "+ getPopis();
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
	public void setTrvanlivost(int nova) 
	{
		this.trvanlivost = nova;
	}
	@Override
	public int getTrvanlivost()
	{
		return trvanlivost;
	}
	@Override
	public int getPower()
	{
		return getUtok()+getObrana();
	}
	@Override
	public int getRarity()
	{
		return rarity;
	}
}
