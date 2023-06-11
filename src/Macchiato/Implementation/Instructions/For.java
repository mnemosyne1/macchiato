package Macchiato.Implementation.Instructions;

import Macchiato.Implementation.Expressions.Expression;
import Macchiato.Implementation.Expressions.ExpressionConst;

import java.util.ArrayList;

public class For extends Block {
    private final char variable;
    private final Expression limit;
    private Integer last;
    private int whichIteration;

    public For(char variable, Expression limit, ArrayList<Instruction> list) {
        super(list);
        this.variable = variable;
        this.limit = limit;
    }

    @Override
    public void executeOne() throws InstructionException, ProgramFinishedException {
        try {
            if (index == 0) { //before first step in this iteration
                last = limit.evaluate();
                if (last <= 0) return;
            }
            if (whichIteration >= last) throw new ProgramFinishedException();
            list.get(index).executeOne();
            if (index == 0)
                new Initialisation(variable, new ExpressionConst(whichIteration)).executeOne();
            if (list.get(index).isFinished()) {
                index++;
                if (index == list.size()) {
                    whichIteration++;
                    index = 0;
                    for (Instruction i : list) i.undo();
                }
            }
        } catch (Expression.ExpressionEvaluationException ex) {
            throw new InstructionException(this);
        }
    }

    @Override
    public boolean isFinished() {
        return whichIteration >= last;
    }

    @Override
    public void undo() {
        super.undo();
        whichIteration = 0;
    }

    @Override
    public String toString() {
        if (index == 0) return "for " + variable + " " + limit.toString();
        return list.get(index).toString();
    }
}
