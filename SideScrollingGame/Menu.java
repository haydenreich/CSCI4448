import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.concurrent.atomic.AtomicBoolean;


public class Menu extends JDialog implements ActionListener {
	
	public Menu() {
		paused = new AtomicBoolean(true);
		initMenu();
	}
	
	private AtomicBoolean paused;
	private boolean running;
	private Game context;
	private JButton play;
	private JButton help;
	private ActionListener actionListener;
	
	
	public void render(){
		ImageIcon button = new ImageIcon("button.png");
		
		JButton startGame = new JButton("Start Game", button);
		startGame.setActionCommand("start");
		startGame.setVerticalTextPosition(AbstractButton.CENTER);
		startGame.setHorizontalTextPosition(AbstractButton.CENTER);
		
		add(startGame);
	}
	
	public void actionPerformed(ActionEvent e) {
		if("start".equals(e.getActionCommand())) {
			paused.set(false);
			this.dispose();
		}
		else if("help".equals(e.getActionCommand())) {
			JOptionPane.showMessageDialog(null, "This is the help menu");
		}
	}
	
	public boolean getPaused() {
		return paused.get();
	}
	
	public void initMenu() {
		setSize(300, 200);
		JPanel subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));
		if(running) {
			play = new JButton("Resume Game");
		}
		else {
			play = new JButton("Start Game");
			
		}
		help = new JButton("Help");
		help.setActionCommand("help");
		help.addActionListener(this);
		
		play.setActionCommand("start");
		play.addActionListener(this);
		
		subPanel.add(play);
		subPanel.add(help);
		
		this.add(subPanel);
		setUndecorated(true);
		setVisible(true);	
		pack();
	}
	
}
