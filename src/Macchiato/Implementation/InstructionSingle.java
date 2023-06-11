package Macchiato.Implementation;

public abstract class InstructionSingle implements Instruction {
    protected boolean wasCalled = false;

    @Override
    public boolean isFinished() {
        return wasCalled;
    }

    @Override
    public void undo() {
        wasCalled = false;
    }
}
