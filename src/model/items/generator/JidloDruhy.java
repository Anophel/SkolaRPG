package model.items.generator;

public enum JidloDruhy 
{
	KOL��("Kol�� z j�delny", 29, "S tvarohem a marmel�dou.", 30, 8), 
	JABLKO("Jablko od maminky", 0, "Tak jak ho m� r�d, m�min maz�nku.", 15, 1), 
	CHLEBA("Chleba se �unkou", 1, "Na m�slo jsi zapomn�l.", 25, 4), 
	COCACOLA("CocaCola z automatu", 25, "Je dost nahrkan�.", 30, 8), 
	SU�ENKA("Rozsednut� su�enka", 16, "Je v n� otisk zadku. Kdyby alespo� tv�ho.", 28, 6), 
	VODA("Voda z kohoutku", 13, "P�kn� drah�. Kdybys pil pivo, u�et�il bys.", 20, 2), 
	��ZEK("��zek na v�let", 25, "Povinn� v�bava v�ech �esk�ch turist�.", 50, 10);
	
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
