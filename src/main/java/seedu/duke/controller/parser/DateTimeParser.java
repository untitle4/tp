package seedu.duke.controller.parser;

import seedu.duke.common.LogManager;

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

/**
 * To configure the Date and Time of the events from yyyy-mm-dd HHMM format to dd suffix mm yyyy, hh:mm aa format.
 */
public class DateTimeParser {

    private static final Logger logger = LogManager.getLogManagerInstance().getLogger();

    public String changeDateTime(String dateTime) throws ParseException {
        logger.log(Level.INFO, "initialising changing of date and time to new format");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy");
        int divider = dateTime.indexOf(" ");

        logger.log(Level.INFO, "split string into date and time");
        String date = dateTime.substring(0, divider);

        logger.log(Level.INFO, "generating day suffix");
        final String[] dayInput = date.split("-");
        int dayInteger = Integer.parseInt(dayInput[2]);
        final String dayNumberSuffix = getDayNumberSuffix(dayInteger);

        logger.log(Level.INFO, "changing date format");
        LocalDate oldDate = LocalDate.parse(date);
        final String changedDate = oldDate.format(formatter);

        logger.log(Level.INFO, "changing time format");
        String time = dateTime.substring(divider + 1);
        DateFormat inputDateTimeFormat = new SimpleDateFormat("HHmm");
        DateFormat outputDateTimeFormat = new SimpleDateFormat("hh:mm aa");
        Date oldTime = null;
        String changedTime = null;
        oldTime = inputDateTimeFormat.parse(time);
        changedTime = outputDateTimeFormat.format(oldTime);

        logger.log(Level.INFO, "generating day");
        String dayString = String.valueOf(dayInteger);

        return dayString + dayNumberSuffix + " " + changedDate + " , " + changedTime;
    }

    /*
    ref to: https://stackoverflow.com/questions/4011075/how-do-you-format-the-day
    -of-the-month-to-say-11th-21st-or-23rd-ordinal
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

    public boolean isDateEqual(String listInput, String userInput) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        int listDay = LocalDate.parse(listInput, formatter).getDayOfMonth();
        int userDay = LocalDate.parse(userInput, formatter).getDayOfMonth();
        int listYear = LocalDate.parse(listInput, formatter).getYear();
        int userYear = LocalDate.parse(userInput, formatter).getYear();
        Month listMonth = LocalDate.parse(listInput, formatter).getMonth();
        Month userMonth = LocalDate.parse(userInput, formatter).getMonth();
        return ((listDay == userDay)
                && (listMonth.equals(userMonth))
                && (listYear == userYear));
    }

    public ArrayList<String> getDaysOfWeek() {
        ArrayList<String> dateStrings = new ArrayList<>();
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

        for (int i = 0; i < 7; i++) {
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            String date = year + "-" + (month + 1) + "-" + dayOfMonth;
            dateStrings.add(date);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return dateStrings;
    }
}

