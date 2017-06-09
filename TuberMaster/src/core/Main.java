package core;




import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Date;

import org.tbot.internal.AbstractScript;
import org.tbot.internal.Manifest;
import org.tbot.internal.ScriptCategory;

import org.tbot.internal.event.listeners.PaintListener;
import org.tbot.internal.handlers.LogHandler;
import org.tbot.methods.Bank;
import org.tbot.methods.Camera;
import org.tbot.methods.Combat;
import org.tbot.methods.Game;
import org.tbot.methods.GameObjects;
import org.tbot.methods.Mouse;
import org.tbot.methods.Npcs;
import org.tbot.methods.Players;
import org.tbot.methods.Random;
import org.tbot.methods.Skills;
import org.tbot.methods.Skills.Skill;
import org.tbot.methods.Time;
import org.tbot.methods.Widgets;
import org.tbot.methods.input.keyboard.Keyboard;
import org.tbot.methods.tabs.Inventory;
import org.tbot.methods.walking.Path;
import org.tbot.methods.walking.Walking;
import org.tbot.methods.web.banks.WebBanks;
import org.tbot.util.Condition;
import org.tbot.wrappers.Area;
import org.tbot.wrappers.GameObject;
import org.tbot.wrappers.NPC;
import org.tbot.wrappers.Tile;
import org.tbot.wrappers.WidgetChild;

@SuppressWarnings("deprecation")
@Manifest(version = 1.1, name = "TuberMaster", description = "attempt 1", category = ScriptCategory.OTHER, openSource = false, authors = "Steve")
public class Main extends AbstractScript implements PaintListener {

	public int currentstate;
	public int startxp;
	public int currentxp;
	public int gainedxp;
	public String currenttask;
	public int antidote1;
	public int antidote2;
	public int antidote3;
	public int antidote4;
	public int antidotecount;
	public int xyz;
	public int abc;
	
	
	public long starttime;
	public long currenttime;
	
	
	Area choppingzone = new Area(2803, 3071, 2816, 3082);
	
	public int gouttubers;
	public int banktubers;
	
	public boolean onStart() {
		LogHandler.log("Started");
		gouttubers=0;
		banktubers = 0;
		abc = 0;
		starttime = System.currentTimeMillis();
		currentstate = 6;
		startxp = Skills.getExperience(Skill.WOODCUTTING);
		xyz = 0;
		return true;
	}
	

	
	private enum State {
		CHOP, COMBAT, DROP, WAIT, BANK, BROODOO, CHARTER;
	};

	
	
	Tile safespot = new Tile(2787, 3102, 0);
	Tile village = new Tile(2809, 3076, 0);
	Tile dock = new Tile(2799, 3413, 0);
	Tile ship = new Tile(2763, 3238, 1);
	
	
	
	
	final WidgetChild Brimhaven = Widgets.getWidget(95, 16);
	final WidgetChild Continue = Widgets.getWidget(299, 1);
	final WidgetChild Okay = Widgets.getWidgetByText("Okay");

	private State getState() {
		
	
		
		if(currentstate == 1){
			currenttask = "Banking";
			return State.BANK;}
		
		
		if(currentstate == 3){
			currenttask = "Running from Broodoo";
			return State.BROODOO;
		}
		
		
		if (currentstate == 4) {
			currenttask = "In Combat";
			return State.COMBAT;
		}
		
		if (currentstate == 5) {
			currenttask = "Dropping";
			return State.DROP;
		}

		if (currentstate == 6) {
			currenttask = "Looking for Tubers";
			return State.CHOP;
		}
		
		if (currentstate == 7){
			currenttask = "Going back to village";
			return State.CHARTER;
		}
		return State.WAIT;
	}


	public void onStop() {
		LogHandler.log("exit!");
	}

	public void onPaint(Graphics2D g) {

	}

	@Override
	public int loop() {
		antidote1 = Inventory.getCount("Antidote++(1)");
		antidote2 = Inventory.getCount("Antidote++(2)");
		antidote3 = Inventory.getCount("Antidote++(3)");
		antidote4 = Inventory.getCount("Antidote++(4)");
		antidotecount = antidote1 + antidote2 + antidote3 + antidote4;
		gouttubers = Inventory.getCount("Gout tuber");
		
		currentxp = Skills.getExperience(Skill.WOODCUTTING);
		gainedxp = currentxp - startxp;
		Antiban();

		switch (getState()) {
		case WAIT:
			Time.sleep(500,700);
			break;
			
		case BROODOO:
			
			LogHandler.log("STATE = BROODOO");
			NPC broodoo2 = Npcs.getNearest("Broodoo victim");
			if(broodoo2 != null){
				

			
				Game.instaHopNextP2P();
				currentstate = 6;
		}
		
				
				
		case DROP:
			LogHandler.log("STATE = DROP");
			LogHandler.log("dropping shit");
			Inventory.dropAll(229);
			Inventory.dropAll(6281);
			abc = 0;
			
			
			currentstate = 6;
			break;
			
		case COMBAT:
			GameObject gouttuber1 =  GameObjects.getNearest(9033);
			if(gouttuber1 != null){
				Inventory.useItemOn("Spade", gouttuber1);
				Time.sleep(1200, 2600);
			LogHandler.log("im sensing a tuber");
			}

			LogHandler.log("detected combat");
			NPC broodoo1 = Npcs.getNearest("Broodoo victim");
			
			if(Combat.isPoisoned()){
				if(Inventory.contains("Antidote++(1)")){
					Inventory.getFirst("Antidote++(1)").interact("Drink");
				}
				else{
					if(Inventory.contains("Antidote++(2)")){
						Inventory.getFirst("Antidote++(2)").interact("Drink");
					}
					else{
						if(Inventory.contains("Antidote++(3)")){
							Inventory.getFirst("Antidote++(3)").interact("Drink");
						}
						else{
							if(Inventory.contains("Antidote++(4)")){
								Inventory.getFirst("Antidote++(4)").interact("Drink");
							}
						}
					}
				}
			}
			
//IF WE'RE POISONED
			
			
			//EAT IF LOW
			if(Players.getLocal().getHealthPercent() < 35) {
				Inventory.getFirst("Shark").interact("Eat");
				LogHandler.log("Ate Shark");
			}			
			

			if(Inventory.contains("Rune scimitar")){
				Inventory.getFirst("Rune scimitar").interact("Wield");
				
				Time.sleepUntil(new Condition(){
					public boolean check(){
						return Inventory.getCount("Rune scimitar") == 0;
					}
				}, Random.nextInt(900, 1100));
					}
			
			if(broodoo1 != null){
				currentstate =3;
				break;
			}
			
			if(!Players.getLocal().inCombat()){
				currentstate = 6;
				break;
			}
			
			if(Inventory.getCount("Shark") < 1){
				currentstate = 1;
				break;
			}
			
			
			

				break;
		case CHOP:
			LogHandler.log("STATE = CHOP");
			GameObject gouttuber =  GameObjects.getNearest(9033);
			GameObject jungle = GameObjects.getNearest("Light jungle");
			Tile chopspot = new Tile(2810, 3077, 0);
			NPC broodoo = Npcs.getNearest("Broodoo victim");
			
			if(!choppingzone.contains(Players.getLocal())){
				Path tochopspot = Walking.findPath(chopspot);
				if(tochopspot != null){
				tochopspot.traverse();
				}
				Time.sleepUntil(new Condition(){
					public boolean check(){
						return chopspot.distance() < 2;
					}
				}, 800);
			}
			
			
			if(jungle != null && !jungle.hasAction("Hack")){
				jungle = GameObjects.getNearest(9010);
				LogHandler.log("Switching to new tree");
				Time.sleep(120, 7000);
			}
			
			
			
			if(Inventory.isFull() && Inventory.contains(6281)){
				if(Inventory.hasItemSelected()){
					Inventory.deselectItem();
				}
				currentstate = 5;
				break;
			}
			
			if(abc ==0 && Inventory.getCount("6281") > 7){
				int randomdrop0;
				
				randomdrop0 = Random.nextInt(0, 20);
				if(randomdrop0 == 1){
					currentstate = 5;
				} else{
					abc = 1;
				}
			}
				if(abc ==6 && Inventory.getCount("6281") > 15){
					int randomdrop6;
					
					randomdrop6 = Random.nextInt(0, 8);
					if(randomdrop6 == 1){
						currentstate = 5;
					} else{
						abc = 7;
					}
					
					if(abc ==1 && Inventory.getCount("6281") > 8){
						int randomdrop1;
						
						randomdrop1 = Random.nextInt(0, 18);
						if(randomdrop1 == 1){
							currentstate = 5;
						} else{
							abc = 2;
						}
					}
						if(abc ==2 && Inventory.getCount("6281") > 9){
							int randomdrop2;
							
							randomdrop2 = Random.nextInt(0, 17);
							if(randomdrop2 == 1){
								currentstate = 5;
							} else{
								abc = 3;
							}
						}
							if(abc ==3 && Inventory.getCount("6281") > 10){
								int randomdrop3;
								
								randomdrop3 = Random.nextInt(0, 14);
								if(randomdrop3 == 1){
									currentstate = 5;
								} else{
									abc = 4;
								}
							}
								if(abc == 4 && Inventory.getCount("6281") > 12){
									int randomdrop4;
									
									randomdrop4 = Random.nextInt(0, 12);
									if(randomdrop4 == 1){
										currentstate = 5;
									} else{
										abc = 5;
									}
								}
									if(abc ==5 && Inventory.getCount("6281") > 13){
										int randomdrop5;
										
										randomdrop5 = Random.nextInt(0, 10);
										if(randomdrop5 == 1){
											currentstate = 5;
										} else{
											abc = 6;
										}
									}
									
									if(abc ==7 && Inventory.getCount("6281") > 16){
										int randomdrop7;
										
										randomdrop7 = Random.nextInt(0, 7);
										if(randomdrop7 == 1){
											currentstate = 5;
										} else{
											abc = 8;
										}
									}
									if(abc ==8 && Inventory.getCount("6281") > 18){
										int randomdrop8;
										
										randomdrop8 = Random.nextInt(0, 9);
										if(randomdrop8 == 1){
											currentstate = 5;
										} else{
											abc = 9;
										}
									}
									
									if(abc ==9 && Inventory.getCount("6281") > 20){
										int randomdrop9;
										
										randomdrop9 = Random.nextInt(0, 7);
										if(randomdrop9 == 1){
											currentstate = 5;
										} else{
											abc = 10;
										}
									}
				
			}
			if(gouttuber != null){
				Inventory.useItemOn("Spade", gouttuber);
				Time.sleep(1200, 2600);
			LogHandler.log("im sensing a tuber");
			}
			else{
				if(Inventory.hasItemSelected()){
					Inventory.deselectItem();
				}
				
				//EAT IF LOW
				if(Players.getLocal().getHealthPercent() < 35) {
					Inventory.getFirst("Shark").interact("Eat");
					LogHandler.log("Ate Shark");
				}
				
				
				if(jungle != null && !jungle.isOnScreen()){
					currentstate = 2;
					break;
				}
				
				if(Players.getLocal().inCombat()){
					currentstate = 4;
					break;
				}else{
			
				
				
			if(broodoo != null){
				currentstate = 3;
				break;
			}
			else{
				
			if(Inventory.contains("Red topaz machete"))	{
				Inventory.getFirst("Red topaz machete").interact("Wield");
				
				Time.sleepUntil(new Condition(){
					public boolean check(){
						return Inventory.getCount("Red topaz machete") == 0;
					}
				}, Random.nextInt(900, 1100));
					}
				
			}
				
			if (jungle != null) {
				if(Players.getLocal().getAnimation() == -1){
				LogHandler.log("Hacking jungle");
				jungle.interact("Hack");
				
				Time.sleep(700, 900);
			}	
				

			}
			if(Inventory.getCount("Gout tuber") > 4 || Inventory.getCount("Shark") < 1 || antidotecount < 1){
				currentstate = 1;
				xyz = 0;
			}
				}
			}
			break;
			
			
		case BANK:
			
			LogHandler.log("BANK STATE");
			
			currentstate = 1;
			
			if(Inventory.contains("Red topaz machete")){
				Inventory.getFirst("Red topaz machete").interact("Wield");
			}
			
			if(Inventory.getCount("Shark") < 10 || Inventory.getCount("Spade") < 1 || Inventory.getCount("Antidote++(4)") < 2 || Inventory.getCount("Camelot teleport") < 1 || Inventory.getCount("Rune Scimitar") < 1 || Inventory.getCount("Coins") < 1000){
				if(!Bank.isOpen()){
				Bank.openBank(WebBanks.CATHERBY_BANK);
				
				Time.sleepUntil(new Condition(){
					public boolean check(){
						return Bank.isOpen();
					}
				}, Random.nextInt(1500,1800));
				}else{
					
				
				if(xyz == 0){
					banktubers = Inventory.getCount("Gout tuber");
					Bank.depositAll();
					Time.sleep(650, 950);
					xyz = 1;
				}
					
				if(Inventory.contains("Gout tuber")){
					Time.sleep(450);
					Bank.depositAll("Gout tuber");
					Time.sleepUntil(new Condition(){
						public boolean check(){
						return Inventory.getCount("Gout Tuber") == 0;
						}
					}, Random.nextInt(1900, 2200));
					
				}
				
				if(Inventory.getCount("Shark")< 10){
				LogHandler.log("We dont have sharks");
				Bank.withdraw("Shark", 10);
				LogHandler.log("withdrawing sharks");
				}
				Time.sleepUntil(new Condition(){
					public boolean check(){
					return Inventory.getCount("Shark") == 10;
					}
				}, Random.nextInt(1900, 2200));
				LogHandler.log("We have sharks, waking up");
				
				
				if(Inventory.getCount("Spade")< 1){
					
					Bank.withdraw("Spade", 1);
					LogHandler.log("withdrawing spade");
					}
					Time.sleepUntil(new Condition(){
						public boolean check(){
						return Inventory.getCount("Spade") == 1;
						}
					}, Random.nextInt(1900, 2200));
					
				
				if(Inventory.getCount("Rune scimitar") < 1){
				Bank.withdraw("Rune scimitar", 1);
				}
				Time.sleepUntil(new Condition(){
					public boolean check(){
					return Inventory.getCount("Rune scimitar") == 1;
					}
				}, Random.nextInt(1900, 2200));
				
				
				if(Inventory.getCount("Camelot teleport") < 1){
					Bank.withdraw("Camelot teleport", 1);
				}
					Time.sleepUntil(new Condition(){
						public boolean check(){
						return Inventory.getCount("Camelot teleport") == 1;
						}
					}, Random.nextInt(1900, 2200));
					
				
				if(Inventory.getCount("Antidote++(4)") < 2){
					Bank.withdraw("Antidote++(4)", 2);
				}
					Time.sleepUntil(new Condition(){
						public boolean check(){
						return Inventory.getCount("Antidote++(4)") == 2;
						}
					}, Random.nextInt(1900, 2200));
					
				
				if(Inventory.getCount("Coins") < 1000){
				Bank.withdraw("Coins", 1000);
				}
				Time.sleepUntil(new Condition(){
					public boolean check(){
					return Inventory.getCount("Coins") == 1000;
					}
				}, Random.nextInt(1900, 2200));
				
				if(Inventory.getCount("Shark") == 10 && Inventory.getCount("Spade") == 1 && Inventory.getCount("Rune scimitar") == 1 && Inventory.getCount("Coins") == 1000 && Inventory.getCount("Camelot teleport") == 1 && Inventory.getCount("Antidote++(4)") == 2){
				Bank.close();
				
				Time.sleep(1500);
				}
			}
			}
			if(Inventory.getCount("Shark") == 10 && Inventory.getCount("Spade") == 1 && Inventory.getCount("Rune scimitar") == 1 && Inventory.getCount("Coins") == 1000 && Inventory.getCount("Camelot teleport") == 1 && Inventory.getCount("Antidote++(4)") == 2){
				
				
				
				Path todock = Walking.findPath(dock);
				if(todock != null){
				todock.traverse();
				}
			}
			
			if(dock.distance() < 10){
				currentstate = 7;
			}
			
		break;
		
		case CHARTER:
			LogHandler.log("Charter CASE");
			NPC charter = Npcs.getNearest(1331);
				if(charter != null){
				charter.interact("Charter");
			Time.sleep(5000);
				}
				if(Brimhaven.isOnScreen()){
					
					LogHandler.log("Trying to click brimhaven");
					Time.sleep(4200);
					Brimhaven.click();
					LogHandler.log("clicked brimhaven");
					Time.sleep(3000);
					Keyboard.typeKey((char) 32);
					Time.sleep(3000);
					Keyboard.typeKey((char) 49);
					Time.sleep(5000);
					
					}
				
			
			
			
			
			if(Inventory.getCount("Coins") == 520 || Inventory.getCount("Coins") == 290 || Inventory.getCount("Coins") == 490){
				if(ship.distance() == 0){
				Time.sleep(5000);
				GameObject gangplank = GameObjects.getNearest("Gangplank");
				if(gangplank != null){
				gangplank.interact("Cross");
				Time.sleep(1200);
				LogHandler.log("Trying to cross gp");
				}
				}
				Path tovillage = Walking.findPath(village);
				if(tovillage != null){
				tovillage.traverse();
				}
			}
			
			if(village.distance() < 3){
				currentstate = 6;
			}
			
			break;
		
		}
			
		
		
	
		return 600;
	}


	@Override
	public void onRepaint(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(300, 0, 225, 110);
		g.setColor(Color.WHITE);
		g.drawString("Gout tubers : " + (gouttubers + banktubers), 325, 20);
		g.drawString("Jungles chopped : " + (gainedxp / 64), 325, 45);
		g.drawString("Current Task : " + currenttask, 325, 70);
		currenttime = System.currentTimeMillis();
		Date myTime = new Date((currenttime - starttime) / 1000);
		g.drawString("Run time: " + myTime.getTime() , 325, 95);

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
			
		if(random > 24 && random < 30){
			LogHandler.log("Sleeping");
			Time.sleep(4850, 15247);
			LogHandler.log("Waking");
		}
		
		if(random > 31 && random < 33){
			GameObject medjungle = GameObjects.getNearest("Medium Jungle");
			if(medjungle != null){
			medjungle.interact("Examine");
			}
		}
			
		}
		
	}
	
}
			
	
					

		
