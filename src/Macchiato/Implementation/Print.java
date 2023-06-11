package Macchiato.Implementation;

public class Print extends InstructionSingle {
    private final Expression e;

    public Print(Expression e) {
        this.e = e;
    }

    @Override
    public void executeOne() throws InstructionException {
        wasCalled = true;
        try {
            int x = e.evaluate();
            System.out.println(x);
        } catch (Expression.ExpressionEvaluationException ex) {
            throw new InstructionException(this);
        }
    }

    @Override
    public String toString() {
        return "print " + e.toString();
    }
}
