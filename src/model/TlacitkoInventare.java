package model;

import javafx.scene.control.Button;
import model.items.INositelne;

public class TlacitkoInventare extends Button
{
	INositelne item;
	
	public void setItem(INositelne item)
	{
		this.item = item;
	}
	public INositelne getItem()
	{
		return item;
	}
}
