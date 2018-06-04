import java.awt.Color;
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
	static TextPrompt t = new TextPrompt();
	static boolean yellow = true;
	BufferedImage image = null;
	
	
	public EndTurn(){
		try {
			image = ImageIO.read(new File("C:\\Users\\PortableJelly\\Desktop\\Barfstone Art\\End Turn.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void draw(Graphics g) {
		if (yellow == true){
			g.setColor(Color.YELLOW);
			}
			else{
				g.setColor(Color.RED);
			}
		g.drawImage(image, x, y, width, height, null);
		//g.fillRect(x, y, width, height);
		//g.setColor(Color.BLACK);
		//g.drawString("End turn.", x+10, y+(height/2));
	}
	
	public void pressed(){
		yellow = false;

	}
	
	public void unclick(){
		yellow = true;
	}
	
	public void click(){
		if (playersTurn == 1){
			playersTurn = 2;
		}
		else{
			playersTurn = 1;
		}
		t.newPrompt("Player " + playersTurn + "'s turn has begun.", "Attention:");
	}
	
	public int getTurn(){
		return playersTurn;
	}
	
}