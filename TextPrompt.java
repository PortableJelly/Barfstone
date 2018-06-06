/**
 * @author Harrison Fah
 * @version 1.0
 * Start Date: 23/5/2018
 * Finish Date: 4/6/2018
 * Copyright 2018, Harrison Fah, All rights reserved.
 */

import javax.swing.JOptionPane;

public class TextPrompt{
	
	/**
	 * Creates new text prompt.
	 * 
	 * @param message Message in prompt.
	 * @param messageType Header for message.
	 */
	public void newPrompt(String message, String messageType){
		String m = message;
		String mT = messageType;
		JOptionPane.showMessageDialog(null, m, mT, JOptionPane.INFORMATION_MESSAGE);
	}
	
}