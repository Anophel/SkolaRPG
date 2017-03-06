package view.animation;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import model.Pozice;
import model.characters.Characters;

public class Avatar extends AnimatedCharacter
{
	public Avatar(ImageWithSource defaultImage) 
	{
		super(defaultImage);
	}
	public Avatar(ImageWithSource defaultImage, ArrayList<ImageWithSource> relax, ArrayList<ImageWithSource> left,
			ArrayList<ImageWithSource> right, ArrayList<ImageWithSource> up, ArrayList<ImageWithSource> down) 
	{
		super(defaultImage, relax, left, right, up, down);
	}
	public Avatar(Characters character)
	{
		super(character);
	}
	
	
	public void center(Canvas canvas)
	{
		pozice = new Pozice(canvas.getWidth()/2 - image.getWidth()/2,canvas.getHeight()/2 - image.getHeight()/2);
	}
	public boolean outOfCenterX(Canvas canvas)
	{
		if(Math.abs((canvas.getWidth()/2 - image.getWidth()/2)-pozice.getXPoz())>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean outOfCenterY(Canvas canvas)
	{
		if(Math.abs((canvas.getHeight()/2 - image.getHeight()/2)-pozice.getYPoz())>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public void update(double time, Canvas canvas)
	{
		double oldXpoz = pozice.getXPoz();
		double oldYpoz = pozice.getYPoz();
		double centerX = canvas.getWidth()/2 - image.getWidth()/2;
		double centerY = canvas.getHeight()/2 - image.getHeight()/2;
		
		double newXpoz = oldXpoz + time*Xvelocity;
		double newYpoz = oldYpoz + time*Yvelocity;
		
		//kontrola, jestli nejsem mimo mapu vlevo a vpravo
		if(newXpoz<=0)
		{
			newXpoz = 0;
		}
		else if(newXpoz>=(canvas.getWidth()-image.getWidth()))
		{
			newXpoz = canvas.getWidth()-image.getWidth();
		}
		
		//kontrola, jestli nejsem mimo mapu nahoøe a dole
		if(newYpoz<=0)
		{
			newYpoz = 0;
		}
		else if(newYpoz>=(canvas.getHeight()-image.getHeight()))
		{
			newYpoz = canvas.getHeight()-image.getHeight();
		}
		
		//kontrola, jestli nový krok nepøekroèí støed
		if(oldXpoz < centerX && newXpoz >= centerX)
		{
			newXpoz = centerX;
		}
		else if(oldXpoz > centerX && newXpoz <= centerX)
		{
			newXpoz = centerX;
		}
		if(oldYpoz < centerY && newYpoz >= centerY)
		{
			newYpoz = centerY;
		}
		else if(oldYpoz > centerY && newYpoz <= centerY)
		{
			newYpoz = centerY;
		}
		
		pozice = new Pozice(newXpoz,newYpoz);
	}
	public void update(Canvas canvas)
	{
		double centerX = canvas.getWidth()/2 - image.getWidth()/2;
		double centerY = canvas.getHeight()/2 - image.getHeight()/2;
		
		double X = 0;
		double Y = 0;
		
		if(outOfCenterX(canvas) && outOfCenterY(canvas))
		{
			X = pozice.getXPoz();
			Y = pozice.getYPoz();
		}
		else if(outOfCenterX(canvas))
		{
			X = pozice.getXPoz();
			Y = centerY;
		}
		else if(outOfCenterY(canvas))
		{
			X = centerX;
			Y = pozice.getYPoz();
		}
		else
		{
			X = centerX;
			Y = centerY;
		}
		
		//poslední kontrola, jestli jsem se nedostal mimo okno
		if(X<=0)
		{
			X = 0;
		}
		else if(X>=(canvas.getWidth()-image.getWidth()))
		{
			X = canvas.getWidth()-image.getWidth();
		}
		
		if(Y<=0)
		{
			Y = 0;
		}
		else if(Y>=(canvas.getHeight()-image.getHeight()))
		{
			Y = canvas.getHeight()-image.getHeight();
		}
		
		pozice = new Pozice(X,Y);
	}
}