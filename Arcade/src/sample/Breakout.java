package sample;

import javax.swing.JFrame;
import java.awt.EventQueue;

public class Breakout extends JFrame {

	public Breakout() {
		initUI();
	}

	public void initUI() {
		add(new Board());
		setTitle("Breakout");


		setLocationRelativeTo(null);
		setResizable(false);
		pack();
	}

	public static void main() {
			var game = new Breakout();
			game.setVisible(true);
	}

}
