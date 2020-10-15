package seedu.duke;

public class Event {
    public static final String DONE_STATUS = "[DONE]";
    public static final String NOT_DONE_STATUS = "[NOT DONE]";
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

    public void setDone() {
        this.isDone = true;
    }

    public String getStatus() {
        return (isDone ? DONE_STATUS : NOT_DONE_STATUS);
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getStatus() + " " + description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Event otherEvent = (Event) obj;
        return this.description.equals(otherEvent.description)
                && this.isDone == otherEvent.isDone();
    }
}
