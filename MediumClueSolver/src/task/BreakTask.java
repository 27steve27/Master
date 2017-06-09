package task;

import core.BreakHandler;

public class BreakTask implements Task {

    @Override
    public void execute() {
	BreakHandler.METHODS.doBreak();
    }

    @Override
    public String toString() {
	return "Taking break";
    }

    @Override
    public boolean validate() {
	return BreakHandler.METHODS.shouldBreak();
    }

}
