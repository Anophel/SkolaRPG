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
		INositelne tvujItem = getVybranyItem(); //uložím item hráèe
		INositelne nepriteluvItem = nepritel.getBatohKBoji().getObsah().get(random.nextInt(nepritel.getBatohKBoji().getObsah().size()));
		boolean obaNazivu = true;
		try
		{
			if(tvujItem.getTypPredmetu() == TypPredmetu.ZBRAN && nepriteluvItem.getTypPredmetu() == TypPredmetu.ZBRAN) //pokud se potkají dvì zbranì
			{
				Zbran tvojeZbran = (Zbran) tvujItem;
				Zbran nepritelovaZbran = (Zbran) nepriteluvItem;
				boolean youWin = compareZbrane(tvojeZbran, nepritelovaZbran);
				if(youWin)	//pokud tvoje pøebije jeho
				{
					int dmg = tvojeZbran.getUtok()-nepritelovaZbran.getObrana();
					if(dmg>0)
					{
						nepritel.injured(dmg);
						setReport("Zranil jsi "+nepritel.getJmeno()+" itemem "+tvojeZbran.getNazev()+" za "+dmg+" poškození!");
					}
					else
					{
						setReport("Použil jsi zbraò "+tvojeZbran.getNazev()+", ale "+nepritel.getJmeno()+" odrazil tvou ránu itemem "+nepritelovaZbran.getNazev()+"!");
					}
				}
				else	//pokud je tvoje slabšího typu
				{
					int dmg = nepritelovaZbran.getUtok()-tvojeZbran.getObrana();
					if(dmg>0)
					{
						tvojePostava.injured(dmg);
						setReport(nepritel.getJmeno()+" tì zranil itemem "+nepritelovaZbran.getNazev()+" za "+dmg+" poškození!");
					}
					else
					{
						setReport(nepritel.getJmeno()+" použil zbraò "+nepritelovaZbran.getNazev()+", ale podaøilo se ti jeho útok odrazit itemem "+tvojeZbran.getNazev()+"!");
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
					setReport("Zranil jsi "+nepritel.getJmeno()+" itemem "+tvojeZbran.getNazev()+" za "+tvojeZbran.getUtok()+", ale "+nepritel.getJmeno()+" se vyléèil itemem "+nepritelovoJidlo.getNazev()+" za "+nepritelovoJidlo.getHodnotaObnovy()+"!");
				}
				else
				{
					setReport("Zranil jsi "+nepritel.getJmeno()+" itemem "+tvojeZbran.getNazev()+" za "+tvojeZbran.getUtok()+" poškození!");
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
					setReport(nepritel.getJmeno()+" zranil tì zranil itemem "+nepritelovaZbran.getNazev()+" za "+nepritelovaZbran.getUtok()+", ale vyléèil ses itemem "+tvojeJidlo.getNazev()+" za "+tvojeJidlo.getHodnotaObnovy()+"!");
				}
				else
				{
					setReport(nepritel.getJmeno()+" tì zranil itemem "+nepritelovaZbran.getNazev()+" za "+nepritelovaZbran.getUtok()+" poškození!");
				}
			}
			else if(tvujItem.getTypPredmetu() == TypPredmetu.JIDLO && nepriteluvItem.getTypPredmetu() == TypPredmetu.JIDLO)
			{
				tvojePostava.eat(tvujItem);
				nepritel.eat(nepriteluvItem);
				setReport("Oba jste si dali nìco k jídlu!");
			}
			else
			{
				System.out.println("Neznámá kombinace v metodì fight()!");
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
			System.out.println("Nebyla vybrána žádná zbraò!");
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
			System.out.println("Neznámá kombinace typù útokù: "+tvojeZbran.getTypUtoku()+" vs. "+nepritelovaZbran.getTypUtoku());
		}
			
		return tvojeJeLepsi;
	}
}
