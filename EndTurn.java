import java.awt.Color;
import java.awt.Graphics;

public class EndTurn {
	int x = 850;
	int y = 300;
	int width = 100;
	int height = 50;
	int playersTurn = 1;
	static boolean yellow = true;
	
	public void draw(Graphics g) {
		if (yellow == true){
			g.setColor(Color.YELLOW);
			}
			else{
				g.setColor(Color.RED);
			}
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawString("End turn.", x+10, y+(height/2));
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
		System.out.println("Turn ended. Players turn: " + playersTurn);
	}
	
	public int getTurn(){
		return playersTurn;
	}
	
}