package view.animation;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import model.characters.Characters;

public class AnimatedCharacter extends Animated
{
	private ArrayList<ImageWithSource> relaxSeq = new ArrayList<ImageWithSource>();
	private ArrayList<ImageWithSource> leftSeq = new ArrayList<ImageWithSource>();
	private ArrayList<ImageWithSource> rightSeq = new ArrayList<ImageWithSource>();
	private ArrayList<ImageWithSource> upSeq = new ArrayList<ImageWithSource>();
	private ArrayList<ImageWithSource> downSeq = new ArrayList<ImageWithSource>();
	
	private int nextImageID = 1; //jedna, proto�e po za��tku u� rovno relaxuje a chce na��st dal�� ob�zek
	protected Status status;
	
	private static final double delay = 30; //mezera mezi kroky animace
	private double lastTime = 0;
	
	
	
	public AnimatedCharacter(ImageWithSource defaultImage)
	{
		image = defaultImage;
	}
	public AnimatedCharacter(ImageWithSource defaultImage, ArrayList<ImageWithSource> relax, ArrayList<ImageWithSource> left, 
						ArrayList<ImageWithSource> right, ArrayList<ImageWithSource> up, ArrayList<ImageWithSource> down)
	{
		image = defaultImage;
		relaxSeq = relax;
		leftSeq = left;
		rightSeq = right;
		upSeq = up;
		downSeq = down;
	}
	public AnimatedCharacter(Characters character)
	{
		this(character.getImage(), character.getRelax(), character.getLeft(), character.getRight(), 
				character.getUp(), character.getDown());
	}
	
	
	public void setRelaxSeq(ArrayList<ImageWithSource> imgs)
	{
		this.relaxSeq = imgs;
	}
	public void setLeftSeq(ArrayList<ImageWithSource> imgs)
	{
		this.leftSeq = imgs;
	}
	public void setRightSeq(ArrayList<ImageWithSource> imgs)
	{
		this.rightSeq = imgs;
	}
	public void setUpSeq(ArrayList<ImageWithSource> imgs)
	{
		this.upSeq = imgs;
	}
	public void setDownSeq(ArrayList<ImageWithSource> imgs)
	{
		this.downSeq = imgs;
	}
	public void relax(double time) //vyhodno� dal�� animaci odpo�inku
	{
		if(this.status != Status.RELAX)	//pokud u� neodpo��v�
		{
			status = Status.RELAX;	//nastav�m ho na odpo�inek
			image = (ImageWithSource) relaxSeq.get(0);	//nastav�m mu prvn� obr�zek odpo�inku
			resetAnimation();
		}
		else	//pokud u� odpo��v�
		{
			if((lastTime+time)>delay)	//pokud u� uplynula ��dan� odmlka animace
			{
				lastTime = 0;	//restartuji prodlevu
				if(nextImageID>=relaxSeq.size())	//pokud u� do�el na konec sekvence
				{
					image = (ImageWithSource) relaxSeq.get(0);	//vezmu prvn� obr�zek
					nextImageID = 1;	//nastav�m na po�adov� ��slo dv�
				}
				else	//nach�z� se uvnit� sekvence
				{
					image = (ImageWithSource) relaxSeq.get(nextImageID);	//nastav�m dal�� obr�zek
					nextImageID++;	//p�iprav�m dal��
				}
			}
			else	//je�t� nen� na �ase zm�nit animaci
			{
				lastTime += time;	//p�id�m uplynul� �as k minul�m
			}
		}
	}
	public void left(double time)	//vyhodno� dal�� animaci vlevo
	{
		if(status != Status.LEFT)
		{
			status = Status.LEFT;
			image = (ImageWithSource) leftSeq.get(0);
			resetAnimation();
		}
		else
		{
			if((lastTime+time)>delay)
			{
				lastTime = 0;
				if(nextImageID>=leftSeq.size())
				{
					image = (ImageWithSource) leftSeq.get(0);
					nextImageID = 1;
				}
				else
				{
					image = (ImageWithSource) leftSeq.get(nextImageID);
					nextImageID++;
				}
			}
			else
			{
				lastTime += time;
			}
		}
	}
	public void right(double time)	//vyhodno� dal�� animaci vpravo
	{
		if(status != Status.RIGHT)
		{
			status = Status.RIGHT;
			image = (ImageWithSource) rightSeq.get(0);
			resetAnimation();
		}
		else
		{
			if((lastTime+time)>delay)
			{
				lastTime = 0;
				if(nextImageID>=rightSeq.size())
				{
					image = (ImageWithSource) rightSeq.get(0);
					nextImageID = 1;
				}
				else
				{
					image = (ImageWithSource) rightSeq.get(nextImageID);
					nextImageID++;
				}
			}
			else
			{
				lastTime += time;
			}
		}
	}
	public void up(double time) //vyhodno� dal�� animaci vzh�ru
	{
		if(status != Status.UP)
		{
			status = Status.UP;
			image = (ImageWithSource) upSeq.get(0);
			resetAnimation();
		}
		else
		{
			if((lastTime+time)>delay)
			{
				lastTime = 0;
				if(nextImageID>=upSeq.size())
				{
					image = (ImageWithSource) upSeq.get(0);
					nextImageID = 1;
				}
				else
				{
					image = (ImageWithSource) upSeq.get(nextImageID);
					nextImageID++;
				}
			}
			else
			{
				lastTime += time;
			}
		}
	}
	public void down(double time)	//vyhodno� dal�� animaci dol�
	{
		if(status != Status.DOWN)
		{
			status = Status.DOWN;
			image = (ImageWithSource) downSeq.get(0);
			resetAnimation();
		}
		else
		{
			if((lastTime+time)>delay)
			{
				lastTime = 0;
				if(nextImageID>=downSeq.size())
				{
					image = (ImageWithSource) downSeq.get(0);
					nextImageID = 1;
				}
				else
				{
					image = (ImageWithSource) downSeq.get(nextImageID);
					nextImageID++;
				}
			}
			else
			{
				lastTime += time;
			}
		}
	}
	private void resetAnimation()
	{
		nextImageID = 1;
		lastTime = 0;
	}
	@Override
	public void setImageSize(double width, double height)
	{
		ArrayList<ImageWithSource> relax = new ArrayList<ImageWithSource>();
		ArrayList<ImageWithSource> left = new ArrayList<ImageWithSource>();
		ArrayList<ImageWithSource> right = new ArrayList<ImageWithSource>();
		ArrayList<ImageWithSource> up = new ArrayList<ImageWithSource>();
		ArrayList<ImageWithSource> down = new ArrayList<ImageWithSource>();
		
		String url = image.getURL();
		this.image = new ImageWithSource(url,width,height,true,true);
		
		for(ImageWithSource img:relaxSeq)
		{
			url = img.getURL();
			relax.add(new ImageWithSource(url,width,height,true,true));
		}
		this.relaxSeq = relax;
		for(ImageWithSource img:leftSeq)
		{
			url = img.getURL();
			left.add(new ImageWithSource(url,width,height,true,true));
		}
		this.leftSeq = left;
		for(ImageWithSource img:rightSeq)
		{
			url = img.getURL();
			right.add(new ImageWithSource(url,width,height,true,true));
		}
		this.rightSeq = right;
		for(ImageWithSource img:upSeq)
		{
			url = img.getURL();
			up.add(new ImageWithSource(url,width,height,true,true));
		}
		this.upSeq = up;
		for(ImageWithSource img:downSeq)
		{
			url = img.getURL();
			down.add(new ImageWithSource(url,width,height,true,true));
		}
		this.downSeq = down;
	}
	@Override
	public void update(double time, Canvas canvas) 
	{

	}
}