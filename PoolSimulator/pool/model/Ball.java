package pool.model;

import java.util.List;
import java.util.PriorityQueue;

import pool.services.CollisionQueue;
import pool.services.InnerClock;
import pool.services.ObjectileList;

public class Ball implements Objectile {

	private static final double RADIUS = 50;
	private double x;
	private double y;
	private double vx;
	private double vy;
	private double r;
	private int collisionValue; // for collision validation, change after every collision 

	List<Objectile> objInVision;
	PriorityQueue<Event> collisions;

	public Ball(double x, double y, double vx, double vy) {
		collisionValue = 0;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.r = RADIUS;
		this.objInVision = ObjectileList.getInstance();
		this.collisions = CollisionQueue.getInstance();
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public double getR() {
		return r;
	}

	public void changeCollisionValue() {
		collisionValue++;
	}

	@Override
	public int getCollisionValue() {
		return collisionValue;
	}
	
	// predict all future collisions for this ball
	public void findCollisions() {
		objInVision.forEach(obj -> {
			Collision collision = obj.findNextCollision(this);
			if (collision != null) {
				collisions.add(collision);
			}
		});
	}

	// predict all future collisions for this with that ball
	@Override
	public Collision findNextCollision(Ball ball) {

		if (this == ball) {
			return null;
		}

		// changing coordinate system. Finally first ball moves parallel to ox and second one stays
		double dx = this.getX() - ball.getX();
		double dy = this.getY() - ball.getY();
		double range = Math.sqrt(dx * dx + dy * dy);

		double dvx = -this.getVx() + ball.getVx();
		double dvy = -this.getVy() + ball.getVy();

		double vl = Math.sqrt(dvx * dvx + dvy * dvy);

		double alf = Math.atan(dvy / dvx);
		if (dvx < 0) {
			alf += Math.PI;
		}

		double bet = Math.atan(dy / dx);
		if (dx < 0) {
			bet += Math.PI;
		}

		double gamm = alf - bet;

		
		double dh = Math.sin(gamm) * range;

		if (Math.abs(dh) >= 2 * this.getR()) {
			return null;
		}

		double dl = Math.sqrt(4 * r * r - dh * dh);

		double dt = (range * Math.cos(gamm) - dl) / vl;

		if (dt <= 0) {
			return null;
		}

		return new Collision(InnerClock.getTime() + dt, this, ball);
	}

	public void move(double dt) {
		x += vx * dt;
		y += vy * dt;
	}

	@Override
	public void collide(Objectile otherObjectile) {
		if (otherObjectile instanceof Ball) {

			Ball ball = (Ball) otherObjectile;

			double v1x = this.getVx();
			double v1y = this.getVy();

			double v2x = ball.getVx();
			double v2y = ball.getVy();

			double dx = -this.getX() + ball.getX();
			double dy = -this.getY() + ball.getY();

			double bet = Math.atan(dy / dx);

			double w2x = v1x * Math.cos(bet) + v1y * Math.sin(bet);
			double w1y = -v1x * Math.sin(bet) + v1y * Math.cos(bet);

			double w1x = v2x * Math.cos(bet) + v2y * Math.sin(bet);
			double w2y = -v2x * Math.sin(bet) + v2y * Math.cos(bet);

			double v1yAfter = w1y * Math.cos(bet) + w1x * Math.sin(bet);
			double v2yAfter = w2y * Math.cos(bet) + w2x * Math.sin(bet);

			double v1xAfter = -w1y * Math.sin(bet) + w1x * Math.cos(bet);
			double v2xAfter = -w2y * Math.sin(bet) + w2x * Math.cos(bet);

			this.setVx(v1xAfter);
			this.setVy(v1yAfter);

			ball.setVx(v2xAfter);
			ball.setVy(v2yAfter);

			this.changeCollisionValue();
			ball.changeCollisionValue();

			ball.findCollisions();
			this.findCollisions();

		} else {

			otherObjectile.collide(this);

		}

	}


}
