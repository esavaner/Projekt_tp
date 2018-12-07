package main.start;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import table.CustomFrame;
public class StartWindow extends JDialog {
	private static final long serialVersionUID = 1L;
	public JButton b2;
	public JButton b3;
	public JButton b6;
	public JButton createServer;
	public JButton joinServer;
	public JDialog joinDialog;
	public StartWindow() {
		b2 = new JButton("2 graczy");
		b2.setPreferredSize(new Dimension(170, 40));
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				begin();
			}
		});
		b3 = new JButton("3 graczy");
		b3.setPreferredSize(new Dimension(170, 40));
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				begin();
			}
		});
		b6 = new JButton("6 graczy");
		b6.setPreferredSize(new Dimension(170, 40));
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				begin();
			}
		});
		createServer = new JButton("Stwórz Serwer");
		createServer.setPreferredSize(new Dimension(170, 40));
		createServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		joinServer = new JButton("Do³¹cz do Serwera");
		joinServer.setPreferredSize(new Dimension(170, 40));
		joinServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getServerIP();
			}
		});
		setLayout(new FlowLayout());
		add(b2);
		add(b3);
		add(b6);
		add(createServer);
		add(joinServer);
		setBounds(700,200,280,280);
		setResizable(false);
		setVisible(true);
	}
	public void begin() {
		Main.f = new CustomFrame();
		Main.f.setVisible(true);
		this.setVisible(false);
	}
	public void getServerIP() {
		this.setVisible(false);
		joinDialog = new JDialog();
		joinDialog.setLayout(new FlowLayout());
		final JTextField joinText = new JTextField();
		joinText.setPreferredSize(new Dimension(170, 40));
		joinDialog.add(joinText);
		JButton joinButton = new JButton("Do³¹cz");
		joinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Main.joinServer(joinText.getText());
				} 
				catch (IOException e1) {
					System.out.println("Z³y adres IP serwera");
				}
				joinDialog.setVisible(false);
			}
		});
		joinDialog.add(joinButton);
		JLabel joinLabel = new JLabel("Podaj IP serwera");
		joinDialog.add(joinLabel);
		joinDialog.setBounds(700,200,270,120);
		joinDialog.setVisible(true);
	}
}