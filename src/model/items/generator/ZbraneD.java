package model.items.generator;

public enum ZbraneD implements ZbraneInterface
{
	HASI��K("Hasic� p��stroj", 1999, "Tak zaji�t�n�, �e v p��pad� po��ru nijak nepom��e.", 70, 30, 3, 10),
	KN͎KA("Mein Kampf", 199, "Do roku 2015 zak�zan�.", 25, 10, 3, 7),
	ODPA��K("Odpadkov� ko�", 99, "D�ky Kultovy velmi vz�cn� v�c", 10, 10, 5, 1),
	BATOH("Ba���ek z 1. t��dy", 29, "M� s n�m spojenou spoustu vzpom�nek, ale te� t� za n�j �ikanuj�.", 28, 15, 10, 8),
	BUSTALENINA("Busta Lenina", 4999, "Ka�d� r�no ji mus� pozdravit.", 23, 35, 1, 4),
	�IDLE("�idle", 799, "Sakra nepohodln�. U�itelsk� by byla lep��.", 20, 30, 8, 2);
	
	private final String nazev;
	private final int hodnota;
	private final String popis;
	private final int obrana;
	private final int utok;
	private final int trvanlivost;
	private final int rarity;
	
	ZbraneD(String nazev, int hodnota, String popis, int obrana, int utok, int trvanlivost, int rarity)
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
