package main;

import view.FEFrame;

public class StartGame {

	// used in various places where files are lodaed. Couldn't really think of a
	// better place for it atm, could be moved.
	public static final String SEPARATOR = System.getProperty("file.separator");

	// args are unused
	public static void main(String[] args) {
		new FEFrame();

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		FEFrame.showTextbox("This textbox totally works with long messages too! Try it if you want! You push a key to advance the textbox. It breaks up your message automatically and everything!");
	}

}
