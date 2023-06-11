package Macchiato.Implementation;

public class ExpressionConst implements Expression {
    private final int value;

    public ExpressionConst(int value) {
        this.value = value;
    }

    @Override
    public int evaluate() {
        return value;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
