package pool.model;

public interface Objectile {
	public abstract int getCollisionValue();
	public abstract Collision findNextCollision(Ball ball);
	public abstract void collide(Objectile objectile_2);
	public abstract void findCollisions();
	public abstract void move(double dt);
}
