package Macchiato.Implementation.Instructions;

import Macchiato.Implementation.Conditions.Condition;
import Macchiato.Implementation.Expressions.Const;
import Macchiato.Implementation.Expressions.Sum;
import Macchiato.Implementation.Expressions.Variable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IfTest {
    @Test
    void test1 () { // if
        final int x = 1;
        try {
            new BlockOpening().executeOne();
            Initialisation.of('z', Const.of(x)).executeOne();
            If f = new If(Condition.newLess(Variable.named('z'), Const.of (3)), new Block.BlockBuilder()
                    .assignVariable('z', Sum.of(Variable.named('z'), Const.of(1))));
            while (!f.isFinished()) f.executeOne();
            assertEquals((x < 3 ? x + 1 : x), Variable.named('z').evaluate());
        } catch (Exception ex){
            fail();
        }
    }

    @Test
    void test2 () { // if + else
        final int x = 4;
        try {
            new BlockOpening().executeOne();
            Initialisation.of('z', Const.of(x)).executeOne();
            If f = new If(Condition.newNotMore(Variable.named('z'), Const.of (3)), new Block.BlockBuilder()
                    .assignVariable('z', Sum.of(Variable.named('z'), Const.of(1))), new Block.BlockBuilder()
                    .assignVariable('z', Const.of(-1)));
            while (!f.isFinished()) f.executeOne();
            assertEquals((x <= 3 ? x + 1 : -1), Variable.named('z').evaluate());
        } catch (Exception ex){
            fail();
        }
    }
}