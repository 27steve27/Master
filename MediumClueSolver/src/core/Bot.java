package core;

import java.util.ArrayList;

import org.tbot.bot.TBot;
import org.tbot.internal.AbstractScript;
import org.tbot.internal.Manifest;
import org.tbot.internal.ScriptCategory;
import org.tbot.internal.handlers.LogHandler;

import task.AcquireClueTask;
import task.BlankTask;
import task.BreakTask;
import task.ClueSolveTask;
import task.LoginTask;
import task.LookForClueTask;
import task.OnDiedTask;
import task.OnPoisonedTask;
import task.Task;

@Manifest(version = 1, name = "Medium Clue Solver", description = "Template for fast prototyping", category = ScriptCategory.OTHER, openSource = false, authors = "Steve")
public class Bot extends AbstractScript {

    public static final long startingTime = System.currentTimeMillis();
    public static ArrayList<Task> TASKS = new ArrayList<>();
    public static String STATUS = "Script started..";
    public static boolean doTaskAfterLogin = true;
    public static boolean hasDied = false;
    public static boolean wasAttacked = false;
    public static boolean isPoisoned = false;
    public static boolean isLoggedOut = false;
    public static boolean showPaint = true;
    public static boolean doClueTask = false;
    public static boolean obtainClue = false;

	
 

    @Override
    public int loop() {
	Bot.TASKS.forEach(task -> {
	    if(task.validate()){
		Bot.STATUS = task.toString();
		task.execute();
	    } else {
		Bot.STATUS = "Looking for new task..";
	    }
	});
	LogHandler.log("loop");
	return 25;
    }

    @Override
    public boolean onStart() {
	// todo disable strange plant
	addTasks();
	TBot.getBot().getScriptHandler().getRandomHandler().get(0).disable();
	BreakHandler.METHODS.initialisation();
	BreakHandler.METHODS.loadBreaks();
	LoginHandler.METHODS.initialisation();
	PaintHandler.METHODS.initialisation();
	MessageHandler.METHODS.initialisation();
	return true;
    }
    
    
    public boolean onStop() {
    	return doClueTask = false;
    }
    
    private void addTasks(){
	Bot.TASKS.add(new BlankTask());
	Bot.TASKS.add(new BreakTask());
	Bot.TASKS.add(new LookForClueTask());
	Bot.TASKS.add(new LoginTask());
	Bot.TASKS.add(new OnDiedTask());
	Bot.TASKS.add(new OnPoisonedTask());
	Bot.TASKS.add(new ClueSolveTask());
	Bot.TASKS.add(new AcquireClueTask());
    }
}