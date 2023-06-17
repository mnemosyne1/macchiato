package Macchiato.Implementation.Expressions;

public class Sum extends ExpressionCombo {
    // public because it's legacy constructor from Macchiato 1.0
    public Sum(Expression exp1, Expression exp2) {
        super(exp1, exp2);
        sign = '+';
    }

    public static Sum of(Expression exp1, Expression exp2) {
        return new Sum(exp1, exp2);
    }

    @Override
    public int evaluate() throws ExpressionEvaluationException {
        return exp1.evaluate() + exp2.evaluate();
    }

    @Override
    public int getPriority() {
        return 2;
    }
}
