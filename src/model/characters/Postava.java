package model.characters;

import model.Batoh;
import model.items.INositelne;
import model.items.Klic;
import model.items.generator.KlicDruhy;
import view.animation.AnimatedCharacter;

public abstract class Postava 
{
	private String jmeno;
	private Batoh batoh;
	private static final String JMENO = "Bojovník beze jména";
	private AnimatedCharacter animatedCharacter;
	
	public Postava()
	{
		this(JMENO);
	}
	public Postava(String jmeno)
	{
		this(jmeno, new Batoh(10));
	}
	public Postava(String jmeno, Batoh batoh) 
	{
		this.jmeno = jmeno;
		this.batoh = batoh;
	}
	public Postava(String jmeno, AnimatedCharacter anim, KlicDruhy klic)
	{
		this.jmeno = jmeno;
		this.animatedCharacter = anim;
		this.batoh = new Batoh(10);
		batoh.add(new Klic(klic));
	}
	
	public void setAnimatedCharacter(AnimatedCharacter avatar)
	{
		this.animatedCharacter = avatar;
	}
	public AnimatedCharacter getAnimatedCharacter()
	{
		return animatedCharacter;
	}
	public void setBatoh(Batoh batoh)
	{
		this.batoh = batoh;
	}
	public String getJmeno()
	{
		return jmeno;
	}
	public Batoh getBatoh()
	{
		return batoh;
	}
	public Batoh getBatohKBoji()
	{
		Batoh boj = new Batoh(0);
		boj.addAll(batoh.getJidlo());
		boj.addAll(batoh.getZbrane());
		return boj;
	}
	public abstract double getHP();
	public abstract void injured(double dmg);
	public abstract void eat(INositelne jidlo);
	public abstract void addToBatoh(INositelne predmet);
}
