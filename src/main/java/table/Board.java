package table;

import main.start.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// TODO: 11.12.2018
//na ten moment moÅ¼na jedynie wykonywaÄ‡ dowolne ruchy pionkami po planszy

public class Board extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static boolean blocked = true;
	public static JLabel messageLabel;
	private Client client = null;
    Field[] pola;
    Field pole;
    JPanel[][] panelHolder;

    boolean moving=true;
    boolean selected=false;

    Color defaultColor=Color.WHITE;
    Color temp;


    public Board(int height, int width){
        int i = 25;
        int j = 18;
        panelHolder = new JPanel[i][j];
        pola=new Field[122];//121 pÃ³l, numeracja od 1 bo pole nr 0 dziwnie wyglÄ…da
        setLayout(new GridLayout(j,i,0,0));


        for(int m = 0; m < j; m++) {
            for(int n = 0; n < i; n++) {
                panelHolder[n][m] = new JPanel();
                panelHolder[n][m].setLayout(new FlowLayout());
                add(panelHolder[n][m]);
            }
        }
        setPreferredSize(new Dimension(height,height));
        int counter=1;
        int row=0;

        pole=new Field(defaultColor, counter); pole.addActionListener(this); panelHolder[12][row].add(pole); pola[counter]=pole; counter++;
        row++;

        pole=new Field(defaultColor, counter); pole.addActionListener(this); panelHolder[11][row].add(pole);  pola[counter]=pole; counter++;
        pole=new Field(defaultColor, counter); pole.addActionListener(this); panelHolder[13][row].add(pole);  pola[counter]=pole; counter++;
        row++;

        pole=new Field(defaultColor, counter); pole.addActionListener(this); panelHolder[10][row].add(pole);  pola[counter]=pole; counter++;
        pole=new Field(defaultColor, counter); pole.addActionListener(this); panelHolder[12][row].add(pole);  pola[counter]=pole; counter++;
        pole=new Field(defaultColor, counter); pole.addActionListener(this); panelHolder[14][row].add(pole);  pola[counter]=pole; counter++;
        row++;

        for(int z=9; z<=15; z=z+2){pole=new Field(defaultColor, counter); pole.addActionListener(this); panelHolder[z][row].add(pole);  pola[counter]=pole; counter++;}
        row++;

        for(int z=0; z<=24; z=z+2){pole=new Field(defaultColor, counter); pole.addActionListener(this); panelHolder[z][row].add(pole);  pola[counter]=pole; counter++;}
        row++;

        for(int z=1; z<=23; z=z+2){pole=new Field(defaultColor, counter); pole.addActionListener(this); panelHolder[z][row].add(pole);  pola[counter]=pole; counter++;}
        row++;

        for(int z=2; z<=22; z=z+2){pole=new Field(defaultColor, counter); pole.addActionListener(this); panelHolder[z][row].add(pole);  pola[counter]=pole; counter++;}
        row++;

        for(int z=3; z<=21; z=z+2){pole=new Field(defaultColor, counter); pole.addActionListener(this); panelHolder[z][row].add(pole);  pola[counter]=pole; counter++;}
        row++;

        for(int z=4; z<=20; z=z+2){pole=new Field(defaultColor, counter); pole.addActionListener(this); panelHolder[z][row].add(pole);  pola[counter]=pole; counter++;}
        row++;

        for(int z=3; z<=21; z=z+2){pole=new Field(defaultColor, counter); pole.addActionListener(this); panelHolder[z][row].add(pole);  pola[counter]=pole; counter++;}
        row++;

        for(int z=2; z<=22; z=z+2){pole=new Field(defaultColor, counter); pole.addActionListener(this); panelHolder[z][row].add(pole);  pola[counter]=pole; counter++;}
        row++;

        for(int z=1; z<=23; z=z+2){pole=new Field(defaultColor, counter); pole.addActionListener(this); panelHolder[z][row].add(pole);  pola[counter]=pole; counter++;}
        row++;

        for(int z=0; z<=24; z=z+2){pole=new Field(defaultColor, counter); pole.addActionListener(this); panelHolder[z][row].add(pole);  pola[counter]=pole; counter++;}
        row++;

        for(int z=9; z<=15; z=z+2){pole=new Field(defaultColor, counter); pole.addActionListener(this); panelHolder[z][row].add(pole); pola[counter]=pole;  counter++;}
        row++;

        pole=new Field(defaultColor, counter); pole.addActionListener(this); panelHolder[10][row].add(pole);  pola[counter]=pole; counter++;
        pole=new Field(defaultColor, counter); pole.addActionListener(this); panelHolder[12][row].add(pole);  pola[counter]=pole; counter++;
        pole=new Field(defaultColor, counter); pole.addActionListener(this); panelHolder[14][row].add(pole);  pola[counter]=pole; counter++;
        row++;

        pole=new Field(defaultColor, counter); pole.addActionListener(this); panelHolder[11][row].add(pole);  pola[counter]=pole; counter++;
        pole=new Field(defaultColor, counter); pole.addActionListener(this); panelHolder[13][row].add(pole); pola[counter]=pole; counter++;
        row++;

        pole=new Field(defaultColor, counter); pole.addActionListener(this); panelHolder[12][row].add(pole); pola[counter]=pole; counter++;







        addPlayer1();
        addPlayer4();

        final JButton ready = new JButton("Gotowoœæ");
        final JDialog message = new JDialog();
        message.setBounds(1500,200,300,100);
        messageLabel = new JLabel("Witaj w grze, oczekiwane na graczy");
        messageLabel.setSize(280,70);
        message.setLayout(new FlowLayout())	;
        message.add(messageLabel);
        message.add(ready);
        ready.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				message.remove(ready);
				messageLabel.setText("Oczekiwanie na gotowoœæ innych graczy");
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

    public void actionPerformed(ActionEvent e) {

        Object obj = e.getSource();
        if(!blocked) {
        if (obj instanceof Field) {
        	Field cb = (Field)obj;


        	if(moving){

            if(selected && !cb.isOccupied()){
                selected=false;
                cb.FieldColor=temp;
                pole.FieldColor=defaultColor; pole.setEmpty(); cb.setOccupied();
                cb.repaint(); pole.repaint();
                client.move(pole.getX(), pole.getY(), cb.getX(), cb.getY());
                blocked = true;

            }

        	    else if(!selected && cb.isOccupied()){
        	        temp=cb.FieldColor;
        	        pole=cb;
        	        cb.FieldColor=Color.cyan;
        	        cb.repaint();
        	         selected=true;}

        	}

        }
        }
    }
    public static void update(int oldX, int oldY, int newX, int newY) {
    	
    }
    public void addPlayer1(){
        for (int z=1; z<=10; z++){pola[z].FieldColor=Color.RED; pola[z].setOccupied(); pola[z].repaint();}

    }

    public void addPlayer4() {
        for (int z=112; z<=121; z++){pola[z].FieldColor=Color.YELLOW; pola[z].setOccupied(); pola[z].repaint();}

    }

}
