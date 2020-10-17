package seedu.duke.model.event.classlesson;

import seedu.duke.Event;

/**
 * <h2>Class class</h2>
 * Contains constructors, getters, toString and equals methods.
 *
 * @see Class#toString()
 * @see Class#equals(Object)
 */
public class Class extends Event {
    public static final String CLASS_ICON = "[CLASS]";
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

    @Override
    public String toString() {
        return CLASS_ICON + " " + super.toString() + "from " + start
                + " to " + end;
    }

    @Override
    public boolean equals(Object obj) {
        Class otherClass = (Class) obj;

        return super.equals(obj)
                && this.start.equals(otherClass.getStart())
                && this.end.equals(otherClass.getEnd());
    }
}