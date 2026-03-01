// Terminal expression — checks for a single word
public class TermExpression implements Expression {
    private String term;
    public TermExpression(String term) { this.term = term.toLowerCase(); }

    public boolean interpret(String context) {
        return context.toLowerCase().contains(term);
    }
}
