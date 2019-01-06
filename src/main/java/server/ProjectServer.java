package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class ProjectServer {
	private static String server_IP;
	private static ServerSocket listener;
	private static boolean gameStarted = false;
	private static int playersJoined = 0;
	private static int playersReady = 0;
	private static Game game;
    public static void main(String[] args) throws Exception {
    	try{
    	listener = new ServerSocket(8901);}
    	catch (java.net.BindException e){System.out.println("może działać tylko jeden serwer!"); System.exit(1);}
        System.out.println("Project Server is Running");
        try {
	        InetAddress iAddress = InetAddress.getLocalHost();
	        server_IP = iAddress.getHostAddress();
	        System.out.println("Server IP address : " +server_IP);
	    } catch (UnknownHostException e) {
	    }
        try {
        	game = new Game();
        	while(playersJoined < 6) {
            	Player player = new Player(listener.accept());
            	if(!gameStarted) {
            		game.add(player);
            		for(int i=0; i<playersJoined; i++) {
            			player.addPlayer(i);
            		}
            		for(Player p : Game.players) {
            			p.addPlayer(playersJoined);
                        Game.addPlayer(playersJoined);
            		}
            		System.out.println("Dolaczyl nowy gracz");
            		player.start();
            		player.starting(playersJoined);
            		playersJoined++;
            	}
            }
        } 
        finally {
            listener.close();
        }

    }
    public static void check() throws IOException, Exception {
    	playersReady++;
    	if(playersReady == playersJoined) {
    		if(playersReady == 1 || playersReady == 5) {
    				Bot bot = new Bot();
    				game.add(bot);
    				for(int i=0; i<playersJoined; i++) {
    					bot.addPlayer(i);
        			}
    				for(Player p : Game.players) {
        				p.addPlayer(playersJoined);
        				Game.addPlayer(playersJoined);
        			}
        			System.out.println("Dolaczyl nowy gracz");
            		bot.starting(playersJoined);
            		bot.start();
            		playersJoined++;
    		}
    		gameStarted = true;
    		game.start();
    	}
    }
}
