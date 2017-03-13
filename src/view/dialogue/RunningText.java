package view.dialogue;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class RunningText extends AnimationTimer{
	
	// Casove udaje
	private long last = System.nanoTime();
	private int waitTime = 100;
	
	// Textove udaje
	private int index = 0;
	private String shownText = "";
	private String originalText;
	@SuppressWarnings("unused")
	private int line = 1;
	
	// Údaje o pozici, kterou ma vyhrazenou
	private int startX;
	private int startY;
	private int width;
	private int height;
	
	// Odskoèení nahoøe a dole
	private int horizontalPadding = 20;
	private int verticalPadding = 10;
	
	// Musí vìdìt kam kreslit
	private GraphicsContext gc;
	
	/**
	 * Tøída, která tiskne bìhající text.
	 * 	
	 * @param originalText
	 * @param gc
	 * @param startX
	 * @param startY
	 * @param width
	 * @param height
	 * @param waitTime
	 */
	public RunningText(String originalText, GraphicsContext gc, int startX, int startY, int width, int height, int waitTime){
		if(width <= 0 || height <= 0){
			throw new IllegalArgumentException("Width or height cannot be negative.");
		}
		this.originalText = originalText;
		this.gc = gc;
		this.startX = startX;
		this.startY = startY;
		this.width = width;
		this.height = height;
		this.waitTime = waitTime;
	}
	
	/**
	 * Tøída, která tiskne bìhající text.
	 * 
	 * Zde je nastavený defaultní waitTime na 100 ms.
	 * 
	 * @param originalText
	 * @param gc
	 * @param startX
	 * @param startY
	 * @param width
	 * @param height
	 */
	
	public RunningText(String originalText, GraphicsContext gc, int startX, int startY, int width, int height){
		this(originalText, gc, startX, startY, width, height, 100);
	}
	
	@Override
	public void handle(long now) {
		if((now - last) > waitTime){ // Zkontroluje, zda už mùže pøidat další písmeno
			last = now;
			
			if(index < originalText.length()){ // Zkontroluje, zda nedojel do konce
				shownText += originalText.charAt(index); // Pøidá jeden char
				
				// Zde se kontroluje, jestli jsme nedojeli do konce vyhrazeného pole a pøípadnì se zalomí
				if(this.computeTextWidth(gc.getFont(), shownText) >= (width-2*verticalPadding)){
					int lastIndex = shownText.lastIndexOf(" ");
					if(lastIndex == -1){
						lastIndex = shownText.length() - 1;
					}
					shownText = shownText.substring(0, lastIndex) + "\n" + shownText.substring(lastIndex+1, shownText.length());
					line++;
				}
				
				// vyèistí po sobì minulý text a vytiskne nový
				// NEBEZPEÈÍ pøemazání vìcí vytisknutých na stejném plátnì.
				gc.clearRect(startX, startY, width, height);
				gc.fillText(shownText, startX+verticalPadding, startY+horizontalPadding, width-2*verticalPadding);
			} else {
				this.stop();
			}
			index++;
		}
	}
	
	/**
	 * Vypoèítává velikost textu s urèitým fontem.
	 * 
	 * @param font
	 * @param text
	 * @return
	 */
	private double computeTextWidth(Font font, String text) {
	    Text helper = new Text();
	    helper.setFont(font);
	    helper.setText(text);
	    helper.setWrappingWidth(0);
	    helper.setLineSpacing(0);
	    double w = helper.prefWidth(-1);
	    helper.setWrappingWidth((int)Math.ceil(w));
	    double textWidth = Math.ceil(helper.getLayoutBounds().getWidth());
	    return textWidth;
	}
}
