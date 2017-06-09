package core;

import java.awt.Graphics;

import org.tbot.internal.AbstractScript;
import org.tbot.internal.Manifest;
import org.tbot.internal.ScriptCategory;
import org.tbot.internal.event.listeners.PaintListener;
import org.tbot.internal.handlers.LogHandler;
import org.tbot.methods.Camera;
import org.tbot.methods.GameObjects;
import org.tbot.methods.Mouse;
import org.tbot.methods.Players;
import org.tbot.methods.Random;
import org.tbot.methods.Time;
import org.tbot.util.Condition;
import org.tbot.wrappers.GameObject;
import org.tbot.wrappers.Tile;

@Manifest(version = 1, name = "PestControlHelper", description = "attempt 1", category = ScriptCategory.OTHER, openSource = false, authors = "Steve")
public class Main extends AbstractScript implements PaintListener {
	
	public int currentstate;
	
	Tile docks = new Tile(2644, 2644, 0);
	Tile boat = new Tile(2640, 2644, 0);

	
	public void onRepaint(Graphics arg0) {

		
	}

	
	public boolean onStart(){
		currentstate =1;
		return true;
		
	}
	
	public enum State{
		BOAT, WAIT;
	}
	
	public State getState(){
		if(currentstate == 1){
			return State.BOAT;
		}
	
		
		else{
		return State.WAIT;
		}
		
		
	}
	
	
	
	@Override
	public int loop() {
	
		switch(getState()){
		case BOAT:
			if(docks.distance() < 1){
				LogHandler.log("On Dock");
				GameObject gangplank = GameObjects.getNearest("Gangplank");
				if(gangplank != null){
					gangplank.interact("Cross");
					Time.sleepUntil(new Condition(){
						public boolean check(){
							return Players.getLocal().getLocation() == boat;
						}
					}, Random.nextInt(1200, 1600));
					
					
				}
				
			}
			
		if(boat.distance() < 1){
			LogHandler.log("in Boat");
			currentstate = 3;
		}
		
		if(boat.distance() > 1 && docks.distance() > 1){
			LogHandler.log("IN GAME");
		}
		
		break;
		
		case WAIT:
			LogHandler.log("Waiting");
			Antiban();
			
			Time.sleep(600, 15000);
			
			
			
			if(Players.getLocal().getLocation() == docks){
				currentstate = 1;
			}
			
			if(boat.distance() > 1){
				LogHandler.log("IN GAME");
			}
			break;
		
			
			
			
		}
		return 600;
	}
	public void Antiban(){
		int random = Random.nextInt(1, 250);
		
		if(random < 10){
			LogHandler.log("moving mouse randomly");
			Mouse.moveRandomly();
		}
		if(random  == 15){
			Camera.rotateAndTiltRandomly();
			LogHandler.log("moving camera randomly");
		}
		if(random > 15 && random < 20){
			Camera.rotateRandomly();
			LogHandler.log("moving camera randomly");
		}
		
		if(random > 20 && random < 24){
			Camera.tiltRandomly();
			LogHandler.log("moving camera randomly");
			
		if(random >= 24 && random < 30){
			LogHandler.log("Sleeping");
			Time.sleep(4850, 15247);
			LogHandler.log("Waking");
		}
			
			
		}
	}
}
	
