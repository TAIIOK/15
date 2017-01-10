package barley_break;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.*;
import java.util.*;

import Cells.*;

public class GameModel {


    // -------------------------------- Поле -------------------------------------
    private GameField gameField = new GameField();

    public GameField field() {
        return gameField;
    }

    int size;



    public GameModel() {
        // Задаем размеры поля по умолчанию
        field().setSize(4, 4);
    }

// ---------------------- Порождение обстановки на поле ---------------------


    private void generateField() {

        field().clear();

        field().setSize(4, 4);

        int[]  available = {1,4,6,7,10,11,13,16};

        size = field().height() * field().width();
        int count = 1;
        int stick = (int) (Math.random() * 16);
        int block = (int) (Math.random() * 8);
        for (int row = 1; row <= field().height(); row++) {
            for (int col = 1; col <= field().width(); col++) {
                if(count == stick) {
                    field().setCell(new Point(col, row), new StickyCell(), String.valueOf(count));
                }
                else if (count == available[block])
                {
                    field().setCell(new Point(col, row), new BrokenCell(), String.valueOf(count));
                }
                else if(count < 16) {
                    field().setCell(new Point(col, row), new SimpleCell() , String.valueOf(count));

                }
                else{
                    field().setCell(new Point(col, row),  new EmptyCell(),"");
                }
                count++;
            }
        }

    }

    public void shuffleField()
    {
        field().MixArray();
    }


    public void start() {

        // Генерируем поле
        generateField();
    }

}

