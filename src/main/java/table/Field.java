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
    int nr;
    public Color FieldColor;
    Shape shape;
    public int x;
    public int y;

    public Field(Color color, int number, int X, int Y){
        this.occupied = false;
        this.FieldColor = color;
        this.nr=number;
        this.x=X;
        this.y=Y;
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width-5, size.height-5);
        setPreferredSize(size);
        setContentAreaFilled(false);
    
    }
    
    public Color getColor() {
    	return this.FieldColor;
    }
    
    public int getNumber() {
    	return this.nr;
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

    public void changeColor(Color newColor) {
        this.FieldColor = newColor;
        this.repaint();
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