package Macchiato.Implementation.Instructions;

import Macchiato.Implementation.Conditions.Condition;
import Macchiato.Implementation.Expressions.Expression;

import java.util.ArrayList;
import java.util.List;

public class Block implements Instruction {
    protected int index = 0;
    protected final ArrayList<Instruction> list;

    public static class BlockBuilder {
        protected ArrayList<Instruction> list;

        public BlockBuilder() {
            list = new ArrayList<>();
            list.add(new BlockOpening());
        }

        public Block build() {
            return new Block(this);
        }

        public BlockBuilder initialiseVariable(char c, Expression e) {
            list.add(Initialisation.of(c, e));
            return this;
        }

        public BlockBuilder assignVariable(char c, Expression e) {
            list.add(Assignment.to(c, e));
            return this;
        }

        public BlockBuilder print(Expression e) {
            list.add(Print.the(e));
            return this;
        }

        public BlockBuilder block(BlockBuilder b) {
            /*
            everything we need, for example opening and closure of block
            is guaranteed by b or constructor, so we don't have to do anything
             */
            list.add(new Block(b));
            return this;
        }

        public BlockBuilder newfor(char c, Expression e, BlockBuilder f) {
            list.add(new For(c, e, f));
            return this;
        }

        public BlockBuilder newif(Condition c, BlockBuilder f) {
            list.add(new If(c, f));
            return this;
        }

        public BlockBuilder declareProcedure(String name, List<Character> arguments, BlockBuilder f) {
            list.add(new Procedure(name, arguments, f));
            return this;
        }

        public BlockBuilder invoke(String name, List<Expression> arguments) {
            list.add(new ProcedureCall(name, arguments));
            return this;
        }
    }

    protected Block(BlockBuilder b) {
        list = new ArrayList<>();
        list.addAll(b.list);
        list.add(new BlockClosure());
    }

    public void finish() {
        index = list.size();
    }

    @Override
    public void executeOne() throws InstructionException, ProgramFinishedException {
        if (index >= list.size()) throw new ProgramFinishedException();
        list.get(index).executeOne();
        if (list.get(index).isFinished()) index++;
    }

    @Override
    public boolean isFinished() {
        return index >= list.size();
    }

    @Override
    public void undo() {
        index = 0;
        for (Instruction i : list)
            i.undo();
    }

    @Override
    public String toString() {
        if (isFinished()) return new BlockClosure().toString();
        return list.get(index).toString();
    }
}
