package table;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

//pole na planszy
public class Field extends JButton {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    boolean occupied;
    //int height;
    //int width;
    //int x;
    //int y;
    int nr;
    Color FieldColor;
    Shape shape;


    //public Field(int x, int y, int r, Color color, int number) {
    public Field(Color color, int number){
        this.occupied = false;
        this.FieldColor = color;
        this.nr=number;
        //this.height = r;
        //this.width = r;
        //this.x = x - (r / 2);
        //this.y = y - (r / 2);
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width-5, size.height-5);
        setPreferredSize(size);
        setContentAreaFilled(false);



    }

    public boolean isOccupied() {
        return this.occupied;
    }

    public void setEmpty() {
        this.occupied = false;
    }

    public void setOccupied() {
        this.occupied = true;
    }

    public void ChangeColor(Color newColor) {
        this.FieldColor = newColor;
    }


    protected void paintComponent(Graphics g) {

            g.setColor(this.FieldColor);


        g.fillOval(0, 0, getWidth(), getHeight());

        super.paintComponent(g);
    }

    // paint a round border as opposed to a rectangular one
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawOval(0, 0, getSize().width-1, getSize().height-1);
    }

    // only clicks within the round shape should be accepted
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }

        return shape.contains(x, y);
    }


}