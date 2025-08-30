package in.sp.main.entities;

public enum Role {
	REPRESENTATIVE("Representative"),
    MANAGER("Manager"),
    ADMIN("Admin"),
    EXECUTIVE("Executive")
    ;

    private final String label;

    Role(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

