package seedu.duke;

public class Test extends Event {

    protected String start;
    protected String end;

    public Test(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Test (String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    public String getStart () {
        return start;
    }

    public String getEnd () {
        return end;
    }

    public String getTypeIcon() {
        return "[TEST]";
    }

    @Override
    public String toString() {
        return "[TEST] " + super.description + "from " + start
                + "to " + end + " has been added.";
    }
}
