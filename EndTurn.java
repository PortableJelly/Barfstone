import java.awt.Color;
import java.awt.Graphics;

public class EndTurn {
	int x = 850;
	int y = 400;
	int width = 100;
	int height = 50;
	
	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, width, height);
	}
	
	public void click(){
		System.out.println("Turn ended.");
	}
}
