package seedu.duke.ui;

import seedu.duke.controller.parser.DateTimeParser;
import seedu.duke.model.event.Event;
import seedu.duke.model.event.EventManager;
import seedu.duke.model.event.ListWeekCommand;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import static seedu.duke.ui.CalendarWeekRendererUtils.DATE_LABEL_SPACES_OFFSET;
import static seedu.duke.ui.CalendarWeekRendererUtils.DAY_LABEL_SPACES_OFFSET;

//@@author durianpancakes

/**
 * Driver class for rendering the Week View of the `list event week` command.
 */
public class CalendarWeekRenderer {
    private final EventManager eventManager;
    private final UserInterface userInterface;
    private static final int DAYS_IN_WEEK = 7;
    public static final String EMPTY_STRING = "";
    private CalendarWeekRendererUtils utils;

    /**
     * Constructs a CalendarWeekRenderer instance.
     * Automatically calls renderWeekSchedule() which produces the Week View.
     *
     * @param eventManager EventManager instance for the usage of certain helper methods in the class.
     * @param listWeekCommand ListWeekCommand to identify if the current or next week is to be listed.
     */
    public CalendarWeekRenderer(EventManager eventManager, ListWeekCommand listWeekCommand) {
        this.eventManager = eventManager;
        this.userInterface = UserInterface.getInstance();
        renderWeekSchedule(listWeekCommand);
    }

    /**
     * Driver method to produce the Week View.
     *
     * @param listWeekCommand ListWeekCommand to identify if the current or next week is to be listed.
     */
    private void renderWeekSchedule(ListWeekCommand listWeekCommand) {
        Calendar calendar = Calendar.getInstance();
        ArrayList<ArrayList<Event>> weekMasterList = new ArrayList<>();
        ArrayList<Calendar> daysOfWeek = null;
        DateTimeParser dateTimeParser = new DateTimeParser();

        if (listWeekCommand == ListWeekCommand.CURRENT_WEEK) {
            weekMasterList = eventManager.getCurrentWeekEventMasterList();
            daysOfWeek = dateTimeParser.getDaysOfWeek(calendar);
        } else if (listWeekCommand == ListWeekCommand.NEXT_WEEK) {
            calendar.add(Calendar.DAY_OF_MONTH, DAYS_IN_WEEK);
            weekMasterList = eventManager.getNextWeekEventMasterList();
            daysOfWeek = dateTimeParser.getDaysOfWeek(calendar);
        }

        utils = new CalendarWeekRendererUtils(weekMasterList);

        // Sort master list before proceeding
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            Collections.sort(weekMasterList.get(i));
        }

        // 17 spaces per day

        // Printing DAY headers
        // 6 spaces before each DAY_LABEL
        StringBuilder dayLabelString = new StringBuilder();
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            dayLabelString.append(utils.getSpaces(DAY_LABEL_SPACES_OFFSET));
            dayLabelString.append(utils.getDayLabel(i));
            dayLabelString.append(utils.getSpaces(DAY_LABEL_SPACES_OFFSET));
        }

        userInterface.showToUser(dayLabelString.toString());

        StringBuilder todayLabelString = new StringBuilder();
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            assert daysOfWeek != null;
            todayLabelString.append(utils.getIsToday(daysOfWeek.get(i)));
        }
        userInterface.showToUser(todayLabelString.toString());

        // Printing DATE headers
        StringBuilder dateHeaderString = new StringBuilder();
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            dateHeaderString.append(utils.getSpaces(DATE_LABEL_SPACES_OFFSET));
            dateHeaderString.append(utils.getDateLabel(daysOfWeek, i));
            dateHeaderString.append(utils.getSpaces(DATE_LABEL_SPACES_OFFSET));
        }
        userInterface.showToUser(dateHeaderString.toString());

        while (!utils.isThereNothingLeftToPrint()) {
            // Print in order of ICON -> DESCRIPTION -> START-END TIMES
            // NOTE: DOES NOT SUPPORT TUITION LOCATION YET
            StringBuilder eventIconString = new StringBuilder();
            for (int i = 0; i < DAYS_IN_WEEK; i++) {
                // Count from Monday to Sunday
                // One Event takes up 5 columns max, with one space between each column
                eventIconString.append(utils.getEventIcons(weekMasterList.get(i), i));
            }

            StringBuilder eventDescriptionString = new StringBuilder();
            for (int i = 0; i < DAYS_IN_WEEK; i++) {
                // Count from Monday to Sunday
                // One Event takes up 5 columns max, with one space between each column
                eventDescriptionString.append(utils.getDescriptions(weekMasterList.get(i), i));
            }

            StringBuilder eventStartEndString = new StringBuilder();
            for (int i = 0; i < DAYS_IN_WEEK; i++) {
                // Count from Monday to Sunday
                // One Event takes up 5 columns max, with one space between each column
                eventStartEndString.append(utils.getStartEndTime(weekMasterList.get(i), i));
            }

            StringBuilder breakTimeString = new StringBuilder();
            for (int i = 0; i < DAYS_IN_WEEK; i++) {
                // Count from Monday to Sunday
                // One Event takes up 5 columns max, with one space between each column
                breakTimeString.append(utils.getBreakTimeString(weekMasterList.get(i), i));
            }

            userInterface.showToUser(eventIconString.toString(),
                    eventDescriptionString.toString(),
                    eventStartEndString.toString(),
                    EMPTY_STRING,
                    breakTimeString.toString(),
                    EMPTY_STRING);
        }
    }
}
