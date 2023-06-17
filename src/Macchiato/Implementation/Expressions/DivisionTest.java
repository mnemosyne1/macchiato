package Macchiato.Implementation.Expressions;

import Macchiato.Implementation.BlockManagement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DivisionTest {

    @Test
    void of() {
        Division d = Division.of(Const.of(5), Const.of(2));
        try {
            assertEquals(d.evaluate(), 2);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void of2() {
        BlockManagement.newBlock();

        Division d = Division.of(Const.of(5), Variable.named('x'));
        assertThrows(Expression.ExpressionEvaluationException.class, () -> d.evaluate());
        BlockManagement.endBlock();
    }

    @Test
    void of3() {
        BlockManagement.newBlock();

        Division d = Division.of(Const.of(5), Const.of(0));
        assertThrows(Expression.ExpressionEvaluationException.class, () -> d.evaluate());
        BlockManagement.endBlock();
    }

    @Test
    void testToString() {
        Division d = Division.of(Const.of(5), Variable.named('x'));
        assertEquals(d.toString(), "5/x");
    }
}