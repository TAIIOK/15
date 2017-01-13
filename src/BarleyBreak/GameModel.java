package BarleyBreak;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.*;
import java.util.*;

import BarleyBreak.Bones.*;

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

        int[]  availableBlock = {1,4,6,7,10,11,13};
        int[]  availableStick = {1,5,9,13};

        size = field().height() * field().width();
        int count = 1;
        int stick = (int) (Math.random() * 4);
        int block = (int) (Math.random() * 8);
        for (int row = 1; row <= field().height(); row++) {
            for (int col = 1; col <= field().width(); col++) {
                if(count == availableStick[stick]) {
                    //field().setBone(new Point(col, row), new StickyBone(), String.valueOf(count));
                    generateStickyBone(col, row, count);
                }
                else if (count == availableBlock[block])
                {
                    //field().setBone(new Point(col, row), new FixedBone(), String.valueOf(count));
                    generateFixedBone(col, row, count);
                }
                else if(count < 16) {
                    //field().setBone(new Point(col, row), new SimpleBone() , String.valueOf(count));
                    generateSimpleBone(col, row, count);
                }
                else{
                    generateEmptyBone(col, row);
                }
                count++;
            }
        }

    }

    private void generateStickyBone(int col, int row, int count){
        field().setBone(new Point(col, row), new StickyBone(), String.valueOf(count));
    }
    
    private void generateFixedBone(int col, int row, int count){
        field().setBone(new Point(col, row), new FixedBone(), String.valueOf(count));
    }
    
    private void generateSimpleBone(int col, int row, int count){
        field().setBone(new Point(col, row), new SimpleBone(), String.valueOf(count));
    }
    
    private void generateEmptyBone(int col, int row){
        field().setBone(new Point(col, row),  new EmptyBone(),"");
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

