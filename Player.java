import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Player {
	int num;
	int health = 30;
	int mana = 1;
	static int x = 200;
	static int y = 650;
	static int width = 100;
	static int height = 150;
	Deck deck = new Deck();
	ArrayList<Card> hand = new ArrayList<Card>();
	ArrayList<Card> controlled = new ArrayList<Card>();
	
	public Player(int i){
		num = i;
	}
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