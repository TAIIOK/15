package barley_break;


public class StickyCell  extends Cell  {


    public StickyCell()
    {
        image = "img/left.png";

    }


    public void setMoved(boolean flag)
    {
        CanBeMoved = flag;
    }

}
