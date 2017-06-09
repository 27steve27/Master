package task;

import org.tbot.internal.handlers.LogHandler;
import org.tbot.methods.Bank;
import org.tbot.methods.Time;
import org.tbot.methods.tabs.Inventory;
import org.tbot.methods.walking.Path;
import org.tbot.methods.walking.Walking;
import org.tbot.util.Condition;
import org.tbot.wrappers.Tile;

import core.Bot;

public class LookForClueTask implements Task {

    @Override
    public void execute() {
    	
	if(Inventory.contains("Clue scroll (medium)") || Inventory.contains ("Reward casket (medium)")){
		//IF CLUE OR CASKET IN INVENTORY
		
	    LogHandler.log("Found clue or casket in INVENTORY...");
	    Bot.doClueTask = true;
		Bot.doTaskAfterLogin = false;
		 	
	}
	else{
		
		while(!Bank.isOpen()){
	Bank.openNearestBank();
		}
	
		Time.sleepUntil(new Condition() {
            @Override
            public boolean check() {
                return Bank.isOpen();
            }
        });
		
	if(Bank.contains("Clue scroll (medium)") || Bank.contains("Reward casket (medium)")){
	    LogHandler.log("No clue or casket found in INVENTORY...");
		LogHandler.log("Found clue or casket in BANK...");

		Bot.doClueTask = true;
		Bot.doTaskAfterLogin = false;
		
	}
	    else{
	    //WE DIDNT FIND A CLUE	
	   
		LogHandler.log("No clue or casket found in BANK...");
		Bot.obtainClue = true;
		Bot.doTaskAfterLogin = false;
		
	    }
	}
    }
    
	


    @Override
    public String toString() {
	return "Looking for clue..";
    }

    @Override
    public boolean validate() {
	return Bot.doTaskAfterLogin;
    }
    
   /* public boolean isClueOrCasketInInventory(){
	return Inventory.contains("Clue scroll (medium)") || Inventory.contains("Reward casket (medium)");
    }
    
    public boolean isClueOrCasketInBank(){
	Bank.openNearestBank();
	return Bank.contains("Clue scroll (medium)") || Bank.contains("Reward casket (medium)");
    }
*/
}
