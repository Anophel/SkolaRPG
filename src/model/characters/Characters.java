package model.characters;

import java.util.ArrayList;

import view.animation.ImageWithSource;
import view.animation.Animations;

public enum Characters
{
	REDMAN(new ImageWithSource("/view/img/Redman.png", 400, 400, true, true),Animations.REDMAN_RELAX.getAnimace(),Animations.REDMAN_LEFT.getAnimace(),Animations.REDMAN_RIGHT.getAnimace(), Animations.REDMAN_UP.getAnimace(), Animations.REDMAN_DOWN.getAnimace()),
	BLONDE(new ImageWithSource("/view/img/cumbova.png", 400, 400, true, true),Animations.BLONDE_RELAX.getAnimace(),Animations.BLONDE_LEFT.getAnimace(),Animations.BLONDE_RIGHT.getAnimace(),Animations.BLONDE_UP.getAnimace(),Animations.BLONDE_DOWN.getAnimace()),
	HERO(new ImageWithSource("/view/img/hero/down1.png", 400, 400, true, true),Animations.HERO_RELAX.getAnimace(),Animations.HERO_LEFT.getAnimace(),Animations.HERO_RIGHT.getAnimace(),Animations.HERO_UP.getAnimace(),Animations.HERO_DOWN.getAnimace());
	
	private ImageWithSource image;
	private ArrayList<ImageWithSource> relaxSeq;
	private ArrayList<ImageWithSource> leftSeq;
	private ArrayList<ImageWithSource> rightSeq;
	private ArrayList<ImageWithSource> upSeq;
	private ArrayList<ImageWithSource> downSeq;

	Characters(ImageWithSource image,ArrayList<ImageWithSource> relax, ArrayList<ImageWithSource> left, 
						ArrayList<ImageWithSource> right, ArrayList<ImageWithSource> up, ArrayList<ImageWithSource> down)
	{		
		this.image = image;
		this.relaxSeq = relax;
		this.leftSeq = left;
		this.rightSeq = right;
		this.upSeq = up;
		this.downSeq = down;
	}
	public ImageWithSource getImage()
	{
		return image;
	}
	public ArrayList<ImageWithSource> getRelax()
	{
		return relaxSeq;
	}
	public ArrayList<ImageWithSource> getLeft()
	{
		return leftSeq;
	}
	public ArrayList<ImageWithSource> getRight()
	{
		return rightSeq;
	}
	public ArrayList<ImageWithSource> getUp()
	{
		return upSeq;
	}
	public ArrayList<ImageWithSource> getDown()
	{
		return downSeq;
	}
}
