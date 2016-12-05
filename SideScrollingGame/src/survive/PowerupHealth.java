package survive;

public class PowerupHealth extends Powerup {
	public PowerupHealth(int x, int y)
	{
		super(x,y, "powerup.png");
		health = 500;
	}
	@Override
	public void HandleCollision(Character player)
	{
		player.TakeDamage(-health);
	}
}
