package Macchiato.Implementation;

public class ExpressionModulo extends ExpressionCombo {
    public ExpressionModulo(Expression exp1, Expression exp2) {
        super(exp1, exp2);
        sign = '%';
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
