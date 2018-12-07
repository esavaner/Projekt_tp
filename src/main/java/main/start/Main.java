package main.start;

import java.io.IOException;
import java.net.Socket;

import table.CustomFrame;
//klasa main
public class Main { 
	public static CustomFrame f;
	private StartWindow sw;
	private static int PORT = 8901;
	private static Socket socket;
	public static void main(String[] args) {
		new Main();
	}
	public Main() {
		sw = new StartWindow();
		sw.setVisible(true);
	}
	public static void joinServer(String ipAdress) throws IOException {
		socket = new Socket(ipAdress, PORT);
	}
}