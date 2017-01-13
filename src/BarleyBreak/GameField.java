package BarleyBreak;

import java.util.List;
import java.awt.Point;
import java.util.ArrayList;

import BarleyBreak.Bones.*;

import javax.swing.*;

/**
 *  Прямоугольное поле, состоящее из ячеек
 */
public class GameField {

    // ----------------------------------------------------------------------------
    public GameField() {
        setSize(4, 4);
    }

    //Список костяшек
    private List<Bone> BoneList = new ArrayList();


    Bone bone(BonePosition pos){
        for (Bone item : this.BoneList) {
            if (item.position().equals(pos)) {
                return item;
            }
        }
        
        return null;
    }
    
    void setBone(int col , int row, Bone bone, String number){

        bone.setPosition(new BonePosition(row,col));


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

    public boolean move(BonePosition p)
    {
        Bone current = bone(p);
        Bone emptyBone ;
        for (Bone item : this.BoneList) {
            if (item instanceof EmptyBone) {
                emptyBone = item;

                if(current instanceof StickyBone)
                {
                    BonePosition point = new BonePosition(current.position().row()-1,current.position().column());

                    Bone pair = bone(point);

                        if (pair instanceof SimpleBone) {
                            ((StickyBone) current).setMoved(false);
                            JOptionPane.showMessageDialog(null, "Костяшка прилипла и не может быть передвинута ! ", "Упс", JOptionPane.PLAIN_MESSAGE);
                            return false;
                        }


                    ((StickyBone) current).setMoved(true);
                }
        if(canBeMoved(current.position().row(),current.position().column(),emptyBone.position().row(),emptyBone.position().column()))
        {

            BonePosition emptyPosition = emptyBone.position();

            int Y = current.position().row();
            int X = current.position().column();

            current.setPosition(emptyPosition);
            emptyBone.setPosition(new BonePosition(Y,X));

            return true;
        }
        else
            return false;


            }
        }
        return  false;

    }

    public boolean determinateWin()
    {
        int start = 1;
        for (int row = 1; row <= height(); row++) {

            for (int col = 1; col <= width(); col++) {
                BonePosition position = new BonePosition(row,col);
                Bone current = bone(position);//.label().getNumber();
                int Number = -1;
                if(current instanceof  EmptyBone)
                {
                    if(col != 4 & row != 4)
                        return false;
                }
                else  {
                    if (current instanceof  FixedBone)
                    {
                        Number =  Integer.parseInt(((FixedBone ) current).getLabel());
                    }

                    if (current instanceof  SimpleBone)
                    {
                        Number =  Integer.parseInt(((SimpleBone ) current).getLabel());
                    }

                    if (current instanceof  StickyBone)
                    {
                        Number =  Integer.parseInt(((StickyBone ) current).getLabel());
                    }

                    if(start != Number )
                        return false;
                }
                start++;

            }
        }

        return true;
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

            BonePosition firstBone = first.position();

            int Y = second.position().row();
            int X = second.position().column();


            second.setPosition(firstBone);

            first.setPosition(new BonePosition(Y,X));



        }


    }

    public void clear(){
        BoneList.clear();
    }    
    
    private void removeBone(BonePosition pos){

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
    

}
