import java.util.ArrayList;

public class Player {
	int health = 30;
	int mana = 1;
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

}
