package barley_break;

import java.util.List;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import Cells.*;

/**
 *  Прямоугольное поле, состоящее из ячеек
 */
public class GameField {
    


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

        if(cell instanceof  EmptyCell){}

        else if(cell instanceof  BrokenCell)
        {
            ((BrokenCell) cell).setLabel(l);
        }

        else if(cell instanceof  SimpleCell)
        {
            ((SimpleCell) cell).setLabel(l);
        }

        else if(cell instanceof  StickyCell)
        {
            ((StickyCell) cell).setLabel(l);
        }



        cellList.add(cell);

    }

    public boolean move(Point p)
    {
        Cell current = cell(p);
        Cell emptyCell ;
        for (Cell item : this.cellList) {
            if (item instanceof EmptyCell) {
                emptyCell = item;

                if(current instanceof StickyCell)
                {
                    Point point = new Point();
                    point.x = (int)current.position().getX()-1;
                    point.y = (int)current.position().getY();

                    Cell pair = cell(point);

                        if (pair instanceof SimpleCell) {
                            ((StickyCell) current).setMoved(false);
                            return false;
                        }


                    ((StickyCell) current).setMoved(true);
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
        }
        return  false;

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


        for(int i = 0 ;Numbers.size()!=14;i++)
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

            if(first instanceof BrokenCell || second instanceof BrokenCell)
                continue;

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
    

// ----------------------------------------------------------------------------    
    public GameField() {
        setSize(4, 4);
    }
}
