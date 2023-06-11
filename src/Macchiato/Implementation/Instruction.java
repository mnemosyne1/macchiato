package Macchiato.Implementation;

public interface Instruction {
    class InstructionException extends Exception {
        private final Instruction bad;

        public InstructionException(Instruction bad) {
            this.bad = bad;
        }

        @Override
        public String getMessage() {
            String ans = "Błąd w instrukcji: " + bad.toString() + '\n';
            ans += BlockManagement.printValues();
            return ans;
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
