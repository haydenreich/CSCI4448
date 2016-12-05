import javax.swing.*;

import java.awt.Color;
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
	private ActionListener actionListener;

	public boolean getPaused() {
		return paused.get();
	}
	
	public void setPaused() {
		paused.set(true);
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
		setSize(400, 300);
		JPanel subPanel = new JPanel();
		subPanel.setSize(400, 300);
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
