package server;

import java.net.Socket;

public class Player extends Thread {
	private Socket socket;
	public Player(Socket socket) {
		this.socket = socket;
	}
	public void doMove() {
		
	}
}