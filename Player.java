import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Player {
	String name = "blank";
	int health = 30;
	int mana = 1;
	int currentMana;
	int x = 200;
	int y = 650;
	int width = 100;
	int height = 150;
	Deck deck = new Deck();
	ArrayList<Card> hand = new ArrayList<Card>();
	ArrayList<Card> controlled = new ArrayList<Card>();
	
	public Player(String name, int i){
		this.name = name;
		mana = i;
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
	
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
		g.setColor(Color.WHITE);
		g.drawString(name, x+(width/2)-(name.length()*2), y+(height/2));
	}

}