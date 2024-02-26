package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.enums;

public enum PaymentType {
    CREDIT_CARD("Credit Card"),
    DEBIT_CARD("Debit Card"),
    PAYPAL("PayPal"),
    GOOGLE_PAY("Google Pay"),
    APPLE_PAY("Apple Pay"),
    CASH_ON_DELIVERY("Cash on Delivery");

    private final String displayName;

    PaymentType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
