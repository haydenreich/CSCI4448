
package survive;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HIGH_SCORES")
public class Character extends Sprite{
		
		transient int health;
		transient int strength;
		transient int speed;
		@Column(name = "SCORE")
		protected int score;
		protected transient int dmgTimeout;
		protected transient boolean leftPressed;
		protected transient boolean rightPressed;
		@Column(name = "PLAYER")
		protected String name;
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		protected long id_no;
		
		public Character(){
			initChar();
		}
		
		private void initChar() {
	        
	        setImage("sprite.png");
	        x = 1230;
	        y = 0;
	        width = 35;
	        height = 41;
			jumpSpeed = 5;
			health = 1000;
			strength = 1000;
			speed = 3;
			score = 0;
			dmgTimeout = 0;
			leftPressed = false;
			rightPressed = false;
			name = "";
	    }
		 
		public void Update()
		{
			if(dmgTimeout>0) dmgTimeout--;
			else
			{
				pushed = 0;
			}
		}
		
		 public void keyPressed(KeyEvent e){
			 int key = e.getKeyCode();
			 /*if (key == KeyEvent.VK_UP){
				 dy = -1;
			 }
			 if(key == KeyEvent.VK_DOWN){
				 dy = 1;
			 }*/
			 if(key == KeyEvent.VK_LEFT){
					 Left();
				 leftPressed = true;
			 }
			 if(key == KeyEvent.VK_RIGHT){
					 Right();
			 	rightPressed = true;
			 }
			 if (key == KeyEvent.VK_SPACE)
			 {
				 if(jump == false && falling == false)
				 {
					 dy = 75;
					 Sound.jump.play();
					 jump = true;
				 }
			 }
		 }
		 
		 public void keyReleased(KeyEvent e) {
		        
		        int key = e.getKeyCode();

				/* if (key == KeyEvent.VK_UP){
					 dy = 0;
				 }
		        if (key == KeyEvent.VK_DOWN) {
		            dy = 0;
		        }*/
		        if (key == KeyEvent.VK_LEFT) {
		        	if(rightPressed == false)
		        		dx = 0;
		        	else
		        		Right();
		            leftPressed = false;
		        }

		        if (key == KeyEvent.VK_RIGHT ) {
		        	if(leftPressed == false)
		        		dx = 0;
		        	else
		        		Left();
		            rightPressed = false;
		        }
		 }
		
		 public void TakeDamage(int dmg)
		 {
			 if(dmgTimeout==0)
			 {
				 if(dmg>0) Sound.hurt.play();
				 dmgTimeout = 5;
				 health-=dmg;
			 }
		 }

		 public int getHealth()
		 {
			 return health;
		 }
		 public int getScore()
		 {
			 return score;
		 }
		 public void UpdateScore(int score)
		 {
			 this.score+=score;
		 }
		 
		 public void setName(String aName)
		 {
			 name = aName;
		 }
		 private void Left()
		 {
			 setImage("spriteL.png");
		        width = 35;
		        height = 41;
			 dx = -speed;
		 }
		 private void Right()
		 {
			 setImage("sprite.png");
				width = 35;
				height = 41;
			 dx = speed;
		 }
			public void HandleCollision(Enemy obj)
			{
		    	if (((this.y+this.height-5)<obj.y) && dmgTimeout == 0)
		    	{
		    		UpdateScore(strength);
		    		obj.TakeDamage(strength);
					 dy = 40;
					 Sound.jump.play();
					 jump = true;
		    	}
			}
			public void Push(int push)
			{
		    		pushed=push;
			}
			public boolean HandleCollision(Environment obj)
			{
				boolean bResult = true;
				Rectangle rcThis = this.getBounds();
				Rectangle rcObj = obj.getBounds();
		    	if (rcObj.intersects(rcThis) && (this.y+this.height-5)<obj.y && !jump)
		    	{
					if(IsFalling()) Sound.fall.play();
		    		this.SetFalling(false);
		    		return true;
		    	}
		    	else
		    		return false;
			}
			public boolean HandleCollision(Destructable obj)
			{
				Rectangle rcThis = this.getBounds();
				Rectangle rcObj = obj.getBounds();
		    	if (rcObj.intersects(rcThis) && (this.y+this.height-5)<obj.y && obj.broken == false && !jump)
		    	{
					if(IsFalling()) Sound.fall.play();
	        		obj.TakeDamage(2);
		    		this.SetFalling(false);
		    	}
		    	else
		    		this.SetFalling(true);
		    	return !this.IsFalling();
			}
		//Dimension size = getSize();
		//double w = size.getWidth();
		//double h = size.getHeight();
		
		//g2d.drawRect(0, 0, 50, 50);
		//g2d.setColor(Color.red);
		//g2d.fillRect(0,0,50,50);
}
