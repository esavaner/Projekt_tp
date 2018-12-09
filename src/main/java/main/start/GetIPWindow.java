package main.start;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import table.CustomFrame;

public class GetIPWindow extends JDialog {
	private static final long serialVersionUID = 1L;
	final JTextField joinText;
	JLabel joinLabel;
	public GetIPWindow() {
		setLayout(new FlowLayout());
		joinText = new JTextField();
		joinText.setPreferredSize(new Dimension(170, 40));
		this.add(joinText);
		JButton joinButton = new JButton("Do³¹cz");
		joinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				join();
			}
		});
		this.add(joinButton);
		joinLabel = new JLabel("Podaj IP serwera");
		this.add(joinLabel);
		this.setBounds(700,200,270,120);
	}
	public void setVisible() {
		this.setVisible(true);
	}
	public void join() {
		try {
			Main.joinServer(joinText.getText());
			setVisible(false);
			begin();
		} 
		catch (IOException e1) {
			joinText.setText("");
			joinLabel.setText("Z³y adres IP serwera");
		}
	}
	public void begin() {
		Main.f = new CustomFrame();
		Main.f.setVisible(true);
		this.setVisible(false);
	}
}