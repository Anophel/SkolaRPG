package view.animation;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import model.Pozice;
import view.animation.ImageWithSource;
import view.animation.Animated;

public class Map extends Animated
{	
	private double Xborder;
	private double Yborder;
	private double upperYborder = 0;
	
	private boolean XborderHit;
	private boolean YborderHit;
	
	private static final ImageWithSource IMAGE = new ImageWithSource("/view/img/chodba.png");
	private static final ImageWithSource POZADI = new ImageWithSource("/view/img/pozadi.png");
	private static final boolean VODOROVNE = true;
	private static final Pozice POZICE = new Pozice(0,0);
	
	private ImageWithSource pozadi;
	private boolean vodorovne;
	
	public Map()
	{
		this(IMAGE, POZADI, VODOROVNE);
	}
	public Map(ImageWithSource image, ImageWithSource pozadi, boolean vodorovnePozadi)
	{
		this(image, pozadi, vodorovnePozadi, POZICE);
	}
	public Map(ImageWithSource image, ImageWithSource pozadi, boolean vodorovnePozadi, Pozice pozice)
	{
		this.image = image;
		this.pozadi = pozadi;
		this.vodorovne = vodorovnePozadi;
		this.pozice = pozice;
	}
	private void setBorders(Canvas canvas)
	{
		/*
		 * Pokud je orientace vodorovná, nachází se pozadí nad mapou a rošiøuje ji do výšky,
		 * pokud je orientace nevodorovná, nachází se pozadí vpravo a rozšiøuje mapu do šíøky.
		 */
		if(vodorovne)
		{
			Xborder = canvas.getWidth()-image.getWidth();
			Yborder = canvas.getHeight()-(image.getHeight()+pozadi.getHeight());
		}
		else
		{
			Xborder = canvas.getWidth()-(image.getWidth()+pozadi.getWidth());
			Yborder = canvas.getHeight()-image.getHeight();
		}
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
	public ImageWithSource getPozadiImage()
	{
		return pozadi;
	}
	public void setPozadiImage(ImageWithSource img)
	{
		this.pozadi = img;
	}
	public boolean getVodorovnePozadi()
	{
		return vodorovne;
	}
	public double getVodorovnyPrechod()
	{
		return pozice.getYPoz()+pozadi.getHeight();
	}
	public double getVertikalniPrechod()
	{
		return pozice.getXPoz()+image.getWidth();
	}
	public boolean intersectsWithBackground(Animated animated)
	{
		if(vodorovne)
		{
			if(animated.getPozice().getYPoz()<(pozice.getYPoz()+pozadi.getHeight()))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			if(animated.getPozice().getXPoz()>(pozice.getXPoz()-pozadi.getWidth()))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	@Override
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
	@Override
	public void render(GraphicsContext gt)
	{
		if(vodorovne)
		{
			
			gt.drawImage(image, pozice.getXPoz(), pozice.getYPoz()+pozadi.getHeight());
			gt.drawImage(pozadi, pozice.getXPoz(), pozice.getYPoz());
		}
		else
		{
			gt.drawImage(image, pozice.getXPoz(), pozice.getYPoz());
			gt.drawImage(pozadi, pozice.getXPoz()+image.getWidth(), pozice.getYPoz());
		}
	}
}
