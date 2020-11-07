package seedu.duke.unused;

public class Event implements Comparable<Event> {
    public static final String DONE_STATUS = "[DONE]";
    public static final String NOT_DONE_STATUS = "[NOT DONE]";

    protected boolean isDone;

    public boolean isDone() {
        return isDone;
    }

    public String getStatus() {
        return (isDone ? DONE_STATUS : NOT_DONE_STATUS);
    }

    @Override
    public int compareTo(Event o) {
        return 0;
    }
}
