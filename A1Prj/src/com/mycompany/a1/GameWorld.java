package com.mycompany.a1;

import java.util.ArrayList;
import java.util.Collections;

import com.codename1.ui.Dialog;
import com.codename1.ui.Display;

import Interfaces.IMovable;

import ClosingForm.ClosingApp;
import Objects.Asteroid;
import Objects.GameObject;
import Objects.Missile;
import Objects.NonPlayerShip;
import Objects.PlayerShip;
import Objects.SpaceStation;



public class GameWorld{

	//Attributes for the class GameWorld----------------------------------------------------------------
	
	private ArrayList<GameObject> worldList;
	private Asteroid asteroid;
	private Missile missile;
	private NonPlayerShip nps;
	private PlayerShip ps;
	private SpaceStation spaceStation;
	private int life;
	private int score;
	private int time;
	
	//Behaviours for the class GameWorld----------------------------------------------------------------
	
	//Default Constructor for the class GameWorld 
	public GameWorld() {
		
		//Calls init to initialte the procedures for running the game
		init();
		
	}
	
	public void init() {
		
		//ArrayList of the type GameObject to store all the objects created for the game
		worldList = new ArrayList<GameObject>();
		
		//Setting the scores, time and life for the start of the game
		score = 0;
		time = 0;
		life = 3;
		
	}
	
	//Method to add an asteroid to the game
	public void addAsteroid() {
		
		asteroid = new Asteroid();
		worldList.add(asteroid);
		
	}
	
	//Method to add a non-player ship to the game
	public void addNps() {
		
		nps = new NonPlayerShip();
		worldList.add(nps);
		
	}
	
	//Method to add a space-station to the game
	public void addStation() {
		
		spaceStation = new SpaceStation();
		worldList.add(spaceStation);
		
	}
	
	//Method to add a player ship to the game
	public void addPs() {
		
		if(!worldList.contains(ps)) {
			ps = new PlayerShip();
			worldList.add(ps);
		}
		else
			System.out.println("Error! Cannot create more than one player ship\n");			//Enforcing that only one player ship should exist at any time
		
	}
	
	//Method to increase the speed of the player ship
	public void increasePsSpeed() {
		
		if(worldList.contains(ps))
			ps.increaseSpeedPs();
		else
			System.out.println("Error! Player ship not found. Create one first by typing in 's'\n");
		
	}
	
	//Method to decrease the speed of the player ship
	public void decreasePsSpeed() {
		
		if(worldList.contains(ps))
			ps.decreaseSpeedPs();
		else
			System.out.println("Error! Player ship not found. Create one first by typing in 's'\n");
		
	}
	
	//Method to turn the Player ship left by a small amount
	public void turnPsLeft() {
		
		if(worldList.contains(ps))
			ps.steerLeft();
		else
			System.out.println("Error! Player ship not found. Create one first by typing in 's'\n");
		
		
	}
	
	//Method to turn the player ship right by a small amount
	public void turnPsRight() {
		
		if(worldList.contains(ps))
			ps.steerRight();
		else
			System.out.println("Error! Player ship not found. Create one first by typing in 's'\n");
		
		
	}
	
	//Method to let the user determine the direction and amount by which the player ship turns
	public void turnPsUser() {
		
		if(worldList.contains(ps))
			ps.steerManual();
		else
			System.out.println("Error! Player ship not found. Create one first by typing in 's'\n");
		
		
	}
	
	//Method to fire out a missile from the player ship
	public void firePs() {
		
		if(worldList.contains(ps)) {
			
			if(ps.getMissileCount() > 0) {
				
				//Setting the missile with the speed, direction and location of the PS for the start
				missile = new Missile(ps.getSpeed(), ps.getDirection(), ps.getLocation(), 0);
				worldList.add(missile);
				
				//Decrementing the missile count for the player ship
				ps.setMissileCount(ps.getMissileCount() - 1);
				
			}
			else
				System.out.println("Your ship has run out of missiles! Please reload at the Space Station\n");
			
		}
		else
			System.out.println("Error! Player ship not found. Create one first by typing in 's'\n");
		
	}
	
	//Method to fire out a missile from the Non player ship
	public void fireNps() {
		
		if(worldList.contains(nps)) {
			
			if(nps.getNpsMissileCount() > 0) {
				
				missile = new Missile(nps.getSpeed(), nps.getDirection(), nps.getLocation(), 1);
				worldList.add(missile);
				
				//Decrementing the missile count for the non player ship
				nps.setNpsMissileCount(nps.getNpsMissileCount() - 1);
				
			}
			else
				System.out.println("The non player ship has run out of missiles!\n");
			
		}
		else
			System.out.println("Error! Non player ship not found. Create one first by typing in 'y'\n");
				
	}
	
	//Method to instantly make the player ship jump to the center of the screen a.k.a hyperjump
	public void hyperJump() {
		
		if(worldList.contains(ps)) {

			ps.defaultLocationPs();
			
		}
		else
			System.out.println("Error! Player ship not found. Create one first by typing in 's'\n");
		
		
	}
	
	//Method to load a new supply of missiles to the Player ship
	public void loadMissiles() {
		
		if(worldList.contains(ps)) {

			ps.setMaxMissiles();
			
		}
		else
			System.out.println("Error! Player ship not found. Create one first by typing in 's'\n");
		
	}
	
	/*Method to handle the situation where a player ship missile has struck an asteroid and destroyed it.
	 * This method removes the asteroid and the Player ship missile that were involved in the collision
	 */
	public void killAsteroidPs() {
		
		Asteroid tempAsteroid = new Asteroid();
		Missile tempMissile = new Missile();
		int count = 0;
		
		if(!worldList.isEmpty()) {
			
			for(int i = 0; i< worldList.size(); i++) {
				
				if(worldList.get(i) instanceof Asteroid) {
					
					tempAsteroid = (Asteroid) worldList.get(i);
					count++;
					break;
					
				}
				
			}
			if(count == 1) {

				for(int i = 0; i< worldList.size(); i++) {
					
					if(worldList.get(i) instanceof Missile) {
						
						tempMissile = (Missile) worldList.get(i);
						if(tempMissile.getMissileFlag() == 0) {
							
							count++;
							break;
							
						}
						
					}
					
				}
				if(count == 2) {
					
					worldList.remove(tempAsteroid);
					worldList.remove(tempMissile);
					
				}
				else
					System.out.println("Error! Insufficient number of required objects in the game\n");
				
			}
			else
				System.out.println("Error! Insufficient number of required objects in the game\n");
			
		}
		else
			System.out.println("Error! Insufficient number of required objects in the game\n");
		
		
	}
	
	/*Method to handle the situation where a player ship missile has struck a non player ship and 
	 * destroyed it. This method removes the non player ship and the Player ship missile that were 
	 * involved in the collision
	 */
	public void eliminateNps() {
		
		NonPlayerShip tempNps = new NonPlayerShip();
		Missile tempMissile = new Missile();
		int count = 0;
		
		if(!worldList.isEmpty()) {
			
			for(int i = 0; i< worldList.size(); i++) {
				
				if(worldList.get(i) instanceof NonPlayerShip) {
					
					tempNps = (NonPlayerShip) worldList.get(i);
					count++;
					break;
					
				}
				
			}
			if(count == 1) {

				for(int i = 0; i< worldList.size(); i++) {
					
					if(worldList.get(i) instanceof Missile) {
						
						tempMissile = (Missile) worldList.get(i);
						if(tempMissile.getMissileFlag() == 0) {
							
							count++;
							
						}
						
					}
					
				}
				if(count == 2) {
					
					worldList.remove(tempNps);
					worldList.remove(tempMissile);
					
				}
				else
					System.out.println("Error! Insufficient number of required objects in the game\n");
				
			}
			else
				System.out.println("Error! Insufficient number of required objects in the game\n");
			
		}
		else
			System.out.println("Error! Insufficient number of required objects in the game\n");
		
	}
	
	/* Method to handle the situation where the non player ship missile has struck and destroyed a 
	 * player ship. This method removes one NPS missile and a player ship
	 */
	public void explodePs() {
		
		Missile tempMissile = new Missile();
		
		int count = 0;
		
		if(!worldList.isEmpty()) {
			
			for(int i = 0; i< worldList.size(); i++) {
				
				if(worldList.get(i) instanceof Missile) {
						
					tempMissile = (Missile) worldList.get(i);
					if(tempMissile.getMissileFlag() == 1) {
							
						count++;
						break;
							
					}	
					
				}

			}
			
			if(count == 1 && worldList.contains(ps)) {
				
				worldList.remove(tempMissile);
				worldList.remove(ps);
					
				if(life > 1) 
					life--;
				else if(life == 1)
					gameOver();
					
			}
			else
				System.out.println("Error! Insufficient number of required objects in the game\n");
			
		}
		else
			System.out.println("Error! Insufficient number of required objects in the game\n");
		
	}
	
	/*Method to handle the situation where a player ship has crashed into an asteroid. In this case
	 * an asteroid and a player ship needs to be destroyed.
	 */
	public void crashPs() {
		
		Asteroid temp = new Asteroid();
		int count = 0;
		
		if(!worldList.isEmpty()) {
			
			for(int i = 0; i< worldList.size(); i++) {
				
				if(worldList.get(i) instanceof Asteroid) {
					
					temp = (Asteroid) worldList.get(i);
					count++;
					break;
					
				}
			
			}
			if(worldList.contains(ps) && count == 1) {
				
				worldList.remove(ps);
				worldList.remove(temp);
				
				if(life > 1) 
					life--;
				else if(life == 1)
					gameOver();
				
			}
			else
				System.out.println("Error! Insufficient number of required objects in the game\n");

		}
		else
			System.out.println("Error! Insufficient number of required objects in the game\n");
		
	}
	
	/*Method invoked when a player ship collides with a non player ship. In this case, both the objects 
	 * are removed from the game
	 */
	public void psHitNps() {
		
		NonPlayerShip tempNps = new NonPlayerShip();
		int count = 0;
		
		if(!worldList.isEmpty()) {
			
			for(int i = 0; i< worldList.size(); i++) {
				
				if(worldList.get(i) instanceof NonPlayerShip) {
					
					tempNps = (NonPlayerShip) worldList.get(i);
					count++;
					break;
					
				}
			
			}
			if(worldList.contains(ps) && count == 1) {
				
				worldList.remove(ps);
				worldList.remove(tempNps);
				
				if(life > 1) 
					life--;
				else if(life == 1)
					gameOver();
				
			}
			else
				System.out.println("Error! Insufficient number of required objects in the game\n");

		}
		else
			System.out.println("Error! Insufficient number of required objects in the game\n");
		
	}
	
	/*Method invoked when two asteroids have collided with each other. In this case, two asteroids are 
	 * removed from the game
	 */
	public void asteroidCollide() {
			
		Asteroid[] temp = new Asteroid[2];
		int count = 0;
		
		if(!worldList.isEmpty()) {
			
			for(int i = 0; i< worldList.size(); i++) {
				
				if(worldList.get(i) instanceof Asteroid) {
					
					temp[count] = (Asteroid) worldList.get(i);
					count++;
					
				}
				
				if(count == 2)
					break;
				
			}
			
			if(count == 2) {
				
				count--;
				worldList.remove(temp[count]);
				count--;
				worldList.remove(temp[count]);
				
			}
			else
				System.out.println("Error! Insufficient number of required objects in the game\n");
			
		}
		else
			System.out.println("Error! Insufficient number of required objects in the game\n");
		
	}
	
	/*Method invoked when an asteroid collides with a non player ship. In this case, both the objects are
	 *  removed from the game
	 */
	public void asteroidCollidesNps() {
		
		/*if(worldList.contains(asteroid) && worldList.contains(nps)) {
			
			worldList.remove(asteroid);
			worldList.remove(nps);
			
		}
		else
			System.out.println("Error! Either a non player ship or an asteroid needs to be added in the game\n");
		*/
		
		Asteroid tempAsteroid = new Asteroid();
		NonPlayerShip tempNps = new NonPlayerShip();
		int count = 0;
		
		if(!worldList.isEmpty()) {
			
			for(int i = 0; i< worldList.size(); i++) {
				
				if(worldList.get(i) instanceof Asteroid) {
					
					tempAsteroid = (Asteroid) worldList.get(i);
					count++;
					break;
					
				}
			
			}
			if(count == 1) {
				
				for(int i = 0; i< worldList.size(); i++) {
					
					if(worldList.get(i) instanceof NonPlayerShip) {
						
						tempNps = (NonPlayerShip) worldList.get(i);
						count++;
						break;
						
					}
					
				}
				if(count == 2) {
					
					worldList.remove(tempAsteroid);
					worldList.remove(tempNps);
					
				}
				else
					System.out.println("Error! Insufficient number of required objects in the game\n");
			
			}
			else
				System.out.println("Error! Insufficient number of required objects in the game\n");

		}
		else
			System.out.println("Error! Insufficient number of required objects in the game\n");
	}
	
	/*Method to tell the game world that the time or the 'game clocked' has ticked. Each tick of the 
	 * game clock tells all movable objects to update their positions, every missile's fuel level
	 * is reduced by one and any missiles which are depleted must be removed. Each space station toggles 
	 * its blinking light if the tick is zero and the game time elapsed is incremented by one. 
	 */
	public void tick() {
		
		time++;
		
		if(!worldList.isEmpty()) {
			
			for(int i = 0; i< worldList.size(); i++) {
				
				if(worldList.get(i) instanceof IMovable) {
					
					IMovable mObj = (IMovable) worldList.get(i);
					mObj.move();
					
					if(mObj instanceof Missile) {
						
						Missile tempMissile = (Missile) worldList.get(i);
						if(tempMissile.getFuel() == 0)
							worldList.remove(tempMissile);
						else
							tempMissile.decreaseFuelLevel();
					}
					
				}
				
			}
			if(worldList.contains(spaceStation)) {
				
				if(spaceStation.getBlinkRate() % time == 0)
					spaceStation.toggleBlink();
				
			}
			
		}
		
	}
	
	/*Method to print the current state of the game, which includes current score, number of missiles,
	 * current time elapsed.
	 */
	public void print() {
		
		System.out.println("\nGame State:\n*********************************************************************************************************\n");
		System.out.println("Current Score: " + score + "\n");
		
		if(worldList.contains(ps)) 			
			System.out.println("Number of missiles in player ship: " + ps.getMissileCount() + "\n");
		else
			System.out.println("Number of missiles in player ship: [Playership not initialized]\n");
		
		System.out.println("Time elapsed: " + time + "\n");
		System.out.println("Lives remaining: " + life + "\n");
		System.out.println("*********************************************************************************************************\n");
	}
	
	// Method to print the current world state
	public void map(){
		
		if(worldList.isEmpty()) {
			
			System.out.println("There are no objects in the game!\n\n");
			
		}
		else {
			for(GameObject temp : worldList) {
				
				System.out.print(temp);
				
			}
			
			System.out.println();
			
		}
		
	}
	
	//Method to quit the game. This method asks the user for confirmation before exiting from the game
	public void quit() {	
		
		new ClosingApp();
		
	}
	
	//Method to initiate the game over sequence. This is called whenever the number of lives for the player has reacher 0
	public void gameOver() {
		
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx     Game Over !!!     xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.exit(0);
		
	}
	
}
