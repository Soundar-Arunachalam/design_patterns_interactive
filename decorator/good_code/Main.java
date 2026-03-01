public class Main {
    public static void main(String[] args) {
        Coffee order1 = new SimpleCoffee();
        System.out.println(order1.getDescription() + " -> $" + order1.getCost());

        // Wrap at runtime — any combination, any order, even double milk
        Coffee order2 = new WhipDecorator(new MilkDecorator(new SimpleCoffee()));
        System.out.println(order2.getDescription() + " -> $" + order2.getCost());

        Coffee order3 = new SugarDecorator(new MilkDecorator(new MilkDecorator(new SimpleCoffee())));
        System.out.println(order3.getDescription() + " -> $" + order3.getCost());

        // Adding Caramel? One new class: CaramelDecorator. Nothing else changes.
    }
}
