package view.animation;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import model.Pozice;
import model.characters.Characters;

public class Enemy extends AnimatedCharacter
{
	public Enemy(ImageWithSource defaultImage) 
	{
		super(defaultImage);
	}
	public Enemy(ImageWithSource defaultImage, ArrayList<ImageWithSource> relax, ArrayList<ImageWithSource> left,
			ArrayList<ImageWithSource> right, ArrayList<ImageWithSource> up, ArrayList<ImageWithSource> down) 
	{
		super(defaultImage, relax, left, right, up, down);
	}
	public Enemy(Characters character)
	{
		super(character);
	}
	

	@Override
	public void update(double time, Canvas canvas) 
	{
		double newXpoz = pozice.getXPoz();
		double newYpoz = pozice.getYPoz();
		
		newXpoz += time*Xvelocity;
		newYpoz += time*Yvelocity;
		
		pozice = new Pozice(newXpoz,newYpoz);
	}
}
