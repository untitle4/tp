package seedu.duke;

public class Cca extends Event {

    protected String start;
    protected String end;

    public Cca(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[CCA] " + super.description + "from " + start
                + "to " + end;
    }
}
