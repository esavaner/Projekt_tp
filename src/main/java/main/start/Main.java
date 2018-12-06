package main.start;

import table.CustomFrame;

public class Main { 
	private CustomFrame f;
	public static void main(String[] args) {
		new Main();
	}
	public Main() {
		f = new CustomFrame();
		f.setVisible(true);
	}
}