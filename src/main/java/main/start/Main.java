package main.start;

import table.CustomFrame;
/**
Klasa Main odpowiada za uruchomienie okna startowego Clienta
 */
public class Main { 
	public static CustomFrame f;
	private StartWindow sw;
	public static void main(String[] args) {
		new Main();
	}
	//okno startowe
	public Main() {
		sw = new StartWindow();
		sw.setVisible(true);
	}
}