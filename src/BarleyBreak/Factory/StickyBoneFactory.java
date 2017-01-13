package BarleyBreak.Factory;

import BarleyBreak.Bones.*;
import BarleyBreak.GameField;

/**
 * Created by romanefimov on 13.01.17.
 */
public class StickyBoneFactory extends BoneFactory{
    @Override
    public Bone createBone(GameField field){
        return new StickyBone(field);
    }
}

