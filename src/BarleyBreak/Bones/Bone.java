package BarleyBreak.Bones;

import BarleyBreak.*;
import java.awt.*;

abstract  public class Bone extends FieldObject<BonePosition>  {

    public Bone(GameField field){

        super(field);
    }


    protected String image;

    protected boolean CanBeMoved;

    protected String Label;



    public void setLabel(String label){
        this.Label = label;
    }

    public String getLabel(){
        return this.Label;
    }
    



    public String GetImage()
    {
        return this.image;
    }

}
