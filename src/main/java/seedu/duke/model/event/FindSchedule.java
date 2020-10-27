package seedu.duke.model.event;

import seedu.duke.common.LogManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author AndreWongZH
/**
 * Represents a process dedicated to filter out events based on given keywords.
 */
public class FindSchedule {
    public static final String INPUT_SPACE = " ";
    private final ArrayList<Event> classes;
    private final ArrayList<Event> ccas;
    private final ArrayList<Event> tests;
    private final ArrayList<Event> tuitions;

    private static final Logger logger = LogManager.getLogManagerInstance().getLogger();
    private final String userInput;
    private final ArrayList<String> filteredEvents;

    public FindSchedule(String userInput, ArrayList<Event> classes, ArrayList<Event> ccas,
                        ArrayList<Event> tests, ArrayList<Event> tuitions) {
        assert userInput.length() != 0 : "user input should not be missing";
        this.userInput = userInput;
        this.classes = classes;
        this.ccas = ccas;
        this.tests = tests;
        this.tuitions = tuitions;
        filteredEvents = new ArrayList<>();
    }

    /**
     * Returns all the events whose description matches with the provided keywords.
     * Merges all the event types into one single arraylist.
     *
     * @return An Arraylist of type String to be printed out.
     */
    public ArrayList<String> getFilteredEvents() {
        logger.log(Level.INFO, "Combining all arraylist into one main arraylist");
        ArrayList<Event> events = new ArrayList<>();
        events.addAll(classes);
        events.addAll(ccas);
        events.addAll(tests);
        events.addAll(tuitions);

        filterEvents(events);

        return filteredEvents;
    }

    /**
     * Loops through event list to checks if the event's description matches with the keywords.
     * Both strings are first converted to lowercase before comparison.
     * If description matches, add the event into the filteredEvents arraylist.
     *
     * @param events An ArrayList of type Event to be checked against
     */
    private void filterEvents(ArrayList<Event> events) {
        logger.log(Level.INFO, "loop through all the keywords to check if in event description");
        String[] separatedInputs = userInput.split(INPUT_SPACE);

        for (Event event : events) {
            for (String keyword : separatedInputs) {
                if (event.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                    filteredEvents.add(event.toString());
                    break;
                }
            }
        }
    }
}
