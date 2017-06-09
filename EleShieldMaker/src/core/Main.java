package core;

import org.tbot.internal.AbstractScript;
import org.tbot.internal.Manifest;
import org.tbot.internal.ScriptCategory;
import org.tbot.internal.handlers.LogHandler;
import org.tbot.methods.Bank;
import org.tbot.methods.GameObjects;
import org.tbot.methods.GroundItems;
import org.tbot.methods.Players;
import org.tbot.methods.Random;
import org.tbot.methods.Time;
import org.tbot.methods.tabs.Inventory;
import org.tbot.methods.walking.Path;
import org.tbot.methods.walking.Walking;
import org.tbot.util.Condition;
import org.tbot.wrappers.Area;
import org.tbot.wrappers.GameObject;
import org.tbot.wrappers.Tile;

@Manifest(version = 1, name = "EleShieldMaker", description = "attempt 1", category = ScriptCategory.OTHER, openSource = false, authors = "Steve")
public class Main extends AbstractScript{

	Tile wall = new Tile(2709, 3494, 0);
	Path towall = Walking.findLocalPath(wall);
	Area workshop = new Area(2694, 9866, 2740, 9925);
	Tile furnace = new Tile(2721, 9877, 0);
	Path tofurnace = Walking.findLocalPath(furnace);
	Tile inwall1 = new Tile(2709, 3496, 0);
	Tile inwall2 = new Tile(2710, 3496, 0);
	GameObject furnaceobject = GameObjects.getNearest("Furnace");
	
	public enum State{
		BAR, ORE, WAIT;
	}
	
private State getState(){
	LogHandler.log(workshop.contains(Players.getLocal()));
	
	if(Inventory.contains("Hammer")){
		return State.BAR;
	}
	
	if(!Inventory.contains("Hammer")){
			return State.ORE;
		}


		
		return State.WAIT;
	}

	@Override
	public int loop() {
		switch (getState()) {
		

			
		case BAR:
		//WITHDRAW ORE AND COAL
		LogHandler.log("BAR STATE");
		if(Inventory.getCount("Coal") != 20 || Inventory.getCount("Elemental Ore") != 5){
			Bank.openNearestBank();
			Time.sleep(4000, 4500);
			if(!Inventory.contains("Slashed book")){
				Bank.withdraw("Slashed book", 1);
			}
			
			Time.sleepUntil(new Condition(){
				public boolean check(){
					return Inventory.contains("Slashed Book");
					}
			}, Random.nextInt(1800));
			
			if(!Inventory.contains("Battered key")){
				Bank.withdraw("Battered key", 1);
			}
			
			Time.sleepUntil(new Condition(){
				public boolean check(){
					return Inventory.contains("Battered key");
					}
			}, Random.nextInt(1800));
			
			
			Bank.withdraw("Coal", 20);
			
			Time.sleepUntil(new Condition(){
				public boolean check(){
					return Inventory.getCount("Coal") == 20;
					}
			}, Random.nextInt(1800));
			
			Bank.withdraw("Elemental Ore", 5);
			
			Time.sleepUntil(new Condition(){
				public boolean check(){
					return Inventory.getCount("Elemental ore") == 5;
					}
			}, Random.nextInt(1800));
		}
		
		//WALK AND SMITH
		else{
			
			
			if(!workshop.contains(Players.getLocal())){
			towall.traverse();
			
			Time.sleepUntil(new Condition(){
				public boolean check(){
					return Players.getLocal().getLocation() == wall;
				}
			}, Random.nextInt(3500, 4800));
			
			if(wall.distance() == 0){
				LogHandler.log("location is wall");
			GameObjects.getNearest("Odd looking wall").interact("Open");
			Time.sleep(6000, 8999);
			
			GameObjects.getNearest("Staircase").interact("Climb-down");
			
			Time.sleep(6000, 6999);
			}
			
			else{ towall.traverse();}
			
			
			
			} else {
				
				tofurnace.traverse();
				
				Time.sleep(4000, 4500);
				
				Inventory.useItemOn("Elemental ore", furnaceobject);
				
				
			}
			
			
			
			
			
		
		
			
		//
			break;
		}
		case ORE:
		
		//WALK TO LADDER
		if(!workshop.contains(Players.getLocal())){
			
		}
		//WALK TO ORE
		int x = Inventory.getCount("Elemental ore");
			
			if(GroundItems.getNearest("Elemental ore")!= null){
				GroundItems.getNearest("Elemental ore").pickUp();
				
				Time.sleepUntil(new Condition(){
					public boolean check(){
						return Inventory.getCount("Elemental ore")== x+1;
					}
				}, Random.nextInt(800));
			}
			
			//
		//BACK TO BANK	
			
			break;
			
		case WAIT:
			Time.sleep(600);
			break;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		}
		return 1000;
	}

}
