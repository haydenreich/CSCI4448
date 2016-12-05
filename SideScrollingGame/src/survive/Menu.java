package survive;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.atomic.AtomicBoolean;


public class Menu extends JDialog implements ActionListener {
	
	public Menu(boolean a) {
		running = a;
		paused = new AtomicBoolean(true);
		initMenu();
	}
	private AtomicBoolean paused;
	private boolean running;
	private Game context;
	private JButton play;
	private JButton help;
	private JButton highScore;
	private ActionListener actionListener;
	private final Font font = new Font("Arial", Font.PLAIN, 32);

	public boolean getPaused() {
		return paused.get();
	}
	
	public void setPaused() {
		paused.set(true);
		if(running)
		{
			play.setText("Resume Game");
		}
	}
	
	public void setRunning() {
		running = true;
	}
	
	public void actionPerformed(ActionEvent e) {
		if("start".equals(e.getActionCommand())) {
			paused.set(false);
			this.setVisible(false);
		}
		else if("help".equals(e.getActionCommand())) {
			JOptionPane.showMessageDialog(null, "This is the help menu");
		}
	}
	
	public void initMenu() {
		setSize(300, 135);
		JPanel subPanel = new JPanel();
		subPanel.setSize(400, 300);
		subPanel.setLayout(new BorderLayout());
		if(running) {
			play = new JButton("Resume Game");
		}
		else {
			play = new JButton("Start Game");
			
		}
		help = new JButton("Help");
		help.setActionCommand("help");
		help.addActionListener(this);
		help.setFont(font);
		
		play.setActionCommand("start");
		play.addActionListener(this);
		play.setFont(font);
		
		highScore = new JButton("High Scores");
		highScore.setActionCommand("highScore");
		highScore.addActionListener(this);
		highScore.setFont(font);
		
		subPanel.add(play, BorderLayout.NORTH);
		subPanel.add(help, BorderLayout.SOUTH);
		subPanel.add(highScore, BorderLayout.CENTER);
		
		this.add(subPanel);
		setUndecorated(true);
		setVisible(true);	
	}
	
}
