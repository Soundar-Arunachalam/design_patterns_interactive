/*
 * PROBLEM STATEMENT
 * -----------------
 * An e-commerce site applies various discounts at checkout: no discount,
 * 10% off, 20% off, flat $5 off, and buy-one-get-one (BOGO).
 * All discount logic lives in one method with growing if-else branches.
 *
 * Issues:
 *  - Adding a "loyalty points" discount requires editing PricingService.
 *  - Discount algorithms cannot be reused across different product categories.
 *  - Unit-testing a single discount type requires running through the whole method.
 *  - Discounts cannot be switched at runtime (e.g. based on membership tier).
 *
 * Task: Refactor so each discount algorithm is its own class, and PricingService
 * receives the discount as a dependency rather than selecting it with if-else.
 */
public class Main {
    public static void main(String[] args) {
        PricingService service = new PricingService();
        double price = 50.0;

        System.out.printf("No discount:   $%.2f%n", service.applyDiscount(price, "none"));
        System.out.printf("10%% off:       $%.2f%n", service.applyDiscount(price, "percentage10"));
        System.out.printf("20%% off:       $%.2f%n", service.applyDiscount(price, "percentage20"));
        System.out.printf("Flat $5 off:   $%.2f%n", service.applyDiscount(price, "flat5"));
        System.out.printf("BOGO:          $%.2f%n", service.applyDiscount(price, "bogo"));
    }
}
