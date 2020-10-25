package seedu.duke.ui;

import seedu.duke.controller.parser.DateTimeParser;
import seedu.duke.model.event.Event;
import seedu.duke.model.event.EventManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

//@@author durianpancakes
public class CalendarWeekRenderer {
    private final EventManager eventManager;
    private final int [] eventCounters = {1, 1, 1, 1, 1, 1, 1};
    private final UserInterface userInterface;

    public CalendarWeekRenderer(EventManager eventManager) {
        this.eventManager = eventManager;
        this.userInterface = UserInterface.getInstance();
        renderWeekSchedule();
    }

    private void renderWeekSchedule() {
        ArrayList<ArrayList<Event>> weekMasterList = eventManager.getCurrentWeekEventMasterList();
        DateTimeParser dateTimeParser = new DateTimeParser();
        ArrayList<Calendar> daysOfWeek = dateTimeParser.getDaysOfWeek();
        CalendarWeekRendererUtils utils = new CalendarWeekRendererUtils(weekMasterList);

        // 17 spaces per day

        // Printing DAY headers
        // 6 spaces before each DAY_LABEL
        StringBuilder dayLabelString = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            dayLabelString.append(getSpaces(6));
            dayLabelString.append(utils.getDayLabel(i));
            dayLabelString.append(getSpaces(6));
        }

        userInterface.showToUser(dayLabelString.toString());

        // Printing DATE headers
        StringBuilder dateHeaderString = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            dateHeaderString.append(getSpaces(5));
            dateHeaderString.append(getDateLabel(daysOfWeek, i));
            dateHeaderString.append(getSpaces(5));
        }

        userInterface.showToUser(dateHeaderString.toString());

        while (!utils.isThereNothingLeftToPrint()) {
            // Print in order of ICON -> DESCRIPTION -> START-END TIMES
            // NOTE: DOES NOT SUPPORT TUITION LOCATION YET
            StringBuilder eventParamsString = new StringBuilder();
            for (int i = 0; i < 7; i++) {
                // Count from Monday to Sunday
                // One Event takes up 5 columns max, with one space between each column
                eventParamsString.append(getEventIcons(utils, weekMasterList.get(i), i));
            }

            userInterface.showToUser(eventParamsString.toString());

            eventParamsString = new StringBuilder();
            for (int i = 0; i < 7; i++) {
                // Count from Monday to Sunday
                // One Event takes up 5 columns max, with one space between each column
                eventParamsString.append(getDescriptions(utils, weekMasterList.get(i), i));
            }

            userInterface.showToUser(eventParamsString.toString());

            eventParamsString = new StringBuilder();
            for (int i = 0; i < 7; i++) {
                // Count from Monday to Sunday
                // One Event takes up 5 columns max, with one space between each column
                eventParamsString.append(getStartEndTime(utils, weekMasterList.get(i), i));
            }

            userInterface.showToUser(eventParamsString.toString());
        }
    }

    private String getEventIcons(CalendarWeekRendererUtils utils, ArrayList<Event> events, int counterIndex) {
        StringBuilder eventIconsString = new StringBuilder();

        if (utils.getCounter(counterIndex) != 0) {
            String indexString = eventCounters[counterIndex] + ".";
            String iconString = events.get(eventCounters[counterIndex] - 1).getIcon();
            eventIconsString.append(indexString);
            eventIconsString.append(iconString);
            eventIconsString.append(getSpaces(17 - iconString.length() - indexString.length()));
        } else {
            eventIconsString.append(getSpaces(17));
        }

        return eventIconsString.toString();
    }

    private String getDescriptions(CalendarWeekRendererUtils utils, ArrayList<Event> events, int counterIndex) {
        StringBuilder descriptionsString = new StringBuilder();

        if (utils.getCounter(counterIndex) != 0) {
            String indexString = eventCounters[counterIndex] + ".";
            int indexStringLength = indexString.length();
            descriptionsString.append(getSpaces(indexStringLength));
            String descriptionString = events.get(eventCounters[counterIndex] - 1).getDescription();
            if (descriptionString.length() > 14) {
                descriptionString = descriptionString.substring(0, 11) + "...";
            }
            descriptionsString.append(descriptionString);
            descriptionsString.append(getSpaces(17 - descriptionString.length() - indexStringLength));
        } else {
            descriptionsString.append(getSpaces(17));
        }

        return descriptionsString.toString();
    }

    private String getStartEndTime(CalendarWeekRendererUtils utils, ArrayList<Event> events, int counterIndex) {
        StringBuilder startEndTimesString = new StringBuilder();

        if (utils.getCounter(counterIndex) != 0) {
            String indexString = eventCounters[counterIndex] + ".";
            int indexStringLength = indexString.length();
            startEndTimesString.append(getSpaces(indexStringLength));
            Calendar startCalendar = events.get(eventCounters[counterIndex] - 1).getStart();
            Calendar endCalendar = events.get(eventCounters[counterIndex] - 1).getEnd();
            DateTimeParser dateTimeParser = new DateTimeParser();
            String startString = dateTimeParser.parseTime(startCalendar);
            String endString = dateTimeParser.parseTime(endCalendar);
            String startEndString = startString + "-" + endString;
            startEndTimesString.append(startEndString);
            startEndTimesString.append(getSpaces(17 - startEndString.length() - indexStringLength));
            utils.reduceCounter(counterIndex);
            eventCounters[counterIndex]++;
        } else {
            startEndTimesString.append(getSpaces(17));
        }

        return startEndTimesString.toString();
    }

    private String getSpaces(int num) {
        return " ".repeat(Math.max(0, num));
    }

    private String getDateLabel(ArrayList<Calendar> daysOfWeek, int num) {
        Calendar calendar = daysOfWeek.get(num);
        DateTimeParser dateTimeParser = new DateTimeParser();
        return "[" + dateTimeParser.obtainFormattedDayAndMonthString(calendar) + "]";
    }
}
