
public class Destructable extends Environment{

	protected int health;
	protected int strength;
	protected int speed;
	
	public Destructable(int x, int y, int w, int h)
	{
		super(x, y, w, h);
		health = 100;
		strength = 0;
		speed = 0;
	}

	 public void TakeDamage(int dmg)
	 {
		 health-=dmg;
		 if(health<=0) vis = false;
	 }

	 public int getHealth()
	 {
		 return health;
	 }
}
