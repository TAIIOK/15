package BarleyBreak.Bones;


import BarleyBreak.GameField;

public class FixedBone extends Bone {

    public FixedBone(GameField field)
    {
        super(field);
        image = "img/off.png";
        CanBeMoved = false;

    }


    public String GetImage()
    {
        return this.image;
    }



}
