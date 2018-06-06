/**
 * @author Harrison Fah
 * @version 1.0
 * Start Date: 23/5/2018
 * Finish Date: 4/6/2018
 * Copyright 2018, Harrison Fah, All rights reserved.
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EndTurn {
	int x = 850;
	int y = 300;
	int width = 100;
	int height = 50;
	int playersTurn = 1;
	static boolean yellow = true;
	BufferedImage image = null;
	
	/**
	 * Constructor for when new end turn button is created.
	 */
	public EndTurn(){
		try {
			image = ImageIO.read(new File("C:\\Users\\PortableJelly\\Desktop\\Barfstone Art\\End Turn.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method for drawing button.
	 * 
	 * @param g Graphics
	 */
	public void draw(Graphics g) {
		g.drawImage(image, x, y, width, height, null);
	}
	
	/**
	 * When button is pressed, increase size by 1.2.
	 */
	public void pressed(){
		width = 120;
		height = 60;
		x = 840;
		y = 297;

	}
	
	/**
	 * When button is not pressed return size to normal.
	 */
	public void unclick(){
		width = 100;
		height = 50;
		x = 850;
		y = 300;
		yellow = true;
	}
	
	/**
	 * When button is clicked, change player's turn.
	 */
	public void click(){
		if (playersTurn == 1){
			playersTurn = 2;
		}
		else{
			playersTurn = 1;
		}
	}
	
	/** 
	 * Returns the current player's turn.
	 * 
	 * @return Player's turn
	 */
	public int getTurn(){
		return playersTurn;
	}
	
}