package Macchiato.Implementation.Instructions;

import Macchiato.Implementation.Expressions.Const;
import Macchiato.Implementation.Expressions.Variable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssignmentTest {
    @Test
    void test() {
        try {
            final int x = 4;
            new BlockOpening().executeOne();
            Initialisation.of('z', Const.of(0)).executeOne();
            Assignment.to('z', Const.of(x)).executeOne();
            assertEquals(x, Variable.named('z').evaluate());
        } catch (Exception ex) {
            fail();
        }
    }
}