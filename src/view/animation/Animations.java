package view.animation;

import java.util.ArrayList;

public enum Animations 
{
	REDMAN_RELAX(new ImageWithSource("/view/img/Redman.png", 400, 400, true, true)),
	REDMAN_LEFT(new ImageWithSource("/view/img/Redman.png", 400, 400, true, true)),
	REDMAN_RIGHT(new ImageWithSource("/view/img/Redman.png", 400, 400, true, true)),
	REDMAN_UP(new ImageWithSource("/view/img/Redman.png", 400, 400, true, true)),
	REDMAN_DOWN(new ImageWithSource("/view/img/Redman.png", 400, 400, true, true)),
	
	BLONDE_RELAX(new ImageWithSource("/view/img/cumbova.png", 400, 400, true, true)),
	BLONDE_LEFT(new ImageWithSource("/view/img/cumbova.png", 400, 400, true, true)),
	BLONDE_RIGHT(new ImageWithSource("/view/img/cumbova.png", 400, 400, true, true)),
	BLONDE_UP(new ImageWithSource("/view/img/cumbova.png", 400, 400, true, true)),
	BLONDE_DOWN(new ImageWithSource("/view/img/cumbova.png", 400, 400, true, true)),
	
	HERO_RELAX(new ImageWithSource("/view/img/hero/down1.png", 400, 400, true, true)),
	HERO_LEFT(new ImageWithSource("/view/img/hero/left1.png", 400, 400, true, true),new ImageWithSource("/view/img/hero/left2.png", 400, 400, true, true), new ImageWithSource("/view/img/hero/left3.png", 400, 400, true, true), new ImageWithSource("/view/img/hero/left4.png", 400, 400, true, true), new ImageWithSource("/view/img/hero/left5.png", 400, 400, true, true)),
	HERO_RIGHT(new ImageWithSource("/view/img/hero/right1.png", 400, 400, true, true), new ImageWithSource("/view/img/hero/right2.png", 400, 400, true, true), new ImageWithSource("/view/img/hero/right3.png", 400, 400, true, true), new ImageWithSource("/view/img/hero/right4.png", 400, 400, true, true), new ImageWithSource("/view/img/hero/right5.png", 400, 400, true, true)),
	HERO_UP(new ImageWithSource("/view/img/hero/up1.png", 400, 400, true, true), new ImageWithSource("/view/img/hero/up2.png", 400, 400, true, true), new ImageWithSource("/view/img/hero/up3.png", 400, 400, true, true), new ImageWithSource("/view/img/hero/up4.png", 400, 400, true, true), new ImageWithSource("/view/img/hero/up5.png", 400, 400, true, true)),
	HERO_DOWN(new ImageWithSource("/view/img/hero/down1.png", 400, 400, true, true), new ImageWithSource("/view/img/hero/down2.png", 400, 400, true, true), new ImageWithSource("/view/img/hero/down3.png", 400, 400, true, true), new ImageWithSource("/view/img/hero/down4.png", 400, 400, true, true), new ImageWithSource("/view/img/hero/down5.png", 400, 400, true, true));
	
	private ArrayList<ImageWithSource> animace = new ArrayList<ImageWithSource>();
	
	Animations(ImageWithSource... imgs)
	{
		for(ImageWithSource image:imgs)
		{
			animace.add(image);
		}
	}
	public ArrayList<ImageWithSource> getAnimace()
	{
		return animace;
	}
}
