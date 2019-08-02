package pool.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import pool.model.Ball;
import pool.model.Objectile;
import pool.services.ObjectileList;

// Class for drawning

public class Visualisation extends JFrame {
	
	private static final long serialVersionUID = 1L;
	List<BallImage> images;
	
    public Visualisation(int width, int height) {
        super("Pool");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JLayeredPane lp = getLayeredPane();
        
        WallImage upperWallImage = new WallImage(height, width);
        upperWallImage.takePlace();
		lp.add(upperWallImage, JLayeredPane.POPUP_LAYER);
        images = new ArrayList<BallImage>();
        

		List<Objectile> objectiles = ObjectileList.getInstance();
		objectiles.forEach(objectile -> {
			if(objectile instanceof Ball) {
				Ball ball = (Ball) objectile;
				
				BallImage image = new BallImage(Color.red, ball);
				image.takePlace();
				images.add(image);
				
				lp.add(image, JLayeredPane.POPUP_LAYER);
			}
			
		});
        
        setSize(1200, 1000);
        setVisible(true);
    }

	public void reDrawElements() {
		images.forEach(image -> {
			image.takePlace();
		});
	}
    
    
}
