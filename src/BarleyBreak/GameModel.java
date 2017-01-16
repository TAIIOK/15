package BarleyBreak;


import java.util.List;

import BarleyBreak.Bones.*;
import BarleyBreak.Factory.BasicBarleyBreakBoneFactory;

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

        List<Bone> BoneList = _BasicBarleyBreakBoneFactory.createBones(field());

        field().setBones(BoneList);

    }

    //Простая фабрика меток
    private BasicBarleyBreakBoneFactory _BasicBarleyBreakBoneFactory = new BasicBarleyBreakBoneFactory();


    public void shuffleField() {
        field().MixArray();
    }


    public void start() {

        // Генерируем поле
        generateField();
    }

}

