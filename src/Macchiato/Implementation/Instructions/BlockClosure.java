package Macchiato.Implementation.Instructions;

import Macchiato.Implementation.BlockManagement;

public class BlockClosure extends InstructionSingle {
    @Override
    public void executeOne() {
        wasCalled = true;
        BlockManagement.endBlock();
    }

    @Override
    public String toString() {
        return "end block";
    }
}
