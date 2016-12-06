package survive;

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
import javax.sound.sampled.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.swing.*;

public class Screen extends JPanel implements ActionListener {
	
	private Menu menu;
	private Timer timer;
	private int gameTime;
	private int spawnTimer;
	private int floordmgTimer;
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
		environmentObjects.add(new Environment(20, 620, 50, 10));
		environmentObjects.add(new Environment(600, 600, 50, 10));
		environmentObjects.add(new Environment(850, 600, 10, 10));
		environmentObjects.add(new Environment(990, 600, 10, 10));
		environmentObjects.add(new Environment(1130, 600, 10, 10));
		environmentObjects.add(new Environment(1200, 580, 10, 10));
		environmentObjects.add(new Environment(1260, 580, 20, 10));
		environmentObjects.add(new Environment(1060, 580, 10, 10));
		environmentObjects.add(new Environment(920, 580, 10, 10));
		environmentObjects.add(new Environment(100, 580, 50, 10));
		environmentObjects.add(new Environment(740, 570, 50, 10));
		environmentObjects.add(new Environment(1260, 540, 20, 10));
		environmentObjects.add(new Environment(1260, 500, 20, 10));
		destructableObjects.add(new Destructable(740, 510, 50, 10));
		environmentObjects.add(new Environment(20, 510, 50, 10));
		environmentObjects.add(new Environment(1260, 460, 20, 10));
		environmentObjects.add(new Environment(20, 480, 50, 10));
		environmentObjects.add(new Environment(170, 480, 50, 10));
		destructableObjects.add(new Destructable(300,480,50,10));
		destructableObjects.add(new Destructable(430,480,50,10));
		environmentObjects.add(new Environment(20, 450, 50, 10));
		environmentObjects.add(new Environment(740, 450, 50, 10));
		environmentObjects.add(new Environment(1180, 420, 100, 10));
		destructableObjects.add(new Destructable(670, 420, 50, 10));
		environmentObjects.add(new Environment(20, 420, 50, 10));
		destructableObjects.add(new Destructable(600, 390, 50, 10));
		environmentObjects.add(new Environment(170, 390, 50, 10));
		destructableObjects.add(new Destructable(1190, 380, 10, 10));
		destructableObjects.add(new Destructable(530, 360, 50, 10));
		environmentObjects.add(new Environment(220, 350, 50, 10));
		destructableObjects.add(new Destructable(1260, 340, 10, 10));
		destructableObjects.add(new Destructable(460, 330, 50, 10));
		environmentObjects.add(new Environment(170, 310, 50, 10));
		destructableObjects.add(new Destructable(1190, 300, 10, 10));
		environmentObjects.add(new Environment(950, 420, 50, 10));
		destructableObjects.add(new Destructable(530, 300, 50, 10));
		destructableObjects.add(new Destructable(600, 270, 50, 10));
		destructableObjects.add(new Destructable(220, 270, 50, 10));
		environmentObjects.add(new Environment(-40, 270, 50, 10));
		destructableObjects.add(new Destructable(1260, 260, 10, 10));
		destructableObjects.add(new Destructable(670, 240, 50, 10));
		environmentObjects.add(new Environment(170, 230, 50, 10));
		destructableObjects.add(new Destructable(1190, 220, 10, 10));
		environmentObjects.add(new Environment(740, 210, 50, 10));
		environmentObjects.add(new Environment(-40, 210, 50, 10));
		destructableObjects.add(new Destructable(1260, 180, 10, 10));
		environmentObjects.add(new Environment(170, 180, 50, 10));
		environmentObjects.add(new Environment(-40, 150, 50, 10));
		destructableObjects.add(new Destructable(1190, 140, 10, 10));
		destructableObjects.add(new Destructable(1260, 100, 10, 10));
		environmentObjects.add(new Environment(170, 100, 50, 10));
		environmentObjects.add(new Environment(-40, 90, 50, 10));
		environmentObjects.add(new Environment(240, 70, 50, 10));
		environmentObjects.add(new Environment(1180, 50, 100, 10));
		destructableObjects.add(new Destructable(1108, 50, 72, 10));
		destructableObjects.add(new Destructable(1036, 50, 75, 10));
		destructableObjects.add(new Destructable(964, 50, 75, 10));
		destructableObjects.add(new Destructable(892, 50, 75, 10));
		environmentObjects.add(new Environment(842, 50, 50, 10));

		environmentObjects.add(new Environment(310, 40, 50, 10));
		environmentObjects.add(new Environment(420, 40, 10, 10));
		environmentObjects.add(new Environment(480, 40, 10, 10));
		environmentObjects.add(new Environment(540, 40, 10, 10));
		environmentObjects.add(new Environment(600, 40, 10, 10));
		environmentObjects.add(new Environment(660, 40, 50, 10));
		//environmentObjects.add(new Environment(570, 40, 10, 10));
		environmentObjects.add(new Environment(-40, 30, 50, 10));
		
		environmentObjects.add(new Environment(710, -10, 10, 60));
		
		
		
		
		
		
		
		player = new Character();
		Enemy enemy = new EnemyType0(500, 200);
		enemyObjects.add(enemy);
		//powerupObjects.add(new PowerupHealth(0,1));
		//powerupObjects.add(new PowerupHealth(200,1));
		//powerupObjects.add(new PowerupHealth(400,1));
		//powerupObjects.add(new PowerupHealth(600,1));
		//powerupObjects.add(new PowerupHealth(800,1));
		//powerupObjects.add(new PowerupHealth(1000,1));
		//powerupObjects.add(new PowerupHealth(1200,1));
		powerupObjects.add(new PowerupGold(0,105));
		powerupObjects.add(new PowerupGold(665,-10));
		powerupObjects.add(new PowerupGold(740,400));
		powerupObjects.add(new PowerupGold(740,160));
		powerupObjects.add(new PowerupGold(1230,370));
		powerupObjects.add(new PowerupGold(1250,0));
		powerupObjects.add(new PowerupGold(950,370));
	}
	
	private void initScreen(){
		addKeyListener(new TAdapter());
		setFocusable(true);
		ImageIcon icon = new ImageIcon("background.jpg");
	    image = icon.getImage();
		//Sound stuff
	    Sound.theme.setGain(-9.0f);
	    Sound.broken.setGain(-12.0f);
	    Sound.hurt.setGain(-15.0f);
	    Sound.fall.setGain(-18.0f);
	    Sound.jump.setGain(-15.0f);
	    Sound.powerup.setGain(-12.0f);
	    Sound.splat.setGain(-12.0f);
		Sound.theme.loop();
		
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
    		Destructable obj = itDestructable.next();
    		if(obj.isVisible())
    		{
    			if(obj.broken == true){
    				g2d.setColor(Color.red);
    			}
    			else{
    				g2d.setColor(Color.green);
    			}
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

		Random rand = new Random();
		if(!menu.getPaused())
		{
			if (gameState == 0) {
				gameState = 1;
				menu.dispose();
				menu.setRunning();
			}
		
			//update player
			player.Update();
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
				Enemy enemy = new EnemyType0(rand.nextInt(800),-50);
				enemyObjects.add(enemy);
			}
			if (spawnTimer % 200 == 0){
				Enemy enemy = new EnemyType1(rand.nextInt(1280),-50);
				enemyObjects.add(enemy);
			}
			if (spawnTimer % 150 == 0){
				Enemy flyer = new BouncingEnemy(-1,1);
				enemyObjects.add(flyer);
			}
			if (spawnTimer % (50 + rand.nextInt(150)) == 0)
			{
				for (Enemy obj: enemyObjects)
				{
					obj.UpdateMovement();
				}
			}
			if (gameTime == 1000)
	    	 {
	    		 gameState += 1;
	    		 gameTime = 0;
	    	 }
			
			//fix destructables
			for(Destructable obj: destructableObjects){
				if(obj.health == 30) obj.broken = false;
				if(obj.broken == true) obj.health += 1;
			}
			
			if (player.y >= 639){
				floordmgTimer += 1;
				if (floordmgTimer >= 50){
					player.TakeDamage(10);
					//floordmgTimer -= 50;
				}
			}
			else{
				floordmgTimer = 0;
			}

			checkCollisions();

			if (player.getHealth()<=0)
			{
				gameState = 0;
				gameOver();
				resetScreen();
			}


			repaint(); 
		}
    }
	
	public void gameOver() {
		String name;
		JFrame frame = new JFrame();
		name = (String)JOptionPane.showInputDialog(frame,
				"Congratulations!\n" +
				"Save your high score!\n" +
				"Please enter your name:",
				"");
		player.setName(name);
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		
		session.save(player);
		
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}
	
	public void resetScreen() {
		menu = null;
		timer.restart();
		gameTime = 0;
		spawnTimer = 0;
		image = null;
		player = null;
		environmentObjects = null;
		destructableObjects = null;
		enemyObjects = null;
		powerupObjects = null;
		gameState = 0;
		initObjects();
		initScreen();
		menu = new Menu(false);
		menu.setPaused();
		menu.setLocationRelativeTo(topFrame);
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
    	//result
    	boolean bResult = false;
    	//Bounding box representing the player
    	Rectangle rcPlayer = player.getBounds();
    	
    	//Check player against environment
    	for (Environment obj : environmentObjects)
    	{
    		bResult = player.HandleCollision(obj);
    		if (bResult) break;
    	}
    	if(!bResult)
	    	for (Destructable obj : destructableObjects)
	    	{
	    		if (player.HandleCollision(obj)) break;
	    	}
    	//Check Object collisions
    	for (Powerup obj : powerupObjects)
    	{    	
    		Rectangle rcObj = obj.getBounds();
    		//Check ground collision
    		for (Environment obj2 : environmentObjects)
	    	{
    			bResult = obj.HandleCollision(obj2);
        		if (bResult) break;
	    	}
    		if (!bResult)
	    		//Check Destructable collision
	        	for (Destructable obj2 : destructableObjects)
	        	{
	        		if (obj.HandleCollision(obj2)) break;
	        	}
    		//Check player collision
    		if (rcObj.intersects(rcPlayer))
    		{
    			obj.HandleCollision(player);
    		}
    	}
    	
    	//Check Enemy collisions
    	for (Enemy obj : enemyObjects)
    	{    	
    		Rectangle rcObj = obj.getBounds(); 
    		//Check Environment collision
    		for (Environment obj2 : environmentObjects)
	    	{
    			bResult = obj.HandleCollision(obj2);
        		if (bResult) break;
	    	}
    		//Check Destructable collision
    		if (!bResult)
	    		for (Destructable obj2 : destructableObjects)
		    	{
	        		if(obj.HandleCollision(obj2)) break;
		    	}
    		//Check player collision
    		if (rcObj.intersects(rcPlayer))
    		{
    			player.HandleCollision(obj);
    			obj.HandleCollision(player);
    		}
    	}
    }
}
