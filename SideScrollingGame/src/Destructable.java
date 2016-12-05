
public class Destructable extends Environment{

	protected int health;
	protected int strength;
	protected int speed;
	protected boolean broken;
	
	public Destructable(int x, int y, int w, int h)
	{
		super(x, y, w, h);
		broken = false;
		health = 20;
		strength = 0;
		speed = 0;
	}

	 public void TakeDamage(int dmg)
	 {
		 health-=dmg;
		 if(health<=0) broken = true;
	 }
	 public int getHealth()
	 {
		 return health;
	 }
}
