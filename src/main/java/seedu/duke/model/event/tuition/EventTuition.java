package seedu.duke.model.event.tuition;

import seedu.duke.model.event.Event;

import seedu.duke.controller.parser.DateTimeParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//@@author durianpancakes
public class EventTuition extends Event {
    public static final String TUITION_ICON = "[TUITION]";
    private final String location;

    public EventTuition(String description, Calendar start, Calendar end, String location) {
        super(description, start, end);
        this.location = location;
    }

    @Override
    public String getIcon() {
        return TUITION_ICON;
    }

    public EventTuition(String description, boolean isDone, Calendar start, Calendar end, String location) {
        super(description, start, end, isDone);
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        result = TUITION_ICON + " " + super.toString() + " from "
                + sdf.format(this.getStart().getTime())
                + " to " + sdf.format(this.getEnd().getTime())
                + " at " + this.location;
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
