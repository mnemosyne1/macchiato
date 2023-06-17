package Macchiato.Implementation.Instructions;

import Macchiato.Implementation.BlockManagement;
import Macchiato.Implementation.Expressions.Expression;

public class Assignment extends InstructionVariable {
    private Assignment(char variable, Expression value) {
        super(variable, value);
    }

    public static Assignment to(char variable, Expression value) {
        return new Assignment(variable, value);
    }

    @Override
    public void executeOne() throws InstructionException {
        wasCalled = true;
        try {
            BlockManagement.setVariable(variable, value);
        } catch (BlockManagement.BlockOperationException ex) {
            throw new InstructionException(this);
        }
    }

    @Override
    public String toString() {
        return variable + " := " + value.toString();
    }
}
