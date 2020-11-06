package seedu.duke.ui;

import seedu.duke.common.Messages;
import seedu.duke.controller.parser.DateTimeParser;
import seedu.duke.model.event.Event;

import java.util.ArrayList;
import java.util.Calendar;

//@@author durianpancakes
public class CalendarWeekRendererUtils {
    private final int[] eventNumberCounter;
    private final int [] eventIndexCounters = {1, 1, 1, 1, 1, 1, 1};
    public static final String TODAY_LABEL = "[TODAY]";
    public static final int COLUMN_SPACES = 17;
    public static final int DESCRIPTION_THRESHOLD = 14;

    public CalendarWeekRendererUtils(ArrayList<ArrayList<Event>> weekMasterList) {
        eventNumberCounter = new int[7];
        this.eventNumberCounter[0] = weekMasterList.get(0).size();
        this.eventNumberCounter[1] = weekMasterList.get(1).size();
        this.eventNumberCounter[2] = weekMasterList.get(2).size();
        this.eventNumberCounter[3] = weekMasterList.get(3).size();
        this.eventNumberCounter[4] = weekMasterList.get(4).size();
        this.eventNumberCounter[5] = weekMasterList.get(5).size();
        this.eventNumberCounter[6] = weekMasterList.get(6).size();
    }

    public void reduceCounter(int counterIndex) {
        eventNumberCounter[counterIndex]--;
    }

    public int getCounter(int counterIndex) {
        return eventNumberCounter[counterIndex];
    }

    public boolean isThereNothingLeftToPrint() {
        return eventNumberCounter[0] == 0 && eventNumberCounter[1] == 0 && eventNumberCounter[2] == 0
                && eventNumberCounter[3] == 0 && eventNumberCounter[4] == 0 && eventNumberCounter[5] == 0
                && eventNumberCounter[6] == 0;
    }

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

    public String getEventDuration(Calendar start, Calendar end) {
        int hours = end.get(Calendar.HOUR_OF_DAY) - start.get(Calendar.HOUR_OF_DAY);
        int minutes = end.get(Calendar.MINUTE) - end.get(Calendar.MINUTE);

        return hours + "h" + minutes + "m";
    }

    public String getIsToday(Calendar compareCalendar) {
        Calendar today = Calendar.getInstance();
        DateTimeParser dateTimeParser = new DateTimeParser();
        if (dateTimeParser.isDateEqual(compareCalendar, today)) {
            return getSpaces(5) + TODAY_LABEL + getSpaces(5);
        } else {
            return getSpaces(COLUMN_SPACES);
        }
    }

    public String getSpaces(int num) {
        return " ".repeat(Math.max(0, num));
    }

    public String getDateLabel(ArrayList<Calendar> daysOfWeek, int num) {
        Calendar calendar = daysOfWeek.get(num);
        DateTimeParser dateTimeParser = new DateTimeParser();
        return "[" + dateTimeParser.obtainFormattedDayAndMonthString(calendar) + "]";
    }

    public String getEventIcons(ArrayList<Event> events, int counterIndex) {
        StringBuilder eventIconsString = new StringBuilder();

        if (getCounter(counterIndex) != 0) {
            String indexString = eventIndexCounters[counterIndex] + ".";
            String iconString = events.get(eventIndexCounters[counterIndex] - 1).getIcon();
            eventIconsString.append(indexString);
            eventIconsString.append(iconString);
            eventIconsString.append(getSpaces(COLUMN_SPACES - iconString.length() - indexString.length()));
        } else {
            eventIconsString.append(getSpaces(COLUMN_SPACES));
        }

        return eventIconsString.toString();
    }

    public String getDescriptions(ArrayList<Event> events, int counterIndex) {
        StringBuilder descriptionsString = new StringBuilder();

        if (getCounter(counterIndex) != 0) {
            String indexString = eventIndexCounters[counterIndex] + ".";
            int indexStringLength = indexString.length();
            descriptionsString.append(getSpaces(indexStringLength));
            String descriptionString = events.get(eventIndexCounters[counterIndex] - 1).getDescription();
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

    public String getStartEndTime(ArrayList<Event> events, int counterIndex) {
        StringBuilder startEndTimesString = new StringBuilder();

        if (getCounter(counterIndex) != 0) {
            String indexString = eventIndexCounters[counterIndex] + ".";
            int indexStringLength = indexString.length();
            startEndTimesString.append(getSpaces(indexStringLength));
            Calendar startCalendar = events.get(eventIndexCounters[counterIndex] - 1).getStart();
            Calendar endCalendar = events.get(eventIndexCounters[counterIndex] - 1).getEnd();
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

    public String getBreakTimeString(ArrayList<Event> events, int counterIndex) {
        StringBuilder breakTimeString = new StringBuilder();
        if (getCounter(counterIndex) != 0) {
            if (getCounter(counterIndex) > 1) {
                String indexString = eventIndexCounters[counterIndex] + ".";
                int indexStringLength = indexString.length();
                breakTimeString.append(getSpaces(indexStringLength));
                Calendar thisEventEnd = events.get(eventIndexCounters[counterIndex] - 1).getEnd();
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
