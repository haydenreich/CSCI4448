import java.awt.Rectangle;

public class EnemyType1 extends Enemy {
	public EnemyType1(int x, int y)
	{
		super(x,y, "crazyenemy.png");
	}
	@Override
	public void DealDamage(Destructable obj)
	{
		obj.TakeDamage(strength);
	}
}
