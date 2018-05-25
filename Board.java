import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board extends JPanel{
	
	static JFrame f = new JFrame("WINDOW NAME");
	
	static EndTurn e = new EndTurn();
	
	static Deck deck = new Deck();
	static ArrayList<Card> friendlyCards = new ArrayList<Card>();
	static int x = 10;
	static int y = 10;
	
	public void setup(){
		friendlyCards.add(deck.drawCard());
		friendlyCards.get(0).setPosition(50, 50);
		friendlyCards.add(deck.drawCard());
		friendlyCards.get(1).setPosition(150, 50);

		f.setSize(1000, 800);
		f.setLocationRelativeTo(null);
		f.add(new Board());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent m) {
				x = m.getX();
				y = m.getY();
			}
			public void mouseEntered(MouseEvent m) {
			}
			public void mouseExited(MouseEvent m) {
			}
			public void mousePressed(MouseEvent m) {
				int mX = m.getX()-7;
				int mY = m.getY()-30;
				for (Card c : friendlyCards){
					if(mX > c.getX() && mX < c.getX()+c.getWidth() && mY > c.getY() && mY < c.getY()+c.getHeight()) {
                        c.click();  
                    }
				}
				if(mX > e.x && mX < e.x+e.width && mY > e.y && mY < e.y+e.height) {
                    e.click();
				}
				
			}
			public void mouseReleased(MouseEvent m) {
				for (Card c : friendlyCards){
                        c.unclick(); 
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
		e.draw(g);
	}
	
	public Board(){
		
	}
}