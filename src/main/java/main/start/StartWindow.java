package main.start;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
Klasa StartWindow odpowiada za okno startowe programu
 */
public class StartWindow extends JDialog {
	private static final long serialVersionUID = 1L;
	private JButton joinServer;
	private JLabel joinLabel;
	public StartWindow() {
		joinLabel = new JLabel("Witaj w Trylmie!");
		joinServer = new JButton("Dolacz do Serwera");
		joinServer.setPreferredSize(new Dimension(220, 40));
		joinServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getServerIP();
			}
		});
		setLayout(new FlowLayout());
		add(joinLabel);
		add(joinServer);
		setBounds(700,200,270,115);
		setResizable(false);
		setVisible(true);
	}
	public void getServerIP() {
		this.setVisible(false);
		GetIPWindow getIp = new GetIPWindow();
		getIp.setVisible();
	}
}