import Macchiato.Implementation.Expressions.Const;
import Macchiato.Implementation.Expressions.Difference;
import Macchiato.Implementation.Expressions.Modulo;
import Macchiato.Implementation.Expressions.Sum;
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
            .newfor('k', Difference.of(
                    Macchiato.newVariableExp('n'), Const.of(1)),
                new Block.BlockBuilder()
                    .initialiseVariable('p', Const.of(1))
                    .assignVariable('k', Sum.of(Macchiato.newVariableExp('k'), Const.of(2)))
                    .newfor('i', Difference.of(
                        Macchiato.newVariableExp('k'), Const.of(2)),
                        new Block.BlockBuilder()
                            .assignVariable('i', Sum.of(Macchiato.newVariableExp('i'), Const.of(2)))
                            .newif (Macchiato.newEqual(Modulo.of(Macchiato.newVariableExp('k'), Macchiato.newVariableExp('i')), Const.of(0)), new Block.BlockBuilder()
                                .assignVariable('p', Const.of(0))
                            )
                    )
                    .newif(Macchiato.newEqual(Macchiato.newVariableExp('p'), Const.of(1)),
                            new Block.BlockBuilder().print(Macchiato.newVariableExp('k'))
                    )
            )
            .build();
        if (debug) Macchiato.debug(program);
        else Macchiato.execute(program);
    }
}