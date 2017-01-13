package BarleyBreak;
import java.util.HashMap;

public class BonePosition
{
    // -- Диапазоны возможных значений по горизонтали и вертикали для всех позиций --

    private static BoneRange _horizontalRange = new BoneRange(0, 15);
    private static BoneRange _verticalRange = new BoneRange(0, 15);

    public static void setHorizontalRange(int min, int max){
        if(BoneRange.isValidRange(min, max))
        { _horizontalRange = new BoneRange(min, max); }
    }

    public static BoneRange horizontalRange(){
        return _horizontalRange;
    }

    public static void setVerticalRange(int min, int max){
        if(BoneRange.isValidRange(min, max))
        { _verticalRange = new BoneRange(min, max); }
    }

    public static BoneRange verticalRange(){
        return _verticalRange;
    }

    // ------------------ Позиция внутри диапазона ---------------------

    private int _row;// = _verticalRange.min();
    private int _column;// = _horizontalRange.min();

    public BonePosition(int row, int col)
    {
        if(!isValid(row, col))
        {  //  TODO породить исключение
        }

        _row = row;
        _column = col;
    }

    public int row(){

        if(!isValid())
        {  //  TODO породить исключение
        }

        return _row;
    }

    public int column(){

        if(!isValid())
        {  //  TODO породить исключение
        }

        return _column;
    }

    public void setRow(int row)
    {
        _row = row;
    }

    public void setColumn(int column)
    {
        _column = column;
    }

    // Позиция может стать невалидной, если изменились диапазоны допустимых значений
    public boolean isValid(){
        return isValid(_row, _column);
    }

    public static boolean isValid(int row, int col){
        return _horizontalRange.contains(col) && _verticalRange.contains(row);
    }

    @Override
    public BonePosition clone(){
        return new BonePosition(_row, _column);
    }

    // ------------------ Порождение и проверка смежных позиций ---------------------

    public BonePosition next(Direction direct){

        int[] newPos = calcNewPosition(_row, _column, direct);
        return new BonePosition(newPos[0], newPos[1]);
    }

    public boolean hasNext(Direction direct){

        int[] newPos = calcNewPosition(_row, _column, direct);
        return isValid(newPos[0], newPos[1]);
    }

    // Вовзвращает массив из двух элементов: индекс строки, индекс столбца
    private int[] calcNewPosition(int row, int col, Direction direct){

        // Таблица смещения для различных направлений: (горизонталь,вертикаль)
        HashMap<Direction, int [] > offset=  new  HashMap<Direction, int [] >();

        offset.put(Direction.north(),   new int []{ 0, -1} );
        offset.put(Direction.south(),   new int []{ 0,  1} );
        offset.put(Direction.east(),    new int []{ 1,  0} );
        offset.put(Direction.west(),    new int []{-1,  0} );

        int[] newPos = new int[2];

        newPos[0] = _row + offset.get(direct)[1];
        newPos[1] = _column + offset.get(direct)[0];

        return newPos;
    }

    // ------------------ Сравнение позиций ---------------------

    @Override
    public boolean equals(Object other){

        if(!isValid())
        {  //  TODO породить исключение
        }

        if(other instanceof BonePosition) {
            // Типы совместимы, можно провести преобразование
            BonePosition otherPosition = (BonePosition)other;
            // Возвращаем результат сравнения углов
            return _row == otherPosition._row && _column == otherPosition._column;
        }

        return false;
    }

    public  void setPosition(BonePosition position) {
        if (position.isValid()) {
            position.setColumn(position.column());
            position.setRow(position.row());

        }

    }
}