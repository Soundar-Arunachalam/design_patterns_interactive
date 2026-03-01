public class PaymentService {

    private PayPalClient payPalClient = new PayPalClient();
    private StripeClient stripeClient = new StripeClient();
    private SquareClient squareClient = new SquareClient();

    public void processPayment(String provider, double amount) {

        if (provider.equals("paypal")) {

            payPalClient.sendPayment(amount, "USD");

        } else if (provider.equals("stripe")) {

            int amountInCents = (int) (amount * 100);
            stripeClient.charge(amountInCents, "USD");

        } else if (provider.equals("square")) {

            boolean success = squareClient.makePayment("USD", amount);
            if (!success) {
                System.out.println("  Square payment failed!");
            }

        } else {
            throw new IllegalArgumentException("Unknown payment provider: " + provider);
        }
    }

    public void refundPayment(String provider, double amount) {

        if (provider.equals("paypal")) {

            payPalClient.sendRefund(amount, "USD");

        } else if (provider.equals("stripe")) {

            int amountInCents = (int) (amount * 100);
            stripeClient.issueRefund(amountInCents, "USD");

        } else if (provider.equals("square")) {

            boolean success = squareClient.makeRefund("USD", amount);
            if (!success) {
                System.out.println("  Square refund failed!");
            }

        } else {
            throw new IllegalArgumentException("Unknown payment provider: " + provider);
        }
    }
}
