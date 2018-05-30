import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board extends JPanel {

	static JFrame f = new JFrame("Barfstone");
	static EndTurn e = new EndTurn();
	static DrawDeck d = new DrawDeck();
	boolean clicked = false;
	static Player player1 = new Player(1);
	static Player player2 = new Player(2);
	static int x = 10;
	static int y = 10;
	Card cardClicked = new Card();
	Card cardReleased = new Card();

	public void setup() {
		player2.addToHand(new Card("Test", 1, 2, 3));
		player2.getControlled().add(player2.getDeck().drawCard());

		f.setSize(1000, 800);
		f.setLocationRelativeTo(null);
		f.add(new Board());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setResizable(false);
		f.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent m) {
				int mX = m.getX() - 7;
				int mY = m.getY() - 30;
				if (e.getTurn() == 1) {
					for (int i = 0; i < player1.getHand().size(); i++) {
						if (mX > player1.getHand().get(i).getX()
								&& mX < player1.getHand().get(i).getX() + player1.getHand().get(i).getWidth()
								&& mY > player1.getHand().get(i).getY()
								&& mY < player1.getHand().get(i).getY() + player1.getHand().get(i).getHeight()) {
							player1.getControlled().add(player1.getHand().get(i));
							player1.getHand().remove(i);
						}
					}
				} else {
					for (int i = 0; i < player2.getHand().size(); i++) {
						if (mX > player2.getHand().get(i).getX()
								&& mX < player2.getHand().get(i).getX() + player2.getHand().get(i).getWidth()
								&& mY > player2.getHand().get(i).getY()
								&& mY < player2.getHand().get(i).getY() + player2.getHand().get(i).getHeight()) {
							player2.getControlled().add(player2.getHand().get(i));
							player2.getHand().remove(i);
						}
					}
				}
				if (mX > e.x && mX < e.x + e.width && mY > e.y && mY < e.y + e.height) {
					e.click();
				}
				if (mX > d.x && mX < d.x + d.width && mY > d.y && mY < d.y + d.height) {
					if (e.getTurn() == 1) {
						if (player1.getDeck().checkDeck()) {
							player1.addToHand(player1.getDeck().drawCard());
							System.out.println(player1.getHand().size());
						} else {
							System.out.println("No cards left in deck.");
						}
					} else {
						if (player2.getDeck().checkDeck()) {
							player2.addToHand(player2.getDeck().drawCard());
							System.out.println(player2.getHand().size());
						} else {
							System.out.println("No cards left in deck.");
						}
					}
					// d.click();
				}
				// x = m.getX();
				// y = m.getY();
			}

			public void mouseEntered(MouseEvent m) {
			}

			public void mouseExited(MouseEvent m) {
			}

			public void mousePressed(MouseEvent m) {
				clicked = false;
				int mX = m.getX() - 7;
				int mY = m.getY() - 30;
				if (e.getTurn() == 1) {
					for (int i = 0; i < player1.getHand().size(); i++) {
						if (mX > player1.getHand().get(i).getX()
								&& mX < player1.getHand().get(i).getX() + player1.getHand().get(i).getWidth()
								&& mY > player1.getHand().get(i).getY()
								&& mY < player1.getHand().get(i).getY() + player1.getHand().get(i).getHeight()) {
							player1.getHand().get(i).click();
						}
					}
					for (Card c : player1.getControlled()) {
						if (mX > c.getX() && mX < c.getX() + c.getWidth() && mY > c.getY()
								&& mY < c.getY() + c.getHeight()) {
							c.click();
							cardClicked = c;
							clicked = true;
						}
					}
				} else {
					for (int i = 0; i < player2.getHand().size(); i++) {
						if (mX > player2.getHand().get(i).getX()
								&& mX < player2.getHand().get(i).getX() + player2.getHand().get(i).getWidth()
								&& mY > player2.getHand().get(i).getY()
								&& mY < player2.getHand().get(i).getY() + player2.getHand().get(i).getHeight()) {
							player2.getHand().get(i).click();
						}
					}
					for (Card c : player2.getControlled()) {
						if (mX > c.getX() && mX < c.getX() + c.getWidth() && mY > c.getY()
								&& mY < c.getY() + c.getHeight()) {
							c.click();
							cardClicked = c;
							clicked = true;
						}
					}
				}
				if (mX > e.x && mX < e.x + e.width && mY > e.y && mY < e.y + e.height) {
					e.pressed();
				}
				if (mX > d.x && mX < d.x + d.width && mY > d.y && mY < d.y + d.height) {
					d.pressed();
				}
			}

			public void mouseReleased(MouseEvent m) {
				int mX = m.getX() - 7;
				int mY = m.getY() - 30;
				if (clicked) {
					if (e.getTurn() == 1) {
						for (Card c : player2.getControlled()) {
							if (mX > c.getX() && mX < c.getX() + c.getWidth() && mY > c.getY()
									&& mY < c.getY() + c.getHeight()) {
								cardReleased = c;
								cardClicked.takeDamage(cardReleased.getAttack());
								cardReleased.takeDamage(cardClicked.getAttack());
								System.out.println("clicked: " + cardClicked.getName() + "(" + cardClicked.getHealth()
										+ "), released: " + cardReleased.getName() + "(" + cardReleased.getHealth()
										+ ")");
							}
						}
					} else {
						for (Card c : player1.getControlled()) {
							if (mX > c.getX() && mX < c.getX() + c.getWidth() && mY > c.getY()
									&& mY < c.getY() + c.getHeight()) {
								cardReleased = c;
								cardClicked.takeDamage(cardReleased.getAttack());
								cardReleased.takeDamage(cardClicked.getAttack());
								System.out.println("clicked: " + cardClicked.getName() + "(" + cardClicked.getHealth()
										+ "), released: " + cardReleased.getName() + "(" + cardReleased.getHealth()
										+ ")");

							}
						}
					}
					healthCheck();
				}
				for (Card c : player1.getControlled()) {
					c.unclick();
				}
				for (Card c : player2.getControlled()) {
					c.unclick();
				}
				for (Card c : player1.getHand()) {
					c.unclick();
				}
				for (Card c : player2.getHand()) {
					c.unclick();
				}
				if (mX > e.x && mX < e.x + e.width && mY > e.y && mY < e.y + e.height) {
					e.unclick();
				}
				if (mX > d.x && mX < d.x + d.width && mY > d.y && mY < d.y + d.height) {
					d.unclick();
				}
			}
		});
	}

	public void loop() {
		f.repaint();
	}

	public void paint(Graphics g) {
		if (e.getTurn() == 1) {
			for (int i = 0; i < player1.getControlled().size(); i++) {
				player1.getControlled().get(i).setPosition(200 + (100 * i), 500);
				player1.getControlled().get(i).draw(g);
			}
			for (int i = 0; i < player1.getHand().size(); i++) {
				player1.getHand().get(i).setPosition(200 + (100 * i), 650);
				player1.getHand().get(i).draw(g);
			}
			for (int i = 0; i < player2.getControlled().size(); i++) {
				player2.getControlled().get(i).setPosition(200 + (100 * i), 300);
				player2.getControlled().get(i).draw(g);
			}
			for (int i = 0; i < player2.getHand().size(); i++) {
				g.setColor(Color.RED);
				g.fillRect(200 + (100 * i), 150, 75, 100);
			}
			player1.setPosition(75, 650);
			player1.draw(g);
			player2.setPosition(75, 150);
			player2.draw(g);
		} else {
			for (int i = 0; i < player2.getControlled().size(); i++) {
				player2.getControlled().get(i).setPosition(200 + (100 * i), 500);
				player2.getControlled().get(i).draw(g);
			}
			for (int i = 0; i < player2.getHand().size(); i++) {
				player2.getHand().get(i).setPosition(200 + (100 * i), 650);
				player2.getHand().get(i).draw(g);
			}
			for (int i = 0; i < player1.getControlled().size(); i++) {
				player1.getControlled().get(i).setPosition(200 + (100 * i), 300);
				player1.getControlled().get(i).draw(g);
			}
			for (int i = 0; i < player1.getHand().size(); i++) {
				g.setColor(Color.RED);
				g.fillRect(200 + (100 * i), 150, 75, 100);
			}
			player2.setPosition(75, 650);
			player2.draw(g);
			player1.setPosition(75, 150);
			player1.draw(g);
		}
		e.draw(g);
		d.draw(g);
	}

	public void healthCheck() {
		for (int i = 0; i < player1.getControlled().size(); i++) {
			if (player1.getControlled().get(i).getHealth() <= 0) {
				System.out.println(player1.getControlled().get(i).getName() + " died.");
				player1.getControlled().remove(i);
			}
		}
		for (int i = 0; i < player2.getControlled().size(); i++) {
			if (player2.getControlled().get(i).getHealth() <= 0) {
				System.out.println(player2.getControlled().get(i).getName() + " died.");
				player2.getControlled().remove(i);
			}
		}
	}

	public Board() {

	}
}