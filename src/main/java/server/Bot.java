package server;

import java.net.Socket;

public class Bot extends Player {
	private Socket socket;
	public Bot(Socket socket) {
		super(socket);
		this.socket = socket;
	}

	@Override
	public void doMove() {
		
	}
}