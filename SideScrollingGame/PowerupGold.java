
public class PowerupGold extends Powerup{
	PowerupGold(int x, int y)
	{
		super(x,y,"powerupgold.png");
		score = 500;
	}
	@Override
	public void HandleCollision(Character player)
	{
		player.UpdateScore(score);
	}
}
