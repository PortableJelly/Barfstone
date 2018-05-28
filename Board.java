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
	static DrawDeck d = new DrawDeck();
	boolean clicked = false;
	Player player1 = new Player();
	Player player2 = new Player();
	static int x = 10;
	static int y = 10;
	Card cardClicked = new Card();
	Card cardReleased = new Card();
	
	public void setup(){
		f.setSize(1000, 800);
		f.setLocationRelativeTo(null);
		f.add(new Board());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setResizable(false);
		f.addMouseListener(new MouseListener(){
			
			public void mouseClicked(MouseEvent m) {
				int mX = m.getX()-7;
				int mY = m.getY()-30;
				for (int i = 0; i < player1.getHand().size(); i++){
					if(mX > player1.getHand().get(i).getX() && mX < player1.getHand().get(i).getX()+player1.getHand().get(i).getWidth() && mY > player1.getHand().get(i).getY() && mY < player1.getHand().get(i).getY()+player1.getHand().get(i).getHeight()) {
                        player1.getControlled().add(player1.getHand().get(i));
                        player1.getHand().remove(i);
                    }
				}
				if(mX > e.x && mX < e.x+e.width && mY > e.y && mY < e.y+e.height) {
                    e.click();
				}
				if(mX > d.x && mX < d.x+d.width && mY > d.y && mY < d.y+d.height) {
					if (player1.getDeck().checkDeck()){
						player1.getHand().add(player1.getDeck().drawCard());
					}
					else{
						System.out.println("No cards left in deck.");
					}
                    //d.click();
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
				for (int i = 0; i < player1.getHand().size(); i++){
					if(mX > player1.getHand().get(i).getX() && mX < player1.getHand().get(i).getX()+player1.getHand().get(i).getWidth() && mY > player1.getHand().get(i).getY() && mY < player1.getHand().get(i).getY()+player1.getHand().get(i).getHeight()) {
                        player1.getHand().get(i).click();
                    }
				}
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
				if(mX > d.x && mX < d.x+d.width && mY > d.y && mY < d.y+d.height) {
                    d.pressed();
				}
			}
			public void mouseReleased(MouseEvent m) {
				int mX = m.getX()-7;
				int mY = m.getY()-30;
				for (int i = 0; i < player1.getHand().size(); i++){
					player1.getHand().get(i).unclick();
				}
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
				if(mX > d.x && mX < d.x+d.width && mY > d.y && mY < d.y+d.height) {
                    d.unclick();
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
		for (int i = 0; i < player1.getHand().size(); i++){
			player1.getHand().get(i).setPosition(200+(100*i), 650);
			player1.getHand().get(i).draw(g);
		}
		e.draw(g);
		d.draw(g);
	}
	
	public Board(){
		
	}
}