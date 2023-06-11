package Macchiato.Implementation.Instructions;

import Macchiato.Implementation.BlockManagement;

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
