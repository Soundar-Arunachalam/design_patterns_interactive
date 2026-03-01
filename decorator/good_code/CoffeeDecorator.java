// Base decorator — wraps a Coffee and delegates to it
public abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) { this.coffee = coffee; }

    public String getDescription() { return coffee.getDescription(); }
    public double getCost()        { return coffee.getCost(); }
}
