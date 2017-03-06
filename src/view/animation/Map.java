package view.animation;

import javafx.scene.canvas.Canvas;
import model.Pozice;

public class Map extends Animated
{	
	private double Xborder;
	private double Yborder;
	private double upperYborder = 0;
	
	private boolean XborderHit;
	private boolean YborderHit;
	
	private static final ImageWithSource IMAGE = new ImageWithSource("/view/img/chodba.png");
	private static final Pozice POZICE = new Pozice(0,0);
	
	public Map()
	{
		this(IMAGE);
	}
	public Map(ImageWithSource image)
	{
		this(image,POZICE);
	}
	public Map(ImageWithSource image, Pozice pozice)
	{
		this.image = image;
		this.pozice = pozice;
	}
	private void setBorders(Canvas canvas)
	{
		this.Xborder = -image.getWidth()+canvas.getWidth();
		this.Yborder = -image.getHeight()+canvas.getHeight();
		XborderHit = false;
		YborderHit = false;
	}
	public boolean getXborderHit()
	{
		return XborderHit;
	}
	public boolean getYborderHit()
	{
		return YborderHit;
	}
	public double getXborder()
	{
		return Xborder;
	}
	public double getYborder()
	{
		return Yborder;
	}
	public void update(double time, Canvas canvas)
	{
		double newX = pozice.getXPoz();
		newX += Xvelocity * time;
		double newY = pozice.getYPoz();
		newY += Yvelocity * time;
		
		setBorders(canvas);
		
		if(newX <= Xborder)
		{
			newX = Xborder;
			XborderHit = true;
		}
		else if(newX >= 0)
		{
			newX = 0;
			XborderHit = true;
		}
		if(newY <= Yborder)
		{
			newY = Yborder;
			YborderHit = true;
		}
		else if(newY >= upperYborder)
		{
			newY = upperYborder;
			YborderHit = true;
		}

		setPozice(new Pozice(newX,newY));
	}
}
