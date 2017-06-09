package task;

import org.tbot.internal.handlers.LogHandler;

import core.Bot;

public class OnPoisonedTask implements Task {

    @Override
    public void execute() {
	LogHandler.log("Player is poisoned. Do something.");
    }

    @Override
    public String toString() {
	return "Player is poisoned";
    }

    @Override
    public boolean validate() {
	return Bot.isPoisoned;
    }
}