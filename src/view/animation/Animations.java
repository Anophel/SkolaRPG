package view.animation;

import java.util.ArrayList;

public enum Animations 
{
	REDMAN_RELAX(new ImageWithSource("/view/img/Redman.png")),
	REDMAN_LEFT(new ImageWithSource("/view/img/Redman.png")),
	REDMAN_RIGHT(new ImageWithSource("/view/img/Redman.png")),
	REDMAN_UP(new ImageWithSource("/view/img/Redman.png")),
	REDMAN_DOWN(new ImageWithSource("/view/img/Redman.png")),
	
	BLONDE_RELAX(new ImageWithSource("/view/img/cumbova.png")),
	BLONDE_LEFT(new ImageWithSource("/view/img/cumbova.png")),
	BLONDE_RIGHT(new ImageWithSource("/view/img/cumbova.png")),
	BLONDE_UP(new ImageWithSource("/view/img/cumbova.png")),
	BLONDE_DOWN(new ImageWithSource("/view/img/cumbova.png")),
	
	HERO_RELAX(new ImageWithSource("/view/img/relax1.png"), new ImageWithSource("/view/img/relax2.png"), new ImageWithSource("/view/img/relax3.png")),
	HERO_LEFT(new ImageWithSource("/view/img/left1.png"), new ImageWithSource("/view/img/left2.png"), new ImageWithSource("/view/img/left3.png")),
	HERO_RIGHT(new ImageWithSource("/view/img/right1.png"), new ImageWithSource("/view/img/right2.png"), new ImageWithSource("/view/img/right3.png")),
	HERO_UP(new ImageWithSource("/view/img/up1.png"), new ImageWithSource("/view/img/up2.png"), new ImageWithSource("/view/img/up3.png")),
	HERO_DOWN(new ImageWithSource("/view/img/down1.png"), new ImageWithSource("/view/img/down2.png"), new ImageWithSource("/view/img/down3.png"));
	
	private ArrayList<ImageWithSource> animace = new ArrayList<ImageWithSource>();
	
	Animations(ImageWithSource... imgs)
	{
		for(ImageWithSource image:imgs)
		{
			this.animace.add(image);
		}
	}
	public ArrayList<ImageWithSource> getAnimace()
	{
		return animace;
	}
}
