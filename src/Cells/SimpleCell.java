package Cells;


public class SimpleCell extends Cell {

    public SimpleCell()
    {
        image = "img/simple.png";
        CanBeMoved = true;
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
