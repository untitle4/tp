package seedu.duke.ui;

import seedu.duke.controller.parser.DateTimeParser;
import seedu.duke.model.event.Event;
import seedu.duke.model.event.EventManager;
import seedu.duke.model.event.ListWeekCommand;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

//@@author durianpancakes
public class CalendarWeekRenderer {
    private final EventManager eventManager;
    private final int [] eventCounters = {1, 1, 1, 1, 1, 1, 1};
    private final UserInterface userInterface;
    private static final int DAYS_IN_WEEK = 7;
    public static final String EMPTY_STRING = "";

    public CalendarWeekRenderer(EventManager eventManager, ListWeekCommand listWeekCommand) {
        this.eventManager = eventManager;
        this.userInterface = UserInterface.getInstance();
        renderWeekSchedule(listWeekCommand);
    }

    private void renderWeekSchedule(ListWeekCommand listWeekCommand) {
        Calendar calendar = Calendar.getInstance();
        ArrayList<ArrayList<Event>> weekMasterList = new ArrayList<>();
        ArrayList<Calendar> daysOfWeek = null;
        DateTimeParser dateTimeParser = new DateTimeParser();

        if (listWeekCommand == ListWeekCommand.CURRENT_WEEK) {
            weekMasterList = eventManager.getCurrentWeekEventMasterList();
            daysOfWeek = dateTimeParser.getDaysOfWeek(calendar);
        } else if (listWeekCommand == ListWeekCommand.NEXT_WEEK) {
            calendar.add(Calendar.DAY_OF_MONTH, 7);
            weekMasterList = eventManager.getNextWeekEventMasterList();
            daysOfWeek = dateTimeParser.getDaysOfWeek(calendar);
        }

        CalendarWeekRendererUtils utils = new CalendarWeekRendererUtils(weekMasterList);

        // Sort master list before proceeding
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            Collections.sort(weekMasterList.get(i));
        }

        // 17 spaces per day

        // Printing DAY headers
        // 6 spaces before each DAY_LABEL
        StringBuilder dayLabelString = new StringBuilder();
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            dayLabelString.append(getSpaces(6));
            dayLabelString.append(utils.getDayLabel(i));
            dayLabelString.append(getSpaces(6));
        }

        userInterface.showToUser(dayLabelString.toString());

        StringBuilder todayLabelString = new StringBuilder();
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            todayLabelString.append(utils.getIsToday(daysOfWeek.get(i)));
        }
        userInterface.showToUser(todayLabelString.toString());

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
            StringBuilder eventIconString = new StringBuilder();
            for (int i = 0; i < 7; i++) {
                // Count from Monday to Sunday
                // One Event takes up 5 columns max, with one space between each column
                eventIconString.append(getEventIcons(utils, weekMasterList.get(i), i));
            }

            StringBuilder eventDescriptionString = new StringBuilder();
            for (int i = 0; i < 7; i++) {
                // Count from Monday to Sunday
                // One Event takes up 5 columns max, with one space between each column
                eventDescriptionString.append(getDescriptions(utils, weekMasterList.get(i), i));
            }

            StringBuilder eventStartEndString = new StringBuilder();
            for (int i = 0; i < 7; i++) {
                // Count from Monday to Sunday
                // One Event takes up 5 columns max, with one space between each column
                eventStartEndString.append(getStartEndTime(utils, weekMasterList.get(i), i));
            }

            StringBuilder breakTimeString = new StringBuilder();
            for (int i = 0; i < DAYS_IN_WEEK; i++) {
                // Count from Monday to Sunday
                // One Event takes up 5 columns max, with one space between each column
                breakTimeString.append(getBreakTimeString(utils, weekMasterList.get(i), i));
            }

            userInterface.showToUser(eventIconString.toString(),
                    eventDescriptionString.toString(),
                    eventStartEndString.toString(),
                    EMPTY_STRING,
                    breakTimeString.toString(),
                    EMPTY_STRING);
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
        } else {
            startEndTimesString.append(getSpaces(17));
        }

        return startEndTimesString.toString();
    }

    private String getBreakTimeString(CalendarWeekRendererUtils utils, ArrayList<Event> events, int counterIndex) {
        StringBuilder breakTimeString = new StringBuilder();
        if (utils.getCounter(counterIndex) != 0) {
            if (utils.getCounter(counterIndex) > 1) {
                String indexString = eventCounters[counterIndex] + ".";
                int indexStringLength = indexString.length();
                breakTimeString.append(getSpaces(indexStringLength));
                Calendar thisEventEnd = events.get(eventCounters[counterIndex] - 1).getEnd();
                Calendar nextEventStart = events.get(eventCounters[counterIndex]).getStart();
                int hours = nextEventStart.get(Calendar.HOUR_OF_DAY) - thisEventEnd.get(Calendar.HOUR_OF_DAY);
                int minutes = nextEventStart.get(Calendar.MINUTE) - thisEventEnd.get(Calendar.MINUTE);
                String breakString = hours + "h" + minutes + "m break";
                breakTimeString.append(breakString);
                breakTimeString.append(getSpaces(17 - breakString.length() - indexStringLength));
            } else {
                breakTimeString.append(getSpaces(17));
            }
            utils.reduceCounter(counterIndex);
            eventCounters[counterIndex]++;
        } else {
            breakTimeString.append(getSpaces(17));
        }

        return breakTimeString.toString();
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
