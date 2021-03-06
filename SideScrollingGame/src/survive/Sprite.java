package survive;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.persistence.MappedSuperclass;
import javax.swing.ImageIcon;

@MappedSuperclass
public class Sprite {
	protected transient int dx;
	protected transient int dy;
	protected transient int x;
	protected transient int y;
    protected transient int width;
    protected transient int height;
    protected transient int jumpSpeed;
    protected transient boolean vis;
    protected transient boolean jump;
    protected transient boolean falling;
    protected transient boolean blocked;
	protected transient Image image;
    protected transient int pushed;
	
	public Sprite()
	{
		vis = true;
		falling = true;
		blocked = false;
		pushed = 0;
	}
	
	public void move(){
		blocked = false;
		//if(x>0 && x<1210) x += dx;
		if(x>0 && x<1245) x += dx+pushed;
		if (jump) jump();
		else if (falling) fall();
		else if(x <= 0) 
		{
			x+=1;
			blocked = true;
		}
		else if(x>= 1245) 
		{
			x-=1;
			blocked = true;
		}
	}
	
	public void jump()
	{
		if (dy>0 && y>0)
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
			vis = false;
//			x = 0;
//			Random rand = new Random();
//			y = rand.nextInt(600) + 1;
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
	 public int getDX(){
		 return dx;
	 }
	 public int getDY(){
		 return dy;
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
