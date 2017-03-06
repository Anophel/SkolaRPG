package model.items.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import model.items.INositelne;
import model.items.Jidlo;
import model.items.Klic;
import model.items.Odpad;
import model.items.TypPredmetu;
import model.items.TypUtoku;
import model.items.Zbran;

public class Generator 
{
	Random random = new Random(); //generátor náhodných èísel
		
	public ArrayList<INositelne> generujBatoh(int pocet) //vrátí list itemù - Zbran, Jidlo
	{
		ArrayList<INositelne> vyber = new ArrayList<INositelne>(); //sem uložím vybrané itemy
		List<TypPredmetu> predmetyBatoh = Arrays.asList(TypPredmetu.ZBRAN, TypPredmetu.JIDLO); //seznam vìcí, které chceme generovat do batohu
		
		for(int i = 0; i<pocet;i++)
		{
			TypPredmetu vybranyTyp = predmetyBatoh.get(random.nextInt(predmetyBatoh.size())); //náhodný výbìr typu pøedmìtu
			if(vybranyTyp == TypPredmetu.ZBRAN)
			{
				int rarity = rarityRoll();
				INositelne item = generujZbran(rarity);
				vyber.add(item); //novou zbraò pøidám do listu
			}
			else if(vybranyTyp == TypPredmetu.JIDLO)
			{
				int rarity = rarityRoll();
				INositelne item = generujJidlo(rarity);
				vyber.add(item); //nové jídlo pøidám do listu
			}
		}
		
		return vyber;
	}
	public ArrayList<INositelne> generujMapu(int pocet) //vrátí list itemù - Zbran, Jidlo, Odpad
	{
		/*
		 * pomocí generátoru náhodných èísel si vyberu, kolik chci mít na mapì
		 * zbraní a jídla, pro nì použiji již existující generátor pro batoh, který funguje stejnì
		 */
		int uzitecneItemy = random.nextInt(pocet);
		ArrayList<INositelne> vyber = generujBatoh(uzitecneItemy); //rovnou je uložím do seznamu, který posléze vrátím
		int zbytek = pocet - uzitecneItemy; //zjistím, kolik itemù "ODPAD" na mapì bude
		for(int i = 0; i<zbytek; i++)
		{
			INositelne odpad = generujOdpad(rarityRoll());
			vyber.add(odpad); //nový Odpad pøidám do listu
		}
		return vyber;
	}
	public Zbran generujZbran(int rarity) //vrátí jednu náhodnou zbraò
	{
		List<TypUtoku> utoky = Arrays.asList(TypUtoku.values());	//možné typy útokù
		
		TypUtoku typUtoku = utoky.get(random.nextInt(utoky.size())); //vybral jsem náhodný typ útoku
		
		ZbraneInterface zbranI = Arrays.asList(ZbraneM.values()).get(random.nextInt(Arrays.asList(ZbraneM.values()).size())); //založím Interface (musí být inicializovaný, je mi jedno, jaký jsem udìlal)
		
		if(typUtoku == TypUtoku.BLIZKY)
		{
			List<ZbraneM> zbraneMNazvy = new LinkedList<ZbraneM>(Arrays.asList(ZbraneM.values()));	//názvy zbraní na blízko
			boolean iterated = false;	//probìhlo už vybírání?
			do
			{
				//System.out.println(rarity);
				if(iterated)	//pokud probìhlo vybírání
				{
					zbraneMNazvy.remove(zbranI);	//odeberu zbraò, která neprošla
				}
				zbranI = zbraneMNazvy.get(random.nextInt(zbraneMNazvy.size()));  //výbìr pøedmìtu ze Zbran
				iterated = true;	//vybírání už probìhlo
			}
			while(zbranI.getRarity()>rarity);	//pokud vybraný item neodpovídá podmínce, zkusím to znovu
		} 
		else if(typUtoku == TypUtoku.DALEKY)
		{
			List<ZbraneR> zbraneRNazvy = new LinkedList<ZbraneR>(Arrays.asList(ZbraneR.values()));	//názvy zbraní na dálku
			boolean iterated = false;
			do
			{
				//System.out.println(rarity);
				if(iterated)
				{
					zbraneRNazvy.remove(zbranI);
				}
				zbranI = zbraneRNazvy.get(random.nextInt(zbraneRNazvy.size())); //výbìr pøemìtu z ZbraneR
				iterated = true;
			}
			while(zbranI.getRarity()>rarity);
		} 
		else
		{
			List<ZbraneD> zbraneDNazvy = new LinkedList<ZbraneD>(Arrays.asList(ZbraneD.values()));	//názvy obranných zbraní
			boolean iterated = false;
			do
			{
				//System.out.println(rarity);
				if(iterated)
				{
					zbraneDNazvy.remove(zbranI);
				}
				zbranI = zbraneDNazvy.get(random.nextInt(zbraneDNazvy.size())); //výbìr pøedmìtu ze ZbraneD
				iterated = true;
			}
			while(zbranI.getRarity()>rarity);
		}
		
		//naplním všechny potøebné parametry pro vytvoøení Zbranì
		String nazev = zbranI.getNazev();
		int hodnota = zbranI.getHodnota();
		int utok = zbranI.getUtok();
		int obrana = zbranI.getObrana();
		int trvanlivost = zbranI.getTrvanlivost();
		String popis = zbranI.getPopis();
		int actualRarity = zbranI.getRarity();
		
		Zbran zbran = new Zbran(nazev,hodnota,utok,obrana,trvanlivost,typUtoku, popis, actualRarity); //vytvoøím zbraò
		
		return zbran; //vrátím ji
	}
	public ArrayList<Zbran> generujZbrane(int pocet) //generuje zadaný poèet zbraní
	{
		ArrayList<Zbran> zbrane = new ArrayList<>();
		for(int i = 0; i<pocet; i++)
		{
			zbrane.add(generujZbran(rarityRoll()));
		}
		return zbrane;
	}
	public Jidlo generujJidlo(int rarity) //vrátí jedno náhodné jídlo
	{
		List<JidloDruhy> jidloDruhy = new LinkedList<JidloDruhy>(Arrays.asList(JidloDruhy.values()));	//názvy jídel
		JidloDruhy vybraneJidlo = jidloDruhy.get(random.nextInt(jidloDruhy.size()));
		
		while(vybraneJidlo.getRarity()>rarity)
		{
			jidloDruhy.remove(vybraneJidlo);
			vybraneJidlo = jidloDruhy.get(random.nextInt(jidloDruhy.size())); //náhodnì vyberu jedno z jídel
		}
		
			
		String nazev = vybraneJidlo.getNazev();	
		int hodnota = vybraneJidlo.getHodnota();
		int hodnotaObnovy = vybraneJidlo.getHodnotaObnovy();		
		String typObnovy = "HP";
		String popis = vybraneJidlo.getPopis();
		int actualRarity = vybraneJidlo.getRarity();

		Jidlo jidlo = new Jidlo(nazev, hodnota, hodnotaObnovy, typObnovy, popis, actualRarity);
		return jidlo;
	}
	public ArrayList<Jidlo> generujJidla(int pocet) //generuje zadaný poèet jídla
	{
		ArrayList<Jidlo> jidlo = new ArrayList<>();
		for(int i = 0; i<pocet; i++)
		{
			jidlo.add(generujJidlo(rarityRoll()));
		}
		return jidlo;
	}
	public Jidlo generujTabak(int rarity) //vygeneruje jeden náhodný tabákový výrobek
	{
		List<TabakDruhy> tabakDruhy = new LinkedList<TabakDruhy>(Arrays.asList(TabakDruhy.values())); //názvy cigaret
		TabakDruhy vybranyTabak = tabakDruhy.get(random.nextInt(tabakDruhy.size())); //náhodnì vyberu jeden z tabákù
		
		while(vybranyTabak.getRarity()>rarity);
		{
			System.out.println(rarity);
			tabakDruhy.remove(vybranyTabak);
			vybranyTabak = tabakDruhy.get(random.nextInt(tabakDruhy.size()));
		}
		
		String nazev = vybranyTabak.getNazev();
		int hodnota = vybranyTabak.getHodnota();
		int hodnotaObnovy = vybranyTabak.getHodnotaObnovy();
		String typObnovy = "nikotin";
		String popis = vybranyTabak.getPopis();
		int actualRarity = vybranyTabak.getRarity();
		
		Jidlo tabak = new Jidlo(nazev, hodnota, hodnotaObnovy, typObnovy, popis, actualRarity);
		return tabak;
	}
	public ArrayList<Jidlo> generujTabaky(int pocet) //generuje zadaný poèet tabáku
	{
		ArrayList<Jidlo> tabak = new ArrayList<>();
		for(int i = 0; i<pocet; i++)
		{
			tabak.add(generujTabak(rarityRoll()));
		}
		return tabak;
	}
	public Odpad generujOdpad(int rarity) //vrátí jeden náhodný odpad
	{
		List<OdpadDruhy> odpadDruhy = new LinkedList<OdpadDruhy>(Arrays.asList(OdpadDruhy.values()));	//názvy odpadu
		OdpadDruhy odpadEnum = odpadDruhy.get(random.nextInt(odpadDruhy.size()));
		
		while(odpadEnum.getRarity()>rarity);
		{
			odpadDruhy.remove(odpadEnum);
			odpadEnum = odpadDruhy.get(random.nextInt(odpadDruhy.size()));
		}
			
		String nazev = odpadEnum.getNazev();
		int hodnota = odpadEnum.getHodnota();
		String popis = odpadEnum.getPopis();
		int actualRarity = odpadEnum.getRarity();
		
		Odpad odpad = new Odpad(nazev, hodnota, popis, actualRarity);
		
		return odpad;
	}
	public ArrayList<Odpad> generujOdpady(int pocet) //navrátí zadaný poèet odpadù
	{
		ArrayList<Odpad> odpad = new ArrayList<>();
		for(int i = 0; i<pocet; i++)
		{
			odpad.add(generujOdpad(rarityRoll()));
		}
		return odpad;
	}
	public Klic generujKlic(int ID) //vrátí klíè se zadaným ID
	{
		try
		{
			KlicDruhy klicI = KlicDruhy.getPodleID(ID);
			Klic klic = new Klic(klicI.getNazev(),klicI.getHodnota(),klicI.getID(), klicI.getPopis());
			return klic;
		}
		catch(NullPointerException e)
		{
			System.out.println("Chybné ID pøi generování klíèe! ID: "+ID);
		}
		Klic neexistujiciKlic = new Klic("Klíè od neznámých dveøí", 0, ID, "Øíká se, že za tìmito dveømi leží svìt, ve kterém ženy vìdí, co chtìjí.");
		return neexistujiciKlic;
	}
	private int rarityRoll()
	{
		int rarity = random.nextInt(11);
		if(rarity == 0)
		{
			rarity = 1;
		}
		return rarity;
	}
}