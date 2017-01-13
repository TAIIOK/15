package BarleyBreak.Bones;


public class FixedBone extends Bone {

    public FixedBone()
    {
        image = "img/off.png";
        CanBeMoved = false;
    }


    public String GetImage()
    {
        return this.image;
    }



}
