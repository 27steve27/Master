package task;

import org.tbot.internal.handlers.LogHandler;
import org.tbot.methods.Bank;
import org.tbot.methods.GameObjects;
import org.tbot.methods.Npcs;
import org.tbot.methods.Players;
import org.tbot.methods.Time;
import org.tbot.methods.input.keyboard.Keyboard;
import org.tbot.methods.tabs.Equipment;
import org.tbot.methods.tabs.Inventory;
import org.tbot.methods.walking.Path;
import org.tbot.methods.walking.Walking;
import org.tbot.methods.web.banks.WebBanks;
import org.tbot.util.Condition;
import org.tbot.wrappers.Area;
import org.tbot.wrappers.Item;
import org.tbot.wrappers.NPC;
import org.tbot.wrappers.Tile;

import core.Bot;

public class ClueSolveTask implements Task{

	@Override
	public void execute() {

		LogHandler.log("Lets do this clue");
		
		char space = 32;
		
	Item spade = Inventory.getFirst("Spade");
	NPC uri = Npcs.getNearest("Uri");
		
		if(!Inventory.contains("Clue scroll (medium)")){
			Bank.withdraw("Clue scroll (medium)", 1);
		Bank.close();
		
		}
		
		
//CLUE 2827
		if(Inventory.contains(2827)){
			Tile cluespot = new Tile(3092, 3225,0); 
			Path path = Walking.findPath(cluespot);
			
			if(cluespot.distance() != 0){
				LogHandler.log("Walking to digspot");
				path.traverse();
				Time.sleep(600, 1200);	
				};
				if(cluespot.distance() == 0){	LogHandler.log("Arrived at Digspot");
				Time.sleep(1000,1500);
				LogHandler.log("Gonna try and dig");
				spade.interact("Dig");
			
					if(!Inventory.contains(2827)){
						LogHandler.log("Step Completed!");
		
					}
			}
		}
		
		
//CLUE 3596
		if(Inventory.contains(3596)){
			Tile cluespot = new Tile(2906, 3294, 0); 
			Path path = Walking.findPath(cluespot);
			
			if(cluespot.distance() != 0){
				LogHandler.log("Walking to digspot");
				path.traverse();
				Time.sleep(600, 1200);	
				};
				if(cluespot.distance() == 0){	LogHandler.log("Arrived at Digspot");
				Time.sleep(1000,1500);
				LogHandler.log("Gonna try and dig");
				spade.interact("Dig");
			
					if(!Inventory.contains(3596)){
						LogHandler.log("Step Completed!");
		
						
					}
			}
		}
//CLUE 7303 ** not working
		if(Inventory.contains(7303)){
			LogHandler.log("Doing Clue 7303");
			Tile cluespot = new Tile(3288, 3019, 0); 
			Path path = Walking.findPath(cluespot);
	
			while(!(Bank.isOpen())){
			Bank.openBank(WebBanks.SHANTAY_PASS_BANK);
			}
			
			while(!(Inventory.contains(1839))){ 	
			Bank.withdraw(1839, 1);
		}
			Bank.withdraw(1854, 1);
			LogHandler.log("Withdrew pass");
			Time.sleepUntil(new Condition() {
	             @Override
	             public boolean check() {
	                 return Inventory.contains(1854);
	             }
	         });
			
			Bank.depositAllEquipment();
			Bank.close();
			Time.sleepUntil(new Condition() {
	             @Override
	             public boolean check() {
	                 return Bank.close();
	             }
	         });
			
			LogHandler.log("going through pass");
			GameObjects.getNearest("Shantay pass").interact("Go-through");
			Time.sleep(3000,4000);
			
			
	

			LogHandler.log("We made it to the desert");
			if(cluespot.distance() != 0){
				LogHandler.log("Walking to digspot");
				path.traverse();
				Time.sleep(600, 1200);	
				};
				if(cluespot.distance() == 0){	LogHandler.log("Arrived at Digspot");
				Time.sleep(1000,1500);
				LogHandler.log("Gonna try and dig");
				spade.interact("Dig");
			
					if(!Inventory.contains(3596)){
						LogHandler.log("Step Completed!");
		
						
					}
			}
		}
		

//CLUE 10276
		if(Inventory.contains(10276)){
			LogHandler.log("Doing clue 10276");
			Tile cluespot = new Tile(2824, 3442, 0);
			Path path = Walking.findPath(cluespot);
			
			while(!(Inventory.contains(2961) && Inventory.contains(630) && Inventory.contains(1131))){
				Bank.openBank(WebBanks.CATHERBY_BANK);
				LogHandler.log("Going to Catherby Bank");
				if(!(Inventory.contains(2961))){
					Bank.withdraw(2961, 1);
					LogHandler.log("Withdrawing 2961");
				}
				
				if(!(Inventory.contains(630))){
					Bank.withdraw(630, 1);
					LogHandler.log("Withdrawing 630");
				}
				
				if(!(Inventory.contains(1131))){
					Bank.withdraw(1131, 1);
					LogHandler.log("Withdrawing 1131");
				}
				
			}
			
			LogHandler.log("Sleeping til we have the stuff in inventory");
			Time.sleepUntil(new Condition() {
				 @Override
	             public boolean check() {
	                 return Inventory.contains(2961) && Inventory.contains(1131) && Inventory.contains(630);
	             }
	         });
				
			if(Bank.isOpen()){
				Bank.close();
				LogHandler.log("Closed bank");
			}
			while(!(Equipment.contains(2961))){
			Inventory.interact(2961, "Wield");
			LogHandler.log("wielding sickle");
			}
			while(!(Equipment.contains(630))){
			Inventory.interact(630, "Wear");
			LogHandler.log("wearing 630");
		}
			while(!(Equipment.contains(1131))){
			Inventory.interact(1131, "Wear");
			LogHandler.log("wearing 1131");
			}
			
			while(Players.getLocal().getLocation() != cluespot){
				LogHandler.log("going to clue spot");
				path.traverse();
			}
			
			
		}
		
		
		
//CLUE 19734		
		if(Inventory.contains(19734) || Inventory.contains(19735)){
			Tile cluespot = new Tile(1562,3600,0);
			Path path = Walking.findPath(cluespot);
			NPC npc = Npcs.getNearest(6895);
			
	
				
				if(cluespot.distance() > 15){
					LogHandler.log("Walking to digspot");
					path.traverse();
					Time.sleep(600, 1200);	
					};
		
					if(cluespot.distance() < 15){	LogHandler.log("Arrived at Digspot");
						LogHandler.log("Arrived at Clue location");
						Time.sleep(1000,1500);
						LogHandler.log("speaking to npc");
						npc.interact("Talk-to");
						Time.sleep(1000,1500);
						Keyboard.pressKey(space);
						Time.sleep(100);
						Keyboard.releaseKey(space);
						Time.sleep(1000,1500);
						npc.interact("Talk-to");
						Time.sleep(1000,1500);
						Keyboard.pressKey(space);
						Time.sleep(100);
						Keyboard.releaseKey(space);
						Time.sleep(1000,1500);
						Keyboard.sendText("113", true);
						Time.sleep(1000,1500);
						Keyboard.pressKey(space);
						Time.sleep(1000,1500);
						Keyboard.releaseKey(space);
						Time.sleep(1000,1500);
						
						if(!Inventory.contains(19734) && (!Inventory.contains(19735))){
							LogHandler.log("Step Completed!");
		
							
						}
				}
		}
//CLUE 19762 ** not working
		
		if(Inventory.contains(19762) || Inventory.contains(19763)){
			Tile cluespot = new Tile(1633, 3800, 0);
			Path path = Walking.findPath(cluespot);
			NPC npc = Npcs.getNearest(7080);
			LogHandler.log("Distance" + cluespot.distance());
			
	
				
				if(cluespot.distance() == 0 ){
					LogHandler.log("Walking to digspot");
					path.traverse();
					LogHandler.log("Sleeping til we get there");
					//Time.sleepUntil();
					};
		
					if(!(cluespot.distance() == 0)){	LogHandler.log("Arrived at Digspot");
						LogHandler.log("Arrived at Clue location");
						Time.sleep(1000,1500);
						LogHandler.log("speaking to npc");
						npc.interact("Talk-to");
						Time.sleep(1000,1500);
						Keyboard.pressKey(space);
						Time.sleep(100);
						Keyboard.releaseKey(space);
						Time.sleep(1000,1500);
						Keyboard.pressKey(space);
						Time.sleep(100);
						Keyboard.releaseKey(space);
						Time.sleep(1000,1500);
						Keyboard.pressKey(space);
						Time.sleep(100);
						Keyboard.releaseKey(space);
						Time.sleep(1000,1500);
						npc.interact("Talk-to");
						Time.sleep(1000,1500);
						Keyboard.pressKey(space);
						Time.sleep(100);
						Keyboard.releaseKey(space);
						Time.sleep(1000,1500);
						Keyboard.sendText("9", true);
						Time.sleep(1000,1500);
						Keyboard.pressKey(space);
						Time.sleep(1000,1500);
						Keyboard.releaseKey(space);
						Time.sleep(1000,1500);
						
						if(!Inventory.contains(19762) && !Inventory.contains(19763)){
							LogHandler.log("Step Completed!");
		
							
						}
				}
		}
		
//CLUE 19772
		if(Inventory.contains(19772) || Inventory.contains(19773)){
			Tile cluespot = new Tile(3438, 9898,0);
			Path path = Walking.findPath(cluespot);
			NPC npc = Npcs.getNearest(3489);
			
	
				
				if(!(cluespot.distance() == 0)){
					LogHandler.log("Walking to digspot");
					path.traverse();
					Time.sleep(600, 1200);	
					};
		
					if(cluespot.distance() == 0){	LogHandler.log("Arrived at Digspot");
						LogHandler.log("Arrived at Clue location");
						Time.sleep(1000,1500);
						LogHandler.log("speaking to npc");
						npc.interact("Talk-to");
						Time.sleep(1000,1500);
						Keyboard.pressKey(space);
						Time.sleep(100);
						Keyboard.releaseKey(space);
						Time.sleep(1000,1500);
						Keyboard.pressKey(space);
						Time.sleep(100);
						Keyboard.releaseKey(space);
						Time.sleep(1000,1500);
						Keyboard.pressKey(space);
						Time.sleep(100);
						Keyboard.releaseKey(space);
						Time.sleep(1000,1500);
						npc.interact("Talk-to");
						Time.sleep(1000,1500);
						Keyboard.pressKey(space);
						Time.sleep(100);
						Keyboard.releaseKey(space);
						Time.sleep(1000,1500);
						Keyboard.sendText("7", true);
						Time.sleep(1000,1500);
						Keyboard.pressKey(space);
						Time.sleep(1000,1500);
						Keyboard.releaseKey(space);
						Time.sleep(1000,1500);
						
						if(!Inventory.contains(19772) && !Inventory.contains(19773)){
							LogHandler.log("Step Completed!");
		

						}
				}
		}
		
		
		
		
		
		
	//CLUE COMPLETED	
		
	if(Inventory.contains(20545)){
		LogHandler.log("CLUE COMPLETED!");
		Bot.doClueTask= false;
		Bot.obtainClue = true;
		}
	}
	
		
		

	@Override
	public boolean validate() {
		
		return Bot.doClueTask;
	}

}
