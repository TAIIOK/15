package Cells;

import java.awt.Point;
import javax.swing.*;
import java.awt.*;

abstract  public class Cell {
    


    protected String image;

    protected boolean CanBeMoved;

    //Позиция
    private Point position;
    
    
    public void setPosition(Point pos) {
        this.position=pos;
    }

    public Point position() {
        return position;
    }

    public String GetImage()
    {
        return this.image;
    }

}
