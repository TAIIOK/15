package barley_break;

import java.awt.Point;

/*
 * Ячейка, являющаяся составной частью поля и содержащая в себе метку
 */
abstract  public class Cell {
    
// --------------------- Позиция метки -----------------------

    protected String image;

    protected boolean CanBeMoved;

    //Позиция
    private Point position;
    
    
    void setPosition(Point pos) {
        this.position=pos;
    }

    public Point position() {
        return position;
    }
    
// --------------------- Метка, принадлежащая ячейке -----------------------
    //Метка
    private Label label;
    
    
    public Label label() {
        return label;
    }
    
    public boolean isEmpty()
    {
        return label == null;
    }
    
    public void setLabel(Label l) {
        l.setCell(this);
        this.label = l;
    }

}
