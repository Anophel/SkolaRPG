package view.animation;

import java.util.ArrayList;
import model.characters.Postava;
import model.items.Klic;
import view.animation.levels.Levels;
import view.animation.levels.Part;

public class Level 
{
	/*
	 * Prozat�m jsou levely slo�eny pouze ze dvou vodorovn�ch map navazuj�c�ch na sebe.
	 * Design do "L" do�e��m hned, jak budu m�t k dispozici lep�� prost�edn� kus.
	 */
	private Map leftMap;
	private Map rightMap;
	
	private Part partBeingUsed = Part.LEFT;
	
	private ArrayList<Postava> leftMapEnemies = new ArrayList<Postava>();
	private ArrayList<Postava> rightMapEnemies = new ArrayList<Postava>();
	
	private Klic leftMapReleaseKey;
	private Klic rightMapReleaseKey;
	
	public Level(Levels level)
	{
		this.leftMap = level.getLeftMap();
		this.rightMap = level.getRightMap();
		this.leftMapEnemies = level.getLeftMapEnemies();
		this.rightMapEnemies = level.getRightMapEnemies();
		leftMapReleaseKey = level.getLeftMapReleaseKey();
		rightMapReleaseKey = level.getRightMapReleaseKey();
	}
	public void setPartBeingUsed(Part part)
	{
		partBeingUsed = part;
	}
	public Part getPartBeingUsed()
	{
		return partBeingUsed;
	}
	public Map getLeftMap()
	{
		return leftMap;
	}
	public Map getRightMap()
	{
		return rightMap;
	}
	public ArrayList<Postava> getLeftMapEnemies()
	{
		return leftMapEnemies;
	}
	public ArrayList<Postava> getRightMapEnemies()
	{
		return rightMapEnemies;
	}
	public Map getMap(Part part)
	{
		if(part == Part.LEFT)
		{
			return leftMap;
		}
		else
		{
			return rightMap;
		}
	}
	public ArrayList<Postava> getEnemies(Part part)
	{
		if(part == Part.LEFT)
		{
			return leftMapEnemies;
		}
		else
		{
			return rightMapEnemies;
		}
	}
	public Klic getLeftMapReleaseKey()
	{
		return leftMapReleaseKey;
	}
	public Klic getRightMapReleaseKey()
	{
		return rightMapReleaseKey;
	}
}
