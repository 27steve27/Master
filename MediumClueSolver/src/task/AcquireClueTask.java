package task;

import org.tbot.internal.handlers.LogHandler;
import org.tbot.methods.Bank;
import org.tbot.methods.Time;
import org.tbot.methods.tabs.Inventory;
import org.tbot.wrappers.Item;

import core.Bot;

public class AcquireClueTask implements Task{

	@Override
	public void execute() {
		
		
		
		LogHandler.log("Let's loot imps til we get one");
		
		Bank.openNearestBank();
		Time.sleep(750);
		Bank.depositAll();
		if(Bank.contains(20545)){
			Bank.withdraw(20545, 1);
			Time.sleep(400);
			Bank.close();
			Time.sleep(600);
			
			Item casket = Inventory.getFirst(20545);
			
			casket.interact("Loot");
			Time.sleep(450);
			Bank.openNearestBank();
			Time.sleep(750);
			Bank.depositAll();
		}
		
		Time.sleep(600,800);
		Bank.withdraw(11248, 14);
		Bank.close();
		Time.sleep(650);
	if(!(Inventory.contains("Clue scroll (medium)")) && Inventory.contains(11248)){
		Item impling =Inventory.getFirst("Eclectic impling jar");
		impling.interact("Loot");
		Time.sleep(700, 1100);}
	if(!(Inventory.contains("Clue scroll (medium)")) && Inventory.contains(11248)){
		Item impling =Inventory.getFirst("Eclectic impling jar");
		impling.interact("Loot");
		Time.sleep(700, 1100);}
	if(!(Inventory.contains("Clue scroll (medium)")) && Inventory.contains(11248)){
		Item impling =Inventory.getFirst("Eclectic impling jar");
		impling.interact("Loot");
		Time.sleep(700, 1100);}
	if(!(Inventory.contains("Clue scroll (medium)")) && Inventory.contains(11248)){
		Item impling =Inventory.getFirst("Eclectic impling jar");
		impling.interact("Loot");
		Time.sleep(700, 1100);}
	if(!(Inventory.contains("Clue scroll (medium)")) && Inventory.contains(11248)){
		Item impling =Inventory.getFirst("Eclectic impling jar");
		impling.interact("Loot");
		Time.sleep(700, 1100);}
	if(!(Inventory.contains("Clue scroll (medium)")) && Inventory.contains(11248)){
		Item impling =Inventory.getFirst("Eclectic impling jar");
		impling.interact("Loot");
		Time.sleep(700, 1100);}
	if(!(Inventory.contains("Clue scroll (medium)")) && Inventory.contains(11248)){
		Item impling =Inventory.getFirst("Eclectic impling jar");
		impling.interact("Loot");
		Time.sleep(700, 1100);}
	if(!(Inventory.contains("Clue scroll (medium)")) && Inventory.contains(11248)){
		Item impling =Inventory.getFirst("Eclectic impling jar");
		impling.interact("Loot");
		Time.sleep(700, 1100);}
	if(!(Inventory.contains("Clue scroll (medium)")) && Inventory.contains(11248)){
		Item impling =Inventory.getFirst("Eclectic impling jar");
		impling.interact("Loot");
		Time.sleep(700, 1100);}
	if(!(Inventory.contains("Clue scroll (medium)")) && Inventory.contains(11248)){
		Item impling =Inventory.getFirst("Eclectic impling jar");
		impling.interact("Loot");
		Time.sleep(700, 1100);}
	if(!(Inventory.contains("Clue scroll (medium)")) && Inventory.contains(11248)){
		Item impling =Inventory.getFirst("Eclectic impling jar");
		impling.interact("Loot");
		Time.sleep(700, 1100);}
	if(!(Inventory.contains("Clue scroll (medium)")) && Inventory.contains(11248)){
		Item impling =Inventory.getFirst("Eclectic impling jar");
		impling.interact("Loot");
		Time.sleep(700, 1100);}
	if(!(Inventory.contains("Clue scroll (medium)")) && Inventory.contains(11248)){
		Item impling =Inventory.getFirst("Eclectic impling jar");
		impling.interact("Loot");
		Time.sleep(700, 1100);}
	if(!(Inventory.contains("Clue scroll (medium)")) && Inventory.contains(11248)){
		Item impling =Inventory.getFirst("Eclectic impling jar");
		impling.interact("Loot");
		Time.sleep(700, 1100);}

		
	else if(Inventory.contains("Clue scroll (medium)")){
		
		Bot.obtainClue = false;
		Bot.doClueTask = true;
		
	}
		
		
		
		
		
	}

	@Override
	public boolean validate() {
		return Bot.obtainClue;
	}

}
