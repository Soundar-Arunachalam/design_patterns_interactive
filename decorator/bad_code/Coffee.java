// A coffee shop app. Each combination of add-ons is its own subclass.
// With 3 add-ons (milk, sugar, whip), you need 2^3 = 8 classes just for combinations.
// Add a 4th add-on → 16 classes. This explodes fast.

public class Coffee {
    public String getDescription() { return "Coffee"; }
    public double getCost() { return 1.00; }
}

class CoffeeWithMilk extends Coffee {
    public String getDescription() { return "Coffee + Milk"; }
    public double getCost() { return 1.30; }
}

class CoffeeWithSugar extends Coffee {
    public String getDescription() { return "Coffee + Sugar"; }
    public double getCost() { return 1.20; }
}

class CoffeeWithMilkAndSugar extends Coffee {
    public String getDescription() { return "Coffee + Milk + Sugar"; }
    public double getCost() { return 1.50; }
}

class CoffeeWithMilkAndSugarAndWhip extends Coffee {
    public String getDescription() { return "Coffee + Milk + Sugar + Whip"; }
    public double getCost() { return 1.80; }
}
// And we haven't even added Vanilla, Caramel, Oat Milk...
