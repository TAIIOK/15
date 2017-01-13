package BarleyBreak;


public abstract class FieldObject<Position> {

    // -------------------------------------------------------------------------
    // Игровое поле.
    // -------------------------------------------------------------------------
    protected GameField _field;

    public FieldObject(GameField field) {
        _field = field;
    }

    // -------------------------------------------------------------------------
    // Позиция.
    // -------------------------------------------------------------------------
    protected Position _position;

    public Position position() {
        return _position;
    }

    abstract boolean setPosition(Position position);

}
