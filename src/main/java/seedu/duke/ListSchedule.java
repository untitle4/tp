package seedu.duke;

import java.util.ArrayList;

public class ListSchedule {
    private final ArrayList<String> events;

    public ListSchedule(ArrayList<String> events) {
        this.events = events;
    }

    public void printAllEvents() {
        events.stream().forEach(System.out::println);
    }
}
