/**
 * @author Harrison Fah
 * @version 1.0
 * Start Date: 23/5/2018
 * Finish Date: 4/6/2018
 * Copyright 2018, Harrison Fah, All rights reserved.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Player {
	String name = "blank";
	int health = 30;
	int mana = 1;
	int currentMana = 1;
	int x = 300;
	int y = 600;
	int width = 100;
	int height = 150;
	Deck deck = new Deck();
	ArrayList<Card> hand = new ArrayList<Card>();
	ArrayList<Card> controlled = new ArrayList<Card>();
	BufferedImage image = null;
	
	/**
	 * Constructor for making new player.
	 * 
	 * @param name Name of player.
	 * @param i Starting mana.
	 * @param imageName Name of image used.
	 */
	public Player(String name, int i, String imageName){
		this.name = name;
		mana = i;
		currentMana = i;
		try {
			image = ImageIO.read(new File("C:\\Users\\PortableJelly\\Desktop\\Barfstone Art\\" + imageName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns name of player.
	 * 
	 * @return Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns x value.
	 * 
	 * @return X position.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Returns y value.
	 * 
	 * @return Y position.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Returns width.
	 * 
	 * @return Width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns height.
	 * 
	 * @return Height
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Returns health.
	 * 
	 * @return Health.
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Returns mana.
	 * 
	 * @return Mana.
	 */
	public int getMana() {
		return mana;
	}
	
	/**
	 * Returns current mana.
	 * 
	 * @return Current mana
	 */
	public int getCurrentMana() {
		return currentMana;
	}
	
	/**
	 * Removes mana from current mana.
	 */
	public void changeCurrentMana(int remove){
		currentMana = currentMana - remove;
	}
	
	/**
	 * When player's turn start, increases mana and resets current mana, draws a card, and sets all controlled minions to being able to attack.
	 */
	public void turnStart(){
		if (mana != 10){
		mana++;
		}
		currentMana = mana;
		if (deck.checkDeck() && hand.size() < 6){
		hand.add(deck.drawCard());
		}
		for (Card c : controlled){
			c.changeCanAttack(true);
		}
		
	}
	
	/**
	 * Deals damage to player.
	 * 
	 * @param damage Damage dealt.
	 */
	public void takeDamage(int damage){
		health = health - damage;
	}
	
	/**
	 * Adds card to player's hand.
	 * 
	 * @param c Card added.
	 */
	public void addToHand(Card c){
		hand.add(c);
	}
	
	/**
	 * Adds a card to the player's controlled minions.
	 * 
	 * @param c Card played.
	 */
	public void playToBoard(Card c){
		controlled.add(c);
		for (int i = 0; i < hand.size(); i++){
			if (hand.get(i) == c){
				hand.remove(i);
				break;
			}
		}
	}
	
	/**
	 * Returns list of controlled minions.
	 * 
	 * @return Controlled minions.
	 */
	public ArrayList<Card> getControlled(){
		return controlled;
	}
	
	/**
	 * Returns list of minions in deck.
	 */
	public Deck getDeck(){
		return deck;
	}
	
	/**
	 * Returns list of minions in hand.
	 * 
	 * @return Minions in hand.
	 */
	public ArrayList<Card> getHand(){
		return hand;
	}
	
	/**
	 * Sets position of player.
	 * 
	 * @param x X value.
	 * @param y Y value.
	 */
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Draws player with text.
	 * 
	 * @param g Graphics
	 */
	public void draw(Graphics g) {
		g.drawImage(image, x, y, width, height, null);
		g.setColor(Color.WHITE);
		g.drawString(name, x+(width/2)-(name.length()*2), y+(height/2)+15);
		g.setColor(Color.WHITE);
		g.drawString(Integer.toString(currentMana) + "/" + Integer.toString(mana) + " MP", x+(width/2)-10, y+(height/2)+65);
		g.setColor(Color.WHITE);
		g.drawString(Integer.toString(health) + " HP", x+(width/2)-10, y+(height/2)+40);
	}

}