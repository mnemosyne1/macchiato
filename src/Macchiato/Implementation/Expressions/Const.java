package Macchiato.Implementation.Expressions;

public class Const implements Expression {
    private final int value;

    // legacy constructor from Macchiato 1.0
    public Const(int value) {
        this.value = value;
    }

    public static Const of(int value) {
        return new Const(value);
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
