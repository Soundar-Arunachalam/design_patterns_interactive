// Calculates the final price of a product after applying a discount.
// Adding a new discount type means editing this class.
public class PricingService {
    public double applyDiscount(double price, String discountType) {
        if (discountType.equals("none")) {
            return price;
        } else if (discountType.equals("percentage10")) {
            return price * 0.90;
        } else if (discountType.equals("percentage20")) {
            return price * 0.80;
        } else if (discountType.equals("flat5")) {
            return Math.max(0, price - 5.0);
        } else if (discountType.equals("bogo")) {
            // buy one get one — half price per item
            return price / 2.0;
        } else {
            System.out.println("Unknown discount: " + discountType);
            return price;
        }
    }
}
