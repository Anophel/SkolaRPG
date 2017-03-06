package model.items.generator;

public enum OdpadDruhy 
{
	PAPÍR("List papíru", 1, "Zmaèkané vysvìdèení z prvního roèníku. Není divu, že se ho adresát zbavil.", 1), 
	ALOBAL("Alobal", 1, "Mastný se zbytkem svaèiny. Dotyèná nejspíš nemìla rajèata v lásce.", 1), 
	LÁHEV("Láhev", 5, "Byla ve špatném koši, tøídìní se ještì poøád moc neuchytilo.", 5), 
	SPONKA("Sponka", 10, "Je na ní ještì vlas. Mohl bys toho využít pro Voo-Doo rituál.", 10), 
	ŽVÝKAÈKA("Žvýkaèka", 2, "Vyžvýkaná, ale stejnì sis nemohl pomoct a vzal sis jí. Èunì.", 2), 
	PRACH("Prach", 0, "Alespoò ten se ti v penìžence válí.", 0);
	
	private String nazev;
	private int hodnota;
	private String popis;
	private int rarity;
	
	
	OdpadDruhy(String nazev, int hodnota, String popis, int rarity)
	{
		this.nazev = nazev;
		this.hodnota = hodnota;
		this.popis = popis;
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
	public int getRarity()
	{
		return rarity;
	}
}
