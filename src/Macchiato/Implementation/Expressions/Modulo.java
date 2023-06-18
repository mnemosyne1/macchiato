package Macchiato.Implementation.Expressions;

public class Modulo extends ExpressionCombo {
    // public because it's legacy constructor from Macchiato 1.0
    public Modulo(Expression exp1, Expression exp2) {
        super(exp1, exp2);
        sign = '%';
    }

    public static Modulo of(Expression exp1, Expression exp2) {
        return new Modulo(exp1, exp2);
    }

    @Override
    public int evaluate() throws ExpressionEvaluationException {
        int divisor = exp2.evaluate();
        if (divisor == 0) throw new ExpressionEvaluationException();
        return exp1.evaluate() % divisor;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
