package input;

public class IncorrectInputValue implements InputValue {
    @Override
    public Types getType() {
        return Types.INCORRECT;
    }
}
