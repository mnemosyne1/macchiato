package Macchiato.Implementation.Expressions;

import Macchiato.Implementation.BlockManagement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DifferenceTest {

    @Test
    void of() {
        Difference d = Difference.of(Const.of(5), Const.of(1));
        try {
            assertEquals(d.evaluate(), 4);
        } catch (Exception e){
            fail();
        }
    }
    @Test
    void of2() {
        BlockManagement.newBlock();
        Difference d = Difference.of(Const.of(5), Variable.named('x'));
        assertThrows(Expression.ExpressionEvaluationException.class, ()->d.evaluate());
        BlockManagement.endBlock();
    }

    @Test
    void testToString() {
        Difference d = Difference.of(Const.of(5), Variable.named('x'));
        assertEquals(d.toString(), "5-x");
    }
}