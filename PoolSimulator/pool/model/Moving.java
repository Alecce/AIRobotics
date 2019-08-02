package pool.model;

import java.util.PriorityQueue;

import pool.services.CollisionQueue;

public class Moving implements Event {

	private static final double FRAME_LENGTH = 0.05;
	private static final long FRAME_PAUSE = 20; // fps ~ 50

	double time;

	PriorityQueue<Event> futureCollisions;
	
	
	public Moving(double time) {
		this.time = time;
		this.futureCollisions = CollisionQueue.getInstance();
	}
	
	public Moving() {
		this(0);
	}

	// we add new Moving and wait FRAME_PAUSE
	@Override
	public void occur() {
		futureCollisions.add(new Moving(time + FRAME_LENGTH));
		try {
			Thread.sleep(FRAME_PAUSE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public double getTime() {
		return time;
	}

	@Override
	public int compareTo(Event e) {
		return time - e.getTime() >= 0 ? 1 : -1;
	}

}
