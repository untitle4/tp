package seedu.duke.model.event.classlesson;

import seedu.duke.model.event.Event;

import seedu.duke.controller.parser.DateTimeParser;

import java.text.ParseException;

/**
 * <h2>Class class</h2>
 * Contains constructors, getters, toString and equals methods.
 *
 * @see EventClass#toString()
 * @see EventClass#equals(Object)
 */
public class EventClass extends Event {
    public static final String CLASS_ICON = "[CLASS]";

    public EventClass(String description, String start, String end) {
        super(description, start, end);
    }

    public EventClass(String description, boolean isDone, String start, String end) {
        super(description, start, end, isDone);
    }

    @Override
    public String getIcon() {
        return CLASS_ICON;
    }

    @Override
    public String toString() {
        String result = "";
        try {
            result = CLASS_ICON + " " + super.toString() + " from "
                    + new DateTimeParser().changeDateTime(super.getStart())
                    + " to " + new DateTimeParser().changeDateTime(super.getEnd());
        } catch (ParseException e) {
            System.out.println("☹ OOPS!!! Please enter valid date and time in format yyyy-mm-dd HHMM!");
        }
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