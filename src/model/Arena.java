package model;
 
import java.util.Random;
import model.characters.Postava;
import model.items.INositelne;
import model.items.Jidlo;
import model.items.TypPredmetu;
import model.items.TypUtoku;
import model.items.Zbran;

public class Arena 
{	
	private Postava tvojePostava;
	private Postava nepritel;
	private INositelne vybranyItem;
	private Random random = new Random();
	private String report = "";
	
	public void setTvojePostava(Postava ty)
	{
		this.tvojePostava = ty;
	}
	public void setNepritel(Postava nepritel)
	{
		this.nepritel = nepritel;
	}
	public boolean fight()
	{
		INositelne tvujItem = getVybranyItem(); //ulo��m item hr��e
		INositelne nepriteluvItem = nepritel.getBatohKBoji().getObsah().get(random.nextInt(nepritel.getBatohKBoji().getObsah().size()));
		boolean obaNazivu = true;
		try
		{
			if(tvujItem.getTypPredmetu() == TypPredmetu.ZBRAN && nepriteluvItem.getTypPredmetu() == TypPredmetu.ZBRAN) //pokud se potkaj� dv� zbran�
			{
				Zbran tvojeZbran = (Zbran) tvujItem;
				Zbran nepritelovaZbran = (Zbran) nepriteluvItem;
				boolean youWin = compareZbrane(tvojeZbran, nepritelovaZbran);
				if(youWin)	//pokud tvoje p�ebije jeho
				{
					int dmg = tvojeZbran.getUtok()-nepritelovaZbran.getObrana();
					if(dmg>0)
					{
						nepritel.injured(dmg);
						setReport("Zranil jsi "+nepritel.getJmeno()+" itemem "+tvojeZbran.getNazev()+" za "+dmg+" po�kozen�!");
					}
					else
					{
						setReport("Pou�il jsi zbra� "+tvojeZbran.getNazev()+", ale "+nepritel.getJmeno()+" odrazil tvou r�nu itemem "+nepritelovaZbran.getNazev()+"!");
					}
				}
				else	//pokud je tvoje slab��ho typu
				{
					int dmg = nepritelovaZbran.getUtok()-tvojeZbran.getObrana();
					if(dmg>0)
					{
						tvojePostava.injured(dmg);
						setReport(nepritel.getJmeno()+" t� zranil itemem "+nepritelovaZbran.getNazev()+" za "+dmg+" po�kozen�!");
					}
					else
					{
						setReport(nepritel.getJmeno()+" pou�il zbra� "+nepritelovaZbran.getNazev()+", ale poda�ilo se ti jeho �tok odrazit itemem "+tvojeZbran.getNazev()+"!");
					}
				}
			}
			else if(tvujItem.getTypPredmetu() == TypPredmetu.ZBRAN && nepriteluvItem.getTypPredmetu() == TypPredmetu.JIDLO)
			{
				Zbran tvojeZbran = (Zbran) tvujItem;
				Jidlo nepritelovoJidlo = (Jidlo) nepriteluvItem;
				nepritel.injured(tvojeZbran.getUtok());
				if(nepritel.getHP()>0)
				{
					nepritel.eat(nepriteluvItem);
					setReport("Zranil jsi "+nepritel.getJmeno()+" itemem "+tvojeZbran.getNazev()+" za "+tvojeZbran.getUtok()+", ale "+nepritel.getJmeno()+" se vyl��il itemem "+nepritelovoJidlo.getNazev()+" za "+nepritelovoJidlo.getHodnotaObnovy()+"!");
				}
				else
				{
					setReport("Zranil jsi "+nepritel.getJmeno()+" itemem "+tvojeZbran.getNazev()+" za "+tvojeZbran.getUtok()+" po�kozen�!");
				}
			}
			else if(tvujItem.getTypPredmetu() == TypPredmetu.JIDLO && nepriteluvItem.getTypPredmetu() == TypPredmetu.ZBRAN)
			{
				Zbran nepritelovaZbran = (Zbran) nepriteluvItem;
				Jidlo tvojeJidlo = (Jidlo) tvujItem;
				tvojePostava.injured(nepritelovaZbran.getUtok());
				if(tvojePostava.getHP()>0)
				{
					tvojePostava.eat(tvujItem);
					setReport(nepritel.getJmeno()+" zranil t� zranil itemem "+nepritelovaZbran.getNazev()+" za "+nepritelovaZbran.getUtok()+", ale vyl��il ses itemem "+tvojeJidlo.getNazev()+" za "+tvojeJidlo.getHodnotaObnovy()+"!");
				}
				else
				{
					setReport(nepritel.getJmeno()+" t� zranil itemem "+nepritelovaZbran.getNazev()+" za "+nepritelovaZbran.getUtok()+" po�kozen�!");
				}
			}
			else if(tvujItem.getTypPredmetu() == TypPredmetu.JIDLO && nepriteluvItem.getTypPredmetu() == TypPredmetu.JIDLO)
			{
				tvojePostava.eat(tvujItem);
				nepritel.eat(nepriteluvItem);
				setReport("Oba jste si dali n�co k j�dlu!");
			}
			else
			{
				System.out.println("Nezn�m� kombinace v metod� fight()!");
			}
			tvujItem.setTrvanlivost(tvujItem.getTrvanlivost()-1);
			nepriteluvItem.setTrvanlivost(nepriteluvItem.getTrvanlivost()-1);
			if(tvujItem.getTrvanlivost()<=0)
			{
				tvojePostava.getBatoh().getObsah().remove(tvujItem);
			}
			if(nepriteluvItem.getTrvanlivost()<=0)
			{
				nepritel.getBatoh().getObsah().remove(nepriteluvItem);
			}
			
			if(nepritel.getHP()>0 && tvojePostava.getHP()>0)
			{
				obaNazivu = true;
			}
			else
			{
				obaNazivu = false;
			}
		}
		catch(Exception ex)
		{
			System.out.println("Nebyla vybr�na ��dn� zbra�!");
		}
		return obaNazivu;
	}
	public void setVybranyItem(INositelne item)
	{
		this.vybranyItem = item;
	}
	public String getReport()
	{
		return report;
	}
	private void setReport(String text)
	{
		this.report = text;
	}
	public INositelne getVybranyItem()
	{
		return vybranyItem;
	}
	private boolean compareZbrane(Zbran tvojeZbran, Zbran nepritelovaZbran)
	{
		boolean tvojeJeLepsi = true;
		if(tvojeZbran.getTypUtoku() == TypUtoku.BLIZKY && nepritelovaZbran.getTypUtoku() == TypUtoku.DALEKY)
		{
			tvojeJeLepsi = false;
		}
		else if(tvojeZbran.getTypUtoku() == TypUtoku.BLIZKY && nepritelovaZbran.getTypUtoku() == TypUtoku.OBRANNY)
		{
			tvojeJeLepsi = true;
		}
		else if(tvojeZbran.getTypUtoku() == TypUtoku.BLIZKY && nepritelovaZbran.getTypUtoku() == TypUtoku.BLIZKY)
		{
			if(tvojeZbran.getObrana()>=nepritelovaZbran.getObrana())
			{
				tvojeJeLepsi = true;
			}
			else
			{
				tvojeJeLepsi = false;
			}
		}
		else if(tvojeZbran.getTypUtoku() == TypUtoku.DALEKY && nepritelovaZbran.getTypUtoku() == TypUtoku.OBRANNY)
		{
			tvojeJeLepsi = false;
		}
		else if(tvojeZbran.getTypUtoku() == TypUtoku.DALEKY && nepritelovaZbran.getTypUtoku() == TypUtoku.BLIZKY)
		{
			tvojeJeLepsi = true;
		}
		else if(tvojeZbran.getTypUtoku() == TypUtoku.DALEKY && nepritelovaZbran.getTypUtoku() == TypUtoku.DALEKY)
		{
			if(tvojeZbran.getObrana()>=nepritelovaZbran.getObrana())
			{
				tvojeJeLepsi = true;
			}
			else
			{
				tvojeJeLepsi = false;
			}
		}
		else if(tvojeZbran.getTypUtoku() == TypUtoku.OBRANNY && nepritelovaZbran.getTypUtoku() == TypUtoku.DALEKY)
		{
			tvojeJeLepsi = true;
		}
		else if(tvojeZbran.getTypUtoku() == TypUtoku.OBRANNY && nepritelovaZbran.getTypUtoku() == TypUtoku.BLIZKY)
		{
			tvojeJeLepsi = false;
		}
		else if(tvojeZbran.getTypUtoku() == TypUtoku.OBRANNY && nepritelovaZbran.getTypUtoku() == TypUtoku.OBRANNY)
		{
			if(tvojeZbran.getObrana()>=nepritelovaZbran.getObrana())
			{
				tvojeJeLepsi = true;
			}
			else
			{
				tvojeJeLepsi = false;
			}
		}
		else
		{
			System.out.println("Nezn�m� kombinace typ� �tok�: "+tvojeZbran.getTypUtoku()+" vs. "+nepritelovaZbran.getTypUtoku());
		}
			
		return tvojeJeLepsi;
	}
}
