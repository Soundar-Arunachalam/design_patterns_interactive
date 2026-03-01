public class WhipDecorator extends CoffeeDecorator {
    public WhipDecorator(Coffee coffee) { super(coffee); }

    public String getDescription() { return coffee.getDescription() + " + Whip"; }
    public double getCost()        { return coffee.getCost() + 0.50; }
}
