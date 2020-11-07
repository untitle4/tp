package seedu.duke.model.event.tuition;

import seedu.duke.model.event.Event;

import seedu.duke.controller.parser.DateTimeParser;

import java.util.Calendar;

//@@author durianpancakes

/**
 * Class EventTuition.
 * Contains constructors, getters, toString and equals methods.
 */
public class EventTuition extends Event {
    public static final String TUITION_ICON = "[TUITION]";
    private final String location;

    /**
     * Constructs a EventTuition with a default NOT DONE status.
     *
     * @param description String containing description of the EventTuition.
     * @param start Calendar containing the start time of the EventTuition.
     * @param end Calendar containing the end time of the EventTuition.
     * @param location String containing the location of the EventTuition.
     */
    public EventTuition(String description, Calendar start, Calendar end, String location) {
        super(description, start, end);
        this.location = location;
    }

    /**
     * Constructs a EventTuition with a given NOT DONE/DONE status.
     *
     * @param description String containing description of the EventTuition.
     * @param isDone boolean containing the done status of the EventTuition.
     * @param start Calendar containing the start time of the EventTuition.
     * @param end Calendar containing the end time of the EventTuition.
     * @param location String containing the location of the EventTuition.
     */
    public EventTuition(String description, boolean isDone, Calendar start, Calendar end, String location) {
        super(description, start, end, isDone);
        this.location = location;
    }

    @Override
    public String getIcon() {
        return TUITION_ICON;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        String result;
        DateTimeParser dateTimeParser = new DateTimeParser();
        result = TUITION_ICON + " " + super.toString() + " from "
                + dateTimeParser.obtainFormattedDateTimeString(this.getStart())
                + " to " + dateTimeParser.obtainFormattedDateTimeString(this.getEnd())
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
