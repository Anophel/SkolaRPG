package model.characters;

import java.util.ArrayList;

import view.animation.Animations;
import view.animation.ImageWithSource;

public enum Characters 
{
	REDMAN(new ImageWithSource("/view/img/Redman.png"),Animations.REDMAN_RELAX.getAnimace(),Animations.REDMAN_LEFT.getAnimace(),Animations.REDMAN_RIGHT.getAnimace(), Animations.REDMAN_UP.getAnimace(), Animations.REDMAN_DOWN.getAnimace()),
	BLONDE(new ImageWithSource("/view/img/cumbova.png"),Animations.BLONDE_RELAX.getAnimace(),Animations.BLONDE_LEFT.getAnimace(),Animations.BLONDE_RIGHT.getAnimace(),Animations.BLONDE_UP.getAnimace(),Animations.BLONDE_DOWN.getAnimace()),
	HERO(new ImageWithSource("/view/img/postava1.jpg"),Animations.HERO_RELAX.getAnimace(),Animations.HERO_LEFT.getAnimace(),Animations.HERO_RIGHT.getAnimace(),Animations.HERO_UP.getAnimace(),Animations.HERO_DOWN.getAnimace());
	
	private ImageWithSource image;
	private ArrayList<ImageWithSource> relaxSeq = new ArrayList<ImageWithSource>();
	private ArrayList<ImageWithSource> leftSeq = new ArrayList<ImageWithSource>();
	private ArrayList<ImageWithSource> rightSeq = new ArrayList<ImageWithSource>();
	private ArrayList<ImageWithSource> upSeq = new ArrayList<ImageWithSource>();
	private ArrayList<ImageWithSource> downSeq = new ArrayList<ImageWithSource>();
	
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
