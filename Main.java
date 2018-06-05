import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main {

	static JButton play = new JButton("Play new game");
	static boolean playClicked = false;
	static JButton instructions = new JButton("How to play");
	static JButton quit = new JButton("Quit");
	static JFrame f = new JFrame("Barfstone");
	static JPanel p = new JPanel();

	public static void main(String[] args) {
		f.setSize(400, 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p.setLayout(null);
		f.add(p);
		play.setBounds(125, 150, 150, 50);
		play.addActionListener(new Action());
		p.add(play);
		instructions.setBounds(125, 250, 150, 50);
		instructions.addActionListener(new Action());
		p.add(instructions);
		quit.setBounds(125, 350, 150, 50);
		quit.addActionListener(new Action());
		p.add(quit);
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("C:\\Users\\PortableJelly\\Desktop\\Barfstone Art\\barfstone.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel picL = new JLabel(new ImageIcon(myPicture));
		picL.setBounds(70, 25, 262, 108);
		p.add(picL);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setVisible(true);

		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//System.out.println("true");
			if (playClicked) {
				nameTake();
			}
		}
	}

	static class Action implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (play.equals(e.getSource())) {
				playClicked = true;
				System.out.println("clicked");
				// test();
				/*
				 * Board board = new Board(); board.setup("test", "test"); while
				 * (true){ board.loop(); System.out.println("loop"); try {
				 * Thread.sleep(1000000); } catch (InterruptedException e1) {
				 * e1.printStackTrace(); } }
				 */
				// System.out.println("play source");
				// f.setVisible(false);

			} else if (instructions.equals(e.getSource())) {
				instructions();
			} else if (quit.equals(e.getSource())) {
				System.exit(0);
			}
		}
	}

	private static void gameStart(String p1, String p2) {
		playClicked = false;
		TextPrompt t = new TextPrompt();
		Board board = new Board();
		board.setup(p1, p2);
		while (board.getPlayer1().getHealth() > 0 && board.getPlayer2().getHealth() > 0 && board.getTie() == false) {
			board.loop();
		}
		if (board.getPlayer1().getHealth() < 1) {
			t.newPrompt(board.getPlayer2().getName() + " wins.", "Attention:");
		} else if (board.getPlayer2().getHealth() < 1) {
			t.newPrompt(board.getPlayer1().getName() + " wins.", "Attention:");
		} else if (board.getTie() == true) {
			t.newPrompt("Game tied.", "Attention:");
		}
		board.setVisible(false);
		main(null);
	}

	public static void nameTake() {
		TextPrompt t = new TextPrompt();
		f.setVisible(false);
		String player1Name = null;
		while (player1Name == null) {
			player1Name = JOptionPane.showInputDialog("What is player 1's name?", player1Name);
			if (player1Name != null) {
				if (player1Name.length() > 10 || player1Name.length() == 0) {
					t.newPrompt("Name must be between 1 and 10 characters.", "Attention:");
					player1Name = null;
				}
			}
		}
		String player2Name = null;
		while (player2Name == null) {
			player2Name = JOptionPane.showInputDialog("What is player 2's name?", player1Name);
			if (player2Name != null) {
				if (player2Name.length() > 10 || player2Name.length() == 0) {
					t.newPrompt("Name must be between 1 and 10 characters.", "Attention:");
					player2Name = null;
				}
			}
		}
		gameStart(player1Name, player2Name);
	}

	public static void instructions() {
		JFrame f2 = new JFrame("Instructions.");
		f2.setSize(800, 200);
		f2.setLocationRelativeTo(null);
		f2.setVisible(true);
		f2.setResizable(false);
		JPanel p2 = new JPanel();
		f2.add(p2);
		JLabel l = new JLabel("Barfstone is a card game played between two players.");
		JLabel l2 = new JLabel(
				"Each player takes turn making moves with the goal of making the opponent's HP equal zero.");
		JLabel l3 = new JLabel(
				"This is done by using a player's minions. Minions can be played onto the board by clicking on a card in the current player's hand.");
		JLabel l4 = new JLabel("Minions can then be clicked and dragged while on the board to attack.");
		JLabel l5 = new JLabel(
				"A minion can only be played from the hand by using the current player's mana which starts at zero and increased by one each turn up to ten.");
		JLabel l6 = new JLabel("A minion can only attack once per turn and cannot attack the turn it has been played.");
		JLabel l7 = new JLabel(
				"Each player also has access to a 'draw card' button which uses 5 mana to draw a card from the player's deck.");
		p2.add(l);
		p2.add(l2);
		p2.add(l3);
		p2.add(l4);
		p2.add(l5);
		p2.add(l6);
		p2.add(l7);

	}

}