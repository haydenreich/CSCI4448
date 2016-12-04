
public class Enemy extends Sprite{
	int health = 1000;
	int strength = 10;
	
	public Enemy(int x, int y) {
        
        setImage("enemy.png");
        this.x = x;
        this.y = y;
        width = 70;
        height = 82;
		jumpSpeed = 5;
    }
	public void DealDamage(Character player)
	{
		player.TakeDamage(strength);
	}
	public void TakeDamage(int dmg)
	{
		health-=dmg;
	}
}
