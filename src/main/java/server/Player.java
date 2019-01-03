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
    		System.out.println("Player died: "+ e);
    	}
    	finally {
    		try {socket.close();} catch (IOException e1) {}
    	}
    }
    public void addPlayer(int num) {
    	output.println("ADD" + num);
    }
    public void yourMove() {
    	output.println("YOUR MOVE");
    }
    public void otherMove(int current) {
    	output.println("RUCH " + current);
    }
    public void update(String command) {
    	output.println(command);
    }
    public void starting(){
        output.println("STARTING");
    }
    public String getIn() {
    	String command;
    	try {
    		command = input.readLine();
    		if(command != null) {
    			return command;
    		}
    		else {
    			return null;
    		}
    	}
    	catch (IOException e1) {
    		return null;
    	}
    }
}