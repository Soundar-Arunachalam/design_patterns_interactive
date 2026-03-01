// Non-terminal: negates a sub-expression
public class NotExpression implements Expression {
    private Expression expr;
    public NotExpression(Expression expr) { this.expr = expr; }

    public boolean interpret(String context) {
        return !expr.interpret(context);
    }
}
