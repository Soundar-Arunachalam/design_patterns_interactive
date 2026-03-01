// Non-terminal: both sub-expressions must be true
public class AndExpression implements Expression {
    private Expression left, right;
    public AndExpression(Expression left, Expression right) { this.left = left; this.right = right; }

    public boolean interpret(String context) {
        return left.interpret(context) && right.interpret(context);
    }
}
