package Macchiato.Implementation.Expressions;

public abstract class ExpressionCombo implements Expression {
    protected final Expression exp1;
    protected final Expression exp2;
    protected char sign;

    public ExpressionCombo(Expression exp1, Expression exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public String toString() {
        boolean brace1 = exp1.getPriority() >= this.getPriority();
        String ans = brace1 ? "(" + exp1.toString() + ")" : exp1.toString();
        ans += sign;
        boolean brace2 = exp2.getPriority() >= this.getPriority();
        ans += brace2 ? "(" + exp2.toString() + ")" : exp2.toString();
        return ans;
    }
}
