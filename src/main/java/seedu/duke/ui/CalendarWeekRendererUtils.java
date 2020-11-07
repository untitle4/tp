package seedu.duke.ui;

import com.sun.jdi.ThreadReference;
import seedu.duke.common.Messages;
import seedu.duke.controller.parser.DateTimeParser;
import seedu.duke.model.event.Event;

import java.util.ArrayList;
import java.util.Calendar;

//@@author durianpancakes

/**
 * Utility class containing methods to assist CalendarWeekRenderer to produce the Week View.
 */
public class CalendarWeekRendererUtils {
    private final int[] eventNumberCounter;
    private final int [] eventIndexCounters = {1, 1, 1, 1, 1, 1, 1};
    public static final String TODAY_LABEL = "[TODAY]";
    public static final int COLUMN_SPACES = 17;
    public static final int DESCRIPTION_THRESHOLD = 14;
    public static final int DAY_LABEL_SPACES_OFFSET = 6;
    public static final int DATE_LABEL_SPACES_OFFSET = 5;
    public static final int TODAY_LABEL_SPACES_OFFSET = 5;
    public static final int NO_EVENTS = 0;
    public static final int ONE_EVENT = 1;
    public static final int INDEX_OFFSET = 1;
    public static final int MONDAY = 0;
    public static final int TUESDAY = 1;
    public static final int WEDNESDAY = 2;
    public static final int THURSDAY = 3;
    public static final int FRIDAY = 4;
    public static final int SATURDAY = 5;
    public static final int SUNDAY = 6;

    /**
     * Constructs a CalendarWeekRendererUtils instance.
     *
     * @param weekMasterList ArrayList of ArrayList of Events containing all events in the week.
     */
    public CalendarWeekRendererUtils(ArrayList<ArrayList<Event>> weekMasterList) {
        eventNumberCounter = new int[7];
        this.eventNumberCounter[MONDAY] = weekMasterList.get(MONDAY).size();
        this.eventNumberCounter[TUESDAY] = weekMasterList.get(TUESDAY).size();
        this.eventNumberCounter[WEDNESDAY] = weekMasterList.get(WEDNESDAY).size();
        this.eventNumberCounter[THURSDAY] = weekMasterList.get(THURSDAY).size();
        this.eventNumberCounter[FRIDAY] = weekMasterList.get(FRIDAY).size();
        this.eventNumberCounter[SATURDAY] = weekMasterList.get(SATURDAY).size();
        this.eventNumberCounter[SUNDAY] = weekMasterList.get(SUNDAY).size();
    }

    /**
     * Reduces the count of a particular eventNumberCounter by 1.
     *
     * @param counterIndex int containing the counter to be reduced.
     */
    private void reduceCounter(int counterIndex) {
        eventNumberCounter[counterIndex]--;
    }

    private int getCounter(int counterIndex) {
        return eventNumberCounter[counterIndex];
    }

    /**
     * Helper function to check if there are any Events left to be printed.
     *
     * @return boolean, true if there is something left to print; false if there is nothing left to print.
     */
    public boolean isThereNothingLeftToPrint() {
        return eventNumberCounter[MONDAY] == NO_EVENTS && eventNumberCounter[TUESDAY] == NO_EVENTS
                && eventNumberCounter[WEDNESDAY] == NO_EVENTS && eventNumberCounter[THURSDAY] == NO_EVENTS
                && eventNumberCounter[FRIDAY] == NO_EVENTS && eventNumberCounter[SATURDAY] == NO_EVENTS
                && eventNumberCounter[SUNDAY] == NO_EVENTS;
    }

    /**
     * Obtain the day label of a given day.
     *
     * @param num int containing the day number based on the Calendar API.
     * @return String containing the day label based on the day number provided.
     */
    public String getDayLabel(int num) {
        switch (num) {
        case 0:
            return Messages.MESSAGE_MONDAY_LABEL;
        case 1:
            return Messages.MESSAGE_TUESDAY_LABEL;
        case 2:
            return Messages.MESSAGE_WEDNESDAY_LABEL;
        case 3:
            return Messages.MESSAGE_THURSDAY_LABEL;
        case 4:
            return Messages.MESSAGE_FRIDAY_LABEL;
        case 5:
            return Messages.MESSAGE_SATURDAY_LABEL;
        case 6:
            return Messages.MESSAGE_SUNDAY_LABEL;
        default:
            return null;
        }
    }

    /**
     * Obtain the Event's duration in NUM "h" NUM "m" format.
     *
     * @param start Calendar containing the start time of the Event.
     * @param end Calendar containing the end time of the Event.
     * @return String containing the Event's duration formatted in " h m".
     */
    public String getEventDuration(Calendar start, Calendar end) {
        int hours = end.get(Calendar.HOUR_OF_DAY) - start.get(Calendar.HOUR_OF_DAY);
        int minutes = end.get(Calendar.MINUTE) - end.get(Calendar.MINUTE);

        return hours + "h" + minutes + "m";
    }

    /**
     * Checks if the date given is today and obtains the Today label if needed.
     *
     * @param compareCalendar Calendar containing the Date of the day to be checked.
     * @return String containing the TODAY_LABEL if the given Calendar has the same day, month and year, else, return
     *     a string containing COLUMN_GAP number of whitespaces.
     */
    public String getIsToday(Calendar compareCalendar) {
        Calendar today = Calendar.getInstance();
        DateTimeParser dateTimeParser = new DateTimeParser();
        if (dateTimeParser.isDateEqual(compareCalendar, today)) {
            return getSpaces(TODAY_LABEL_SPACES_OFFSET) + TODAY_LABEL + getSpaces(TODAY_LABEL_SPACES_OFFSET);
        } else {
            return getSpaces(COLUMN_SPACES);
        }
    }

    /**
     * Helper method to obtain a String containing a given number of whitespaces.
     *
     * @param num int containing the number of spaces in the String to be returned.
     * @return String containing the number of spaces required.
     */
    public String getSpaces(int num) {
        return " ".repeat(Math.max(0, num));
    }

    /**
     * Obtain the Date label of a given day in the week.
     *
     * @param daysOfWeek ArrayList containing the Calendars of a week.
     * @param num int containing the Day number (0 for Monday, 6 for Sunday).
     * @return String containing the Day label formatted in [DD-MM].
     */
    public String getDateLabel(ArrayList<Calendar> daysOfWeek, int num) {
        Calendar calendar = daysOfWeek.get(num);
        DateTimeParser dateTimeParser = new DateTimeParser();
        return "[" + dateTimeParser.obtainFormattedDayAndMonthString(calendar) + "]";
    }

    /**
     * Obtain the Event Icon of Event to be rendered.
     *
     * @param events ArrayList containing Events of the week.
     * @param counterIndex int containing the day's event to be printed.
     * @return String containing the Event's Icon, if any. Else, returns a string containing COLUMN_GAP number of
     *     whitespaces.
     */
    public String getEventIcons(ArrayList<Event> events, int counterIndex) {
        StringBuilder eventIconsString = new StringBuilder();

        if (getCounter(counterIndex) != NO_EVENTS) {
            String indexString = eventIndexCounters[counterIndex] + ".";
            String iconString = events.get(eventIndexCounters[counterIndex] - INDEX_OFFSET).getIcon();
            eventIconsString.append(indexString);
            eventIconsString.append(iconString);
            eventIconsString.append(getSpaces(COLUMN_SPACES - iconString.length() - indexString.length()));
        } else {
            eventIconsString.append(getSpaces(COLUMN_SPACES));
        }

        return eventIconsString.toString();
    }

    /**
     * Obtain the Event's description to be rendered.
     *
     * @param events ArrayList containing Events of the week.
     * @param counterIndex int containing the day's event to be printed, if any.
     * @return String containing the Event's description, if any. Else, returns a string containing COLUMN_GAP number of
     *     whitespaces.
     */
    public String getDescriptions(ArrayList<Event> events, int counterIndex) {
        StringBuilder descriptionsString = new StringBuilder();

        if (getCounter(counterIndex) != NO_EVENTS) {
            String indexString = eventIndexCounters[counterIndex] + ".";
            int indexStringLength = indexString.length();
            descriptionsString.append(getSpaces(indexStringLength));
            String descriptionString = events.get(eventIndexCounters[counterIndex] - INDEX_OFFSET).getDescription();
            if (descriptionString.length() > DESCRIPTION_THRESHOLD) {
                // Truncate the string as the description is too long
                descriptionString = descriptionString.substring(0, 11) + "...";
            }
            descriptionsString.append(descriptionString);
            descriptionsString.append(getSpaces(COLUMN_SPACES - descriptionString.length() - indexStringLength));
        } else {
            descriptionsString.append(getSpaces(COLUMN_SPACES));
        }

        return descriptionsString.toString();
    }

    /**
     * Obtain the Event's start end time to be rendered.
     *
     * @param events ArrayList containing Events of the week.
     * @param counterIndex int containing the day's event to be printed, if any.
     * @return String containing the Event's start end time, if any. Else, returns a string containing COLUMN_GAP
     *     number of whitespaces.
     */
    public String getStartEndTime(ArrayList<Event> events, int counterIndex) {
        StringBuilder startEndTimesString = new StringBuilder();

        if (getCounter(counterIndex) != NO_EVENTS) {
            String indexString = eventIndexCounters[counterIndex] + ".";
            int indexStringLength = indexString.length();
            startEndTimesString.append(getSpaces(indexStringLength));
            Calendar startCalendar = events.get(eventIndexCounters[counterIndex] - INDEX_OFFSET).getStart();
            Calendar endCalendar = events.get(eventIndexCounters[counterIndex] - INDEX_OFFSET).getEnd();
            DateTimeParser dateTimeParser = new DateTimeParser();
            String startString = dateTimeParser.parseTime(startCalendar);
            String endString = dateTimeParser.parseTime(endCalendar);
            String startEndString = startString + "-" + endString;
            startEndTimesString.append(startEndString);
            startEndTimesString.append(getSpaces(COLUMN_SPACES - startEndString.length() - indexStringLength));
        } else {
            startEndTimesString.append(getSpaces(COLUMN_SPACES));
        }

        return startEndTimesString.toString();
    }

    /**
     * Obtain the break time between each events to be rendered.
     *
     * @param events ArrayList containing Events of the week.
     * @param counterIndex int containing the day's event to be printed, if any.
     * @return String containing the Event's break time before another event, if any. Else, returns a string
     *     containing COLUMN_GAP number of whitespaces.
     */
    public String getBreakTimeString(ArrayList<Event> events, int counterIndex) {
        StringBuilder breakTimeString = new StringBuilder();
        if (getCounter(counterIndex) != NO_EVENTS) {
            if (getCounter(counterIndex) > ONE_EVENT) {
                String indexString = eventIndexCounters[counterIndex] + ".";
                int indexStringLength = indexString.length();
                breakTimeString.append(getSpaces(indexStringLength));
                Calendar thisEventEnd = events.get(eventIndexCounters[counterIndex] - INDEX_OFFSET).getEnd();
                Calendar nextEventStart = events.get(eventIndexCounters[counterIndex]).getStart();
                int hours = nextEventStart.get(Calendar.HOUR_OF_DAY) - thisEventEnd.get(Calendar.HOUR_OF_DAY);
                int minutes = nextEventStart.get(Calendar.MINUTE) - thisEventEnd.get(Calendar.MINUTE);
                String breakString = hours + "h" + minutes + "m break";
                breakTimeString.append(breakString);
                breakTimeString.append(getSpaces(COLUMN_SPACES - breakString.length() - indexStringLength));
            } else {
                breakTimeString.append(getSpaces(COLUMN_SPACES));
            }
            reduceCounter(counterIndex);
            eventIndexCounters[counterIndex]++;
        } else {
            breakTimeString.append(getSpaces(COLUMN_SPACES));
        }

        return breakTimeString.toString();
    }
}
