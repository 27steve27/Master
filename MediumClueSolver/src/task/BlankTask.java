package task;

public class BlankTask implements Task {

    @Override
    public void execute() {

    }

    @Override
    public String toString() {
	return "Blank";
    }

    @Override
    public boolean validate() {
	return false;
    }
}
