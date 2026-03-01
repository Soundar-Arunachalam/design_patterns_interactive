public class Main {
    public static void main(String[] args) {
        String doc = "Java Spring Boot developer with cloud experience";

        // "java AND spring"
        Expression query1 = new AndExpression(
            new TermExpression("java"), new TermExpression("spring"));
        System.out.println("java AND spring: " + query1.interpret(doc)); // true

        // "python OR java"
        Expression query2 = new OrExpression(
            new TermExpression("python"), new TermExpression("java"));
        System.out.println("python OR java: " + query2.interpret(doc)); // true

        // "java AND (spring OR boot)" — impossible in bad code, trivial here
        Expression query3 = new AndExpression(
            new TermExpression("java"),
            new OrExpression(new TermExpression("spring"), new TermExpression("boot")));
        System.out.println("java AND (spring OR boot): " + query3.interpret(doc)); // true

        // "java AND NOT python"
        Expression query4 = new AndExpression(
            new TermExpression("java"), new NotExpression(new TermExpression("python")));
        System.out.println("java AND NOT python: " + query4.interpret(doc)); // true

        // Adding a new operator? One new class. Expression.java stays untouched.
    }
}
