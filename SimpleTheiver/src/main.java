import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import java.awt.*;

@ScriptManifest(author = "Itspeaches", info = "My first script", name = "tree chopper", version = 0, logo = "")
public class main extends Script {

	@Override
	public void onStart() {
		log("Welcome to Simple Tea Thiever by Apaec.");

	}

	private enum State {
		CHOP, DROP, WAIT
	};

	private State getState() {
		Entity tree = objects.closest("Tree");
		if(!inventory.isEmpty())
			return State.DROP;
		if (tree != null)
			return State.CHOP;
		return State.WAIT;
	}

	@Override
	public int onLoop() throws InterruptedException {
		switch (getState()) {
		case CHOP:
			Entity tree = objects.closest("Tree");
			if (tree != null) {
				tree.interact("Chop down");
			}
			break;
		case DROP:
			inventory.dropAll();
			break;
		case WAIT:
			sleep(random(500, 700));
			break;
		}
		return random(200, 300);
	}

	@Override
	public void onExit() {
		log("Thanks for running my Tea Thiever!");
	}

	@Override
	public void onPaint(Graphics2D g) {

	}

}