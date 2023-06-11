package Macchiato.Implementation;

public class BlockOpening extends InstructionSingle {
    @Override
    public void executeOne() {
        wasCalled = true;
        BlockManagement.newBlock();
    }

    @Override
    public String toString() {
        return "begin block";
    }
}
