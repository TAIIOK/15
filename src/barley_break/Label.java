package barley_break;


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
    
    void setCell(Cell c){
        cell=c;
    }



}
