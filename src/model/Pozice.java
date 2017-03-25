package model;
public class Pozice 
{
	private double xPoz = 0;
	private double yPoz = 0;
	
	public Pozice(double xPoz, double yPoz)
	{
		setPozice(xPoz,yPoz);
	}
	public Pozice(Pozice pozice)
	{
		this(pozice.getXPoz(),pozice.getYPoz());
	}
	public Pozice()
	{
		this(0,0);
	}
	public void setPozice(double xPoz, double yPoz)
	{
		this.xPoz = xPoz;
		this.yPoz = yPoz;
	}
	public Pozice getPozice()
	{
		return this;
	}
	public double getXPoz()
	{
		return this.xPoz;
	}
	public double getYPoz()
	{
		return this.yPoz;
	}
	public Pozice getCopy()
	{
		return new Pozice(this);
	}
	public void plus(Vektor v)
	{
		this.xPoz += v.getX();
		this.yPoz += v.getY();
	}
	@Override
	public String toString()
	{
		return "<"+xPoz+";"+yPoz+">";
	}
}
