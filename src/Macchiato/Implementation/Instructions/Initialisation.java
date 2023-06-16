package Macchiato.Implementation.Instructions;

import Macchiato.Implementation.BlockManagement;
import Macchiato.Implementation.Expressions.Expression;

public class Initialisation extends InstructionVariable {
    private Initialisation(char variable, Expression value) {
        super(variable, value);
    }
    public static Initialisation of (char variable, Expression value){
        return new Initialisation(variable, value);
    }

    @Override
    public void executeOne() throws InstructionException {
        wasCalled = true;
        try {
            BlockManagement.initialise(variable, value);
        } catch (BlockManagement.BlockOperationException ex) {
            throw new InstructionException(this);
        }
    }

    @Override
    public String toString() {
        return "var " + variable + " " + value.toString();
    }
}
