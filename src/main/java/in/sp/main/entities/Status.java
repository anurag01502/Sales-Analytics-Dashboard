package in.sp.main.entities;

public enum Status {

	ACTIVE("Active"),
    INACTIVE("InActive")
    ;

    private final String label;

    Status(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
