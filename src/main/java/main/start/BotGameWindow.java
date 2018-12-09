package main.start;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;

import table.CustomFrame;

public class BotGameWindow extends JDialog {
	private static final long serialVersionUID = 1L;
	public JButton b2;
	public JButton b3;
	public JButton b4;
	public JButton b6;
	public BotGameWindow() {
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
		b4 = new JButton("4 graczy");
		b4.setPreferredSize(new Dimension(170, 40));
		b4.addActionListener(new ActionListener() {
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
		setLayout(new FlowLayout());
		add(b2);
		add(b3);
		add(b4);
		add(b6);
		this.setBounds(700,200,270,250);
	}
	public void setVisible() {
		this.setVisible(true);
	}
	public void begin() {
		Main.f = new CustomFrame();
		Main.f.setVisible(true);
		this.setVisible(false);
	}
}