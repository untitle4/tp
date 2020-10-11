package seedu.duke;

public class Class extends Event {

    protected String start;
    protected String end;

    public Class(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Class(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getTypeIcon() {
        return "[CLASS]";
    }

    @Override
    public String toString() {
        return "[Class] " + super.getDescription() + "from " + start
                + "to " + end;
    }
}