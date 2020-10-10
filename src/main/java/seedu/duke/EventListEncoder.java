package seedu.duke;

import java.util.ArrayList;

public class EventListEncoder {
    public EventListEncoder() {
    }

    public ArrayList<String> encodeEventList (ArrayList<Event> eventList) {
        ArrayList<String> encodedEvents = new ArrayList<>();

        for (Event event : eventList) {
            encodedEvents.add(encodeEventToString(event));
        }

        return encodedEvents;
    }

    private String encodeEventToString (Event event) {
        String result = "";

        if(event instanceof Cca) {
            Cca cca = (Cca) event;
            result = cca.getTypeIcon() + "|"
                    + cca.isDone() + "|"
                    + cca.getDescription() + "|"
                    + cca.getStart() + "|"
                    + cca.getEnd();
        } else if (event instanceof Test) {
            Test test = (Test) event;
            result = test.getTypeIcon() + "|"
                    + test.isDone() + "|"
                    + test.getDescription() + "|"
                    + test.getStart() + "|"
                    + test.getEnd();
        } else if (event instanceof Class){
            Class aClass = (Class) event;
            result = aClass.getTypeIcon() + "|"
                    + aClass.isDone() + "|"
                    + aClass.getDescription() + "|"
                    + aClass.getStart() + "|"
                    + aClass.getEnd();
        }

        return result;
    }
}
