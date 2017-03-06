package model.items.generator;

public enum ZbraneD implements ZbraneInterface
{
	HASIÈÁK("Hasicí pøístroj", 1999, "Tak zajištìnı, e v pøípadì poáru nijak nepomùe.", 70, 30, 3, 10),
	KNÍKA("Mein Kampf", 199, "Do roku 2015 zakázaná.", 25, 10, 3, 7),
	ODPAÏÁK("Odpadkovı koš", 99, "Díky Kultovy velmi vzácná vìc", 10, 10, 5, 1),
	BATOH("Baùek z 1. tøídy", 29, "Máš s ním spojenou spoustu vzpomínek, ale teï tì za nìj šikanují.", 28, 15, 10, 8),
	BUSTALENINA("Busta Lenina", 4999, "Kadé ráno ji musíš pozdravit.", 23, 35, 1, 4),
	IDLE("idle", 799, "Sakra nepohodlná. Uèitelská by byla lepší.", 20, 30, 8, 2);
	
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
