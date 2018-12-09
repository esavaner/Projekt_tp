package server;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

// uruchom serwer, poda ci twoje ip (jednoczesnie ip serwera); uruchom klase main (klient), jesli chcesz dolaczc do serwera musisz podac jego ip(twoje ip ktore wyswietlilo ci w konsoli)
public class ProjectServer {
	private static String server_IP;
    public static void main(String[] args) throws Exception {
    	ServerSocket listener = new ServerSocket(8901);
        System.out.println("Project Server is Running");
        try {
	        InetAddress iAddress = InetAddress.getLocalHost();
	        server_IP = iAddress.getHostAddress();
	        System.out.println("Server IP address : " +server_IP);
	    } catch (UnknownHostException e) {
	    }
        try {
            while (true) {
            	Player player = new Player(listener.accept());
            	System.out.println("Do³¹czy³ nowy gracz");
            }
        } 
        finally {
            listener.close();
        }
    }
}
