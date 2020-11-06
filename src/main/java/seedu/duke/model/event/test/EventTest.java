package seedu.duke.model.event.test;

import seedu.duke.model.event.Event;

import seedu.duke.controller.parser.DateTimeParser;

import java.util.Calendar;

//@@author Aliciaho
/**
 * <h2>Class EventTest</h2>
 * Contains constructors, getters, toString and equals methods.
 *
 * @see EventTest#toString()
 * @see EventTest#equals(Object)
 */
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
        DateTimeParser dateTimeParser = new DateTimeParser();
        result = TEST_ICON + " " + super.toString() + " from "
                + dateTimeParser.obtainFormattedDateTimeString(this.getStart())
                + " to " + dateTimeParser.obtainFormattedDateTimeString(this.getEnd());
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
