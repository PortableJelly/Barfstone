import javax.swing.JOptionPane;

public class TextPrompt{
	
	public void newPrompt(String message, String messageType){
		String m = message;
		String mT = messageType;
		JOptionPane.showMessageDialog(null, m, mT, JOptionPane.INFORMATION_MESSAGE);
	}
	
}