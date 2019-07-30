package input;

public interface InputValue {
    enum Types {EXIT, INCORRECT, VALUE}
    Types getType();
}