package table;



import java.awt.*;

import javax.swing.*;

public class CustomFrame extends JFrame{
	private static final long serialVersionUID = 1L;

	public CustomFrame() {
		super("Projekt tp");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Double width = (double) (0.8 * screenSize.getWidth());
		Double height = (double) (0.5 * screenSize.getWidth());
		setSize(width.intValue(), height.intValue());
		setLocation(100, 50);
		setResizable(false);
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		//Board board=new Board(height.intValue(),width.intValue());


		//add(board);


	}




}