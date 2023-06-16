package Macchiato;

import Macchiato.Implementation.BlockManagement;
import Macchiato.Implementation.Conditions.*;
import Macchiato.Implementation.Expressions.*;
import Macchiato.Implementation.Instructions.*;

import java.io.*;
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
                case "m" -> {
                    // TODO: procedure declarations
                    // to be done when procedures are implemented
                    String path = input.next();
                    try {
                        FileWriter writer = new FileWriter(path);
                        writer.write(BlockManagement.printValues(0));
                        writer.close();
                    } catch (IOException e){
                        System.out.println("Błąd przy operacji na pliku!");
                    } catch (BlockManagement.BlockPrintException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                default -> System.out.println("Błędna instrukcja debuggera.");
            }
        }
    }

    /**INSTRUCTIONS*/
    static Instruction newPrint(Expression e) {
        return new Print(e);
    }

    static Instruction newInitialisation(char c, Expression e) {
        return new Initialisation(c, e);
    }

    static Instruction newAssignment(char c, Expression e) {
        return new Assignment(c, e);
    }

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
