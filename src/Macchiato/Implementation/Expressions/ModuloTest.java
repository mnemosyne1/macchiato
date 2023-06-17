package Macchiato.Implementation.Expressions;

import Macchiato.Implementation.BlockManagement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModuloTest {
    @Test
    void of() {
        Modulo d = Modulo.of(Const.of(5), Const.of(2));
        try {
            assertEquals(d.evaluate(), 1);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void of2() {
        BlockManagement.newBlock();

        Modulo d = Modulo.of(Const.of(5), Variable.named('x'));
        assertThrows(Expression.ExpressionEvaluationException.class, () -> d.evaluate());
        BlockManagement.endBlock();
    }

    @Test
    void of3() {
        BlockManagement.newBlock();

        Modulo d = Modulo.of(Const.of(5), Const.of(0));
        assertThrows(Expression.ExpressionEvaluationException.class, () -> d.evaluate());
        BlockManagement.endBlock();
    }

    @Test
    void testToString() {
        Modulo d = Modulo.of(Const.of(5), Variable.named('x'));
        assertEquals(d.toString(), "5%x");
    }
}