package main.start;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GetIPWindow extends JDialog {
	private static final long serialVersionUID = 1L;
	private String ipAddress;
	final JTextField joinText;
	JLabel joinLabel;

	/**
	 * klasa GetIPWindow odpowiada za polaczenie gracza z serwerem oraz uruchomienie okienka z grą(kl. Client)
	 */
	public GetIPWindow() {
		setLayout(new FlowLayout());
		joinText = new JTextField();
		joinText.setPreferredSize(new Dimension(170, 40));
		this.add(joinText);
		joinLabel = new JLabel("Podaj IP serwera");
		JButton joinButton = new JButton("Dolacz");
		joinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					join();
				} catch (Exception e1) {
					joinLabel.setText("Zly adres serwera, podaj jeszcze raz");
				}
			}
		});
		this.add(joinButton);
		this.add(joinLabel);
		this.setBounds(700,200,270,120);
	}
	public void setVisible() {
		this.setVisible(true);
	}
	public void join() throws Exception {
		setIp();
		Client.ipAddress = getIp();
		Client client = Client.getClient();
		this.setVisible(false);
	}
	public void setIp() {
		ipAddress = joinText.getText();
	}
	public String getIp() {
		return ipAddress.replaceAll("\\s", "");
	}
}