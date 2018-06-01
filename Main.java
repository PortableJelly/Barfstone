import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main {

	static JButton play = new JButton("Play new game");
	static JButton instructions = new JButton("How to play");
	static JButton quit = new JButton("Quit");

	public static void main(String[] args) {
		
		// String input = null;
		// input = JOptionPane.showInputDialog(null, input);
		// System.out.println(input);

		JFrame f = new JFrame("Barfstone");
		f.setSize(400, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		JPanel p = new JPanel();
		f.add(p);
		p.add(play);
		play.addActionListener(new Action());
		p.add(instructions);
		instructions.addActionListener(new Action());
		p.add(quit);
		quit.addActionListener(new Action());
		//gameStart();
		// b.setBounds(50, 50, 50, 50);
		// p.add(b);

		// System.exit(0);
	}
	
	public static void gameStart(){
		TextPrompt t = new TextPrompt();
		Board board = new Board();
		board.setup("Mike", "Ike");
		while (board.getPlayer1().getHealth() > 0 && board.getPlayer2().getHealth() > 0
				&& board.getTie() == false) {
			board.loop();
		}
		if (board.getPlayer1().getHealth() < 1) {
			t.newPrompt(board.getPlayer2().getName() + " wins.", "Attention:");
		} else if (board.getPlayer2().getHealth() < 1) {
			t.newPrompt(board.getPlayer1().getName() + " wins.", "Attention:");
		}
		if (board.getTie() == false) {
			t.newPrompt("Game tied.", "Attention:");
		}
	}

	static class Action implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (play.getModel().isArmed()) {
				play.getModel().setArmed(false);
				gameStart();
			} else if (instructions.getModel().isArmed()) {
				System.out.println("instructions");
			} else if (quit.getModel().isArmed()) {
				System.exit(0);
			}
		}
	}

}