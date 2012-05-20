package main;

import view.FEFrame;

public class StartGame {

	public static final String SEPARATOR = System.getProperty("path.separator");
	
	// args are unused
	public static void main(String[] args) {
		new FEFrame();
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		FEFrame.showTextbox();
	}
	
}
