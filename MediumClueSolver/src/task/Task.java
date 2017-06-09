package task;

public interface Task {

    public void execute();

    @Override
    public String toString();

    public boolean validate();
}
