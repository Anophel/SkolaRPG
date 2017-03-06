package view.animation;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.Pozice;

public abstract class Animated 
{
	protected double Xvelocity;
	protected double Yvelocity;
	
	protected Pozice pozice;
	
	protected ImageWithSource image;
	
	public void setVelocity(double X, double Y)
	{
		this.Xvelocity = X;
		this.Yvelocity = Y;
	}
	public void addVelocity(double X, double Y)
	{
		this.Xvelocity += X;
		this.Yvelocity += Y;
	}
	public double getXvelocity()
	{
		return Xvelocity;
	}
	public double getYvelocity()
	{
		return Yvelocity;
	}
	public void setPozice(Pozice pozice)
	{
		this.pozice = pozice;
	}
	public Pozice getPozice()
	{
		return pozice;
	}
	public void setImage(ImageWithSource img)
	{
		this.image = img;
	}
	public void setImage(String URL)
	{
		this.image = new ImageWithSource(URL);
	}
	public Image getImage()
	{
		return image;
	}
	public void render(GraphicsContext gc)
	{
		gc.drawImage(image, pozice.getXPoz(), pozice.getYPoz());
	}
	public void setImageSize(double width, double height)
	{
		String url = image.getURL();
		
		image = new ImageWithSource(url,width,height,false,true);
	}
	public boolean intersectsWith(AnimatedCharacter other)
	{
		/*			 My			Other	
		 * 				
		 * 			A - B		E - F	
		 * 			|	|		|	|
		 * 			C - D		G - H
		 * 
		 */
		Pozice A = pozice;
		Pozice B = new Pozice(pozice.getXPoz()+image.getWidth(),pozice.getYPoz());
		Pozice C = new Pozice(pozice.getXPoz(),pozice.getYPoz()+image.getHeight());
		Pozice D = new Pozice(pozice.getXPoz()+image.getWidth(),pozice.getYPoz()+image.getHeight());
		
		Pozice OthPoz = other.getPozice();
		Image OthImg = other.getImage();
		Pozice E = OthPoz;
		Pozice F = new Pozice(OthPoz.getXPoz()+OthImg.getWidth(),OthPoz.getYPoz());
		Pozice G = new Pozice(OthPoz.getXPoz(),OthPoz.getYPoz()+OthImg.getHeight());
		Pozice H = new Pozice(OthPoz.getXPoz()+OthImg.getWidth(),OthPoz.getYPoz()+OthImg.getHeight());
		
		if(smallIntersection(A,B,C,E))
		{
			return true;
		}
		else if(smallIntersection(A,B,C,F))
		{
			return true;
		}
		else if(smallIntersection(A,B,C,G))
		{
			return true;
		}
		else if(smallIntersection(A,B,C,H))
		{
			return true;
		}
		else if(bigIntersection(E,F,G,A))
		{
			return true;
		}
		else if(bigIntersection(E,F,G,B))
		{
			return true;
		}
		else if(bigIntersection(E,F,G,C))
		{
			return true;
		}
		else if(bigIntersection(E,F,G,D))
		{
			return true;
		}

		return false;
	}
	private boolean smallIntersection(Pozice A, Pozice B, Pozice C, Pozice O)
	{
		/*
		 * 			Me			Other
		 * 		
		 * 		A - B			O
		 * 		|
		 * 		C				
		 * 	
		 */
		
		if(O.getXPoz()>=A.getXPoz() && O.getXPoz()<=B.getXPoz())
		{
			//E se nachází mezi A a B
			
			if(O.getYPoz()>=A.getYPoz() && O.getYPoz()<=C.getYPoz())
			{
				//E se nachází mezi A a B a zároveò mezi A a C
				return true;
			}
		}
		return false;
	}
	private boolean bigIntersection(Pozice E, Pozice F, Pozice G, Pozice M)
	{
		/*
		 * 			Other			Me
		 * 				
		 * 		E - F			M
		 * 		|
		 * 		G
		 * 
		 */
		
		if(M.getXPoz()>=E.getXPoz() && M.getXPoz()<=F.getXPoz())
		{
			//M se nachází mezi E a F
			if(M.getYPoz()>=E.getYPoz() && M.getYPoz()<=G.getYPoz())
			{
				return true;
			}
		}
		return false;
	}
	public abstract void update(double time, Canvas canvas);
}