import java.util.Random;

public class BouncingEnemy extends Enemy {
	int strength;
	
	public BouncingEnemy(int x, int y){
		super(x,y);
		setImage("enemy.png");
		Random rand = new Random();
		this.x = 0;
		this.y = rand.nextInt(600) + 1;
		width = 40;
		height = 40;
		
	}

}
