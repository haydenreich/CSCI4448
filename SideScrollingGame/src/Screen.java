
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
import java.util.Iterator;
import java.util.ListIterator;
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
		//Init object arrays
		environmentObjects = new ArrayList<>();
		enemyObjects = new ArrayList<>();
		powerupObjects = new ArrayList<>();
		
		//The Level
		environmentObjects.add(new Environment(0, 540, 600, 100));
		environmentObjects.add(new Destructable(0,300,50,25)); 
		environmentObjects.add(new Destructable(50,300,50,25)); 
		environmentObjects.add(new Destructable(100,300,50,25)); 
		environmentObjects.add(new Destructable(150,300,50,25)); 
		environmentObjects.add(new Destructable(200,300,50,25)); 
		environmentObjects.add(new Destructable(500,300,50,25)); 
		environmentObjects.add(new Destructable(550,300,50,25)); 
		environmentObjects.add(new Destructable(600,300,50,25)); 
		
		
		
		player = new Character();
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
//    	for (Environment obj : environmentObjects)
//    	{
//    		g2d.setColor(obj.getColor());
//    		g2d.fillRect(obj.getX(), obj.getY(),obj.getWidth(),obj.getHeight());
//    	}
    	ListIterator<Environment> itEnvironment = environmentObjects.listIterator(0);
    	while(itEnvironment.hasNext()){
    		Environment obj = itEnvironment.next();
    		if(obj.isVisible())
    		{
	    		g2d.setColor(obj.getColor());
	    		g2d.fillRect(obj.getX(), obj.getY(),obj.getWidth(),obj.getHeight());
    		}
    		else
    			itEnvironment.remove();
    	}
    	//Draw Enemies
//    	for (Enemy obj : enemyObjects)
//    	{
//    		if(obj.isVisible())
//    			g2d.drawImage(obj.getImage(), obj.getX(), obj.getY(),obj.getWidth(),obj.getHeight(), this);
//    	}
    	ListIterator<Enemy> itEnemy = enemyObjects.listIterator(0);
    	while(itEnemy.hasNext()){
    		Enemy obj = itEnemy.next();
    		if(obj.isVisible())
    		{
    			g2d.drawImage(obj.getImage(), obj.getX(), obj.getY(),obj.getWidth(),obj.getHeight(), this);
    		}
    		else
    			itEnemy.remove();
    	}
    	//Draw Powerups
//    	for (Powerup obj : powerupObjects)
//    	{
//    		if(obj.isVisible())
//    			g2d.drawImage(obj.getImage(), obj.getX(), obj.getY(),obj.getWidth(),obj.getHeight(), this);
//    	}
    	ListIterator<Powerup> itPowerup = powerupObjects.listIterator(0);
    	while(itPowerup.hasNext()){
    		Powerup obj = itPowerup.next();
    		if(obj.isVisible())
    		{
    			g2d.drawImage(obj.getImage(), obj.getX(), obj.getY(),obj.getWidth(),obj.getHeight(), this);
    		}
    		else
    			itPowerup.remove();
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
    	
    	Toolkit.getDefaultToolkit().sync();
    	
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
        	if (!rcObj.intersects(rcPlayer))
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
	        		enemyObjects.remove(obj);
	        		Enemy enemy = new Enemy(1,1);
	        		enemyObjects.add(enemy);
	        		repaint();
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
