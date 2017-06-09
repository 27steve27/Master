package task;

import org.tbot.internal.handlers.LogHandler;

import core.Bot;

public class OnDiedTask implements Task {

    @Override
    public void execute() {
	LogHandler.log("Player died. Do something.");
    }

    @Override
    public String toString() {
	return "Player died";
    }

    @Override
    public boolean validate() {
	return Bot.hasDied;
    }

}
