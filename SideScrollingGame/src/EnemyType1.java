import java.util.Random;

public class EnemyType1 extends Enemy{
	public EnemyType1(int x, int y)
	{
		super(x,y);
        setImage("crazyenemy.png");
        width = 200;
        height = 200;
	}
	@Override
	public void DealDamage(Destructable obj)
	{
		obj.TakeDamage(strength);
	}
}
