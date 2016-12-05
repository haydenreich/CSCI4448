package survive;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;

public class Environment{

	protected int x;
	protected int y;
    protected int width;
    protected int height;
    protected Color color;
    protected boolean vis;
    
	public Environment(int x, int y, int w, int h)
	{
		vis = true;
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.color = color.WHITE;
	}
	 public int getX(){
		 return x;
	 }
	 public int getY(){
		 return y;
	 }
	 public int getWidth()
	 {
		 return width;
	 }
	 public int getHeight()
	 {
		 return height;
	 }
	 public Color getColor(){
		return color;
	 }
	public void setColor(Color color)
	{
		this.color = color;
	}
	public Rectangle getBounds() {
	    return new Rectangle(x, y, width, height);
	}
	public boolean isVisible() {
	    return vis;
	}
	
	public void setVisible(Boolean visible) {
	    vis = visible;
	}
	
}
