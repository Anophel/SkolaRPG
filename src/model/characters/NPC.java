package model.characters;

import model.Batoh;
import model.items.INositelne;
import model.items.Jidlo;

public class NPC extends Postava
{
	private static String JMENO = "Neznámé NPC";
	private static double defHP = 100;
	
	private double HP;
	
	public NPC()
	{
		this(JMENO);
	}
	public NPC(String jmeno)
	{
		this(jmeno, new Batoh(10));
	}
	public NPC(String jmeno, Batoh batoh)
	{
		this(jmeno, batoh, defHP);
	}
	public NPC(String jmeno, Batoh batoh, double HP)
	{
		super(jmeno,batoh);
		this.HP = HP;
	}
	public NPC(NPCs npc)
	{
		super(npc.getJmeno(),npc.getAnimatedCharacter(), npc.getKlic());
		this.HP = defHP;
	}
	
	@Override
	public double getHP(){return HP;}
	public void setHP(double HP){this.HP = HP;}
	@Override
	public void injured(double dmg)
	{
		if(getHP()>dmg)
		{
			setHP(getHP()-dmg);
		}
		else
		{
			setHP(0);
		}
	}
	@Override
	public void eat(INositelne item) 
	{
		Jidlo jidlo = (Jidlo) item;
		setHP(getHP()+jidlo.getHodnotaObnovy());
	}
	@Override
	public void addToBatoh(INositelne item) 
	{
		Batoh batoh = getBatoh();
		batoh.add(item);
		setBatoh(batoh);
	}
}