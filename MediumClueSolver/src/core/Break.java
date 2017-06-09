package core;

public class Break {

    private final long startAt;
    private final long stopAt;

    public Break(long startAt, long stopAt) {
	this.startAt = startAt;
	this.stopAt = stopAt;
    }

    public boolean shouldBreak(long startedAt) {
	return System.currentTimeMillis() - startedAt > startAt && System.currentTimeMillis() - startedAt < stopAt;
    }
}
