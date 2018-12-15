package server;

import java.util.ArrayList;
import java.util.List;

public class Game extends Thread {
	private Player currentPlayer;
	private int current = 0;
	public static List<Player> players = new ArrayList<Player>();
	public void run() {
		System.out.println("Game started");
		for(Player p : players){
			p.starting();
		}
		while(true) {
			currentPlayer = players.get(current);
			String command = null;
			for(Player p : players) {
				if(p == currentPlayer) {
					command = p.yourMove();
				}
				else {
					p.otherMove(current + 1);
				}
			}
			if(command.startsWith("MOVE")) {
				for(Player p : players) {
					if(p != currentPlayer) {
						p.update(command.charAt(5), command.charAt(6), command.charAt(7), command.charAt(8));
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