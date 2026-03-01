/*
 * PROBLEM STATEMENT
 * -----------------
 * A pizza shop lets customers customise their pizza with toppings:
 * Extra Cheese, Olives, Jalapenos, etc. Currently, every unique combination
 * is a separate class (MargheritaWithExtraCheese, PepperoniWithOlives, …).
 *
 * Issues:
 *  - 2 bases × 4 toppings already creates dozens of classes.
 *  - Adding one new topping requires a new class for EVERY base pizza.
 *  - There is no way to stack toppings dynamically at runtime.
 *  - getDescription() and getCost() logic is duplicated in every class.
 *
 * Task: Refactor so toppings can be added dynamically by wrapping a base pizza
 * object. Each topping adds its own cost and label without creating new subclasses.
 */
public class Main {
    public static void main(String[] args) {
        // Works, but completely inflexible
        MargheritaPizza p1 = new MargheritaPizza();
        System.out.printf("%s — $%.2f%n", p1.getDescription(), p1.getCost());

        MargheritaWithExtraCheese p2 = new MargheritaWithExtraCheese();
        System.out.printf("%s — $%.2f%n", p2.getDescription(), p2.getCost());

        MargheritaWithExtraCheeseAndOlives p3 = new MargheritaWithExtraCheeseAndOlives();
        System.out.printf("%s — $%.2f%n", p3.getDescription(), p3.getCost());

        PepperoniWithOlives p4 = new PepperoniWithOlives();
        System.out.printf("%s — $%.2f%n", p4.getDescription(), p4.getCost());

        // How do we add "Jalapenos" to any pizza without a new class per combo?
    }
}
