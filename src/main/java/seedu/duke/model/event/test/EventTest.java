package seedu.duke.model.event.test;

import seedu.duke.model.event.Event;

import seedu.duke.controller.parser.DateTimeParser;

import java.text.ParseException;

public class EventTest extends Event {
    public static final String TEST_ICON = "[TEST]";

    public EventTest(String description, String start, String end) {
        super(description, start, end);
    }

    public EventTest(String description, boolean isDone, String start, String end) {
        super(description, start, end, isDone);
    }

    @Override
    public String getIcon() {
        return TEST_ICON;
    }

    @Override
    public String toString() {
        String result = "";
        try {
            result = TEST_ICON + " " + super.toString() + " from "
                    + new DateTimeParser().changeDateTime(super.getStart())
                    + " to " + new DateTimeParser().changeDateTime(super.getEnd());
        } catch (ParseException e) {
            System.out.println("☹ OOPS!!! Please enter valid date and time in format yyyy-mm-dd HHMM!");
        }
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
