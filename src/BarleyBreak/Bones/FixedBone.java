package BarleyBreak.Bones;


public class FixedBone extends Bone {

    public FixedBone()
    {
        image = "img/off.png";
        CanBeMoved = false;
    }

    // --------------------- Метка, принадлежащая ячейке -----------------------
    //Метка
    private Label label;


    public Label label() {
        return label;
    }

    public boolean isEmpty()
    {
        return label == null;
    }

    public void setLabel(Label l) {
        l.setBone(this);
        this.label = l;
    }

    public String GetImage()
    {
        return this.image;
    }



}
