package Cells;


public class Label {
    
    
// --------------- Ячейка, которой прнадлежит метка. Задает сама ячейка -------
    private Cell cell;

    private String number;


    public String getNumber ()
    {
        return this.number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public Cell cell(){
        return cell;
    }
    
    public void setCell(Cell c){
        cell=c;
    }



}
