package Macchiato.Implementation.Expressions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstTest {

    @Test
    void of() {
        Const c = Const.of(5);
        assertEquals(c.evaluate(), 5);
    }

    @Test
    void testToString() {
        Const c = Const.of(5);
        assertEquals(c.toString(), "5");
    }
}