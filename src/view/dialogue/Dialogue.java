package view.dialogue;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import model.characters.Characters;

public class Dialogue extends Canvas{
	
	// Avatar position
	private int xAvatar = 10;
	private int yAvatar = 10;
	private Double percentAvatarWidth = 0.20;
	private Double percentAvatarHeight = 0.50;
	
	// Ronning text position
	private int xGapFromAvatar = 10;
	private int yRunningText = 10;
	
	private ArrayList<Speech> speeches = new ArrayList<Speech>();
	private AnimationTimer timer;
	
	public Dialogue(Dialogues dial, int width, int height){
		super(width, height);
		try {
	         File inputFile = new File(dial.getPath());
	         DocumentBuilderFactory dbFactory = 
	            DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();
	         NodeList ss = doc.getElementsByTagName("speech");
	         
	         for (int i = 0; i < ss.getLength(); i++) {
	            Node speech = ss.item(i);
	            
	            if (speech.getNodeType() == Node.ELEMENT_NODE) {
	               Element eSpeech = (Element) speech;
	               NodeList carNameList = 
	                  eSpeech.getElementsByTagName("text");
	               
	               for (int count = 0; count < carNameList.getLength(); count++) {	 
	                  Node text = carNameList.item(count);
	                  if (text.getNodeType() == Node.ELEMENT_NODE) {
	                     Element t = (Element) text;
	                     
	                     Speech sp = new Speech(Characters.valueOf(eSpeech.getAttribute("name").toUpperCase()), t.getTextContent(), Boolean.parseBoolean(eSpeech.getAttribute("fromRight")));
	                     speeches.add(sp);
	                  }
	               }
	            }
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		
		timer = new AnimationTimer(){
			int index = 0;
			
			RunningText text = null;
			
			@Override
			public void handle(long arg0) {
				if(text == null || !text.isRunning()){
					if(index < speeches.size()){
						text = renderSpeech(index);
						index++;
					} else {
						this.stop();
					}
				}
			}
		};
	}
	
	public void start(){
		timer.start();
	}
	
	private RunningText renderSpeech(int index){
		return this.renderSpeech(speeches.get(index));
	}
	
	private RunningText renderSpeech(Speech speech){
		GraphicsContext gc = super.getGraphicsContext2D();
		gc.clearRect(0, 0, super.getWidth(), super.getWidth());
		RunningText text = new RunningText(speech.getText(), gc, (int)(xAvatar + super.getWidth() * percentAvatarWidth + xGapFromAvatar), yRunningText, (int)((1-percentAvatarWidth)*super.getWidth()), (int)((1-percentAvatarHeight)*super.getHeight()));
		gc.drawImage(speech.getAvatar(), xAvatar, yAvatar, super.getWidth() * percentAvatarWidth, super.getHeight() * percentAvatarHeight);
		text.start();
		return text;
	}
}
