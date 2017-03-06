package model.items.generator;

public enum ZbraneR implements ZbraneInterface
{
	ALOBALOV�KULI�KA("Alobalov� kuli�ka", 0, "P�kn� zama�t�n� od sva�iny.", 0, 10, 1, 1),
	FOTBALOVEJM��("Fotbalov� m��", 699, "M� b�t slo�en� z rovnostrann�ch p�ti�heln�k�.", 10, 37, 2, 9),
	TROJ�HELN�K("Troj�heln�k", 29, "Nech� se jeho ryska zbarv� krv�!", 6, 35, 1, 5),
	PANTOFLE("Pantofle", 159, "Na z�chodech se nabalila nechutnou �p�nou.", 5, 40, 1, 8),
	CD("CD", 299, "Poch�z� z u�ebnice angli�tiny. Stejn� jsi ho nikdy nepou�il.", 2, 98, 1, 10),
	KRU��TKO("Kru��tko", 69, "Hrot m� na�t�st� ostr� jako �ihadlo.", 2, 29, 1, 3);
	
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
