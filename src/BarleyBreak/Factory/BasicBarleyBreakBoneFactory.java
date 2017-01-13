package BarleyBreak.Factory;

import BarleyBreak.BonePosition;
import BarleyBreak.Bones.*;
import BarleyBreak.GameField;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romanefimov on 13.01.17.
 */
public class BasicBarleyBreakBoneFactory extends BoneFactory {

    @Override

    public List<Bone> createBones(GameField field){
        List<Bone> BoneList = new ArrayList();

        int[]  availableBlock = {1,4,6,7,10,11,13};
        int[]  availableStick = {1,5,9,13};

        int count = 1;
        int stick = (int) (Math.random() * 4);
        int block = (int) (Math.random() * 8);
        for (int row = 1; row <= 4; row++) {
            for (int col = 1; col <= 4; col++) {
                if(count == availableStick[stick]) {
                    //field().setBone(new Point(col, row), new StickyBone(), String.valueOf(count));
                    StickyBone newBone = new StickyBone(field);
                    newBone.setPosition(new BonePosition(row,col));
                    newBone.setLabel(String.valueOf(count));
                    BoneList.add(newBone);
                }
                else if (count == availableBlock[block])
                {
                    //field().setBone(new Point(col, row), new FixedBone(), String.valueOf(count));
                    FixedBone newBone = new FixedBone(field);
                    newBone.setPosition(new BonePosition(row,col));
                    newBone.setLabel(String.valueOf(count));
                    BoneList.add(newBone);
                }
                else if(count < 16) {
                    //field().setBone(new Point(col, row), new SimpleBone() , String.valueOf(count));
                    SimpleBone newBone = new SimpleBone(field);
                    newBone.setPosition(new BonePosition(row,col));
                    newBone.setLabel(String.valueOf(count));
                    BoneList.add(newBone);
                }
                else{

                }
                count++;
            }
        }

        return BoneList ;
    }
}
