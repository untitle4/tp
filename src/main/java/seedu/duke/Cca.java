package seedu.duke;

import seedu.duke.parser.DateTimeParser;

import java.text.ParseException;
import java.time.format.DateTimeParseException;

public class Cca extends Event {
    public static final String CCA_ICON = "[CCA]";

    public Cca(String description, String start, String end) {
        super(description, start, end);
    }

    public Cca(String description, boolean isDone, String start, String end) {
        super(description, start, end, isDone);
    }

    @Override
    public String toString() {
        String result = "";
        try {
            result = CCA_ICON + " " + super.toString() + " from " + new DateTimeParser().changeDateTime(super.getStart())
                    + " to " + new DateTimeParser().changeDateTime(super.getEnd());
        } catch (ParseException parseException) {
            System.out.println("☹ OOPS!!! Please enter valid date and time in format yyyy-mm-dd HHMM!");
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        Cca otherCca = (Cca) obj;

        return super.equals(obj)
                && this.getStart().equals(otherCca.getStart())
                && this.getEnd().equals(otherCca.getEnd());
    }
}
