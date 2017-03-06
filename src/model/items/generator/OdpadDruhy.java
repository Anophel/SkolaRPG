package model.items.generator;

public enum OdpadDruhy 
{
	PAP�R("List pap�ru", 1, "Zma�kan� vysv�d�en� z prvn�ho ro�n�ku. Nen� divu, �e se ho adres�t zbavil.", 1), 
	ALOBAL("Alobal", 1, "Mastn� se zbytkem sva�iny. Doty�n� nejsp� nem�la raj�ata v l�sce.", 1), 
	L�HEV("L�hev", 5, "Byla ve �patn�m ko�i, t��d�n� se je�t� po��d moc neuchytilo.", 5), 
	SPONKA("Sponka", 10, "Je na n� je�t� vlas. Mohl bys toho vyu��t pro Voo-Doo ritu�l.", 10), 
	�V�KA�KA("�v�ka�ka", 2, "Vy�v�kan�, ale stejn� sis nemohl pomoct a vzal sis j�. �un�.", 2), 
	PRACH("Prach", 0, "Alespo� ten se ti v pen�ence v�l�.", 0);
	
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
