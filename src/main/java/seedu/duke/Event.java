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

    public void setDone() {
        this.isDone = true;
    }

    public String getStatus() {
        return (isDone ? "[DONE]" : "[NOT DONE]");
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return getStatus() + " " + description;
    }

    @Override
    public boolean equals(Object obj) {
        Event otherEvent = (Event) obj;

        return this.description.equals(otherEvent.getDescription())
                && this.isDone == otherEvent.isDone();
    }
}
