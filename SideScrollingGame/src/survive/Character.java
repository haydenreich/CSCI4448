
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
		
		int health;
		int strength;
		int speed;
		@Column(name = "SCORE")
		protected int score;
		protected int dmgTimeout;
		protected boolean leftPressed;
		protected boolean rightPressed;
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
	        x = 30;
	        y = 600;
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
		    		obj.TakeDamage(strength);
					 dy = 40;
					 jump = true;
		    	}
			}
			public boolean HandleCollision(Environment obj)
			{
				Rectangle rcThis = this.getBounds();
				Rectangle rcObj = obj.getBounds();
		    	if (rcObj.intersects(rcThis) && (this.y+this.height-5)<obj.y)
		    		this.SetFalling(false);
		    	else
		    		this.SetFalling(true);
		    	return !this.IsFalling();
			}
			public boolean HandleCollision(Destructable obj)
			{
				Rectangle rcThis = this.getBounds();
				Rectangle rcObj = obj.getBounds();
		    	if (rcObj.intersects(rcThis) && (this.y+this.height-5)<obj.y && obj.broken == false)
		    	{
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
