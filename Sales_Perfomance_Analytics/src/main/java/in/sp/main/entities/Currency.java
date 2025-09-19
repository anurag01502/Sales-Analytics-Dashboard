package in.sp.main.entities;

public enum Currency {
    USD("USD"),
    EURO("EURO"),
    INR("INR"),
    CAD("CAD");

    private final String displayName;

    Currency(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

