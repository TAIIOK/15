package BarleyBreak.Bones;

import BarleyBreak.GameField;

public class EmptyBone extends Bone {


    public EmptyBone(GameField field) {
        super(field);
        image = "img/off.png";
        CanBeMoved = false;


    }
}
