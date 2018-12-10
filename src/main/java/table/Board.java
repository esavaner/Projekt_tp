package table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// TODO: 11.12.2018
//na ten moment można jedynie wykonywać dowolne ruchy pionkami po planszy

public class Board extends JPanel implements ActionListener {
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
        pola=new Field[122];//121 pól, numeracja od 1 bo pole nr 0 dziwnie wygląda
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



















    }
    public void actionPerformed(ActionEvent e) {

        Object obj = e.getSource();

        if (obj instanceof Field) {
        	Field cb = (Field)obj;



        	if(moving){

            if(selected && !cb.isOccupied()){
                selected=false;
                cb.FieldColor=temp;
                pole.FieldColor=defaultColor; pole.setEmpty(); cb.setOccupied();
                cb.repaint(); pole.repaint();

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

    public void addPlayer1(){
        for (int z=1; z<=10; z++){pola[z].FieldColor=Color.RED; pola[z].setOccupied(); pola[z].repaint();}

    }

    public void addPlayer4() {
        for (int z=112; z<=121; z++){pola[z].FieldColor=Color.YELLOW; pola[z].setOccupied(); pola[z].repaint();}

    }

}
