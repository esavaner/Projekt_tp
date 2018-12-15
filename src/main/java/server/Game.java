package server;

import java.util.ArrayList;
import java.util.List;

public class Game extends Thread {
	private Player currentPlayer;
	private int current = 0;
	public static List<Player> players = new ArrayList<Player>();
	public void run() {
		/*
		System.out.println("Game started");
		for(Player p : players){
			p.starting();
		}*/
		while(true) {
			currentPlayer = players.get(current);
			for(Player p : players) {
				if(p == currentPlayer) {
					p.yourMove();
				}
				else {
					p.otherMove(current + 1);
				}
			}
			String command = currentPlayer.getIn();
			if(command == null) {
				System.out.println("null");
			}
			//pomimo przekazania move podczas ruchu gracza, serwer jej nie czyta
			if(command.startsWith("MOVE")) {
				for(Player p : players) {
					if(p != currentPlayer) {
						p.update();
					}
				}
			}
			current = (current + 1)%players.size();
		}
	}
	public void add(Player player) {
		players.add(player);
	}
}