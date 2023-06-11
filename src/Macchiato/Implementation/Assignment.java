package Macchiato.Implementation;

public class Assignment extends InstructionVariable {
    public Assignment(char variable, Expression value) {
        super(variable, value);
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
