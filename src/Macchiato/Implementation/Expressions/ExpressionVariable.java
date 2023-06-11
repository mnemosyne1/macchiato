package Macchiato.Implementation.Expressions;

import Macchiato.Implementation.BlockManagement;

public class ExpressionVariable implements Expression {
    private final char variable;

    public ExpressionVariable(char variable) {
        this.variable = variable;
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
