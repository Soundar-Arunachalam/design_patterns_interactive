public class Main {
    public static void main(String[] args) {
        Coffee order1 = new CoffeeWithMilk();
        System.out.println(order1.getDescription() + " -> $" + order1.getCost());

        Coffee order2 = new CoffeeWithMilkAndSugarAndWhip();
        System.out.println(order2.getDescription() + " -> $" + order2.getCost());

        System.out.println("\nCustomer: 'Can I get Milk + Whip but no Sugar?'");
        System.out.println("You: *creates CoffeeWithMilkAndWhip.java...*");
        System.out.println("Customer: 'Actually, double milk?'");
        System.out.println("You: 😰");
    }
}
