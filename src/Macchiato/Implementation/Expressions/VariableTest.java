package Macchiato.Implementation.Expressions;

import Macchiato.Implementation.BlockManagement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VariableTest {
    @Test
    void named() {
        BlockManagement.newBlock();
        Variable v = Variable.named('a');
        assertEquals(v.toString(), "a");
        BlockManagement.endBlock();
    }
}