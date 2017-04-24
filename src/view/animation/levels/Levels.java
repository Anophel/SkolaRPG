package view.animation.levels;

import java.util.ArrayList;
import model.characters.Postava;
import model.items.Klic;
import view.animation.Map;

public enum Levels 
{
	LEVEL_1(Maps.CHODBA_1, Maps.CHODBA_2);
	
	/*
	 * Prozat�m jsou levely slo�eny pouze ze dvou vodorovn�ch map navazuj�c�ch na sebe.
	 * Design do "L" do�e��m hned, jak budu m�t k dispozici lep�� prost�edn� kus.
	 */
	
	private Map leftMap;
	private Map rightMap;
	private ArrayList<Postava> leftMapEnemies = new ArrayList<Postava>();
	private ArrayList<Postava> rightMapEnemies = new ArrayList<Postava>();
	private Klic leftMapReleaseKey;
	private Klic rightMapReleaseKey;
	
	Levels(Maps left, Maps right)
	{
		leftMap = new Map(Maps.CHODBA_1);
		rightMap = new Map(Maps.CHODBA_2);
		leftMapEnemies = left.getEnemies();
		rightMapEnemies = right.getEnemies();
		leftMapReleaseKey = left.getReleaseKey();
		rightMapReleaseKey = right.getReleaseKey();
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
	public Klic getLeftMapReleaseKey()
	{
		return leftMapReleaseKey;
	}
	public Klic getRightMapReleaseKey()
	{
		return rightMapReleaseKey;
	}
}
