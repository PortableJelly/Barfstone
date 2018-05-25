import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Deck {
	
	Queue<Card> cards = new LinkedList<Card>();
	
	public Deck(){
		ArrayList a = fileReader();
		for(int i = 0; i < a.size(); i++){
			cards.add(new Card(((String) a.get(i)).split(", ")));
		}
	}
	
	public ArrayList<String> fileReader(){
		ArrayList<String> a = null;
		try{
			FileInputStream fstream = new FileInputStream(
					"cardData.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			a = new ArrayList<String>();
			String hasNextLine = br.readLine();
			while (hasNextLine != null) {
				a.add(hasNextLine);
				hasNextLine = br.readLine();
			}
			br.close();	
		}
		catch(IOException e){
			System.out.println("Error Ocurred");
		}
		return a;
	}
	
	public Card drawCard(){
		return cards.poll();
	}
	
	public void printDeck(){
		for (Card card : cards){
			System.out.println(card.getName() + card.getMana() + card.getAttack() + card.getHealth());
			}
	}
	
}