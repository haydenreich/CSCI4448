import java.awt.Rectangle;
import java.util.Random;

public class Enemy extends Sprite{
	int health = 1000;
	int strength = 10;
	int speed = 1;
	public Enemy(int x, int y, String path) {
        Random rand = new Random();
        setImage(path);
        this.x = rand.nextInt(1210) + 1;
        this.y = 0;
        if (x == -1){
        	this.x = rand.nextInt(1210) + 1;
        }
        else{
        	this.x = x;
        }
        this.y = -200;
        width = 35;
        height = 41;
		jumpSpeed = 5;
		falling = true;
		this.dx = speed;
		if(rand.nextInt(2)==1) dx=-speed;
    }
	public void DealDamage(Character player)
	{
		player.TakeDamage(strength);
	}
	public void DealDamage(Destructable obj)
	{
	}
	public void TakeDamage(int dmg)
	{
		health-=dmg;
		if (health<=0) vis = false;
	}
	public void HandleCollision(Character player)
	{
    	if (!((player.y+player.height-5)<this.y))
    	{
    		DealDamage(player);
    	}
	}
	public void UpdateMovement()
	{
		if(blocked)
			vis = false;
	}
	public boolean HandleCollision(Environment obj)
	{
		Rectangle rcThis = this.getBounds();
		Rectangle rcObj = obj.getBounds();
    	if (rcObj.intersects(rcThis) && (this.y+this.height-5)<obj.y)
    	{
    		this.SetFalling(false);
    		if (this.y+this.height>=680) vis = false;
    	}
    	else
    		this.SetFalling(true);
    	return !this.IsFalling();
	}
	public boolean HandleCollision(Destructable obj)
	{
		Rectangle rcThis = this.getBounds();
		Rectangle rcObj = obj.getBounds();
    	if (rcObj.intersects(rcThis) && (this.y+this.height-5)<obj.y)
    	{
    		this.SetFalling(false);
    	}
    	else
    		this.SetFalling(true);
    	return !this.IsFalling();
	}
}
