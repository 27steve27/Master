package core;

import org.tbot.internal.event.events.MessageEvent;
import org.tbot.internal.event.listeners.MessageListener;

public enum MessageHandler implements MessageListener {

    METHODS;

    @Override
    public void messageReceived(MessageEvent me) {
	if (me.getMessage().getText().equals("Oh dear, you are dead!")) {
	    Bot.hasDied = true;
	    if (Bot.isPoisoned) {
		Bot.wasAttacked = false;
		Bot.isPoisoned = false;
	    } else {
		Bot.wasAttacked = true;
		Bot.isPoisoned = false;
	    }
	}
	if (me.getMessage().getText().equals("You have been poisoned!")) {
	    Bot.wasAttacked = true;
	    Bot.isPoisoned = true;
	}
    }

    public void initialisation() {
    }
}
