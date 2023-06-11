package Macchiato.Implementation.Instructions;

import java.util.ArrayList;

public class Block implements Instruction {
    protected int index = 0;
    protected ArrayList<Instruction> list;

    public Block() {
    }

    public Block(ArrayList<Instruction> list) {
        ArrayList<Instruction> myList = new ArrayList<>();
        myList.add(new BlockOpening());
        myList.addAll(list);
        myList.add(new BlockClosure());
        this.list = myList;
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
