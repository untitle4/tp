package seedu.duke.model.event.cca;

import seedu.duke.model.event.Event;

import seedu.duke.controller.parser.DateTimeParser;

import java.util.Calendar;

//@@author untitle4
/**
 * A class for event cca.
 */
public class EventCca extends Event {
    public static final String CCA_ICON = "[CCA]";

    public EventCca(String description, Calendar start, Calendar end) {
        super(description, start, end);
    }

    public EventCca(String description, boolean isDone, Calendar start, Calendar end) {
        super(description, start, end, isDone);
    }

    @Override
    public String getIcon() {
        return CCA_ICON;
    }

    @Override
    public String toString() {
        String result = "";
        DateTimeParser dateTimeParser = new DateTimeParser();
        result = CCA_ICON + " " + super.toString() + " from "
                + dateTimeParser.obtainFormattedDateTimeString(this.getStart())
                + " to " + dateTimeParser.obtainFormattedDateTimeString(this.getEnd());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        EventCca otherEventCca = (EventCca) obj;

        return super.equals(obj)
                && this.getStart().equals(otherEventCca.getStart())
                && this.getEnd().equals(otherEventCca.getEnd());
    }
}
