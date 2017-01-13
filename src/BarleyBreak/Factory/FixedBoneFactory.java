package BarleyBreak.Factory;
import BarleyBreak.Bones.*;
import BarleyBreak.GameField;

/**
 * Фабрика, порождающая фиксированые кости.
 */
public class FixedBoneFactory extends BoneFactory {

    @Override
    public Bone createBone(GameField field){
        return new FixedBone(field);
    }
}
