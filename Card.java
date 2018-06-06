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

import javax.imageio.ImageIO;

public class Card {
	String name;
	int mana;
	int attack;
	int health;
	int x;
	int y;
	int width = 75;
	int height = 100;
	boolean canAttack = false;
	boolean clicked = false;
	BufferedImage image = null;
	
	/**
	 * Constructor for creating new card.
	 */
	public Card(){
		
	}
	
	/**
	 * Constructor for creating a card from a string array of data.
	 * 
	 * @param a String array of data.
	 */
	public Card(String[] a) {
		name = a[0];
		mana = Integer.parseInt(a[1]);
		attack = Integer.parseInt(a[2]);
		health = Integer.parseInt(a[3]);
		try {
			image = ImageIO.read(new File("C:\\Users\\PortableJelly\\Desktop\\Barfstone Art\\" + name + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Changes the position of the card.
	 * 
	 * @param x X value
	 * @param y Y value
	 */
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Deals damage to the card.
	 * 
	 * @param damage Damage dealt
	 */
	public void takeDamage(int damage) {
		health = health - damage;
	}
	
	/**
	 * When card is clicked, increase size by 1.2.
	 */
	public void click() {
		clicked = true;
		width = 90;
		height = 120;
	}
	
	/** 
	 * When card is no longer clicked, set size back to default.
	 */
	public void unclick() {
		clicked = false;
		width = 75;
		height = 100;
	}
	
	/**
	 * Returns the x position.
	 * 
	 * @return X position
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Returns the y position
	 * 
	 * @return Y position
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Returns width of card.
	 * 
	 * @return Width
	 */
	public int getWidth(){
		return width;
	}
	
	/** 
	 * Returns height of card
	 * 
	 * @return Height
	 */ 
	public int getHeight(){
		return height;
	}
	
	/**
	 * Returns the name of card.
	 *  
	 * @return Name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the mana of card.
	 * 
	 * @return Mana
	 */
	public int getMana() {
		return mana;
	}

	/**
	 * Returns attack of card.
	 * 
	 * @return Attack
	 */
	public int getAttack() {
		return attack;
	}

	/**
	 * Returns health of card.
	 * 
	 * @return Health
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Returns the state of the canAttack boolean.
	 * 
	 * @return canAttack
	 */
	public boolean getCanAttack(){
		return canAttack;
	}
	
	/**
	 * Changes whether a card can attack or not.
	 * 
	 * @param c True or false for card being able to attack.
	 */
	public void changeCanAttack(boolean c){
		canAttack = c;
	}
	
	/**
	 * Method for drawing the image of card with text on it.
	 * 
	 * @param g Graphics
	 */
	public void draw(Graphics g) {
		g.drawImage(image, x, y, width, height, null);
		g.setColor(Color.WHITE);
		g.drawString(name, x+(width/2)-(name.length()*2), y+(height/2)+15);
		g.drawString(attack + "/" + health, x+(width/2)-(name.length()*2), y+(height/2)+30);
		g.drawString(mana + " MP", x+(width/2)-(name.length()*2), y+(height/2)+45);
	}
}