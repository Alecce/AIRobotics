package pool.model;

public interface Event extends Comparable<Event>{
	public abstract void occur();
	public abstract boolean isValid();
	public abstract double getTime();
}
