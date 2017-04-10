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
	
	HERO_RELAX(new ImageWithSource("/view/img/hero/down1.png")),
	HERO_LEFT(new ImageWithSource("/view/img/hero/left1.png"), new ImageWithSource("/view/img/hero/left2.png"), new ImageWithSource("/view/img/hero/left3.png"), new ImageWithSource("/view/img/hero/left4.png"), new ImageWithSource("/view/img/hero/left5.png")),
	HERO_RIGHT(new ImageWithSource("/view/img/hero/right1.png"), new ImageWithSource("/view/img/hero/right2.png"), new ImageWithSource("/view/img/hero/right3.png"), new ImageWithSource("/view/img/hero/right4.png"), new ImageWithSource("/view/img/hero/right5.png")),
	HERO_UP(new ImageWithSource("/view/img/hero/up1.png"), new ImageWithSource("/view/img/hero/up2.png"), new ImageWithSource("/view/img/hero/up3.png"), new ImageWithSource("/view/img/hero/up4.png"), new ImageWithSource("/view/img/hero/up5.png")),
	HERO_DOWN(new ImageWithSource("/view/img/hero/down1.png"), new ImageWithSource("/view/img/hero/down2.png"), new ImageWithSource("/view/img/hero/down3.png"), new ImageWithSource("/view/img/hero/down4.png"), new ImageWithSource("/view/img/hero/down5.png"));
	
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
