import java.awt.Color;
import java.awt.Graphics;

public class DrawDeck {
	int x = 850;
	int y = 400;
	int width = 100;
	int height = 50;
	static boolean purple = true;
	
	public void draw(Graphics g) {
		if (purple == true){
			g.setColor(Color.magenta);
			}
			else{
				g.setColor(Color.RED);
			}
		g.fillRect(x, y, width, height);
		g.setColor(Color.WHITE);
		g.drawString("Draw from Deck", x+10, y+(height/2));
		g.drawString("(5 Mana)", x+15, y+(height/2)+10);
	}
	
	public void pressed(){
		purple = false;
	}
	
	public void unclick(){
		purple = true;
	}
	
	public void click(){
		System.out.println("Drawed from deck");
	}
	
}