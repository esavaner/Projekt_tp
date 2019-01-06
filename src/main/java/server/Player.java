package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * klasa jest reprezentacja gracza na serwerze,
 * odpowiada za komunikacje miedzy Clientem a Game, czyli graczem a rozgrywka
 */
public class Player extends Thread{
	private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    public Player() {
    }
    public Player(Socket socket) {
    	this.socket = socket;
        try {
        	input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
        }
        catch (IOException e) {
        	System.out.println("Player died: " + e);
        }
    }
    public void run() {
    	try {
    		while(true) {
    	   		String command = input.readLine();
    	   		if(command.startsWith("READY")) {
    	   			ProjectServer.check();
    	   		}
    	   		else if(command.startsWith("MOVE")) {
    	   			Game.update(command);
    	   		}
    		}
    	}
    	catch(IOException e) {
    		System.out.println("Player died: "+ e); ProjectServer.kill();
    	} catch (Exception e) {
			e.printStackTrace();

		}
    	finally {
    		try {socket.close();} catch (IOException e1) {}
    	}
    }
    public void addPlayer(int num) {
    	output.println("ADD" + num);
    }
    public void yourMove() {
    	output.println("YOUR");
    }
    public void otherMove(int current) {
    	output.println("RUCH " + current);
    }
    public void update(String command) {
    	output.println(command);
    }
    public void starting(int playerNumber){
        output.println("START" + playerNumber);
    }
    public void otherWon(int playerNumber){output.println("LOST" + playerNumber);}
    public void youWon(){output.println("WINNER");}
    public void kill(){
        output.println("KILL");}
}