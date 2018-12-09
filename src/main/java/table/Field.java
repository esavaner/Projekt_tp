package table;

import java.awt.*;
import java.awt.geom.Ellipse2D;

//pole na planszy
public class Field extends Ellipse2D.Double {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean occupied;
    double height;
    double width;
    double x;
    double y;
    Color FieldColor;


    public Field(double x, double y, double r, Color color){
      this.occupied=false;
      this.FieldColor=color;
      this.height=r;
      this.width=r;
      this.x=x-(r/2);
      this.y=y-(r/2);
    }

    public boolean isOccupied(){
        return this.occupied;
    }
    
    public void setEmpty() {
    	this.occupied = false;
    }
    
    public void setOccupied() {
    	this.occupied = true;
    }
    
    public void ChangeColor(Color newColor){
        this.FieldColor=newColor;
    }
}
