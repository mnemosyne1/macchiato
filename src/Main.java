import Macchiato.Implementation.Conditions.Condition;
import Macchiato.Implementation.Expressions.*;
import Macchiato.Implementation.Instructions.Block;
import Macchiato.Macchiato;

public class Main {
    public static void main(String[] args) {
        System.out.println("Test przykładowy bez debuggera");
        test0 (false);
        System.out.println("Test przykładowy z debuggerem");
        test0(true);
    }

    private static void test0 (boolean debug){
        // example test from Macchiato 1.0
        var program = new Block.BlockBuilder()
            .initialiseVariable('n', Const.of(30))
            .newfor('k', Difference.of(Variable.named('n'), Const.of(1)),
                new Block.BlockBuilder()
                    .initialiseVariable('p', Const.of(1))
                    .assignVariable('k', Sum.of(Variable.named('k'), Const.of(2)))
                    .newfor('i', Difference.of(Variable.named('k'), Const.of(2)),
                        new Block.BlockBuilder()
                            .assignVariable('i', Sum.of(Variable.named('i'), Const.of(2)))
                            .newif (Condition.newEqual(Modulo.of(Variable.named('k'),
                                Variable.named('i')), Const.of(0)), new Block.BlockBuilder()
                                .assignVariable('p', Const.of(0))
                            )
                    )
                    .newif(Condition.newEqual(Variable.named('p'), Const.of(1)),
                            new Block.BlockBuilder().print(Variable.named('k'))
                    )
            )
            .build();
        if (debug) Macchiato.debug(program);
        else Macchiato.execute(program);
    }
}