package Macchiato.Implementation.Instructions;

import Macchiato.Implementation.BlockManagement;
import Macchiato.Implementation.Expressions.Expression;

import java.util.List;

public class ProcedureCall implements Instruction {
    /* this implementation does not support recurrence */
    private final String name;
    private final List<Expression> args;
    private boolean started;
    private Procedure declaration;
    private Block instructions;
    protected ProcedureCall(String procedure, List<Expression> args) {
        this.args = args;
        name = procedure;
        started = false;
    }

    @Override
    public void executeOne() throws InstructionException, ProgramFinishedException {
        if (isFinished()) undo();
        if (instructions == null){
            try {
                declaration = BlockManagement.getProcedure(name);
            } catch (BlockManagement.ProcedureException ex){
                throw new InstructionException(this, "Nie ma takiej procedury!");
            }
            if (declaration.argCount() != args.size())
                throw new InstructionException(this, "Zła liczba argumentów!");
            instructions = declaration.getBB().build();
        }
        instructions.executeOne();
        if (!started){
            declaration.initParams(args);
            started = true;
        }
    }

    @Override
    public boolean isFinished() {
        if (instructions == null) return false;
        return instructions.isFinished();
    }

    @Override
    public void undo() {
        if (instructions != null) instructions.undo();
        started = false;
    }

    @Override
    public String toString() {
        if (instructions == null){
            StringBuilder ans = new StringBuilder(name);
            ans.append(" (");
            for (int i = 0; i < args.size(); i++){
                if (i > 0) ans.append(", ");
                ans.append(args.get(i).toString());
            }
            ans.append(")");
            return ans.toString();
        }
        return instructions.toString();
    }
}
