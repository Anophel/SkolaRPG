package model.items.generator;

public enum ZbraneR implements ZbraneInterface
{
	ALOBALOVÁKULIÈKA("Alobalová kulièka", 0, "Pìknì zamaštìná od svaèiny.", 0, 10, 1, 1),
	FOTBALOVEJMÍÈ("Fotbalovı míè", 699, "Má bıt sloenı z rovnostrannıch pìtiúhelníkù.", 10, 37, 2, 9),
	TROJÚHELNÍK("Trojúhelník", 29, "Nech se jeho ryska zbarví krví!", 6, 35, 1, 5),
	PANTOFLE("Pantofle", 159, "Na záchodech se nabalila nechutnou špínou.", 5, 40, 1, 8),
	CD("CD", 299, "Pochází z uèebnice angliètiny. Stejnì jsi ho nikdy nepouil.", 2, 98, 1, 10),
	KRUÍTKO("Kruítko", 69, "Hrot má naštìstí ostrı jako ihadlo.", 2, 29, 1, 3);
	
	private final String nazev;
	private final int hodnota;
	private final String popis;
	private final int obrana;
	private final int utok;
	private final int trvanlivost;
	private final int rarity;
	
	ZbraneR(String nazev, int hodnota, String popis, int obrana, int utok, int trvanlivost, int rarity)
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
