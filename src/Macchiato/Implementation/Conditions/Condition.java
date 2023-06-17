package Macchiato.Implementation.Conditions;

import Macchiato.Implementation.BlockManagement;
import Macchiato.Implementation.Expressions.Expression;

public abstract class Condition {
    public static class ConditionException extends Exception {
        private final Condition bad;

        public ConditionException(Condition bad) {
            this.bad = bad;
        }

        @Override
        public String getMessage() {
            String ans = "Błąd w warunku: " + bad.toString() + '\n';
            ans += BlockManagement.printValues();
            return ans;
        }
    }

    protected final Expression exp1;
    protected final Expression exp2;
    protected String comparisonSign;

    public Condition(Expression exp1, Expression exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    public abstract boolean checkCondition() throws ConditionException;

    public static Condition newEqual(Expression a, Expression b) {
        return new ConditionEqual(a, b);
    }

    public static Condition newDifferent(Expression a, Expression b) {
        return new ConditionDifferent(a, b);
    }

    public static Condition newLess(Expression a, Expression b) {
        return new ConditionLess(a, b);
    }

    public static Condition newMore(Expression a, Expression b) {
        return new ConditionMore(a, b);
    }

    public static Condition newNotMore(Expression a, Expression b) {
        return new ConditionNotMore(a, b);
    }

    public static Condition newNotLess(Expression a, Expression b) {
        return new ConditionNotLess(a, b);
    }

    @Override
    public String toString() {
        return exp1.toString() + comparisonSign + exp2.toString();
    }
}
