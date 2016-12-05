package survive;

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
		jumpSpeed = 1;
		strength = 0;
		speed = 0;
		health = 0;
		score = 0;
	}
	public void HandleCollision(Character player)
	{
	}
}
