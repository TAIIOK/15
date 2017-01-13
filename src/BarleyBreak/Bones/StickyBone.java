package BarleyBreak.Bones;


public class StickyBone  extends Bone  {


    public StickyBone()
    {
        image = "img/left.png";

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


    public void setMoved(boolean flag)
    {
        CanBeMoved = flag;
    }

}
