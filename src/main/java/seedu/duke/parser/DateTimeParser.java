package seedu.duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.text.ParseException;

/**
 * To configure the Date and Time of the events from yyyy-mm-dd HHMM format to dd suffix mm yyyy, hh:mm aa format.
 */
public class DateTimeParser {

    private final String dateTime;

    public DateTimeParser(String dateTime) {
        this.dateTime = dateTime;
    }

    public String changeDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy");
        int divider = dateTime.indexOf(" ");

        String date = dateTime.substring(0, divider);
        final String[] dayInput = date.split("-");
        int dayInteger = Integer.parseInt(dayInput[2]);
        String dayNumberSuffix = getDayNumberSuffix(dayInteger);

        LocalDate oldDate = LocalDate.parse(date);
        String changedDate = oldDate.format(formatter);

        String time = dateTime.substring(divider + 1);
        DateFormat inputDateTimeFormat = new SimpleDateFormat("HHmm");
        DateFormat outputDateTimeFormat = new SimpleDateFormat("hh:mm aa");
        Date oldTime = null;
        String changedTime = null;
        try {
            oldTime = inputDateTimeFormat.parse(time);
            changedTime = outputDateTimeFormat.format(oldTime);
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }

        String dayString = String.valueOf(dayInteger);

        return dayString + dayNumberSuffix + " " + changedDate + " , " + changedTime;
    }

    /*
    ref to: https://stackoverflow.com/questions/4011075/how-do-you-format-the-day
    -of-the-month-to-say-11th-21st-or-23rd-ordinal
     */
    private String getDayNumberSuffix(int day) {
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

