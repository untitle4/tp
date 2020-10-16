package seedu.duke;

import seedu.duke.exception.EmptyTuitionInputException;
import seedu.duke.exception.InvalidTuitionInputException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TuitionManager {
    private ArrayList<Event> tuitionList;
    private static Logger logger = Logger.getLogger("tuition");

    public TuitionManager(ArrayList<Event> tuitionList) {
        this.tuitionList = tuitionList;
    }

    public ArrayList<Event> getTuitions() {
        return tuitionList;
    }

    public int getTuitionListSize() {
        return tuitionList.size();
    }

    public void addTuition(String userInput) throws InvalidTuitionInputException, EmptyTuitionInputException {
        logger.log(Level.INFO, "Initializing adding of a tuition");
        final String DESCRIPTION_PREFIX = "/n";
        final String START_PREFIX = "/s";
        final String END_PREFIX = "/e";
        final String LOCATION_PREFIX = "/l";

        if ((!userInput.contains(DESCRIPTION_PREFIX)) || (!userInput.contains(START_PREFIX))
                || (!userInput.contains(END_PREFIX)) || (!userInput.contains(LOCATION_PREFIX))) {
            logger.log(Level.WARNING, "either class description, start date-time or end date-time parameter is"
                    + " missing");
            throw new InvalidTuitionInputException();
        }

        final int indexOfDescriptionPrefix = userInput.indexOf(DESCRIPTION_PREFIX);
        final int indexOfStartPrefix = userInput.indexOf(START_PREFIX);
        final int indexOfEndPrefix = userInput.indexOf(END_PREFIX);
        final int indexOfLocationPrefix = userInput.indexOf(LOCATION_PREFIX);

        String description = userInput.substring(indexOfDescriptionPrefix, indexOfStartPrefix)
                .replace(DESCRIPTION_PREFIX, "").trim();
        String start = userInput.substring(indexOfStartPrefix, indexOfEndPrefix)
                .replace(START_PREFIX, "").trim();
        String end = userInput.substring(indexOfEndPrefix, indexOfLocationPrefix)
                .replace(END_PREFIX, "").trim();
        String location = userInput.substring(indexOfLocationPrefix)
                .replace(LOCATION_PREFIX, "").trim();

        if(isEmptyString(description) || isEmptyString(start) || isEmptyString(end) || isEmptyString(location)) {
            throw new EmptyTuitionInputException();
        }

        try {
            parseLocalDateTime(start);
            parseLocalDateTime(end);

            Tuition tuition = new Tuition(description, start, end, location);
            tuitionList.add(tuition);
            logger.log(Level.INFO, "Tuition added successfully");
            System.out.println("Got it. I've added this tuition: " + tuition);
            System.out.println(getTuitionStatement());
        } catch (DateTimeParseException e) {
            System.out.println("☹ OOPS!!! Please enter valid date and time in format yyyy-mm-dd HHMM!");
        }
    }

    public void deleteTuition(String[] userInput) {
        try {
            // Tries to convert classIndex user input into an integer
            int tuitionIndex = Integer.parseInt(userInput[2]);

            // Assertion to test assumption that classIndex should be a positive integer
            assert tuitionIndex > 0 : "classIndex should be a positive integer";

            // Just to test if class index is valid - for exception use only
            tuitionList.get(tuitionIndex - 1);

            System.out.println("Noted. I've removed this class: ");
            System.out.println(tuitionList.get(tuitionIndex - 1));

            // Deletes class from classes ArrayList
            tuitionList.remove(tuitionIndex - 1);
            logger.log(Level.INFO, "Deletion of class from ArrayList");
            System.out.println(getTuitionStatement());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS! Please indicate which class you'd like to delete");
            logger.log(Level.WARNING, "Absence of class index for deletion");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS! Please indicate a valid class index!");
            logger.log(Level.WARNING, "Invalid class index entered for deletion");
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS! Please indicate in NUMERALS, which class you'd like to delete!");
            logger.log(Level.WARNING, "Non-integer class index entered for deletion");
        }
    }

    public void setTuitionDone(String[] userInput) throws IndexOutOfBoundsException {
        int tuitionNumber;
        logger.log(Level.INFO, "Initialising setting tuition as done");

        try {
            // Trying to convert user's input into an integer
            tuitionNumber = Integer.parseInt(userInput[2]);
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Wrong number format entered");
            System.out.println("☹ OOPS!!! Please indicate in NUMERALS, which class you'd like to set as Done!");
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Please indicate which class you'd like to set as Done!");
            return;
        }

        // Checking if user's input is a valid class index integer
        if ((tuitionNumber <= 0) || (tuitionNumber > getTuitionListSize())) {
            logger.log(Level.WARNING, "index entered is out of bounds");
            throw new IndexOutOfBoundsException();
        }

        // Sets class as done
        tuitionList.get(tuitionNumber - 1).setDone();
        logger.log(Level.INFO, "set class as done from ArrayList");

        System.out.println("Nice! I've marked this class as done:");
        System.out.println("  " + tuitionList.get(tuitionNumber - 1));

        System.out.println(getTuitionStatement());
    }

    private String getTuitionStatement() {
        String tuitionStatement = getTuitionListSize() == 1 ? " class" : " classes";

        return "Now you have " + getTuitionListSize() + tuitionStatement + " in the list";
    }

    private boolean isEmptyString(String string) {
        return string.equals("");
    }

    private LocalDateTime parseLocalDateTime (String localDateTimeString) {
        return LocalDateTime.parse(localDateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}
