package model.characters;

import model.Batoh;
import model.Pozice;
import model.items.INositelne;
import model.items.Jidlo;

public class Hrac extends Postava{

	private static final Pozice POZICE = new Pozice(0,0);
	private static final String JMENO = "Frajer";
	private static final double SYTOST = 100.0;
	private static final double KOFEIN = 0.0;
	private static final double NIKOTIN = 0.0;
	private static final Batoh BATOH = new Batoh(20);
	
	private double sytost;
	private double kofein;
	private double nikotin;
	private Pozice pozice;
	
	private INositelne vybranyItem;
	
	public Hrac()
	{
		this(JMENO);
	}
	public Hrac(String jmeno)
	{
		this(jmeno,NIKOTIN);
	}
	public Hrac(String jmeno, double nikotin)
	{
		this(jmeno, nikotin, SYTOST);
	}
	public Hrac(String jmeno, double nikotin, double sytost)
	{
		this(jmeno, nikotin, sytost, KOFEIN);
	}
	public Hrac(String jmeno, double nikotin, double sytost, double kofein)
	{
		this(jmeno, nikotin, sytost, kofein, POZICE);
	}
	public Hrac(String jmeno, double nikotin, double sytost, double kofein, Pozice pozice)
	{
		this(jmeno,nikotin,sytost,kofein,pozice,BATOH);
	}
	public Hrac(String jmeno, double nikotin, double sytost, double kofein, Pozice pozice, Batoh batoh)
	{
		super(jmeno,batoh);
		this.nikotin = nikotin;
		this.sytost = sytost;
		this.kofein = kofein;
		this.pozice = pozice;
	}
	public double getNikotin(){return nikotin;}
	public void setNikotin(double newNikotin){this.nikotin = newNikotin;}
	public double getKofein(){return kofein;}
	public void setKofein(double newKofein){this.kofein = newKofein;}
	public double getSytost(){return sytost;}
	public void setSytost(double newSytost){this.sytost = newSytost;}
	public Pozice getPozice(){return pozice;}
	public void setPozice(Pozice pozice){this.pozice = pozice;}
	public void setVybranyItem(INositelne item)
	{
		this.vybranyItem = item;
	}
	public INositelne getVybranyItem()
	{
		return vybranyItem;
	}
	@Override
	public double getHP(){return getSytost()+getKofein();}
	@Override
	public void injured(double dmg) //vstupuje po�kozen�, spo��t� novou hodnotu Kofeinu a Nikotinu
	{
		/*
		 * Pokud by p�i d�len� dmg p�l na p�l mezi nikotin a sytost jedna z hodnot spadla pod nulu
		 * mus�m rozpo��tat dmg tak, aby se pokud mo�no ob� hodnoty vyrovnaly. Pokud to mo�n� nen�,
		 * ode�tu dmg jenom od jedn�.
		 */
		if((getKofein()+getSytost())<dmg) //v tomto p��pad� hr�� �tok nep�e�il
		{
			setKofein(0);
			setSytost(0);
		}
		else if(getKofein()<(dmg/2)) //pokud by kofein spadl pod nulu
		{
			if(dmg<(getSytost()-getKofein())) //pokud je dmg men�� ne� rozd�l obou hodnot
			{
				setSytost(getSytost()-dmg);
			}
			else //dmg se rozd�l� tak, aby se ob� hodnoty vyrovnaly
			{
				double newHodnoty = getSytost();
				newHodnoty = newHodnoty - (getSytost()-getKofein()); //vyrovn�m ob� hodnoty
				newHodnoty = newHodnoty - (dmg-newHodnoty)/2;	//a rozd�l�m zb�vaj�c� dmg na poloviny
				setKofein(newHodnoty);
				setSytost(newHodnoty);
			}
		}
		else if(getSytost()<(dmg/2)) //pokud by sytost spadl pod nulu, zachov�m se stejn�, jako v p�ede�l�m p��pad�
		{
			if(dmg<(getKofein()-getSytost()))
			{
				setKofein(getKofein()-dmg);
			}
			else
			{
				double newHodnoty = getKofein();
				newHodnoty = newHodnoty - (getKofein()-getSytost());
				newHodnoty = newHodnoty - (dmg-newHodnoty)/2;
				setKofein(newHodnoty);
				setSytost(newHodnoty);
			}
		}
		else //v tomto p��pad� rozpo��t�m dmg p�l na p�l
		{
			setKofein(getKofein()-(dmg/2));
			setSytost(getSytost()-(dmg/2));
		}
	}
	@Override
	public void eat(INositelne item) //vstupuje INositelne, vystupuje nov� Kofein nebo Nikotin
	{
		Jidlo jidlo = (Jidlo) item;
		if(jidlo.getTypObnovy().equals("HP"))
		{
			setSytost(getSytost()+jidlo.getHodnotaObnovy());
		}
		else
		{
			setNikotin(getNikotin()+jidlo.getHodnotaObnovy());
		}
	}
	@Override
	public void addToBatoh(INositelne predmet) //p�id�v� INositelne do batohu hr��e
	{
		Batoh batoh = getBatoh();
		batoh.add(predmet);
		setBatoh(batoh);
	}
}
