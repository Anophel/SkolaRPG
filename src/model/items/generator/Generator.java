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
	Random random = new Random(); //gener�tor n�hodn�ch ��sel
		
	public ArrayList<INositelne> generujBatoh(int pocet) //vr�t� list item� - Zbran, Jidlo
	{
		ArrayList<INositelne> vyber = new ArrayList<INositelne>(); //sem ulo��m vybran� itemy
		List<TypPredmetu> predmetyBatoh = Arrays.asList(TypPredmetu.ZBRAN, TypPredmetu.JIDLO); //seznam v�c�, kter� chceme generovat do batohu
		
		for(int i = 0; i<pocet;i++)
		{
			TypPredmetu vybranyTyp = predmetyBatoh.get(random.nextInt(predmetyBatoh.size())); //n�hodn� v�b�r typu p�edm�tu
			if(vybranyTyp == TypPredmetu.ZBRAN)
			{
				int rarity = rarityRoll();
				INositelne item = generujZbran(rarity);
				vyber.add(item); //novou zbra� p�id�m do listu
			}
			else if(vybranyTyp == TypPredmetu.JIDLO)
			{
				int rarity = rarityRoll();
				INositelne item = generujJidlo(rarity);
				vyber.add(item); //nov� j�dlo p�id�m do listu
			}
		}
		
		return vyber;
	}
	public ArrayList<INositelne> generujMapu(int pocet) //vr�t� list item� - Zbran, Jidlo, Odpad
	{
		/*
		 * pomoc� gener�toru n�hodn�ch ��sel si vyberu, kolik chci m�t na map�
		 * zbran� a j�dla, pro n� pou�iji ji� existuj�c� gener�tor pro batoh, kter� funguje stejn�
		 */
		int uzitecneItemy = random.nextInt(pocet);
		ArrayList<INositelne> vyber = generujBatoh(uzitecneItemy); //rovnou je ulo��m do seznamu, kter� posl�ze vr�t�m
		int zbytek = pocet - uzitecneItemy; //zjist�m, kolik item� "ODPAD" na map� bude
		for(int i = 0; i<zbytek; i++)
		{
			INositelne odpad = generujOdpad(rarityRoll());
			vyber.add(odpad); //nov� Odpad p�id�m do listu
		}
		return vyber;
	}
	public Zbran generujZbran(int rarity) //vr�t� jednu n�hodnou zbra�
	{
		List<TypUtoku> utoky = Arrays.asList(TypUtoku.values());	//mo�n� typy �tok�
		
		TypUtoku typUtoku = utoky.get(random.nextInt(utoky.size())); //vybral jsem n�hodn� typ �toku
		
		ZbraneInterface zbranI = Arrays.asList(ZbraneM.values()).get(random.nextInt(Arrays.asList(ZbraneM.values()).size())); //zalo��m Interface (mus� b�t inicializovan�, je mi jedno, jak� jsem ud�lal)
		
		if(typUtoku == TypUtoku.BLIZKY)
		{
			List<ZbraneM> zbraneMNazvy = new LinkedList<ZbraneM>(Arrays.asList(ZbraneM.values()));	//n�zvy zbran� na bl�zko
			boolean iterated = false;	//prob�hlo u� vyb�r�n�?
			do
			{
				//System.out.println(rarity);
				if(iterated)	//pokud prob�hlo vyb�r�n�
				{
					zbraneMNazvy.remove(zbranI);	//odeberu zbra�, kter� nepro�la
				}
				zbranI = zbraneMNazvy.get(random.nextInt(zbraneMNazvy.size()));  //v�b�r p�edm�tu ze Zbran
				iterated = true;	//vyb�r�n� u� prob�hlo
			}
			while(zbranI.getRarity()>rarity);	//pokud vybran� item neodpov�d� podm�nce, zkus�m to znovu
		} 
		else if(typUtoku == TypUtoku.DALEKY)
		{
			List<ZbraneR> zbraneRNazvy = new LinkedList<ZbraneR>(Arrays.asList(ZbraneR.values()));	//n�zvy zbran� na d�lku
			boolean iterated = false;
			do
			{
				//System.out.println(rarity);
				if(iterated)
				{
					zbraneRNazvy.remove(zbranI);
				}
				zbranI = zbraneRNazvy.get(random.nextInt(zbraneRNazvy.size())); //v�b�r p�em�tu z ZbraneR
				iterated = true;
			}
			while(zbranI.getRarity()>rarity);
		} 
		else
		{
			List<ZbraneD> zbraneDNazvy = new LinkedList<ZbraneD>(Arrays.asList(ZbraneD.values()));	//n�zvy obrann�ch zbran�
			boolean iterated = false;
			do
			{
				//System.out.println(rarity);
				if(iterated)
				{
					zbraneDNazvy.remove(zbranI);
				}
				zbranI = zbraneDNazvy.get(random.nextInt(zbraneDNazvy.size())); //v�b�r p�edm�tu ze ZbraneD
				iterated = true;
			}
			while(zbranI.getRarity()>rarity);
		}
		
		//napln�m v�echny pot�ebn� parametry pro vytvo�en� Zbran�
		String nazev = zbranI.getNazev();
		int hodnota = zbranI.getHodnota();
		int utok = zbranI.getUtok();
		int obrana = zbranI.getObrana();
		int trvanlivost = zbranI.getTrvanlivost();
		String popis = zbranI.getPopis();
		int actualRarity = zbranI.getRarity();
		
		Zbran zbran = new Zbran(nazev,hodnota,utok,obrana,trvanlivost,typUtoku, popis, actualRarity); //vytvo��m zbra�
		
		return zbran; //vr�t�m ji
	}
	public ArrayList<Zbran> generujZbrane(int pocet) //generuje zadan� po�et zbran�
	{
		ArrayList<Zbran> zbrane = new ArrayList<>();
		for(int i = 0; i<pocet; i++)
		{
			zbrane.add(generujZbran(rarityRoll()));
		}
		return zbrane;
	}
	public Jidlo generujJidlo(int rarity) //vr�t� jedno n�hodn� j�dlo
	{
		List<JidloDruhy> jidloDruhy = new LinkedList<JidloDruhy>(Arrays.asList(JidloDruhy.values()));	//n�zvy j�del
		JidloDruhy vybraneJidlo = jidloDruhy.get(random.nextInt(jidloDruhy.size()));
		
		while(vybraneJidlo.getRarity()>rarity)
		{
			jidloDruhy.remove(vybraneJidlo);
			vybraneJidlo = jidloDruhy.get(random.nextInt(jidloDruhy.size())); //n�hodn� vyberu jedno z j�del
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
	public ArrayList<Jidlo> generujJidla(int pocet) //generuje zadan� po�et j�dla
	{
		ArrayList<Jidlo> jidlo = new ArrayList<>();
		for(int i = 0; i<pocet; i++)
		{
			jidlo.add(generujJidlo(rarityRoll()));
		}
		return jidlo;
	}
	public Jidlo generujTabak(int rarity) //vygeneruje jeden n�hodn� tab�kov� v�robek
	{
		List<TabakDruhy> tabakDruhy = new LinkedList<TabakDruhy>(Arrays.asList(TabakDruhy.values())); //n�zvy cigaret
		TabakDruhy vybranyTabak = tabakDruhy.get(random.nextInt(tabakDruhy.size())); //n�hodn� vyberu jeden z tab�k�
		
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
	public ArrayList<Jidlo> generujTabaky(int pocet) //generuje zadan� po�et tab�ku
	{
		ArrayList<Jidlo> tabak = new ArrayList<>();
		for(int i = 0; i<pocet; i++)
		{
			tabak.add(generujTabak(rarityRoll()));
		}
		return tabak;
	}
	public Odpad generujOdpad(int rarity) //vr�t� jeden n�hodn� odpad
	{
		List<OdpadDruhy> odpadDruhy = new LinkedList<OdpadDruhy>(Arrays.asList(OdpadDruhy.values()));	//n�zvy odpadu
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
	public ArrayList<Odpad> generujOdpady(int pocet) //navr�t� zadan� po�et odpad�
	{
		ArrayList<Odpad> odpad = new ArrayList<>();
		for(int i = 0; i<pocet; i++)
		{
			odpad.add(generujOdpad(rarityRoll()));
		}
		return odpad;
	}
	public Klic generujKlic(int ID) //vr�t� kl�� se zadan�m ID
	{
		try
		{
			KlicDruhy klicI = KlicDruhy.getPodleID(ID);
			Klic klic = new Klic(klicI.getNazev(),klicI.getHodnota(),klicI.getID(), klicI.getPopis());
			return klic;
		}
		catch(NullPointerException e)
		{
			System.out.println("Chybn� ID p�i generov�n� kl��e! ID: "+ID);
		}
		Klic neexistujiciKlic = new Klic("Kl�� od nezn�m�ch dve��", 0, ID, "��k� se, �e za t�mito dve�mi le�� sv�t, ve kter�m �eny v�d�, co cht�j�.");
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