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
	Field[] pola;
	Field pole;
	JPanel[][] panelHolder;
	JPanel panelglowny;
	CustomFrame f;
	boolean moving=true;
	boolean selected=false;
	Color defaultColor=Color.WHITE;
	Color temp;
	
	public Client() throws Exception {
		socket = new Socket(ipAddress, 8901);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
		//Main.f = new CustomFrame();
        f=new CustomFrame();
        int i = 25;
        int j = 18;
        panelHolder = new JPanel[i][j];
        pola=new Field[122];//121 pól, numeracja od 1 bo pole nr 0 dziwnie wygląda

        panelglowny=new JPanel();

        panelglowny.setLayout(new GridLayout(j,i,0,0));


        for(int m = 0; m < j; m++) {
            for(int n = 0; n < i; n++) {
                panelHolder[n][m] = new JPanel();
                panelHolder[n][m].setLayout(new FlowLayout());
                panelglowny.add(panelHolder[n][m]);
            }
        }



        panelglowny.setPreferredSize(new Dimension(f.getHeight(),f.getHeight()));
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

      //todo przestawić dodawanie pionków na plansze gdzie indziej
        addPlayer1();
        addPlayer4();
        f.add(panelglowny);
        f.setVisible(true);
        
        final JButton ready = new JButton("Gotowosc");
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

                    if(selected && !cb.isOccupied()){
                        selected=false;
                        cb.FieldColor=temp;
                        pole.FieldColor=defaultColor; pole.setEmpty(); cb.setOccupied();
                        cb.repaint(); pole.repaint();
                        //tutaj jest ok, przekazuje move do outputstreama
                        out.println("MOVE ");
                        System.out.println("MOVE ");
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
				if(response.startsWith("YOUR")) {
					blocked = false;
					messageLabel.setText("Twoj ruch");
					messageLabel.repaint();
				}
				else if(response.startsWith("UPDATE")) {
					//this.update(response.charAt(7), response.charAt(8) ,response.charAt(9), response.charAt(10));
				}
				else if(response.startsWith("SHUTDOWN")) {
					break;
				}
				else if(response.startsWith("RUCH")) {
					messageLabel.setText("Ruch gracza " + response.charAt(5));
					messageLabel.repaint();
				}
				//todo możliwe że nie potrzebne
				/*
                else if(response.startsWith("START")) {

                	messageLabel.setText("zaczynamy");
                	messageLabel.repaint();
                }*/
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
	
	public void addPlayer1(){
        for (int z=1; z<=10; z++){pola[z].FieldColor=Color.RED; pola[z].setOccupied(); pola[z].repaint();}
    }

    public void addPlayer4() {
        for (int z=112; z<=121; z++){pola[z].FieldColor=Color.YELLOW; pola[z].setOccupied(); pola[z].repaint();}
    }

    public static void update() {
    	
    }

}