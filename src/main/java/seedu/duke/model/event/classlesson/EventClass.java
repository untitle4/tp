package seedu.duke.model.event.classlesson;

import seedu.duke.model.event.Event;

import seedu.duke.controller.parser.DateTimeParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//@@author elizabethcwt
/**
 * <h2>Class class</h2>
 * Contains constructors, getters, toString and equals methods.
 *
 * @see EventClass#toString()
 * @see EventClass#equals(Object)
 */
public class EventClass extends Event {
    public static final String CLASS_ICON = "[CLASS]";

    public EventClass(String description, Calendar start, Calendar end) {
        super(description, start, end);
    }

    public EventClass(String description, boolean isDone, Calendar start, Calendar end) {
        super(description, start, end, isDone);
    }

    @Override
    public String getIcon() {
        return CLASS_ICON;
    }

    @Override
    public String toString() {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        result = CLASS_ICON + " " + super.toString() + " from "
                + sdf.format(this.getStart().getTime())
                + " to " + sdf.format(this.getEnd().getTime());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        EventClass otherEventClass = (EventClass) obj;

        return super.equals(obj)
                && this.getStart().equals(otherEventClass.getStart())
                && this.getEnd().equals(otherEventClass.getEnd());
    }
}