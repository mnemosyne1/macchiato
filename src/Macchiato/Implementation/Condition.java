package Macchiato.Implementation;

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

    @Override
    public String toString() {
        return exp1.toString() + comparisonSign + exp2.toString();
    }
}
