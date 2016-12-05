package survive;
import java.util.Random;

public class Enemy extends Sprite{
	int health = 1000;
	int strength = 10;
	
	public Enemy(int x, int y) {
        Random rand = new Random();
        setImage("enemy.png");
        this.x = rand.nextInt(1210) + 1;
        this.y = 0;
        width = 70;
        height = 82;
		jumpSpeed = 5;
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
	}
}
