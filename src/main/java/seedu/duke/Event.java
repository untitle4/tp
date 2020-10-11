package seedu.duke;

public class Event {
    protected String description;
    protected boolean isDone;

    public Event(String description) {
        this.description = description;
        isDone = false;
    }

    public Event(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }
}
