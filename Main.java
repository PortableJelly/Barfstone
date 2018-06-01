public class Main {

	public static void main(String[] args) {
		TextPrompt t = new TextPrompt();
		Board board = new Board();
		board.setup("Mike", "Ike");
		while (board.getPlayer1().getHealth() > 0 && board.getPlayer2().getHealth() > 0 && board.getTie() == false) {
			board.loop();
		}
		if (board.getPlayer1().getHealth() < 1) {
			t.newPrompt(board.getPlayer2().getName() + " wins.", "Attention:");
		}
		if (board.getPlayer2().getHealth() < 1) {
			t.newPrompt(board.getPlayer1().getName() + " wins.", "Attention:");
		}
		System.exit(0);
	}

}