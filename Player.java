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
	
	public Player(String name, int i, String imageName){
		this.name = name;
		mana = i;
		currentMana = i;
		try {
			image = ImageIO.read(new File("C:\\Users\\PortableJelly\\Desktop\\Barfstone Art\\" + imageName + ".png"));
			//image = ImageIO.read(new File("C:\\Users\\PortableJelly\\Desktop\\" + name + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getName() {
		return name;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getHealth() {
		return health;
	}
	
	public int getMana() {
		return mana;
	}
	public int getCurrentMana() {
		return currentMana;
	}
	
	public void changeCurrentMana(int remove){
		currentMana = currentMana - remove;
	}
	
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
	
	public void takeDamage(int damage){
		health = health - damage;
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
		g.drawImage(image, x, y, width, height, null);
		//g.setColor(Color.GREEN);
		//g.fillRect(x, y, width, height);
		g.setColor(Color.WHITE);
		g.drawString(name, x+(width/2)-(name.length()*2), y+(height/2)+15);
		g.setColor(Color.WHITE);
		g.drawString(Integer.toString(currentMana) + "/" + Integer.toString(mana) + " MP", x+(width/2)-10, y+(height/2)+65);
		g.setColor(Color.WHITE);
		g.drawString(Integer.toString(health) + " HP", x+(width/2)-10, y+(height/2)+40);
	}

}