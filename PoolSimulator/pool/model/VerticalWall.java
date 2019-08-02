package pool.model;

import pool.services.InnerClock;

public class VerticalWall implements Objectile {

	double position;
	int collisionValue;

	public VerticalWall(double position) {
		collisionValue = 0;
		this.position = position;
	}

	@Override
	public Collision findNextCollision(Ball ball) {
		double dx = position - ball.getX();
		double dt;

		if (dx * ball.getVx() <= 0) {
			return null;
		}
		if (dx > 0) {
			dt = (dx - ball.getR()) / ball.getVx();
		} else {
			dt = (dx + ball.getR()) / ball.getVx();
		}

		return new Collision(InnerClock.getTime() + dt, ball, this);

	}

	@Override
	public void collide(Objectile objectile_2) {

		Ball ball = (Ball) objectile_2;
		ball.setVx(-ball.getVx());
		ball.getCollisionValue();
		ball.findCollisions();
	}

	@Override
	public int getCollisionValue() {
		return collisionValue;
	}

	@Override
	public void findCollisions() {

	}

	@Override
	public void move(double dt) {
		// walls dont move
	}
}
