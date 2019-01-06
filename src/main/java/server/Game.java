package server;

import table.Field;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * klasa Game odpowiada za komunikacje miedzy grajacymi, kolejnosc ruchow,
 * przekazywanie informacji o ruchach innych graczy oraz za zakonczenie rozgrywki
 */
public class Game extends Thread {
    private static Player currentPlayer;
    private static boolean waiting = false;
    private int current = 0;
    private static Field[] pola= new Field[122];
    private static Color defaultColor=Color.WHITE;
    public static String[] update;
    private static int i;
    public static List<Player> players = new ArrayList<Player>();
    public Game(){for(int i=1; i<122; i++) pola[i]=new Field(i);}
    public void run() {
        while(true) {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(waiting == false) {
                currentPlayer = players.get(current);
                for(Player p : players) {
                    if(p == currentPlayer) {
                        p.yourMove();
                    }
                    else {
                        p.otherMove(current + 1);
                    }
                }
                current = (current + 1)%players.size();
                waiting = true;
            }
        }
    }
    public void add(Player player) {
        players.add(player);
    }

    public static void update(String command) {

        for(Player p : players) {
            if(p != currentPlayer) {
                p.update(command);
            }
        }
        update = command.split(";");
        update(Integer.parseInt(update[1]), Integer.parseInt(update[2]));
        waiting = false;
        for(i=0; i<=5; i++){if(hasWon(i)) break;}
        if(i<6){
            players.get(i).youWon();
            for(int j=1; j<players.size(); j++){players.get((i+j)%players.size()).otherWon(i+1);}
        waiting=true;
        }
    }
    
    public static void addPlayer(int num) {
        if(num == 0) addPlayer0();
        else if(num == 1) addPlayer1();
        else if(num == 2) addPlayer2();
        else if(num == 3) addPlayer3();
        else if(num == 4) addPlayer4();
        else if(num == 5) addPlayer5();
    }
    public static void addPlayer0() {
        for (int z=112; z<=121; z++){pola[z].FieldColor=Color.YELLOW; pola[z].setOccupied(); pola[z].repaint();}
    }

    public static void addPlayer1(){
        for (int z=1; z<=10; z++){pola[z].FieldColor= Color.RED; pola[z].setOccupied(); pola[z].repaint();}
    }

    public static void addPlayer2() {
        for (int z=11; z<=14; z++){pola[z].FieldColor=Color.BLUE; pola[z].setOccupied(); pola[z].repaint();}
        for (int z=24; z<=26; z++){pola[z].FieldColor=Color.BLUE; pola[z].setOccupied(); pola[z].repaint();}
        for (int z=36; z<=37; z++){pola[z].FieldColor=Color.BLUE; pola[z].setOccupied(); pola[z].repaint();}
        pola[47].FieldColor=Color.BLUE; pola[47].setOccupied(); pola[47].repaint();
    }

    public static void addPlayer3() {
        pola[75].FieldColor=Color.GREEN; pola[75].setOccupied(); pola[75].repaint();
        for (int z=85; z<=86; z++){pola[z].FieldColor=Color.GREEN; pola[z].setOccupied(); pola[z].repaint();}
        for (int z=96; z<=98; z++){pola[z].FieldColor=Color.GREEN; pola[z].setOccupied(); pola[z].repaint();}
        for (int z=108; z<=111; z++){pola[z].FieldColor=Color.GREEN; pola[z].setOccupied(); pola[z].repaint();}
    }

    public static void addPlayer5() {
        for (int z=20; z<=23; z++){pola[z].FieldColor=Color.ORANGE; pola[z].setOccupied(); pola[z].repaint();}
        for (int z=33; z<=35; z++){pola[z].FieldColor=Color.ORANGE; pola[z].setOccupied(); pola[z].repaint();}
        for (int z=45; z<=46; z++){pola[z].FieldColor=Color.ORANGE; pola[z].setOccupied(); pola[z].repaint();}
        pola[56].FieldColor=Color.ORANGE; pola[56].setOccupied(); pola[56].repaint();
    }
    public static void addPlayer4() {
        pola[66].FieldColor=Color.BLACK; pola[66].setOccupied(); pola[66].repaint();
        for (int z=76; z<=77; z++){pola[z].FieldColor=Color.BLACK; pola[z].setOccupied(); pola[z].repaint();}
        for (int z=87; z<=89; z++){pola[z].FieldColor=Color.BLACK; pola[z].setOccupied(); pola[z].repaint();}
        for (int z=99; z<=102; z++){pola[z].FieldColor=Color.BLACK; pola[z].setOccupied(); pola[z].repaint();}
    }

    public static void update(int oldPlace, int newPlace) {
        Color oldColor = pola[oldPlace].getColor();
        pola[oldPlace].changeColor(defaultColor);
        pola[oldPlace].setEmpty();
        pola[newPlace].changeColor(oldColor);
        pola[newPlace].setOccupied();
    }

    private static boolean hasWon(int nr){
        switch (nr){
            case 0: for (int z=1; z<=10; z++){if(pola[z].FieldColor!=Color.YELLOW) return false;}
                break;
            case 1: for (int z=112; z<=121; z++){if(pola[z].FieldColor!=Color.RED) return false;}
                break;
            case 2: if(pola[75].FieldColor!=Color.BLUE) return false;
                for (int z=85; z<=86; z++){if(pola[z].FieldColor!=Color.BLUE) return false;}
                for (int z=96; z<=98; z++){if(pola[z].FieldColor!=Color.BLUE) return false;}
                for (int z=108; z<=111; z++){if(pola[z].FieldColor!=Color.BLUE) return false;}
                break;
            case 3:  for (int z=11; z<=14; z++){if(pola[z].FieldColor!=Color.GREEN) return false;}
                for (int z=24; z<=26; z++){if(pola[z].FieldColor!=Color.GREEN) return false;}
                for (int z=36; z<=37; z++){if(pola[z].FieldColor!=Color.GREEN) return false;}
                if(pola[47].FieldColor!=Color.GREEN) return false;
                break;
            case 4: if(pola[66].FieldColor!=Color.ORANGE)return false;
                for (int z=76; z<=77; z++){if(pola[z].FieldColor!=Color.ORANGE)return false;}
                for (int z=87; z<=89; z++){if(pola[z].FieldColor!=Color.ORANGE) return false;}
                for (int z=99; z<=102; z++){if(pola[z].FieldColor!=Color.ORANGE) return false;}
                break;
            case 5:for (int z=20; z<=23; z++){if(pola[z].FieldColor!=Color.BLACK)return false;}
                for (int z=33; z<=35; z++){if(pola[z].FieldColor!=Color.BLACK)return false;}
                for (int z=45; z<=46; z++){if(pola[z].FieldColor!=Color.BLACK) return false;}
                if(pola[56].FieldColor!=Color.BLACK) return false;
                break;

        }

        return true;
    }

    public static void kill(){
        for(Player p : players) {p.kill();}
        System.exit(1);
    }
}
