package Macchiato.Implementation;

import Macchiato.Implementation.Expressions.Expression;
import Macchiato.Implementation.Instructions.Procedure;

import java.util.HashMap;
import java.util.Map;

/*
W Macchiato 1.0 ta implementacja pozwalała na uruchomienie tylko jednej
instancji Macchiato naraz. Macchiato 1.1 nie rozwiązuje tego problemu.
*/

public abstract class BlockManagement {
    private static class BlockInstance {
        private final BlockInstance previous;
        private final Integer[] variables;
        private final char firstchar = 'a';
        private final char lastchar = 'z';
        private Map<String, Procedure> procedures;

        private BlockInstance(BlockInstance b) {
            previous = b;
            final int alphabetsize = lastchar - firstchar + 1;
            variables = new Integer[alphabetsize];
            procedures = new HashMap<>();
        }

        private static class Reinitialisation extends Exception {
            @Override
            public String getMessage() {
                return "Już zainicjalizowano w tym bloku!";
            }
        }

        private static class NoVariable extends Exception {
            @Override
            public String getMessage() {
                return "Nie ma takiej zmiennej!";
            }
        }

        private static class WrongSymbol extends Exception {
            @Override
            public String getMessage() {
                return "To nie jest odpowiedni znak zmiennej!";
            }
        }

        private boolean wasDeclared(char c) {
            if (c < firstchar || c > lastchar) return false;
            return variables[c - firstchar] != null;
        }
        private boolean wasDeclared (String procedure){
            return procedures.containsKey(procedure);
        }

        private void initialise(char c, int x) throws Reinitialisation, WrongSymbol {
            if (c < firstchar || c > lastchar) throw new WrongSymbol();
            if (!wasDeclared(c)) variables[c - firstchar] = x;
            else throw new Reinitialisation();
        }

        private void setVariable(char c, int x) throws NoVariable, WrongSymbol {
            if (c < firstchar || c > lastchar) throw new WrongSymbol();
            if (wasDeclared(c)) variables[c - firstchar] = x;
            else if (previous == null) throw new NoVariable();
            else previous.setVariable(c, x);
        }

        private int getVariable(char c) throws NoVariable, WrongSymbol {
            if (c < firstchar || c > lastchar) throw new WrongSymbol();
            if (wasDeclared(c)) return variables[c - firstchar];
            else if (previous == null) throw new NoVariable();
            else return previous.getVariable(c);
        }

        private String printValues() {
            StringBuilder ans = new StringBuilder("Widoczne zmienne:\n");
            for (char c = firstchar; c <= lastchar; c++) {
                try {
                    String s = c + " = " + getVariable(c) + '\n';
                    ans.append(s);
                } catch (NoVariable | WrongSymbol ignored) {
                }
            }
            ans.append("Koniec listy zmiennych.");
            return ans.toString();
        }

        private void declareProcedure (String name, Procedure procedure){
            procedures.put(name, procedure);
        }
    }

    public static class BlockOperationException extends Exception {
    }

    public static class BlockPrintException extends Exception {
        @Override
        public String getMessage() {
            return "W programie nie ma tylu warstw zagnieżdżenia.";
        }
    }
    public static class ProcedureException extends Exception {
    }

    private static BlockInstance current = null;

    public static void newBlock() {
        current = new BlockInstance(current);
    }

    public static void endBlock() {
        current = current.previous;
    }

    public static void closeAll() {
        current = null;
    }

    public static void initialise(char c, Expression e) throws BlockOperationException {
        try {
            current.initialise(c, e.evaluate());
        } catch (BlockInstance.Reinitialisation | Expression.ExpressionEvaluationException |
                 BlockInstance.WrongSymbol ex) {
            System.out.println(ex.getMessage());
            throw new BlockOperationException();
        }
    }

    public static void setVariable(char c, Expression e) throws BlockOperationException {
        try {
            current.setVariable(c, e.evaluate());
        } catch (BlockInstance.NoVariable | Expression.ExpressionEvaluationException | BlockInstance.WrongSymbol ex) {
            System.out.println(ex.getMessage());
            throw new BlockOperationException();
        }
    }

    public static int getVariable(char c) throws BlockOperationException {
        try {
            return current.getVariable(c);
        } catch (BlockInstance.NoVariable | BlockInstance.WrongSymbol ex) {
            System.out.println(ex.getMessage());
            throw new BlockOperationException();
        }
    }

    public static String printValues(int layers) throws BlockPrintException {
        BlockInstance b = current;
        while (layers-- > 0) {
            if (b == null) throw new BlockPrintException();
            b = b.previous;
        }
        if (b == null) throw new BlockPrintException();
        return b.printValues();
    }

    public static String printValues() {
        return current.printValues();
    }

    public static Procedure getProcedure (String name) throws ProcedureException{
        Procedure ans = current.procedures.get(name);
        if (ans == null) throw new ProcedureException();
        return ans;
    }

    public static void addProcedure (String name, Procedure proc) throws ProcedureException{
        if (current.wasDeclared(name))
            throw new ProcedureException();
        current.declareProcedure (name, proc);
    }
}
