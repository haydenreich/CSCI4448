
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Locale;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Screen extends JPanel implements ActionListener {
	
	private Menu menu;
	private Timer timer;
	private final int DELAY = 10;
	private Character player;
	private ArrayList<Environment> environmentObjects;
	private ArrayList<Enemy> enemyObjects;
	private ArrayList<Powerup> powerupObjects;
	public Screen(){
		initObjects();
		initScreen();
	}
	
	private void initObjects()
	{
		environmentObjects = new ArrayList<>();
		enemyObjects = new ArrayList<>();
		powerupObjects = new ArrayList<>();
		player = new Character();
		Environment ground = new Environment(0, 540, 600, 100);
		environmentObjects.add(ground);
		Enemy enemy = new Enemy(500, 200);
		enemyObjects.add(enemy);
		Powerup powerup = new Powerup(0,0);
		powerupObjects.add(powerup);
		powerup = new Powerup(100,0);
		powerupObjects.add(powerup);
		powerup = new Powerup(200,0);
		powerupObjects.add(powerup);
		powerup = new Powerup(300,0);
		powerupObjects.add(powerup);
		powerup = new Powerup(400,0);
		powerupObjects.add(powerup);
		powerup = new Powerup(500,0);
		powerupObjects.add(powerup);
		powerup = new Powerup(600,0);
		powerupObjects.add(powerup);
	}
	
	private void initScreen(){
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		
		timer = new Timer(DELAY, this);
		timer.start();
	}
	//Character c = new Character();
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawObjects(g);
	}
	
	
	public void drawObjects(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		//Draw player
		g2d.drawImage(player.getImage(), player.getX(), player.getY(),player.getWidth(),player.getHeight(), this);
		
		//Draw Environment Objects
    	for (Environment obj : environmentObjects)
    	{
    		g2d.setColor(obj.getColor());
    		g2d.fillRect(obj.getX(), obj.getY(),obj.getWidth(),obj.getHeight());
    	}
    	
    	//Draw Enemies
    	for (Enemy obj : enemyObjects)
    	{
    		if(obj.isVisible())
    			g2d.drawImage(obj.getImage(), obj.getX(), obj.getY(),obj.getWidth(),obj.getHeight(), this);
    	}
    	
    	//Draw Powerups
    	for (Powerup obj : powerupObjects)
    	{
    		if(obj.isVisible())
    			g2d.drawImage(obj.getImage(), obj.getX(), obj.getY(),obj.getWidth(),obj.getHeight(), this);
    	}
    	
    	//Draw health
    	Font small = new Font("Helvetica", Font.BOLD, 14);
    	FontMetrics fm = getFontMetrics(small);
    	StringBuilder sb = new StringBuilder();
    	Formatter formatter = new Formatter(sb, Locale.US);
    	formatter.format("HEALTH: %d", player.getHealth());
    	
    	g2d.setColor(Color.green);
    	g2d.setFont(small);
    	g2d.drawString(sb.toString(), 10, 20);
    	
	}
	@Override
    public void actionPerformed(ActionEvent e) {

		//update player
        player.move();
        
        //update enemies
    	for (Enemy obj : enemyObjects)
    	{
    		obj.move();
    	}
    	
        //update powerups
    	for (Powerup obj : powerupObjects)
    	{
    		obj.move();
    	}
        
		checkCollisions();
        
		if (player.getHealth()<=0)
		{
			initObjects();
		}
		
        repaint();  
    }
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }
		
	}
    private void checkCollisions()
    {
    	//Bounding box representing the player
    	Rectangle rcPlayer = player.getBounds();
    	
    	//Check all objects against environment
    	for (Environment obj : environmentObjects)
    	{
    		Rectangle rcObj = obj.getBounds();
        	if (rcPlayer.y + rcPlayer.height < rcObj.y)
        		player.SetFalling(true);
        	else
        	{
        		player.SetFalling(false);
        		break;
        	}
    	}

    	//Check Object collisions
    	for (Powerup obj : powerupObjects)
    	{    	
    		Rectangle rcObj = obj.getBounds();
    		//Check ground collision
    		for (Environment obj2 : environmentObjects)
	    	{
	    		Rectangle rcObj2 = obj2.getBounds();
	        	if (rcObj.y + rcObj.height < rcObj2.y)
	        		obj.SetFalling(true);
	        	else
	        	{
	        		obj.SetFalling(false);
	        		break;
	        	}
	    	}
    		//Check player collision
    		if (rcObj.intersects(rcPlayer))
    		{
    			obj.setVisible(false);
    		}
    	}
    	
    	//Check Enemy collisions
    	for (Enemy obj : enemyObjects)
    	{    	
    		Rectangle rcObj = obj.getBounds(); 
    		//Check ground collision
    		for (Environment obj2 : environmentObjects)
	    	{
	    		Rectangle rcObj2 = obj2.getBounds();
	        	if (rcObj.y + rcObj.height < rcObj2.y)
	        		obj.SetFalling(true);
	        	else
	        	{
	        		obj.SetFalling(false);
	        		break;
	        	}
	    	}
    		//Check player collision
    		if (rcObj.intersects(rcPlayer))
    		{
    			obj.DealDamage(player);
    		}
    	}
    }
}
