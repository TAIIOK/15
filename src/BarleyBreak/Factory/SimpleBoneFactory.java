package BarleyBreak.Factory;
import BarleyBreak.Bones.*;
import BarleyBreak.GameField;

/**
 * Фабрика, порождающая простые кости
 */
public class SimpleBoneFactory extends BoneFactory {

    @Override
    public Bone createBone(GameField field){
        return new SimpleBone(field);
    }
}
