package Macchiato.Implementation;

import Macchiato.Implementation.Expressions.Expression;

public abstract class BlockManagement {
    private static class BlockInstance {
        private final BlockInstance previous;
        private final Integer[] variables;

        private BlockInstance(BlockInstance b) {
            previous = b;
            variables = new Integer[26];
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
            if (c < 'a' || c > 'z') return false;
            return variables[c - 'a'] != null;
        }

        private void initialise(char c, int x) throws Reinitialisation, WrongSymbol {
            if (c < 'a' || c > 'z') throw new WrongSymbol();
            if (!wasDeclared(c)) variables[c - 'a'] = x;
            else throw new Reinitialisation();
        }

        private void setVariable(char c, int x) throws NoVariable, WrongSymbol {
            if (c < 'a' || c > 'z') throw new WrongSymbol();
            if (wasDeclared(c)) variables[c - 'a'] = x;
            else if (previous == null) throw new NoVariable();
            else previous.setVariable(c, x);
        }

        private int getVariable(char c) throws NoVariable, WrongSymbol {
            if (c < 'a' || c > 'z') throw new WrongSymbol();
            if (wasDeclared(c)) return variables[c - 'a'];
            else if (previous == null) throw new NoVariable();
            else return previous.getVariable(c);
        }

        private String printValues() {
            String ans = "Widoczne zmienne:\n";
            for (char c = 'a'; c <= 'z'; c++) {
                try {
                    String s = c + " = " + getVariable(c) + '\n';
                    ans += s;
                } catch (NoVariable | WrongSymbol ignored) {
                }
            }
            ans += "Koniec listy zmiennych.";
            return ans;
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
}
