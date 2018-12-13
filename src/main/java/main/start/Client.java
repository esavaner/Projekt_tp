package main.start;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import table.Board;
import table.CustomFrame;

public class Client {
	private volatile static Client instance = null;
	public static String ipAddress = null;
	private Socket socket;
	private BufferedReader in;
    private PrintWriter out;
	public Client() throws Exception {
		socket = new Socket(ipAddress, 8901);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
		Main.f = new CustomFrame();
		Main.f.setVisible(true);
		wait(1);
		play();
	}
	public static Client getClient() throws Exception {
		if(instance == null) {
			synchronized(Client.class) {
				if(instance == null) {
					instance = new Client();
				}
			}
		}
		return instance;
	}
	public void play() throws Exception {
		String response;
		try {
			while(true) {
				response = in.readLine();
				if(response.startsWith("YOUR")) {
					Board.blocked = false;
					Board.messageLabel.setText("Twój ruch");
					Board.messageLabel.repaint();
				}
				else if(response.startsWith("WELCOME")) {
					System.out.println("welcome");
				}
				else if(response.startsWith("UPDATE")) {
					Board.update(response.charAt(7), response.charAt(8) ,response.charAt(9), response.charAt(10));
				}
				else if(response.startsWith("SHUTDOWN")) {
					break;
				}
				else if(response.startsWith("RUCH")) {
					Board.messageLabel.setText("Ruch gracza " + response.charAt(5));
				}
			}
			out.println("QUIT");
		}
		finally {
			socket.close();
		}
	}
	public Socket getSocket() {
		return this.socket;
	}
	public void move(int oldX, int oldY, int newX, int newY) {
		out.println("MOVE " + oldX + oldY + newX + newY );
	}
	public PrintWriter getOut() {
		return this.out;
	}
}