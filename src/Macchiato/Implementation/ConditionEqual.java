package Macchiato.Implementation;

public class ConditionEqual extends Condition {
    public ConditionEqual(Expression exp1, Expression exp2) {
        super(exp1, exp2);
        comparisonSign = "==";
    }

    @Override
    public boolean checkCondition() throws ConditionException {
        try {
            return exp1.evaluate() == exp2.evaluate();
        } catch (Expression.ExpressionEvaluationException ex) {
            throw new ConditionException(this);
        }
    }
}
