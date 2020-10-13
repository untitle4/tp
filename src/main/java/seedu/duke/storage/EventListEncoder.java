package seedu.duke.storage;

import seedu.duke.Cca;
import seedu.duke.Class;
import seedu.duke.Event;
import seedu.duke.Test;

import java.util.ArrayList;

public class EventListEncoder {
    public EventListEncoder() {
    }

    public ArrayList<String> encodeEventList(ArrayList<Event> eventList) {
        ArrayList<String> encodedEvents = new ArrayList<>();

        for (Event event : eventList) {
            encodedEvents.add(encodeEventToString(event));
        }

        return encodedEvents;
    }

    private String encodeEventToString(Event event) {
        String result = "";

        if (event instanceof Cca) {
            Cca cca = (Cca) event;
            assertValidCca(cca);
            result = cca.getTypeIcon() + "|"
                    + cca.isDone() + "|"
                    + cca.getDescription() + "|"
                    + cca.getStart() + "|"
                    + cca.getEnd();
        } else if (event instanceof Test) {
            Test test = (Test) event;
            assertValidTest(test);
            result = test.getTypeIcon() + "|"
                    + test.isDone() + "|"
                    + test.getDescription() + "|"
                    + test.getStart() + "|"
                    + test.getEnd();
        } else if (event instanceof Class) {
            Class newClass = (Class) event;
            assertValidClass(newClass);
            result = newClass.getTypeIcon() + "|"
                    + newClass.isDone() + "|"
                    + newClass.getDescription() + "|"
                    + newClass.getStart() + "|"
                    + newClass.getEnd();
        }

        return result;
    }

    private void assertValidCca(Cca cca) {
        isValidParams(cca.getDescription(), cca.getStart(), cca.getEnd());
    }

    private void assertValidClass(Class inputClass) {
        isValidParams(inputClass.getDescription(), inputClass.getStart(), inputClass.getEnd());
    }

    private void assertValidTest(Test test) {
        isValidParams(test.getDescription(), test.getStart(), test.getEnd());
    }

    private void isValidParams(String description, String start, String end) {
        assert description != null;
        assert !description.equals("");
        assert start != null;
        assert !start.equals("");
        assert end != null;
        assert !end.equals("");
    }
}
