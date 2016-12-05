import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Powerup extends Sprite{

	protected int health;
	protected int strength;
	protected int speed;
	protected int score;
	public Powerup(int x, int y, String path)
	{
		setImage(path);
        this.x = x;
        this.y = y;
        dx = 0;
        dy = 0;
        width = 35;
        height = 41;
		speed = 0;
		health = 0;
		score = 0;
	}
	public void HandleCollision(Character player)
	{
		vis = false;
	}
	public boolean HandleCollision(Environment obj)
	{
		Rectangle rcThis = this.getBounds();
		Rectangle rcObj = obj.getBounds();
    	if (rcObj.intersects(rcThis) && (this.y+this.height-5)<obj.y)
    		this.SetFalling(false);
    	else
    		this.SetFalling(true);
    	return !this.IsFalling();
	}
}
