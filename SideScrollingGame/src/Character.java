
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Character {
		private int dx;
		private int dy;
		private int x;
		private int y;
		private Image image;
		
		public Character(){
			initChar();
		}
		private void initChar() {
	        
	        ImageIcon icon = new ImageIcon("sprite.png");
	        image = icon.getImage();
	        x = 50;
	        y = 50;        
	    }
		public void move(){
			x += dx;
			y += dy;
		}
		 public int getX(){
			 return x;
		 }
		 public int getY(){
			 return y;
		 }
		 public Image getImage(){
			return image;
		 }
		 
		 public void keyPressed(KeyEvent e){
			 int key = e.getKeyCode();
			 if (key == KeyEvent.VK_UP){
				 dy = -1;
			 }
			 if(key == KeyEvent.VK_DOWN){
				 dy = 1;
			 }
			 if(key == KeyEvent.VK_LEFT){
				 dx = -1;
			 }
			 if(key == KeyEvent.VK_RIGHT){
				 dx = 1;
			 }
		 }
		 
		 public void keyReleased(KeyEvent e) {
		        
		        int key = e.getKeyCode();
		        
		        if (key == KeyEvent.VK_UP) {
		            dy = 0;
		        }

		        if (key == KeyEvent.VK_DOWN) {
		            dy = 0;
		        }

		        if (key == KeyEvent.VK_LEFT) {
		            dx = 0;
		        }

		        if (key == KeyEvent.VK_RIGHT) {
		            dx = 0;
		        }
		 }
		
		
		//Dimension size = getSize();
		//double w = size.getWidth();
		//double h = size.getHeight();
		
		//g2d.drawRect(0, 0, 50, 50);
		//g2d.setColor(Color.red);
		//g2d.fillRect(0,0,50,50);
}
