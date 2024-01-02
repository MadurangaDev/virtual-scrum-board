package Enums;

public enum TicketStatus {
    TO_DO,
    ONGOING,
    DONE;

    private String value;

    TicketStatus() {
        this.value = name().toLowerCase();
    }

    public String getValue() {
        return value;
    }

    static {
        TO_DO.value = "ToDo";
        ONGOING.value = "Ongoing";
        DONE.value = "Done";
    }
}
