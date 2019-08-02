package pool.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class WallImage extends JComponent {
	private static final long serialVersionUID = 1L;
	private static final int PADDING = 10;
    int height, wigth;
    
	public WallImage(int wigth, int height) {
		super();
		this.wigth = wigth;
		this.height = height;
        setOpaque(false);
	}

	public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.drawLine(0, 0, 0, height);
        g.drawLine(0, height, wigth, height);
        g.drawLine(wigth, height, wigth, 0);
        g.drawLine(wigth, 0, 0, 0);
    }
	public void takePlace() {
		this.setBounds(PADDING, PADDING, wigth + PADDING, height + PADDING);
	}
}
