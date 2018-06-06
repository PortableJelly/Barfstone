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

public class DrawDeck {
	int x = 850;
	int y = 400;
	int width = 100;
	int height = 50;
	BufferedImage image = null;
	
	/**
	 * Constructor for making new draw deck button and setting its image.
	 */
	public DrawDeck(){
		try {
			image = ImageIO.read(new File("C:\\Users\\PortableJelly\\Desktop\\Barfstone Art\\Draw Deck.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Draws the button.
	 * 
	 * @param g Graphics
	 */
	public void draw(Graphics g) {
		g.drawImage(image, x, y, width, height, null);
	}
	
	/**
	 * When button is pressed, increases size by 1.2.
	 */
	public void pressed(){
		width = 120;
		height = 60;
		x = 840;
		y = 397;
	}
	
	/**
	 * When button is unpressed, sets size to normal.
	 */
	public void unclick(){
		width = 100;
		height = 50;
		x = 850;
		y = 400;
	}
	
}