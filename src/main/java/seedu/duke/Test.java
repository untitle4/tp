package seedu.duke;

public class Test extends Event {
    public static final String TEST_ICON = "[TEST]";
    protected String start;
    protected String end;

    public Test(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Test(String description, boolean isDone, String start, String end) {
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

    @Override
    public String toString() {
        return TEST_ICON + " " + super.toString() + " from " + start
                + " to " + end;
    }

    @Override
    public boolean equals(Object obj) {
        Test otherTest = (Test) obj;

        return super.equals(obj)
                && this.start.equals(otherTest.getStart())
                && this.end.equals(otherTest.getEnd());
    }
}
