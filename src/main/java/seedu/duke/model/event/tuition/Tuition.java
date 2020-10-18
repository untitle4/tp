package seedu.duke.model.event.tuition;

import seedu.duke.model.event.classlesson.Class;

import seedu.duke.parser.DateTimeParser;

import java.text.ParseException;
import java.time.format.DateTimeParseException;

public class Tuition extends Class {
    public static final String TUITION_ICON = "[TUITION]";
    private String location;

    public Tuition(String description, String start, String end, String location) {
        super(description, start, end);
        this.location = location;
    }

    public Tuition(String description, boolean isDone, String start, String end, String location) {
        super(description, isDone, start, end);
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        String result = "";
        try {
            result = TUITION_ICON + " " + super.toString() + " from "
                    + new DateTimeParser().changeDateTime(super.getStart())
                    + " to " + new DateTimeParser().changeDateTime(super.getEnd()) + " at " + getLocation();
        } catch (ParseException e) {
            System.out.println("☹ OOPS!!! Please enter valid date and time in format yyyy-mm-dd HHMM!");
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        Tuition otherTuition = (Tuition) obj;

        return super.equals(obj)
                && this.getStart().equals(otherTuition.getStart())
                && this.getEnd().equals(otherTuition.getEnd())
                && this.location.equals(otherTuition.getLocation());
    }
}
