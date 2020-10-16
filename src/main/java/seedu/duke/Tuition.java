package seedu.duke;

public class Tuition extends Class {
    public static final String TUITION_ICON = "[TUITION]";
    private String location;

    public Tuition(String description, String start, String end, String location) {
        super(description, start, end);
        this.location = location;
    }

    public Tuition(String description, boolean isDone, String start, String end, String location) {
        super(description, isDone, start, end);
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return TUITION_ICON + " " + super.toString() + "from " + start
                + " to " + end
                + " location " + location;
    }

    @Override
    public boolean equals(Object obj) {
        Tuition otherTuition = (Tuition) obj;

        return super.equals(obj)
                && this.start.equals(otherTuition.getStart())
                && this.end.equals(otherTuition.getEnd())
                && this.location.equals(otherTuition.getLocation());
    }
}
