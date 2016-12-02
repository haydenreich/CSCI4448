
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class Character {
	private int dx;
	private int dy;
	private int x;
	private int y;
	
	public void draw(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		rh.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		
		g2d.setRenderingHints(rh);
		
		//Dimension size = getSize();
		//double w = size.getWidth();
		//double h = size.getHeight();
		
		g2d.drawRect(0, 0, 50, 50);
		g2d.setColor(Color.red);
		g2d.fillRect(0,0,50,50);
	}
}
