
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Game extends JFrame {
	public Game() {
		initUI();
	}
	
	private void initUI() {
		add(new Screen());
		setSize(600,600);
		setTitle("Survive");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run(){
				Game ex = new Game();
				ex.setVisible(true);
			}
		});
	}
}
