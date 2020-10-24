package seedu.duke.model.event;

//@@author elizabethcwt
public class Event {
    public static final String DONE_STATUS = "[DONE]";
    public static final String NOT_DONE_STATUS = "[NOT DONE]";

    protected String description;
    protected boolean isDone;
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        this.description = description;
        this.start = start;
        this.end = end;
        isDone = false;
    }

    //@@author durianpancakes
    public Event(String description, String start, String end, boolean isDone) {
        this.description = description;
        this.start = start;
        this.end = end;
        this.isDone = isDone;
    }

    //@@author
    public String getIcon() {
        return null;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getStatus() {
        return (isDone ? DONE_STATUS : NOT_DONE_STATUS);
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getStatus() + " " + description;
    }

    //@@author durianpancakes
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
