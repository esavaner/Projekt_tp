package server;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

// uruchom serwer, poda ci twoje ip (jednoczesnie ip serwera); uruchom klase main (klient), jesli chcesz dolaczc do serwera musisz podac jego ip(twoje ip ktore wyswietlilo ci w konsoli)
public class ProjectServer {
	//192.168.1.30
	private static String server_IP;
	private static ServerSocket listener;
	private static boolean gameStarted = false;
	private static int playersJoined = 0;
	private static int playersReady = 0;
	private static Game game;
    public static void main(String[] args) throws Exception {
    	listener = new ServerSocket(8901);
        System.out.println("Project Server is Running");


         try {
	        InetAddress iAddress = InetAddress.getLocalHost();
	        server_IP = iAddress.getHostAddress();
	        System.out.println("Server IP address : " +server_IP);
	    } catch (UnknownHostException e) {
	    }
        try {
        	game = new Game();

        	/**while(playersJoined < 6) {
            	Player player = new Player(listener.accept());
            	if(!gameStarted) {
            		game.add(player);
            		playersJoined++;
            		System.out.println("Do��czy� nowy gracz");
            	}
            }*/
            Player player1 = new Player(listener.accept());
            game.add(player1);
            playersJoined++;
            System.out.println("Do��czy� nowy gracz");
            Player player2 = new Player(listener.accept());
            game.add(player2);
            playersJoined++;
            System.out.println("Do��czy� nowy gracz");
            player1.start();
            player2.start();
        } 
        finally {
            listener.close();
        }

    }
    public static void check() {
    	playersReady++;
    	if(playersReady == playersJoined) {
    		System.out.println("Gra zacz�ta");
    		gameStarted = true;
    		game.start();
    	}
    }
}
