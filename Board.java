/**
 * @author Harrison Fah
 * @version 1.0
 * Start Date: 23/5/2018
 * Finish Date: 4/6/2018
 * Copyright 2018, Harrison Fah, All rights reserved.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel {

	static TextPrompt t = new TextPrompt();
	static JFrame f = new JFrame("Barfstone");
	static EndTurn e = new EndTurn();
	static DrawDeck d = new DrawDeck();
	static boolean clicked = false;
	static boolean tie = false;
	static Player player1;
	static Player player2;
	static int x = 10;
	static int y = 10;
	static int targetX;
	static int targetY;
	Card cardClicked = new Card();
	Card cardReleased = new Card();
	static BufferedImage cardBack = null;
	static BufferedImage background = null;
	
	/**
	 * Method for setting up board and creating players along with adding cards to each player's hand
	 * 
	 * @param player1Name Player 1's name
	 * @param player2Name Player 2's name
	 */
	public void setup(String player1Name, String player2Name) {
		player1 = new Player(player1Name, 1, "player1");
		player2 = new Player(player2Name, 0, "player2");
		for (int i = 0; i < 3; i++) {
			player1.addToHand(player1.getDeck().drawCard());
			player2.addToHand(player2.getDeck().drawCard());
		}
		try {
			cardBack = ImageIO.read(new File("C:\\Users\\PortableJelly\\Desktop\\Barfstone Art\\cardBack.png"));
			background = ImageIO.read(new File("C:\\Users\\PortableJelly\\Desktop\\Barfstone Art\\background.png"));
		} catch (IOException e) {
			e.printStackTrace();
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
		/**
		 * Method for taking in mouse clicks, presses, and releases and then interacting with whichever card or button is clicked on.
		 */
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
							if (manaCheck(player1, player1.getHand().get(i).getMana())
									&& player1.getControlled().size() < 5) {
								player1.changeCurrentMana(player1.getHand().get(i).getMana());
								player1.getControlled().add(player1.getHand().get(i));
								player1.getHand().remove(i);
							} else {
								t.newPrompt(
										"Player either does not have enough mana to play this or controls 5 minions.",
										"Attention:");
							}
						}
					}
				} else {
					for (int i = 0; i < player2.getHand().size(); i++) {
						if (mX > player2.getHand().get(i).getX()
								&& mX < player2.getHand().get(i).getX() + player2.getHand().get(i).getWidth()
								&& mY > player2.getHand().get(i).getY()
								&& mY < player2.getHand().get(i).getY() + player2.getHand().get(i).getHeight()) {
							if (manaCheck(player2, player2.getHand().get(i).getMana())
									&& player2.getControlled().size() < 5) {
								player2.changeCurrentMana(player2.getHand().get(i).getMana());
								player2.getControlled().add(player2.getHand().get(i));
								player2.getHand().remove(i);
							} else {
								t.newPrompt(
										"Player either does not have enough mana to play this or controls 5 minions.",
										"Attention:");
							}
						}
					}
				}
				if (mX > e.x && mX < e.x + e.width && mY > e.y && mY < e.y + e.height) {
					e.click();
					if (e.getTurn() == 1) {
						t.newPrompt(player1.getName() + "'s turn has begun.", "Attention:");
						player1.turnStart();
					} else {
						t.newPrompt(player2.getName() + "'s turn has begun.", "Attention:");
						player2.turnStart();
					}
					tieCheck();
				}
				if (mX > d.x && mX < d.x + d.width && mY > d.y && mY < d.y + d.height) {
					if (e.getTurn() == 1) {
						if (manaCheck(player1, 5) && player1.getHand().size() < 6) {
							if (player1.getDeck().checkDeck()) {
								player1.addToHand(player1.getDeck().drawCard());
								player1.changeCurrentMana(5);
							} else {
								t.newPrompt("No cards left in deck.", "Attention:");
							}
						} else {
							t.newPrompt(
									"Player either does not have enough mana to do this or has at least 6 cards in hand.",
									"Attention:");
						}
					} else {
						if (manaCheck(player2, 5) && player1.getHand().size() < 6) {
							if (player2.getDeck().checkDeck()) {
								player2.addToHand(player2.getDeck().drawCard());
								player2.changeCurrentMana(5);
							} else {
								t.newPrompt("No cards left in deck.", "Attention:");
							}
						} else {
							t.newPrompt(
									"Player either does not have enough mana to do this or has at least 6 cards in hand.",
									"Attention:");
						}
					}
				}
			}

			public void mouseEntered(MouseEvent m) {
			}

			public void mouseExited(MouseEvent m) {
			}

			public void mousePressed(MouseEvent m) {
				int mX = m.getX() - 7;
				int mY = m.getY() - 30;
				targetX = mX;
				targetY = mY;
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
						t.newPrompt("Card was either played this turn or already attacked.", "Attention:");
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
				clicked = false;
			}
		});

	}
	
	/**
	 * Method for looping and painting the frame.
	 */
	public void loop() {
		f.repaint();
	}
	
	/**
	 * Paint method that draws all cards, players, and buttons depending on who's turn it is.
	 */
	public void paint(Graphics g) {
		g.drawImage(background, -2, -13, null);
		if (e.getTurn() == 1) {
			for (int i = 0; i < player1.getControlled().size(); i++) {
				if (player1.getControlled().get(i).clicked) {
					player1.getControlled().get(i).setPosition(250 + (100 * i) - 7, 465);
				} else {
					player1.getControlled().get(i).setPosition(250 + (100 * i), 475);
				}
				player1.getControlled().get(i).draw(g);
			}
			for (int i = 0; i < player1.getHand().size(); i++) {
				if (player1.getHand().get(i).clicked) {
					player1.getHand().get(i).setPosition(250 + (100 * i) - 7, 615);
				} else {
					player1.getHand().get(i).setPosition(250 + (100 * i), 625);
				}
				player1.getHand().get(i).draw(g);
			}
			for (int i = 0; i < player2.getControlled().size(); i++) {
				player2.getControlled().get(i).setPosition(250 + (100 * i), 250);
				player2.getControlled().get(i).draw(g);
			}
			for (int i = 0; i < player2.getHand().size(); i++) {
				g.drawImage(cardBack, 250 + (100 * i), 100, 75, 100, null);
			}
			player1.setPosition(125, 515);
			player1.draw(g);
			player2.setPosition(125, 100);
			player2.draw(g);
		} else {
			for (int i = 0; i < player2.getControlled().size(); i++) {
				if (player2.getControlled().get(i).clicked) {
					player2.getControlled().get(i).setPosition(250 + (100 * i) - 7, 465);
				} else {
					player2.getControlled().get(i).setPosition(250 + (100 * i), 475);
				}
				player2.getControlled().get(i).draw(g);
			}
			for (int i = 0; i < player2.getHand().size(); i++) {
				if (player2.getHand().get(i).clicked) {
					player2.getHand().get(i).setPosition(250 + (100 * i) - 7, 615);
				} else {
					player2.getHand().get(i).setPosition(250 + (100 * i), 625);
				}
				player2.getHand().get(i).draw(g);
			}
			for (int i = 0; i < player1.getControlled().size(); i++) {
				player1.getControlled().get(i).setPosition(250 + (100 * i), 250);
				player1.getControlled().get(i).draw(g);
			}
			for (int i = 0; i < player1.getHand().size(); i++) {
				g.drawImage(cardBack, 250 + (100 * i), 100, 75, 100, null);
			}
			player2.setPosition(125, 515);
			player2.draw(g);
			player1.setPosition(125, 100);
			player1.draw(g);
		}
		if (clicked) {
			g.setColor(Color.RED);
			g.fillOval(targetX - 5, targetY - 5, 20, 20);
			g.setColor(Color.WHITE);
			g.fillOval(targetX, targetY, 10, 10);
			g.setColor(Color.RED);
			g.fillOval(targetX + 2, targetY + 2, 5, 5);
		}
		e.draw(g);
		d.draw(g);
	}
	
	/**
	 * Method for checking if a minion's health is below zero and therefore dead.
	 */
	public void healthCheck() {
		for (int i = 0; i < player1.getControlled().size(); i++) {
			if (player1.getControlled().get(i).getHealth() <= 0) {
				player1.getControlled().remove(i);
			}
		}
		for (int i = 0; i < player2.getControlled().size(); i++) {
			if (player2.getControlled().get(i).getHealth() <= 0) {
				player2.getControlled().remove(i);
			}
		}
	}
	
	/**
	 * Checks to see if a player has enough mana to perform an action.
	 * 
	 * @param p Player being checked
	 * @param m Mana cost of action
	 * @return Returns true or false whether or not action can be performed
	 */
	public boolean manaCheck(Player p, int m) {
		if (p.getCurrentMana() < m) {
			return false;
		}
		return true;
	}
	
	/**
	 * Checks to see if a minion can attack.
	 * 
	 * @param c Card being checked
	 * @return Returns true or false whether or not minion can attack.
	 */
	public boolean canAttackCheck(Card c) {
		if (c.getCanAttack() == false) {
			return false;
		}
		return true;
	}
	
	/**
	 * Checks to see if both player's hands, decks, and boards are empty and therefore a tie has happened.
	 */
	public void tieCheck() {
		if (player1.getDeck().returnSize() == 0 && player1.getHand().size() == 0 && player1.getControlled().size() == 0
				&& player2.getDeck().returnSize() == 0 && player2.getHand().size() == 0
				&& player2.getControlled().size() == 0) {
			tie = true;
		}
		tie = false;
	}
	
	/**
	 * Sets the board to visible or not.
	 */
	public void setVisible(boolean b) {
		f.setVisible(b);
	}
	
	/**
	 * Returns the tie boolean.
	 * 
	 * @return Tie boolean
	 */
	public boolean getTie() {
		return tie;
	}

	/**
	 * Returns player 1
	 * 
	 * @return player 1
	 */
	public Player getPlayer1() {
		return player1;
	}
	
	/**
	 * Returns player 2
	 * 
	 * @return player 2
	 */
	public Player getPlayer2() {
		return player2;
	}

}