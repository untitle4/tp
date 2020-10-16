package seedu.duke.parser;

import seedu.duke.LogManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * To configure the Date and Time of the events from yyyy-mm-dd HHMM format to dd suffix mm yyyy, hh:mm aa format.
 */
public class DateTimeParser {

    private final String dateTime;
    private static final Logger logger = LogManager.getLogger();

    public DateTimeParser(String dateTime) {
        this.dateTime = dateTime;
    }

    public String changeDateTime() throws ParseException {
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
}

