package model.items.generator;

public enum KlicDruhy 
{
	KLIC_ID0("Karta od vchodu", 50, 0, "Otvírá vchod do školy. Nefunguje vìtšinu dne."), 
	KLIC_ID1("Èip od biologie", 100, 1, "Otvírá dveøe ke spoustì preparovaných zvíøat."), 
	KLIC_ID2("Èip od kanceláøe", 200, 2, "Otvírá dveøe k osobním informacím nás všech."), 
	KLIC_ID3("Èip od poèítaèovny", 400, 3, "Otvírá dveøe k nejvìtší krádeži, kterou škola zažila.");
	
	private String nazev;
	private int hodnota;
	private int ID;
	private String popis;
	KlicDruhy(String nazev, int hodnota, int ID, String popis)
	{
		this.nazev = nazev;
		this.hodnota = hodnota;
		this.ID = ID;
		this.popis = popis;
	}
	
	public String getNazev()
	{
		return nazev;
	}
	public int getHodnota()
	{
		return hodnota;
	}
	public int getID()
	{
		return ID;
	}
	public String getPopis()
	{
		return popis;
	}
	public static KlicDruhy getPodleID(int ID) //vrací null, když ID neodpovídá žádnému klíèi
	{
		for(KlicDruhy klic: KlicDruhy.values())
		{
			if(klic.getID() == ID)
			{
				return klic;
			}
		}
		return null;
	}
}
