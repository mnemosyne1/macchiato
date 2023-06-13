import Macchiato.Implementation.Instructions.Block;
import Macchiato.Macchiato;

import java.util.ArrayList;

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
            .initialiseVariable('n', Macchiato.newConst(30))
            .newfor('k', Macchiato.newDifference(
                    Macchiato.newVariableExp('n'), Macchiato.newConst(1)),
                new Block.BlockBuilder()
                    .initialiseVariable('p', Macchiato.newConst(1))
                    .assignVariable('k', Macchiato.newSum(
                        Macchiato.newVariableExp('k'), Macchiato.newConst(2)
                    ))
                    .newfor('i', Macchiato.newDifference(
                        Macchiato.newVariableExp('k'), Macchiato.newConst(2)),
                        new Block.BlockBuilder()
                            .assignVariable('i', Macchiato.newSum(Macchiato.newVariableExp('i'), Macchiato.newConst(2)))
                            .newif (Macchiato.newEqual(Macchiato.newModulo(Macchiato.newVariableExp('k'), Macchiato.newVariableExp('i')), Macchiato.newConst(0)), new Block.BlockBuilder()
                                .assignVariable('p', Macchiato.newConst(0))
                            )
                    )
                    .newif(Macchiato.newEqual(Macchiato.newVariableExp('p'), Macchiato.newConst(1)), new Block.BlockBuilder().print(Macchiato.newVariableExp('k'))
                    )
            )
            .build();
        if (debug) Macchiato.debug(program);
        else Macchiato.execute(program);
    }
}