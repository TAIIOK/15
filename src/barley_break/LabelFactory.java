package barley_break;


/**
 * Фабрика, порождающая метку. Реализует самую простую стратегию
 */
abstract public class LabelFactory {
    public abstract Label createLabel();
    public abstract void setGameModel(GameModel model);
}
