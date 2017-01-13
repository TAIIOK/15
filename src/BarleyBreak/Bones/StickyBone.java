package BarleyBreak.Bones;


import BarleyBreak.GameField;

public class StickyBone  extends Bone  {


    public StickyBone(GameField field)
    {
        super(field);
        image = "img/left.png";
    }




    public void setMoved(boolean flag)
    {
        CanBeMoved = flag;
    }

}
