package Macchiato.Implementation.Conditions;

import Macchiato.Implementation.Expressions.Const;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConditionTest {

    @Test
    void checkCondition() {
        try {
            boolean check;
            check = Condition.newDifferent(Const.of(1), Const.of(2)).checkCondition();
            assertTrue (check);
            check = Condition.newEqual(Const.of(1), Const.of(2)).checkCondition();
            assertTrue (!check);
            check = Condition.newLess(Const.of(1), Const.of(2)).checkCondition();
            assertTrue (check);
            check = Condition.newMore(Const.of(1), Const.of(2)).checkCondition();
            assertTrue (!check);
            check = Condition.newNotLess(Const.of(1), Const.of(2)).checkCondition();
            assertTrue (!check);
            check = Condition.newNotMore(Const.of(1), Const.of(2)).checkCondition();
            assertTrue (check);

        }
        catch (Exception e){
            fail();
        }
    }
}