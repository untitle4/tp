package seedu.duke.model.event.cca;

import seedu.duke.model.event.Event;

import seedu.duke.controller.parser.DateTimeParser;

import java.text.ParseException;

public class EventCca extends Event {
    public static final String CCA_ICON = "[CCA]";

    public EventCca(String description, String start, String end) {
        super(description, start, end);
    }

    public EventCca(String description, boolean isDone, String start, String end) {
        super(description, start, end, isDone);
    }

    @Override
    public String toString() {
        String result = "";
        try {
            result = CCA_ICON + " " + super.toString() + " from "
                    + new DateTimeParser().changeDateTime(super.getStart())
                    + " to " + new DateTimeParser().changeDateTime(super.getEnd());
        } catch (ParseException parseException) {
            System.out.println("☹ OOPS!!! Please enter valid date and time in format yyyy-mm-dd HHMM!");
        }
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
