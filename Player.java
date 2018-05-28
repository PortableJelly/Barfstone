import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Player {
	int health = 30;
	int mana = 1;
	int x = 200;
	int y = 650;
	int width = 100;
	int height = 150;
	static Deck deck = new Deck();
	static ArrayList<Card> hand = new ArrayList<Card>();
	static ArrayList<Card> controlled = new ArrayList<Card>();
	
	public void turnStart(){
		mana++;
	}
	
	public void addToHand(Card c){
		hand.add(c);
	}
	
	public void playToBoard(Card c){
		controlled.add(c);
		for (int i = 0; i < hand.size(); i++){
			if (hand.get(i) == c){
				hand.remove(i);
				break;
			}
		}
	}
	
	public ArrayList<Card> getControlled(){
		return controlled;
	}
	
	public Deck getDeck(){
		return deck;
	}
	
	public ArrayList<Card> getHand(){
		return hand;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
	}

}