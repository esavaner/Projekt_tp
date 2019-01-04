package server;

import java.util.ArrayList;
import java.util.List;

public class Game extends Thread {
	private static Player currentPlayer;
	private static boolean waiting = false;
	private int current = 0;
	public static List<Player> players = new ArrayList<Player>();
	public void run() {
		while(true) {
			System.out.println();
			if(waiting == false) {
				currentPlayer = players.get(current);
				for(Player p : players) {
					if(p == currentPlayer) {
						p.yourMove(current);
					}
					else {
						p.otherMove(current + 1);
					}
				}
				current = (current + 1)%players.size();
				waiting = true;
			}
		}
	}
	public void add(Player player) {
		players.add(player);
	}
	
	public static void update(String command) {
		for(Player p : players) {
			if(p != currentPlayer) {
				p.update(command);
			}
		}
		waiting = false;
	}
}