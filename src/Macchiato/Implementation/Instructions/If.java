package Macchiato.Implementation.Instructions;

import Macchiato.Implementation.Conditions.Condition;

import java.util.ArrayList;

public class If extends Block {
    private final Condition condition;
    private final ArrayList<Instruction> elselist;
    private final BlockBuilder ifblock;
    private final BlockBuilder elseblock;
    private Boolean conditionFulfilled;
    private Block chosenPath;


    public If (Condition condition, BlockBuilder block){
        super(block);
        this.elselist = new ArrayList<>();
        this.condition = condition;
        this.ifblock = block;
        this.elseblock = new BlockBuilder();
    }
    public If (Condition condition, BlockBuilder block, BlockBuilder elseblock){
        super (block);
        this.elselist = elseblock.list;
        this.ifblock = block;
        this.elseblock = elseblock;
        this.condition = condition;
    }

    @Override
    public void executeOne() throws InstructionException, ProgramFinishedException {
        try {
            if (conditionFulfilled == null) {
                conditionFulfilled = condition.checkCondition();
                chosenPath = conditionFulfilled ? ifblock.build() : elseblock.build();
            }
            if (chosenPath.isFinished()) throw new ProgramFinishedException();
            chosenPath.executeOne();
            index++; //here index only counts instruction, not affecting execution
        } catch (Condition.ConditionException ex) {
            throw new InstructionException(this);
        }
    }

    @Override
    public boolean isFinished() {
        return chosenPath != null && chosenPath.isFinished();
    }

    @Override
    public void undo() {
        index = 0;
        if (chosenPath != null) chosenPath.undo();
        chosenPath = null;
        conditionFulfilled = null;
    }

    @Override
    public String toString() {
        if (conditionFulfilled == null || conditionFulfilled) {
            if (index == 0) return "if (" + condition.toString() + ")";
            if (index > list.size()) return "fi";
            return list.get(index - 1).toString();
        } else {
            if (index == 0) return "else // from if (" + condition.toString() + ")";
            if (index > elselist.size()) return "fi";
            return elselist.get(index - 1).toString();
        }
    }
}
