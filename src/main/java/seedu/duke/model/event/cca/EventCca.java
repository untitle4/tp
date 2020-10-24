package seedu.duke.model.event.cca;

import seedu.duke.model.event.Event;

import seedu.duke.controller.parser.DateTimeParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//@@author untitle4
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        result = CCA_ICON + " " + super.toString() + " from "
                + sdf.format(this.getStart().getTime())
                + " to " + sdf.format(this.getEnd().getTime());
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
