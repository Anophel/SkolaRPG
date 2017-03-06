package model.items.generator;

public enum JidloDruhy 
{
	KOLÁÈ("Koláè z jídelny", 29, "S tvarohem a marmeládou.", 30, 8), 
	JABLKO("Jablko od maminky", 0, "Tak jak ho máš rád, mámin mazánku.", 15, 1), 
	CHLEBA("Chleba se šunkou", 1, "Na máslo jsi zapomnìl.", 25, 4), 
	COCACOLA("CocaCola z automatu", 25, "Je dost nahrkaná.", 30, 8), 
	SUŠENKA("Rozsednutá sušenka", 16, "Je v ní otisk zadku. Kdyby alespoò tvýho.", 28, 6), 
	VODA("Voda z kohoutku", 13, "Pìknì drahá. Kdybys pil pivo, ušetøil bys.", 20, 2), 
	ØÍZEK("Øízek na výlet", 25, "Povinná výbava všech èeských turistù.", 50, 10);
	
	private final String nazev;
	private final int hodnota;
	private final String popis;
	private final int hodnotaObnovy;
	private final int rarity;
	
	JidloDruhy(String nazev, int hodnota, String popis, int hodnotaObnovy, int rarity)
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
