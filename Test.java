public class Test {

	public static void main(String[] args) {
		Board board = new Board();
		board.setup();
		while(true){
			board.loop();
		}
	}

}