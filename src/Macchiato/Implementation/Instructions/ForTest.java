package Macchiato.Implementation.Instructions;

import Macchiato.Implementation.Expressions.Const;
import Macchiato.Implementation.Expressions.Sum;
import Macchiato.Implementation.Expressions.Variable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ForTest {
    @Test
    void test () {
        final int x = 4;
        try {
            new BlockOpening().executeOne();
            Initialisation.of('z', Const.of(0)).executeOne();
            For f = new For('i', Sum.of(Variable.named('z'), Const.of(x)), new Block.BlockBuilder()
                    .assignVariable('z', Sum.of(Variable.named('z'), Const.of(1))));
            while (!f.isFinished()) f.executeOne();
            assertEquals(x, Variable.named('z').evaluate());
        } catch (Exception ex){
            fail();
        }
    }
}