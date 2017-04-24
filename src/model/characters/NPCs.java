package model.characters;

import model.Pozice;
import model.items.generator.KlicDruhy;
import view.animation.AnimatedCharacter;

public enum NPCs 
{
	BLONDE("Blonde", Characters.BLONDE, new Pozice(2500,550), KlicDruhy.KLIC_ID0),
	REDMAN("Redman", Characters.REDMAN, new Pozice(1250, 450), KlicDruhy.KLIC_ID1);
	
	private String jmeno;
	private AnimatedCharacter animatedCharacter;
	private KlicDruhy klic;
	
	NPCs(String jmeno, Characters character, Pozice pozice, KlicDruhy klic)
	{
		this.jmeno = jmeno;
		animatedCharacter = new AnimatedCharacter(character);
		animatedCharacter.setPozice(pozice);
		this.klic = klic;
	}
	public String getJmeno()
	{
		return jmeno;
	}
	public AnimatedCharacter getAnimatedCharacter()
	{
		return animatedCharacter;
	}
	public KlicDruhy getKlic()
	{
		return klic;
	}
}
