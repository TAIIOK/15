package barley_break;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.*;
import java.util.*;

import Cells.*;
/**
/* Aбстракция всей игры; генерирует стартовую обстановку; поочередно передает 
* ход игрокам, задавая им метку для установки на поле; следит за игроками с 
* целью определения конца игры
*/
public class GameModel {


    // -------------------------------- Поле -------------------------------------
    private GameField gameField = new GameField();

    public GameField field() {
        return gameField;
    }

    int size;

// -------------------------------- Игроки -----------------------------------


    public GameModel() {
        // Задаем размеры поля по умолчанию
        field().setSize(4, 4);
    }

// ---------------------- Порождение обстановки на поле ---------------------


    private void generateField() {

        field().clear();
        field().setSize(4, 4);
        size = field().height() * field().width();
        int count = 1;
        for (int row = 1; row <= field().height(); row++) {
            for (int col = 1; col <= field().width(); col++) {
                if(count == 1) {
                    field().setCell(new Point(col, row), new StickyCell(), String.valueOf(count));
                }
                else if (count == 7)
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

