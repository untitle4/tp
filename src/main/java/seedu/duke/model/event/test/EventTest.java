package seedu.duke.model.event.test;

import seedu.duke.model.event.Event;

import seedu.duke.controller.parser.DateTimeParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//@@author Aliciaho
public class EventTest extends Event {
    public static final String TEST_ICON = "[TEST]";

    public EventTest(String description, Calendar start, Calendar end) {
        super(description, start, end);
    }

    public EventTest(String description, boolean isDone, Calendar start, Calendar end) {
        super(description, start, end, isDone);
    }

    @Override
    public String getIcon() {
        return TEST_ICON;
    }

    @Override
    public String toString() {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        result = TEST_ICON + " " + super.toString() + " from "
                + sdf.format(this.getStart().getTime())
                + " to " + sdf.format(this.getEnd().getTime());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        EventTest otherEventTest = (EventTest) obj;

        return super.equals(obj)
                && this.getStart().equals(otherEventTest.getStart())
                && this.getEnd().equals(otherEventTest.getEnd());
    }
}
