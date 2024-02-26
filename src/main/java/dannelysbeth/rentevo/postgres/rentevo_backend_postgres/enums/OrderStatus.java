package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.enums;

public enum OrderStatus {
    PENDING("Pending Payment"),
    PROCESSING("Processing"),
    SHIPPED("Shipped"),
    DELIVERED("Delivered"),
    CANCELED("Canceled");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
