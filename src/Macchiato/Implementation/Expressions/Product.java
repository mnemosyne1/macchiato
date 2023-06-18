package Macchiato.Implementation.Expressions;

public class Product extends ExpressionCombo {
    // public because it's legacy constructor from Macchiato 1.0
    public Product(Expression exp1, Expression exp2) {
        super(exp1, exp2);
        sign = '*';
    }

    public static Product of(Expression exp1, Expression exp2) {
        return new Product(exp1, exp2);
    }

    @Override
    public int evaluate() throws ExpressionEvaluationException {
        return exp1.evaluate() * exp2.evaluate();
    }

    @Override
    public int getPriority() {
        return 2;
    }
}
