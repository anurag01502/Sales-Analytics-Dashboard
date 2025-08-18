package in.sp.main.entities;

public enum PaymentStatus {

	PAID("Paid"),
	UNPAID("Pending"),
	PROCESSING("Processing"),
	SHIPPED("Shipped"),
	DELIVERED("Delivered"),
	CANCELLED("Cancelled"),
	PENDING("PENDING")
	;
	
	
    private final String label;

    PaymentStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
