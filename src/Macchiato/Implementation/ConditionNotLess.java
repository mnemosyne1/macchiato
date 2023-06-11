package Macchiato.Implementation;

public class ConditionNotLess extends Condition {
    public ConditionNotLess(Expression exp1, Expression exp2) {
        super(exp1, exp2);
        comparisonSign = ">=";
    }

    @Override
    public boolean checkCondition() throws Condition.ConditionException {
        try {
            return exp1.evaluate() >= exp2.evaluate();
        } catch (Expression.ExpressionEvaluationException ex) {
            throw new ConditionException(this);
        }
    }
}
