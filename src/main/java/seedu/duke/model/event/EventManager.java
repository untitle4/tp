package seedu.duke.model.event;

import seedu.duke.common.LogManager;
import seedu.duke.controller.parser.DateTimeParser;
import seedu.duke.exception.EmptyListException;
import seedu.duke.exception.InvalidDateException;
import seedu.duke.exception.InvalidDateType;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.model.ConfigParameter;
import seedu.duke.model.ModelMain;
import seedu.duke.model.event.cca.EventCcaManager;
import seedu.duke.model.event.classlesson.EventClassManager;
import seedu.duke.common.Messages;
import seedu.duke.model.event.test.EventTestManager;
import seedu.duke.model.event.tuition.EventTuitionManager;
import seedu.duke.ui.UserInterface;

import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author AndreWongZH
/**
 * Represents a handler that manages the four different event managers.
 * This provides access to each individual event managers and
 * also performs listing and searches for the entire events data set.
 */
public class EventManager extends ModelMain implements EventManagerInteractable {
    public static final int EMPTY_SIZE = 0;
    public static final int USER_INPUT_OFFSET = 10;
    public static final String INPUT_SPACE = " ";
    public static final String INPUT_WEEK = "week";
    public static final int INPUT_LENGTH_NO_PARAMS = 2;
    public static final int INPUT_LENGTH_ONE_PARAM = 3;
    public static final int DATE_PARAM_INDEX = 2;
    public static final String INPUT_NEXTWEEK = "nextweek";
    private static EventClassManager eventClassManager;
    private static EventTestManager eventTestManager;
    private static EventCcaManager eventCcaManager;
    private static EventTuitionManager eventTuitionManager;
    private final UserInterface userInterface;
    private static final Logger logger = LogManager.getLogManagerInstance().getLogger();
    private final ConfigParameter configParameter;
    DateTimeParser dateTimeParser = new DateTimeParser();

    public EventManager(EventParameter eventParameter, ConfigParameter configParameter) {
        eventClassManager = new EventClassManager(eventParameter.getClasses(), this);
        eventTestManager = new EventTestManager(eventParameter.getTests(), this);
        eventCcaManager = new EventCcaManager(eventParameter.getCcas(), this);
        eventTuitionManager = new EventTuitionManager(eventParameter.getTuitions(), this);
        userInterface = UserInterface.getInstance();
        this.configParameter = configParameter;
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

    //@@author AndreWongZH
    /**
     * Prints to user all the found events that matches with keyword provided.
     *
     * @param userInput Input supplied by the user that contains the keywords.
     * @throws MissingParameterException If input supplied does not contain any keywords.
     */
    @Override
    public void find(String userInput) throws MissingParameterException {
        String param = userInput.substring(USER_INPUT_OFFSET).trim();

        if (param.length() == EMPTY_SIZE) {
            throw new MissingParameterException("keywords as");
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

    //@@author AndreWongZH
    @Override
    public void list(String userInput) {
        ArrayList<String> printedEvents;
        try {
            String[] separatedInputs = userInput.split(INPUT_SPACE);

            // check if user entered extra parameters
            if (separatedInputs.length > INPUT_LENGTH_ONE_PARAM) {
                userInterface.showToUser(Messages.MESSAGE_INVALID_EXTRA_PARAM);
                return;
            }

            String dateParam =  separatedInputs.length == INPUT_LENGTH_NO_PARAMS
                    ? null
                    : separatedInputs[DATE_PARAM_INDEX];

            ListSchedule listSchedule = new ListSchedule(dateParam, eventClassManager.getClasses(),
                    eventCcaManager.getCcas(), eventTestManager.getTests(),
                    eventTuitionManager.getTuitions(), configParameter);

            if (separatedInputs.length == INPUT_LENGTH_ONE_PARAM
                    && separatedInputs[DATE_PARAM_INDEX].contentEquals(INPUT_NEXTWEEK)) {
                userInterface.printWeekSchedule(this, ListWeekCommand.NEXT_WEEK);
            } else if (separatedInputs.length == INPUT_LENGTH_ONE_PARAM
                    && separatedInputs[DATE_PARAM_INDEX].contentEquals(INPUT_WEEK)) {
                userInterface.printWeekSchedule(this, ListWeekCommand.CURRENT_WEEK);
            } else {
                printedEvents = listSchedule.getPrintableEvents();
                userInterface.printArray(printedEvents);
            }
        } catch (EmptyListException e) {
            userInterface.showToUser(String.format(Messages.MESSAGE_EMPTY_SCHEDULE_LIST, e.getMessage()));
        } catch (DateTimeParseException e) {
            userInterface.showToUser(Messages.MESSAGE_LIST_INVALID_DATE);
        } catch (ParseException e) {
            logger.log(Level.WARNING, "valid datetime not inputted");
            userInterface.showToUser(Messages.MESSAGE_LIST_INVALID_DATE);
        }
    }

    //@@author durianpancakes
    public ArrayList<ArrayList<Event>> getCurrentWeekEventMasterList() {
        DateTimeParser dateTimeParser = new DateTimeParser();
        ArrayList<Event> eventMasterList = getEventMasterList();
        ArrayList<Calendar> daysOfWeek = dateTimeParser.getCurrentDaysOfWeek();
        ArrayList<ArrayList<Event>> result = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            result.add(getDayEventList(eventMasterList, daysOfWeek.get(i)));
        }

        return result;
    }

    public ArrayList<ArrayList<Event>> getNextWeekEventMasterList() {
        DateTimeParser dateTimeParser = new DateTimeParser();
        ArrayList<Event> eventMasterList = getEventMasterList();
        ArrayList<Calendar> daysOfWeek = dateTimeParser.getNextDaysOfWeek();
        ArrayList<ArrayList<Event>> result = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            result.add(getDayEventList(eventMasterList, daysOfWeek.get(i)));
        }

        return result;
    }

    //@@author Aliciaho
    /**
     * Adds the relevant events whose date correspond to the date inputted in the masterList.
     *
     * @param masterList ArrayList containing all the events
     * @param date Date inputted to filter out the corresponding events
     * @return result ArrayList contain the relevant events for that date
    */
    private ArrayList<Event> getDayEventList(ArrayList<Event> masterList, Calendar date) {
        assert masterList.size() >= 0;
        assert date != null;
        DateTimeParser dateTimeParser = new DateTimeParser();
        ArrayList<Event> result = new ArrayList<>();

        for (Event event : masterList) {
            Calendar startCalendar = event.getStart();
            if (dateTimeParser.isDateEqual(date, startCalendar)) {
                result.add(event);
            }
        }

        return result;
    }

    //@@author Aliciaho
    /**
     * Adds all the ccas, classes, tests and tuitions into one Master ArrayList.
     *
     * @return masterList ArrayList containing all the events
     */
    public ArrayList<Event> getEventMasterList() {
        logger.log(Level.INFO, "getting all ccas, classes, tests and tuitions");
        ArrayList<Event> ccas = eventCcaManager.getCcas();
        ArrayList<Event> tests = eventTestManager.getTests();
        ArrayList<Event> classes = eventClassManager.getClasses();
        ArrayList<Event> tuitions = eventTuitionManager.getTuitions();

        ArrayList<Event> masterList = new ArrayList<>(ccas);
        masterList.addAll(tests);
        masterList.addAll(classes);
        masterList.addAll(tuitions);
        logger.log(Level.INFO, "added all ccas, classes, tests and tuitions");

        return masterList;
    }

    public ArrayList<Event> checkEventClash(Event event) {
        ArrayList<Event> relevantEvents = getDayEventList(getEventMasterList(), event.getStart());
        ArrayList<Event> results = new ArrayList<>();

        for (Event e : relevantEvents) {
            if (isTimeClash(e, event)) {
                results.add(e);
            }
        }

        return results;
    }

    public void processInvalidDateException(InvalidDateType errorCode) {
        switch (errorCode) {
        case START_AFTER_END:
            userInterface.showToUser(Messages.MESSAGE_ERROR_START_AFTER_END);
            break;
        case START_EQUALS_END:
            userInterface.showToUser(Messages.MESSAGE_ERROR_EQUALS_END);
            break;
        default:
            // No default cases needed here
        }
    }

    // Check if start time given is before end time
    public void checkValidTimeGiven(Event inputEvent) throws InvalidDateException {
        Calendar startCalendar = inputEvent.getStart();
        Calendar endCalendar = inputEvent.getEnd();

        if (startCalendar.equals(endCalendar)) {
            throw new InvalidDateException(InvalidDateType.START_EQUALS_END);
        }

        if (startCalendar.after(endCalendar)) {
            throw new InvalidDateException(InvalidDateType.START_AFTER_END);
        }
    }

    // Check if there are any clashes with other events
    private boolean isTimeClash(Event referenceEvent, Event inputEvent) {
        Calendar startInputCalendar = inputEvent.getStart();
        Calendar endInputCalendar = inputEvent.getEnd();
        Calendar startReferenceCalendar = referenceEvent.getStart();
        Calendar endReferenceCalendar = referenceEvent.getEnd();

        if (startInputCalendar.after(startReferenceCalendar)
                && startInputCalendar.before(endReferenceCalendar)) {
            return true;
        }

        if (endInputCalendar.after((startReferenceCalendar))
                && endInputCalendar.before(endReferenceCalendar)) {
            return true;
        }

        if (startInputCalendar.equals(startReferenceCalendar)
                || endInputCalendar.equals(endReferenceCalendar)) {
            return true;
        }

        return false;
    }

    /**
     * Checks if the recommended time for that day exceeded.
     *
     * @param event Event that user is trying to add
     * @return true if the time did exceed, vice versa.
     */
    public boolean didTimeExceed(Event event) {
        logger.log(Level.INFO, "checking if time exceeded");
        ArrayList<Event> eventArrayList = getEventMasterList();
        long noOfMinutes = dateTimeParser.getDuration(event.getStart(),event.getEnd());
        noOfMinutes += getNoOfMinutes(event, eventArrayList);
        return noOfMinutes > (configParameter.getRecommendedHours() * 60);
    }

    /**
     * Get the total number of productive minutes for a particular day.
     *
     * @param event Event that user is trying to add
     * @param eventArrayList Masterlist containing all the events
     * @return total number of minutes for that day
     */
    private long getNoOfMinutes(Event event, ArrayList<Event> eventArrayList) {
        long noOfMinutes = 0;
        for (int i = 0; i < eventArrayList.size(); i++) {
            if (dateTimeParser.isDateEqual(eventArrayList.get(i).getStart(),
                    event.getStart())) {
                noOfMinutes += dateTimeParser.getDuration(eventArrayList.get(i).getStart(),
                        eventArrayList.get(i).getEnd());
            }
        }
        return noOfMinutes;
    }

    /**
     * Get the time left for each day.
     *
     * @param event Event that user is trying to add
     * @return string containing the time left for that particular day
     */
    public String getTimeLeft(Event event) {
        logger.log(Level.INFO, "checking time left for that day");
        ArrayList<Event> eventArrayList = getEventMasterList();
        long noOfMinutes = getNoOfMinutes(event, eventArrayList);
        long noOfMinutesLeft = (configParameter.getRecommendedHours() * 60) - noOfMinutes;
        int hoursLeft = Math.toIntExact(noOfMinutesLeft / 60);
        int actualNoOfMinutesLeft = Math.toIntExact(noOfMinutesLeft - (hoursLeft * 60));
        return hoursLeft + "hr " + actualNoOfMinutesLeft + "mins";
    }
}
