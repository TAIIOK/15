package BarleyBreak.Factory;
import BarleyBreak.Bones.*;
import BarleyBreak.GameField;

/**
 * Фабрика, порождающая кость.
 */
abstract public class BoneFactory  {
    public abstract Bone createBone(GameField field);

}
