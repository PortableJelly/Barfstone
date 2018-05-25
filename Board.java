import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board extends JPanel{
	
	static JFrame f = new JFrame("WINDOW NAME");
	
	static Deck deck = new Deck();
	static ArrayList<Card> friendlyCards = new ArrayList<Card>();
	static int x = 10;
	static int y = 10;
	
	public void setup(){
		friendlyCards.add(deck.drawCard());
		friendlyCards.get(0).setPosition(50, 50);
		friendlyCards.add(deck.drawCard());
		friendlyCards.get(1).setPosition(150, 50);
		f.setSize(500, 500);
		f.setLocationRelativeTo(null);
		f.add(new Board());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent m) {
				x = m.getX() - 37;
				y = m.getY() - 75;
			}
			public void mouseEntered(MouseEvent m) {
			}
			public void mouseExited(MouseEvent m) {
			}
			public void mousePressed(MouseEvent m) {
				for (Card c : friendlyCards){
					if(m.getX() > c.x-c.getWidth()/2 && m.getX() < c.x+c.getWidth()/2 && m.getY() > c.y-c.getHeight()/2 && m.getY() < c.y+c.getHeight()/2) {
                        //c.setPosition(m.getX(), m.getY());
                        c.click();
                    }
				}
				
			}
			public void mouseReleased(MouseEvent m) {
				for (Card c : friendlyCards){
					if(m.getX() > c.x-c.getWidth()/2 && m.getX() < c.x+c.getWidth()/2 && m.getY() > c.y-c.getHeight()/2 && m.getY() < c.y+c.getHeight()/2) {
                        //c.setPosition(m.getX(), m.getY());
                        c.unclick();
                    }
				}
			}
		});
	}
	
	public void loop(){
		f.repaint();
	}
	
	public void paint(Graphics g){
		for (Card card : friendlyCards){
			card.draw(g);
		}
	}
	
	public Board(){
		
	}
}