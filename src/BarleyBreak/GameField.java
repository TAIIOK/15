package BarleyBreak;

import java.util.List;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import BarleyBreak.Bones.*;

import javax.swing.*;

/**
 *  Прямоугольное поле, состоящее из ячеек
 */
public class GameField {
    


    //Список костяшек
    private List<Bone> BoneList = new ArrayList();
    
    Bone bone(Point pos){
        for (Bone item : this.BoneList) {
            if (item.position().equals(pos)) {
                return item;
            }
        }
        
        return null;
    }
    
    void setBone(Point pos, Bone bone, String number){
        bone.setPosition(pos);



        if(bone instanceof  EmptyBone){}

        else if(bone instanceof  FixedBone)
        {
            ((FixedBone) bone).setLabel(number);
        }

        else if(bone instanceof  SimpleBone)
        {
            ((SimpleBone) bone).setLabel(number);
        }

        else if(bone instanceof  StickyBone)
        {
            ((StickyBone) bone).setLabel(number);
        }



        BoneList.add(bone);

    }

    public boolean move(Point p)
    {
        Bone current = bone(p);
        Bone emptyBone ;
        for (Bone item : this.BoneList) {
            if (item instanceof EmptyBone) {
                emptyBone = item;

                if(current instanceof StickyBone)
                {
                    Point point = new Point();
                    point.x = (int)current.position().getX()-1;
                    point.y = (int)current.position().getY();

                    Bone pair = bone(point);

                        if (pair instanceof SimpleBone) {
                            ((StickyBone) current).setMoved(false);
                            JOptionPane.showMessageDialog(null, "Костяшка прилипла и не может быть передвинута ! ", "Упс", JOptionPane.PLAIN_MESSAGE);
                            return false;
                        }


                    ((StickyBone) current).setMoved(true);
                }
        if(canBeMoved(current.position().getX(),current.position().getY(),emptyBone.position().getX(),emptyBone.position().getY()))
        {

            Point emptyPosition = emptyBone.position();

            double Y = current.position().getY();
            double X = current.position().getX();

            current.position().setLocation(emptyPosition);
            emptyBone.position().x = (int)X;
            emptyBone.position().y = (int)Y;
            return true;
        }
        else
            return false;


            }
        }
        return  false;

    }


    private boolean canBeMoved(double x_1, double y_1 , double x_2, double y_2 )
    {


            if(y_1>y_2)
            {
                if((x_1==x_2) && y_1-y_2==1)
                {
                    return true;
                }
            }
            else if(y_1<y_2)
            {
                if((x_1==x_2) && y_2-y_1==1)
                {
                    return true;
                }
            }

            if(x_1>x_2)
            {
                if((y_1==y_2 ) && x_1-x_2==1)
                {
                    return true;
                }
            }
            else if(x_1<x_2)
            {
                if((y_1==y_2 ) && x_2-x_1==1)
                {
                    return true;
                }
            }


        return false;
    }

    public  List<Bone> getBones() {

        return this.BoneList;
    }

    public void MixArray()
    {
        List<Integer> Numbers  = new ArrayList();


        for(int i = 0 ;Numbers.size()!=14;i++)
        {
            int fistnumber;
            int secondnumber;
            do{
                fistnumber = (int) (Math.random() * 16);
                secondnumber = (int) (Math.random() * 16);
            }
            while(Numbers.contains(fistnumber) || Numbers.contains(secondnumber));

                Numbers.add(fistnumber);
                Numbers.add(secondnumber);

            Bone first = BoneList.get(fistnumber);

            Bone second = BoneList.get(secondnumber);

            if(first instanceof FixedBone || second instanceof FixedBone)
                continue;

            Point firstBone = first.position();

            double Y = second.position().getY();
            double X = second.position().getX();


            second.position().setLocation(firstBone);

            first.position().x = (int)X;
            first.position().y = (int)Y;


        }


    }

    public void clear(){
        BoneList.clear();
    }    
    
    private void removeBone(Point pos){

        BoneList.remove(bone(pos));
    }
    

// ----------------------- Ширина и высота поля ------------------------------
    private int _width;
    private int _height;

    public void setSize(int width, int height) {
        _width = width;
        _height = height;
    }

    public int width() {
        return _width;
    }

    public int height() {
        return _height;
    }
    

// ----------------------------------------------------------------------------    
    public GameField() {
        setSize(4, 4);
    }
}
