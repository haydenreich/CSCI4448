import javax.swing.ImageIcon;

public class Powerup extends Sprite{
	
	public Powerup(int x, int y)
	{
		setImage("powerup.png");
        this.x = x;
        this.y = y;
        dx = 0;
        dy = 0;
        width = 35;
        height = 41;
		jumpSpeed = 1;
	}
}
