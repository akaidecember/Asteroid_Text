package com.mycompany.a1;

import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;


public class Game extends Form{

	//Object of the type gameWorld
	private GameWorld gw;
	
	//Constructor
	public Game() {
		
		//Initializing the gameWorld
		gw = new GameWorld();
		gw.init();
		play();
		
	}
	
	//Method to accept the commands necessary to play the game
	private void play() {
		
		Label myLabel = new Label("Enter a command:");
		this.addComponent(myLabel);
		final TextField myTextField = new TextField();
		this.addComponent(myTextField);
		this.show();
		try {
			
				myTextField.addActionListener(new ActionListener(){
				
				public void actionPerformed(ActionEvent evt) {
					
					String sCommand = myTextField.getText().toString();
					
					if(sCommand.length() != 1) {
						
						sCommand = "*";
						
					}
					
					switch(sCommand.charAt(0)) {
					
					case 'a':
						gw.addAsteroid();
						break;
						
					case 'y':
						gw.addNps();
						break;
						
					case 'b':
						gw.addStation();
						break;
					
					case 's':
						gw.addPs();
						break;
					
					case 'i':
						gw.increasePsSpeed();
						break;
						
					case 'd':
						gw.decreasePsSpeed();
						break;
						
					case 'l':
						gw.turnPsLeft();
						break;
						
					case 'r':
						gw.turnPsRight();
						break;
						
					case '>':
						gw.turnPsUser();
						break;
					
					case 'f':
						gw.firePs();
						break;
						
					case 'L':
						gw.fireNps();
						break;
						
					case 'j':
						gw.hyperJump();
						break;
						
					case 'n':
						gw.loadMissiles();
						break;
						
					case 'k':
						gw.killAsteroidPs();
						break;
						
					case 'e':
						gw.eliminateNps();
						break;
						
					case 'E':
						gw.explodePs();
						break;
						
					case 'c':
						gw.crashPs();
						break;
						
					case 'h':
						gw.psHitNps();
						break;
						
					case 'x':
						gw.asteroidCollide();
						break;
						
					case 'I':
						gw.asteroidCollidesNps();
						break;
						
					case 't':
						gw.tick();
						break;
					
					case 'p':
						gw.print();
						break;
						
					case 'm':
						gw.map();
						break;
						
					case 'q':
						gw.quit();
						break;
						
					default: 
						System.out.println("Please enter a valid command");
						break;
				
					}
					
					myTextField.clear();
				}
				
			});}catch(StringIndexOutOfBoundsException e) {e.addSuppressed(e);}
		
	}
}
