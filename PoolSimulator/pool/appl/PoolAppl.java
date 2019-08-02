package pool.appl;

import java.util.List;
import java.util.PriorityQueue;

import pool.model.Ball;
import pool.model.Event;
import pool.model.Moving;
import pool.model.HorizontalWall;
import pool.model.Objectile;
import pool.model.VerticalWall;
import pool.services.CollisionQueue;
import pool.services.InnerClock;
import pool.services.ObjectileList;
import pool.view.Visualisation;

public class PoolAppl {

	private static final int HEIGHT = 800;
	private static final int WIDTH = 800;
	private static final int N_BALLS = 10;
	private static final double RANGE = 100;
	private static final double START_V = 400;

	public static void main(String[] args) throws InterruptedException {

		List<Objectile> objectiles = ObjectileList.getInstance();
		generateWalls(objectiles);
		generateBalls(objectiles, N_BALLS);

		// queue of predicted collisions and movings. 
		PriorityQueue<Event> collisions = CollisionQueue.getInstance();
		collisions.add(new Moving());

		Visualisation visualisation = new Visualisation(WIDTH, HEIGHT);

		objectiles.forEach(objectile -> objectile.findCollisions());

		while (true) {
			Event nextCollision = collisions.poll();
			if (nextCollision.isValid()) {
				double dt = nextCollision.getTime() - InnerClock.getTime();
				objectiles.forEach(objectile -> {
					objectile.move(dt);
				});
				
				InnerClock.setTime(nextCollision.getTime());
				nextCollision.occur();
				visualisation.reDrawElements();
			}
		}
	}

	private static void generateWalls(List<Objectile> objectiles) {
		HorizontalWall downWall = new HorizontalWall(0);
		HorizontalWall upWall = new HorizontalWall(HEIGHT);
		VerticalWall leftWall = new VerticalWall(0);
		VerticalWall rightWall = new VerticalWall(WIDTH);

		objectiles.add(downWall);
		objectiles.add(upWall);
		objectiles.add(rightWall);
		objectiles.add(leftWall);
	}

	private static void generateBalls(List<Objectile> objectiles, int nBalls) {

		for (int i = 0; i < nBalls; i++) {
			double x = RANGE + Math.random() * (WIDTH - 2 * RANGE);
			double y = RANGE + Math.random() * (HEIGHT - 2 * RANGE);

			while (isNotValid(objectiles, x, y)) {
				x = RANGE + Math.random() * (WIDTH - 2 * RANGE);
				y = RANGE + Math.random() * (HEIGHT - 2 * RANGE);
			}

			double vx = (Math.random() - 0.5) * START_V;
			double vy = (Math.random() - 0.5) * START_V;
			Ball b1 = new Ball(x, y, vx, vy);
			objectiles.add(b1);
		}

	}

	private static boolean isNotValid(List<Objectile> objectiles, double x, double y) {

		return objectiles.stream().filter(obj -> obj instanceof Ball).map(obj -> (Ball) obj)
				.mapToDouble(ball -> Math.sqrt(Math.pow(ball.getX() - x, 2) + Math.pow(ball.getY() - y, 2)))
				.filter(r -> r < RANGE).findFirst().isPresent();
	}

}
