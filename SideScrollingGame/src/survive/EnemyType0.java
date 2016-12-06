package survive;

import java.util.Random;

public class EnemyType0 extends Enemy{
	public EnemyType0(int x, int y)
	{
		super(x,y, "enemy.png");
	}
	@Override
	public boolean HandleCollision(Destructable obj)
	{
		if(super.HandleCollision(obj))
		{
			obj.TakeDamage(strength);
			return true;
		}
		else
			return false;
		
	}
	@Override
	public void HandleCollision(Character player)
	{
    	super.HandleCollision(player);
    	if(dx<0)
    		player.Push(-4);
    	else
    		player.Push(4);
	}
}
