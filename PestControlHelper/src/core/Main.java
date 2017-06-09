package core;


import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.wrappers.interactive.NPC;

@ScriptManifest(author = "itspeaches", name = "Cow Killer", version = 1.0, description = "Kills cows south of Falador, banks hides", category = Category.COMBAT)
public class Main extends AbstractScript {

public int currentstate;
public boolean cowhides;

public Area cows = new Area(3023, 3313, 3043, 3300);
public Area lumbridge = new Area(3217, 3224, 3226, 3214);
	public void onStart() {
		currentstate = 1;
		log("Thank you for killing the cows");
	}
	
	private enum State {
		FIGHT, DEATHWALK, BANK, WAIT
	};

	private State getState() {
	        if (currentstate == 1)
	            return State.FIGHT;
	        if (currentstate == 2)
	            return State.BANK;
	        if(currentstate == 3)
	        	return State.DEATHWALK;
	        return State.WAIT;
	    }

	public void onExit() {

	}

	@SuppressWarnings({ "deprecation" })
	@Override
	public int onLoop() {
		switch (getState()) {
		case FIGHT:
		
		
		if(lumbridge.contains(getLocalPlayer())){
			log("we're in lumbridge, deathwalking back");
			currentstate = 3;
			break;
		}
			
		if(getPlayers().myPlayer().isInCombat()){
			log("in combat, sleeping");
			sleep(Calculations.random(300, 1258));
			antiBan();
		}else{

		if(getGroundItems().closest("Cowhide") != null){
			log("cowhide spotted, looting");
			getGroundItems().closest("Cowhide").interact("Take");
			sleepUntil(() -> getLocalPlayer().isStandingStill(), Calculations.random(1200, 3000));}
		}
			NPC cow;
			cow = getNpcs().closest(npc -> npc != null && npc.getName() != null && npc.getName().equals("Cow") && !npc.isInCombat() && npc.getInteractingCharacter() == null);
			if(cow != null && !cow.isInCombat()){
				log("cows here, trying to attack");
				cow.interact("Attack");
			}
		
		
		
		if(getInventory().isFull()){
			log("inventory is full, switching to bank state");
			currentstate = 2;
		}
			break;
			
		case BANK:
			if(getInventory().isFull()){
				log("walking to nearest bank");
				getBank().openClosest();
				sleep(455, 2241);
			}
			
			if(getBank().isOpen()){
				log("bank is open, depositing cowhide");
				getBank().depositAll("Cowhide");
				sleep(100);
				getBank().close();
			}
			
			if(!getInventory().contains("Cowhide")){
				log("no cowhides detected, switching to deathwalk to walk back");
				currentstate = 3;
			}
			break;
			
		case DEATHWALK:
			getWalking().walk(cows.getRandomTile());
			sleepUntil(() -> getLocalPlayer().isStandingStill(), Calculations.random(1200, 5000));
			
			if(cows.contains(getLocalPlayer())){
				currentstate = 1;
			}
			break;
		case WAIT:
//code to wait here
			break;
		}
		return Calculations.random(500, 600);
	}

	
	public void antiBan() {
		int random = Calculations.random(1, 250);

		if (random == 1) {
		if (!getTabs().isOpen(Tab.STATS)) {
		getTabs().open(Tab.STATS);
		getSkills().hoverSkill(Skill.STRENGTH);
		sleep(Calculations.random(1000, 2000));
		getTabs().open(Tab.INVENTORY);
		}
		} else if (random <= 10) {
		if (!getTabs().isOpen(Tab.INVENTORY)) {
		getTabs().open(Tab.INVENTORY);
		}
		} else if (random <= 15) {
		getCamera().rotateToTile(cows.getRandomTile());
		} else if (random <= 20) {
		getCamera().rotateToEntity(getLocalPlayer());
		} else if (random <= 88) {
		if (getMouse().isMouseInScreen()) {
		if (getMouse().moveMouseOutsideScreen()) {
		sleep(Calculations.random(1500, 3000));
		}
		}
		}
	}
	
}
	
