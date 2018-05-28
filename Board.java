import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board extends JPanel{
	
	static JFrame f = new JFrame("Barfstone");
	static EndTurn e = new EndTurn();
	boolean clicked = false;
	Player player1 = new Player();
	static int x = 10;
	static int y = 10;
	Card cardClicked = new Card();
	Card cardReleased = new Card();
	
	public void setup(){
		player1.getControlled().add(player1.deck.drawCard());
		player1.getControlled().get(0).setPosition(200, 500);
		player1.getControlled().add(player1.deck.drawCard());
		player1.getControlled().get(1).setPosition(150, 50);
		System.out.println(player1.getControlled().size());

		f.setSize(1000, 800);
		f.setLocationRelativeTo(null);
		f.add(new Board());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent m) {
				int mX = m.getX()-7;
				int mY = m.getY()-30;
				if(mX > e.x && mX < e.x+e.width && mY > e.y && mY < e.y+e.height) {
                    e.click();
				}
				//x = m.getX();
				//y = m.getY();
			}
			public void mouseEntered(MouseEvent m) {
			}
			public void mouseExited(MouseEvent m) {
			}
			public void mousePressed(MouseEvent m) {
				clicked = false;
				int mX = m.getX()-7;
				int mY = m.getY()-30;
				for (Card c : player1.getControlled()){
					if(mX > c.getX() && mX < c.getX()+c.getWidth() && mY > c.getY() && mY < c.getY()+c.getHeight()) {
                        c.click();
                        cardClicked = c;
                        clicked = true;
                    }
				}
				if(mX > e.x && mX < e.x+e.width && mY > e.y && mY < e.y+e.height) {
                    e.pressed();
				}
			}
			public void mouseReleased(MouseEvent m) {
				int mX = m.getX()-7;
				int mY = m.getY()-30;
				for (Card c : player1.getControlled()){
                        c.unclick(); 
				}
				if (clicked){
				for (Card c : player1.getControlled()){
					if(mX > c.getX() && mX < c.getX()+c.getWidth() && mY > c.getY() && mY < c.getY()+c.getHeight()) {
                        cardReleased = c;
                        cardClicked.takeDamage(cardReleased.getAttack());
                        cardReleased.takeDamage(cardClicked.getAttack());
                        System.out.println("clicked: " + cardClicked.getName() + "(" + cardClicked.getHealth() + "), released: " + cardReleased.getName() + "(" + cardReleased.getHealth() + ")");
                    }
				}
				}
				if(mX > e.x && mX < e.x+e.width && mY > e.y && mY < e.y+e.height) {
                    e.unclick();
				}
			}
		});
	}
	
	public void loop(){
		f.repaint();
	}
	
	public void paint(Graphics g){
		for (int i = 0; i < player1.getControlled().size(); i++){
			player1.getControlled().get(i).setPosition(200+(100*i), 500);
			player1.getControlled().get(i).draw(g);
		}
		e.draw(g);
	}
	
	public Board(){
		
	}
}