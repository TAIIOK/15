package Cells;


public class StickyCell  extends Cell  {


    public StickyCell()
    {
        image = "img/left.png";

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


    public void setMoved(boolean flag)
    {
        CanBeMoved = flag;
    }

}
