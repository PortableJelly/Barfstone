/**
 * @author Harrison Fah
 * @version 1.0
 * Start Date: 23/5/2018
 * Finish Date: 4/6/2018
 * Copyright 2018, Harrison Fah, All rights reserved.
 */

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
	
	/**
	 * Reads from the file of cards and adds them to an array, then shuffles the cards into a queue.
	 */
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
	
	/**
	 * Reads the card data from the file and puts it into an array list.
	 * 
	 * @return Returns the array list of data.
	 */
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
	
	/**
	 * Takes a card from the deck and returns it.
	 * 
	 * @return Card taken from deck.
	 */
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
	
	/**
	 * Checks deck to see if there is a card that can be drawn.
	 */
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
	
	/**
	 * Returns size of deck.
	 * 
	 * @return Size of deck
	 */
	public int returnSize(){
		return cards.size();
	}
	
}