package seedu.duke.model.event;

import seedu.duke.controller.parser.DateTimeParser;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.model.event.cca.EventCcaManager;
import seedu.duke.model.event.classlesson.EventClassManager;
import seedu.duke.common.Messages;
import seedu.duke.model.event.test.EventTestManager;
import seedu.duke.model.event.tuition.EventTuitionManager;
import seedu.duke.ui.UserInterface;
import seedu.duke.exception.EmptyListException;

import java.lang.reflect.Array;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Represents a handler that manages the four different event managers.
 * This provides access to each individual event managers and
 * also performs listing and searches for the entire events data set.
 */
public class EventManager {
    public static final int EMPTY_SIZE = 0;
    public static final int USER_INPUT_OFFSET = 10;
    private static EventClassManager eventClassManager;
    private static EventTestManager eventTestManager;
    private static EventCcaManager eventCcaManager;
    private static EventTuitionManager eventTuitionManager;
    private final UserInterface userInterface;

    public EventManager(EventParameter eventParameter) {
        eventClassManager = new EventClassManager(eventParameter.getClasses());
        eventTestManager = new EventTestManager(eventParameter.getTests());
        eventCcaManager = new EventCcaManager(eventParameter.getCcas());
        eventTuitionManager = new EventTuitionManager(eventParameter.getTuitions());
        userInterface = UserInterface.getInstance();
    }

    public EventClassManager getClassManager() {
        return eventClassManager;
    }

    public EventTestManager getTestManager() {
        return eventTestManager;
    }

    public EventCcaManager getCcaManager() {
        return eventCcaManager;
    }

    public EventTuitionManager getTuitionManager() {
        return eventTuitionManager;
    }

    public void listSchedule(String userInput) {
        ArrayList<String> printedEvents;
        try {
            String dateParam = userInput.split(" ").length == 2 ? null : userInput.split(" ")[2];
            ListSchedule listSchedule = new ListSchedule(dateParam, eventClassManager.getClasses(),
                    eventCcaManager.getCcas(), eventTestManager.getTests(), eventTuitionManager.getTuitions());

            if (userInput.contains("week")) {
                printedEvents = listSchedule.getPrintableEventsWeek();
            } else {
                printedEvents = listSchedule.getPrintableEvents();
            }
            userInterface.printArray(printedEvents);
        } catch (EmptyListException e) {
            userInterface.showToUser(Messages.MESSAGE_EMPTY_SCHEDULE_LIST);
        } catch (DateTimeParseException e) {
            System.out.println("☹ OOPS!!! Please enter valid date and time in format yyyy-mm-dd or today!");
        }
    }

    public ArrayList<ArrayList<Event>> getCurrentWeekEventMasterList() {
        DateTimeParser dateTimeParser = new DateTimeParser();
        ArrayList<Event> eventMasterList = getEventMasterList();
        ArrayList<String> daysOfWeek = dateTimeParser.getDaysOfWeek();
        ArrayList<ArrayList<Event>> result = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            result.add(getDayEventList(eventMasterList, daysOfWeek.get(i)));
        }

        return result;
    }

    private ArrayList<Event> getDayEventList(ArrayList<Event> masterList, String date) {
        DateTimeParser dateTimeParser = new DateTimeParser();
        ArrayList<Event> result = new ArrayList<>();

        for (Event event : masterList) {
            String[] listDate = event.getStart().split(" ");
            if (dateTimeParser.isDateEqual(date, listDate[0])) {
                result.add(event);
            }
        }

        return result;
    }

    public ArrayList<Event> getEventMasterList() {
        ArrayList<Event> ccas = eventCcaManager.getCcas();
        ArrayList<Event> tests = eventTestManager.getTests();
        ArrayList<Event> classes = eventClassManager.getClasses();
        ArrayList<Event> tuitions = eventTuitionManager.getTuitions();

        ArrayList<Event> masterList = new ArrayList<>(ccas);
        masterList.addAll(tests);
        masterList.addAll(classes);
        masterList.addAll(tuitions);

        return masterList;
    }

    /**
     * Prints to user all the found events that matches with keyword provided.
     *
     * @param userInput Input supplied by the user that contains the keywords.
     * @throws MissingParameterException If input supplied does not contain any keywords
     */
    public void findEvents(String userInput) throws MissingParameterException {
        String param = userInput.substring(USER_INPUT_OFFSET).trim();

        if (param.length() == EMPTY_SIZE) {
            throw new MissingParameterException();
        }

        FindSchedule findSchedule = new FindSchedule(param, eventClassManager.getClasses(),
                eventCcaManager.getCcas(), eventTestManager.getTests(), eventTuitionManager.getTuitions());
        ArrayList<String> filteredEvents = findSchedule.getFilteredEvents();

        if (filteredEvents.size() == EMPTY_SIZE) {
            userInterface.showToUser(Messages.MESSAGE_NO_EVENTS_FOUND);
            return;
        }

        userInterface.printArray(filteredEvents);
    }
}
