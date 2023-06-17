import Macchiato.Implementation.Conditions.Condition;
import Macchiato.Implementation.Expressions.*;
import Macchiato.Implementation.Instructions.Block;
import Macchiato.Macchiato;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bardzo prosty test działania procedur z debuggerem");
        basicProcedureTest(true);
        System.out.println("Test przykładowy Macchiato 1.0 bez debuggera");
        test0 (false);
        System.out.println("Test przykładowy Macchiato 1.0 z debuggerem");
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

    private static void basicProcedureTest (boolean debug){
        var program = new Block.BlockBuilder()
                .declareProcedure("aaa", List.of('a'),
                        new Block.BlockBuilder()
                                .declareProcedure("bbb", List.of(), new Block.BlockBuilder()
                                        .declareProcedure("aaa", List.of(), new Block.BlockBuilder()))
                                .print(Variable.named('a'))
                                .invoke("bbb", List.of())
                )
                .initialiseVariable('a', Const.of(2))
                .initialiseVariable('b', Const.of(2))
                .invoke("aaa", List.of(Variable.named('b'))) // prints 2
                //.invoke("aab", List.of(Const.of(1))) // error
                .build();
        if (debug) Macchiato.debug(program);
        else Macchiato.execute(program);
    }
}