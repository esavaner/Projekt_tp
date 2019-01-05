package main.start;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import table.CustomFrame;
import table.Field;

import javax.swing.*;

public class Client extends Thread implements ActionListener {
	public static JLabel messageLabel;
	private volatile static Client instance = null;
	public static String ipAddress = null;
	private Socket socket;
	private BufferedReader in;
    private static PrintWriter out;
    private boolean blocked = true;
    private String[] update;
	private Field[] pola;
	private Field pole;
	private Field tmpField;
	private Field jumpStart;
	private Field jumpEnd;
	private JPanel[][] panelHolder;
	private JPanel panelglowny;
	private JPanel message;
	private JButton endTurn;
	private boolean jumped=false;
	private CustomFrame f;
	boolean moving=true;
	boolean selected=false;
	private Color defaultColor=Color.WHITE;
	private Color temp;
	private String colorName[] = {"zoltymi", "czerwonymi", "niebieskimi", "zielonymi", "pomaranczowymi", "czarnymi"};
	private Color colorTable[] = {Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.BLACK};
	private Color playerColor;
	private int playerNumber;
	
	
	public Client() throws Exception {
		socket = new Socket(ipAddress, 8901);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        f=new CustomFrame();
        int i = 25;
        int j = 18;
        panelHolder = new JPanel[i][j];
        pola=new Field[122];

        panelglowny=new JPanel();

        panelglowny.setLayout(new GridLayout(j,i,0,0));


        for(int m = 0; m < j; m++) {
            for(int n = 0; n < i; n++) {
                panelHolder[n][m] = new JPanel();
                panelHolder[n][m].setLayout(new FlowLayout());
                panelglowny.add(panelHolder[n][m]);
            }
        }



        panelglowny.setPreferredSize(new Dimension((int)(f.getHeight()*0.8), (int)(f.getHeight()*0.9) ));
        int counter=1;
        int row=0;

        pole=new Field(defaultColor, counter, 12, row); pole.addActionListener(this); panelHolder[12][row].add(pole); pola[counter]=pole; counter++;
        row++;

        pole=new Field(defaultColor, counter, 11, row); pole.addActionListener(this); panelHolder[11][row].add(pole);  pola[counter]=pole; counter++;
        pole=new Field(defaultColor, counter, 13, row); pole.addActionListener(this); panelHolder[13][row].add(pole);  pola[counter]=pole; counter++;
        row++;

        pole=new Field(defaultColor, counter, 10, row); pole.addActionListener(this); panelHolder[10][row].add(pole);  pola[counter]=pole; counter++;
        pole=new Field(defaultColor, counter, 12, row); pole.addActionListener(this); panelHolder[12][row].add(pole);  pola[counter]=pole; counter++;
        pole=new Field(defaultColor, counter, 14, row); pole.addActionListener(this); panelHolder[14][row].add(pole);  pola[counter]=pole; counter++;
        row++;

        for(int z=9; z<=15; z=z+2){pole=new Field(defaultColor, counter, z, row); pole.addActionListener(this); panelHolder[z][row].add(pole);  pola[counter]=pole; counter++;}
        row++;

        for(int z=0; z<=24; z=z+2){pole=new Field(defaultColor, counter, z, row); pole.addActionListener(this); panelHolder[z][row].add(pole);  pola[counter]=pole; counter++;}
        row++;

        for(int z=1; z<=23; z=z+2){pole=new Field(defaultColor, counter, z, row); pole.addActionListener(this); panelHolder[z][row].add(pole);  pola[counter]=pole; counter++;}
        row++;

        for(int z=2; z<=22; z=z+2){pole=new Field(defaultColor, counter, z, row); pole.addActionListener(this); panelHolder[z][row].add(pole);  pola[counter]=pole; counter++;}
        row++;

        for(int z=3; z<=21; z=z+2){pole=new Field(defaultColor, counter, z, row); pole.addActionListener(this); panelHolder[z][row].add(pole);  pola[counter]=pole; counter++;}
        row++;

        for(int z=4; z<=20; z=z+2){pole=new Field(defaultColor, counter, z, row); pole.addActionListener(this); panelHolder[z][row].add(pole);  pola[counter]=pole; counter++;}
        row++;

        for(int z=3; z<=21; z=z+2){pole=new Field(defaultColor, counter, z, row); pole.addActionListener(this); panelHolder[z][row].add(pole);  pola[counter]=pole; counter++;}
        row++;

        for(int z=2; z<=22; z=z+2){pole=new Field(defaultColor, counter, z, row); pole.addActionListener(this); panelHolder[z][row].add(pole);  pola[counter]=pole; counter++;}
        row++;

        for(int z=1; z<=23; z=z+2){pole=new Field(defaultColor, counter, z, row); pole.addActionListener(this); panelHolder[z][row].add(pole);  pola[counter]=pole; counter++;}
        row++;

        for(int z=0; z<=24; z=z+2){pole=new Field(defaultColor, counter, z, row); pole.addActionListener(this); panelHolder[z][row].add(pole);  pola[counter]=pole; counter++;}
        row++;

        for(int z=9; z<=15; z=z+2){pole=new Field(defaultColor, counter, z, row); pole.addActionListener(this); panelHolder[z][row].add(pole); pola[counter]=pole;  counter++;}
        row++;

        pole=new Field(defaultColor, counter, 10, row); pole.addActionListener(this); panelHolder[10][row].add(pole);  pola[counter]=pole; counter++;
        pole=new Field(defaultColor, counter, 12, row); pole.addActionListener(this); panelHolder[12][row].add(pole);  pola[counter]=pole; counter++;
        pole=new Field(defaultColor, counter, 14, row); pole.addActionListener(this); panelHolder[14][row].add(pole);  pola[counter]=pole; counter++;
        row++;

        pole=new Field(defaultColor, counter, 11, row); pole.addActionListener(this); panelHolder[11][row].add(pole);  pola[counter]=pole; counter++;
        pole=new Field(defaultColor, counter, 13, row); pole.addActionListener(this); panelHolder[13][row].add(pole); pola[counter]=pole; counter++;
        row++;

        pole=new Field(defaultColor, counter, 12, row); pole.addActionListener(this); panelHolder[12][row].add(pole); pola[counter]=pole; counter++;
        f.add(panelglowny);
        f.setVisible(true);
        
        final JButton ready = new JButton("Gotowosc");
        message = new JPanel();
        message.setPreferredSize(new Dimension((int)(f.getHeight()*0.8), (int)(f.getHeight()*0.1) ));
        message.setBounds(700,200,300,100);
        messageLabel = new JLabel();
        messageLabel.setSize(280,120);
        message.setLayout(new FlowLayout())	;
        message.add(messageLabel);
        message.add(ready);
         f.add(message);




        endTurn= new JButton("Zakoncz Ruch");
        endTurn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 selected=false;
                if(jumped) {
                    jumpEnd.FieldColor=playerColor;
                    jumpEnd.repaint();
                    out.println("MOVE;" + jumpStart.getNumber() + ";" + jumpEnd.getNumber());
                    System.out.println("MOVE;" + jumpStart.getNumber() + ";" + jumpEnd.getNumber());
                }
                else out.println("MOVE;" + 1 + ";" + 1);

                jumped=false;


                blocked=true;

            }
        });;


        ready.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                message.remove(ready);
                messageLabel.setText("Oczekiwanie na gotowosc innych graczy");
                message.repaint();
                out.println("READY");
            }
        });
        message.setVisible(true);
        
		start();
	}
	
	public void actionPerformed(ActionEvent e) {

        Object obj = e.getSource();
        if(blocked == false) {
            if (obj instanceof Field) {
                Field cb = (Field)obj;


                if(moving){

                    //przesuwanie
                    if(selected && !cb.isOccupied()){
                        //zwykly ruch
                        if(isNextTo(pole, cb) && !jumped){
                        selected=false;
                        cb.FieldColor=temp;
                        pole.FieldColor=defaultColor; pole.setEmpty(); cb.setOccupied();
                        cb.repaint(); pole.repaint();
                        out.println("MOVE;" + pole.getNumber() + ";" + cb.getNumber());
                        System.out.println("MOVE;" + pole.getNumber() + ";" + cb.getNumber());
                        blocked = true;}

                        //skakanie



                        if(ableToJump(pole, cb)) {
                            jumped=true;
                            selected=false;
                            cb.FieldColor=Color.pink;
                            pole.FieldColor=defaultColor; pole.setEmpty(); cb.setOccupied();
                            cb.repaint(); pole.repaint();
                            tmpField=cb;
                            jumpEnd=cb;

                            //blocked = true;



                        }


                    }

                    // wybór pola gdy żadne nie jest wybrane
                    else if(!selected && cb.isOccupied()) {
                        if (!jumped && (cb.getColor() == playerColor)) {
                            jumpStart=cb;
                            temp = cb.FieldColor;
                            pole = cb;
                            cb.FieldColor = Color.cyan;
                            cb.repaint();
                            selected = true;
                        }

                        if(jumped && cb==tmpField){
                            temp = playerColor;
                            pole = cb;
                            cb.FieldColor = Color.cyan;
                            cb.repaint();
                            selected = true;
                        }
                    }

                    //gdy gracz wybierze inne swoje pole, to zmieni wybor na nie
                    if(selected && cb.isOccupied() && cb.getColor() == playerColor && !jumped){
                        jumpStart=cb;
                       pole.FieldColor=temp;
                       pole.repaint();
                       pole=cb;
                       cb.FieldColor=Color.cyan;
                       cb.repaint();
                    }
                }

            }
        }
    }
	
	public static Client getClient() throws Exception {
		if(instance == null) {
			synchronized(Client.class) {
				if(instance == null) {
					instance = new Client();
				}
			}
		}
		return instance;
	}
	
	public void run(){
	    String response;
		try {
			while(true) {
				response = in.readLine();
				if(response.startsWith("START")) {
					playerColor = colorTable[response.charAt(5)-'0'];
					playerNumber = response.charAt(5)-'0';
					messageLabel.setText("Witaj w grze, oczekiwane na graczy. Grasz " + colorName[(int)response.charAt(5)-'0'] + " pionkami ");
				}
				else if(response.startsWith("YOUR")) {
					blocked = false;
					messageLabel.setText("Twoj ruch. Grasz " + colorName[playerNumber] + " pionkami ");

                    message.add(endTurn);
                    message.repaint();
                    messageLabel.repaint();
				}
				else if(response.startsWith("ADD")) {
					addPlayer(Integer.parseInt("" + response.charAt(3)));
				}
				else if(response.startsWith("MOVE")) {
					update = response.split(";");
					this.update(Integer.parseInt(update[1]), Integer.parseInt(update[2]));
				}
				else if(response.startsWith("SHUTDOWN")) {
					break;
				}
				else if(response.startsWith("RUCH")) {
					messageLabel.setText("Ruch gracza " + response.charAt(5));
					messageLabel.repaint();
                    message.remove(endTurn);
                    message.repaint();
				}
			}
			out.println("QUIT");
		} catch(IOException e){

        }

		finally {
		    try{
			socket.close();} catch(IOException e){}
		}
	}
	
	public Socket getSocket() {
		return this.socket;
	}
	
	public void addPlayer(int num) {
		if(num == 0) addPlayer0();
		else if(num == 1) addPlayer1();
		else if(num == 2) addPlayer2();
		else if(num == 3) addPlayer3();
		else if(num == 4) addPlayer4();
		else if(num == 5) addPlayer5();
	}
	
	public void addPlayer1(){
        for (int z=1; z<=10; z++){pola[z].FieldColor=Color.RED; pola[z].setOccupied(); pola[z].repaint();}
    }

	public void addPlayer2() {
		for (int z=11; z<=14; z++){pola[z].FieldColor=Color.BLUE; pola[z].setOccupied(); pola[z].repaint();}
		for (int z=24; z<=26; z++){pola[z].FieldColor=Color.BLUE; pola[z].setOccupied(); pola[z].repaint();}
		for (int z=36; z<=37; z++){pola[z].FieldColor=Color.BLUE; pola[z].setOccupied(); pola[z].repaint();}
		pola[47].FieldColor=Color.BLUE; pola[47].setOccupied(); pola[47].repaint();
	}
	
	public void addPlayer3() {
		pola[75].FieldColor=Color.GREEN; pola[75].setOccupied(); pola[75].repaint();
		for (int z=85; z<=86; z++){pola[z].FieldColor=Color.GREEN; pola[z].setOccupied(); pola[z].repaint();}
		for (int z=96; z<=98; z++){pola[z].FieldColor=Color.GREEN; pola[z].setOccupied(); pola[z].repaint();}
		for (int z=108; z<=111; z++){pola[z].FieldColor=Color.GREEN; pola[z].setOccupied(); pola[z].repaint();}
	}
	
    public void addPlayer0() {
        for (int z=112; z<=121; z++){pola[z].FieldColor=Color.YELLOW; pola[z].setOccupied(); pola[z].repaint();}
    }
    
    public void addPlayer5() {
    	pola[66].FieldColor=Color.BLACK; pola[66].setOccupied(); pola[66].repaint();
    	for (int z=76; z<=77; z++){pola[z].FieldColor=Color.BLACK; pola[z].setOccupied(); pola[z].repaint();}
    	for (int z=87; z<=89; z++){pola[z].FieldColor=Color.BLACK; pola[z].setOccupied(); pola[z].repaint();}
    	for (int z=99; z<=102; z++){pola[z].FieldColor=Color.BLACK; pola[z].setOccupied(); pola[z].repaint();}
    }
    
    public void addPlayer4() {
    	for (int z=20; z<=23; z++){pola[z].FieldColor=Color.ORANGE; pola[z].setOccupied(); pola[z].repaint();}
    	for (int z=33; z<=35; z++){pola[z].FieldColor=Color.ORANGE; pola[z].setOccupied(); pola[z].repaint();}
    	for (int z=45; z<=46; z++){pola[z].FieldColor=Color.ORANGE; pola[z].setOccupied(); pola[z].repaint();}
    	pola[56].FieldColor=Color.ORANGE; pola[56].setOccupied(); pola[56].repaint();
    }

    public void update(int oldPlace, int newPlace) {
    	Color oldColor = pola[oldPlace].getColor();
    	pola[oldPlace].changeColor(defaultColor);
    	pola[oldPlace].setEmpty();
    	pola[newPlace].changeColor(oldColor);
    	pola[newPlace].setOccupied();
    }

    public boolean isNextTo(Field field1, Field field2){
	    if(field1.x==field2.x+1 || field1.x==field2.x-1){if(field1.y==field2.y-1 || field1.y==field2.y+1) return true;}
	    if(field1.x==field2.x+2 || field1.x==field2.x-2){if(field1.y==field2.y) return true;}

	    return false;
    }
    public boolean ableToJump(Field field1, Field field2){
	    if(field1.x==field2.x+2 || field1.x==field2.x-2){
	        if(field1.y==field2.y+2 || field1.y==field2.y-2){
	            for (int v=1; v<122; v++){if(pola[v].y==(field1.y+field2.y)/2 && pola[v].x==(field1.x+field2.x)/2 && pola[v].isOccupied()) return true;}
            }
        }
        if(field1.x==field2.x+4 || field1.x==field2.x-4){
            if(field2.y==field1.y){
                for (int v=1; v<122; v++){if(pola[v].y==(field1.y+field2.y)/2 && pola[v].x==(field1.x+field2.x)/2 && pola[v].isOccupied()) return true;}

        }}


        return false;
    }
}