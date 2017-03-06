package model.items.generator;

public enum ZbraneM implements ZbraneInterface
{
	PRAVÍTKO("Zlomené pravítko", 19, "Alespoò tentokrát ti k nìèemu bude.", 15, 16, 2, 1),
	FLOORBALLKA("Floorballová hokejka", 349, "Sice na opaènou stranu, ale bolet to bude i tak!", 15, 40, 11, 9),
	PROPISKA("Propiska", 9, "Do oka padne jako ulitá.", 5, 21, 1, 3),
	UKAZOVÁTKO("Ukazovátko", 79, "Škoda jen, že s ním neumíš šermovat tak dobøe, jako uèitelé.", 12, 20, 3, 5),
	NÙŽKY("Tupé nùžky", 39, "Kdysi dávno byly ostré.", 10, 25, 10, 8),
	KVÌTINOVOMEÈ("Kvìtinùv meè", 14499, "Patøil jednomu z dávných králù ze severovýchodu. Vybojoval s ním mnoho bitev.", 1, 99, 1, 10);
	
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
