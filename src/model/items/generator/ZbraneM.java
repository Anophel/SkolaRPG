package model.items.generator;

public enum ZbraneM implements ZbraneInterface
{
	PRAV�TKO("Zlomen� prav�tko", 19, "Alespo� tentokr�t ti k n��emu bude.", 15, 16, 2, 1),
	FLOORBALLKA("Floorballov� hokejka", 349, "Sice na opa�nou stranu, ale bolet to bude i tak!", 15, 40, 11, 9),
	PROPISKA("Propiska", 9, "Do oka padne jako ulit�.", 5, 21, 1, 3),
	UKAZOV�TKO("Ukazov�tko", 79, "�koda jen, �e s n�m neum� �ermovat tak dob�e, jako u�itel�.", 12, 20, 3, 5),
	NَKY("Tup� n��ky", 39, "Kdysi d�vno byly ostr�.", 10, 25, 10, 8),
	KV�TINOVOME�("Kv�tin�v me�", 14499, "Pat�il jednomu z d�vn�ch kr�l� ze severov�chodu. Vybojoval s n�m mnoho bitev.", 1, 99, 1, 10);
	
	private final String nazev;
	private final int hodnota;
	private final String popis;
	private final int obrana;
	private final int utok;
	private final int trvanlivost;
	private final int rarity;
	
	ZbraneM(String nazev, int hodnota, String popis, int obrana, int utok, int trvanlivost, int rarity)
	{
		this.nazev = nazev;
		this.hodnota = hodnota;
		this.popis = popis;
		this.obrana = obrana;
		this.utok = utok;
		this.trvanlivost = trvanlivost;
		this.rarity = rarity;
	}

	@Override
	public String getNazev() {return nazev;}

	@Override
	public int getHodnota() {return hodnota;}

	@Override
	public String getPopis() {return popis;}

	@Override
	public int getObrana() {return obrana;}

	@Override
	public int getUtok() {return utok;}

	@Override
	public int getTrvanlivost() {return trvanlivost;}
	
	@Override
	public int getRarity() {return rarity;}
}
