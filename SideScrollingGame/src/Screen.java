
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Screen extends JPanel implements ActionListener {
	
	private Menu menu;
	private Timer timer;
	private final int DELAY = 10;
	
	public Screen(){
		initScreen();
	}
	
	private enum STATE{
		MENU,
		GAME
	};
	private STATE State = STATE.MENU;
	
	public void initScreen(){
		//addKeyListener(new TAdapter());
		setFocusable(true);
		menu = new Menu();
		if(State == STATE.GAME){
			timer = new Timer(DELAY, this);
			timer.start();
		}
	}
	Character c = new Character();
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(State == STATE.GAME){
			c.draw(g);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
