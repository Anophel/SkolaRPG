package view.animation.levels;

import java.util.ArrayList;

import model.Pozice;
import model.characters.NPC;
import model.characters.NPCs;
import model.characters.Postava;
import model.items.Klic;
import model.items.generator.KlicDruhy;
import view.animation.ImageWithSource;

public enum Maps 
{
	CHODBA_1(new ImageWithSource("/view/img/maps/chodba1.png"), new ImageWithSource("/view/img/maps/pozadi1.png"), true, new Pozice(0,-500), new Klic(KlicDruhy.KLIC_ID0), new NPC(NPCs.BLONDE)),
	CHODBA_2(new ImageWithSource("/view/img/maps/chodba2.png"), new ImageWithSource("/view/img/maps/pozadi2.png"), true, new Pozice(0, -500), new Klic(KlicDruhy.KLIC_ID1), new NPC(NPCs.REDMAN));
	
	private ImageWithSource image;
	private ImageWithSource pozadi;
	private boolean vodorovne;
	private Pozice pozice;
	private ArrayList<Postava> enemies = new ArrayList<Postava>();
	private Klic releaseKey;
	
	Maps(ImageWithSource image, ImageWithSource pozadi, boolean vodorovne, Pozice pozice, Klic klic, Postava...postavy)
	{
		this.image = image;
		this.pozadi = pozadi;
		this.vodorovne = vodorovne;
		this.pozice = pozice;
		this.releaseKey = klic;
		for(Postava postava:postavy)
		{
			enemies.add(postava);
		}
	}
	public ImageWithSource getImage()
	{
		return image;
	}
	public ImageWithSource getPozadi()
	{
		return pozadi;
	}
	public boolean getVodorovne()
	{
		return vodorovne;
	}
	public Pozice getPozice()
	{
		return pozice;
	}
	public ArrayList<Postava> getEnemies()
	{
		return enemies;
	}
	public Klic getReleaseKey()
	{
		return releaseKey;
	}
}
