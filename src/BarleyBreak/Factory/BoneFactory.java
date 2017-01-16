package BarleyBreak.Factory;

import BarleyBreak.Bones.*;
import BarleyBreak.GameField;

import java.util.List;

/**
 * Фабрика, порождающая кость.
 */
abstract public class BoneFactory {
    public abstract List<Bone> createBones(GameField field);

}
