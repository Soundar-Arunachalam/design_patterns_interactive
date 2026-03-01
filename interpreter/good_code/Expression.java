// Each grammar rule is its own class — new rules are just new classes
public interface Expression {
    boolean interpret(String context);
}
