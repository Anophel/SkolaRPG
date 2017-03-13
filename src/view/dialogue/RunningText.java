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
	
	// �daje o pozici, kterou ma vyhrazenou
	private int startX;
	private int startY;
	private int width;
	private int height;
	
	// Odsko�en� naho�e a dole
	private int horizontalPadding = 20;
	private int verticalPadding = 10;
	
	// Mus� v�d�t kam kreslit
	private GraphicsContext gc;
	
	/**
	 * T��da, kter� tiskne b�haj�c� text.
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
	 * T��da, kter� tiskne b�haj�c� text.
	 * 
	 * Zde je nastaven� defaultn� waitTime na 100 ms.
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
		if((now - last) > waitTime){ // Zkontroluje, zda u� m��e p�idat dal�� p�smeno
			last = now;
			
			if(index < originalText.length()){ // Zkontroluje, zda nedojel do konce
				shownText += originalText.charAt(index); // P�id� jeden char
				
				// Zde se kontroluje, jestli jsme nedojeli do konce vyhrazen�ho pole a p��padn� se zalom�
				if(this.computeTextWidth(gc.getFont(), shownText) >= (width-2*verticalPadding)){
					int lastIndex = shownText.lastIndexOf(" ");
					if(lastIndex == -1){
						lastIndex = shownText.length() - 1;
					}
					shownText = shownText.substring(0, lastIndex) + "\n" + shownText.substring(lastIndex+1, shownText.length());
					line++;
				}
				
				// vy�ist� po sob� minul� text a vytiskne nov�
				// NEBEZPE�� p�emaz�n� v�c� vytisknut�ch na stejn�m pl�tn�.
				gc.clearRect(startX, startY, width, height);
				gc.fillText(shownText, startX+verticalPadding, startY+horizontalPadding, width-2*verticalPadding);
			} else {
				this.stop();
			}
			index++;
		}
	}
	
	/**
	 * Vypo��t�v� velikost textu s ur�it�m fontem.
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
