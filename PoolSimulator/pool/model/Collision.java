package pool.model;

// Predicted collision of two objectiles

public class Collision implements Event {
	double time;
	int collisionValue1;
	Objectile objectile_1;
	int collisionValue2;
	Objectile objectile_2;

	public Collision(double time, Objectile objectile_1, Objectile objectile_2) {
		super();
		this.time = time;
		this.collisionValue1 = objectile_1.getCollisionValue();
		this.objectile_1 = objectile_1;
		this.collisionValue2 = objectile_2.getCollisionValue();
		this.objectile_2 = objectile_2;
	}

	public double getTime() {
		return time;
	}

	public int getCollisionValue1() {
		return collisionValue1;
	}

	public Objectile getObjectile_1() {
		return objectile_1;
	}

	public int getCollisionValue2() {
		return collisionValue2;
	}

	public Objectile getObjectile_2() {
		return objectile_2;
	}

	// I check if any collisions happens between prediction and current moment. If so, then collision in objectile 
	// will be changed and method return false, otherwise true.
	public boolean isValid() {
		return objectile_1.getCollisionValue() == collisionValue1 && objectile_2.getCollisionValue() == collisionValue2;
	}

	public void occur() {

		objectile_1.collide(objectile_2);
	}


	@Override
	public int compareTo(Event e) {
		return time - e.getTime() >= 0 ? 1 : -1;
	}
	
}
