package barley_break;

import java.util.List;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;


/**
 *  Прямоугольное поле, состоящее из ячеек
 */
public class GameField {
    
// ----------------- Контейнер ячеек, а в конечном счете, и меток ---------------
// Позиции ячеек и меток  задаются строками и столбцами в диапазоне [1, height] и
// [1, width] соответсвенно
    
// ------------------------------ Ячейки ---------------------------------------    
    //Список ячеек
    private List<Cell> cellList = new ArrayList();
    
    Cell cell(Point pos){
        for (Cell item : this.cellList) {
            if (item.position().equals(pos)) {
                return item;
            }
        }
        
        return null;
    }
    
    void setCell(Point pos, Cell cell, String number){
        cell.setPosition(pos);

        Label l = new Label();
        l.setCell(cell);
        l.setNumber(number);

        cell.setLabel(l);

        cellList.add(cell);

    }

    public boolean move(Point p)
    {
        Cell current = cell(p);
        Cell emptyCell = new Cell();
        for (Cell item : this.cellList) {
            if (item.label().getNumber() == "") {
                emptyCell = item;
            }
        }


        if(isLegalPlay(current.position().getX(),current.position().getY(),emptyCell.position().getX(),emptyCell.position().getY()))
        {

            Point emptyPosition = emptyCell.position();

            double Y = current.position().getY();
            double X = current.position().getX();

            current.position().setLocation(emptyPosition);
            emptyCell.position().x = (int)X;
            emptyCell.position().y = (int)Y;
            return true;
        }
        else
            return false;

    }


    private boolean isLegalPlay(double x_1, double y_1 , double x_2, double y_2 )
    {

            if(y_1>y_2)
            {
                if((x_1==x_2) && y_1-y_2==1)
                {
                    return true;
                }
            }
            else if(y_1<y_2)
            {
                if((x_1==x_2) && y_2-y_1==1)
                {
                    return true;
                }
            }

            if(x_1>x_2)
            {
                if((y_1==y_2 ) && x_1-x_2==1)
                {
                    return true;
                }
            }
            else if(x_1<x_2)
            {
                if((y_1==y_2 ) && x_2-x_1==1)
                {
                    return true;
                }
            }


        return false;
    }

    public  List<Cell> getCells() {

        return this.cellList;
    }

    public void MixArray()
    {
        List<Integer> Numbers  = new ArrayList();


        for(int i = 0 ;Numbers.size()!=16;i++)
        {
            int fistnumber;
            int secondnumber;
            do{
                fistnumber = (int) (Math.random() * 16);
                secondnumber = (int) (Math.random() * 16);
            }
            while(Numbers.contains(fistnumber) || Numbers.contains(secondnumber));

                Numbers.add(fistnumber);
                Numbers.add(secondnumber);

            Cell first = cellList.get(fistnumber);
            Cell second = cellList.get(secondnumber);

            Point firstCell = first.position();

            double Y = second.position().getY();
            double X = second.position().getX();


            second.position().setLocation(firstCell);

            first.position().x = (int)X;
            first.position().y = (int)Y;


        }


    }

    public void clear(){
        cellList.clear();
    }    
    
    private void removeCell(Point pos){

        cellList.remove(cell(pos));
    }
    

// ----------------------- Ширина и высота поля ------------------------------
    private int _width;
    private int _height;

    public void setSize(int width, int height) {
        _width = width;
        _height = height;
    }

    public int width() {
        return _width;
    }

    public int height() {
        return _height;
    }
    
    public boolean containsRange(Point p){
        return p.getX() >= 1 && p.getX() <= _width &&
               p.getY() >= 1 && p.getY() <= _height ;
    }
    
// ----------------------------------------------------------------------------    
    public GameField() {
        setSize(4, 4);
    }
}
