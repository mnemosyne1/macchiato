package Macchiato.Implementation.Instructions;

import Macchiato.Implementation.Expressions.Expression;

public abstract class InstructionVariable extends InstructionSingle {
    protected final char variable;
    protected final Expression value;

    public InstructionVariable(char variable, Expression value) {
        this.variable = variable;
        this.value = value;
    }
}
