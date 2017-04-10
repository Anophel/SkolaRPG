package model;

public class Vektor 
{
	private double X;
	private double Y;
	
	public Vektor(double x, double y)
	{
		this.X = x;
		this.Y = y;
	}
	public double getX()
	{
		return X;
	}
	public double getY()
	{
		return Y;
	}
	public void set(Vektor v)
	{
		this.X = v.getX();
		this.Y = v.getY();
	}
	public void addX(double x)
	{
		this.X += x;
	}
	public void addY(double y)
	{
		this.Y += y;
	}
}
