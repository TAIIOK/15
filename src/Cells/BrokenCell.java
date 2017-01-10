package Cells;


public class BrokenCell extends Cell {

    public BrokenCell()
    {
        image = "img/off.png";
        CanBeMoved = false;
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

    public String GetImage()
    {
        return this.image;
    }



}
