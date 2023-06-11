package Macchiato.Implementation;

public class ExpressionDiff extends ExpressionCombo {
    public ExpressionDiff(Expression exp1, Expression exp2) {
        super(exp1, exp2);
        sign = '-';
    }

    @Override
    public int evaluate() throws ExpressionEvaluationException {
        return exp1.evaluate() - exp2.evaluate();
    }

    @Override
    public int getPriority() {
        return 2;
    }
}
