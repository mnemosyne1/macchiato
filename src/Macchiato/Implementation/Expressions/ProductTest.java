package Macchiato.Implementation.Expressions;

import Macchiato.Implementation.BlockManagement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    @Test
    void of() {
        Product p = Product.of(Const.of(5), Const.of(1));
        try {
            assertEquals(p.evaluate(), 5);
        } catch (Exception e){
            fail();
        }
    }
    @Test
    void of2() {
        BlockManagement.newBlock();
        Product p = Product.of(Const.of(5), Variable.named('x'));
        assertThrows(Expression.ExpressionEvaluationException.class, ()->p.evaluate());
        BlockManagement.endBlock();
    }

    @Test
    void testToString() {
        Product p = Product.of(Const.of(5), Variable.named('x'));
        assertEquals(p.toString(), "5*x");
    }
}