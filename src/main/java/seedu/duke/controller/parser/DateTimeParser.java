package seedu.duke.controller.parser;

import seedu.duke.common.LogManager;
import seedu.duke.model.event.Event;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author Aliciaho
/**
 * To configure the Date and Time of the events from yyyy-mm-dd HHMM format to dd suffix mm yyyy, hh:mm aa format.
 */
public class DateTimeParser {

    private static final Logger logger = LogManager.getLogManagerInstance().getLogger();

    /**
     * Get the suffix for each day.
     * ref to: https://stackoverflow.com/questions/4011075/how-do-you-format-the-day-of-the-month-to-say-11th-21st-or-23rd-ordinal
     * @param day day number of the month
     * @return respective suffix for the day inputted
     */
    private String getDayNumberSuffix(int day) {
        assert day > 0;
        assert day < 31;

        if (day >= 11 && day <= 13) {
            return "th";
        }
        switch (day % 10) {
        case 1:
            return "st";
        case 2:
            return "nd";
        case 3:
            return "rd";
        default:
            return "th";
        }
    }

    /**
     * Convert time from HH:mm format to hh:mma format.
     *
     * @param calendar date time inputted
     * @return the time in hh:mma format
     */
    public String parseTime(Calendar calendar) {
        logger.log(Level.INFO, "converting time to hh:mma");
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mma");
        return sdf.format(calendar.getTime());
    }

    /**
     * Convert day and month into MM-yyyy format.
     *
     * @param calendar date time inputted
     * @return the date in MM-yyyy format
     */
    public String parseDayAndMonth(Calendar calendar) {
        logger.log(Level.INFO, "converting date to MM-yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("MM-yyyy");
        return sdf.format(calendar.getTime());
    }

    /**
     * Convert a string input to date time format yyyy-MM-dd HHmm in Calendar form.
     *
     * @param string user input string
     * @return calendar date time in yyyy-MM-dd HHmm in Calendar form
     * @exception ParseException exception thrown when valid date and time is not inputted
     */
    public Calendar convertStringToCalendar(String string) {
        logger.log(Level.INFO, "converting string to calendar");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HHmm");
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = sdf.parse(string);
            calendar.setTime(date);
        } catch (ParseException parseException) {
            logger.log(Level.WARNING, "valid datetime not inputted");
            System.out.println("☹ OOPS!!! Please enter today/week/valid date "
                    + "and time in format yyyy-mm-dd!");
        }
        return calendar;
    }

    /**
     * Convert a string input to date time format yyyy-MM-dd HHmm in Calendar form.
     *
     * @param string user input string
     * @return calendar date time in yyyy-MM-dd HHmm in Calendar form
     * @exception ParseException exception thrown when valid date and time is not inputted
     */
    public Calendar convertStringToCalendarByDate(String string) {
        logger.log(Level.INFO, "converting string to calendar");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = sdf.parse(string);
            calendar.setTime(date);
        } catch (ParseException parseException) {
            logger.log(Level.WARNING, "valid datetime not inputted");
            System.out.println("☹ OOPS!!! Please enter today/week/valid date "
                    + "and time in format yyyy-mm-dd!");
        }
        return calendar;
    }


    /**
     * Convert a Calendar form in date time format yyyy-MM-dd HHmm to String.
     *
     * @param calendar date time input in Calendar form
     * @return calendar date time input in String form
     */
    public String convertCalendarToString(Calendar calendar) {
        logger.log(Level.INFO, "converting calendar to string");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HHmm");

        return sdf.format(calendar.getTime());
    }

    /**
     * Add day suffix to date time string.
     *
     * @param calendar date time in calendar form
     * @return date time string with day suffix
     */
    public String obtainFormattedDateTimeString(Calendar calendar) {
        logger.log(Level.INFO, "converting to MMM yyyy, hh:mma format");
        SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy, hh:mma");
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        logger.log(Level.INFO, "getting day suffix");
        String dayOfMonthString = dayOfMonth + getDayNumberSuffix(dayOfMonth);
        String monthAndYearString = sdf.format(calendar.getTime());

        return dayOfMonthString + " " + monthAndYearString;
    }

    /**
     * Convert date time from Calendar form to dd-MM-yyyy, hh:mma format in String form.
     *
     * @param calendar date time in Calendar form
     * @return dd-MM-yyyy, hh:mma format in String form
     */
    public String obtainFormattedDateString(Calendar calendar) {
        logger.log(Level.INFO, "converting to dd-MM-yyyy, hh:mma format");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy, hh:mma");

        return sdf.format(calendar.getTime());
    }

    /**
     * Convert date time in Calendar form to dd-MM in String form.
     *
     * @param calendar date time in Calendar form
     * @return dd-MM in String form
     */
    public String obtainFormattedDayAndMonthString(Calendar calendar) {
        logger.log(Level.INFO, "converting to dd-MM format");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM");

        return sdf.format(calendar.getTime());
    }

    /**
     * Check if two dates are equal.
     *
     * @param listInput date time input from List
     * @param userInput date time input by user
     * @return true if both dates are equal
     * @throws DateTimeParseException if valid datetime is not inputted
     */
    public boolean isDateEqual(Calendar listInput, Calendar userInput) throws DateTimeParseException {
        logger.log(Level.INFO, "checking if dates are equal");
        int listDay = listInput.get(Calendar.DAY_OF_MONTH);
        int userDay = userInput.get(Calendar.DAY_OF_MONTH);
        int listMonth = listInput.get(Calendar.MONTH);
        int userMonth = userInput.get(Calendar.MONTH);
        int listYear = listInput.get(Calendar.YEAR);
        int userYear = userInput.get(Calendar.YEAR);
        return (listDay == userDay)
                && (listMonth == userMonth)
                && (listYear == userYear);
    }

    /**
     * Get the dates for this week.
     *
     * @return dateCalendars ArrayList containing the dates for this week
     */
    public ArrayList<Calendar> getDaysOfWeek() {
        logger.log(Level.INFO, "getting days of the week");
        ArrayList<Calendar> dateCalendars = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
        case Calendar.MONDAY:
            calendar.add(Calendar.DAY_OF_MONTH, 0);
            break;
        case Calendar.TUESDAY:
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            break;
        case Calendar.WEDNESDAY:
            calendar.add(Calendar.DAY_OF_MONTH, -2);
            break;
        case Calendar.THURSDAY:
            calendar.add(Calendar.DAY_OF_MONTH, -3);
            break;
        case Calendar.FRIDAY:
            calendar.add(Calendar.DAY_OF_MONTH, -4);
            break;
        case Calendar.SATURDAY:
            calendar.add(Calendar.DAY_OF_MONTH, -5);
            break;
        case Calendar.SUNDAY:
            calendar.add(Calendar.DAY_OF_MONTH, -6);
            break;
        default:
            break;
        }

        logger.log(Level.INFO, "adding days of week to dateCalendars");
        for (int i = 0; i < 7; i++) {
            Calendar newCalendar = (Calendar) calendar.clone();
            dateCalendars.add(newCalendar);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return dateCalendars;
    }
}

