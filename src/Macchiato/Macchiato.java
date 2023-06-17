package Macchiato;

import Macchiato.Implementation.BlockManagement;
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
                    String path = input.next();
                    try {
                        FileWriter writer = new FileWriter(path);
                        writer.write(BlockManagement.printProcedures());
                        writer.write(BlockManagement.printValues(0));
                        writer.close();
                    } catch (IOException e){
                        System.out.println("Błąd przy operacji na pliku!");
                        System.out.println(e.getMessage());
                    } catch (BlockManagement.BlockPrintException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                default -> System.out.println("Błędna instrukcja debuggera.");
            }
        }
    }
}
