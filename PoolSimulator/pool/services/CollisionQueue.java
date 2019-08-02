package pool.services;

import java.util.PriorityQueue;

import pool.model.Event;

public class CollisionQueue {

	private static PriorityQueue<Event> collisions = new PriorityQueue<>();
	
	
	private CollisionQueue() {
	}


	public static PriorityQueue<Event> getInstance() {
		return collisions;
	}
}
