import java.awt.Rectangle;

public class EnemyType1 extends Enemy {
	public EnemyType1(int x, int y)
	{
<<<<<<< HEAD
		super(x,y, "crazyenemy.png");
=======
		super(x,y);
        setImage("crazyenemy.png");
        width = 200;
        height = 200;
>>>>>>> branch 'master' of https://github.com/haydenreich/CSCI4448
	}
	@Override
	public void DealDamage(Destructable obj)
	{
		obj.TakeDamage(strength);
	}
}
