import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board extends JPanel {

	static JFrame f = new JFrame("Barfstone");
	static EndTurn e = new EndTurn();
	static DrawDeck d = new DrawDeck();
	boolean clicked = false;
	static Player player1;
	static Player player2;
	static int x = 10;
	static int y = 10;
	static int targetX;
	static int targetY;
	Card cardClicked = new Card();
	Card cardReleased = new Card();

	public void setup(String player1Name, String player2Name) {
		player1 = new Player(player1Name, 1);
		player2 = new Player(player2Name, 0);
		for (int i = 0; i < 3; i++) {
			player1.addToHand(player1.getDeck().drawCard());
			player2.addToHand(player2.getDeck().drawCard());
		}
		f.setSize(1000, 800);
		f.setLocationRelativeTo(null);
		f.add(new Board());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setResizable(false);
		f.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent m) {
				targetX = m.getX() - 7;
				targetY = m.getY() - 30;
			}
		});
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
							if (manaCheck(player1, player1.getHand().get(i).getMana())) {
								player1.changeCurrentMana(player1.getHand().get(i).getMana());
								player1.getControlled().add(player1.getHand().get(i));
								player1.getHand().remove(i);
							} else {
								System.out.println("Player does not have enough mana to play this.");
							}
						}
					}
				} else {
					for (int i = 0; i < player2.getHand().size(); i++) {
						if (mX > player2.getHand().get(i).getX()
								&& mX < player2.getHand().get(i).getX() + player2.getHand().get(i).getWidth()
								&& mY > player2.getHand().get(i).getY()
								&& mY < player2.getHand().get(i).getY() + player2.getHand().get(i).getHeight()) {
							if (manaCheck(player2, player2.getHand().get(i).getMana())) {
								player2.changeCurrentMana(player2.getHand().get(i).getMana());
								player2.getControlled().add(player2.getHand().get(i));
								player2.getHand().remove(i);
							} else {
								System.out.println("Player does not have enough mana to play this.");
							}
						}
					}
				}
				if (mX > e.x && mX < e.x + e.width && mY > e.y && mY < e.y + e.height) {
					e.click();
					if (e.getTurn() == 1) {
						player1.turnStart();
					} else {
						player2.turnStart();
					}
				}
				if (mX > d.x && mX < d.x + d.width && mY > d.y && mY < d.y + d.height) {
					if (e.getTurn() == 1) {
						if (manaCheck(player1, 5) && player1.getHand().size() < 6) {
							if (player1.getDeck().checkDeck()) {
								player1.addToHand(player1.getDeck().drawCard());
								System.out.println(player1.getHand().size());
							} else {
								System.out.println("No cards left in deck.");
							}
						} else {
							System.out.println(
									"Player either does not have enough mana to do this or has at least 6 cards in hand.");
						}
					} else {
						if (manaCheck(player2, 5) && player1.getHand().size() < 6) {
							if (player2.getDeck().checkDeck()) {
								player2.addToHand(player2.getDeck().drawCard());
								System.out.println(player2.getHand().size());
							} else {
								System.out.println("No cards left in deck.");
							}
						} else {
							System.out.println(
									"Player either does not have enough mana to do this or has at least 6 cards in hand.");
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
					if (canAttackCheck(cardClicked)) {
						if (e.getTurn() == 1) {
							for (Card c : player2.getControlled()) {
								if (mX > c.getX() && mX < c.getX() + c.getWidth() && mY > c.getY()
										&& mY < c.getY() + c.getHeight()) {
									cardReleased = c;
									cardClicked.takeDamage(cardReleased.getAttack());
									cardReleased.takeDamage(cardClicked.getAttack());
									System.out.println("clicked: " + cardClicked.getName() + "("
											+ cardClicked.getHealth() + "), released: " + cardReleased.getName() + "("
											+ cardReleased.getHealth() + ")");
									cardClicked.changeCanAttack(false);
								}
							}
							if (mX > player2.getX() && mX < player2.getX() + player2.getWidth() && mY > player2.getY()
									&& mY < player2.getY() + player2.getHeight()) {
								player2.takeDamage(cardClicked.getAttack());
								cardClicked.changeCanAttack(false);
							}
						} else {
							for (Card c : player1.getControlled()) {
								if (mX > c.getX() && mX < c.getX() + c.getWidth() && mY > c.getY()
										&& mY < c.getY() + c.getHeight()) {
									cardReleased = c;
									cardClicked.takeDamage(cardReleased.getAttack());
									cardReleased.takeDamage(cardClicked.getAttack());
									System.out.println("clicked: " + cardClicked.getName() + "("
											+ cardClicked.getHealth() + "), released: " + cardReleased.getName() + "("
											+ cardReleased.getHealth() + ")");
									cardClicked.changeCanAttack(false);
								}
							}
							if (mX > player1.getX() && mX < player1.getX() + player1.getWidth() && mY > player1.getY()
									&& mY < player1.getY() + player1.getHeight()) {
								player1.takeDamage(cardClicked.getAttack());
								cardClicked.changeCanAttack(false);
							}
						}
						healthCheck();
					} else {
						System.out.println("Card was either played this turn or already attacked.");
					}
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
				e.unclick();
				d.unclick();
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
			player1.setPosition(75, 550);
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
			player2.setPosition(75, 550);
			player2.draw(g);
			player1.setPosition(75, 150);
			player1.draw(g);
		}
		if (clicked) {
			System.out.println("clicked");
			g.setColor(Color.CYAN);
			g.fillOval(targetX, targetY, 10, 10);
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

	public boolean manaCheck(Player p, int m) {
		if (p.getCurrentMana() < m) {
			return false;
		}
		return true;
	}

	public boolean canAttackCheck(Card c) {
		if (c.getCanAttack() == false) {
			return false;
		}
		return true;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public Board() {

	}
}
