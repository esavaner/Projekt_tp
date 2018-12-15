package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player extends Thread{
	Socket socket;
    BufferedReader input;
    PrintWriter output;
    public Player(Socket socket) {
    	this.socket = socket;
        try {
        	input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            output.println("WELCOME");
        }
        catch (IOException e) {
        	System.out.println("Player died: " + e);
        }
    }
    public void run() {System.out.println("started!");
    	try {
    		while(true) {
    	   		String command = input.readLine();
    	   		if(command.startsWith("READY")) {
    	   			System.out.println("checked");
    	   			ProjectServer.check();
    	   		}
    		}
    	}
    	catch(IOException e) {
    		System.out.println("Player died: "+ e);
    	}
    	finally {
    		try {socket.close();} catch (IOException e1) {}
    	}
    }
    public String yourMove() {
    	output.println("YOUR MOVE");
    	try {
    		return input.readLine();
    	}
    	catch (IOException e1) {
    		return null;
    	}
    }
    public void otherMove(int current) {
    	output.println("RUCH " + current);
    }
    public void update(int oldX, int oldY, int newX, int newY) {
    	output.println("UPDATE " + oldX + oldY + newX + newY);
    }

    public void starting(){
        System.out.println("player zrozumial, przekazuje dalej");
        output.println("STARTING");
    }
}