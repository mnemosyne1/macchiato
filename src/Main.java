import Macchiato.Implementation.Instruction;
import Macchiato.Macchiato;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Sprawdzanie wyjątku dzielenia przez zero z debuggerem");
        dziel0(true);
        System.out.println("Test przykładowy bez debuggera");
        test0 (false);
        System.out.println("Test przykładowy z debuggerem");
        test0(true);
        System.out.println("Obliczanie liczby Fibonacciego z debuggerem");
        fibonacci(true, 5);
        System.out.println("Obliczanie liczby Fibonacciego bez debuggera");
        fibonacci(false, 30);
        System.out.println("Sprawdzanie wyjątku dzielenia przez zero");
        dziel0(false);
    }

    private static void test0(boolean debug) {
        ArrayList<Instruction> program = new ArrayList<>();
        program.add(Macchiato.newInitialisation('n', Macchiato.newConst(30)));
        ArrayList<Instruction> inFor1 = new ArrayList<>();
        inFor1.add(Macchiato.newInitialisation('p', Macchiato.newConst(1)));
        inFor1.add(Macchiato.newAssignment('k', Macchiato.newSum(Macchiato.newVariableExp('k'), Macchiato.newConst(2))));
        ArrayList<Instruction> inFor2 = new ArrayList<>();
        inFor2.add(Macchiato.newAssignment('i', Macchiato.newSum(Macchiato.newVariableExp('i'), Macchiato.newConst(2))));
        ArrayList<Instruction> inIf1 = new ArrayList<>();
        inIf1.add(Macchiato.newAssignment('p', Macchiato.newConst(0)));
        inFor2.add(Macchiato.newIf(Macchiato.newEqual(Macchiato.newModulo(Macchiato.newVariableExp('k'), Macchiato.newVariableExp('i')), Macchiato.newConst(0)), inIf1));
        inFor1.add(Macchiato.newFor('i', Macchiato.newDifference(Macchiato.newVariableExp('k'), Macchiato.newConst(2)), inFor2));
        ArrayList<Instruction> inIf2 = new ArrayList<>();
        inIf2.add(Macchiato.newPrint(Macchiato.newVariableExp('k')));
        inFor1.add(Macchiato.newIf(Macchiato.newEqual(Macchiato.newVariableExp('p'), Macchiato.newConst(1)), inIf2));
        program.add(Macchiato.newFor('k', Macchiato.newDifference(Macchiato.newVariableExp('n'), Macchiato.newConst(1)), inFor1));
        if (debug) Macchiato.debug(program);
        else Macchiato.execute(program);
    }

    private static void templateTest(boolean debug) {
        ArrayList<Instruction> program = new ArrayList<>();
        /*
         * tu wstawiać na listy instrukcje zgodne z interfejsem z pliku Macchiato
         * tj. Macchiato.new___ (odpowiednie argumenty)
         */
        if (debug) Macchiato.debug(program);
        else Macchiato.execute(program);
    }

    private static void fibonacci(boolean debug, int which) {
        /*int a = 1;
        int b = 1;
        for (int i = 2; i <= which; i++){ // for (int i = 0; i <= which - 2; i++)
            int c = a + b;
            a = b;
            b = c;
        }
        print (b);
        */
        ArrayList<Instruction> program = new ArrayList<>();
        program.add(Macchiato.newInitialisation('a', Macchiato.newConst(1)));
        program.add(Macchiato.newInitialisation('b', Macchiato.newConst(1)));
        ArrayList<Instruction> inFor1 = new ArrayList<>();
        inFor1.add(Macchiato.newInitialisation('c', Macchiato.newSum(Macchiato.newVariableExp('a'), Macchiato.newVariableExp('b'))));
        inFor1.add(Macchiato.newAssignment('a', Macchiato.newVariableExp('b')));
        inFor1.add(Macchiato.newAssignment('b', Macchiato.newVariableExp('c')));
        program.add(Macchiato.newFor('i', Macchiato.newConst(which - 2), inFor1));
        program.add(Macchiato.newPrint(Macchiato.newVariableExp('b')));
        if (debug) Macchiato.debug(program);
        else Macchiato.execute(program);
    }

    private static void dziel0(boolean debug) {
        ArrayList<Instruction> program = new ArrayList<>();
        program.add(Macchiato.newInitialisation('z', Macchiato.newDivision(Macchiato.newConst(1), Macchiato.newConst(0))));
        if (debug) Macchiato.debug(program);
        else Macchiato.execute(program);
    }

    private void invalidVariable(boolean debug) {
        ArrayList<Instruction> program = new ArrayList<>();
        program.add(Macchiato.newInitialisation('A', Macchiato.newConst(1)));
        if (debug) Macchiato.debug(program);
        else Macchiato.execute(program);
    }

    private static void emptyIf(boolean debug) {
        ArrayList<Instruction> program = new ArrayList<>();
        ArrayList<Instruction> inIf2 = new ArrayList<>();
        inIf2.add(Macchiato.newPrint(Macchiato.newConst(0)));
        ArrayList<Instruction> inIf1 = new ArrayList<>();
        inIf1.add(Macchiato.newIf(Macchiato.newMore(Macchiato.newConst(1), Macchiato.newConst(2)), inIf2));
        program.add(Macchiato.newIf(Macchiato.newDifferent(Macchiato.newConst(1), Macchiato.newConst(2)), new ArrayList<>(), inIf1));
        if (debug) Macchiato.debug(program);
        else Macchiato.execute(program);
    }
}