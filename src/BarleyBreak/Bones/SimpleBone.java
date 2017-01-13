package BarleyBreak.Bones;


public class SimpleBone extends Bone {

    public SimpleBone()
    {
        image = "img/simple.png";
        CanBeMoved = true;
    }


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


}
