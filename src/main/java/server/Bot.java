package server;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.start.Client;
import table.CustomFrame;
import table.Field;

public class Bot extends Player {
	private int targetX =0;
	private int targetY =0;
	private Socket socket;
    private String[] update;
	private Field[] pola;
	private Field pole;
	private Field tmpField;
	private Field jumpStart;
	private Field jumpEnd;
	private JPanel[][] panelHolder;
	private JPanel panelglowny;
	private JPanel message;
	private boolean jumped=false;
	private CustomFrame f;
	boolean moving=true;
	boolean selected=false;
	private Color defaultColor=Color.WHITE;
	private Color temp;
	private Color colorTable[] = {Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.BLACK};
	private Color playerColor;
	private int playerNumber;
	private boolean canMove=false;
	public Bot() throws Exception {
		super();
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

        //panelglowny.setPreferredSize(new Dimension((int)(f.getHeight()*0.8), (int)(f.getHeight()*0.9) ));
        int counter=1;
        int row=0;
        pole=new Field(defaultColor, counter, 12, row); panelHolder[12][row].add(pole);  pola[counter]=pole; counter++; row++;
        pole=new Field(defaultColor, counter, 11, row); panelHolder[11][row].add(pole);  pola[counter]=pole; counter++;
        pole=new Field(defaultColor, counter, 13, row); panelHolder[13][row].add(pole);  pola[counter]=pole; counter++; row++;
        pole=new Field(defaultColor, counter, 10, row); panelHolder[10][row].add(pole);  pola[counter]=pole; counter++;
        pole=new Field(defaultColor, counter, 12, row); panelHolder[12][row].add(pole);  pola[counter]=pole; counter++;
        pole=new Field(defaultColor, counter, 14, row); panelHolder[14][row].add(pole);  pola[counter]=pole; counter++; row++;
        for(int z=9; z<=15; z=z+2){pole=new Field(defaultColor, counter, z, row); panelHolder[z][row].add(pole);  pola[counter]=pole; counter++;} row++;
        for(int z=0; z<=24; z=z+2){pole=new Field(defaultColor, counter, z, row); panelHolder[z][row].add(pole);  pola[counter]=pole; counter++;} row++;
        for(int z=1; z<=23; z=z+2){pole=new Field(defaultColor, counter, z, row); panelHolder[z][row].add(pole);  pola[counter]=pole; counter++;} row++;
        for(int z=2; z<=22; z=z+2){pole=new Field(defaultColor, counter, z, row); panelHolder[z][row].add(pole);  pola[counter]=pole; counter++;} row++;
        for(int z=3; z<=21; z=z+2){pole=new Field(defaultColor, counter, z, row); panelHolder[z][row].add(pole);  pola[counter]=pole; counter++;} row++;
        for(int z=4; z<=20; z=z+2){pole=new Field(defaultColor, counter, z, row); panelHolder[z][row].add(pole);  pola[counter]=pole; counter++;} row++;
        for(int z=3; z<=21; z=z+2){pole=new Field(defaultColor, counter, z, row); panelHolder[z][row].add(pole);  pola[counter]=pole; counter++;} row++;
        for(int z=2; z<=22; z=z+2){pole=new Field(defaultColor, counter, z, row); panelHolder[z][row].add(pole);  pola[counter]=pole; counter++;} row++;
        for(int z=1; z<=23; z=z+2){pole=new Field(defaultColor, counter, z, row); panelHolder[z][row].add(pole);  pola[counter]=pole; counter++;} row++;
        for(int z=0; z<=24; z=z+2){pole=new Field(defaultColor, counter, z, row); panelHolder[z][row].add(pole);  pola[counter]=pole; counter++;} row++;
        for(int z=9; z<=15; z=z+2){pole=new Field(defaultColor, counter, z, row); panelHolder[z][row].add(pole);  pola[counter]=pole;  counter++;} row++;

        pole=new Field(defaultColor, counter, 10, row); panelHolder[10][row].add(pole);  pola[counter]=pole; counter++;
        pole=new Field(defaultColor, counter, 12, row); panelHolder[12][row].add(pole);  pola[counter]=pole; counter++;
        pole=new Field(defaultColor, counter, 14, row); panelHolder[14][row].add(pole);  pola[counter]=pole; counter++;
        row++;

        pole=new Field(defaultColor, counter, 11, row); panelHolder[11][row].add(pole);  pola[counter]=pole; counter++;
        pole=new Field(defaultColor, counter, 13, row); panelHolder[13][row].add(pole); pola[counter]=pole; counter++;
        row++;

        pole=new Field(defaultColor, counter, 12, row); panelHolder[12][row].add(pole); pola[counter]=pole; counter++;
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
    
    public void addPlayer4() {
    	pola[66].FieldColor=Color.BLACK; pola[66].setOccupied(); pola[66].repaint();
    	for (int z=76; z<=77; z++){pola[z].FieldColor=Color.BLACK; pola[z].setOccupied(); pola[z].repaint();}
    	for (int z=87; z<=89; z++){pola[z].FieldColor=Color.BLACK; pola[z].setOccupied(); pola[z].repaint();}
    	for (int z=99; z<=102; z++){pola[z].FieldColor=Color.BLACK; pola[z].setOccupied(); pola[z].repaint();}
    }
    
    public void addPlayer5() {
    	for (int z=20; z<=23; z++){pola[z].FieldColor=Color.ORANGE; pola[z].setOccupied(); pola[z].repaint();}
    	for (int z=33; z<=35; z++){pola[z].FieldColor=Color.ORANGE; pola[z].setOccupied(); pola[z].repaint();}
    	for (int z=45; z<=46; z++){pola[z].FieldColor=Color.ORANGE; pola[z].setOccupied(); pola[z].repaint();}
    	pola[56].FieldColor=Color.ORANGE; pola[56].setOccupied(); pola[56].repaint();
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
    
    public void doMove() {
    	try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	double distance;
    	double shortest = 100;
    	Field shortestOld = null;
    	Field shortestNew = null;
    	int oldPlace = 0;
    	int newPlace = 0 ;
    	for(int z=1; z<=121; z++) {
    		if(pola[z].getColor() == playerColor) {
    			for(int z2=1; z2<=121; z2++) {
    				if(isNextTo(pola[z2], pola[z]) && !pola[z2].isOccupied()) {
    					distance = Math.sqrt(Math.pow(Math.abs(targetX-pola[z2].x), 2) + Math.pow(Math.abs(targetY-pola[z2].y), 2));
    					if(distance < shortest) {
    						shortest = distance;
    						shortestOld = pola[z];
    						shortestNew = pola[z2];
    					}
    				}
    			}
    		}
    	}
    	shortestNew.setOccupied();
    	shortestNew.changeColor(playerColor);
    	shortestOld.setEmpty();
    	shortestOld.changeColor(defaultColor);
		oldPlace = shortestOld.getNumber();
		newPlace = shortestNew.getNumber();
    	Game.update("MOVE;" + oldPlace + ";" + newPlace);

    }
    
    @Override
    public void run() {
		while(true){
			try {
				TimeUnit.MILLISECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(canMove) {doMove(); canMove=false;}
		}
    }
    
	@Override
	public void addPlayer(int num) {
		if(num == 0) addPlayer0();
		else if(num == 1) addPlayer1();
		else if(num == 2) addPlayer2();
		else if(num == 3) addPlayer3();
		else if(num == 4) addPlayer4();
		else if(num == 5) addPlayer5();
	}
	
	@Override
	public void starting(int playerN){
		playerColor = colorTable[playerN];
		playerNumber = playerN;
		if(playerColor == Color.RED) {
			targetX = 12;
			targetY = 16;
		}
		else {
			targetX = 0;
			targetY = 24;
		}
    }
	
	@Override
	public void yourMove() {
		canMove=true;
    }
	
	@Override
    public void otherMove(int current) {
    }
	
	@Override
	public void update(String command) {
		update = command.split(";");
		int oldPlace = Integer.parseInt(update[1]);
		int newPlace = Integer.parseInt(update[2]);
    	Color oldColor = pola[oldPlace].getColor();
    	pola[oldPlace].changeColor(defaultColor);
    	pola[oldPlace].setEmpty();
    	pola[newPlace].changeColor(oldColor);
    	pola[newPlace].setOccupied();
    }
}