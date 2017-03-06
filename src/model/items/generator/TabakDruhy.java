package model.items.generator;

public enum TabakDruhy 
{
	MARLBORO("Marlboro", 90, "Rakovina plic už èeká.", 25, 6),
	CHESTERFIELD("Chesterfield 70s. Red", 70, "Tvùj vìrný kamarád.", 15, 1),
	DAVIDOFF("Davidoff Gold slims", 94, "Vážnì bys toho už mìl nechat.", 30, 9),
	CAMEL("Camel no filter", 72, "Není to samé, co Camle Toe.", 20, 3);
	
	private String nazev;
	private int hodnota;
	private String popis;
	private int hodnotaObnovy;
	private int rarity;
	
	TabakDruhy(String nazev, int hodnota, String popis, int hodnotaObnovy, int rarity)
	{
		this.nazev = nazev;
		this.hodnota = hodnota;
		this.popis = popis;
		this.hodnotaObnovy = hodnotaObnovy;
		this.rarity = rarity;
	}
	public String getNazev()
	{
		return nazev;
	}
	public int getHodnota()
	{
		return hodnota;
	}
	public String getPopis()
	{
		return popis;
	}
	public int getHodnotaObnovy()
	{
		return hodnotaObnovy;
	}
	public int getRarity()
	{
		return rarity;
	}
}
