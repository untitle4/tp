package seedu.duke;

public class Class extends Event {

    protected String start;
    protected String end;

    public Class(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[Class] " + super.description + "from " + start
                + "to " + end;
    }
}