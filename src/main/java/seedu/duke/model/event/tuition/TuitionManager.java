package seedu.duke.model.event.tuition;

import seedu.duke.Event;
import seedu.duke.common.LogManager;
import seedu.duke.common.Messages;
import seedu.duke.exception.EmptyTuitionInputException;
import seedu.duke.exception.InvalidTuitionInputException;
import seedu.duke.ui.UserInterface;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TuitionManager {
    private ArrayList<Event> tuitionList;
    private static final Logger logger = LogManager.getLogger();
    private UserInterface userInterface;

    public TuitionManager(ArrayList<Event> tuitionList) {
        this.tuitionList = tuitionList;
        userInterface = UserInterface.getInstance();
    }

    public ArrayList<Event> getTuitions() {
        return tuitionList;
    }

    public int getTuitionListSize() {
        return tuitionList.size();
    }

    public void addTuition(String userInput) throws InvalidTuitionInputException, EmptyTuitionInputException {
        logger.log(Level.INFO, "Initializing adding of a tuition");
        final String descriptionPrefix = "/n";
        final String startPrefix = "/s";
        final String endPrefix = "/e";
        final String locationPrefix = "/l";

        if ((!userInput.contains(descriptionPrefix)) || (!userInput.contains(startPrefix))
                || (!userInput.contains(endPrefix)) || (!userInput.contains(locationPrefix))) {
            logger.log(Level.WARNING, "either class description, start date-time or end date-time parameter is"
                    + " missing");
            throw new InvalidTuitionInputException();
        }

        final int indexOfDescriptionPrefix = userInput.indexOf(descriptionPrefix);
        final int indexOfStartPrefix = userInput.indexOf(startPrefix);
        final int indexOfEndPrefix = userInput.indexOf(endPrefix);
        final int indexOfLocationPrefix = userInput.indexOf(locationPrefix);

        String description = userInput.substring(indexOfDescriptionPrefix, indexOfStartPrefix)
                .replace(descriptionPrefix, "").trim();
        String start = userInput.substring(indexOfStartPrefix, indexOfEndPrefix)
                .replace(startPrefix, "").trim();
        String end = userInput.substring(indexOfEndPrefix, indexOfLocationPrefix)
                .replace(endPrefix, "").trim();
        String location = userInput.substring(indexOfLocationPrefix)
                .replace(locationPrefix, "").trim();

        if (isEmptyString(description) || isEmptyString(start) || isEmptyString(end) || isEmptyString(location)) {
            throw new EmptyTuitionInputException();
        }

        try {
            parseLocalDateTime(start);
            parseLocalDateTime(end);

            Tuition tuition = new Tuition(description, start, end, location);
            tuitionList.add(tuition);
            logger.log(Level.INFO, "Tuition added successfully");
            userInterface.showToUser(Messages.MESSAGE_TUITION_ADD_SUCCESS,
                    tuition.toString(),
                    getTuitionStatement());
        } catch (DateTimeParseException e) {
            userInterface.showToUser(Messages.MESSAGE_INVALID_DATE);
        }
    }

    public void deleteTuition(String[] userInput) {
        try {
            // Tries to convert classIndex user input into an integer
            int tuitionIndex = Integer.parseInt(userInput[2]);

            // Assertion to test assumption that classIndex should be a positive integer
            assert tuitionIndex > 0 : "tuitionIndex should be a positive integer";

            // Just to test if class index is valid - for exception use only
            tuitionList.get(tuitionIndex - 1);

            userInterface.showToUser("Noted. I've removed this tuition class: ",
                    tuitionList.get(tuitionIndex - 1).toString());

            // Deletes class from classes ArrayList
            tuitionList.remove(tuitionIndex - 1);
            logger.log(Level.INFO, "Deletion of tuition class from ArrayList");
            userInterface.showToUser(getTuitionStatement());
        } catch (ArrayIndexOutOfBoundsException e) {
            userInterface.showToUser(Messages.MESSAGE_TUITION_DELETE_ERROR_NO_NUMBER_GIVEN);
        } catch (IndexOutOfBoundsException e) {
            userInterface.showToUser(Messages.MESSAGE_INVALID_TUITION_INDEX);
        } catch (NumberFormatException e) {
            userInterface.showToUser(Messages.MESSAGE_TUITION_DELETE_ERROR_NON_NUMBER);
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
            userInterface.showToUser(Messages.MESSAGE_TUITION_DONE_ERROR_NON_NUMBER);
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            userInterface.showToUser(Messages.MESSAGE_TUITION_DONE_ERROR_NO_NUMBER_GIVEN);
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

        userInterface.showToUser(Messages.MESSAGE_TUITION_DONE_SUCCESS,
                "  " + tuitionList.get(tuitionNumber - 1),
                getTuitionStatement());
    }

    private String getTuitionStatement() {
        String tuitionStatement = getTuitionListSize() == 1 ? " class" : " classes";

        return "Now you have " + getTuitionListSize() + tuitionStatement + " in the list";
    }

    private boolean isEmptyString(String string) {
        return string.equals("");
    }

    private LocalDateTime parseLocalDateTime(String localDateTimeString) {
        return LocalDateTime.parse(localDateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}
