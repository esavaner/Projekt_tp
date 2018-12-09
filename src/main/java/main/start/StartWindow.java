package main.start;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartWindow extends JDialog {
	private static final long serialVersionUID = 1L;
	public JButton startBotGame;
	public JButton joinServer;
	public StartWindow() {
		//gra lokalna z botem
		startBotGame = new JButton("Gra lokalna(z komputerem)");
		startBotGame.setPreferredSize(new Dimension(220, 40));
		startBotGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botGame();
			}
		});
		//dolaczenie do serwera multi(bez botow)
		joinServer = new JButton("Do³¹cz do Serwera");
		joinServer.setPreferredSize(new Dimension(220, 40));
		joinServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getServerIP();
			}
		});
		setLayout(new FlowLayout());
		add(startBotGame);
		add(joinServer);
		setBounds(700,200,270,140);
		setResizable(false);
		setVisible(true);
	}
	public void getServerIP() {
		this.setVisible(false);
		GetIPWindow getIp = new GetIPWindow();
		getIp.setVisible();
	}
	public void botGame() {
		this.setVisible(false);
		BotGameWindow bgwindow = new BotGameWindow();
		bgwindow.setVisible();
	}
}