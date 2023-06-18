package Macchiato.Implementation.Expressions;

import Macchiato.Implementation.BlockManagement;

public class Variable implements Expression {
    private final char variable;

    // public because it's legacy constructor from Macchiato 1.0
    public Variable(char variable) {
        this.variable = variable;
    }

    public static Variable named(char variable) {
        return new Variable(variable);
    }

    @Override
    public int evaluate() throws Expression.ExpressionEvaluationException {
        try {
            return BlockManagement.getVariable(variable);
        } catch (BlockManagement.BlockOperationException ex) {
            throw new Expression.ExpressionEvaluationException();
        }
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public String toString() {
        return String.valueOf(variable);
    }
}
