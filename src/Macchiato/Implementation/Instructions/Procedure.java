package Macchiato.Implementation.Instructions;

import Macchiato.Implementation.BlockManagement;
import Macchiato.Implementation.Expressions.Const;
import Macchiato.Implementation.Expressions.Expression;

import java.util.ArrayList;
import java.util.List;

public class Procedure extends InstructionSingle {
    private final String name;
    private final List<Character> args;
    private final Block.BlockBuilder b;
    protected Procedure(String name, List<Character> args, Block.BlockBuilder b){
        this.name = name;
        this.args = args;
        this.b = b;
    }
    protected int argCount (){
        return args.size();
    }
    protected Block.BlockBuilder getBB (){
        return b;
    }
    protected void initParams (List<Expression> expressions) throws InstructionException {
        assert (expressions.size() == args.size());
        List<Const> values = new ArrayList<>();
        for (int i = 0; i < args.size(); i++){
            try {
                values.add(Const.of(expressions.get(i).evaluate()));
            } catch (Expression.ExpressionEvaluationException ex){
                throw new InstructionException(this);
            }
        }
        for (int i = 0; i < args.size(); i++){
            try {
                Initialisation.of(args.get(i), values.get(i)).executeOne();
            } catch (InstructionException e) {
                throw new InstructionException(this);
            }
        }
    }

    @Override
    public void executeOne() throws InstructionException {
        try {
            BlockManagement.addProcedure(name, this);
        } catch (BlockManagement.ProcedureException e) {
            throw new InstructionException(this);
        }
        wasCalled = true;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("proc ");
        s.append(procName());
        return s.toString();
    }

    public String procName (){
        StringBuilder ans = new StringBuilder();
        ans.append(name);
        ans.append(" (");
        for (int i = 0; i < argCount(); i++){
            if (i > 0) ans.append(", ");
            ans.append(args.get(i));
        }
        ans.append(")");
        return ans.toString();
    }
}
