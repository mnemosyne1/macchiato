package Macchiato;

import Macchiato.Implementation.BlockManagement;
import Macchiato.Implementation.Conditions.*;
import Macchiato.Implementation.Expressions.*;
import Macchiato.Implementation.Instructions.*;

import java.util.Scanner;

public interface Macchiato {
    /**LAUNCHING PROGRAM*/
    static void execute(Block program) {
        try {
            while (!program.isFinished()) program.executeOne();
        } catch (Instruction.InstructionException | Instruction.ProgramFinishedException ex) {
            System.out.println(ex.getMessage());
            BlockManagement.closeAll();
        } finally {
            program.undo();
        }
    }

    static void debug(Block program) {
        Scanner input = new Scanner(System.in);
        while (true) {
            String request = input.next();
            switch (request) {
                case "e" -> {
                    BlockManagement.closeAll();
                    program.undo();
                    return;
                }
                case "c" -> {
                    try {
                        if (program.isFinished()) throw new Instruction.ProgramFinishedException();
                        while (!program.isFinished()) program.executeOne();
                        program.undo();
                        return;
                    } catch (Instruction.InstructionException ex) {
                        System.out.println(ex.getMessage());
                        BlockManagement.closeAll();
                        program.undo();
                        return;
                    } catch (Instruction.ProgramFinishedException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                case "s" -> {
                    int steps = input.nextInt();
                    try {
                        for (int i = 0; i < steps; i++) program.executeOne();
                        if (!program.isFinished()) System.out.println(program.toString());
                        else throw new Instruction.ProgramFinishedException();
                    } catch (Instruction.InstructionException | Instruction.ProgramFinishedException ex) {
                        System.out.println(ex.getMessage());
                        BlockManagement.closeAll();
                        program.finish();
                    }
                }
                case "d" -> {
                    int layers = input.nextInt();
                    try {
                        System.out.println(BlockManagement.printValues(layers));
                    } catch (BlockManagement.BlockPrintException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                default -> System.out.println("Błędna instrukcja debuggera.");
            }
        }
    }

    /**INSTRUCTIONS*/
    /*static Instruction newBlock(ArrayList<Instruction> list) {
        return new Block(list);
    }

    static Instruction newIf(Condition cond, ArrayList<Instruction> list,
                             ArrayList<Instruction> elselist) {
        return new If(cond, list, elselist);
    }

    static Instruction newIf(Condition cond, ArrayList<Instruction> list) {
        return new If(cond, list);
    }

    static Instruction newFor(char variable, Expression limit, ArrayList<Instruction> list) {
        return new For(variable, limit, list);
    }*/ // DEPRECATED

    static Instruction newPrint(Expression e) {
        return new Print(e);
    }

    static Instruction newInitialisation(char c, Expression e) {
        return new Initialisation(c, e);
    }

    static Instruction newAssignment(char c, Expression e) {
        return new Assignment(c, e);
    }

    /**EXPRESSIONS*/

    /*static Expression newConst(int value) {
        return new ExpressionConst(value);
    } DEPRECATED */

    static Expression newVariableExp(char c) {
        return new ExpressionVariable(c);
    }

    /*static Expression newSum(Expression a, Expression b) {
        return new Sum(a, b);
    }

    static Expression newDifference(Expression a, Expression b) {
        return new Difference(a, b);
    }

    static Expression newProduct(Expression a, Expression b) {
        return new Product(a, b);
    }

    static Expression newDivision(Expression a, Expression b) {
        return new Division(a, b);
    }

    static Expression newModulo(Expression a, Expression b) {
        return new Modulo(a, b);
    } DEPRECATED */

    /**CONDITIONS*/
    static Condition newEqual(Expression a, Expression b) {
        return new ConditionEqual(a, b);
    }

    static Condition newDifferent(Expression a, Expression b) {
        return new ConditionDifferent(a, b);
    }

    static Condition newLess(Expression a, Expression b) {
        return new ConditionLess(a, b);
    }

    static Condition newMore(Expression a, Expression b) {
        return new ConditionMore(a, b);
    }

    static Condition newNotMore(Expression a, Expression b) {
        return new ConditionNotMore(a, b);
    }

    static Condition newNotLess(Expression a, Expression b) {
        return new ConditionNotLess(a, b);
    }
}
