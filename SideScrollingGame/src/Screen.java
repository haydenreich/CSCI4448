
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
import java.util.Random;

import javax.swing.*;

public class Screen extends JPanel implements ActionListener {
	
	private Menu menu;
	private Timer timer;
	private int gameTime;
	private int spawnTimer;
	protected Image image;
	private final int DELAY = 10;
	private Character player;
	private ArrayList<Environment> environmentObjects;
	private ArrayList<Destructable> destructableObjects;
	private ArrayList<Enemy> enemyObjects;
	private ArrayList<Powerup> powerupObjects;
	private JFrame topFrame;
	private int gameState;
	public Screen(Menu m){
		topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		gameState = 0;
		menu = m;
		initObjects();
		initScreen();
	}
	
	private void initObjects()
	{
		//Init object arrays
		environmentObjects = new ArrayList<>();
		enemyObjects = new ArrayList<>();
		powerupObjects = new ArrayList<>();
		destructableObjects = new ArrayList<>();
		
		//The Level
		environmentObjects.add(new Environment(0, 680, 1280, 5));
		//destructableObjects.add(new Destructable(0,300,50,25)); 
		
		
		
		
		player = new Character();
		Enemy enemy = new Enemy(500, 200);
		enemyObjects.add(enemy);
		powerupObjects.add(new PowerupHealth(0,1));
		powerupObjects.add(new PowerupHealth(200,1));
		powerupObjects.add(new PowerupHealth(400,1));
		powerupObjects.add(new PowerupHealth(600,1));
		powerupObjects.add(new PowerupHealth(800,1));
		powerupObjects.add(new PowerupHealth(1000,1));
		powerupObjects.add(new PowerupHealth(1200,1));
		powerupObjects.add(new PowerupGold(0,1));
		powerupObjects.add(new PowerupGold(100,1));
		powerupObjects.add(new PowerupGold(300,1));
		powerupObjects.add(new PowerupGold(500,1));
		powerupObjects.add(new PowerupGold(700,1));
		powerupObjects.add(new PowerupGold(900,1));
		powerupObjects.add(new PowerupGold(1100,1));
	}
	
	private void initScreen(){
		addKeyListener(new TAdapter());
		setFocusable(true);
		ImageIcon icon = new ImageIcon("background.jpg");
	    image = icon.getImage();
		
		
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
		g2d.drawImage(image, 0, 0, 1280, 720, this);
		//Draw player
		g2d.drawImage(player.getImage(), player.getX(), player.getY(),player.getWidth(),player.getHeight(), this);
		
		//Draw Environment Objects
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
    	
    	//Draw Destructable objects
    	ListIterator<Destructable> itDestructable = destructableObjects.listIterator(0);
    	while(itDestructable.hasNext()){
    		Environment obj = itDestructable.next();
    		if(obj.isVisible())
    		{
	    		g2d.setColor(obj.getColor());
	    		g2d.fillRect(obj.getX(), obj.getY(),obj.getWidth(),obj.getHeight());
    		}
    		else
    			itDestructable.remove();
    	}
    	//Draw Enemies
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
    	
		StringBuilder scoreString = new StringBuilder();
    	Formatter scoreFormatter = new Formatter(scoreString, Locale.US);
    	scoreFormatter.format("Score: %d", player.getScore());
		
    	g2d.setColor(Color.green);
    	g2d.setFont(small);
    	g2d.drawString(sb.toString(), 10, 20);
		g2d.drawString(scoreString.toString(), 500, 20);
    	
    	Toolkit.getDefaultToolkit().sync();
    	
	}
	@Override
    public void actionPerformed(ActionEvent e) {

		if(!menu.getPaused())
		{
			if (gameState == 0) {
				gameState = 1;
				menu.dispose();
			}
		
			//update player
			player.move();

			//update enemies
			for (Enemy obj : enemyObjects)
			{
				if(obj instanceof BouncingEnemy){
					obj.fly();
				}
				else{
					obj.move();
				}
			}
			
			//for (BouncingEnemy obj : flyingObjects)
			//{
				//obj.fly();
			//}

			//update powerups
			for (Powerup obj : powerupObjects)
			{
				obj.move();
			}
			gameTime += 1;
	    	spawnTimer += 1;
			if (spawnTimer % 100 == 0){
				Enemy enemy = new Enemy(1,1);
				enemyObjects.add(enemy);
			}
			if (spawnTimer % 500 == 0){
				Enemy enemy = new EnemyType1(1,1);
				enemyObjects.add(enemy);
			}
			if (spawnTimer % 150 == 0){
				Enemy flyer = new BouncingEnemy(0,1);
				enemyObjects.add(flyer);
			}
			if (gameTime == 1000)
	    	 {
	    		 gameState += 1;
	    		 gameTime = 0;
	    	 }


			checkCollisions();

			if (player.getHealth()<=0)
			{
				gameState = 0;
				initObjects();
			}


			repaint(); 
		}
    }
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        	{
        		if(menu == (Menu)null) {
        			menu = new Menu(true);
        		}
        		else {
        			menu.setVisible(true);
        		}
        		menu.setPaused();
        		menu.setLocationRelativeTo(topFrame);
        	}
        	else {
            	player.keyReleased(e);
			}
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
    	
    	//Check player against environment
    	for (Environment obj : environmentObjects)
    	{
    		Rectangle rcObj = obj.getBounds();
        	if (!rcObj.intersects(rcPlayer))
        		player.SetFalling(true);
        	else if (rcObj.intersects(rcPlayer) && (player.y+player.height-5)<obj.y)
        	{
        		player.SetFalling(false);
        		break;
        	}
        	//else{
        		//player.SetFalling(false);
        		//break;
        	//}
    	}
    	if (player.IsFalling())
    	{
	    	for (Destructable obj : destructableObjects)
	    	{
	    		Rectangle rcObj = obj.getBounds();
	        	if (!rcObj.intersects(rcPlayer))
	        		player.SetFalling(true);
	        	else if (rcObj.intersects(rcPlayer) && (player.y+player.height-5)<obj.y)
	        	{
	        		player.SetFalling(false);
	        		break;
	        	}
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
	        	if (!rcObj.intersects(rcObj2))
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
    			obj.HandleCollision(player);
    			obj.setVisible(false);
    		}
    	}
    	
    	//Check Enemy collisions
    	for (Enemy obj : enemyObjects)
    	{    	
    		Rectangle rcObj = obj.getBounds(); 
    		//Check Environment collision
    		for (Environment obj2 : environmentObjects)
	    	{
	    		Rectangle rcObj2 = obj2.getBounds();
	        	if (!rcObj.intersects(rcObj2))
	        		obj.SetFalling(true);
	        	//else
	        //	{
	        		//obj.SetFalling(false);
//	        		Random rand = new Random();
//	        		obj.x = rand.nextInt(1210) + 1;
//	        		obj.y = 0;
//	        		repaint();
	        	//	break;
	        	//}
	    	}
    		//Check Destructable collision
    		for (Destructable obj2 : destructableObjects)
	    	{
	    		Rectangle rcObj2 = obj2.getBounds();
	        	if (!rcObj.intersects(rcObj2))
	        		obj.SetFalling(true);
	        	else
	        	{
	        		obj.DealDamage(obj2);
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
