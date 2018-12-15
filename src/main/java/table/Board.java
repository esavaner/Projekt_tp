package table;

import main.start.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Board extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static JLabel messageLabel;
	private Client client = null;
    public static boolean blocked = true;



    public Board(int height, int width){









        final JButton ready = new JButton("Gotowo��");
        final JDialog message = new JDialog();
        message.setBounds(700,200,300,100);
        messageLabel = new JLabel("Witaj w grze, oczekiwane na graczy");
        messageLabel.setSize(280,70);
        message.setLayout(new FlowLayout())	;
        message.add(messageLabel);
        message.add(ready);
        ready.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				message.remove(ready);
				messageLabel.setText("Oczekiwanie na gotowo�� innych graczy");
				message.repaint();
				try {
					client = Client.getClient();
				} catch (Exception e1) {
				}
				client.getOut().println("READY");
			}
		});
        message.setVisible(true);

    }


    public static void update(int oldX, int oldY, int newX, int newY) {
    	
    }
    /** */

}
