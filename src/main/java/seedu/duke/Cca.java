package seedu.duke;

public class Cca extends Event {

    protected String start;
    protected String end;

    public Cca(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Cca(String description, boolean isDone, String start, String end) {
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
        return "[CCA]";
    }

    @Override
    public String toString() {
        return "[CCA] " + super.getDescription() + "from " + start
                + "to " + end;
    }

    @Override
    public boolean equals(Object obj) {
        Cca otherCca = (Cca) obj;

        return super.equals(obj)
                && this.start.equals(otherCca.getStart())
                && this.end.equals(otherCca.getEnd());
    }
}
