package Macchiato.Implementation.Instructions;

import Macchiato.Implementation.Expressions.Expression;

public class Print extends InstructionSingle {
    private final Expression e;

    private Print(Expression e) {
        this.e = e;
    }

    public static Print the(Expression e) {
        return new Print(e);
    }

    @Override
    public void executeOne() throws InstructionException {
        wasCalled = true;
        try {
            int x = e.evaluate();
            System.out.println(x);
        } catch (Expression.ExpressionEvaluationException ex) {
            throw new InstructionException(this);
        }
    }

    @Override
    public String toString() {
        return "print " + e.toString();
    }
}
