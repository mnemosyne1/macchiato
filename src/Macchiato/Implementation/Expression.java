package Macchiato.Implementation;

public interface Expression {
    class ExpressionEvaluationException extends Exception {
        @Override
        public String getMessage() {
            return "Nie udało się obliczyć!";
        }
    }

    int evaluate() throws ExpressionEvaluationException;

    int getPriority();
}
