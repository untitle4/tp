package seedu.duke.model.event.tuition;

import seedu.duke.model.event.Event;

import seedu.duke.controller.parser.DateTimeParser;

import java.text.ParseException;

public class EventTuition extends Event {
    public static final String TUITION_ICON = "[TUITION]";
    private final String location;

    public EventTuition(String description, String start, String end, String location) {
        super(description, start, end);
        this.location = location;
    }

    @Override
    public String getIcon() {
        return TUITION_ICON;
    }

    public EventTuition(String description, boolean isDone, String start, String end, String location) {
        super(description, start, end, isDone);
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        String result = "";
        try {
            result = TUITION_ICON + " " + super.toString() + " from "
                    + new DateTimeParser().changeDateTime(super.getStart())
                    + " to " + new DateTimeParser().changeDateTime(super.getEnd()) + " at " + getLocation();
        } catch (ParseException e) {
            System.out.println("☹ OOPS!!! Please enter valid date and time in format yyyy-mm-dd HHMM!");
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        EventTuition otherEventTuition = (EventTuition) obj;

        return super.equals(obj)
                && this.getStart().equals(otherEventTuition.getStart())
                && this.getEnd().equals(otherEventTuition.getEnd())
                && this.location.equals(otherEventTuition.getLocation());
    }
}
