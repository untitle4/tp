package seedu.duke;
import seedu.duke.parser.DateTimeParser;

import java.text.ParseException;
import java.time.format.DateTimeParseException;

public class Test extends Event {
    public static final String TEST_ICON = "[TEST]";

    public Test(String description, String start, String end) {
        super(description, start, end);
    }

    public Test(String description, boolean isDone, String start, String end) {
        super(description, start, end, isDone);
    }

    @Override
    public String toString() {
        String result = "";
        try {
            result = TEST_ICON + " " + super.toString() + " from " + new DateTimeParser().changeDateTime(super.getStart())
                    + " to " + new DateTimeParser().changeDateTime(super.getEnd());
        } catch (ParseException e) {
            System.out.println("☹ OOPS!!! Please enter valid date and time in format yyyy-mm-dd HHMM!");
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        Test otherTest = (Test) obj;

        return super.equals(obj)
                && this.getStart().equals(otherTest.getStart())
                && this.getEnd().equals(otherTest.getEnd());
    }
}
