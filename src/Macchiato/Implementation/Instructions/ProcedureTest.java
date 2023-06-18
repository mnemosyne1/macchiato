package Macchiato.Implementation.Instructions;

import Macchiato.Implementation.BlockManagement;
import Macchiato.Implementation.Expressions.Const;
import Macchiato.Implementation.Expressions.Variable;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProcedureTest {
    @Test
    void proc() {
        var program = new Block.BlockBuilder()
                .declareProcedure("aaa", List.of('a'),
                        new Block.BlockBuilder().print(Variable.named('a')))
                .initialiseVariable('a', Const.of(2))
                .initialiseVariable('b', Const.of(2))
                .invoke("aaa", List.of(Const.of(1))) // prints 1
                .invoke("aaa", List.of(Variable.named('b'))) // prints 2
                //.invoke("aaa", List.of(Const.of(1), Const.of(2))) // error
                //.invoke("aaa", List.of()) // error
                .invoke("aab", List.of(Const.of(1))) // error
                .build();
        boolean ok = false;
        try {
            while (!program.isFinished()) program.executeOne();
        } catch (Instruction.InstructionException | Instruction.ProgramFinishedException ex) {
            ok = true;
            System.out.println(ex.getMessage());
            BlockManagement.closeAll();
        } finally {
            program.undo();
            assertTrue(ok);
        }
    }
}