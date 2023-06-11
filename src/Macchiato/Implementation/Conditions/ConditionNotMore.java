package Macchiato.Implementation.Conditions;

import Macchiato.Implementation.Expressions.Expression;

public class ConditionNotMore extends Condition {
    public ConditionNotMore(Expression exp1, Expression exp2) {
        super(exp1, exp2);
        comparisonSign = "<=";
    }

    @Override
    public boolean checkCondition() throws Condition.ConditionException {
        try {
            return exp1.evaluate() <= exp2.evaluate();
        } catch (Expression.ExpressionEvaluationException ex) {
            throw new ConditionException(this);
        }
    }
}