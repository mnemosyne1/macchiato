package Macchiato.Implementation.Instructions;

import Macchiato.Implementation.BlockManagement;

public interface Instruction {
    class InstructionException extends Exception {
        private final Instruction bad;
        private String message;

        public InstructionException(Instruction bad) {
            this.bad = bad;
        }
        public InstructionException(Instruction bad, String message) {
            this.bad = bad;
            this.message = message;
        }

        @Override
        public String getMessage() {
            StringBuilder ans = new StringBuilder ("Błąd w instrukcji: " + bad.toString() + '\n');
            if (message != null){
                ans.append (message);
                ans.append("\n");
            }
            ans.append(BlockManagement.printValues());
            return ans.toString();
        }
    }

    class ProgramFinishedException extends Exception {
        @Override
        public String getMessage() {
            return "Program już się zakończył.";
        }
    }

    void executeOne() throws InstructionException, ProgramFinishedException;

    boolean isFinished();

    void undo();
}
