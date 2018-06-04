import java.awt.Color;
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
	static boolean purple = true;
	BufferedImage image = null;
	
	public DrawDeck(){
		try {
			image = ImageIO.read(new File("H:\\Comp Sci 30\\Final Project\\Art\\Draw Deck.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void draw(Graphics g) {
		if (purple == true){
			g.setColor(Color.magenta);
			}
			else{
				g.setColor(Color.RED);
			}
		g.drawImage(image, x, y, width, height, null);
		//g.fillRect(x, y, width, height);
		//g.setColor(Color.WHITE);
		//g.drawString("Draw from Deck", x+10, y+(height/2));
		//g.drawString("(5 Mana)", x+15, y+(height/2)+10);
	}
	
	public void pressed(){
		width = 120;
		height = 60;
		x = 840;
		y = 397;
		purple = false;
	}
	
	public void unclick(){
		width = 100;
		height = 50;
		x = 850;
		y = 400;
		purple = true;
	}
	
	public void click(){
		System.out.println("Drawed from deck");
	}
	
}