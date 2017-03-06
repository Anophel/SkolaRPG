package view.animation;

import javafx.scene.image.Image;

public class ImageWithSource extends Image
{
	private String URL;
	
	public ImageWithSource(String url) 
	{
		super(url);
		this.URL = url;
	}
	public ImageWithSource(String url, double requestedWidth, double requestedHeight, boolean preserveRatio,boolean smooth) 
	{
		super(url, requestedWidth, requestedHeight, preserveRatio, smooth);
		this.URL = url;
	}	

	public String getURL()
	{
		return URL;
	}
}
