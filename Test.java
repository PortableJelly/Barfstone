public class Test {

	public static void main(String[] args) {
		Board board = new Board();
		board.setup("Mike", "Ike");
		while(true){
			board.loop();
		}
	}

}