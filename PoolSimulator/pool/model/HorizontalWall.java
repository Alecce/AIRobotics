package pool.model;

import pool.services.InnerClock;

public class HorizontalWall implements Objectile {

	double position;
	int collisionValue;
	
	public HorizontalWall(double position) {
		collisionValue = 0;
		this.position = position;
	}

	@Override
	public Collision findNextCollision(Ball ball) {
		double dy = position - ball.getY();
		double dt;
		
		if(dy * ball.getVy() <= 0) {
			return null;
		}
		if(dy > 0) {
			dt = (dy - ball.getR()) / ball.getVy();
		} else {
			dt = (dy + ball.getR()) / ball.getVy();
		}
		
		
		return new Collision(InnerClock.getTime() + dt, ball, this);
		
	}

	@Override
	public void collide(Objectile objectile_2) {
		Ball ball = (Ball) objectile_2;
		ball.setVy(-ball.getVy());
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
		//walls dont move
	}
	

	
}
