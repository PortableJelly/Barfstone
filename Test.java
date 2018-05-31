import java.awt.PopupMenu;

import javax.swing.JFrame;

public class Test {

	public static void main(String[] args) {
		Board board = new Board();
		board.setup("Mike", "Ike");
		while(board.getPlayer1().getHealth() > 0 && board.getPlayer2().getHealth() > 0){
			board.loop();
		}
		System.out.println("Game ended.");
		System.exit(0);
	}

}