import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Sprite {
	protected int dx;
	protected int dy;
	protected int x;
	protected int y;
    protected int width;
    protected int height;
    protected int jumpSpeed;
    protected boolean vis;
    protected boolean jump;
    protected boolean falling;
    protected boolean blocked;
	protected Image image;
	
	public Sprite()
	{
		vis = true;
		falling = true;
		blocked = false;
	}
	
	public void move(){
<<<<<<< HEAD
		blocked = false;
		if(x>0 && x<1210) x += dx;
=======
		if(x>0 && x<1245) x += dx;
>>>>>>> branch 'master' of https://github.com/haydenreich/CSCI4448
		if (jump) jump();
		else if (falling) fall();
<<<<<<< HEAD
		else if(x <= 0) 
		{
			x+=1;
			blocked = true;
		}
		else if(x>= 1210) 
		{
			x-=1;
			blocked = true;
		}
=======
		else if(x <= 0) x+=1;
		else if(x>= 1245) x-=1;
		else y+=dy;
>>>>>>> branch 'master' of https://github.com/haydenreich/CSCI4448
	}
	
	public void jump()
	{
		if (dy>0)
		{
			y-=5;
			dy-=5;
		}
		else jump = false;
	}
	public void fall()
	{
		if(!jump)
			y+=5;
	}
	public void fly()
	{
		x+=6;
		if (x >= 1280){
			
			
			x = 0;
			Random rand = new Random();
			y = rand.nextInt(600) + 1;
		}
	}
	public void SetFalling(boolean falling)
	{
		this.falling = falling;
	}

	public boolean IsFalling()
	{
		return falling;
	}
	public void SetJump(boolean jump)
	{
		this.jump = jump;
	}
    protected void getImageDimensions() {

        width = image.getWidth(null);
        height = image.getHeight(null);
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
	 public Image getImage(){
		return image;
	 }
	 public void setImage(String path)
	 {
	    ImageIcon icon = new ImageIcon(path);
	    image = icon.getImage();
	    getImageDimensions();
	 }
	 
	public boolean isVisible() {
	    return vis;
	}
	
	public void setVisible(Boolean visible) {
	    vis = visible;
	}
	
	public Rectangle getBounds() {
	    return new Rectangle(x, y, width, height);
	}
}
