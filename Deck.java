import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Deck {
	
	ArrayList<Card> unshuffled = new ArrayList<Card>();
	ArrayList<Integer> usedNumbers = new ArrayList<Integer>();
	Queue<Card> cards = new LinkedList<Card>();
	
	public Deck(){
		ArrayList<String> a = fileReader();
		for(int i = 0; i < a.size(); i++){
			unshuffled.add(new Card(((String) a.get(i)).split(",")));
		}
		boolean foundNumber = false;
		for (int i = 0; i < unshuffled.size(); i++){
			do{
			int randCard = (int) (Math.random()*(unshuffled.size()));
			if (!usedNumbers.contains(randCard)){
				cards.add(unshuffled.get(randCard));
				usedNumbers.add(randCard);
				foundNumber = true;
			}
			}while(foundNumber == false);
			foundNumber = false;
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
		try{
		if (!cards.peek().equals(null)){
			return cards.poll();
		}
		}
		catch(NullPointerException e){
			return null;
		}
		return null;
	}
	
	public boolean checkDeck(){
		try{
			if (!cards.peek().equals(null)){
				return true;
			}
		}
		catch(NullPointerException e){
		}
		return false;
	}
	
	public int returnSize(){
		return cards.size();
	}
	
	public void printDeck(){
		for (Card card : cards){
			System.out.println(card.getName() + card.getMana() + card.getAttack() + card.getHealth());
			}
	}
	
}