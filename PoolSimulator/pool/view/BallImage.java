package pool.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import pool.model.Ball;

public class BallImage extends JComponent {

	private static final long serialVersionUID = 1L;
	private static final int PADDING = 10;
    private Color color;
    private Ball ball;
    BallImage(Color color, Ball ball) {
        this.color = color;
        this.ball = ball;
        setOpaque(false);
    }
    public void paintComponent(Graphics g) {
        g.setColor(color);
        g.fillOval(0, 0, (int) ball.getR() * 2, (int) ball.getR() * 2);
    }
	public void takePlace() {
		this.setBounds((int) (ball.getX() - ball.getR()) + PADDING, (int) (ball.getY() - ball.getR()) + PADDING,
				(int) ball.getR() * 2, (int) ball.getR() * 2);
	}

}
